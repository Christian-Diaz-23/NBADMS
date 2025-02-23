import java.util.Scanner;

public class NBAApp{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            NBAPlayerManager.displayMenu();
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1": NBAPlayerManager.addPlayer(); break;
                case "2": NBAPlayerManager.viewPlayers(); break;
                case "3": NBAPlayerManager.updatePlayer(); break;
                case "4": NBAPlayerManager.deletePlayer(); break;
                case "5": NBAPlayerManager.addPlayersFromFile(); break;
                case "6": System.out.println("Exiting..."); return;
                default: System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
