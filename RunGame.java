import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

/**
 * The RunGame class puts together and controls the
 * flow of the dungeon crawler game.
 *
 * @author Hector Jimenez
 */
public class RunGame {
    private static User user = new User();
    private static Player player = new Player("");
    private static final Dungeon dungeon = new Dungeon();
    private static final Utility utility = new Utility();
    private static final Battle_System battle = new Battle_System();
    private static final Random rand = new Random();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        if(Files.exists(Paths.get("updatedUsers.csv"))){
            utility.userDataFile("updatedUsers.csv");
        }else {
            utility.userDataFile("Users.csv");
        }
        main_menu();
    }

    /**
     * The main_menu method takes charge of display the options the users
     * can take before starting a game
     */
    public static void main_menu(){
        System.out.println("-----Main Menu-----");
        System.out.println("a. Register");
        System.out.println("b. Login");
        System.out.println("Type 'EXIT' to exit program");

        String option = sc.next();

        switch (option) {
            case "a":
                register();
                break;
            case "b":
                login();
                break;
            case "EXIT":
                break;
            case "adminPower":
                game_admin();
                main_menu();
            default:
                System.out.println("This input is invalid. Try Again.");
                main_menu();
        }
    }

    /**
     * game_admin method is a state of the program where the admin can get information about users
     */
    private static void game_admin() {
        Game_Administrator admin = new Game_Administrator("", "");
        while(true) {
            System.out.print("user: ");
            String adminName = sc.next();
            System.out.println("PIN: ");
            String pin = sc.next();
            if (utility.checkUser(adminName, pin)) {
                admin = new Game_Administrator(adminName, pin);
                break;
            }else{
                System.out.println("Invalid");
            }
        }
        System.out.print("User to look up: ");
        user.setUsername(sc.next());
        admin.generateAndWriteStatisticsFile(user);

    }

    /**
     * The login_menu method is a different variation of the main_menu for when
     * a user has confirmed his credentials.
     */
    public static void login_menu(){
        System.out.println("-----Main Menu-----");
        System.out.println("a. Continue");
        System.out.println("b. New Game");
        System.out.println("c. Logout");

        System.out.println("Type 'EXIT' to exit program");

        String option = sc.next();

        switch(option){
            case "a":
                if(Files.exists(Paths.get("savedDungeon.csv"))){
                    load_game();
                }else{
                    System.out.println("There is no saved file");
                    login_menu();
                }
                break;
            case "b":
                new_game();
                break;
            case "c":
                logout();
                break;
            case "EXIT":
                break;
            default:
                System.out.println("Invalid input");
                login_menu();
                break;
        }
    }

    /**
     * The register method asks the user for information relating to them
     * to register the new user into the file
     */
    public static void register(){
        System.out.println("Please enter the following information to register:");
        System.out.print("First Name: ");
        String firstname = sc.next();
        System.out.print("Last Name: ");
        String lastname = sc.next();
        System.out.print("New Username: ");
        String username = sc.next();
        System.out.print("New Pin Number: ");
        String pin = sc.next();
        System.out.print("State (ex. TX): ");
        String state = sc.next();
        System.out.print("City: ");
        String city = sc.nextLine();
        System.out.print("Zip Code: ");
        String zip = sc.next();
        System.out.print("Date of Birth (mm/dd/yy): ");
        String dateOfBirth = sc.next();
        utility.registerUser(state,username,firstname,pin,lastname,city,zip,dateOfBirth);
        System.out.println("Registration Complete!");
        Log.msg(username + " has registered");
        login();
    }

    /**
     * The login method asks the user for his credentials and
     * makes sure if the user has access to the game
     *
     * If the user successfully logins the users files will be updated
     * with his last login time
     */
    public static void login(){
        System.out.print("Enter Username: ");
        String username = sc.next();
        System.out.print("Enter PIN: ");
        String pin = sc.next();

        if(utility.checkUser(username, pin)){
            user.setUsername(username);
            user.setPin(pin);
            utility.lastSignin(username);
            Log.msg("User "+ user.getUsername() + " logged in");
            login_menu();
        }else {
            System.out.println("Username or Password maybe be wrong. Try Again");
            login();
        }
    }

    /**
     * The logout class updates the users file with the
     * total time played.
     */
    public static void logout(){
        //logout time registration
        Log.msg("User "+ user.getUsername() + " logged out");
        main_menu();
    }

    /**
     * The new_game method clears the dungeon of any changes and
     * starts a fresh game for the users.
     */
    public static void new_game(){
        System.out.print("Enter a name for your character: ");
        player.set_name(sc.next());
        player.setHP(10);
        player.setAttackBonus(1);
        dungeon.setMap(utility.readDungeon("Dungeon.csv"));
        game();
    }

    /**
     * The load_game reads the save file to restore the previous game the user left
     */
    public static void load_game(){
//        dungeon.setMap(utility.readDungeon(user.getUsername()+"savedDungeon.csv"));
//        game();
        login_menu();
    }

    /**
     * The game method controls the flow of the game updating the log.
     */
    public static void game(){

        int[] position = player.getPosition();
        dungeon.updatePlayerPosition(position[1], position[0]);

        int game = 1;
        while(game == 1){
            dungeon.printMap();
            System.out.println("Use WASD and the Enter key to move across the dungeon(Enter -1 to exit to menu)");
            String input = sc.next();
            switch (input){
                case "w":
                    move_player(0, -1);
                    break;
                case "s":
                    move_player(0, 1);
                    break;
                case "a":
                    move_player(-1, 0);
                    break;
                case "d":
                    move_player(1, 0);
                    break;
                case "-1":
                    saveGame();
                    login_menu();
                    game = -1;
                    break;
                default:
                    System.out.println("This is invalid input");
                    break;
            }
            int chestSpawn = rand.nextInt(5);
            int enemySpawn = rand.nextInt(5);
            if(chestSpawn == 1) dungeon.generateRandomItem(position[0], position[1]);
            if(enemySpawn == 1){
                Enemy enemy = new Enemy();
                battle.Start(player, enemy);
                if(player.getHP() < 1){
                    System.out.println("You Died");
                    login_menu();
                    break;
                }
            }
        }
    }

    /**
     * The move_player method move the player if possible to the direction inputted by the user
     * @param xChange takes the change that will be made to the x coordinate
     * @param yChange takes the change that will be mafe to the y coordinate
     */
    public static void move_player(int xChange, int yChange){

        int[] position = player.getPosition();
        int x = position[0];
        int y = position[1];

        dungeon.updatePlayerPosition(y, x);

        if(dungeon.isMovePossible(y + yChange, x + xChange)){
            dungeon.updateExploredCell(y, x);
            x += xChange;
            y += yChange;
            dungeon.updatePlayerPosition(y, x);
            player.setPosition(x, y);
        }else{
            System.out.println("Can not go there!");
        }
    }

    /**
     * The saveGame method save the current state of the dungeon for future use.
     */
    private static void saveGame() {
        String saveFile = user.getUsername();
        utility.saveGame(saveFile, dungeon, player);
    }


}