package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import Products.*;
class TestClothingProduct {
	private static ClothingProduct testClothingProduct;
	
	@BeforeAll
	// test add product via admin user and input all product info manually
	static void setUpBeforeClass() throws Exception {
		testClothingProduct = new ClothingProduct("PUMA Tshirt",15.99F,200,3,"L","cotton");
	}

	@Test
	void testGetWarrentyPeroid() {
		boolean result1 = false;
		if(this.testClothingProduct.getWarrentyPeroid() == 3) {
			result1 = true;
		}
		assertEquals(true,result1);
	}

	@Test
	void testGetSize() {
		boolean result2 = false;
		if(this.testClothingProduct.getSize().equals("L")) {
			result2 = true;
		}
		assertEquals(true,result2);
	}

	@Test
	void testGetMaterial() {
		boolean result4 = false;
		if(this.testClothingProduct.getMaterial().equals("cotton")) {
			result4 = true;
		}
		assertEquals(true,result4);
	}

}
