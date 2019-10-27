package com.salestaxes.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Utility class that performs tax amount rounding
 * @author fab
 *
 */
public class PriceRounder {
	/**
	 * It returns the price rounded to the nearest greater 0.05
	 * @param price Price to round
	 * @return Rounded price
	 */
	public static BigDecimal roundUp(BigDecimal price) {
		double rounded = ((double) (long) (price.doubleValue() * 20 + 0.5)) / 20;
		if(rounded < price.doubleValue())
			rounded += 0.05;
		return BigDecimal.valueOf(rounded).setScale(2, RoundingMode.DOWN);
	}
}
