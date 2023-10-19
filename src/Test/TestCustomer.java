package Test;

import static org.junit.jupiter.api.Assertions.*;
import Products.*;
import UserManagement.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.HashMap;

class TestCustomer {
	private static Customer testCustomer;
	private static Eproduct testEproduct;
	private static ClothingProduct testClothing;
	
	@BeforeAll
	// test add product via admin user and input all product info manually
	static void setUpBeforeClass() throws Exception {
		testCustomer = new Customer("Xin Sun","sxi19@tu-clausthal.de");
		testEproduct = new Eproduct("Iphone15",1000,100,12,12.5F);
		testClothing = new ClothingProduct("PUMA Tshirt",10F,50,3,"XL","Cotton");
	}

	@Test
	void testAddToCart() {
		/**
		 * Test addToCart function under invalid and valid quantity input
		 * Check if the product stock and quantity in product list are updated correctly
		 */
		testCustomer.addToCart(testEproduct, 10);
		int result1 = testEproduct.getStockQuantity();
		testCustomer.addToCart(testClothing, 20);
		int result2 = testClothing.getStockQuantity();
		testCustomer.addToCart(testEproduct, 900);
		int result3 = testEproduct.getStockQuantity();
		testCustomer.addToCart(testClothing, 2000);
		int result4 = testClothing.getStockQuantity();
		assertEquals(90,result1);
		assertEquals(30,result2);
		assertEquals(90,result3);
		assertEquals(30,result4);
		
		// add same product twice into product list, check if quantity is updated
		testCustomer.addToCart(testEproduct, 10);
		int result5 = testEproduct.getStockQuantity();
		testCustomer.addToCart(testClothing, 20);
		int result6 = testClothing.getStockQuantity();
		assertEquals(80,result5);
		assertEquals(10,result6);
		
		// check the quantity in product list after adding the same product twice
		HashMap<Product,Integer> testShoppingList = testCustomer.calculateTotalTest();
		int result7 = (int)testShoppingList.get(testEproduct);
		int result8 = (int)testShoppingList.get(testClothing);
		assertEquals(20,result7);
		assertEquals(40,result8);
		
		testCustomer.removeFromCart(testEproduct);
		testCustomer.removeFromCart(testClothing);
		
	}

	@Test
	void testRemoveFromCart() {
		/**
		 * Test removeFromCart method under two steps.
		 * First to remove the product from shopping cart and check the product stock
		 * Then to delete the product and make sure it can not be referenced in program
		 */
		
		// remove products from shopping cart and then check if the removed items is returned to stock
		testCustomer.addToCart(testEproduct, 5);
		testCustomer.addToCart(testClothing, 10);
		testCustomer.removeFromCart(testEproduct);
		testCustomer.removeFromCart(testClothing);
		int result1 = testEproduct.getStockQuantity();
		int result2 = testClothing.getStockQuantity();
		assertEquals(100,result1);
		assertEquals(50,result2);
		
		// check if products are removed from product list correctly
		HashMap<Product,Integer> testShoppingList = testCustomer.calculateTotalTest();
		boolean result3 = false;
		boolean result4 = false;
		if (testShoppingList.get(testEproduct)==null) {
			// perform the removing after checking admin right
			result3 = true;
		};
		if (testShoppingList.get(testClothing)==null) {
			result4 = true;
		};
		assertEquals(true,result3);
		assertEquals(true,result4);
		
	}

	@Test
	void testCalculateTotal() {
		// check if the product and quantity append to product list correctly 
		HashMap<Product,Integer> testShoppingList01 = testCustomer.calculateTotalTest();
		testCustomer.addToCart(testEproduct, 10);
		testCustomer.addToCart(testClothing, 20);
		HashMap<Product,Integer> testShoppingList = testCustomer.calculateTotalTest();
		int resultEproduct = testShoppingList.get(testEproduct);
		int resultClothing = testShoppingList.get(testClothing);
		assertEquals(10,resultEproduct);
		assertEquals(20,resultClothing);
		testCustomer.calculateTotal();	
	}
	
	@Test
	void testPlaceOrder() {
		testCustomer.placeOrder();
		HashMap<Product,Integer> testShoppingList = testCustomer.calculateTotalTest();
		boolean result1 = false;
		boolean result2 = false;
		
		// check if after place order the shopping cart is cleared
		if (testShoppingList.get(testEproduct)==null) {
			result1 = true;
		};
		if (testShoppingList.get(testClothing)==null) {
			result2 = true;
		};
		assertEquals(true,result1);
		assertEquals(true,result2);
	}

}










