import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * This is the Dungeon class that sets and gets the dungeon map from the utility class
 * , prints the dungeon map, checks for player moves, and updates the dungeon if needed
 * @author Edgar Arellano
 */
class Dungeon {

    private Map<String, Map<String, String>> map;
    private List<Enemy> enemies = new ArrayList<>();

    /**
     * This method sets the dungeonMap gathered from the Utility Class
     * @param dungeonMap
     */
    public void setMap(Map<String, Map<String, String>> dungeonMap) {
        this.map = dungeonMap;
    }

    /**
     * Getter method to return the map
     * @return this method returns the dungeon map
     */
    public Map<String, Map<String, String>> getMap() {
        return map;
    }

    /**
     * Print method to print out the dungeon map
     *
     */
    public void printMap() {
        System.out.println("------------------------------------------------------------------");

        for (int row = 0; row < 20; row++) {
            Map<String, String> rowMap = map.get(String.valueOf(row));

            if (rowMap != null) {
                List<String> sortedColumns = new ArrayList<>(rowMap.keySet());
                Collections.sort(sortedColumns);

                for (int col = 0; col < 24; col++) {
                    String colKey = String.valueOf(col);
                    String cellValue = rowMap.get(colKey);

                    switch (cellValue) {
                        case "-1":
                            System.out.print("| ");
                            break;
                        case "0":
                            System.out.print("0 ");
                            break;
                        case "e":
                            System.out.print("e ");
                            break;
                        case "$":
                            System.out.print("$ ");
                            break;
                        default:
                            System.out.print(cellValue + " ");
                    }
                }
            }


            System.out.println();
        }
        System.out.println("------------------------------------------------------------------");
    }

    /**
     * Checks if the next move by the player is possible.
     *
     * @param row
     * @param col
     * @return true if the move is possible, false otherwise.
     */
    public boolean isMovePossible(int row, int col) {
        if (map == null || row < 0 || row >= map.size()) {
            return false;
        }

        Map<String, String> rowMap = map.get(String.valueOf(row));
        if (rowMap == null || col < 0 || col >= rowMap.size()) {
            return false;
        }

        // Checks if the cell contains a wall
        String cellValue = rowMap.get(String.valueOf(col));
        return cellValue != null && !cellValue.equals("-1");
    }

    /**
     * This method updates the cells that the player has explored.
     * Cells are initially set as "u" for unexplored
     * and when the player explores the cell, it is set to "x".
     *
     * @param playerRow The row coordinate of the player.
     * @param playerCol The column coordinate of the player.
     */
    public void updateExploredCell(int playerRow, int playerCol) {
        if (map != null) {
            for (Map.Entry<String, Map<String, String>> entry : map.entrySet()) {
                String rowKey = entry.getKey();
                Map<String, String> rowMap = entry.getValue();

                for (Map.Entry<String, String> cellEntry : rowMap.entrySet()) {
                    String colKey = cellEntry.getKey();
                    if (Integer.parseInt(rowKey) == playerRow && Integer.parseInt(colKey) == playerCol) {
                        // If the cell has been explored, it will appear as "x"
                        rowMap.put(colKey, "x");
                    }
                }
            }
        }
    }


    /**
     * Updates the player's position and displays it in the dungeon.
     *
     * @param playerRow The new row coordinate of the player.
     * @param playerCol The new column coordinate of the player.
     */
    public void updatePlayerPosition(int playerRow, int playerCol) {
        if (isMovePossible(playerRow, playerCol)) {
            // Updates the player's position in the dungeon
            map.get(String.valueOf(playerRow)).put(String.valueOf(playerCol), "$");

        } else {
            System.out.println("Invalid move. Player position remains unchanged.");
        }
    }

    /**
     * Randomize items in a chest at the specified location.
     */
    public void generateRandomItem(Player player) {
        List<String> items = new ArrayList<>(Arrays.asList("Sword Upgrade", "Health Upgrade", "Clear Potion", "Health Potion", "Smoke Bomb", "Coins"));
        Random random = new Random();
        String randomItem = items.get(random.nextInt(items.size()));

        System.out.println("You found: " + randomItem);

        switch (randomItem) {
            case "Sword Upgrade" -> player.addItem(new Sword(randomItem), 1);
            case "Health Upgrade" -> player.addItem(new Heart(randomItem), 1);
            case "Clear Potion" -> player.addItem(new clearPotion(randomItem), 1);
            case "Health Potion" -> player.addItem(new healthPotion(randomItem), 1);
            case "Smoke Bomb" -> player.addItem(new smokeBomb(randomItem),1);
            case "Coins" -> player.addItem(new Coin(randomItem),1);
        }
    }

    public void setEnemies(List<Enemy> enemies){
        this.enemies = enemies;
    }

    public Enemy generateEnemy() {
        Random rand = new Random();
        int enemyRand = rand.nextInt(21);
        if(enemyRand < 6){
            return enemies.get(0);
        }else if(enemyRand < 11){
            return enemies.get(1);
        }else if(enemyRand < 15){
            return enemies.get(2);
        }else if(enemyRand < 18){
            return enemies.get(3);
        }else if(enemyRand < 20){
            return enemies.get(4);
        }else{
            return enemies.get(5);
        }
    }
}