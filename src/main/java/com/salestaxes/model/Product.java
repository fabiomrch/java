package com.salestaxes.model;

import java.math.BigDecimal;

import com.salestaxes.model.type.ProductType;

/**
 * Model of a purchasable product 
 * @author fab
 *
 */
public class Product {
	private final String name;
	private final BigDecimal price;
	private final boolean imported;
	private final ProductType type;
	
	/**
	 * 
	 * @param name Name that identifies the product
	 * @param price Price potentially subject to taxation 
	 * @param imported Boolean that indicates whether the product is imported (true) or local (false)
	 * @param type Category expressed by an enum value
	 */
	public Product(String name, BigDecimal price, boolean imported, ProductType type) {
		super();
		this.name = name;
		this.price = price;
		this.imported = imported;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public boolean isImported() {
		return imported;
	}

	public ProductType getType() {
		return type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (imported ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (imported != other.imported)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return (imported ? "imported " : "") + name;
	}
	
}
