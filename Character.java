public abstract class Character {
    protected String name;
    protected int attackPower;
    protected int HP;
    protected boolean poison;

    public Character(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void set_name(String name){
        this.name = name;
    }

    public int getAttackPower(){
        return attackPower;
    }

    public void setAttackPower(int attackPower){
        this.attackPower = attackPower;
    }
    public int getHP(){
        return HP;
    }

    public void setHP(int HP){
        this.HP = HP;
    }

    public boolean getPoison(){
        return poison;
    }

    public void setPoison(boolean poison){
        this.poison = poison;
    }

    public abstract void setGold(int gold);
    public abstract int getGold();

    public abstract int getMaxHP();
    public abstract void setMaxHP(int HP);
}
