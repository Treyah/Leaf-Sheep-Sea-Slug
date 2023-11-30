import java.util.Date;

/**
 * The User class create an object where we save
 * important information of the user logged in. 
 *
 * @author Trey Holguín
 * @version 2.0
 */
public class User{

    private String DOB;
    private String username;
    private String pin;
    private String lastSignIn;
    private int battlesWon = 0, battlesLost = 0, itemTotalNum = 0, userGameCompletions = 0, TotalPlaytime = 0;
    private Date firstTime;
    private String State;
    private String firstName;
    private String loginTime;
    private String lastName;
    private String city;
    private String zip;

    /** 
     * Setter method for the user's Username which will be referenced in RunGame.java
     * @param usernameSetter username to set
     */
    public void setUsername(String usernameSetter){
        this.username = usernameSetter;
    }
    
    /** 
     * Setter method for the user's PIN which will be referenced in RunGame.java
     * @param pinSetter pin to set
     */
    public void setPin(String pinSetter){
        this.pin = pinSetter;
    }
    
    /** 
     * Setter method that sets the user's last login. 
     * @param LSI sets the user's last sign in time as a String.
     */
    public void setLastSignIn(String LSI){
        this.lastSignIn = LSI;
        this.firstTime = new Date();
    }
    
    /**
     * Setter method for battlesWon attribute. 
     * @param won the number of wins
     */
    public void setBattlesWon(int won){
        this.battlesWon = won;
    }

    /**
     * Setter method for the battlesLost attribute. 
     * @param lost the number of loses
     */
    public void setBattlesLost(int lost){
        this.battlesLost = lost;
    }

    /**
     * Sets the amount of items that the user has collected within the game.
     * @param items int value representing the user's total collect items. 
     */
    public void setItemTotalNum(int items){
        this.itemTotalNum = items;
    }

    /**
     * Will keep track of the amount of times the user has completed the game.
     * @param completions Number of the times the game has been completed 
     */
    public void setGameCompletions(int completions){
        this.userGameCompletions = completions; 
    }

    /** 
     * Getter method for obtaining user's PIN. 
     * @return username as a String
     */
    public String getUsername(){
        return username;
    }

    /**
     * Getter method for obtaining user's PIN.
     * @return PIN as String
     */
    public String getPin(){
        return pin;
    }
    
    /** Getter method for obtaining user's Last Sign In information.  
     * @return String
     */
    public String getLastSignIn() {
        return lastSignIn;
    }

    /**
     * Getter method for the amount of battles the user has won. 
     * @return int value of battles won.
     */
    public int getBattlesWon(){
        return battlesWon; 
    }

    /**
     * Getter method for the amount of battles the user has lost.
     * @return int value of battles lost. 
     */
    public int getBattlesLost(){
        return battlesLost;
    }

    /**
     * Getter method for the amount of items the user has collected.
     * @return int value of the total items. 
     */
    public int getItemTotalNum(){
        return itemTotalNum; 
    }

    /**
     * Getter method for the amount of times the user has completed the game.
     * @return int value of the number of times user has completed game. 
     */
    public int getGameCompletions(){
        return userGameCompletions; 
    }


    /**
     * Method used to update the user's total playtime.
     * This is done through saving a Date object as well as platime long. 
     */

    public void updatePlaytime() {
        Date secondTime = new Date();
        int playTime = (secondTime.getHours() - firstTime.getHours());
        TotalPlaytime += playTime;
    }

    /**
     * Sets the state the player is in. 
     * @param state such as TX, CA, CT, etc.
     */
    public void setState(String state) {
        this.State = state;
    }

    /**
     * Getter method that retrieves what state the player lives in. 
     * @return State
     */
    public String getState(){
        return State;
    }

    /**
     * Set's the player's first name
     * @param firstName is given as a string. 
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter method for the player's first name.
     * @return firstName is returned as a string. 
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter method for the player's login time. 
     * @param loginTime
     */
    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * Getter method for the player's Login Time
     * @return loginTime
     */
    public String getLoginTime() {
        return loginTime;
    }

    /**
     * Setter method for the Last Name.  
     * @param lastName will be used to set the attribute lastName.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter method for Player's Last Name.
     * @return lastName
     */
    public String getLastName(){
        return lastName;
    }

    /**
     * Setter method for player's total play time. 
     * @param totalPlaytime
     */
    public void setTotalPlaytime(String totalPlaytime) {
        this.TotalPlaytime = Integer.parseInt(totalPlaytime);
    }

    /**
     * Getter method for Total Play time. 
     * @return String of play time. 
     */
    public String getTotalPlaytime(){
        return String.valueOf(TotalPlaytime);
    }

    /**
     * Setter method for city that the player lives in.
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }
    
    /**
     * Getter method for the player's city that will return player's city as a string. 
     * @return city as a String. 
     */
    public String getCity(){
        return city;
    }

    /**
     * Setter method for the player's zipcode. 
     * @param zip string value
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * Getter method for the player's zipcode. 
     * @return
     */
    public String getZip(){
        return zip;
    }

    /**
     * Setter method for the player's Date of Birth. 
     * @param dateOfBirth will be in string format. 
     */
    public void setDOB(String dateOfBirth) {
        this.DOB = dateOfBirth;
    }

    /**
     * Getter method for the player's DOB. Will return a string value.
     * @return DOB
     */
    public String getDOB(){
        return DOB;
    }
}
