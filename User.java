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
     * updates the total playtime of the user
     **/
    public void updatePlaytime() {
        Date secondTime = new Date();
        int playTime = (secondTime.getHours() - firstTime.getHours());
        TotalPlaytime += playTime;
    }

    public void setState(String state) {
        this.State = state;
    }

    public String getState(){
        return State;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String setFirstName() {
        return firstName;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }
    public String getLoginTime() {
        return loginTime;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getLastName(){
        return lastName;
    }

    public void setTotalPlaytime(String totalPlaytime) {
        this.TotalPlaytime = Integer.parseInt(totalPlaytime);
    }
    public String getTotalPlaytime(){
        return String.valueOf(TotalPlaytime);
    }

    public void setCity(String city) {
        this.city = city;
    }
    public String getCity(){
        return city;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
    public String getZip(){
        return zip;
    }

    public void setDOB(String dateOfBirth) {
        this.DOB = dateOfBirth;
    }
    public String getDOB(){
        return DOB;
    }
}
