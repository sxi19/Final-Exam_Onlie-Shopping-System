package Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import Products.*;

class TestEproduct {
	private static Eproduct testObject;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		testObject = new Eproduct("Iphone15",1499,100,12,12.5F);
	}

	@Test
	void testGetWarrentyPeroid() {
		int result = testObject.getWarrentyPeroid();
		assertEquals(12,result);
	}

	@Test
	void testGetVoltage() {
		float result = testObject.getVoltage();
		assertEquals(12.5,result);
	}

	@Test
	void testReduceStockTrue() {
		boolean result = testObject.reduceStock(10);
		assertEquals(true,result);
	}

	@Test
	void testReduceStockFalse() {
		boolean result = testObject.reduceStock(1000);
		assertEquals(false,result);
	}
	
	@Test
	void testIsAvailavleTrue() {
		boolean result = testObject.isAvailavle();
		assertEquals(true,result);
	}

	@Test
	void testIsAvailavleFalse() {
		testObject.setStockQuantity(0);
		boolean result = testObject.isAvailavle();
		assertEquals(false,result);
	}

	
	@Test
	void testSetStockQuantityTrue() {
		testObject.setStockQuantity(10);
		int result = testObject.getStockQuantity();
		assertEquals(10,result);
	}
	
	@Test
	void testSetStockQuantityTrue2() {
		// check if the stock quantity is updated correctly after multiple updating
		testObject.setStockQuantity(10);
		testObject.setStockQuantity(-10);
		int result = testObject.getStockQuantity();
		assertEquals(-10,result);
		testObject.setStockQuantity(100);
	}

}
