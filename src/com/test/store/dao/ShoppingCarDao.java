package com.test.store.dao;

import java.util.List;

import com.test.store.domain.ShoppingCar;
import com.test.store.domain.ShoppingCarItem;

public interface ShoppingCarDao {
	int addProductToCarByPid(String userID, int pid, int quantity) throws Exception;

	List<ShoppingCarItem> findShoppingCarProduct(String userID) throws Exception;
	
	int UpdateShoppingCarProduct(int quantity, int pid, String userID) throws Exception;
	
	ShoppingCar findShoppingCarProductByPid(String userID, int pid) throws Exception;
	
	int delShoppingCarProduct(int pid, String userID) throws Exception;
	
	int clearShoppingCarProduct(String userID) throws Exception;
}
