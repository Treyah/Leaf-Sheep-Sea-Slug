/**
 * Subclass of Item that increases the max health of a player
 * @author Hector Jimenez
 */
public class Heart extends Item{
    public Heart(String name) {
        super(name);
    }

    @Override
    public void use(Character player){
        player.setMaxHP(player.getMaxHP() + 5);
        player.setHP(player.getHP() + 5);
        System.out.println(player.getName()+ " used " + itemNameGetter());
        System.out.println("Health Increased");
        System.out.println("Your Max Health increased to " + player.getMaxHP());
    }
}
