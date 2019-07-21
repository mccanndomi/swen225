public class Characters {
    private int locationX;
    private  int locationY;
    private int CharacterNumber;
    private String name;
    private Boolean MurderPerson;
    private boolean IsPlayer;
    public Characters(int i, String n, boolean b, int locx, int locy){
        locationX = locx;
        locationY = locy;
        CharacterNumber = i;
        name = n;
        MurderPerson = b;
        IsPlayer = false;

    }


    @Override
    public String toString() {
        return "Characters{}";
    }

    /*
    getters and setters
     */
    public Boolean getMurderPerson() { return MurderPerson; }
    public void setMurderPerson(Boolean murderPerson) { MurderPerson = murderPerson; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getCharacterNumber() { return CharacterNumber; }
    public void setCharacterNumber(int characterNumber) { CharacterNumber = characterNumber; }
    public int getLocationY() { return locationY; }
    public void setLocationY(int locationY) { this.locationY = locationY; }
    public int getLocationX() { return locationX; }
    public void setLocationX(int locationX) { this.locationX = locationX; }
    public Boolean getIsPlayer(){return  IsPlayer;}
    public void setIsPlayer(Boolean isPlayer ){IsPlayer = isPlayer;}



}
