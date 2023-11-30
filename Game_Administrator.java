import java.io.FileWriter;
import java.io.IOException;

/**
 * The Administrator class extends the User class and represents a Game Administrator.
 * This class is used to create the admin and generate the statistics file into a file named stats.txt
 *
 * @author Edgar Arellano
 */
public class Game_Administrator extends User {

    /**
     * Constructor for Game Administrator.
     * @param username 
     * @param pin 
     */
    public Game_Administrator(String username, String pin) {
        super();
        super.setUsername(username);
        super.setPin(pin);
    }

      /**
     * This method generates a statistics file for a specific user and writes it to stats.txt
     *
     * @param user
     */
    public void generateAndWriteStatisticsFile(User user) {

        String statisticsFile = "Statistics for User: " + user.getUsername() + "\n";
        statisticsFile += "Last Sign In Time: " + user.getLastSignIn() + "\n";
        statisticsFile += "Number of Battles Won: " + user.getBattlesWon() + "\n";
        statisticsFile += "Number of Battles Lost: " + user.getBattlesLost() + "\n";
        statisticsFile += "Number of Times Users Finished the Game: " + user.getGameCompletions() + "\n";
        statisticsFile += "Number of Items Picked Up Overall: " + user.getItemTotalNum() + "\n";

        // Writes the statistics to stats.txt
        String filePath = user.getUsername()+"Stats.txt";
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(statisticsFile);
            System.out.println("Statistics file created successfully at: " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing statistics file: " + e.getMessage());
        }
    }
}
