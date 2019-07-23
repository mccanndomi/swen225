import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.math.*;
import java.util.Arrays;
import java.util.Random;
import java.io.File;
import java.util.Scanner;
import java.io.FileReader;


public class GameMain {
    private ArrayList<PlayerObject> Players = new ArrayList<>();
    private ArrayList<Characters> PlayableCharcters = new ArrayList();
    private ArrayList<Characters> ActiveCharacters = new ArrayList<>();
    private ArrayList<Rooms> PlayableRooms = new ArrayList();
    private ArrayList<Weapons> PlayableWeapons = new ArrayList();
    public int diceone;
    public int dicetwo;
    public  int numpeople;
    public GameMain() { CreateGame();
    }

    public void CreateGame() {
        GenerateStartCharacters();
        GenerateRooms();
        GenerateWeapons();
        chooseCharacters();
        createMurder();
        playGame();
    }

    public void chooseCharacters() {
        numpeople = -1;
        try {
            while (numpeople < 0 || numpeople > 7) {
                System.out.println("How many people would like to play ? (max 6 players)");
                InputStreamReader isr = new InputStreamReader(System.in);
                BufferedReader br = new BufferedReader(isr);
                String answer = br.readLine();
                numpeople = Integer.parseInt(answer);
            }
        } catch (IOException ioerror) {
            System.out.println("io exception");
        }

        try{
            for (int i = 0; i <  numpeople; i++) {
                System.out.println("enter player " + i + "'s  name");
                InputStreamReader isr = new InputStreamReader(System.in);
                BufferedReader br = new BufferedReader(isr);
                String answer = br.readLine();
                PlayerObject p = new PlayerObject(answer, i);
                Players.add(p);
            }

        }catch(IOException ioerror){
            System.out.println("io exception");
        }


        Characters player = null;
        System.out.println("num people is " + numpeople);
        int num = 7;
        String confirm = "";
        for (PlayerObject p : Players){
           // while (player == null) {
                System.out.println(p.getName() + ", please choose a character to play as by entering the number next to their name ");
                System.out.println("Your options are: \n");
                for (Characters c : PlayableCharcters){
                    if (!ActiveCharacters.contains(c)){
                        System.out.println(c.getIDnumber() + " : " + c.getName());
                    }
                }
                //System.out.println("0 : Mrs. White \n" + "1 : Colonel Mustard \n" + "2 : Miss Scarlett  \n" + "3: Professor Plum \n" + "4 : Mrs. Peacock \n" + "5 : Mr. Green \n");
                try {
                    InputStreamReader isr = new InputStreamReader(System.in);
                    BufferedReader br = new BufferedReader(isr);


                    String answer = br.readLine();
                    num = Integer.parseInt(answer);

                    System.out.println("you have selected " + PlayableCharcters.get(num).getName() + " if this is correct please press y, else press n ");
                    confirm = br.readLine();



                    if (confirm.equals("y") || confirm.equals("Y")) {
                        player = PlayableCharcters.get(num);
                        PlayableCharcters.get(num).setIsPlayer(true);
                        ActiveCharacters.add(PlayableCharcters.get(num));
                        p.setCharacterNum(num);
                    }
                    if (player != null) {
                        System.out.println("You have selected your player,  you are " + PlayableCharcters.get(num).getName() + " and your start postion is ( " + PlayableCharcters.get(num).getLocationX() + " , " + PlayableCharcters.get(num).getLocationY() + " )");
                    }

                } catch (IOException wronginput) {
                    System.out.println("io exception");
                }

        }

    }

    public void createMurder(){
        Random room = new Random();
        Random weapon = new Random();
        Random character = new Random();
        int r = room.nextInt(9);
        int w = weapon.nextInt(6);
        int c = character.nextInt(numpeople);
        PlayableRooms.get(r).setMurderRoom(true);
        PlayableWeapons.get(w).setMurderWeapon(true);
        int cnum = Players.get(c).getCharacterNum();
        PlayableCharcters.get(cnum).setMurderPerson(true);
        int murderernum = 0;
        for (Characters ch : PlayableCharcters){
            if(ch.getMurderPerson()){
                System.out.println(ch.getName() + " is murderer");
                murderernum = ch.getIDnumber();
            }
        }
        for (Weapons we : PlayableWeapons){
            if(we.getMurderWeapon()){
                System.out.println(we.getName() + " is murder weapon");
            }
        }
        for (Rooms ro : PlayableRooms){
            if(ro.getMurderRoom()){
                System.out.println(ro.getName() + " is murder room");
            }
        }
        for (PlayerObject pl : Players){
            if (pl.getCharacterNum() == murderernum){
                System.out.println(pl.getName() + " is mudrder player ");
            }
        }





    }


    public void playGame(){

        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            System.out.println("press r to roll dice");
            String answer = br.readLine();
            if(answer.equals("r") || answer.equals("R")){
                rollDice();
                int sum = diceone + dicetwo;
                System.out.println("you rolled a " + diceone + " and a " + dicetwo + "\n you can move ");
            }

            String confirm = br.readLine();
        } catch (IOException wronginput) {
            System.out.println("io exception");
        }


    }


    public void GenerateStartCharacters(){
        ArrayList<Integer> startlocations = new ArrayList<>();
        System.out.println("characters");

        startlocations.addAll(Arrays.asList(9,-1,0,16,7,24));
        /*
        set up stuff that is imutable, create characters and add to a list
         */
        ArrayList<String> characternames = new ArrayList<>();
        characternames.add("Mrs. White");
        characternames.add("Colonel Mustard");
        characternames.add("Miss Scarlett");
        characternames.add("Professor Plum");
        characternames.add("Mrs. Peacock");
        characternames.add("Mr. Green");


        ArrayList<Integer> characterStartLocaations = new ArrayList<>();
        characterStartLocaations.addAll(Arrays.asList(9,0,0,17,7,24,23,19,23,6,14,0));
        int pos = 0;
        for (int i = 0; i < 6 ; i++) {
            String tempname = characternames.get(i);
            Boolean tempBool = false;
            int lx = characterStartLocaations.get(pos);
            int ly = characterStartLocaations.get(pos+1);
            Characters temp = new Characters(i,tempname,tempBool,lx,ly, i);
            PlayableCharcters.add(temp);
            pos = pos +2;
        }
        /*
        for (Characters c : PlayableCharcters){
            System.out.println(c.getName());
            System.out.println(c.getLocationX());
            System.out.println(c.getLocationY());
        }
        */


        /*
        do player interaction stuff, eg : choose a player
         */

        Random r = new Random();
        int randomInteger = r.nextInt(6);



    }
    public void GenerateWeapons(){
        System.out.println("weapons");
        ArrayList<String> weaponnames = new ArrayList<>();
        weaponnames.add("Candlestick");
        weaponnames.add("Dagger");
        weaponnames.add("Lead Pipe");
        weaponnames.add("Revolver");
        weaponnames.add("Rope");
        weaponnames.add("Spanner");
        Random rand = new Random();
        ArrayList<Integer> roomnums = new ArrayList<>();
        roomnums.addAll(Arrays.asList(0,1,2,3,4,5));
        for (int i = 0; i < 6; i++) {
            int p = rand.nextInt(roomnums.size());
            int n = roomnums.get(p);
            Weapons a = new Weapons(i,weaponnames.get(i), n, false);
            roomnums.remove(p);
            PlayableWeapons.add(a);

        }
        /*
        for (Weapons w : PlayableWeapons){
            System.out.println(w.getName());
            System.out.println(w.getRoom());
            System.out.println(w.getWeaponNumber());
        }
        */



    }
    public void GenerateRooms(){
        System.out.println("rooms");
        int pos = 0;


        try {
            File file = new File("C:\\Users\\aidan\\IdeaProjects\\SWEN225_A1\\src\\roomData.txt");
            Scanner input = new Scanner(file);
            BufferedReader br = new BufferedReader(new FileReader(file));
            input = new Scanner(file);
            String in = br.readLine();
            if (in.equals("Rooms:")){
                while(PlayableRooms.size() < 9){
                    String name = br.readLine();
                    String line = br.readLine();
                    String [] tokens = line.split(",");

                    ArrayList<boardspot> corners = new ArrayList<>();
                    //name = input.nextLine();
                    int p = 0;
                    int j = p+1;
                   for(int i =0 ; i < 4; i++){
                        int a = Integer.parseInt( tokens[p]);
                        int b = Integer.parseInt( tokens[j]);
                        boardspot c = new boardspot(a,b,true);
                        corners.add(c);
                        p = p+2;
                        j = p+1;
                    }
                    line = br.readLine();
                    String na [] = line.split(",");
                    ArrayList<boardspot> notAvailable = new ArrayList<>();
                    //name = input.nextLine();

                    p = 0;
                    j = p + 1;
                    for(int i = 0; i < na.length/2; i++){

                        int a = Integer.parseInt(tokens[p]);
                        int b = Integer.parseInt(tokens[j]);
                        boardspot c = new boardspot(a,b,false);
                        notAvailable.add(c);
                        p = p+2;
                        j = p+1;
                    }
                    ArrayList<boardspot> doors = new ArrayList<>();
                    line = br.readLine();
                    String d [] = line.split(",");
                    p = 0;
                    j = p+1;
                    for (int i = 0; i < d.length/2; i++) {
                        int a = Integer.parseInt(tokens[p]);
                        int b = Integer.parseInt(tokens[j]);
                        boardspot c = new boardspot(a, b, true);
                        doors.add(c);
                        p = p+2;
                        j = p+1;
                    }

                    System.out.println(pos);
                    Rooms r = new Rooms(name, pos, false , corners, notAvailable, doors);
                    pos++;
                    PlayableRooms.add(r);
                }

            }

        } catch (IOException sc){
            System.out.println("ioexception");
        }
        System.out.println("clear");
        for (Rooms r : PlayableRooms){
            System.out.println(r.getName());


            System.out.println("corners " + r.corners.size());
            System.out.println("not avilable " + r.notAvailable.size());
            System.out.println("doors " + r.Doors.size());
            System.out.println(" ");
        }
        /*



        String name = "Kitchen";
        int num = 1;
        boolean mr = false;

        ArrayList<Integer> coords = new ArrayList<>();
        coords.addAll(Arrays.asList(0,1,0,6,5,6,5,1));
        ArrayList<boardspot> corners = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            boardspot a = new boardspot(coords.get(i),coords.get(i+1),false);
            corners.add(a);
            //System.out.println("nums are " + coords.get(i) + " "+ coords.get(i+1));
            i++;
        }

        coords.clear();
        coords.addAll(Arrays.asList(5,1));
        ArrayList<boardspot> notAvailable = new ArrayList<>();
        for (int i = 0; i < coords.size() ; i++) {
            boardspot e = new boardspot(i,i+1,true);
            notAvailable.add(e);
            i++;
        }
        ArrayList<Integer> Doors = new ArrayList<>();
        Doors.addAll(Arrays.asList(4,6));
        Rooms k = new Rooms(name,num,mr,corners,notAvailable, Doors);
        PlayableRooms.add(k);

        //dinging room


        name = "Dining Room";
        num = 2;
        mr = false;
        coords.clear();
        coords.addAll(Arrays.asList(0,9,0,15,7,15,7,9));
        corners.clear();

        for (int i = 0; i < 8; i++) {
            boardspot a = new boardspot(coords.get(i),coords.get(i+1),false);
            corners.add(a);
            //System.out.println("nums are  " + coords.get(i) + " "+ coords.get(i+1));
            i++;
        }
        coords.clear();
        coords.addAll(Arrays.asList(5,9,6,9,7,9));
        notAvailable.clear();
        for (int i = 0; i < coords.size() ; i++) {
            boardspot e = new boardspot(i,i+1,true);
            notAvailable.add(e);
            i++;
        }
        Doors.clear();
        Doors.addAll(Arrays.asList(7,12,6,15));
        Rooms dr = new Rooms(name,num,mr,corners,notAvailable, Doors);
        PlayableRooms.add(dr);


        name = "lounge";
        num = 3;
        mr = false;
        coords.clear();
        coords.addAll(Arrays.asList(0,19,0,24,6,24,6,19));
        corners.clear();

        for (int i = 0; i < 8; i++) {
            boardspot a = new boardspot(coords.get(i),coords.get(i+1),false);
            corners.add(a);
            //System.out.println("nums  are  " + coords.get(i) + " "+ coords.get(i+1));
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
        Doors.clear();
        Doors.addAll(Arrays.asList(6,19));
        Rooms l = new Rooms(name,num,mr,corners,notAvailable, Doors);
        PlayableRooms.add(l);


        name = "hall";
        num = 4;
        mr = false;
        coords.clear();
        coords.addAll(Arrays.asList(9,18, 9, 24, 14, 24, 14, 18));
        corners.clear();

        for (int i = 0; i < 8; i++) {
            boardspot a = new boardspot(coords.get(i),coords.get(i+1),false);
            corners.add(a);
           // System.out.println(" nums  are  " + coords.get(i) + " "+ coords.get(i+1));
            i++;
        }
        coords.clear();
        coords.addAll(Arrays.asList(9,24,14,24));
        notAvailable.clear();
        for (int i = 0; i < coords.size() ; i++) {
            boardspot e = new boardspot(i,i+1,true);
            notAvailable.add(e);
            i++;
        }
        Doors.clear();
        Doors.addAll(Arrays.asList(11,18, 12,18,14,20));
        Rooms h = new Rooms(name,num,mr,corners,notAvailable, Doors);
        PlayableRooms.add(h);

        name = "study";
        num = 5;
        mr = false;
        coords.clear();
        coords.addAll(Arrays.asList(17,21, 17,24,23,24,23,21));
        corners.clear();

        for (int i = 0; i < 8; i++) {
            boardspot a = new boardspot(coords.get(i),coords.get(i+1),false);
            corners.add(a);
            //System.out.println(" nums  are " + coords.get(i) + " "+ coords.get(i+1));
            i++;
        }
        coords.clear();
        coords.addAll(Arrays.asList(17,24));
        notAvailable.clear();
        for (int i = 0; i < coords.size() ; i++) {
            boardspot e = new boardspot(i,i+1,true);
            notAvailable.add(e);
            i++;
        }
        Doors.clear();
        Doors.addAll(Arrays.asList(17,21));
        Rooms s = new Rooms(name,num,mr,corners,notAvailable, Doors);
        PlayableRooms.add(s);

        name = "library";
        num = 6;
        mr = false;
        coords.clear();
        coords.addAll(Arrays.asList(17,14,17,18,23,18,23,14));
        corners.clear();
        for (int i = 0; i < 8; i++) {
            boardspot a = new boardspot(coords.get(i),coords.get(i+1),false);
            corners.add(a);
            //System.out.println("nums  are " + coords.get(i) + " "+ coords.get(i+1));
            i++;
        }
        coords.clear();
        coords.addAll(Arrays.asList(17,14,17,18,23,18,23,14));
        notAvailable.clear();
        for (int i = 0; i < coords.size() ; i++) {
            boardspot e = new boardspot(i,i+1,true);
            notAvailable.add(e);
            i++;
        }
        Doors.clear();
        Doors.addAll(Arrays.asList(17,16, 20, 14));
        Rooms lib = new Rooms(name,num,mr,corners,notAvailable, Doors);
        PlayableRooms.add(lib);

        name = "billard room";
        num = 7;
        mr = false;
        coords.clear();
        coords.addAll(Arrays.asList(18,8, 18, 12, 23, 12, 23,8 ));
        corners.clear();
        for (int i = 0; i < 8; i++) {
            boardspot a = new boardspot(coords.get(i),coords.get(i+1),false);
            corners.add(a);
            //System.out.println(" nums are  " + coords.get(i) + " "+ coords.get(i+1));
            i++;
        }
        coords.clear();
        coords.addAll(Arrays.asList());
        notAvailable.clear();
        for (int i = 0; i < coords.size() ; i++) {
            boardspot e = new boardspot(i,i+1,true);
            notAvailable.add(e);
            i++;
        }
        Doors.clear();
        Doors.addAll(Arrays.asList(15, 9, 19,12));
        Rooms billard = new Rooms(name,num,mr,corners,notAvailable, Doors);
        PlayableRooms.add(billard);

        name = "conservatory";
        num = 8;
        mr = false;
        coords.clear();
        coords.addAll(Arrays.asList(18,1,18,5,23,5,23,1));
        corners.clear();
        for (int i = 0; i < 8; i++) {
            boardspot a = new boardspot(coords.get(i),coords.get(i+1),false);
            corners.add(a);
            //System.out.println(" nums are  " + coords.get(i) + "  "+ coords.get(i+1));
            i++;
        }
        coords.clear();
        coords.addAll(Arrays.asList(18,5,23,5));
        notAvailable.clear();
        for (int i = 0; i < coords.size() ; i++) {
            boardspot e = new boardspot(i,i+1,true);
            notAvailable.add(e);
            i++;
        }
        Doors.clear();
        Doors.addAll(Arrays.asList(18, 4));
        Rooms conserve = new Rooms(name,num,mr,corners,notAvailable, Doors);
        PlayableRooms.add(conserve);

        name = "ball room";
        num = 9;
        mr = false;
        coords.clear();
        coords.addAll(Arrays.asList(8,1,8,7,15,7,15,1));
        corners.clear();
        for (int i = 0; i < 8; i++) {
            boardspot a = new boardspot(coords.get(i),coords.get(i+1),false);
            corners.add(a);
            //System.out.println(" nums are  " + coords.get(i) + "   "+ coords.get(i+1));
            i++;
        }
        coords.clear();
        coords.addAll(Arrays.asList(8,1,9,1,14,1,15,1));
        notAvailable.clear();
        for (int i = 0; i < coords.size() ; i++) {
            boardspot e = new boardspot(i,i+1,true);
            notAvailable.add(e);
            i++;
        }
        Doors.clear();
        Doors.addAll(Arrays.asList(8, 5, 10, 7, 15, 7, 16, 5));
        Rooms ball = new Rooms(name,num,mr,corners,notAvailable, Doors);
        PlayableRooms.add(ball);
        */

    }

    public void rollDice (){
        Random diceo = new Random();
        Random dicet = new Random();
        int d1 = diceo.nextInt(7);
        int d2 = dicet.nextInt(7);
        diceone = d1;
        dicetwo = d2;
    }

    public static void main(String[] args) {
         new GameMain();
    }

}
