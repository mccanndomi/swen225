public class PlayerObject {
    private String name;
    private Integer userID;
    private int characterNum;



    public PlayerObject(String n, int uid){
        name = n;
        userID = uid;


    }

    public Integer getUserID() { return userID; }
    public void setUserID(Integer userID) { this.userID = userID; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name;}
    public int getCharacterNum() { return characterNum; }
    public void setCharacterNum(int characterNum) { this.characterNum = characterNum; }




}
