public class Status_Effect {

    public void Boost_Attack(Player player){
        player.setAttackPower(player.getAttackPower()+2);
    }

    public void Lower_Attack(Enemy enemy){
        enemy.setAttackPower(enemy.getAttackPower()-5);
    }

    public void Poison(Character character){
        character.setPoison(true);
    }

    public void clear(Player player) {
        player.setPoison(false);
    }
}
