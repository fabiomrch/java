package com.salestaxes.payment;

import java.math.BigDecimal;
import java.util.List;

/**
 * Class representing a purchase order receipt generated after sales taxes and total amount calculation
 * @author fab
 *
 */
public class Receipt {
	private final List<ReceiptItem> items;
	private final BigDecimal totalAmount;
	private final BigDecimal salesTaxesAmount;
	
	/**
	 * 
	 * @param items Product purchased along with quantity and price after tax
	 * @param totalAmount Total payment amount
	 * @param salesTaxesAmount Total sales taxes amount applied on the whole order
	 */
	public Receipt(List<ReceiptItem> items, BigDecimal totalAmount, BigDecimal salesTaxesAmount) {
		super();
		this.items = items;
		this.totalAmount = totalAmount;
		this.salesTaxesAmount = salesTaxesAmount;
	}

	public List<ReceiptItem> getItems() {
		return items;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public BigDecimal getSalesTaxesAmount() {
		return salesTaxesAmount;
	}

	@Override
	public String toString() {
		String str = "";
		
		for(ReceiptItem item : items)
		{
			str += item.getQuantity() + " " + item.getProduct().toString() + ": " + item.getPriceAfterTax() + "\n";
		}	
		str += "Sales Taxes: " + salesTaxesAmount + "\n";
		str += "Total: " + totalAmount;
		
		return str;
	}
	
}
