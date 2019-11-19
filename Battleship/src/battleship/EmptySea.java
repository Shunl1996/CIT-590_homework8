package battleship;

/**
 * Class of an empty Sea
 */
public class EmptySea extends Ship {
    /**the length of an empty sea */
    static final int length = 1;

    public EmptySea(){
        super(length);
        
    }

    @Override
    boolean shootAt(int row, int column){
        this.getHit()[0] = true;
        return false;
    }

    @Override
    boolean isSunk(){
        return false;
    }

    @Override
    public String toString() {
        return "- ";
    }

    @Override
    public String getShipType(){
        return "empty";
    }
}
