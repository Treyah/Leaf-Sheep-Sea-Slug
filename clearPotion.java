/**
 * Subclass of Item that clears any status effects
 * @author Hector Jimenez
 */
public class clearPotion extends Item{
    public clearPotion(String name) {
        super(name);
    }

    @Override
    public void use(Character player){
        new Status_Effect().clear((Player) player);
        System.out.println(player.getName()+ " used " + itemNameGetter());
        System.out.println("Poison is cleared");
    }
}
