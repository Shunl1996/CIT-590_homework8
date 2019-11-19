package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OceanTest2 {
	Ocean ocean;
	Ship battleship;
	Ship cruiser;
	Ship destroyer;
	Ship submarine;
	Ship emptysea;

	@BeforeEach
	void setUp() throws Exception {
		this.battleship = new Battleship();
		this.cruiser = new Cruiser();
		this.destroyer = new Destroyer();
		this.submarine = new Submarine();
		this.emptysea = new EmptySea();
		this.ocean = new Ocean();
	}

	@Test
	void testPlaceAllShipsRandomly() {
		ocean.placeAllShipsRandomly();
		int shipCount = 0;
		int battleshipCount = 0;
		int cruiserCount = 0;
		int destroyerCount = 0;
		int submarineCount = 0;
		int emptyCount = 0;
		for (int i = 0; i <= 9; i++) {
			for (int j = 0; j <= 9; j++) {
				if (!(ocean.getShipArray()[i][j] instanceof EmptySea)) {
					shipCount++;
					if (ocean.getShipArray()[i][j] instanceof Battleship) {
						battleshipCount++;
					}
					if (ocean.getShipArray()[i][j] instanceof Cruiser) {
						cruiserCount++;
					}
					if (ocean.getShipArray()[i][j] instanceof Destroyer) {
						destroyerCount++;
					}
					if (ocean.getShipArray()[i][j] instanceof Submarine) {
						submarineCount++;
					}
				}else {
					emptyCount++;
				}
			}
		}
		assertEquals(20, shipCount);
		assertEquals(1, battleshipCount/4);
		assertEquals(2, cruiserCount/3);
		assertEquals(3, destroyerCount/2);
		assertEquals(4, submarineCount);
		assertEquals(80, emptyCount);
	}



	@Test
	void testIsOccupied() {
		
		// Check place have a real ship.
		cruiser.placeShipAt(2, 3, true, ocean);
		assertTrue(ocean.isOccupied(2, 3));
		assertTrue(ocean.isOccupied(2, 2));
		assertTrue(ocean.isOccupied(2, 1));
		
		// Check place has an emptysea.
		assertFalse(ocean.isOccupied(1, 1));
		assertFalse(ocean.isOccupied(9, 9));
		
		// Check place out of boundary.
		assertFalse(ocean.isOccupied(-1, 1));
		assertFalse(ocean.isOccupied(-1, -1));
		assertFalse(ocean.isOccupied(19, -19));
	}

	@Test
	void testShootAt() {
		destroyer.placeShipAt(5, 5, false, ocean);
		// Shoot a real, float ship.
		assertTrue(ocean.shootAt(5, 5));
		assertEquals(1, ocean.getShotsFired());
		assertEquals(1, ocean.getHitCount());
		// Repeat, still true
		assertTrue(ocean.shootAt(5, 5));
		assertEquals(2, ocean.getShotsFired());
		assertEquals(2, ocean.getHitCount());
		// Shoot until sink.
		assertTrue(ocean.shootAt(4, 5));
		assertEquals(3, ocean.getShotsFired());
		assertEquals(1, ocean.getShipsSunk());
		// Repeat shoot after sink will be false, and the hit count don't increase.
		assertFalse(ocean.shootAt(5, 5));
		assertEquals(4, ocean.getShotsFired());
		assertEquals(3, ocean.getHitCount());
		assertFalse(ocean.shootAt(4, 5));
		assertEquals(5, ocean.getShotsFired());
		assertEquals(3, ocean.getHitCount());
		// Shoot at empty sea.
		ocean.shootAt(0, 0);
		assertEquals(6, ocean.getShotsFired());
		assertEquals(3, ocean.getHitCount());
	}

	@Test
	void testGetShotsFired() {
		//Before shoot, initiall = 0.
		assertEquals(0, ocean.getShotsFired());
		ocean.shootAt(3, 4);
		ocean.shootAt(1, 9);
		ocean.shootAt(2, 2);
		ocean.shootAt(4 ,4);
		ocean.shootAt(4 ,5);
		ocean.shootAt(3 ,4);
		assertEquals(6, ocean.getShotsFired());
	}

	@Test
	void testGetHitCount() {
		cruiser.placeShipAt(2, 3, true, ocean);
		// Before shoot:
		assertEquals(0, ocean.getHitCount());
		// Shoot once but don't hit.
		ocean.shootAt(0, 3);
		assertEquals(0, ocean.getHitCount());
		// After hit.
		ocean.shootAt(2, 3);
		assertEquals(1, ocean.getHitCount());
		ocean.shootAt(2, 3);
		assertEquals(2, ocean.getHitCount());
	}

	@Test
	void testGetShipsSunk() {
		// Initially, no ship is sunk.
		assertEquals(0, ocean.getShipsSunk());
	}

	@Test
	void testIsGameOver() {
		// Before game.
		assertFalse(ocean.isGameOver());
		// Shoot every point.
		ocean.placeAllShipsRandomly();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				ocean.shootAt(i, j);
			}
		}
		// Ships sunk should equal to 10 and game should be over.
		assertEquals(10, ocean.getShipsSunk());
		assertTrue(ocean.isGameOver());
	}

	@Test
	void testGetShipArray() {
		cruiser.placeShipAt(2, 3, true, ocean);
		Ship ships1 = ocean.getShipArray()[2][3];
		assertEquals("cruiser", ships1.getShipType());
		Ship ships2 = ocean.getShipArray()[2][2];
		assertEquals("cruiser", ships2.getShipType());
		Ship ships3 = ocean.getShipArray()[2][1];
		assertEquals("cruiser", ships3.getShipType());
	}


}
