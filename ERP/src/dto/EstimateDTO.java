package dto;

import java.sql.Date;

public class EstimateDTO {
	private int estNum;
	private String estCorp;
	private String estDate;
	private String[] productName = new String[5];
	private int[] quantity = new int [5];
	private int[] unitPrice = new int [5];
	private int[] supplyPrice = new int [5];

	public int getEstNum() {
		return estNum;
	}
	public void setEstNum(int string) {
		this.estNum = string;
	}
	public String getEstCorp() {
		return estCorp;
	}
	public void setEstCorp(String string) {
		this.estCorp = string;
	}
	public String getEstDate() {
		return estDate;
	}
	public void setEstDate(String string) {
		this.estDate = string;
	}
	public String getProductName(int i) {
		return productName[i];
	}
	public void setProductName(int i,String temp) {
		productName[i] = temp;
	}
	public int getQuantity(int i) {
		return quantity[i];
	}
	public void setQuantity(int i,int temp ) {
		quantity[i] = temp;
	}
	public int getUnitPrice(int i) {
		return unitPrice[i];
	}
	public void setUnitPrice(int i ,int temp) {
		unitPrice[i] = temp;
	}
	public int getSupplyPrice(int i) {
		return supplyPrice[i];
	}
	public void setSupplyPrice(int i, int temp) {
		supplyPrice[i] = temp;
	}
}
