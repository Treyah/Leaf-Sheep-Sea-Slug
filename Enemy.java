/**
 * This class models an enemy in the dungeon. 
 * 
 * @author Kelcey Calderon
 */
public class Enemy {

    private int attackPower;
    private int defense;
    private int HP;
    private int[] position;
    private boolean poison;

    /**
     * Initializes a new enemy with hit points, attack power, defense
     * and initial position
     * 
     * @param HP         the hit points of the enemy.
     * @param attackPower the attack power of the enemy.
     * @param defense    the defense value of the enemy.
     * @param x          the x-coordinate of the position.
     * @param y          the y-coordinate of the position.
     */
    public Enemy(int HP, int attackPower, int defense, int x, int y) {
        this.HP = HP;
        this.attackPower = attackPower;
        this.defense = defense;
        this.position = new int[]{x, y};
    }

    // Getters and setters

    /** @return the attack power of the enemy. */
    public int getAttackPower() {
        return attackPower;
    }

    /** @return the defense value of the enemy. */
    public int getDefense() {
        return defense;
    }

    /** @return the hit points (HP) of the enemy. */
    public int getHP() {
        return HP;
    }

    /** @return the position of the enemy in the dungeon as an array of size 2. */
    public int[] getPosition() {
        return position;
    }

    /** @return status of the poison condition */
    public boolean getPoison() {
        return poison;
    }

    /**
     * Sets the attack power of the enemy.
     * 
     * @param attackPower the attack power to set.
     */
    private void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    /**
     * Sets the defense value of the enemy.
     * 
     * @param defense the defense value to set.
     */
    private void setDefense(int defense) {
        this.defense = defense;
    }

    /**
     * Sets the hit points (HP) of the enemy.
     * 
     * @param HP the hit points to set.
     */
    private void setHP(int HP) {
        this.HP = HP;
    }

    /**
     * Sets the position of the enemy in the dungeon.
     * 
     * @param x the x-coordinate of the position.
     * @param y the y-coordinate of the position.
     */
    private void setPosition(int x, int y) {
        this.position[0] = x;
        this.position[1] = y;
    }

    /**
     * Sets the poison condition on the enemy
     * @param poison whether or not the poison condition is on the enemy
     */
    private void setPoison(boolean poison) {
        this.poison = poison;
    }

    /**
     * Handles the enemy's attack action.
     * 
     */
    public void attack() {
        // Stub
        Log.msg("Enemy attacks!");
    }

     /**
     * Handles the enemy's move action.
     * 
     */
    public void move() {
        // Stub
        Log.msg("Enemy moves");
    }

    /**
     * Reduces the enemy's hit points by the amount of damage taken.
     * 
     * @param damage the amount of damage to inflict on the enemy.
     */
    public void takeDamage(int damage) {
        this.HP -= damage;
        if(this.HP <= 0) {
            Log.msg("Enemy defeated!");
        } else {
            Log.msg("Enemy takes " + damage + " damage, remaining HP: " + this.HP);
        }
    }

    /**
     * Determines if the enemy is still alive.
     * 
     * @return true if the enemy has more than 0 hit points, false otherwise.
     */
    public boolean isAlive() {
        return this.HP > 0;
    }
}
