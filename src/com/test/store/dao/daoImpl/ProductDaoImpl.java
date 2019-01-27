package com.test.store.dao.daoImpl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import com.test.store.dao.ProductDao;
import com.test.store.domain.Product;
import com.test.store.domain.UserCollection;
import com.test.store.util.JDBCUtils;
import com.test.store.util.PageUtils;

public class ProductDaoImpl implements ProductDao {

	public List<Product> findNewProducts() throws SQLException {

		String sql = "SELECT * FROM product WHERE pflag=0 ORDER BY pdate DESC";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class));

	}

	public List<Product> findHotProducts() throws SQLException {

		String sql = "SELECT * FROM product WHERE pflag=0 && is_hot=1 ORDER BY pdate DESC";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class));

	}

	public Product findProduct(int pid) throws SQLException {

		String sql = "SELECT * FROM product WHERE pid=? ";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<Product>(Product.class), pid);

	}

	public List<Product> findAllProduct(String cid, PageUtils pageUtil) throws SQLException {

		String sql = "SELECT * FROM product WHERE pflag=0 && cid=? LIMIT ?,?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class), cid,
				(pageUtil.getCurrentPage() - 1) * pageUtil.getPageSize(), pageUtil.getPageSize());

	}

	public int countAll(String cid) throws Exception {

		String sql = "SELECT COUNT(*) FROM product WHERE pflag=0 && cid=?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		Long num = (Long) qr.query(sql, new ScalarHandler(), cid);
		return num.intValue();

	}

	public List<Product> findAllProductWihPage(int startIndex, int pageSize) throws SQLException {

		String sql = "SELECT * FROM product WHERE pflag=0 ORDER BY pdate DESC LIMIT ?,?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class), startIndex, pageSize);

	}

	public List<Product> findLosePrductWithPage(int pflag, int startIndex, int pageSize) throws SQLException {

		String sql = "SELECT * FROM product WHERE pflag=? ORDER BY pdate DESC LIMIT ?,?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class), pflag, startIndex, pageSize);

	}

	public int countAllProduct() throws Exception {

		String sql = "SELECT COUNT(*) FROM product WHERE pflag=0";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		Long num = (Long) qr.query(sql, new ScalarHandler());
		return num.intValue();

	}

	public int countProduct(String s1, Object s2) throws Exception {

		String sql = "SELECT COUNT(*) FROM product where pflag=1";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		Long num = (Long) qr.query(sql, new ScalarHandler());
		return num.intValue();

	}

	public List<Product> findSearchProduct(String pname) throws SQLException {

		//String sql = "SELECT * FROM product WHERE pflag=0 && pname LIKE ? LIMIT ?,?;";
		String sql = "SELECT * FROM product WHERE pflag=0 && pname LIKE ?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class), "%" + pname + "%");

	}

	public int CountSearchProduct(String pname) throws SQLException {

		String sql = "SELECT COUNT(*) FROM product WHERE pflag=0 && pname LIKE ?";

		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

		Long num = (Long) qr.query(sql, new ScalarHandler(), "%" + pname + "%");

		return num.intValue();
	}

	public int getMaxPid() throws Exception {

		String sql = "SELECT * FROM product ORDER BY pid DESC LIMIT 0 ,1";

		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

		Product query = qr.query(sql, new BeanHandler<Product>(Product.class));

		int max = query.getPid() + 1;

		return max;

	}

	public int saveProduct(Product product) throws Exception {

		String sql = "INSERT INTO product VALUES(?,?,?,?,?,?,?,?,?,?)";

		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

		Object[] params = { product.getPid(), product.getPname(), product.getMarket_price(), product.getShop_price(),
				product.getPimage(), product.getPdate(), product.getIs_hot(), product.getPdesc(), product.getPflag(),
				product.getCid() };

		int i = qr.update(sql, params);

		return i;

	}

	public int delProduct(int pid) throws Exception {

		String sql = "DELETE FROM product WHERE pid=?";

		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

		int i = qr.update(sql, pid);

		return i;

	}

	public int UpdateProduct(int pid, Product product) throws Exception {

		String sql = "UPDATE product SET pid=?,pname=?,market_price=?,shop_price=?,pimage=?,pdate=?,is_hot=?,pdesc=?,pflag=?,cid=? WHERE pid=?";

		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

		Object[] params = { pid, product.getPname(), product.getMarket_price(), product.getShop_price(),
				product.getPimage(), product.getPdate(), product.getIs_hot(), product.getPdesc(), product.getPflag(),
				product.getCid(), pid };

		int i = qr.update(sql, params);

		return i;

	}

	public int CollectionProduct(String userID, int pid, Date date) throws Exception {

		String sql = "SELECT * FROM collection WHERE userID=? && pid=?";

		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

		UserCollection collection = qr.query(sql, new BeanHandler<UserCollection>(UserCollection.class), userID, pid);

		if (collection != null) {

			return 0;

		} else {

			sql = "INSERT INTO collection VALUES(?,?,?);";

			qr = new QueryRunner(JDBCUtils.getDataSource());

			int i = qr.update(sql, userID, pid, date);

			return i;
		}
	}

	public int CollectionProductOrNot(String userID, int pid) throws Exception {

		String sql = "SELECT * FROM collection WHERE userID=? && pid=?";

		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

		UserCollection query = qr.query(sql, new BeanHandler<UserCollection>(UserCollection.class), userID, pid);

		if (query != null) {
			return 1;
		} else {
			return 0;
		}

	}

	public List<UserCollection> findCollectionByUserIDWithPage(String userID, int startIndex, int pageSize)
			throws SQLException {

		String sql = "SELECT * FROM collection WHERE userID=? ORDER BY date DESC LIMIT ?,?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		List<UserCollection> list = qr.query(sql, new BeanListHandler<UserCollection>(UserCollection.class), userID,
				startIndex, pageSize);
		return list;

	}

	public int CountCollectionbyUserID(String userID) throws Exception {
		String sql = "SELECT COUNT(*) FROM collection WHERE userID=?";

		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

		Long num = (Long) qr.query(sql, new ScalarHandler(), userID);

		return num.intValue();

	}

	public int delCollectionByPid(int pid) throws Exception {

		String sql = "DELETE FROM collection WHERE pid=?";

		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

		int i = qr.update(sql, pid);

		return i;

	}

	public int CountAllProductByPflag(int pflag) throws Exception {

		String sql = "SELECT COUNT(*) FROM product WHERE pflag=?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		Long num = (Long) qr.query(sql, new ScalarHandler(), pflag);
		return num.intValue();

	}

}
