package battleship;

/**
 * The class of a cruiser
 */
public class Cruiser extends Ship {
    /**the length of a cruiser */
    static final int length = 3;

    static final String shiptype = "Cruiser";

    public Cruiser(){
        super(length);
    }

    @Override 
    public String getShipType(){
        return shiptype;
    }
}
