import java.io.*;
import java.util.*;

public class NBAPlayerManager {
    private static final String FILE_NAME = "nba_player_stats.txt";

    public static void displayMenu() {
        System.out.println("\nNBA Player Stats Management System");
        System.out.println("1. Add Player Stats");
        System.out.println("2. View Player Stats");
        System.out.println("3. Update Player Stats");
        System.out.println("4. Delete Player Stats");
        System.out.println("5. Add Players from File");
        System.out.println("6. Exit");
    }

    public static String getStringInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (input.isEmpty() || input.matches(".*\\d.*")) {
                System.out.println("That's not a valid string. Try again.");
            } else {
                return input;
            }
        }
    }

    public static int getNumericInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("That's not a valid number. Try again.");
            }
        }
    }

    public static void addPlayer() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            String playerNumber = String.valueOf(getNumericInput("Enter Player Number: "));
            String name = getStringInput("Enter Player Name: ");
            String team = getStringInput("Enter Team: ");
            int points = getNumericInput("Enter Points: ");
            int rebounds = getNumericInput("Enter Rebounds: ");
            int assists = getNumericInput("Enter Assists: ");

            NBAPlayer player = new NBAPlayer(playerNumber, name, team, points, rebounds, assists);
            writer.write(player.toCSV() + "\n");
            System.out.println("Player stats added successfully!");
        } catch (IOException e) {
            System.out.println("Error adding player: " + e.getMessage());
        }
    }

    public static void viewPlayers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                found = true;
            }
            if (!found) System.out.println("No records found.");
        } catch (IOException e) {
            System.out.println("Error viewing players: " + e.getMessage());
        }
    }

    public static void updatePlayer() {
        String playerNumber = String.valueOf(getNumericInput("Enter Player Number to update: "));
        File file = new File(FILE_NAME);
        List<String> players = new ArrayList<>();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(playerNumber)) {
                    System.out.println("Current Data: " + line);
                    data[1] = getStringInput("Enter New Name: ");
                    data[2] = getStringInput("Enter New Team: ");
                    data[3] = String.valueOf(getNumericInput("Enter New Points: "));
                    data[4] = String.valueOf(getNumericInput("Enter New Rebounds: "));
                    data[5] = String.valueOf(getNumericInput("Enter New Assists: "));
                    players.add(String.join(",", data));
                    found = true;
                } else {
                    players.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error updating player: " + e.getMessage());
        }

        if (found) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (String player : players) {
                    writer.write(player + "\n");
                }
                System.out.println("Player stats updated successfully!");
            } catch (IOException e) {
                System.out.println("Error saving updates: " + e.getMessage());
            }
        } else {
            System.out.println("Player Number not found.");
        }
    }

    public static void deletePlayer() {
        String playerNumber = String.valueOf(getNumericInput("Enter Player Number to delete: "));
        File file = new File(FILE_NAME);
        List<String> players = new ArrayList<>();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(playerNumber + ",")) {
                    players.add(line);
                } else {
                    found = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error deleting player: " + e.getMessage());
        }

        if (found) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (String player : players) {
                    writer.write(player + "\n");
                }
                System.out.println("Player stats deleted successfully!");
            } catch (IOException e) {
                System.out.println("Error saving deletion: " + e.getMessage());
            }
        } else {
            System.out.println("Player Number not found.");
        }
    }

    public static void addPlayersFromFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path of the text file: ");
        String filePath = scanner.nextLine();
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("File not found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.split(",").length == 6) {
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
                        writer.write(line + "\n");
                    }
                } else {
                    System.out.println("Skipping invalid line: " + line);
                }
            }
            System.out.println("Players added from file successfully!");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
