import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Represents a player character in a dungeon crawler game
 * Tracks things like player character stats, positions, and
 * handles some actions related to dungeon crawling like
 * attacking and looting
 *
 * @author Kelcey Calderon
 */
public class Player extends Character implements Behaivor{
    private int maxHP;
    private int gold;
    private int[] position;
    /*
     Example implementation of an inventory - use a hashmap:
     https://stackoverflow.com/questions/74963886/objects-as-keys-in-a-hashmap-how-to-reference-them-java
     */
    private Map<Item, Integer> inventory = new HashMap<>();

    public Player(String name){
        super(name);
        this.position = new int[]{8,19};
        this.maxHP = 10;
        this.HP = 10;
        this.attackPower = 1;
    }

    public int getGold(){
        return gold;
    }

    public int[] getPosition(){
        return position;
    }
    public void setGold(int gold){
        this.gold = gold;
    }

    public void setPosition(int x, int y){
        this.position[0] = x;
        this.position[1] = y;
    }

    public void attack(Character enemy) {
        enemy.setHP(enemy.getHP()-attackPower);
        Log.msg("Player attacked");
    }

    public void openInventory(Enemy enemy){
        System.out.println("---------------------------Inventory------------------------------");
        for (Map.Entry<Item, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey().itemNameGetter() + " x"+entry.getValue());
        }
        System.out.println("------------------------------------------------------------------");
        System.out.println("Enter the Item you want to use(Enter i to exit Inventory)");
        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine();

        if(choice.equals("i")) return;

        for (Map.Entry<Item, Integer> entry : inventory.entrySet()) {
            if(entry.getKey().itemNameGetter().equals(choice)){
                Item item = entry.getKey();
                useItem(item, enemy);
            }
        }
        openInventory(enemy);
    }

    public void addItem(Item item, int quantity){
        inventory.put(item, inventory.getOrDefault(item, 0) + quantity);
    }

    public void removeItem(Item item, int quantity){
        int currentQty = inventory.getOrDefault(item, 0);
        if (currentQty > quantity) {
            inventory.put(item, currentQty - quantity);
        } else {
            inventory.remove(item);
        }
    }

    public String getInventory(){
        StringBuilder sb = new StringBuilder("\n");
        for (Map.Entry<Item, Integer> entry : inventory.entrySet()) {
            sb.append(entry.getKey().itemNameGetter()).append(",").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }

    /**
     * this currently assumes item is used with something like item.use() to apply effects
     * @param item the item to use
     */
    public void useItem(Item item, Character enemy) {
        if(item.getClass().equals(smokeBomb.class)){
            if(enemy != null) {
                item.use(enemy);
                removeItem(item, 1);
            }else{
                System.out.println("Can only be used in battle");
            }
        }else{
            item.use(this);
            removeItem(item, 1);
        }
    }

    public void setMaxHP(int HP){
        this.maxHP = HP;
    }

    public int getMaxHP(){
        return maxHP;
    }

}