package UserManagement;
import Products.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.math.*;

public class Customer extends User implements ShoppingCart {
	// Following HashMap is defined to storage product and its quantity. HashMap<Key,Value> --> <Product,Integer>
	private HashMap<Product,Integer> myProductList = new HashMap<Product,Integer>();

	// ArrayList myOrder manage all orders for a customer;
	private ArrayList<Order> myOrder = new ArrayList<Order>();
	private static int orderNum = 0;

	public Customer(String name, String email) {
		super(name, email);
	}
	
	@Override
	public void addToCart(Product product, int quantity) {
		// add product into myProductList
		if (quantity > 0 && product != null) {
			// update stock value via reduceStock(quantity)
			if (product.isAvailavle() && product.reduceStock(quantity)) {
				addToMyShoppingList(product, quantity);
			}
		}
	}

	private void addToMyShoppingList(Product product, int quantity) {
		/**
		 * This function is defined to add product information in product list
		 */
		if (myProductList.containsKey(product)) {
			// update the product quantity in list
			System.out.println("add " + product.getProductName() + " to shopping cart!");
			myProductList.put(product, myProductList.get(product) + (Integer)quantity);
			
		}
		else{
			// storage new product name and its quantities & price in list
			myProductList.put(product, (Integer)quantity);
			System.out.println("Add " + quantity +" pcs " + product.getProductName() + " to shopping cart!");
		}
	}

	@Override
	public void removeFromCart(Product product) {
		if (product != null && myProductList.containsKey(product)) {
			product.setStockQuantity(product.getStockQuantity() + (int)myProductList.get(product));
			myProductList.remove(product);
			System.out.println(product.getProductName() + " is removed from shopping cart!");
		}else {
			System.out.println( "Unable to remove product! "+ product.getProductName() + " not in shopping list!");
		}
	}

	
	@Override
	public void calculateTotal() {
		float sumPrice = 0;
		float totalPrice = 0;
		int quantity = 0;
		if (myProductList.isEmpty()== false) {
			System.out.println();
			System.out.println("---------------------------- SHOPPING LIST ---------------------------");
			for(Product k : myProductList.keySet()) {
				sumPrice = Math.round(k.getPrice()*myProductList.get(k)*100)/100.0F;
				// print the quantity and product name
				System.out.println(myProductList.get(k) + "\t" + "x\t" + k.getProductName() + 
						"\t\t Unit Price:\t" + k.getPrice() + "\t" + "EUR");
				totalPrice += sumPrice;
			}
			System.out.println("----------------------------------------------------------------------");
			System.out.println("						SUM:\t" + totalPrice + "\tEUR");
			System.out.println("----------------------------------------------------------------------");
			System.out.println();
		}
		else {
			System.out.println("No Product in Shopping Cart");
		}
	}
	
	// this method is only for JUnit test case
	public HashMap<Product,Integer> calculateTotalTest() {
		return this.myProductList;
	}
	
	public void placeOrder() {
		/**
		 * create a new HashMap customerInfo to storage customer email, name and User ID
		 * then generate a new Order object
		 */
		HashMap<String,String> customerInfo = new HashMap<String, String>();
		customerInfo.put("name",this.getName());
		customerInfo.put("email", this.getEmail());
		Integer usID = this.getUserID();
		customerInfo.put("UserID", usID.toString());
		
		// copy the current shopping list to a new HashMap by using constructor 
		HashMap<Product,Integer> myProductListOld = new HashMap<Product,Integer>(this.myProductList);
		
		// create a new Order object and append it in myOrder list
		Order myOrder = new Order(customerInfo, myProductListOld);
		this.myOrder.add(myOrder);
		
		// print invoice information for the current shopping
		myOrder.generateInvoice();
		this.orderNum ++;
		
		//clear current shopping cart, remove all products from myProductList
		this.myProductList.clear();
		
	}
	
	//print all invoices for a customer
	public void showOrder() {
		if (this.myOrder.isEmpty() == false) {
			System.out.println("Print history invoice......");
			for(Order k:this.myOrder) {
				k.generateInvoice();
				System.out.println();
			}
		}else {
			System.out.println("Order not exist");
		}
	}
}
