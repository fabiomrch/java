package com.salestaxes.purchasing;

import java.util.HashMap;
import java.util.Map;

import com.salestaxes.exception.InvalidItemQuantityException;
import com.salestaxes.model.Product;

/**
 * Class representing the shopping basket
 * @author fab
 *
 */
public class ShoppingBasket {
	private Map<Product, Integer> products;

	public ShoppingBasket() {
		products = new HashMap<Product, Integer>();
	}

	public Map<Product, Integer> getProducts() {
		return products;
	}

	/**
	 * Add a single product to the basket
	 * @param product Product to add
	 */
	public void addProduct(Product product) {
		if(products.containsKey(product))
			products.put(product, products.get(product) + 1);
		else
			products.put(product, 1);
	}
	
	/**
	 * Add a specific quantity of a product to the basket
	 * @param product Product to add 
	 * @param qty Quantity related to the product
	 * @throws InvalidItemQuantityException The quantity of an item must be greater than zero
	 */
	public void addProduct(Product product, int qty) throws InvalidItemQuantityException {
		if(qty < 1)
			throw new InvalidItemQuantityException("The quantity of an item must be greater than zero");
			
		products.put(product, qty);
	}
		
}
