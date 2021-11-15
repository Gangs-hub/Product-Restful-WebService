package in.microsoft.service;

import java.util.List;

import in.microsoft.entity.Product;

public interface IProductService {
	
	Integer savePrd(Product prd);
	List<Product> getAllPrd();
	Product getOnePrd(Integer id);
	void removeOnePrd(Integer id);
	void updatePrd(Product prd);
	
	void partialPrdUp(Integer id,String name);
	
	

}
