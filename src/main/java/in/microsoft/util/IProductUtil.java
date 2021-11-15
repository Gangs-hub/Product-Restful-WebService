package in.microsoft.util;

import in.microsoft.entity.Product;

public interface IProductUtil {

	public static void calcProd(Product prd) {
		double cost=prd.getProdCost();
		
		double gst=cost*20/100;
		double desc=cost*12/100;
		
		prd.setProdGst(gst);
		prd.setProdDisc(desc);
		
	}
}
