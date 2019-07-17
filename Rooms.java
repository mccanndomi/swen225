import java.util.ArrayList;

public class Rooms {
    ArrayList boardpos [][] = new ArrayList[24][25];
    @Override
    public String toString() {
        return "Rooms{}";
    }

    public Rooms(String n, int rn, boolean mr, ArrayList<boardspot> c, ArrayList<boardspot> na){
        String name;
        int RoomNumber;
        Boolean MurderRoom;
        ArrayList<boardspot> corners;
        ArrayList<boardspot> notAvailable;

        name = n;
        RoomNumber = rn;
        MurderRoom = mr;
        corners = c;
        notAvailable =  na;


    }

}
