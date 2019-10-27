package com.salestaxes.tax;

import com.salestaxes.model.Product;

/**
 * Blueprint of a condition that a product has to satisfy in order to be subject to a tax
 * @author fab
 *
 */
public interface TaxSubjectionCondition {
	/**
	 * Check whether the product must be subject of a tax or not
	 * @param product Product to check
	 * @return true if the product satisfies the conditions according to which must be subject to tax
	 */
	boolean isSubject(Product product);
}
