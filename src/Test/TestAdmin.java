package Test;
import Products.*;
import UserManagement.*;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestAdmin {
	private static Eproduct testEproduct;
	private static ClothingProduct testClothing;
	private static Admin testAdmin;
	private static Customer testCustomer;
	
	@BeforeAll
	// create test objects and initialization with all information
	static void setUpBeforeClass() throws Exception {
		testAdmin = new Admin("Xin Sun","sxi19@tu-clausthal.de");
		testCustomer = new Customer("Mr.Magpie","magpie@bird.de");
		testEproduct = new Eproduct("Iphone15",1000,100,12,12.5F);
		testClothing = new ClothingProduct("PUMA Tshirt",10F,50,3,"XL","Cotton");
	}

	@Test
	// test add product via admin user and input all product info manually
	void testAddProduct() {
		boolean result1 = false;
		boolean result2 = false;
		Eproduct myEproduct = (Eproduct)testAdmin.addProduct("eproduct");
		ClothingProduct myClothing = (ClothingProduct)testAdmin.addProduct("clothing");
		
		if (myEproduct instanceof Eproduct) {
			result1 = true;
		}
		if (myClothing instanceof ClothingProduct) {
			result2 = true;
		}
		assertEquals(true,result1);
		assertEquals(true,result2);
		
	}

	@Test
	// test remove product via admin user
	void testRemoveProduct() {
		boolean result3 = false;
		boolean result4 = false;
		if (testAdmin.removeProduct(testEproduct)) {
			testEproduct = null;
			if (testEproduct == null) {
				//System.out.println("testProduct = null");
				result3 = true;
			}
		}
		if (testAdmin.removeProduct(testClothing)) {
			testClothing = null;
			if (testClothing == null) {
				result4 = true;
			}
		}
		
		assertEquals(true,result3);
		assertEquals(true,result4);
	}

	@Test
	// test update product stock under different input values and check the result persistence
	void testUpdateProductStock() {
		Eproduct testEproduct = new Eproduct("Iphone15",1000,100,12,12.5F);
		ClothingProduct testClothing = new ClothingProduct("PUMA Tshirt",10F,50,3,"XL","Cotton");
		testAdmin.updateProductStock(testEproduct, 0);
		int result4 = testEproduct.getStockQuantity();
		testAdmin.updateProductStock(testClothing, -10);
		int result5 = testClothing.getStockQuantity();
		testAdmin.updateProductStock(testClothing, 100);
		int result6 = testClothing.getStockQuantity();
		assertEquals(0,result4);
		assertEquals(-10,result5);
		assertEquals(100,result6);
		
	}

	@Test
	void testRemoveCustomer() {
		boolean result7 = false;
		if (this.testCustomer != null) {
			testAdmin.removeCustomer(testCustomer);
			testCustomer = null;
			if (testCustomer == null) {
				System.out.println("testCustomer = null");
				result7 = true;
			}
		}
		assertEquals(true,result7);
	}

	@Test
	void testCheckPassword() {
		boolean result8 = false;
		if(testAdmin.checkPassword() == true) {
			result8 = true;
		}
		assertEquals(true,result8);
	}

}
