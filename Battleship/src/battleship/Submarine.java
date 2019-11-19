package battleship;

/**
 * Class of a submarine
 */
public class Submarine extends Ship {
    /**the length of a submarine */
    static final int length = 1;

    static final String shiptype = "Submarine";

    public Submarine(){
        super(length);
    }

    @Override 
    public String getShipType(){
        return shiptype;
    }
}
