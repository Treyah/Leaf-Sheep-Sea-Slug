import java.util.Scanner;

public class Battle_System {

    private Player player;
    private Enemy enemy;

    private Status_Effect statusEffect;

    /**
     * Start method sets up the battlefield
     * @param player takes the current player
     * @param enemy takes an enemy object to fight player
     */
    public void Start(Player player, Enemy enemy){
        this.player = player;
        this.enemy = enemy;
        while (player.getHP() != 0 && enemy.getHP() != 0) {
            System.out.println(enemy.getName() + " HP: " + enemy.getHP() + "\n");
            System.out.println("Player HP: " + player.getHP());
            Player_turn();
            if(enemy.getHP() > 0) {
                Enemy_turn();
            }
        }
    }

    /**
     * Player_turn controls the state in which a user
     * can control the player in battle
     */
    public void Player_turn(){
        System.out.println("----------");
        System.out.println("Actions");
        System.out.println("a) attack");
        System.out.println("b) use items");
        System.out.println("c) escape");
        System.out.println("----------");

        Scanner sc = new Scanner(System.in);
        String option = sc.next();

        switch(option){
            case "a":
                player.attack(enemy);
                break;
            case "b":
                Item item = new Item();
                player.useItem(item);
                break;
            case "c":
                break;
            default:
                System.out.println("Invalid input. Try Again.");
                Player_turn();
                break;
        }

        if(player.getPoison() && player.getHP() < 1){
            player.setHP(player.getHP()-1);
        }

    }

    /**
     * Enemy_turn is a state that controls the enemy of the battle
     */
    public void Enemy_turn(){
        enemy.attack(player);
        if(enemy.getPoison() && enemy.getHP() < 1){
            enemy.setHP(enemy.getHP()-1);
        }
    }

}
