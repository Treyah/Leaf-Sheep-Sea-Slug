public class smokeBomb extends Item{
    public smokeBomb(String name) {
        super(name);
    }

    @Override
    public void use(Character enemy){
        new Status_Effect().Lower_Attack((Enemy) enemy);
        System.out.println(enemy.getName()+ " was hit with " + itemNameGetter());
        System.out.println(enemy.getName()+ " 's attack is lowered");
    }
}
