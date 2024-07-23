package dto;

public class StockDTO {
	private String productName;
	private String loc;
	private String producCorpName;
	private int totalQuantity;

	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public String getProducCorpName() {
		return producCorpName;
	}
	public void setProducCorpName(String p) {
		this.producCorpName = p;
	}
	public int getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(int i) {
		this.totalQuantity = i;
	}
}
