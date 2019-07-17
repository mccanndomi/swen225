import java.util.ArrayList;
import java.math.*;
import java.util.Arrays;
import java.util.Random;


public class GameMain {
    private ArrayList<Characters> PlayableCharcters = new ArrayList();
    private ArrayList<Rooms> PlayableRooms = new ArrayList();
    private ArrayList<Weapons> PlayableWeapons = new ArrayList();
    public GameMain() { CreateGame();
    }

    public void CreateGame(){

        GenerateStartCharacters();
        GenerateRooms();
        GenerateWeapons();
    }
    public void GenerateStartCharacters(){
        /*
        set up stuff that is imutable, create characters and add to a list
         */
        ArrayList<String> characternames = new ArrayList<>();
        characternames.add("Miss Scarlett");
        characternames.add("Colonel Mustard");
        characternames.add("Mrs. White");
        characternames.add("Mr. Green");
        characternames.add("Mrs. Peacock");
        characternames.add("Professor Plum");

        for (int i = 0; i < 5 ; i++) {
            String tempname = characternames.get(i);
            Boolean tempBool = false;
            Characters temp = new Characters(i,tempname,tempBool);
            PlayableCharcters.add(temp);
        }
        System.out.println(PlayableCharcters.size());

        /*
        do player interaction stuff, eg : choose a player
         */

        Random r = new Random();
        int randomInteger = r.nextInt(6);



    }
    public void GenerateWeapons(){
        ArrayList<String> weaponnames = new ArrayList<>();
        weaponnames.add("Candlestick");
        weaponnames.add("Dagger");
        weaponnames.add("Lead Pipe");
        weaponnames.add("Revolver");
        weaponnames.add("Rope");
        weaponnames.add("Spanner");
        for (int i = 0; i < 5; i++) {


        }

    }
    public void GenerateRooms(){
        /*
        Kitchen
         */
        String name = "Kitchen";
        int num = 1;
        boolean mr = false;

        ArrayList<Integer> coords = new ArrayList<>();
        coords.addAll(Arrays.asList(0,0,0,5,5,5,0,5));
        ArrayList<boardspot> corners = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            boardspot a = new boardspot(coords.get(i),coords.get(i+1),false);
            corners.add(a);
            System.out.println("nums are " + coords.get(i) + " "+ coords.get(i+1));
            i++;
        }

        coords.clear();
        coords.addAll(Arrays.asList(0,5));
        ArrayList<boardspot> notAvailable = new ArrayList<>();

        for (int i = 0; i < coords.size() ; i++) {
            boardspot e = new boardspot(i,i+1,true);
            notAvailable.add(e);
            i++;
        }

        Rooms k = new Rooms(name,num,mr,corners,notAvailable);
        PlayableRooms.add(k);


        /*
            dining room
         */

        name = "Dining Room";
        num = 2;
        mr = false;
        coords.clear();
        coords.addAll(Arrays.asList(0,8,0,14,7,14,8,7));
        corners.clear();

        for (int i = 0; i < 8; i++) {
            boardspot a = new boardspot(coords.get(i),coords.get(i+1),false);
            corners.add(a);
            System.out.println("nums are  " + coords.get(i) + " "+ coords.get(i+1));
            i++;
        }
        coords.clear();
        coords.addAll(Arrays.asList(5,8,6,8,7,8));
        notAvailable.clear();
        for (int i = 0; i < coords.size() ; i++) {
            boardspot e = new boardspot(i,i+1,true);
            notAvailable.add(e);
            i++;
        }
        Rooms dr = new Rooms(name,num,mr,corners,notAvailable);
        PlayableRooms.add(dr);
        /*
             lounge
         */

        name = "lounge";
        num = 3;
        mr = false;
        coords.clear();
        coords.addAll(Arrays.asList(0,19,0,24,6,24,6,19));
        corners.clear();

        for (int i = 0; i < 8; i++) {
            boardspot a = new boardspot(coords.get(i),coords.get(i+1),false);
            corners.add(a);
            System.out.println("nums  are  " + coords.get(i) + " "+ coords.get(i+1));
            i++;
        }
        coords.clear();
        coords.addAll(Arrays.asList(6,24));
        notAvailable.clear();
        for (int i = 0; i < coords.size() ; i++) {
            boardspot e = new boardspot(i,i+1,true);
            notAvailable.add(e);
            i++;
        }
        Rooms l = new Rooms(name,num,mr,corners,notAvailable);
        PlayableRooms.add(l);

        /*
        hall
         */









    }

    public static void main(String[] args) {
         new GameMain();
    }

}
