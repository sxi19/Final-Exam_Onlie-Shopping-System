package Products;

public class Eproduct extends Product {
	
	private final int warrentyPeroid;
	private final float voltage;
	
	
	public Eproduct(String productName, float price, int stockQuantity, int warrPeriode, float volt) {
		
		super(productName, price, stockQuantity);
		this.voltage = volt;
		this.warrentyPeroid = warrPeriode;
	}
	
	public int getWarrentyPeroid() {
		return warrentyPeroid;
	}

	public float getVoltage() {
		return voltage;
	}
	
	@Override
	// print all production information
	public void showProductInfo() {
		System.out.printf("ID: %s, Name: %s, Price: %.2f, StockQTY: %d, Warrenty Period: %d month, Voltage: %.2fv\n",
					this.getProductID(),this.getProductName(),this.getPrice(),this.getStockQuantity(),
					this.getWarrentyPeroid(),this.getVoltage());
	}

}
