package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.sun.org.apache.regexp.internal.recompile;

import service.IFoodService;
import service.IFoodTypeService;
import entity.Condition;
import entity.Food;
import entity.FoodType;
import entity.PageBean;
import factory.BeanFactory;

public class FoodServlet extends BaseServlet {
	protected String get(HttpServletRequest request, HttpServletResponse response) {
		String foodName = request.getParameter("keyword");
		List<Food> list = foodService.getFoods(foodName);
		request.setAttribute("list", list);
		return uri = "/sys/detail/food/list.jsp";
	}

	protected String update(HttpServletRequest request, HttpServletResponse response) {
		try {
			Food food = getFood(request);
			foodService.update(food);
			return uri = "foodServlet?method=list";
		}catch (Exception e) {
			e.printStackTrace();
			return uri = "error/error.html";
		}
	}

	protected String updateView(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		Food food = foodService.getFood(id);
		request.setAttribute("food", food);
		List<FoodType> list = foodTypeService.getFoodTypes();
		request.setAttribute("list", list);
		return uri = "sys/detail/food/update.jsp";
	}

	protected String delete(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		foodService.delete(id);
		return uri = "foodServlet?method=list";
	}

	protected String add(HttpServletRequest request, HttpServletResponse response) {
		try {
			Food food = getFood(request);
			foodService.add(food);
			return uri = "foodServlet?method=list";
		}catch (Exception e) {
			e.printStackTrace();
			return uri = "error/error.html";
		}
	}

	protected Food getFood(HttpServletRequest request)
			throws FileUploadException, IllegalAccessException,
			InvocationTargetException, UnsupportedEncodingException {
		Food food = new Food();
		FileItemFactory fac = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(fac);
		List<FileItem> fileItems = null;//得到所有属性参数
		Map<String, String> map = new HashMap<String, String>();
		fileItems = upload.parseRequest(request);
		for(FileItem item:fileItems)
		{
			if(item.isFormField())//不是文件 
			{
				String fileName = item.getFieldName();
				String fileValue = item.getString();
				map.put(fileName, fileValue);
			}
			else
			{
				String fileName = item.getName();//获得文件名
				food.setImg(fileName);
			}
		}
		BeanUtils.populate(food, map);
		String name = new String(food.getFoodName().getBytes("iso8859-1"), "utf-8");
		String introduce = new String(food.getIntroduce().getBytes("iso8859-1"), "utf-8");
		food.setFoodName(name);
		food.setIntroduce(introduce);
		if("".equals(food.getImg().trim()))
			food.setImg(foodService.getFood(food.getId()).getImg());
		return food;
	}

	protected String addView(HttpServletRequest request, HttpServletResponse response) {
		List<FoodType> list = foodTypeService.getFoodTypes();
		request.setAttribute("list", list);
		return uri = "/sys/detail/food/add.jsp";
	}

	protected String list(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		PageBean<Food> pb = new PageBean<Food>();
		String curPage = request.getParameter("currentPage");
		if(curPage == null || "".equals(curPage.trim())){
			pb.setCurrentPage(1);
		}
		else{
			pb.setCurrentPage(Integer.parseInt(curPage));
		}
		Condition condition = new Condition();
		/*
		 * 
		 */
		String foodName = request.getParameter("keyword");
		if("1".equals(request.getParameter("keywordlib")))
		{
			foodName = (String) session.getAttribute("keyword");
		}
		if((foodName != null) && (foodName.trim() != ""))
		{
			session.setAttribute("keyword", foodName);
			
			request.setAttribute("keywordlib", 1);
			
			condition.setFoodName(foodName);
			pb.setCurrentPage(1);
		}
		
		
		pb.setCondition(condition);//把条件设置到页面对象中
		foodService.getAll(pb);
		request.setAttribute("pb", pb);//将页面对象保存
		
		return uri = "/sys/detail/food/list.jsp";
	}
}
