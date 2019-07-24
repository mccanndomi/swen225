/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4597.b7ac3a910 modeling language!*/


import java.util.Objects;

// line 17 "model.ump"
// line 93 "model.ump"
public class boardSpot
{

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //boardSpot Attributes
    private String type;
    private String name;
    private int x;
    private int y;
    private boolean available;

    //boardSpot Associations
    private Board board;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public boardSpot(String aType, String aName, int aX, int aY, boolean aAvailable, Board aBoard)
    {
        type = aType;
        name = aName;
        x = aX;
        y = aY;
        available = aAvailable;
        boolean didAddBoard = setBoard(aBoard);
        if (!didAddBoard)
        {
            throw new RuntimeException("Unable to create boardSpot due to board. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
        }
    }

    //------------------------
    // INTERFACE
    //------------------------

    public boolean setType(String aType)
    {
        boolean wasSet = false;
        type = aType;
        wasSet = true;
        return wasSet;
    }


    public boolean setName(String aName)
    {
        boolean wasSet = false;
        name = aName;
        wasSet = true;
        return wasSet;
    }

    public boolean setX(int aX)
    {
        boolean wasSet = false;
        x = aX;
        wasSet = true;
        return wasSet;
    }

    public boolean setY(int aY)
    {
        boolean wasSet = false;
        y = aY;
        wasSet = true;
        return wasSet;
    }

    public boolean setAvailable(boolean aAvailable)
    {
        boolean wasSet = false;
        available = aAvailable;
        wasSet = true;
        return wasSet;
    }

    public String getType()
    {
        return type;
    }

    public String getName()
    {
        return name;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public boolean getAvailable()
    {
        return available;
    }
    /* Code from template association_GetOne */
    public Board getBoard()
    {
        return board;
    }
    /* Code from template association_SetOneToMany */
    public boolean setBoard(Board aBoard)
    {
        boolean wasSet = false;
        if (aBoard == null)
        {
            return wasSet;
        }

        Board existingBoard = board;
        board = aBoard;
        if (existingBoard != null && !existingBoard.equals(aBoard))
        {
            existingBoard.removeBoardSpot(this);
        }
        board.addBoardSpot(this);
        wasSet = true;
        return wasSet;
    }

    public void delete()
    {
        Board placeholderBoard = board;
        this.board = null;
        if(placeholderBoard != null)
        {
            placeholderBoard.removeBoardSpot(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        boardSpot boardSpot = (boardSpot) o;
        return type == boardSpot.type &&
                x == boardSpot.x &&
                y == boardSpot.y &&
                available == boardSpot.available &&
                Objects.equals(name, boardSpot.name) &&
                Objects.equals(board, boardSpot.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name, x, y, available, board);
    }

    public String toString()
    {
        return super.toString() + "["+
                "name" + ":" + getName()+ "," +
                "x" + ":" + getX()+ "," +
                "y" + ":" + getY()+ "," +
                "available" + ":" + getAvailable()+ "]" + System.getProperties().getProperty("line.separator") +
                "  " + "type" + "=" + (getType() != null ? !getType().equals(this)  ? getType().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
                "  " + "board = "+(getBoard()!=null?Integer.toHexString(System.identityHashCode(getBoard())):"null");
    }
}