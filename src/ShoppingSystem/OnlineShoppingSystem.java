/**
 * Date: 19.20.2023
 * Developer: Xin Sun
 * Description: this is a simulation class for a online shopping system
 */
package ShoppingSystem;
import Products.*;
import UserManagement.*;
import java.util.Scanner;
import java.util.ArrayList;

public class OnlineShoppingSystem {
	/**
	 * These four ArrayList are defined to storage products, customer and admin objects
	 */
	private ArrayList<Eproduct> EproductList = new ArrayList<Eproduct>();
	private ArrayList<ClothingProduct> clothingProductList = new ArrayList<ClothingProduct>();
	private ArrayList<Customer> customerList = new ArrayList<Customer>();
	private ArrayList<Admin> adminList = new ArrayList<Admin>();
	
	
	public void addCustomer(Customer customer) {
		// append existed Customer object into customer list
		if (customer !=null) {
		// add customer to customer list
			this.customerList.add(customer);
		}
	}
	
	public void removeCustomer(Customer customer, Admin admin) {
		if (customer != null && this.customerList.contains(customer)) {
			// remove customer with admin right
			if (admin.removeCustomer(customer)) {
				// remove customer from customer list
				this.customerList.remove(customer);
				System.out.println("Remove successfully");
			}
		}
	}
	
	public void addAdmin (Admin admin) {
		// append new admin into Admin list
		if (admin != null) {
			this.adminList.add(admin);
		}
	}
	
	public void addProduct(Admin admin) {
		/**
		 * A new product which authorized by Admin is created and added to product list
		 * The product type will be imported as String
		 * Product information will be added manually, then it will be appended to product list
		 */
		if (admin != null) {
			if (admin.checkPassword()) {
				Scanner in = new Scanner(System.in);
				System.out.println("Which product need to add?(eproduct/clothing)");
				String productType = in.next();
				if (productType.equals("eproduct")) {
					Eproduct newEproduct = (Eproduct)admin.addProduct(productType);
					this.EproductList.add(newEproduct);
				}
				if (productType.equals("clothing")) {
					ClothingProduct newClothing = (ClothingProduct)admin.addProduct(productType);
					this.clothingProductList.add(newClothing);
				}
				
			}
		}
	}
	
	public void addProduct(Product product) {
		/**
		 * Overloading the method addProduct(Admin admin)
		 * In this function a product which already existed will be added to product list
		 */
		if (product != null) {
			if (product instanceof Eproduct) {
				this.EproductList.add((Eproduct)product);
			}
			if (product instanceof ClothingProduct) {
				this.clothingProductList.add((ClothingProduct)product);
			}
		}
	}
	
	public void removeProduct(Product product, Admin admin) {
		/**
		 * an existed product will be removed from product list and then it will be deleted
		 * Deletion a product only with admin rights possible
		 */
		if (product != null) {
			if(product instanceof Eproduct && this.EproductList.contains(product)) {
				// delete product with admin right
				if (admin.removeProduct(product)) {
					// first remove product from product list
					this.EproductList.remove(product);
					System.out.println("Remove successfully");
				}
			}
			if(product instanceof ClothingProduct && this.clothingProductList.contains(product)) {
				if (admin.removeProduct(product)) {
					this.clothingProductList.remove(product);
					System.out.println("Remove successfully");
				}
			}
		}
	}
	
	public void showProductInfo() {
		// shows all product information in two product list
		System.out.println();
		if (this.clothingProductList.isEmpty() == false) {
			System.out.println("Clothing Product");
			for(ClothingProduct k:this.clothingProductList) {
				k.showProductInfo();
			}
			System.out.println();
		}
		if (this.EproductList.isEmpty() == false) {
			System.out.println("Electronics Product");
			for(Eproduct k:this.EproductList) {
				k.showProductInfo();
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		/**
		 * The whole online shopping system is simulated in main() method
		 * Admin and customer can be created and append to their corresponding lists
		 * Electronic- and clothing products can be created and add to their lists
		 * In simulation, a customer can add, remove products to the shopping cart and show all products in shopping cart. 
		 * An order can be generated and managed by customer, the information can be printed on console.
		 * An admin can create a new product via input information on console. Deletion a product is only possible with admin right
		 */
		// initialization OnlineShoppingSystem, create and append admin
		OnlineShoppingSystem shoppingSystem = new OnlineShoppingSystem();
		Admin admin01 = new Admin("admin01","admin01@shopping.com");
		shoppingSystem.addAdmin(admin01);
		
		// initialize product list, first create new product and then append it to product list
		Eproduct iphone15 = new Eproduct("Iphone15",899,100,12,12.5F);
		Eproduct iphone15Pro = new Eproduct("Iphone15Pro",1299,50,12,12.5F);
		ClothingProduct tShirt = new ClothingProduct("PUMA Tshirt",15.99F,200,3,"L","cotton");
		ClothingProduct jeans = new ClothingProduct("Levi's Jeans",69.99F,100,3,"XS","woolen");
		shoppingSystem.addProduct(jeans);
		shoppingSystem.addProduct(tShirt);
		shoppingSystem.addProduct(iphone15);
		shoppingSystem.addProduct(iphone15Pro);
		
		// initialize customer
		Customer customer01 = new Customer("customer01","customer01@shopping.com");
		shoppingSystem.addCustomer(customer01);
		
		// simulation 
		customer01.addToCart(jeans, 3);
		customer01.addToCart(iphone15Pro, 1);
		customer01.addToCart(iphone15, 2);
		customer01.addToCart(tShirt, 2);
		customer01.calculateTotal();
		customer01.removeFromCart(iphone15);
		customer01.calculateTotal();
		
		shoppingSystem.showProductInfo();
		shoppingSystem.removeProduct(iphone15Pro, admin01);
		shoppingSystem.showProductInfo();
		customer01.placeOrder();
		//customer01.showOrder();
	}

}
