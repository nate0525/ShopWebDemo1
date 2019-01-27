package com.test.store.dao.daoImpl;

import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import com.test.store.dao.CategoryDao;
import com.test.store.domain.Category;
import com.test.store.util.JDBCUtils;

public class CategoryDaoImpl implements CategoryDao {

	public List<Category> findAllCategory() throws Exception {

		String sql = "SELECT * FROM category";

		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

		return qr.query(sql, new BeanListHandler<Category>(Category.class));

	}

	public int AddCategory(int cid, String cname) throws Exception {

		String sql = "INSERT INTO category VALUE(?,?)";

		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

		int i = qr.update(sql, cid, cname);

		return i;

	}

	public int delCategory(int cid) {

		try {

			String sql = "DELETE FROM category WHERE cid=?";

			QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

			int i = qr.update(sql, cid);

			return i;

		} catch (SQLException e) {
			if (e.getMessage().contains("foreign")) {
				return -1;
			}
		}
		return 0;
	}

	public Category searchCategory(int cid) throws Exception {

		String sql = "SELECT * FROM category WHERE cid=?";

		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

		Category category = qr.query(sql, new BeanHandler<Category>(Category.class), cid);

		return category;

	}

	public int EditCategory(int cid, String cname) throws Exception {

		String sql = "UPDATE category SET cname=? WHERE cid=?";

		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

		int i = qr.update(sql, cname, cid);

		return i;

	}

	public int getMaxCid() throws Exception {

		String sql = "SELECT * FROM category ORDER BY cid DESC LIMIT 0 ,1;";

		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

		Category query = qr.query(sql, new BeanHandler<Category>(Category.class));

		int max = query.getCid() + 1;

		return max;

	}

}
