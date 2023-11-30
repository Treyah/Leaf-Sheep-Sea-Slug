/**
 * @author Trey Holguin
 * @version 1.1
 * This class is for items that the player will obtain through chests in the dungeon. 
 */
public abstract class Item {
    private String itemName;

    public Item(String name) {
        this.itemName = name;
    }

    /**
     * Setter method for naming item's that the player can obtain. 
     * @param x String for the item's name
     */
    public void itemNameSetter(String x){
        this.itemName = x; 
    }

    public String itemNameGetter(){
        return itemName; 
    }

    public abstract void use(Character character);
}