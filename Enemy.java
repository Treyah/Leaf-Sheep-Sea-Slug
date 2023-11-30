/**
 * This class models an enemy in the dungeon. 
 *
 * @author Kelcey Calderon
 */
public class Enemy extends Character implements Behaivor{

    /**
     * Initializes a new enemy with hit points, attack power, defense
     * and initial position
     *
     * @param HP         the hit points of the enemy.
     * @param attackPower the attack power of the enemy.
     */
    public Enemy(String name, int HP, int attackPower) {
        super(name);
        this.HP = HP;
        this.attackPower = attackPower;
    }

    public void useItem(Item item, Character character) {
        //stud
    }

    /**
     * Handles the enemy's attack action.
     *
     */
    public void attack(Character player) {
        player.setHP(player.getHP() - attackPower);
        Log.msg("Enemy attacks!");
    }

    @Override
    public void setGold(int gold) {

    }

    @Override
    public int getGold() {
        return 0;
    }

    @Override
    public int getMaxHP() {
        return 0;
    }

    @Override
    public void setMaxHP(int HP) {

    }
}