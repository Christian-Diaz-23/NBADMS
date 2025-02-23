public class NBAPlayer {
    private String playerNumber;
    private String name;
    private String team;
    private int points;
    private int rebounds;
    private int assists;

    public NBAPlayer(String playerNumber, String name, String team, int points, int rebounds, int assists) {
        this.playerNumber = playerNumber;
        this.name = name;
        this.team = team;
        this.points = points;
        this.rebounds = rebounds;
        this.assists = assists;
    }

    public String getPlayerNumber() {
        return playerNumber;
    }

    public String toCSV() {
        return playerNumber + "," + name + "," + team + "," + points + "," + rebounds + "," + assists;
    }

    @Override
    public String toString() {
        return "Player Number: " + playerNumber + ", Name: " + name + ", Team: " + team +
                ", Points: " + points + ", Rebounds: " + rebounds + ", Assists: " + assists;
    }
}
