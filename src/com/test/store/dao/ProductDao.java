package com.test.store.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.test.store.domain.Product;
import com.test.store.domain.UserCollection;
import com.test.store.util.PageUtils;

public interface ProductDao {

	List<Product> findNewProducts() throws SQLException;

	List<Product> findHotProducts() throws SQLException;

	Product findProduct(int pid) throws SQLException;

	List<Product> findAllProduct(String cid, PageUtils pageUtil) throws SQLException;

	int countAll(String cid) throws Exception;

	List<Product> findAllProductWihPage(int startIndex, int pageSize) throws SQLException;

	List<Product> findLosePrductWithPage(int pflag, int startIndex, int pageSize) throws SQLException;

	int countProduct(String s1, Object s2) throws Exception;

	int countAllProduct() throws Exception;

	List<Product> findSearchProduct(String pname) throws SQLException;

	int CountSearchProduct(String pname) throws SQLException;

	int getMaxPid() throws Exception;

	int saveProduct(Product product) throws Exception;

	int delProduct(int pid) throws Exception;
	
	int UpdateProduct(int pid,Product product) throws Exception;
	
	int CollectionProduct(String userID, int pid,Date date) throws Exception;
	
	List<UserCollection> findCollectionByUserIDWithPage(String userID, int startIndex, int pageSize) throws SQLException;
	
	int CountCollectionbyUserID(String userID) throws Exception ;
	
	int delCollectionByPid(int pid) throws Exception;
	
	int CollectionProductOrNot(String userID, int pid) throws Exception;
	
	int CountAllProductByPflag(int pflag) throws Exception;
}
