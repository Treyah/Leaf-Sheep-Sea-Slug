/**
 * Class that affects various attributes of Character classes
 * @author Hector Jimenez
 */
public class Status_Effect {

    public void Boost_Attack(Player player){
        player.setAttackPower(player.getAttackPower()+2);
    }

    public void Lower_Attack(Enemy enemy){
        enemy.setAttackPower(enemy.getAttackPower()-5);
        if(enemy.getAttackPower() < 1){
            enemy.setAttackPower(1);
        }
    }

    public void Poison(Character character){
        character.setPoison(true);
    }

    public void clear(Player player) {
        player.setPoison(false);
    }
}
