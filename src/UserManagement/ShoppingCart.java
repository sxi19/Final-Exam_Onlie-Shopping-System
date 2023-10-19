package UserManagement;
import Products.*;

interface ShoppingCart {
	/**
	 * Methods of shopping cart are defined in this interface, which is implemented by Customer Class
	 * @param product
	 * @param quantity
	 */
	
	// add product into shopping cart 
	void addToCart(Product product, int quantity);
	
	// remove product from shopping cart
	void removeFromCart(Product product);
	
	// list all products in shopping cart and their price
	void calculateTotal();
}
