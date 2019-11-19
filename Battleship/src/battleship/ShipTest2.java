package battleship;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipTest2 {
	Ship battleship;
	Ship cruiser;
	Ship destroyer;
	Ship submarine;
	Ship emptysea;
	Ocean ocean;
	
	@BeforeEach
	void setUp() throws Exception {
		this.battleship = new Battleship();
		this.cruiser = new Cruiser();
		this.destroyer = new Destroyer();
		this.submarine = new Submarine();
		this.emptysea = new EmptySea();
		this.ocean = new Ocean();
		cruiser.placeShipAt(2, 3, true, ocean);
	}


	@Test
	void testGetLength() {
		
		// Check lengths.
		assertEquals(4, battleship.getLength());
		assertEquals(3, cruiser.getLength());		
		assertEquals(2, destroyer.getLength());
		assertEquals(1, submarine.getLength());
	}

	@Test
	void testGetBowRow() {
		
		// Test for place has a real, horizontal ship.
		Ship currentShip1 = ocean.getShipArray()[2][3];
		assertEquals(2,currentShip1.getBowRow());
		
		// Test for place has a real vertical ship.
		battleship.placeShipAt(4, 3, false, ocean);
		Ship currentShip2 = ocean.getShipArray()[3][3];
		assertEquals(4,currentShip2.getBowRow());
		
		//Test for place doesn't have a ship. Should return 0 by default, i.e. empty sea does not have a bow.
		Ship currentShip3 = ocean.getShipArray()[5][5];
		assertEquals(0,currentShip3.getBowRow());
	}

	@Test
	void testGetBowColumn() {
		// Test for place has a real, horizontal ship.
		Ship currentShip1 = ocean.getShipArray()[2][2];
		assertEquals(3,currentShip1.getBowColumn());
		
		// Test for place has a real vertical ship.
		battleship.placeShipAt(4, 3, false, ocean);
		Ship currentShip2 = ocean.getShipArray()[1][3];
		assertEquals(3,currentShip2.getBowColumn());
		
		//Test for place doesn't have a ship. Should return 0 by default, i.e. empty sea does not have a bow.
		Ship currentShip3 = ocean.getShipArray()[5][5];
		assertEquals(0,currentShip3.getBowColumn());
	}

	@Test
	void testGetHit() {
		// Test hit array before shoot.
		boolean[] hit0 = submarine.getHit();
		boolean[] a0 = {false, false, false, false};
		assertTrue(Arrays.equals(hit0, a0));
		// Test hit array after one shoot.
		ocean.shootAt(2,3);
		boolean[] hit1 = cruiser.getHit();
		boolean[] a1 = {true, false, false, false};
		assertTrue(Arrays.equals(hit1, a1));
		// Test hit array after repeat shoot.
		ocean.shootAt(2,3);
		boolean[] hit2 = cruiser.getHit();
		assertTrue(Arrays.equals(hit2, a1));
		// Test hit array after the ship is sunk.
		ocean.shootAt(2,2);
		ocean.shootAt(2,1);
		boolean[] hit3 = cruiser.getHit();
		boolean[] a3 = {true, true, true, false};
		assertTrue(Arrays.equals(hit3, a3));
		
	}

	@Test
	void testIsHorizontal() {
		// Place a ship horizontally.
		assertTrue(cruiser.isHorizontal());
		// Plase a ship vertically.
		submarine.placeShipAt(8, 8, false, ocean);
		assertFalse(submarine.isHorizontal());
		// Test a empty sea.
		assertFalse(emptysea.isHorizontal());
	}
	
	@Test
	void testGetShipType() {
		assertEquals("battleship", battleship.getShipType());
		assertEquals("cruiser", cruiser.getShipType());
		assertEquals("destroyer", destroyer.getShipType());
		assertEquals("submarine", submarine.getShipType());
		assertEquals("empty", emptysea.getShipType());
		
	}
	@Test
	void testSetBowRow() {
		
		destroyer.setBowRow(5);
		assertEquals(5, destroyer.getBowRow());
	}
		
	@Test
	void testSetBowColumn() {
		
		destroyer.setBowColumn(5);
		assertEquals(5, destroyer.getBowColumn());
	}

	@Test
	void testSetHorizontal() {
		submarine.setHorizontal(true);
		assertTrue(submarine.isHorizontal());
		destroyer.setHorizontal(false);
		assertFalse(destroyer.isHorizontal());
		
	}

	@Test
	void testOkToPlaceShipAt() {
		// Try to place a ship on a existing ship.
		assertFalse(battleship.okToPlaceShipAt(2, 3, true, ocean));
		assertFalse(destroyer.okToPlaceShipAt(2, 3, true, ocean));
		assertFalse(submarine.okToPlaceShipAt(2, 3, true, ocean));
		
		// Should be false if the ship stick out.
		assertFalse(battleship.okToPlaceShipAt(1, 1, true, ocean));
		
		// Should be false if the ship has a horizontal contact.
		assertFalse(battleship.okToPlaceShipAt(1, 3, true, ocean));
		assertFalse(destroyer.okToPlaceShipAt(3, 3, true, ocean));
		assertFalse(battleship.okToPlaceShipAt(3, 6, true, ocean));
		// Should be false if the ship has a vertical contact.
		assertFalse(battleship.okToPlaceShipAt(4, 0, false, ocean));
		assertFalse(destroyer.okToPlaceShipAt(2, 4, false, ocean));
		assertFalse(submarine.okToPlaceShipAt(2, 0, false, ocean));
		// Should be false if the ship has a diagonal contact.
		assertFalse(submarine.okToPlaceShipAt(1, 4, true, ocean));
		assertFalse(submarine.okToPlaceShipAt(3, 4, false, ocean));
		assertFalse(submarine.okToPlaceShipAt(1, 0, false, ocean));
		assertFalse(submarine.okToPlaceShipAt(3, 0, false, ocean));
		// Test place far from 2,3 should be true.
		assertTrue(submarine.okToPlaceShipAt(4, 5, true, ocean));
		assertTrue(submarine.okToPlaceShipAt(0, 9, true, ocean));
		// Should be false if the place is out of field.
		assertFalse(submarine.okToPlaceShipAt(-1, -1, true, ocean));
		assertFalse(submarine.okToPlaceShipAt(-1, 10, false, ocean));
		assertFalse(battleship.okToPlaceShipAt(10, 12, true, ocean));
	}

	@Test
	void testPlaceShipAt() {
		
		// Check row of the bow.
		assertEquals(2, cruiser.getBowRow());
		
		// Check column of the bow.
		assertEquals(3, cruiser.getBowColumn());
		
		// Check horizontal of the bow.
		assertTrue(cruiser.isHorizontal());
		
		// Check the type of marked coordinate.
		Ship currentShip1 = ocean.getShipArray()[2][2];
		Ship currentShip2 = ocean.getShipArray()[2][1];
		String type1 = currentShip1.getShipType();
		String type2 = currentShip2.getShipType();
		assertEquals("cruiser", type1);
		assertEquals("cruiser", type2);
	}

	@Test
	void testShootAt() {
		
		// Shoot but not sunk.
		cruiser.shootAt(2,3);
		boolean[] hit1 = cruiser.getHit();
		assertTrue(hit1[0]);
		
		// Continue shoot until sunk.
		cruiser.shootAt(2,2);
		boolean[] hit2 = cruiser.getHit();
		assertTrue(hit2[1]);
		cruiser.shootAt(2,1);
		boolean[] hit3 = cruiser.getHit();
		assertTrue(hit3[2]);
		
		// Shoot at a coordinate that the ship doesn't occupy, should be false.
		assertFalse(cruiser.shootAt(2,4));
		
		// Test for a vertical submarine.
		submarine.placeShipAt(4, 5, false, ocean);
		assertTrue(submarine.shootAt(4, 5));
	}

	@Test
	void testIsSunk() {
		// Ship hasn't been shot.
		assertFalse(cruiser.isSunk());
		// Shoot at a place has a real ship, but not yet sunk.
		cruiser.shootAt(2,3);
		assertFalse(cruiser.isSunk());
		// Continue shoot until sunk.
		cruiser.shootAt(2,1);
		assertFalse(cruiser.isSunk());
		cruiser.shootAt(2,2);
		assertTrue(cruiser.isSunk());
		
		destroyer.placeShipAt(5, 5, false, ocean);
		// Shoot a real, float ship.
		assertTrue(destroyer.shootAt(5, 5));

		// Repeat, still true
		assertTrue(destroyer.shootAt(5, 5));
		// Shoot until sink.
		assertTrue(destroyer.shootAt(4, 5));
		assertTrue(destroyer.isSunk());
	}

	@Test
	void testToString() {
		// Haven't shot, of course haven't sunk, should print "x".
		assertEquals("x", cruiser.toString());
		
		// Shoot at a place has a real ship, but not yet sunk, should print"x".
		cruiser.shootAt(2,3);
		assertEquals("x", cruiser.toString());
	
		// Continue shoot until sunk, should print "s".
		cruiser.shootAt(2,2);
		cruiser.shootAt(2,1);
		assertEquals("s", cruiser.toString());
	}

}
