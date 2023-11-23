import java.util.HashMap;
import java.util.Map;

/**
 * Represents a player character in a dungeon crawler game.
 * Tracks things like player character stats, positions, and
 * handles some actions related to dungeon crawling like
 * attacking, looting, and leveling up
 *
 * @author Kelcey Calderon
 */
public class Player extends Interface {

    private int attackBonus = 5;
    private int defense;
    private int HP = 5;
    private int XP;
    private int gold;
    private int[] position;
    private int level;
    private boolean poison;

    /*
     Example implementation of an inventory - use a hashmap:
     https://stackoverflow.com/questions/74963886/objects-as-keys-in-a-hashmap-how-to-reference-them-java
     */
    private Map<Item, Integer> inventory = new HashMap<>();

    /**
     * Initializes a new player with the given name.
     *
     * @param name the name of the player.
     */
    public Player(String name) {
        super(name);
        int[] coord = {8, 19};
        this.position = coord;
    }

    // getters and setters

    /** @return the attack bonus of the player. */
    public int getAttackBonus() {
        return attackBonus;
    }

    /** @return the defense value of the player. */
    public int getDefense() {
        return defense;
    }

    /** @return the hit points (HP) of the player. */
    public int getHP() {
        return HP;
    }

    /** @return the experience points (XP) of the player. */
    public int getXP() {
        return XP;
    }

    /** @return the amount of gold the player has. */
    public int getGold() {
        return gold;
    }

    /** @return the position of the player in the dungeon as an array of size 2. */
    public int[] getPosition() {
        return position;
    }

    /** @return the level of the player. */
    public int getLevel() {
        return level;
    }

    /** @return status of the poison condition */
    public boolean getPoison() {
        return poison;
    }

    /**
     * Sets the attack bonus of the player.
     *
     * @param attackBonus the attack bonus to set.
     */
    public void setAttackBonus(int attackBonus) {
        this.attackBonus = attackBonus;
    }

    /**
     * Sets the defense value of the player.
     *
     * @param defense the defense value to set.
     */
    public void setDefense(int defense) {
        this.defense = defense;
    }

    /**
     * Sets the hit points (HP) of the player.
     *
     * @param HP the hit points to set.
     */
    public void setHP(int HP) {
        this.HP = HP;
    }

    /**
     * Sets the experience points (XP) of the player.
     *
     * @param XP the experience points to set.
     */
    public void setXP(int XP) {
        this.XP = XP;
    }

    /**
     * Sets the amount of gold the player has.
     *
     * @param gold the gold amount to set.
     */
    public void setGold(int gold) {
        this.gold = gold;
    }

    /**
     * Sets the position of the player in the dungeon.
     *
     * @param x the x-coordinate of the position.
     * @param y the y-coordinate of the position.
     */
    public void setPosition(int x, int y) {
        this.position[0] = x;
        this.position[1] = y;
    }

    /**
     * Sets the level of the player.
     *
     * @param level the level to set.
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Sets the poison condition on the player
     * @param poison whether or not the poison condition is on the player
     */
    public void setPoison(boolean poison) {
        this.poison = poison;
    }

    // Methods

    /**
     * Handles the player leveling up.
     * Should be called when needed in gainXP
     */
    public void levelUp() {
        // Stub
        Log.msg("Player leveled up.");
    }

    /** Handles the player's attack. */
    public void attack(Enemy enemy) {
        enemy.setHP(enemy.getHP()-attackBonus);
        Log.msg("Player attacked");
    }

    /** Handles the player looting defeated enemies. */
    public void loot() {
        // Stub
        Log.msg("Player looted some items");
    }

    /** Handles the player's rest action. */
    public void rest() {
        // Stub
        Log.msg("Player is resting");
    }

    /** Handles the player gaining experience. */
    public void gainXP() {
        // Stub
        Log.msg("Player gained experience");
    }

    /** Handles opening the player's inventory */
    public void openInventory() {
        // Stub
        Log.msg("Player opened inventory");
    }

    /* 
    How to handle getting things from hashmaps:
    https://www.geeksforgeeks.org/hashmap-getordefaultkey-defaultvalue-method-in-java-with-examples/
    */

    /**
     * 
     * @param item item to add to inventory
     * @param quantity how many to add
     */
    public void addItem(Item item, int quantity) {
        inventory.put(item, inventory.getOrDefault(item, 0) + quantity);
    }

    /**
     * 
     * @param item item to remove from inventory
     * @param quantity how many to remove
     */
    public void removeItem(Item item, int quantity) {
        int currentQty = inventory.getOrDefault(item, 0);
        if (currentQty > quantity) {
            inventory.put(item, currentQty - quantity);
        } else {
            inventory.remove(item);
        }
    }

    /**
     * 
     * @return inventory processed as a string
     */
    public String getInventory() {
        StringBuilder sb = new StringBuilder("Inventory:\n");
        for (Map.Entry<Item, Integer> entry : inventory.entrySet()) {
            sb.append(entry.getKey().getName()).append(" x ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }

    /**
     * this currently assumes item is used with something like item.use() to apply effects
     * @param item the item to use
     */
    public void useItem(Item item) {
        if (inventory.containsKey(item) && inventory.get(item) > 0) {
            // this currently assumes item is used with something like item.use() to apply effects
            item.use();
            removeItem(item, 1);
            Log.msg("Used " + item.itemNameGetter());
        } else {
            Log.msg("Item not available in inventory");
        }
    }
    public String getStatusEffects() {
        return "Status effects";
    }
}
