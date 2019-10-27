package com.salestaxes.tax;

import com.salestaxes.model.Product;

/**
 * Condition by which the tax is applied on imported products (imported = true)
 * @author fab
 *
 */
public class ImportedTaxSubjectionCondition implements TaxSubjectionCondition {

	public boolean isSubject(Product product) {
		return product.isImported();
	}

}
