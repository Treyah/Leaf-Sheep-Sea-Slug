import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Represents a merchant that sells item upgrades to a Player
 *
 * @author Kelcey Calderon
 */
public class Merchant {
    /*
    Example implementation of an inventory system using hashmaps
    https://stackoverflow.com/questions/74963886/objects-as-keys-in-a-hashmap-how-to-reference-them-java

     */
    private Map<Item, Integer> inventory;

    /**
     * Constructor
     */
    public Merchant() {
        inventory = new HashMap<>();
        // Stroe items and the number in stock
        inventory.put(new healthPotion("Health Potion"), 5);
        inventory.put(new Sword("Sword Upgrade"), 3);
        inventory.put(new smokeBomb("Smoke Bomb"), 10);
    }

    /**
     * Initiates the interaction process with the player
     *
     * @param player The player who is interacting with the merchant.
     */

    public void start(Player player) {

        Scanner scanner = new Scanner(System.in);


        System.out.println("Hello adventurer and welcome to my store!");
        System.out.println("I have many fine wares to sell.");
        System.out.println("1. Enter shop");
        System.out.println("2. Leave");

        int choice = scanner.nextInt();
        if (choice == 1) {
            displayStore(player);
            sellItem(player);
        } else {
            System.out.println("Until next time then.");
        }

    }

    /**
     * Displays the store's inventory to the player.
     *
     * @param player The player whose gold amount is to be displayed as a part
     * of the store menu
     */
    public void displayStore(Player player) {
        System.out.println("Coin(s): " + player.getGold());
        // Display the right number of items in stock
        System.out.println("1. Health Potion - Cost 5 (" + inventory.get(new healthPotion("Health Potion")) + " available)");
        System.out.println("2. Sword Upgrade - Cost 10 (" + inventory.get(new Sword("Sword Upgrade")) + " available)");
        System.out.println("3. Smoke Bomb - Cost 1 (" + inventory.get(new smokeBomb("Smoke Bomb")) + " available)");
        System.out.println("4. Leave");

    }

    /**
     * Handles the selling of items to the player. It processes the player's choice,
     * checks if the item is in stock and if the player has enough gold.
     *
     * If a sale is completed, updatse the player's inventory and gold
     * and the merchant's stock.
     *
     * @param player The player attempting to purchase an item.
     */
    public void sellItem(Player player) {
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        int cost = 0;
        Item selectedItem = null;

        switch (choice) {
            case 1: // Health Potion
                selectedItem = new healthPotion("Health Potion");
                cost = 5;
                break;
            case 2: // Sword
                selectedItem = new Sword("Sword Upgrade");
                cost = 10;
                break;
            case 3: // Smoke Bomb
                selectedItem = new smokeBomb("Health Potion");
                cost = 1;
                break;
            default:

                return;
        }
        if (player.getGold() >= cost && inventory.get(selectedItem) > 0) {
            player.setGold(player.getGold() - cost);
            player.addItem(selectedItem, 1);
            inventory.put(selectedItem, inventory.get(selectedItem) - 1);
            System.out.println("Splendid choice... What else can I get for you?");
            displayStore(player);
            sellItem(player);
        } else {
            System.out.println("Not enough coin or item is sold out.");
        }
    }

}