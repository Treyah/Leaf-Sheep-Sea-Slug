import java.util.Scanner;

public class Battle_System {

    private Player player;
    private Enemy enemy;

    /**
     * Start method sets up the battlefield
     * @param player takes the current player
     * @param enemy takes an enemy object to fight player
     */
    public void Start(Player player, Enemy enemy, User user){
        this.player = player;
        this.enemy = enemy;
        while (player.getHP() != 0 && enemy.getHP() != 0) {
            System.out.println(enemy.getName() + " HP: " + enemy.getHP() + "\n");
            System.out.println("Player HP: " + player.getHP());
            if(!Player_turn()){
                break;
            }
            if(enemy.getHP() > 0) {
                Enemy_turn();
            }
        }
        if(enemy.getHP() <= 0){
            user.setBattlesWon(user.getBattlesWon()+1);
        }
        if(player.getHP() <= 0){
            user.setBattlesLost(user.getBattlesLost()+1);
        }
        if(enemy.getName().equals("DarkKing") && enemy.getHP() <= 0){
            user.setGameCompletions(user.getGameCompletions()+1);
            System.out.println("Congratulations! You Defeated the DarkKing!");
            System.out.println("You Can  Continue in this World or Create a new game in main menu");
        }
    }

    /**
     * Player_turn controls the state in which a user
     * can control the player in battle
     */
    public boolean Player_turn(){
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
                player.openInventory(enemy);
                break;
            case "c":
                System.out.println("You escaped");
                return false;
            default:
                System.out.println("Invalid input. Try Again.");
                Player_turn();
                break;
        }

        if(player.getPoison() && player.getHP() < 1){
            player.setHP(player.getHP()-1);
        }
        return true;
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
