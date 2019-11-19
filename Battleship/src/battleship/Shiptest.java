package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipTest {

	Ship emptySea, submarine, destroyer, battleship, cruiser;
	Ocean ocean;
	@BeforeEach
	void setUp() throws Exception {
		 emptySea = new EmptySea();
		 submarine = new Submarine();
		 destroyer = new Destroyer();
		 battleship = new Battleship();
		 cruiser = new Cruiser();
		 ocean = new Ocean();
		 emptySea.placeShipAt(0, 0, true, ocean);
		 submarine.placeShipAt(1, 0, true, ocean);
		 destroyer.placeShipAt(2, 1, true, ocean);
		 battleship.placeShipAt(3, 3, true, ocean);
		 cruiser.placeShipAt(4, 2, false, ocean);

		 destroyer.setHorizontal(true);
		 cruiser.setHorizontal(false);
		 submarine.setHorizontal(true);
		 battleship.setHorizontal(true);

	}


	@Test
	void testGetLength() {
		
		assertEquals(1, emptySea.getLength());
		assertEquals(1, submarine.getLength());
		assertEquals(2, destroyer.getLength());
		assertEquals(3, cruiser.getLength());
		assertEquals(4, battleship.getLength());
	}

	@Test
	void testGetBowRow() {
		assertEquals(0, emptySea.getBowRow()); 
		assertEquals(1, submarine.getBowRow());
		assertEquals(2, destroyer.getBowRow());
		assertEquals(3, battleship.getBowRow());
		assertEquals(4, cruiser.getBowRow());
	}

	@Test
	void testGetBowColumn() {
		assertEquals(0, emptySea.getBowColumn()); 
		assertEquals(0, submarine.getBowColumn());
		assertEquals(1, destroyer.getBowColumn());
		assertEquals(3, battleship.getBowColumn());
		assertEquals(2, cruiser.getBowColumn());
	}

	@Test
	void testGetHit() {
		boolean[] hit = {false,false,false,false};
		assertArrayEquals(hit, emptySea.getHit());
		hit[0] = true;
		assertNotEquals(hit[0], submarine.getHit()[0]); 
	}
	@Test
	void testGetHit2(){
		boolean[] hit = {true,false,false,false};
		battleship.shootAt(3, 3);
		assertArrayEquals(hit, battleship.getHit());
		battleship.shootAt(3, 1);
		boolean[] hit2 = {true,false,true,false};
		assertArrayEquals(hit2, battleship.getHit());
	}
	@Test
	void testIsHorizontal() {
		assertTrue(submarine.isHorizontal());
		assertTrue(battleship.isHorizontal());
		assertFalse(cruiser.isHorizontal());
	}

	@Test
	void testSetBowRow() {
		submarine.setBowRow(5);
		assertNotEquals(1,submarine.getBowRow());
		emptySea.setBowRow(78);
		assertEquals(78, emptySea.getBowRow());
		destroyer.setBowRow(6);
		assertEquals(6, destroyer.getBowRow());

	}

	@Test
	void testSetBowColumn() {
		cruiser.setBowColumn(5);
		assertNotEquals(1,submarine.getBowColumn());
		emptySea.setBowColumn(4);
		assertEquals(4, emptySea.getBowColumn());
		battleship.setBowColumn(6);
		assertEquals(6, battleship.getBowColumn());
	}

	@Test
	void testSetHorizontal() {
		destroyer.setHorizontal(false);
		assertFalse(destroyer.isHorizontal());
		cruiser.setHorizontal(true);
		assertTrue(cruiser.isHorizontal());
		battleship.setHorizontal(true);
		assertTrue(battleship.isHorizontal());
	}

	@Test
	void testGetShipType() {
		assertSame("empty", emptySea.getShipType());
		assertEquals("Destroyer", destroyer.getShipType());
		assertEquals("Cruiser", cruiser.getShipType());
		assertEquals("Submarine", submarine.getShipType());
		assertEquals("Battleship", battleship.getShipType());
	}


	@Test
	void testToString() {
		battleship.shootAt(3, 3);
		battleship.shootAt(3, 2);
		battleship.shootAt(3, 1);
		battleship.shootAt(3, 0);
		String bat =  battleship.toString();
		assertEquals("s ", bat);
		assertEquals("x ", cruiser.toString());
		assertEquals("- ", emptySea.toString());

	}

	@Test
	void testOkToPlaceShipAt(){

		assertFalse(destroyer.okToPlaceShipAt(2, 1, false, ocean));
		assertTrue(submarine.okToPlaceShipAt(8, 8, true, ocean));
		assertFalse(battleship.okToPlaceShipAt(0, 0, true, ocean));
	}

	@Test
	void testPlaceShipAt(){
		submarine.placeShipAt(5, 0, true, ocean);
		assertEquals(submarine.getClass(), ocean.getShipArray()[5][0].getClass()); 
		destroyer.placeShipAt(5, 1, false, ocean);
		assertEquals(destroyer.getClass(), ocean.getShipArray()[4][1].getClass());
		cruiser.placeShipAt(5, 2, false, ocean);
		assertEquals(cruiser.getClass(), ocean.getShipArray()[3][2].getClass());
		battleship.placeShipAt(5, 9, true, ocean);
		assertEquals(battleship.getClass(), ocean.getShipArray()[5][8].getClass());
	}

	@Test
	void testShootAt(){
		submarine.shootAt(1, 0);
		boolean[] array = {true,false,false,false};
		assertArrayEquals(array, submarine.getHit());
		destroyer.shootAt(2, 0);
		array[0] = false;
		array[1] = true;
		assertArrayEquals(array, destroyer.getHit());
		cruiser.shootAt(3, 2);
		cruiser.shootAt(4, 2);
		array[0] = true;
		assertArrayEquals(array, cruiser.getHit());
		battleship.shootAt(3, 3);
		battleship.shootAt(3, 2);
		battleship.shootAt(3, 1);
		battleship.shootAt(3, 0);
		array[2] = true;
		array[3] = true;
		assertArrayEquals(array, battleship.getHit());
	}

	@Test
	void testIsSunk(){
		battleship.shootAt(3, 3);
		battleship.shootAt(3, 2);
		battleship.shootAt(3, 1);
		battleship.shootAt(3, 0);
		assertTrue(battleship.isSunk());
		assertFalse(submarine.isSunk());
		destroyer.shootAt(2, 0);
		assertFalse(destroyer.isSunk());
		cruiser.shootAt(4, 2);
		assertFalse(cruiser.isSunk());
		emptySea.shootAt(0, 0);
		assertFalse(emptySea.isSunk());
	}

	
}
