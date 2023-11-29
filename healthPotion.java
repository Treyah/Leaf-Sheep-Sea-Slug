public class healthPotion extends Item{
    public healthPotion(String name) {
        super(name);
    }

    @Override
    public void use(Character player){
        player.setHP(player.getHP() + 10);
        if(player.getHP() > player.getMaxHP()){
            player.setHP(player.getMaxHP());
        }
        System.out.println(player.getName()+ " used " + itemNameGetter());
        System.out.println("Health Restored");
    }
}
