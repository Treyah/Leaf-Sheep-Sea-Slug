/**
 * @author Trey Holguin
 * @version 1.1
 * This class is for items that the player will obtain through chests in the dungeon. 
 */
public class Item {
    private String itemName;


    /**
     * Constructor method that will create a new item that the player may obtain. 
     * @param itemName Takes the item's name into consideration for creation of item. 
     */
    public Item(String itemName){
        this.itemName = itemName; 
    }
    /**
     * Setter method for naming item's that the player can obtain. 
     * @param x String for the item's name
     */
    public void itemNameSetter(String x){
        this.itemName = x; 
    }

    /**
     * Getter method for itemName
     * @return Name of item. 
     */
    public String itemNameGetter(){
        return itemName; 
    }
}
