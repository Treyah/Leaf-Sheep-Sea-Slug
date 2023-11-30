import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * The Utility class contains methods to read files 'Dungeon.csv' and 'users.csv' and methods that 
 * check the username and password inputed by the user. If no user is found
 * another method will allow the user to register 
 * @author Edgar Arellano
 * 
 */

class Utility {
    private Map<String, Map<String, String>> userDataMap;
    private static final Path updated_users = Paths.get("updatedUsers.csv");

    public void userDataFile(String userFile){
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

        this.userDataMap = userData;
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
     * This method returns all the info of a certain user
     * @param targetUser is the person whose info we are trying to get
     */

     public User getUser(String targetUser) {
         Map<String, String> userInfo = userDataMap.get(targetUser);
         User user = new User();
         user.setState(userInfo.get("State"));
         user.setLastSignIn(userInfo.get("LastSignIn"));
         user.setUsername(userInfo.get("Username"));
         user.setFirstName(userInfo.get("First Name"));
         user.setLoginTime(userInfo.get("LogInTime"));
         user.setPin(userInfo.get("PIN"));
         user.setLastName(userInfo.get("Last Name"));
         user.setTotalPlaytime(userInfo.get("TotalPlaytime"));
         user.setCity(userInfo.get("City"));
         user.setZip(userInfo.get("Zip"));
         user.setDOB(userInfo.get("Date of Birth"));

         return user;
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
     * updateUsers creates if not already existing new file with the updates on users
     */
    public void updateUsersFile(){
        String text = "State,LastSignIn,Username,First Name,LogInTime,PIN,Last Name,TotalPlaytime,City,Zip,Date of Birth, battlesWon, battlesLost, itemTotalNum, userGameCompletions\n";

        try{
            if (!Files.exists(updated_users)) {
                Files.createFile(updated_users);
            }

            FileWriter writer = new FileWriter("updatedUsers.csv");
            writer.write(text);
            writer.close();

            FileWriter fw = new FileWriter("updatedUsers.csv", true);

            for(Map.Entry<String, Map<String, String>> entry: userDataMap.entrySet()){
                String user = entry.getKey();
                Map<String, String> userInfo = userDataMap.get(user);
                String info = userInfo.get("State") + "," + userInfo.get("LastSignIn") + "," + userInfo.get("Username") + "," + userInfo.get("First Name") + "," + userInfo.get("LogInTime") + "," + userInfo.get("PIN")
                        + "," + userInfo.get("Last Name") + "," + userInfo.get("TotalPlaytime") + "," + userInfo.get("City") + "," + userInfo.get("Zip") + "," + userInfo.get("Date of Birth")
                        + "," + userInfo.get("battlesWon") + "," + userInfo.get("battlesLost") + "," + userInfo.get("itemTotalNum") + "," + userInfo.get("userGameCompletions") + "\n";
                fw.write(info);
            }

            fw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
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
        newUser.put("State", state);
        newUser.put("LastSignIn", "0");
        newUser.put("Username", username);
        newUser.put("First Name", firstname);
        newUser.put("LogInTime", "0");
        newUser.put("PIN", password);
        newUser.put("Last Name", lastName);
        newUser.put("TotalPlaytime", "0");
        newUser.put("City", city);
        newUser.put("Zip", zip);
        newUser.put("Date of Birth", dateOfBirth);

        userDataMap.put(username, newUser);

        updateUsersFile();
    }

     /**
     * Creates a saveFile for the game
     * @param username  
     * @param dungeon   
     */
    public void saveGame(String username, Dungeon dungeon, Player player) {
        try {
            // Save Dungeon State
            String dungeonSaveFile = username + "savedDungeon.csv";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(dungeonSaveFile))) {
                saveDungeonState(writer, dungeon.getMap());
            }

            // Save Player Attributes
            String playerAttributesSaveFile = username + "Player.csv";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(playerAttributesSaveFile))) {
                savePlayerAttributes(writer, player);
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
     * @param player  The player attributes to be saved.
     * @throws IOException
     */
    private void savePlayerAttributes(BufferedWriter writer, Player player) throws IOException {
        writer.write("Name,attackPower,HP,poison,maxHP,gold,position");
        writer.newLine();
        int[] coords = player.getPosition();
        writer.write(player.getName()+","+player.getAttackPower()+","+player.getHP()+","+player.getPoison()+","+player.getMaxHP()+","+player.getGold()+","+coords[0]+","+coords[1]);
        writer.newLine();
        writer.write(player.getInventory());
        
    }

    /**
     * Helper method to save the state of the dungeon map.
     * 
     * @param writer  
     * @param dungeonMap  
     * @throws IOException 
     */
    private void saveDungeonState(BufferedWriter writer, Map<String, Map<String, String>> dungeonMap) throws IOException {

        for (int row = 0; row < 20; row++) {
            Map<String, String> rowMap = dungeonMap.get(String.valueOf(row));

            if (rowMap != null) {
                List<String> sortedColumns = new ArrayList<>(rowMap.keySet());
                Collections.sort(sortedColumns);

                for (int col = 0; col < 24; col++) {
                    String colKey = String.valueOf(col);
                    String cellValue = rowMap.get(colKey);

                    writer.write(cellValue + ",");
                }
            }
            writer.newLine();
        }

    }

    public List<Enemy> getEnemies() {
        List<Enemy> enemies = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("Enemies(1).csv"))) {
            String header = reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] enemy = line.split(",");
                Enemy enemy_character = new Enemy(enemy[0], Integer.parseInt(enemy[1]), Integer.parseInt(enemy[2]));
                enemies.add(enemy_character);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return enemies;
    }

    /**
     * This method read a given file where the attributes of a character
     * were saved and create a new Player class to continue a saved game
     * @param file the file containing the saved attributes
     * @return Player ready to continue game
     */
    public Player getSavedPlayer(String file) {
        Player player = new Player("");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String header = reader.readLine();

            String line;
            line = reader.readLine();
            String[] playerInfo = line.split(",");
            player.set_name(playerInfo[0]);
            player.setAttackPower(Integer.parseInt(playerInfo[1]));
            player.setHP(Integer.parseInt(playerInfo[2]));
            player.setPoison(Boolean.parseBoolean(playerInfo[3]));
            player.setMaxHP(Integer.parseInt(playerInfo[4]));
            player.setGold(Integer.parseInt(playerInfo[5]));
            player.setPosition(Integer.parseInt(playerInfo[6]),Integer.parseInt(playerInfo[7]));
            line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] inventory = line.split(",");
                System.out.println(inventory[0]);
                switch (inventory[0]) {
                    case "Sword Upgrade" -> player.addItem(new Sword(inventory[0]), 1);
                    case "Health Upgrade" -> player.addItem(new Heart(inventory[0]), 1);
                    case "Clear Potion" -> player.addItem(new clearPotion(inventory[0]), 1);
                    case "Health Potion" -> player.addItem(new healthPotion(inventory[0]), 1);
                    case "Smoke Bomb" -> player.addItem(new smokeBomb(inventory[0]),1);
                    case "Coins" -> player.addItem(new Coin(inventory[0]),1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return player;
    }

    /**
     * updates a user on the attributes gained during playtime
     * @param user to update
     */
    public void updateUser(User user) {
        Map<String, String> userInfo = userDataMap.get(user.getUsername());
        userInfo.put("LastSignIn", user.getLastSignIn());
        userInfo.put("LogInTime", user.getLoginTime());
        userInfo.put("TotalPlaytime", user.getTotalPlaytime());
        userInfo.put("battlesWon" , String.valueOf(user.getBattlesWon()));
        userInfo.put("battlesLost", String.valueOf(user.getBattlesLost()));
        userInfo.put("itemTotalNum", String.valueOf(user.getItemTotalNum()));
        userInfo.put("userGameCompletions", String.valueOf(user.getGameCompletions()));
        updateUsersFile();
    }
}
