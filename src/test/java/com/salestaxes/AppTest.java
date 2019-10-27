package com.salestaxes;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.salestaxes.exception.InvalidItemQuantityException;
import com.salestaxes.model.Product;
import com.salestaxes.model.type.ProductType;
import com.salestaxes.payment.PriceCalculator;
import com.salestaxes.payment.Receipt;
import com.salestaxes.payment.ReceiptGenerator;
import com.salestaxes.purchasing.ShoppingBasket;
import com.salestaxes.tax.ImportedTaxSubjectionCondition;
import com.salestaxes.tax.ProductTypeTaxSubjectionCondition;
import com.salestaxes.tax.Tax;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     * @throws InvalidItemQuantityException 
     */
    public void testApp() throws InvalidItemQuantityException
    {
    	// Common test cases configuration
    	Tax basicTax = new Tax("Basic Tax", 0.10, new ProductTypeTaxSubjectionCondition(ProductType.BOOK, ProductType.FOOD, ProductType.MEDICAL));
    	Tax importTax = new Tax("Import Tax", 0.05, new ImportedTaxSubjectionCondition());
    	
    	PriceCalculator priceCalculator = new PriceCalculator(basicTax, importTax);
    	
    	
    	// Test case 1
    	Product p11 = new Product("book", new BigDecimal(12.49), false, ProductType.BOOK);
    	Product p12 = new Product("music CD", new BigDecimal(14.99), false, ProductType.OTHER);
    	Product p13 = new Product("chocolate bar", new BigDecimal(0.85), false, ProductType.FOOD);
        ShoppingBasket shoppingBasket1 = new ShoppingBasket();
        shoppingBasket1.addProduct(p11, 1);
        shoppingBasket1.addProduct(p12, 1);
        shoppingBasket1.addProduct(p13, 1);

        assertEquals(new BigDecimal("12.49"), priceCalculator.calculateTotalAmountAfterTax(p11, 1));
        assertEquals(new BigDecimal("16.49"), priceCalculator.calculateTotalAmountAfterTax(p12, 1));
        assertEquals(new BigDecimal("0.85"), priceCalculator.calculateTotalAmountAfterTax(p13, 1));
        assertEquals(new BigDecimal("1.50"), priceCalculator.calculateSalesTaxesAmount(shoppingBasket1));
        assertEquals(new BigDecimal("29.83"), priceCalculator.calculateTotalAmountAfterTax(shoppingBasket1));
       
        
        // Test case 2
        Product p21 = new Product("box of chocolates", new BigDecimal(10.00), true, ProductType.FOOD);
        Product p22 = new Product("bottle of perfume", new BigDecimal(47.50), true, ProductType.OTHER);
        	
        ShoppingBasket shoppingBasket2 = new ShoppingBasket();        
        shoppingBasket2.addProduct(p21, 1);
        shoppingBasket2.addProduct(p22, 1);
    
        assertEquals(new BigDecimal("10.50"), priceCalculator.calculateTotalAmountAfterTax(p21, 1));
        assertEquals(new BigDecimal("54.65"), priceCalculator.calculateTotalAmountAfterTax(p22, 1));
        assertEquals(new BigDecimal("7.65"), priceCalculator.calculateSalesTaxesAmount(shoppingBasket2));
        assertEquals(new BigDecimal("65.15"), priceCalculator.calculateTotalAmountAfterTax(shoppingBasket2));

        
        // Test case 3
        Product p31 = new Product("bottle of perfume", new BigDecimal(27.99), true, ProductType.OTHER);
        Product p32 = new Product("bottle of perfume", new BigDecimal(18.99), false, ProductType.OTHER);
        Product p33 = new Product("packet of headache pills", new BigDecimal(9.75), false, ProductType.MEDICAL);
        Product p34 = new Product("box of chocolates", new BigDecimal(11.25), true, ProductType.FOOD);
        
        ShoppingBasket shoppingBasket3 = new ShoppingBasket();
        shoppingBasket3.addProduct(p31, 1);
        shoppingBasket3.addProduct(p32, 1);
        shoppingBasket3.addProduct(p33, 1);
        shoppingBasket3.addProduct(p34, 1);
        
        assertEquals(new BigDecimal("32.19"), priceCalculator.calculateTotalAmountAfterTax(p31, 1));
        assertEquals(new BigDecimal("20.89"), priceCalculator.calculateTotalAmountAfterTax(p32, 1));
        assertEquals(new BigDecimal("9.75"), priceCalculator.calculateTotalAmountAfterTax(p33, 1));
        assertEquals(new BigDecimal("11.85"), priceCalculator.calculateTotalAmountAfterTax(p34, 1));
        assertEquals(new BigDecimal("6.70"), priceCalculator.calculateSalesTaxesAmount(shoppingBasket3));
        assertEquals(new BigDecimal("74.68"), priceCalculator.calculateTotalAmountAfterTax(shoppingBasket3));
    }
}
