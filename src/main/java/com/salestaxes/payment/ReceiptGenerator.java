package com.salestaxes.payment;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.salestaxes.purchasing.ShoppingBasket;
import com.salestaxes.tax.Tax;

/**
 * Class representing a biller that generates receipts
 * @author fab
 *
 */
public class ReceiptGenerator {
	private final List<Tax> taxes;
	
	/**
	 * 
	 * @param taxes Taxes to consider while calculating the total amount corresponding to the order to include in the receipt
	 */
	public ReceiptGenerator(Tax... taxes) {
		super();
		this.taxes = Arrays.asList(taxes);
	}

	/**
	 * Method that generates the receipt
	 * @param shoppingBasket Shopping basket containing all the products purchased
	 * @return Generated receipt
	 */
	public Receipt generateReceipt(ShoppingBasket shoppingBasket) {
		Tax[] taxArray = new Tax[taxes.size()];
		PriceCalculator priceCalculator = new PriceCalculator(taxes.toArray(taxArray));
		BigDecimal totalAmount = priceCalculator.calculateTotalAmountAfterTax(shoppingBasket);
		BigDecimal salesTaxesAmount = priceCalculator.calculateSalesTaxesAmount(shoppingBasket);
		List<ReceiptItem> receiptItems = 
			shoppingBasket
				.getProducts()
				.keySet()
				.stream()
				.map(product -> new ReceiptItem(product, shoppingBasket.getProducts().get(product), priceCalculator.calculateTotalAmountAfterTax(product, shoppingBasket.getProducts().get(product))))
				.collect(Collectors.toList());
		return new Receipt(receiptItems, totalAmount, salesTaxesAmount);
	}

}
