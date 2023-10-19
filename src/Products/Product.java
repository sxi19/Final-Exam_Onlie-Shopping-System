package Products;

public abstract class Product {
	
	private final int productID;
	private final String productName;
	private float price;
	private int stockQuantity;
	

	public Product(String productName, float price, int stockQuantity) {
		super();
		this.productID = this.generateProductID();
		this.productName = productName;
		this.setPrice(price);
		this.setStockQuantity(stockQuantity);
	}

	public boolean reduceStock(int quantity) {
		/**
		 * return true if stock quantity be reduced successfully
		 * return false if quantity is larger then stock quantity
		 */
		boolean ret = true;
		if (this.stockQuantity >= quantity) {
			this.stockQuantity -= quantity;
		}else {
			ret = false;
			System.out.println("Quantities of "+ this.productName + " not enouth, only " 
								+ this.stockQuantity + "pcs in stock!");
		}
		return ret;
	}
	
	public boolean isAvailavle() {
		// return true if product is available and vice versa
		boolean ret = true;
		if (this.stockQuantity == 0) {
			ret = false;
		}
		return ret;
	}
	
	private int generateProductID() {
		/**
		 * random number is applied to generate produce ID
		 * min and max are the ranges
		 */
		int min = 1000000;
		int max = 9999999;
		return java.util.concurrent.ThreadLocalRandom.current().nextInt(max - min + 1) + min;
	}
	
	public void setStockQuantity(int stockQuantity) {
		if (stockQuantity == (int)stockQuantity) {
			this.stockQuantity = stockQuantity;
		}else {
			System.out.println("Stock type must be an integer!");
		}
	}
	
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		if (price > 0) {
			this.price = price;
		}else {
			System.out.println("Invalid price!");
		}
	}

	public int getProductID() {
		return productID;
	}

	public String getProductName() {
		return productName;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}
	
	public void showProductInfo() {}
	

}
