/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4597.b7ac3a910 modeling language!*/


import java.util.*;

// line 25 "model.ump"
// line 98 "model.ump"
public class GameMain
{

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //GameMain Associations
    private Board board;
    private Deck deck;
    private List<Player> players;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public GameMain(Board aBoard, Deck aDeck)
    {
        if (aBoard == null || aBoard.getGameMain() != null)
        {
            throw new RuntimeException("Unable to create GameMain due to aBoard. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
        }
        board = aBoard;
        if (aDeck == null || aDeck.getGameMain() != null)
        {
            throw new RuntimeException("Unable to create GameMain due to aDeck. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
        }
        deck = aDeck;
        players = new ArrayList<Player>();
    }

    public GameMain(ArrayList aWeaponsForBoard, ArrayList aCharactersForBoard, ArrayList aRoomsForBoard, ArrayList aActiveForDeck, ArrayList aInactiveForDeck)
    {
        board = new Board(aWeaponsForBoard, aCharactersForBoard, aRoomsForBoard, this);
        deck = new Deck(aActiveForDeck, aInactiveForDeck, this);
        players = new ArrayList<Player>();
    }

    //------------------------
    // INTERFACE
    //------------------------
    /* Code from template association_GetOne */
    public Board getBoard()
    {
        return board;
    }
    /* Code from template association_GetOne */
    public Deck getDeck()
    {
        return deck;
    }
    /* Code from template association_GetMany */
    public Player getPlayer(int index)
    {
        Player aPlayer = players.get(index);
        return aPlayer;
    }

    public List<Player> getPlayers()
    {
        List<Player> newPlayers = Collections.unmodifiableList(players);
        return newPlayers;
    }

    public int numberOfPlayers()
    {
        int number = players.size();
        return number;
    }

    public boolean hasPlayers()
    {
        boolean has = players.size() > 0;
        return has;
    }

    public int indexOfPlayer(Player aPlayer)
    {
        int index = players.indexOf(aPlayer);
        return index;
    }
    /* Code from template association_IsNumberOfValidMethod */
    public boolean isNumberOfPlayersValid()
    {
        boolean isValid = numberOfPlayers() >= minimumNumberOfPlayers() && numberOfPlayers() <= maximumNumberOfPlayers();
        return isValid;
    }
    /* Code from template association_MinimumNumberOfMethod */
    public static int minimumNumberOfPlayers()
    {
        return 3;
    }
    /* Code from template association_MaximumNumberOfMethod */
    public static int maximumNumberOfPlayers()
    {
        return 6;
    }
    /* Code from template association_AddMNToOnlyOne */
    public Player addPlayer(HashSet aHand, ArrayList aSeen, Characters aCharacter, int aId)
    {
        if (numberOfPlayers() >= maximumNumberOfPlayers())
        {
            return null;
        }
        else
        {
            return new Player(aHand, aSeen, aCharacter, aId, this);
        }
    }

    public boolean addPlayer(Player aPlayer)
    {
        boolean wasAdded = false;
        if (players.contains(aPlayer)) { return false; }
        if (numberOfPlayers() >= maximumNumberOfPlayers())
        {
            return wasAdded;
        }

        GameMain existingGameMain = aPlayer.getGameMain();
        boolean isNewGameMain = existingGameMain != null && !this.equals(existingGameMain);

        if (isNewGameMain && existingGameMain.numberOfPlayers() <= minimumNumberOfPlayers())
        {
            return wasAdded;
        }

        if (isNewGameMain)
        {
            aPlayer.setGameMain(this);
        }
        else
        {
            players.add(aPlayer);
        }
        wasAdded = true;
        return wasAdded;
    }

    public boolean removePlayer(Player aPlayer)
    {
        boolean wasRemoved = false;
        //Unable to remove aPlayer, as it must always have a gameMain
        if (this.equals(aPlayer.getGameMain()))
        {
            return wasRemoved;
        }

        //gameMain already at minimum (3)
        if (numberOfPlayers() <= minimumNumberOfPlayers())
        {
            return wasRemoved;
        }
        players.remove(aPlayer);
        wasRemoved = true;
        return wasRemoved;
    }
    /* Code from template association_AddIndexControlFunctions */
    public boolean addPlayerAt(Player aPlayer, int index)
    {
        boolean wasAdded = false;
        if(addPlayer(aPlayer))
        {
            if(index < 0 ) { index = 0; }
            if(index > numberOfPlayers()) { index = numberOfPlayers() - 1; }
            players.remove(aPlayer);
            players.add(index, aPlayer);
            wasAdded = true;
        }
        return wasAdded;
    }

    public boolean addOrMovePlayerAt(Player aPlayer, int index)
    {
        boolean wasAdded = false;
        if(players.contains(aPlayer))
        {
            if(index < 0 ) { index = 0; }
            if(index > numberOfPlayers()) { index = numberOfPlayers() - 1; }
            players.remove(aPlayer);
            players.add(index, aPlayer);
            wasAdded = true;
        }
        else
        {
            wasAdded = addPlayerAt(aPlayer, index);
        }
        return wasAdded;
    }

    public void delete()
    {
        Board existingBoard = board;
        board = null;
        if (existingBoard != null)
        {
            existingBoard.delete();
        }
        Deck existingDeck = deck;
        deck = null;
        if (existingDeck != null)
        {
            existingDeck.delete();
        }
        for(int i=players.size(); i > 0; i--)
        {
            Player aPlayer = players.get(i - 1);
            aPlayer.delete();
        }
    }

}