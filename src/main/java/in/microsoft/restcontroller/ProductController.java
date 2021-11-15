package in.microsoft.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.microsoft.entity.Product;
import in.microsoft.exception.ProductNotFoundException;
import in.microsoft.serviceImpl.ProductServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/product")
@Api(description="Product Test Operations")
@Slf4j
public class ProductController {

	@Autowired
	private ProductServiceImpl prodImpl;

	//1. create product
	@PostMapping("/save")
	public ResponseEntity<String>CreatePrd(
			@RequestBody Product product

			){
		Integer id= prodImpl.savePrd(product);
		String body="Product with id '"+id+"'Created!";
		log.info("Product with id '"+id+"'Created!");
		return new ResponseEntity<String>(body, HttpStatus.CREATED);//201 new object!

	}

	//2. fetch all products

	@GetMapping("/all")
	@ApiOperation("Fetch all rows as List")
	public ResponseEntity<List<Product>> fetchAllPrd(){
		List<Product> prdList=prodImpl.getAllPrd();
		return new ResponseEntity<List<Product>>(prdList,HttpStatus.OK);

	}


	//3. fetch one by id
	@GetMapping("/one/{id}") //Dyanamic Path
	public ResponseEntity<?>fetchonePrd(
			@PathVariable Integer id){
		ResponseEntity<?> response=null;
		try {
			Product prd=prodImpl.getOnePrd(id);
			response=new ResponseEntity<Product>(prd,HttpStatus.OK);
		}catch(ProductNotFoundException ex){
			
			log.error("Opps error!",ex);
			//ex.printStackTrace();
			//response=new ResponseEntity<String>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			throw ex;
		}
		return response;

	}

	//4. remove one by id
	//@ApiIgnore
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?>deletePrd(
			@PathVariable Integer id){
		ResponseEntity<?> response =null;
		try {
			prodImpl.removeOnePrd(id);
			response=new ResponseEntity<String>(id+" Removed",HttpStatus.OK);

		} catch (ProductNotFoundException ex) {
			//ex.printStackTrace();
			//response =new ResponseEntity<String>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			log.error("Opps error!",ex);
			throw ex;
		}
		return response;

	}
	//5. update product

	//1 Put: complete update
	@PutMapping("/modify")
	public ResponseEntity<String>modifyPrd(
			@RequestBody Product product
			){
		ResponseEntity<String> response=null;
		try {
			prodImpl.updatePrd(product);
			response=new ResponseEntity<>(product.getProdId()+"Updated",HttpStatus.OK);
			log.info(product.getProdId()+"Updated");
		} catch (ProductNotFoundException ex) {
			//response=new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			log.error("Opps error!",ex);
			throw ex;
			
		}

		return response;

	}


	//2 Patch: Partial update
	@PatchMapping("/update/{id}/{name}")
	public ResponseEntity<String>updatePrd(
			@PathVariable Integer id,
			@PathVariable String name){
		ResponseEntity<String>response=null;
		try {
			prodImpl.partialPrdUp(id, name);
			response =new ResponseEntity<String>(id+"Upadted",HttpStatus.OK);
            log.info(id+"Upadted");
		} catch (ProductNotFoundException ex) {
		//	response =new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			log.error("Opps error!",ex);
			throw ex;
		}
		return response;

	}



}
