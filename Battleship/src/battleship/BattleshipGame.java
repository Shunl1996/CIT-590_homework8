package battleship;

public class BattleshipGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly();
		
		ocean.print();
	}

}
