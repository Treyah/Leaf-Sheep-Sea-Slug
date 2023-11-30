/**
 * Subclass of Item that increases the attack power of the player
 * @author Hector Jimenez
 */
public class Sword extends Item{
    public Sword(String name) {
        super(name);
    }

    @Override
    public void use(Character player){
        new Status_Effect().Boost_Attack((Player) player);
        System.out.println(player.getName()+ " used " + itemNameGetter());
        System.out.println("Attack Power Increased");
        System.out.println("You Attack Power increased to " + player.getAttackPower());
    }
}
