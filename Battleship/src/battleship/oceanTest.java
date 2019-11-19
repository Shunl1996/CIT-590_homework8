package battleship;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

class OceanTest {
	
	
	@Test
	void testOcean() {
		Ocean ocean = new Ocean();
		assertEquals(10,ocean.getShipArray().length);
		assertEquals(10,ocean.getShipArray()[0].length);
		int totalLength=0;
		for(int i =0;i<ocean.getShipArray().length;i++) {
			int length = ocean.getShipArray()[i].length;
			totalLength+=length;
		}
		assertTrue(totalLength==100);
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				assertEquals("empty",ocean.getShipArray()[i][j].getShipType());
			}
		}
		assertEquals(0,ocean.getHitCount());
		assertEquals(0,ocean.getShotsFired());
		assertEquals(0,ocean.getShipsSunk());
		
	}

	@Test
	void testPlaceAllShipsRandomly() {
		System.out.println("When testing the \"testPlaceAllShipsRandomly\" method, locations of ships can be placed as followings.[row,column]");
		System.out.println();
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly();
		// test battleship
		ArrayList<Integer> rowBattleship = new ArrayList<Integer>();
		ArrayList<Integer> columnBattleship = new ArrayList<Integer>();
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(ocean.getShipArray()[i][j].getShipType()=="Battleship") {
					rowBattleship.add(i);
					columnBattleship.add(j);
				}	
			}
		}
		ArrayList<Integer>rowAndColumn = new ArrayList<Integer>(2);
		System.out.print("Battleship is placed at: ");
		for(int i=0;i<4;i++) {
			rowAndColumn.add(rowBattleship.get(i));
			rowAndColumn.add(columnBattleship.get(i));
			System.out.print(rowAndColumn);
			rowAndColumn.remove(0);
			rowAndColumn.remove(0);
		}
		System.out.println();
		// test battleship if battleship is horizontal
		if(rowBattleship.get(0)==rowBattleship.get(1)) {
			Collections.sort(columnBattleship);
			for(int i=columnBattleship.get(0);i<columnBattleship.get(0)+ocean.getShipArray()[rowBattleship.get(0)][columnBattleship.get(0)].getLength();i++) {
				assertEquals("Battleship",ocean.getShipArray()[rowBattleship.get(0)][i].getShipType());
			}
			for(int i=rowBattleship.get(0)-1;i<=rowBattleship.get(0)+1;i++) {
				if(i>=0&&i<=9&&columnBattleship.get(3)<9&&columnBattleship.get(0)>0) {
					assertEquals("empty",ocean.getShipArray()[i][columnBattleship.get(3)+1].getShipType());
					assertEquals("empty",ocean.getShipArray()[i][columnBattleship.get(0)-1].getShipType());
				}
				if(i!=rowBattleship.get(0)) {
					if(i>=0&&i<=9) {
						for(int j=columnBattleship.get(0);j<=columnBattleship.get(3);j++) {
							assertEquals("empty",ocean.getShipArray()[i][j].getShipType());
						}
					}	
				}
			}	
		}
		// test battleship if battleship is vertical
		if(columnBattleship.get(0)==columnBattleship.get(1)) {
			Collections.sort(rowBattleship);
			for(int i=rowBattleship.get(0);i<rowBattleship.get(0)+ocean.getShipArray()[rowBattleship.get(0)][columnBattleship.get(0)].getLength();i++) {
				assertEquals("Battleship",ocean.getShipArray()[i][columnBattleship.get(0)].getShipType());
			}
			for(int i=columnBattleship.get(0)-1;i<=columnBattleship.get(0)+1;i++) {
				if(i>=0&&i<=9&&rowBattleship.get(3)<9&&rowBattleship.get(0)>0) {
					assertEquals("empty",ocean.getShipArray()[rowBattleship.get(3)+1][i].getShipType());
					assertEquals("empty",ocean.getShipArray()[rowBattleship.get(0)-1][i].getShipType());
				}
				if(i!=columnBattleship.get(0)) {
					if(i>=0&&i<=9) {
						for(int j=rowBattleship.get(0);j<=rowBattleship.get(3);j++) {
							assertEquals("empty",ocean.getShipArray()[j][i].getShipType());
						}
					}	
				}
			}		
		}
		// test Cruiser
		ArrayList<Integer> rowCruiser=null;
		ArrayList<Integer> columnCruiser=null;
		for(int times=0;times<2;times++) {
			int countCruiser=0;
			for(int i=0;i<10;i++) {
				for(int j=0;j<10;j++) {
					if(ocean.getShipArray()[i][j].getShipType()=="Cruiser") {
						countCruiser++;
						if(countCruiser==1) {
							if(j!=9&&ocean.getShipArray()[i][j+1].getShipType()=="Cruiser") {
								//horizontal
								rowCruiser = new ArrayList<Integer>(Arrays.asList(i,i,i));
								columnCruiser = new ArrayList<Integer>(Arrays.asList(j,j+1,j+2));
								ocean.getShipArray()[i][j]=new EmptySea();
								ocean.getShipArray()[i][j+1]=new EmptySea();
								ocean.getShipArray()[i][j+2]=new EmptySea();	
							}
							if(i!=9&&ocean.getShipArray()[i+1][j].getShipType()=="Cruiser") {
								//vertical
								rowCruiser = new ArrayList<Integer>(Arrays.asList(i,i+1,i+2));
								columnCruiser = new ArrayList<Integer>(Arrays.asList(j,j,j));
								ocean.getShipArray()[i][j]=new EmptySea();
								ocean.getShipArray()[i+1][j]=new EmptySea();
								ocean.getShipArray()[i+2][j]=new EmptySea();	
							}
						}
					}	
				}
			}
			ArrayList<Integer>rowAndColumn1 = new ArrayList<Integer>(2);
			System.out.print("Cruiser is placed at: ");
			for(int i=0;i<3;i++) {
				rowAndColumn1.add(rowCruiser.get(i));
				rowAndColumn1.add(columnCruiser.get(i));
				System.out.print(rowAndColumn1);
				rowAndColumn1.remove(0);
				rowAndColumn1.remove(0);
			}
			System.out.println();
			// test cruiser if cruiser is horizontal
			if(rowCruiser.get(0)==rowCruiser.get(1)) {
				Collections.sort(columnCruiser);
				for(int i=rowCruiser.get(0)-1;i<=rowCruiser.get(0)+1;i++) {
					if(i>=0&&i<=9&&columnCruiser.get(2)<9&&columnCruiser.get(0)>0) {
						assertEquals("empty",ocean.getShipArray()[i][columnCruiser.get(2)+1].getShipType());
						assertEquals("empty",ocean.getShipArray()[i][columnCruiser.get(0)-1].getShipType());
					}
					if(i!=rowCruiser.get(0)) {
						if(i>=0&&i<=9) {
							for(int j=columnCruiser.get(0);j<=columnCruiser.get(2);j++) {
								assertEquals("empty",ocean.getShipArray()[i][j].getShipType());
							}
						}	
					}
				}	
			}
			// test cruiser if cruiser is vertical
			if(columnCruiser.get(0)==columnCruiser.get(1)) {
				Collections.sort(rowCruiser);
				for(int i=columnCruiser.get(0)-1;i<=columnCruiser.get(0)+1;i++) {
					if(i>=0&&i<=9&&rowCruiser.get(2)<9&&rowCruiser.get(0)>0) {
						assertEquals("empty",ocean.getShipArray()[rowCruiser.get(2)+1][i].getShipType());
						assertEquals("empty",ocean.getShipArray()[rowCruiser.get(0)-1][i].getShipType());
					}
					if(i!=columnCruiser.get(0)) {
						if(i>=0&&i<=9) {
							for(int j=rowCruiser.get(0);j<=rowCruiser.get(2);j++) {
								assertEquals("empty",ocean.getShipArray()[j][i].getShipType());
							}
						}	
					}
				}		
			}
		}
		// test Destroyer
		ArrayList<Integer> rowDestroyer=null;
		ArrayList<Integer> columnDestroyer=null;
		for(int times=0;times<3;times++) {
			int countDestroyer=0;
			for(int i=0;i<10;i++) {
				for(int j=0;j<10;j++) {
					if(ocean.getShipArray()[i][j].getShipType()=="Destroyer") {
						countDestroyer++;
						if(countDestroyer==1) {
							if(j!=9&&ocean.getShipArray()[i][j+1].getShipType()=="Destroyer") {
								//horizontal
								rowDestroyer = new ArrayList<Integer>(Arrays.asList(i,i));
								columnDestroyer = new ArrayList<Integer>(Arrays.asList(j,j+1));
								ocean.getShipArray()[i][j]=new EmptySea();
								ocean.getShipArray()[i][j+1]=new EmptySea();	
							}
							if(i!=9&&ocean.getShipArray()[i+1][j].getShipType()=="Destroyer") {
								//vertical
								rowDestroyer = new ArrayList<Integer>(Arrays.asList(i,i+1));
								columnDestroyer = new ArrayList<Integer>(Arrays.asList(j,j));
								ocean.getShipArray()[i][j]=new EmptySea();
								ocean.getShipArray()[i+1][j]=new EmptySea();	
							}
						}
					}	
				}
			}
			ArrayList<Integer>rowAndColumn2 = new ArrayList<Integer>(2);
			System.out.print("Destroyer is placed at: ");
			for(int i=0;i<2;i++) {
				rowAndColumn2.add(rowDestroyer.get(i));
				rowAndColumn2.add(columnDestroyer.get(i));
				System.out.print(rowAndColumn2);
				rowAndColumn2.remove(0);
				rowAndColumn2.remove(0);
			}
			System.out.println();
			// test destroyer if destroyer is horizontal
			if(rowDestroyer.get(0)==rowDestroyer.get(1)) {
				Collections.sort(columnDestroyer);
				for(int i=rowDestroyer.get(0)-1;i<=rowDestroyer.get(0)+1;i++) {
					if(i>=0&&i<=9&&columnDestroyer.get(1)<9&&columnDestroyer.get(0)>0) {
						assertEquals("empty",ocean.getShipArray()[i][columnDestroyer.get(1)+1].getShipType());
						assertEquals("empty",ocean.getShipArray()[i][columnDestroyer.get(0)-1].getShipType());
					}
					if(i!=rowDestroyer.get(0)) {
						if(i>=0&&i<=9) {
							for(int j=columnDestroyer.get(0);j<=columnDestroyer.get(1);j++) {
								assertEquals("empty",ocean.getShipArray()[i][j].getShipType());
							}
						}	
					}
				}	
			}
			// test destroyer if destroyer is vertical
			if(columnDestroyer.get(0)==columnDestroyer.get(1)) {
				Collections.sort(rowDestroyer);
				for(int i=columnDestroyer.get(0)-1;i<=columnDestroyer.get(0)+1;i++) {
					if(i>=0&&i<=9&&rowDestroyer.get(1)<9&&rowDestroyer.get(0)>0) {
						assertEquals("empty",ocean.getShipArray()[rowDestroyer.get(1)+1][i].getShipType());
						assertEquals("empty",ocean.getShipArray()[rowDestroyer.get(0)-1][i].getShipType());
					}
					if(i!=columnDestroyer.get(0)) {
						if(i>=0&&i<=9) {
							for(int j=rowDestroyer.get(0);j<=rowDestroyer.get(1);j++) {
								assertEquals("empty",ocean.getShipArray()[j][i].getShipType());
							}
						}	
					}
				}		
			}
		}
		//test Submarine
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(ocean.getShipArray()[i][j].getShipType()=="Submarine") {
					ArrayList<Integer>rowAndColumn3 = new ArrayList<Integer>(2);
					System.out.print("Submarine is placed at: ");
					rowAndColumn3.add(i);
					rowAndColumn3.add(j);
					System.out.print(rowAndColumn3);
					rowAndColumn3.remove(0);
					rowAndColumn3.remove(0);
					System.out.println();
					for(int rowSubmarine=i-1;rowSubmarine<=i+1;rowSubmarine++) {
						for(int columnSubmarine=j-1;columnSubmarine<=j+1;columnSubmarine++) {
							if(rowSubmarine>=0&&rowSubmarine<=9&&columnSubmarine>=0&&columnSubmarine<=9) {
								if(rowSubmarine!=i&&columnSubmarine!=j) {
									assertEquals("empty",ocean.getShipArray()[rowSubmarine][columnSubmarine].getShipType());
								}
							}
						}
					}
				}
			}
		}
	}

	@Test
	void testIsOccupied() {
		Ocean ocean = new Ocean();
		if(ocean.getShipArray()[2][5].getShipType()!="empty") {
			assertEquals(true,ocean.isOccupied(2, 5));
		}else {
			assertEquals(false,ocean.isOccupied(2, 5));
		}
		if(ocean.getShipArray()[1][4].getShipType()!="empty") {
			assertEquals(true,ocean.isOccupied(1, 4));
		}else {
			assertEquals(false,ocean.isOccupied(1, 4));
		}
		if(ocean.getShipArray()[7][9].getShipType()!="empty") {
			assertEquals(true,ocean.isOccupied(7, 9));
		}else {
			assertEquals(false,ocean.isOccupied(7, 9));
		}
		if(ocean.getShipArray()[0][8].getShipType()!="empty") {
			assertEquals(true,ocean.isOccupied(0, 8));
		}else {
			assertEquals(false,ocean.isOccupied(0, 8));
		}
	}

	@Test
	void testGetShotsFired() {
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly();
		assertEquals(0,ocean.getShotsFired());
		ocean.shootAt(2, 3);
		assertEquals(1,ocean.getShotsFired());
		ocean.shootAt(4, 5);
		assertEquals(2,ocean.getShotsFired());
		for(int i=0; i<2;i++) {
			for(int j=5;j<8;j++) {
				ocean.shootAt(i, j);
			}
		}
		assertEquals(8,ocean.getShotsFired());
		ocean.shootAt(9,9);
		assertEquals(9,ocean.getShotsFired());
		ocean.shootAt(9,9);
		assertNotEquals(9,ocean.getShotsFired());
		assertEquals(10,ocean.getShotsFired());
		
	}

	@Test
	void testGetHitCount() {
		Ocean ocean1= new Ocean();
		ocean1.placeAllShipsRandomly();
		if(ocean1.getShipArray()[2][5].getShipType()=="Submarine") {
			ocean1.shootAt(2,5);
			assertEquals(1,ocean1.getHitCount());
			ocean1.shootAt(2,5);
			assertEquals(1,ocean1.getHitCount());
		}
		
		Ocean ocean2 = new Ocean();
		ocean2.placeAllShipsRandomly();
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				ocean2.shootAt(i, j);
			}
		}
		assertEquals(20,ocean2.getHitCount());
		Ocean ocean3 = new Ocean();
		ArrayList<Integer>rowBattleship = new ArrayList<Integer>();
		ArrayList<Integer>columnBattleship = new ArrayList<Integer>();
		ocean3.placeAllShipsRandomly();
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(ocean3.getShipArray()[i][j].getShipType()=="Battleship") {
					rowBattleship.add(i);
					columnBattleship.add(j);	
				}
			}
		}
		ocean3.shootAt(rowBattleship.get(0), columnBattleship.get(0));
		assertEquals(1,ocean3.getHitCount());
		ocean3.shootAt(rowBattleship.get(0), columnBattleship.get(0));
		assertEquals(2,ocean3.getHitCount());
		ocean3.shootAt(rowBattleship.get(1), columnBattleship.get(1));
		ocean3.shootAt(rowBattleship.get(2), columnBattleship.get(2));
		ocean3.shootAt(rowBattleship.get(3), columnBattleship.get(3));
		assertEquals(5,ocean3.getHitCount());
		ocean3.shootAt(rowBattleship.get(0), columnBattleship.get(0));
		assertEquals(5,ocean3.getHitCount());
	}

	@Test
	void testGetShipsSunk() {
		Ocean ocean1 = new Ocean();
		ocean1.placeAllShipsRandomly();
		assertEquals(0,ocean1.getShipsSunk());
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(ocean1.getShipArray()[i][j].getShipType()=="Battleship") {
					ocean1.shootAt(i, j);
				}
			}
		}
		assertEquals(1,ocean1.getShipsSunk());
		Ocean ocean2 = new Ocean();
		ocean2.placeAllShipsRandomly();
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				ocean2.shootAt(i, j);
			}
		}
		assertEquals(10,ocean2.getShipsSunk());
		Ocean ocean3 = new Ocean();
		ocean3.placeAllShipsRandomly();
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(ocean3.getShipArray()[i][j].getShipType()=="Destroyer"||ocean3.getShipArray()[i][j].getShipType()=="Cruiser") ocean3.shootAt(i, j);
			}
		}
		assertEquals(5,ocean3.getShipsSunk());
	}

	@Test
	void testIsGameOver() {
		Ocean ocean1 = new Ocean();
		ocean1.placeAllShipsRandomly();
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				ocean1.shootAt(i, j);
			}
		}
		assertEquals(10,ocean1.getShipsSunk());
		assertEquals(true,ocean1.isGameOver());
		
		Ocean ocean2 = new Ocean();
		ocean2.placeAllShipsRandomly();
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(ocean2.getShipArray()[i][j].getShipType()=="Submarine") ocean2.shootAt(i, j);
			}
		}
		assertEquals(4,ocean2.getShipsSunk());
		assertEquals(false,ocean2.isGameOver());
		
		Ocean ocean3 = new Ocean();
		ocean3.placeAllShipsRandomly();
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(ocean3.getShipArray()[i][j].getShipType()!="Battleship") ocean3.shootAt(i, j);
			}
		}
		assertEquals(9,ocean3.getShipsSunk());
		assertEquals(false,ocean3.isGameOver());
	}

	@Test
	void testShootAt() {
		Ocean ocean1 = new Ocean();
		ocean1.placeAllShipsRandomly();
		boolean testShootAt1=false;
		int count1 =0;
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(ocean1.getShipArray()[i][j].getShipType()=="Battleship") {
					testShootAt1 = ocean1.shootAt(i, j);
					count1++;
					break;
				}
			}
			if(count1==1) break;	
		}
		assertEquals(true,testShootAt1);
		assertEquals(1,ocean1.getHitCount());
		assertEquals(1,ocean1.getShotsFired());
		
		Ocean ocean2 = new Ocean();
		ocean2.placeAllShipsRandomly();
		boolean testShootAt2=false;
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(ocean2.getShipArray()[i][j].getShipType()=="Destroyer") {
					testShootAt2 = ocean2.shootAt(i, j);
				}
			}
		}
		assertEquals(6,ocean2.getShotsFired());
		assertEquals(6,ocean2.getHitCount());
		testShootAt2=true;
		int count2=0;
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(ocean2.getShipArray()[i][j].getShipType()=="Destroyer") {
					testShootAt2 = ocean2.shootAt(i, j);
					count2++;
					break;
				}
			}if(count2==1) break;
		}
		assertEquals(false,testShootAt2);
		assertEquals(7,ocean2.getShotsFired());
		assertNotEquals(7,ocean2.getHitCount());
		assertEquals(6,ocean2.getHitCount());
		
		Ocean ocean3 = new Ocean();
		ocean3.placeAllShipsRandomly();
		boolean testShootAt3=false;
		int count3=0;
		int i=0;
		int j=0;
		for(i=0;i<10;i++) {
			for(j=0;j<10;j++) {
				if(ocean3.getShipArray()[i][j].getShipType()=="Cruiser") {
					testShootAt3=ocean3.shootAt(i, j);
					count3++;
					break;
				}
			}if(count3==1) break;
		}
		testShootAt3=ocean3.shootAt(i, j);
		testShootAt3=ocean3.shootAt(i, j);
		assertEquals(true,testShootAt3);
		assertEquals(3,ocean3.getHitCount());		
	}

	@Test
	void testGetShipArray() {
		Ocean ocean =new Ocean();
		ocean.placeAllShipsRandomly();
		assertEquals(10,ocean.getShipArray().length);
		for(int i=0;i<10;i++) {
			assertEquals(10,ocean.getShipArray()[i].length);
		}
		assertThrows(Exception.class,()->{
			ocean.getShipArray()[1][11].getLength();
		});
		assertDoesNotThrow(()->{
			ocean.getShipArray()[1][2].getLength();
		});
		int countCruiser=0;
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(ocean.getShipArray()[i][j].getShipType()=="Cruiser") {
					ocean.shootAt(i, j);
					if(ocean.getShipArray()[i][j].isSunk()) countCruiser++;
				}
			}
		}
		assertEquals(2,countCruiser);
			
	}
}
