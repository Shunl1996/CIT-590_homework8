package battleship;

/**
 * Class of a destroyer
 */
public class Destroyer extends Ship {
    /** length of a destroyer */
    static final int length = 2;

    static final String shiptype = "Destroyer";

    public Destroyer(){
        super(length);
    }

    @Override 
    public String getShipType(){
        return shiptype;
    }
}
