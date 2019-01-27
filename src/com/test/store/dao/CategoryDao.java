package com.test.store.dao;

import java.util.List;
import com.test.store.domain.Category;

public interface CategoryDao {
	public List<Category> findAllCategory() throws Exception;

	public int AddCategory(int cid, String cname) throws Exception;

	int delCategory(int cid);

	Category searchCategory(int cid) throws Exception;

	int EditCategory(int cid, String cname) throws Exception;

	int getMaxCid() throws Exception;
}
