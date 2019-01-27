package com.test.store.service.serviceImpl;

import java.util.List;

import com.test.store.dao.ShoppingCarDao;
import com.test.store.domain.ShoppingCar;
import com.test.store.domain.ShoppingCarItem;
import com.test.store.service.ShoppingCarService;
import com.test.store.util.BeanFactory;

public class ShoppingCarServiceImpl implements ShoppingCarService {

	ShoppingCarDao shoppingcardao = (ShoppingCarDao) BeanFactory.createObject("ShoppingCarDao");

	public int addProductToCarByPid(String userID, int pid, int quantity) throws Exception {
		return shoppingcardao.addProductToCarByPid(userID, pid, quantity);
	}

	public List<ShoppingCarItem> findShoppingCarProduct(String userID) throws Exception {
		return shoppingcardao.findShoppingCarProduct(userID);
	}

	public int UpdateShoppingCarProduct(int quantity, int pid, String userID) throws Exception {
		return shoppingcardao.UpdateShoppingCarProduct(quantity, pid, userID);
	}

	public ShoppingCar findShoppingCarProductByPid(String userID, int pid) throws Exception {
		return shoppingcardao.findShoppingCarProductByPid(userID, pid);
	}

	public int delShoppingCarProduct(int pid, String userID) throws Exception {
		return shoppingcardao.delShoppingCarProduct(pid, userID);
	}

	public int clearShoppingCarProduct(String userID) throws Exception {
		return shoppingcardao.clearShoppingCarProduct(userID);
	}

}
