package com.salestaxes.tax;

import java.math.BigDecimal;

import com.salestaxes.model.Product;
import com.salestaxes.utils.PriceRounder;

/**
 * Model of a tax to apply on products according to custom conditions while calculating their price: 
 * it makes the creation of new tax methods flexible and scalable
 * @author fab
 *
 */
public class Tax {
	private final String name;
	private final double rate;
	private final TaxSubjectionCondition subjectionCondition;
	
	/**
	 * 
	 * @param name Tax identifier
	 * @param rate Rate expressed as a percentage
	 * @param subjectionCondition Condition necessary to check whether a specific product must be subject to the tax or not
	 */
	public Tax(String name, double rate, TaxSubjectionCondition subjectionCondition) {
		super();
		this.name = name;
		this.rate = rate;
		this.subjectionCondition = subjectionCondition;
	}

	public String getName() {
		return name;
	}

	public double getRate() {
		return rate;
	}

	public TaxSubjectionCondition getSubjectionCondition() {
		return subjectionCondition;
	}
	
	/**
	 * Calculate tax amount on a specific product
	 * @param product Product whose tax amount on the price is calculated by the method
	 * @return Tax amount corresponding to the product price
	 */
	public BigDecimal calculateTaxAmount(Product product) {
		if(subjectionCondition.isSubject(product))
		{
			BigDecimal taxAmount = product.getPrice().multiply(new BigDecimal(rate));
			return PriceRounder.roundUp(taxAmount);
		}

		return BigDecimal.ZERO;
	}
}
