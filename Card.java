/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4597.b7ac3a910 modeling language!*/



// line 37 "model.ump"
// line 113 "model.ump"
public class Card
{

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //Card Attributes
    private String name;
    private boolean isMurderCard;

    //Card Associations
    private Deck deck;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public Card(String aName, boolean aIsMurderCard, Deck aDeck)
    {
        name = aName;
        isMurderCard = aIsMurderCard;
        boolean didAddDeck = setDeck(aDeck);
        if (!didAddDeck)
        {
            throw new RuntimeException("Unable to create card due to deck. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
        }
    }

    //------------------------
    // INTERFACE
    //------------------------

    public boolean setName(String aName)
    {
        boolean wasSet = false;
        name = aName;
        wasSet = true;
        return wasSet;
    }

    public boolean setIsMurderCard(boolean aIsMurderCard)
    {
        boolean wasSet = false;
        isMurderCard = aIsMurderCard;
        wasSet = true;
        return wasSet;
    }

    public String getName()
    {
        return name;
    }

    public boolean getIsMurderCard()
    {
        return isMurderCard;
    }
    /* Code from template association_GetOne */
    public Deck getDeck()
    {
        return deck;
    }
    /* Code from template association_SetOneToAtMostN */
    public boolean setDeck(Deck aDeck)
    {
        boolean wasSet = false;
        //Must provide deck to card
        if (aDeck == null)
        {
            return wasSet;
        }

        //deck already at maximum (21)
        if (aDeck.numberOfCards() >= Deck.maximumNumberOfCards())
        {
            return wasSet;
        }

        Deck existingDeck = deck;
        deck = aDeck;
        if (existingDeck != null && !existingDeck.equals(aDeck))
        {
            boolean didRemove = existingDeck.removeCard(this);
            if (!didRemove)
            {
                deck = existingDeck;
                return wasSet;
            }
        }
        deck.addCard(this);
        wasSet = true;
        return wasSet;
    }

    public void delete()
    {
        Deck placeholderDeck = deck;
        this.deck = null;
        if(placeholderDeck != null)
        {
            placeholderDeck.removeCard(this);
        }
    }


    public String toString()
    {
        return super.toString() + "["+
                "name" + ":" + getName()+ "," +
                "isMurderCard" + ":" + getIsMurderCard()+ "]" + System.getProperties().getProperty("line.separator") +
                "  " + "deck = "+(getDeck()!=null?Integer.toHexString(System.identityHashCode(getDeck())):"null");
    }
}