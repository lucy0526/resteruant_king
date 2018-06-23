package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import service.IFoodService;
import service.IFoodTypeService;
import entity.Food;
import entity.FoodType;
import factory.BeanFactory;
/**
 * 菜系管理Servlet开发
 * 1、添加菜系
 * 2、菜系列表展示
 * 3、进入更新页面
 * 4、删除
 * @author asus
 *
 */
public class FoodTypeServlet extends BaseServlet {
	
	protected String getFoodType(HttpServletRequest request, HttpServletResponse response) {
		
		String typeName = request.getParameter("keyword");
		List<FoodType> list = foodTypeService.getFoodTypes(typeName);
		request.setAttribute("list", list);
		return uri = "/sys/detail/foodType/list.jsp";
	}
	protected String delete(HttpServletRequest request, HttpServletResponse response) {
		try {
			Food food = foodService.getFoodByFoodType_id(Integer.parseInt(request.getParameter("id")));
			if(food == null)
				foodTypeService.deleteFoodType(Integer.parseInt(request.getParameter("id")));
			else 
				throw new RuntimeException();
			
			return uri = "/foodTypeServlet?method=list";
		} catch (Exception e) {
			return uri = "/sys/error/deleteFoodType.html";
		}
	}
	protected String update(HttpServletRequest request, HttpServletResponse response) {
		//			int id = Integer.parseInt(request.getParameter("id"));
//					String name = request.getParameter("foodTypeName");
					FoodType foodType = new FoodType();
					try {
						BeanUtils.populate(foodType, request.getParameterMap());
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
					foodTypeService.updataFoodType(foodType);
					return uri = "/foodTypeServlet?method=list";
	}
	protected String updateView(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		FoodType ft = foodTypeService.getFoodType(Integer.parseInt(id));
		request.setAttribute("foodType", ft);
		return uri = "/sys/detail/foodType/updateFoodType.jsp";
	}
	protected String list(HttpServletRequest request, HttpServletResponse response) {
		List<FoodType> list = foodTypeService.getFoodTypes();
		request.setAttribute("list", list);
		try {
			return uri = "sys/detail/foodType/list.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			return uri = "sys/error/";
		}
	}
	protected String addFoodType(HttpServletRequest request, HttpServletResponse response) {
		//获取请求数据封装
		String typeName = request.getParameter("foodTypeName");
		FoodType ft = new FoodType();
		ft.setTypeName(typeName);
		//调用请求数据
		foodTypeService.addFoodType(ft);
		try {
			return uri = "/foodTypeServlet?method=list";
		} catch (Exception e) {
			e.printStackTrace();
			return uri = "/sys/error/error.html";
		}
	}
}
