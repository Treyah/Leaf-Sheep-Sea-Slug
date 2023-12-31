/**
 * Subclass of Item that adds gold to the player
 * @author Hector Jimenez
 */
public class Coin extends Item{
    public Coin(String name) {
        super(name);
    }

    @Override
    public void use(Character player){
        player.setGold(player.getGold()+1);
        System.out.println(player.getName()+ " used " + itemNameGetter());
        System.out.println("Gold Added");
        System.out.println("You have " + player.getGold() + " gold");
    }
}
