/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4597.b7ac3a910 modeling language!*/


import java.util.*;

// line 31 "model.ump"
// line 107 "model.ump"
public class Deck
{

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //Deck Attributes
    private ArrayList<BoardEntity> active;
    private ArrayList<BoardEntity> inactive;

    //Deck Associations
    private List<Card> cards;
    private GameMain gameMain;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public Deck(ArrayList aActive, ArrayList aInactive, GameMain aGameMain)
    {
        active = aActive;
        inactive = aInactive;
        cards = new ArrayList<Card>();
        if (aGameMain == null || aGameMain.getDeck() != null)
        {
            throw new RuntimeException("Unable to create Deck due to aGameMain. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
        }
        gameMain = aGameMain;
    }

    public Deck(ArrayList aActive, ArrayList aInactive, Board aBoardForGameMain)
    {
        active = aActive;
        inactive = aInactive;
        cards = new ArrayList<Card>();
        //gameMain = new GameMain(aBoardForGameMain, this);
    }

    //------------------------
    // INTERFACE
    //------------------------

    public boolean setActive(ArrayList aActive)
    {
        boolean wasSet = false;
        active = aActive;
        wasSet = true;
        return wasSet;
    }

    public boolean setInactive(ArrayList aInactive)
    {
        boolean wasSet = false;
        inactive = aInactive;
        wasSet = true;
        return wasSet;
    }

    public ArrayList getActive()
    {
        return active;
    }

    public ArrayList getInactive()
    {
        return inactive;
    }
    /* Code from template association_GetMany */
    public Card getCard(int index)
    {
        Card aCard = cards.get(index);
        return aCard;
    }

    public List<Card> getCards()
    {
        List<Card> newCards = Collections.unmodifiableList(cards);
        return newCards;
    }

    public int numberOfCards()
    {
        int number = cards.size();
        return number;
    }

    public boolean hasCards()
    {
        boolean has = cards.size() > 0;
        return has;
    }

    public int indexOfCard(Card aCard)
    {
        int index = cards.indexOf(aCard);
        return index;
    }
    /* Code from template association_GetOne */
    public GameMain getGameMain()
    {
        return gameMain;
    }
    /* Code from template association_IsNumberOfValidMethod */
    public boolean isNumberOfCardsValid()
    {
        boolean isValid = numberOfCards() >= minimumNumberOfCards() && numberOfCards() <= maximumNumberOfCards();
        return isValid;
    }
    /* Code from template association_RequiredNumberOfMethod */
    public static int requiredNumberOfCards()
    {
        return 21;
    }
    /* Code from template association_MinimumNumberOfMethod */
    public static int minimumNumberOfCards()
    {
        return 21;
    }
    /* Code from template association_MaximumNumberOfMethod */
    public static int maximumNumberOfCards()
    {
        return 21;
    }
    /* Code from template association_AddMNToOnlyOne */
    public Card addCard(String aName, boolean aIsMurderCard)
    {
        if (numberOfCards() >= maximumNumberOfCards())
        {
            return null;
        }
        else
        {
            return new Card(aName, aIsMurderCard, this);
        }
    }

    public boolean addCard(Card aCard)
    {
        boolean wasAdded = false;
        if (cards.contains(aCard)) { return false; }
        if (numberOfCards() >= maximumNumberOfCards())
        {
            return wasAdded;
        }

        Deck existingDeck = aCard.getDeck();
        boolean isNewDeck = existingDeck != null && !this.equals(existingDeck);

        if (isNewDeck && existingDeck.numberOfCards() <= minimumNumberOfCards())
        {
            return wasAdded;
        }

        if (isNewDeck)
        {
            aCard.setDeck(this);
        }
        else
        {
            cards.add(aCard);
        }
        wasAdded = true;
        return wasAdded;
    }

    public boolean removeCard(Card aCard)
    {
        boolean wasRemoved = false;
        //Unable to remove aCard, as it must always have a deck
        if (this.equals(aCard.getDeck()))
        {
            return wasRemoved;
        }

        //deck already at minimum (21)
        if (numberOfCards() <= minimumNumberOfCards())
        {
            return wasRemoved;
        }
        cards.remove(aCard);
        wasRemoved = true;
        return wasRemoved;
    }

    public void delete()
    {
        for(int i=cards.size(); i > 0; i--)
        {
            Card aCard = cards.get(i - 1);
            aCard.delete();
        }
        GameMain existingGameMain = gameMain;
        gameMain = null;
        if (existingGameMain != null)
        {
            existingGameMain.delete();
        }
    }


    public String toString()
    {
        return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
                "  " + "active" + "=" + (getActive() != null ? !getActive().equals(this)  ? getActive().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
                "  " + "inactive" + "=" + (getInactive() != null ? !getInactive().equals(this)  ? getInactive().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
                "  " + "gameMain = "+(getGameMain()!=null?Integer.toHexString(System.identityHashCode(getGameMain())):"null");
    }
}