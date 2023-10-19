package UserManagement;
import java.util.HashMap;
import Products.*;
import java.time.LocalDate;
import java.math.*;

public class Order {
	/**
	 * Order information is processed and to generate invoice in this class
	 * HashMap customer is a container for customer information (key:"name","email","userID" value:name,email,userID) 
	 * HashMap myProuctList is a container for product information (key:product, value:quantity) 
	 */
	private int orderID;
	private HashMap<String,String> customer;
	private HashMap<Product,Integer> myProductList;
	private LocalDate orderDate;
	private float totalAmount;
	
	public Order(HashMap<String,String> customer, HashMap<Product, Integer> myProductList) {
		this.orderID = this.generateOrderID();
		this.customer = customer;
		this.orderDate = LocalDate.now();
		this.myProductList = myProductList;
		this.totalAmount = 0;
	}
	
	// generate order ID with random number
	private int generateOrderID() {
		int min = 1000;
		int max = 9999;
		return java.util.concurrent.ThreadLocalRandom.current().nextInt(max - min + 1) + min;
	}
	
	public void generateInvoice() {
		if (myProductList.isEmpty()== false) {
			// sumPrice = quantity * single price
			float sumPrice = 0;
			this.totalAmount = 0;
			System.out.println();
			System.out.println("****************************** INVOICE *******************************");
			System.out.println("----------------------------------------------------------------------");
			System.out.println("Customer:\t" + this.customer.get("name"));
			System.out.println("User ID:\t" + this.customer.get("UserID"));
			System.out.println("E-mail:\t\t" + this.customer.get("email"));
			System.out.println("----------------------------------------------------------------------");
			System.out.println("						Date: " + this.orderDate);
			System.out.println("Order-Nr.: " + this.orderID);
			System.out.println("----------------------------------------------------------------------");
			System.out.println("Artl.-Nr.\tName\t\t\tQuantity\tEUR");
			for(Product k : myProductList.keySet()) {
				sumPrice = Math.round(k.getPrice()*myProductList.get(k)*100)/100.0F;
				this.totalAmount += sumPrice;
				System.out.println(k.getProductID() + "\t\t" + k.getProductName() + "\t\t" + myProductList.get(k) + "\t\t" + sumPrice);
			}
			System.out.println("----------------------------------------------------------------------");
			System.out.println("					TOTAL DUE:	" + this.totalAmount + " EUR");
			System.out.println("----------------------------------------------------------------------");
			System.out.println("**********************************************************************");
			System.out.println();
		}else {
			System.out.println("Order does not exist!");
		}
	}
	
	public float getTotalAmount() {
		return this.totalAmount;
	}
	
}
