package com.test.store.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.test.store.domain.PageModel;
import com.test.store.domain.Product;
import com.test.store.util.PageUtils;

public interface ProductService {

	List<Product> findNewProducts() throws SQLException;

	List<Product> findHotProducts() throws SQLException;

	Product findProduct(int pid) throws SQLException;

	List<Product> findAllProduct(String cid, PageUtils pageUtil) throws SQLException;

	int countAll(String cid) throws Exception;

	PageModel findAllProductWithPage(int curNum) throws Exception;
	
	PageModel findLosePrductWithPage(String Sname, int pflag, int curNum) throws Exception;

	List<Product> findSearchProduct(String pname) throws SQLException;

	int CountSearchProduct(String pname) throws SQLException;
	
	int getMaxPid() throws Exception;

	int saveProduct(Product product)throws Exception;
	
	int delProduct(int pid) throws Exception;
	
	int UpdateProduct(int pid,Product product) throws Exception;
	
	int CollectionProduct(String userID, int pid,Date date) throws Exception;
	
	int CountCollectionbyUserID(String userID) throws Exception;
	
	PageModel findCollectionByUserIDWithPage(String userID, int curNum) throws Exception;
	
	int delCollectionByPid(int pid) throws Exception;
	
	int CollectionProductOrNot(String userID, int pid) throws Exception;
	
	int CountAllProductByPflag(int pflag) throws Exception;
}
