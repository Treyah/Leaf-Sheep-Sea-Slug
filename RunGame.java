import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

/**
 * The RunGame class puts together and controls the
 * flow of the dungeon crawler game.
 *
 * @author Hector Jimenez
 */
public class RunGame {
    private static final Utility utility = new Utility();
    private static final Dungeon dungeon = new Dungeon();
    private static User user;
    private static Player player;

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args){
        //set up of documents and classes
        if (Files.exists(Paths.get("updatedUsers.csv"))) {
            utility.userDataFile("updatedUsers.csv");
        }else{
            utility.userDataFile("Users.csv");
        }
        dungeon.setEnemies(utility.getEnemies());

        main_menu();
    }

    private static void main_menu() {
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
                break;
            default:
                System.out.println("This input is invalid. Try Again.");
                main_menu();
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
     * If the user successfully logins the users files will be updated
     * with his last login time
     */
    public static void login(){
        System.out.print("Enter Username: ");
        String username = sc.next();
        System.out.print("Enter PIN: ");
        String pin = sc.next();
        if(utility.checkUser(username, pin)){
            user = utility.getUser(username);
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            user.setLastSignIn(timestamp);
            Log.msg("User "+ user.getUsername() + " logged in");
            login_menu();
        }else {
            System.out.println("Username or Password maybe be wrong. Try Again");
            login();
        }
    }

    /**
     * game_admin method is a state of the program where the admin can get information about users
     */
    private static void game_admin() {
        System.out.print("user: ");
        String adminName = sc.next();
        System.out.println("PIN: ");
        String pin = sc.next();
        if (utility.checkUser(adminName, pin)) {
            Game_Administrator admin = new Game_Administrator(adminName, pin);
            System.out.print("Create Statistics File For: ");
            String userName = sc.next();
            user = utility.getUser(userName);
            admin.generateAndWriteStatisticsFile(user);
        }
    }

    public static void login_menu(){
        System.out.println("-----Main Menu-----");
        System.out.println("a. Continue");
        System.out.println("b. New Game");
        System.out.println("c. Logout");
        System.out.println("Type 'EXIT' to exit program");
        String option = sc.next();

        switch(option){
            case "a":
                if(Files.exists(Paths.get(user.getUsername()+"savedDungeon.csv"))){
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
                user.updatePlaytime();
                break;
            default:
                System.out.println("Invalid input");
                login_menu();
                break;
        }
    }

    /**
     * The logout class updates the users file with the
     * total time played.
     */
    public static void logout(){
        user.updatePlaytime();
        utility.updateUser(user);
        Log.msg("User "+ user.getUsername() + " logged out");
        main_menu();
    }

    /**
     * The new_game method clears the dungeon of any changes and
     * starts a fresh game for the users.
     */
    public static void new_game(){
        System.out.print("Enter a name for your character: ");
        player = new Player(sc.next());
        player.setHP(10);
        player.setAttackPower(1);
        dungeon.setMap(utility.readDungeon("Dungeon.csv"));
        game();
    }

    /**
     * The load_game reads the save file to restore the previous game the user left
     */
    public static void load_game(){
        dungeon.setMap(utility.readDungeon(user.getUsername()+"savedDungeon.csv"));
        player = utility.getSavedPlayer(user.getUsername()+"Player.csv");
        game();
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
            System.out.println("Use WASD and the Enter key to move across the dungeon. Enter i to open Inventory(Enter -1 to exit to menu)");
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
                case "i":
                    player.openInventory(null);
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

        }
    }
    /**
     * The move_player method move the player if possible to the direction inputted by the user
     * @param xChange takes the change that will be made to the x coordinate
     * @param yChange takes the change that will be made to the y coordinate
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
            Random random = new Random();
            int itemChance = random.nextInt(5);
            if(itemChance == 1){
                dungeon.generateRandomItem(player);
                user.setItemTotalNum(user.getItemTotalNum()+1);
            }
            int enemyChance = random.nextInt(7);
            if(enemyChance == 1){
                Enemy enemy = dungeon.generateEnemy();
                new Battle_System().Start(player, enemy, user);
            }
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