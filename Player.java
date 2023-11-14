/**
 * Represents a player character in a dungeon crawler game.
 * Tracks things like player character stats, positions, and
 * handles some actions related to dungeon crawling like
 * attacking, looting, and leveling up
 *
 * @author Kelcey Calderon
 */
public class Player extends Person {

    private int attackBonus = 5;
    private int defense;
    private int HP = 5;
    private int XP;
    private int gold;
    private int[] position;
    private int level;
    private boolean poison;

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

    /** Handles a player using an item */
    public void useItem(Item item) {
        // Stub
        Log.msg("Player used an item.");
    }

    public String getInventory() {
        return "Inventory";
    }

    public String getStatusEffects() {
        return "Status effects";
    }
}
