/**
 * The Behaivor interface factors out common behaivor
 * done between the player class and the enemy class
 * @author Hector Jimenez
 */
public interface Behaivor {
   void attack(Character character);

    void useItem(Item item, Character character);
}
