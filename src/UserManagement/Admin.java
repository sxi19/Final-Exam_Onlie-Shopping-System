package UserManagement;
import Products.*;
import java.util.Scanner;

public class Admin extends User{
	private int password;
	
	public Admin(String name, String email) {
		super(name, email);
		this.password = 121212;
	}
	
	public Product addProduct(String productType) {
		/**
		 * this function create a new product according to the input product type
		 * if function parameter is "eproduct", a new Eproduct will be instantiated. The same to parameter "clothing"
		 */
		Product newProduct = null;
		Scanner in = new Scanner(System.in);
		if (this.checkPassword()) {
			// create new Product
			if(productType.equals("eproduct")) {
				/**
				 * here all necessary attributes of Eproduct will be input manually
				 * after instantiation an Eproduct Object will be returned
				 */
				System.out.println("Please input: Product Name, Price, Stock Quanity, warrenty Period, Voltage:");
				String name = in.next();
				float price = in.nextFloat();
				int stockQuantity = in.nextInt();
				int warrentyPeriod = in.nextInt();
				float voltage = in.nextFloat();
				return new Eproduct(name, price, stockQuantity, warrentyPeriod, voltage);
			}
			if (productType.equals("clothing")) {
				/**
				 * here all necessary attributes of ClothingProduct will be input manually
				 * after instantiation an ClothingProduct Object will be returned
				 */
				System.out.println("Please input: Product Name, Price, Stock Quanity, warrenty Period, Size, Material:");
				String name = in.next();
				float price = in.nextFloat();
				int stockQuantity = in.nextInt();
				int warrentyPeriod = in.nextInt();
				String size = in.next();
				String material = in.next();
				return new ClothingProduct(name, price, stockQuantity, warrentyPeriod, size, material);
			}
		}
		return newProduct;
	}
	
	public boolean removeProduct(Product product) {
		/**
		 * removing product is only with correct password possible
		 * check password is realized in method checkPassword()
		 */
		boolean ret = false;
		if (product != null) {
			System.out.println("Request to remove " + product.getProductName());
			if (this.checkPassword()) {
				System.out.printf(product.getProductName() + " is deleted from stock!\n");
				product = null;
				ret = true;
			}
		}
		return ret;
	}
	
	public void updateProductStock(Product product, int quantity) {
		if (product != null) {
			if (this.checkPassword()) {
				product.setStockQuantity(quantity);
				System.out.printf("Update stock quantity successful!");
			}
		}
	}
	
	public boolean removeCustomer(Customer customer) {
		boolean ret = false;
		if (customer != null) {
			if (this.checkPassword()) {
				System.out.printf("Customer " + customer.getName() + " is deleted!");
				customer = null;
				ret = true;
			}
		}
		return ret;
	}
	
	public boolean checkPassword() {
		// return true if password is correct and vice versa
		boolean ret = false;
		Scanner in = new Scanner(System.in);
		System.out.printf("Please input password: ");
		int password = in.nextInt();
		if(password == this.password) {
			ret = true;
		}else {
			System.out.printf("Invalid password!");
		}
		return ret;
	}
	
}
