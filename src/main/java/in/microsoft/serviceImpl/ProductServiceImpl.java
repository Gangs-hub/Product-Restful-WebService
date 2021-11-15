package in.microsoft.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.microsoft.entity.Product;
import in.microsoft.exception.ProductNotFoundException;
import in.microsoft.jparepo.ProductReposity;
import in.microsoft.service.IProductService;
import in.microsoft.util.IProductUtil;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements IProductService {

	@Autowired
	private ProductReposity prRepo;

	/*
	 * @Autowired private ProductUtil util;
	 */


	@Override
	public Integer savePrd(Product prd) {
		IProductUtil.calcProd(prd);           //here we can directly call static method(body in itself) from Interface java 8 feature 
		return prRepo.save(prd).getProdId();
	}

	@Override
	public List<Product> getAllPrd() {

		return prRepo.findAll();
	}

	@Override
	public Product getOnePrd(Integer id) {
		/*
		 * Optional<Product> opt= prRepo.findById(id); if(opt.isPresent()) { return
		 * opt.get(); }else { throw new ProductNotFoundException(id+ " Not Exist"); }
		 */
		return prRepo.findById(id).orElseThrow(()->new ProductNotFoundException(id+" Not Exist"));

	}

	@Override
	public void removeOnePrd(Integer id) {
		if(id!=null&&prRepo.existsById(id)) {
			prRepo.deleteById(id);
		}else {
			log.error(id+"Not found");
			throw new ProductNotFoundException(id+"Not found");
		}


	}

	@Override
	public void updatePrd(Product prd) {
		if(prd.getProdId()!=null && prRepo.existsById(prd.getProdId())) {
			IProductUtil.calcProd(prd);
			prRepo.save(prd);
		}else {
			log.error(prd.getProdId()+" Not found");
			throw new ProductNotFoundException(prd.getProdId()+" Not found");
		}

	}
//Must applied over custome defined methods
	@Transactional                 //Will begin Tx and commit() if method/query success or else will rollback()
	public void partialPrdUp(Integer id, String name) {
		
		if(id!=null&& prRepo.existsById(id)) {
			prRepo.partialModifyprd(id, name);
		}else {
			log.error(id+"Not found!");
			throw new ProductNotFoundException(id+"Not found!");
		}
		
	}

}
