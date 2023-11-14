public class Enemy {
    private String name;
    private int HP;
    private int Damage;
    private boolean poison;

    public Enemy(){
        this.name = "Enemy";
        this.HP = 5;
        this.Damage = 1;
        this.poison = false;
    }
    public void attack(Player player){
        player.setHP(player.getHP()-Damage);
        System.out.println(name + " attacked for " + Damage + " damage.");
    }

    public void use_item(String item){

    }

    public int getHP() {
        return HP;
    }

    public String getName() {
        return name;
    }

    public boolean getPoison() {
        return poison;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }
}
