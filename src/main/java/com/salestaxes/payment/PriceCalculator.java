package com.salestaxes.payment;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.salestaxes.model.Product;
import com.salestaxes.purchasing.ShoppingBasket;
import com.salestaxes.tax.Tax;

/**
 * Class containing all the functions neccesary to calculate products final cost
 * @author fab
 *
 */
public class PriceCalculator {
	private final List<Tax> taxes;

	/**
	 * 
	 * @param taxes Taxes that we want to consider while calculating the total cost of the items in the shopping basket
	 */
	public PriceCalculator(Tax... taxes) {
		super();
		this.taxes = Arrays.asList(taxes);
	}
	
	/**
	 * Calculate taxes amount applied on a specific quantity of a product
	 * @param product Specific product
	 * @param qty Product quantity
	 * @return Taxes amount applied on the product
	 */
	public BigDecimal calculateSalesTaxesAmount(Product product, int qty) {
		BigDecimal amount = BigDecimal.ZERO;
		for(Tax tax : taxes)
		{
			amount = amount.add(tax.calculateTaxAmount(product).multiply(new BigDecimal(qty)));
		}
		
		return amount.setScale(2, RoundingMode.DOWN);
	}
	
	/**
	 * Calculate taxes amount applied on the totality of the products added to the shopping basket
	 * @param shoppingBasket Shopping basket containing all the products to purchase
	 * @return Taxes amount applied on all the products in the shopping basket
	 */
	public BigDecimal calculateSalesTaxesAmount (ShoppingBasket shoppingBasket){
		Map<Product, Integer> products = shoppingBasket.getProducts();
		
		BigDecimal salesTaxesAmount = BigDecimal.ZERO;
		for(Product product : products.keySet())
		{
			salesTaxesAmount = salesTaxesAmount.add(calculateSalesTaxesAmount(product, products.get(product)));
		}
		
		return salesTaxesAmount.setScale(2, RoundingMode.DOWN);
	}
	
	/**
	 * Calculate total amount after taxes for a specific quantity of a product
	 * @param product Specific product
	 * @param qty Product quantity
	 * @return Total amount after taxes to pay for the product
	 */
	public BigDecimal calculateTotalAmountAfterTax(Product product, int qty) {
		BigDecimal tax = calculateSalesTaxesAmount(product, qty);
		BigDecimal price = BigDecimal.valueOf(product.getPrice().doubleValue()).multiply(BigDecimal.valueOf(qty));
		return price.add(tax).setScale(2, RoundingMode.DOWN);
	}
	
	/**
	 * Calculate total amount after taxes for the totality of the products added to the shopping basket
	 * @param shoppingBasket Shopping basket containing all the products to purchase
	 * @return Total amount after taxes to pay for all the products in the shopping basket
	 */
	public BigDecimal calculateTotalAmountAfterTax (ShoppingBasket shoppingBasket){
		Map<Product, Integer> products = shoppingBasket.getProducts();
		
		BigDecimal totalAmountAfterTax = BigDecimal.ZERO;
		for(Product product : products.keySet())
		{
			totalAmountAfterTax = totalAmountAfterTax.add(calculateTotalAmountAfterTax(product, products.get(product)));
		}
		
		return totalAmountAfterTax.setScale(2, RoundingMode.DOWN);
	}
}
