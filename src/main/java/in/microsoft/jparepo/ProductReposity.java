package in.microsoft.jparepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import in.microsoft.entity.Product;

public interface ProductReposity extends JpaRepository<Product, Integer> {
	
	@Modifying  //for other than select queries
	@Query(" UPDATE Product p SET p.prodName=:name where p.prodId=:id")
   public void partialModifyprd(Integer id,String name);
	//only update name of product based on its id
	
	//sample query
	//@Query("SELECT count(e.empMail) FROM Employee e WHERE e.empMail=:email")

}
