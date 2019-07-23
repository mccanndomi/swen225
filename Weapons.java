public class Weapons implements Card{
    private int locationX;
    private int LocationY;
    private  int WeaponNumber;
    private  String name;
    private  int room;
    private Boolean MurderWeapon;

    @Override
    public String toString() {
        return "Weapons{}";
    }
    public Weapons(int wn, String n, int r, boolean mw){
        WeaponNumber = wn;
        name = n;
        room  = r;
        MurderWeapon = mw;

    }

    public Boolean getMurderWeapon() { return MurderWeapon; }
    public void setMurderWeapon(Boolean murderWeapon) { MurderWeapon = murderWeapon; }
    public int getRoom() { return room; }
    public void setRoom(int room) { this.room = room; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getWeaponNumber() { return WeaponNumber; }
    public void setWeaponNumber(int weaponNumber) { WeaponNumber = weaponNumber; }

}
