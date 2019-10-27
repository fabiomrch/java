package com.salestaxes;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.salestaxes.model.Product;
import com.salestaxes.model.type.ProductType;
import com.salestaxes.payment.Receipt;
import com.salestaxes.payment.ReceiptGenerator;
import com.salestaxes.purchasing.ShoppingBasket;
import com.salestaxes.tax.ImportedTaxSubjectionCondition;
import com.salestaxes.tax.ProductTypeTaxSubjectionCondition;
import com.salestaxes.tax.Tax;

/**
 * Class with main method usable to test receipt generation
 * @author fab
 *
 */
public class App 
{
	private static final Logger log = LoggerFactory.getLogger(App.class);
	
    public static void main( String[] args )
    {
    	try {
	    	Tax basicTax = new Tax("Basic Tax", 0.10, new ProductTypeTaxSubjectionCondition(ProductType.BOOK, ProductType.FOOD, ProductType.MEDICAL));
	    	Tax importTax = new Tax("Import Tax", 0.05, new ImportedTaxSubjectionCondition());
	    	
	    	ReceiptGenerator receiptGenerator = new ReceiptGenerator(basicTax, importTax);
	    	
	        ShoppingBasket shoppingBasket1 = new ShoppingBasket();
	        shoppingBasket1.addProduct(new Product("book", new BigDecimal(12.49), false, ProductType.BOOK), 1);
	        shoppingBasket1.addProduct(new Product("music CD", new BigDecimal(14.99), false, ProductType.OTHER), 1);
	        shoppingBasket1.addProduct(new Product("chocolate bar", new BigDecimal(0.85), false, ProductType.FOOD), 1);
	
	        Receipt receipt1 = receiptGenerator.generateReceipt(shoppingBasket1);
	        System.out.println(receipt1.toString() + "\n");
	        
	        ShoppingBasket shoppingBasket2 = new ShoppingBasket();        
	        shoppingBasket2.addProduct(new Product("box of chocolates", new BigDecimal(10.00), true, ProductType.FOOD), 1);
	        shoppingBasket2.addProduct(new Product("bottle of perfume", new BigDecimal(47.50), true, ProductType.OTHER), 1);
	    
	        Receipt receipt2 = receiptGenerator.generateReceipt(shoppingBasket2);      
	        System.out.println(receipt2.toString() + "\n");
	        
	        ShoppingBasket shoppingBasket3 = new ShoppingBasket();
	        shoppingBasket3.addProduct(new Product("bottle of perfume", new BigDecimal(27.99), true, ProductType.OTHER), 1);
	        shoppingBasket3.addProduct(new Product("bottle of perfume", new BigDecimal(18.99), false, ProductType.OTHER), 1);
	        shoppingBasket3.addProduct(new Product("packet of headache pills", new BigDecimal(9.75), false, ProductType.MEDICAL), 1);
	        shoppingBasket3.addProduct(new Product("box of chocolates", new BigDecimal(11.25), true, ProductType.FOOD), 1);
	        
	        Receipt receipt3 = receiptGenerator.generateReceipt(shoppingBasket3);      
	        System.out.println(receipt3.toString());
    	} catch(Exception e) {
    		log.error("", e);
    	}
    }
}
