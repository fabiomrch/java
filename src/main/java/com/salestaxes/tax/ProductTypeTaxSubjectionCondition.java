package com.salestaxes.tax;

import java.util.Arrays;
import java.util.List;

import com.salestaxes.model.Product;
import com.salestaxes.model.type.ProductType;

/**
 * Condition by which the tax is applied on specific product types only
 * @author fab
 *
 */
public class ProductTypeTaxSubjectionCondition implements TaxSubjectionCondition {

	private final List<ProductType> exemptTypes;
	
	/**
	 * 
	 * @param exemptTypes List of product types exempt from the subjection to the tax
	 */
	public ProductTypeTaxSubjectionCondition(ProductType... exemptTypes) {
		super();
		this.exemptTypes = Arrays.asList(exemptTypes);
	}

	public boolean isSubject(Product product) {
		return !exemptTypes.contains(product.getType());
	}

}
