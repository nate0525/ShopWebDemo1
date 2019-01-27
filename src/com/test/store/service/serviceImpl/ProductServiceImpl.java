package com.test.store.service.serviceImpl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import com.test.store.dao.ProductDao;
import com.test.store.domain.PageModel;
import com.test.store.domain.Product;
import com.test.store.domain.UserCollection;
import com.test.store.service.ProductService;
import com.test.store.util.BeanFactory;
import com.test.store.util.PageUtils;

public class ProductServiceImpl implements ProductService {

	ProductDao productDao = (ProductDao) BeanFactory.createObject("ProductDao");

	public List<Product> findNewProducts() throws SQLException {

		List<Product> findNewProductsList = productDao.findNewProducts();

		return findNewProductsList;

	}

	public List<Product> findHotProducts() throws SQLException {

		List<Product> findHotProductsList = productDao.findHotProducts();

		return findHotProductsList;

	}

	public Product findProduct(int pid) throws SQLException {

		Product findProduct = productDao.findProduct(pid);

		return findProduct;

	}

	public List<Product> findAllProduct(String cid, PageUtils pageUtil) throws SQLException {

		List<Product> findAllProduct = productDao.findAllProduct(cid, pageUtil);

		return findAllProduct;

	}

	public int countAll(String cid) throws Exception {

		int i = productDao.countAll(cid);

		return i;

	}

	public PageModel findAllProductWithPage(int curNum) throws Exception {

		int totalcords = productDao.countAllProduct();

		PageModel pm = new PageModel(curNum, totalcords, 7);

		List<Product> list = productDao.findAllProductWihPage(pm.getStartIndex(), pm.getPageSize());

		pm.setList(list);

		pm.setUrl("/AdminProductServlet?method=findAllPrductWithPage");

		return pm;

	}

	public PageModel findLosePrductWithPage(String Sname, int pflag, int curNum) throws Exception {

		int totalcords = productDao.countProduct(Sname, pflag);

		PageModel pm = new PageModel(curNum, totalcords, 7);

		List<Product> list = productDao.findLosePrductWithPage(pflag, pm.getStartIndex(), pm.getPageSize());

		pm.setList(list);

		pm.setUrl("/AdminProductServlet?method=findLosePrductWithPage");

		return pm;

	}

	public List<Product> findSearchProduct(String pname) throws SQLException {

		List<Product> findSearchProduct = productDao.findSearchProduct(pname);

		return findSearchProduct;

	}

	public int CountSearchProduct(String pname) throws SQLException {
		return productDao.CountSearchProduct(pname);
	}

	public int getMaxPid() throws Exception {
		return productDao.getMaxPid();
	}

	public int saveProduct(Product product) throws Exception {
		return productDao.saveProduct(product);
	}

	public int delProduct(int pid) throws Exception {
		return productDao.delProduct(pid);
	}
	
	public int UpdateProduct(int pid,Product product) throws Exception{
		return productDao.UpdateProduct(pid, product);
	}
	
	public int CollectionProduct(String userID, int pid,Date date) throws Exception{
		return productDao.CollectionProduct(userID, pid,date);
	}
	
	public PageModel findCollectionByUserIDWithPage(String userID, int curNum) throws Exception{
		
		int totalcords = productDao.CountCollectionbyUserID(userID);

		PageModel pm = new PageModel(curNum, totalcords, 12);

		List<UserCollection> list = productDao.findCollectionByUserIDWithPage(userID, pm.getStartIndex(), pm.getPageSize());
		
		pm.setList(list);

		pm.setUrl("/ProductServlet?method=findMyCollection");

		return pm;
	}
	
	public int CountCollectionbyUserID(String userID) throws Exception {
		return productDao.CountCollectionbyUserID(userID);
	}
	
	public int delCollectionByPid(int pid) throws Exception{
		return productDao.delCollectionByPid(pid);
	}
	
	public int CollectionProductOrNot(String userID, int pid) throws Exception{
		return productDao.CollectionProductOrNot(userID, pid);
	}

	public int CountAllProductByPflag(int pflag) throws Exception {
		return productDao.CountAllProductByPflag(pflag);
	}
	
	
}
