package battleship;

/**
 * Class of a battleship
 */
public class Battleship extends Ship {
    /**the length of a battleship */
    static final int length = 4;
    
    static final String shiptype = "Battleship";

    public Battleship(){
        super(length);
    }

    @Override
    public String getShipType(){

        return shiptype;
    }
}
