package com.test.store.service.serviceImpl;

import java.sql.Connection;
import java.util.List;
import com.test.store.dao.CategoryDao;
import com.test.store.domain.Category;
import com.test.store.service.CategoryService;
import com.test.store.util.BeanFactory;
import com.test.store.util.JDBCUtils;

public class CategoryServiceImpl implements CategoryService {

	CategoryDao categoryDao = (CategoryDao) BeanFactory.createObject("CategoryDao");

	public List<Category> findAllCategory() throws Exception {

		List<Category> findAllCategory = categoryDao.findAllCategory();
		return findAllCategory;
	}

	public int AddCategory(int cid, String cname) throws Exception {

		Connection conn = null;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			int i = categoryDao.AddCategory(cid, cname);
			conn.commit();
			return i;
		} catch (Exception e) {
			conn.rollback();
		}
		return 0;
	}

	public int delCategory(int cid) {

		int i = categoryDao.delCategory(cid);

		return i;

	}

	public Category searchCategory(int cid) throws Exception {
		
		Category category = categoryDao.searchCategory(cid);

		return category;
	}

	public int EditCategory(int cid, String cname) throws Exception {

		int i = categoryDao.EditCategory(cid, cname);

		return i;

	}

	public int getMaxCid() throws Exception {

		return categoryDao.getMaxCid();

	}

}
