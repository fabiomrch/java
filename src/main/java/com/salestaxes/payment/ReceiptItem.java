package com.salestaxes.payment;

import java.math.BigDecimal;

import com.salestaxes.model.Product;

/**
 * A single item that composes the receipt
 * @author fab
 *
 */
public class ReceiptItem {
	private Product product;
	private int quantity;
	private BigDecimal priceAfterTax;
	
	/**
	 * 
	 * @param product Product purchased
	 * @param quantity Quantity related to the product
	 * @param priceAfterTax Price after tax paid for the quantity of the specific product purchased
	 */
	public ReceiptItem(Product product, int quantity, BigDecimal priceAfterTax) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.priceAfterTax = priceAfterTax;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getPriceAfterTax() {
		return priceAfterTax;
	}
	public void setPriceAfterTax(BigDecimal priceAfterTax) {
		this.priceAfterTax = priceAfterTax;
	}
	
}
