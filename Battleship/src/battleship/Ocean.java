package battleship;

import java.util.Random;

/**
 * Class of the 10X10 ocean
 */
public class Ocean {

    /**Used to quickly determine which ship is in any given location*/
    private Ship[][] ships = new Ship[10][10];

    /**The total number of shots fired by the user*/
    private int shotsFired;

    
    /**The number of times a shot hit a ship. 
    If the user shoots the same part of a ship more than once,
    every hit is counted, even though additional hits do the user any good.
    */
    private int hitCount;

    /**The number of ships sunk (10 ships in all)*/
    private int shipsSunk;

    /**
     * To initialize the empty ocean
     */
    private void initialOcean(){

        for(Ship[] item : this.ships){
            for(int n = 0 ; n <= 9; n++){
                item[n] = new EmptySea();
            }

        }
    }

    /**
     * Creates an ocean
     * initializes any game variables, such as how many shots have been fired.
     */
    public Ocean(){

        initialOcean();
        this.shotsFired = 0;
        this.hitCount = 0;
        this.shipsSunk = 0;

    }

    /**
     * Place all ten ships randomly on the (initially empty) ocean. Place larger ships before smaller ones,
     */
    void placeAllShipsRandomly(){
        
    	Random random = new Random();
    	//place battleship
    	boolean horizontal = random.nextInt(2)==0?true:false;
    	int row = random.nextInt(10);
    	int column = random.nextInt(10);
    	Battleship battleship = new Battleship();
    	while(true) {
    		if(battleship.okToPlaceShipAt(row, column, horizontal, this)==true) {
        		battleship.placeShipAt(row, column, horizontal, this);
        		//System.out.println("battleship placed");
        		break;	
        	}
    		row = random.nextInt(10);
    		column = random.nextInt(10);
    	}
    	//place cruiser
    	for(int i = 2; i>0;i--) {
    		boolean horizontal1 = random.nextInt(2)==0?true:false;
        	int row1 = random.nextInt(10);
        	int column1 = random.nextInt(10);
        	Cruiser cruiser = new Cruiser();
        	while(true) {
        		if(cruiser.okToPlaceShipAt(row1, column1, horizontal1, this)==true) {
        			cruiser.placeShipAt(row1, column1, horizontal1, this);
        			//System.out.println("cruiser placed");
        			break;
        		}
        		row1 = random.nextInt(10);
        		column1 = random.nextInt(10);	
        	}	
    	}
    	// place destroyer
    	for(int i = 3; i>0;i--) {
    		boolean horizontal2 = random.nextInt(2)==0?true:false;
        	int row2 = random.nextInt(10);
        	int column2 = random.nextInt(10);
        	Destroyer destroyer = new Destroyer();
        	while(true) {
        		if(destroyer.okToPlaceShipAt(row2, column2, horizontal2, this)==true) {
        			destroyer.placeShipAt(row2, column2, horizontal2, this);
        			//System.out.println("destroyer placed");
        			break;
        		}
        		row2 = random.nextInt(10);
        		column2 = random.nextInt(10);	
        	}	
    	}
    	// place submarine
    	for(int i = 4; i>0;i--) {
    		boolean horizontal3 = random.nextInt(2)==0?true:false;
        	int row3 = random.nextInt(10);
        	int column3 = random.nextInt(10);
        	Submarine submarine = new Submarine();
        	while(true) {
        		if(submarine.okToPlaceShipAt(row3, column3, horizontal3, this)==true) {
        			submarine.placeShipAt(row3, column3, horizontal3, this);
        			//System.out.println("submarine placed");
        			break;
        		}
        		row3 = random.nextInt(10);
        		column3 = random.nextInt(10);	
        	}	
    	}	
    }

    /**
     * Returns true if the given location contains a real ship, still afloat,false if it does not. 
     * In addition, this method updates the number of shots that have been fired, and the number of hits.
     * @param row 
     * @param column
     * @return True if there is a ship
     */
    boolean isOccupied(int row, int column){

        Ship ship = this.getShipArray()[row][column];
        String shipType = ship.getShipType();
        if(shipType != "empty") {
            return true;
        }else{
            return false;
        }
    }

    /**
     * Get shots you have fired.
     * @return the number of shots fired (in the game)
     */
    int getShotsFired(){
        return this.shotsFired;
    }

    /**
     * Get hits you already hit.
     * @return the number of hits recorded (in the game). All hits are counted, not just the first time a given square is hit.
     */
    int getHitCount(){
        return this.hitCount;
    }

    /**
     * Returns true if the given location contains a real ship, still afloat, (not an EmptySea), 
     * false if it does not. 
     * In addition, this method updates the number of shots that have been fired, and the number of hits. 
     * @param row
     * @param column
     * @return
     */
    boolean shootAt(int row, int column){
        //boolean checkRealShip = this.isOccupied(row, column);
        Ship[][] ships = this.getShipArray();
        boolean checkIsSunk = ships[row][column].isSunk();
        this.shotsFired += 1;
        if(checkIsSunk){
            return false;
        }else{
            boolean checkShiphit = ships[row][column].shootAt(row, column);        
    
            if(checkShiphit){
                this.hitCount +=1;
                if(ships[row][column].isSunk()) this.shipsSunk += 1;
                return true;
            }
            return false;
        }
    }

    /**
     * Get if the ship is sunk.
     * @return the number of ships sunk (in the game)
     */
    int getShipsSunk(){
        return this.shipsSunk;
    }

    /**
     * Check if the game is over.
     * @return true if all ships have been sunk, otherwise false
     */
    boolean isGameOver(){
        int sink = this.getShipsSunk();
        if (sink ==10){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Get ships array from ocean.
     * @return the 10x10 array of Ships
     */
    Ship[][] getShipArray(){

        return this.ships;
    }

    /** 
     * Prints the Ocean.
     * The top left corner square should be 0, 0.
     * Use "x" to indicate a location that you have fired upon and hit a (real) ship.
     * Use "-" to indicate a location that you have fired upon and found nothing there.
     * Use "s" to indicate a location containing a sunken ship.
     * and use "." (a period) to indicate a location that you have never fired upon.
     */
    void print(){

        System.out.println("  0 1 2 3 4 5 6 7 8 9");
        
        for(int i = 0 ; i<= 9; i++){
            //TODO
            System.out.print(i + " ");
           for(int j = 0; j <= 9; j++){
                Ship ship = ships[i][j];
                String stringOfStatus = "? ";
                if(ship.getShipType() == "empty"){
                    boolean checkHit = ship.getHit()[0];
                    if(checkHit){
                        stringOfStatus = ship.toString();
                    }else{
                        stringOfStatus = ". ";
                    }
                }else{
                    int bowColumn = ship.getBowColumn();
			        int bowRow = ship.getBowRow();
			        int position;
			        if(ship.isHorizontal()){
				        position = bowColumn - j;
			        }else{
				        position = bowRow - i;
			        }

			        if(ship.getHit()[position] == true){
                        stringOfStatus = ship.toString();
                    }else{
                        stringOfStatus = ". ";
                    }
                }
                System.out.print(stringOfStatus);
                if(j==9) System.out.print("\n");
            }
        }
    }
}
