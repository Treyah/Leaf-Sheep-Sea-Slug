import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The Utility class contains methods to read files 'Dungeon.csv' and 'users.csv' and methods that 
 * check the username and password inputed by the user. If no user is found
 * another method will allow the user to register 
 * @author Edgar Arellano
 * 
 */

class Utility {
    private Map<String, Map<String, String>> userDataMap;

    public void userDataFile(String userFile){
        this.userDataMap = readUserInfo(userFile);
    }

     /**
     * This method reads the dungeon file "Dungeon.csv" and stores into an array
     * @param dungeonFile
     * @return this method returns the map after reading the 'Dungeon.csv' file
     */
    public Map<String, Map<String, String>> readDungeon(String dungeonFile) {
        Map<String, Map<String, String>> dungeonMap = new LinkedHashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(dungeonFile))) {
            String line;
            int rowNumber = 0;

            while ((line = reader.readLine()) != null) {
                String[] rowData = line.split(",");
                Map<String, String> rowMap = new HashMap<>();

                for (int col = 0; col < rowData.length; col++) {
                    rowMap.put(String.valueOf(col), rowData[col].trim());
                }
                dungeonMap.put(String.valueOf(rowNumber), rowMap);
                rowNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dungeonMap;
    }
       
    /**
     * This method reads the file 'Users.csv' and stores in a HashMap
     * @param userFile
     */

     public Map<String, Map<String, String>> readUserInfo(String userFile) {
        Map<String, Map<String, String>> userData = new LinkedHashMap<>();
    
        try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
            String header = reader.readLine();
            String[] headerColumns = header.split(",");
    
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userInfo = line.split(",");
                Map<String, String> userInfoMap = new HashMap<>();
    
                if (userInfo.length == headerColumns.length) {
                    for (int i = 0; i < headerColumns.length; i++) {
                        userInfoMap.put(headerColumns[i].trim(), userInfo[i].trim());
                    }
                    userData.put(userInfoMap.get("Username"), userInfoMap);
                } 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userData;
    }

    /**
     * This method checks the username and password entered by the player and returns a boolean upon 
     * success or failure
     * 
     * @param targetUsername
     * @param targetPassword
     * @return boolean depending on successful user and password
     */

     public boolean checkUser(String targetUsername, String targetPassword) {
        Map<String, String> userInfo = userDataMap.get(targetUsername);

        return userInfo != null && userInfo.get("PIN").equals(targetPassword);
    }

    /**
     * Allows the user to register as new user with the parameters as inputs
     * 
     * @param state
     * @param username
     * @param firstname
     * @param password
     * @param lastName
     * @param city
     * @param zip
     * @param dateOfBirth
     */

    public void registerUser(String state, String username, String firstname, String password, String lastName, String city, String zip, String dateOfBirth) {
        Map<String, String> newUser = new HashMap<>();
        newUser.put("state", state);
        newUser.put("username", username);
        newUser.put("firstname", firstname);
        newUser.put("password", password);
        newUser.put("lastName", lastName);
        newUser.put("city", city);
        newUser.put("zip", zip);
        newUser.put("dateOfBirth", dateOfBirth);

        userDataMap.put(username, newUser);
    }

     /**
     * Creates a saveFile for the game
     * @param username  
     * @param dungeon   
     */
    public void saveGame(String username, Dungeon dungeon, Player playerAttributes) {
        try {
            // Save Dungeon State
            String dungeonSaveFile = username + "savedDungeon.csv";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(dungeonSaveFile))) {
                saveDungeonState(writer, dungeon.getMap());
            }

            // Save Player Attributes
            String playerAttributesSaveFile = username + "Player.csv";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(playerAttributesSaveFile))) {
                savePlayerAttributes(writer, playerAttributes);
            }

            System.out.println("Game saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error saving the game.");
        }
    }

    /**
     * Helper method to save the attributes of the player.
     *
     * @param writer  
     * @param playerAttributes  The player attributes to be saved.
     * @throws IOException 
     */
    private void savePlayerAttributes(BufferedWriter writer, Player playerAttributes) throws IOException {
    
        writer.write("HealthPoints," + playerAttributes.getHP());
        writer.newLine();
        writer.write("Inventory," + playerAttributes.getInventory());
        writer.newLine();
        writer.write("StatusEffects," + playerAttributes.getStatusEffects());
        writer.newLine();
        
    }

    /**
     * Helper method to save the state of the dungeon map.
     * 
     * @param writer  
     * @param dungeonMap  
     * @throws IOException 
     */
    private void saveDungeonState(BufferedWriter writer, Map<String, Map<String, String>> dungeonMap) throws IOException {
        for (Map.Entry<String, Map<String, String>> entry : dungeonMap.entrySet()) {
            String rowKey = entry.getKey();
            Map<String, String> rowMap = entry.getValue();

            for (Map.Entry<String, String> cellEntry : rowMap.entrySet()) {
                String colKey = cellEntry.getKey();
                String cellValue = cellEntry.getValue();

                writer.write(rowKey + "," + colKey + "," + cellValue);
                writer.newLine();
            }
        }
    }
}
