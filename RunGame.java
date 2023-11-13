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

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        utility.userDataFile("Users.csv");
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
            default:
                System.out.println("This input is invalid. Try Again.");
                main_menu();
        }
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
                load_game();
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
        String city = sc.next();
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
            //Login time registration
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
        dungeon.setMap(utility.readDungeon("Dungeon.csv"));
        game();
    }

    /**
     * The load_game reads the save file to restore the previous game the user left
     */
    public static void load_game(){
        dungeon.setMap(utility.readDungeon(user.getUsername()+"savedDungeon.csv"));
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
            System.out.println("Use WASD and the Enter key to move across the dungeon(Enter -1 to exit to menu)");
            String input = sc.next();
            switch (input){
                case "w":
                    if(dungeon.isMovePossible(position[1] - 1, position[0])){
                        dungeon.updateExploredCell(position[1], position[0]);
                        position[1]--;
                        dungeon.updatePlayerPosition(position[1], position[0]);
                        player.setPosition(position[0], position[1]);
                    }
                    break;
                case "s":
                    if(dungeon.isMovePossible(position[1] + 1, position[0])){
                        dungeon.updateExploredCell(position[1], position[0]);
                        position[1]++;
                        dungeon.updatePlayerPosition(position[1], position[0]);
                        player.setPosition(position[0], position[1]);
                    }
                case "a":
                    if(dungeon.isMovePossible(position[1], position[0] - 1)){
                        dungeon.updateExploredCell(position[1], position[0]);
                        position[0]--;
                        dungeon.updatePlayerPosition(position[1], position[0]);
                        player.setPosition(position[0], position[1]);
                    }
                    break;
                case "d":
                    if(dungeon.isMovePossible(position[1], position[0] + 1)){
                        dungeon.updateExploredCell(position[1], position[0]);
                        position[0]++;
                        dungeon.updatePlayerPosition(position[1], position[0]);
                        player.setPosition(position[0], position[1]);
                    }
                    break;
                case "-1":
                    saveGame();
                    game = -1;
                    break;
                default:
                    System.out.println("This is invalid input");
                    break;
            }
        }
    }

    /**
     * The saveGame method save the current state of the dungeon for future use.
     */

    private static void saveGame() {
        String saveFile = user.getUsername() + "SaveFile.csv";
        utility.saveGame(saveFile, dungeon);
    }


}