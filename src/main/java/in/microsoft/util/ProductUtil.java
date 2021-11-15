package in.microsoft.util;



import org.springframework.stereotype.Component;

import in.microsoft.entity.Product;

@Component
//Java 8- interface #Static methods
public class ProductUtil {
	
	public void calcProd(Product prd) {
		double cost=prd.getProdCost();
		
		double gst=cost*20/100;
		double desc=cost*12/100;
		
		prd.setProdGst(gst);
		prd.setProdDisc(desc);
		
	}

}
