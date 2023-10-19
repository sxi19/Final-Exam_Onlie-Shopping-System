package Products;

public class ClothingProduct extends Product {
	
	private final int warrentyPeroid;
	private final String size;
	private final String material;
	
	
	public ClothingProduct(String productName, float price, int stockQuantity 
						, int warrPeriod, String size, String material) {
		
		super(productName, price, stockQuantity);
		this.warrentyPeroid = warrPeriod;
		this.material = material;
		this.size = size;
	}

	public int getWarrentyPeroid() {
		return warrentyPeroid;
	}

	public String getSize() {
		return size;
	}

	public String getMaterial() {
		return material;
	}

	@Override
	// print all production information
	public void showProductInfo() {
		System.out.printf("ID: %s; Name: %s; Price: %.2f; StockQTY: %d; Warrenty Period: %d month; Size: %s; Material: %s\n",
			this.getProductID(),this.getProductName(),this.getPrice(),this.getStockQuantity(),
			this.getWarrentyPeroid(),this.getSize(),this.getMaterial());
	}
}
