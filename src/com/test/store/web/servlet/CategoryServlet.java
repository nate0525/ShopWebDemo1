package com.test.store.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.test.store.domain.Category;
import com.test.store.service.CategoryService;
import com.test.store.service.serviceImpl.CategoryServiceImpl;
import com.test.store.util.JedisUtils;
import com.test.store.web.base.BaseServlet;
import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;

public class CategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public String findAllCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/*Jedis jedis = JedisUtils.getJedis();

		String jsonStr = jedis.get("findAllCategory");
		
		System.out.println(jsonStr);

		if (null == jsonStr || "".equals(jsonStr)) {

			CategoryService categoryService = new CategoryServiceImpl();

			List<Category> findAllCategory = categoryService.findAllCategory();

			jsonStr = JSONArray.fromObject(findAllCategory).toString();
			//存入jedis
			jedis.set("findAllCategory", jsonStr);
			
			System.out.println("redis缓存里没有数据");

			response.setContentType("application/json;charset=utf-8");

			response.getWriter().print(jsonStr);

		} else {

			System.out.println("redis缓存里有数据");

			response.setContentType("application/json;charset=utf-8");

			response.getWriter().print(jsonStr);
		}

		JedisUtils.closeJedis(jedis);

		return null;*/
		
		CategoryService categoryService = new CategoryServiceImpl();
		
		List<Category> findAllCategory = categoryService.findAllCategory();
		
		String 	jsonStr = JSONArray.fromObject(findAllCategory).toString();
		
		response.setContentType("application/json;charset=utf-8");

		response.getWriter().print(jsonStr);
		
		return null;

	}

}
