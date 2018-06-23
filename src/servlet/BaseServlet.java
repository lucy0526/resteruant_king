package servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.IDinnerTableService;
import service.IFoodService;
import service.IFoodTypeService;
import service.IOrderDetailService;
import service.IOrderService;
import factory.BeanFactory;

/**
 * 使用通用的Servlet类，所有Servlet都继承该类
 * @author asus
 *
 */
public class BaseServlet extends HttpServlet {
	protected IDinnerTableService tableService = BeanFactory.getInstance("DinnerTableService", IDinnerTableService.class);
	protected IOrderService orderService = BeanFactory.getInstance("OrderService", IOrderService.class);
	protected IFoodService foodService = BeanFactory.getInstance("FoodService", IFoodService.class);
	protected IFoodTypeService foodTypeService = BeanFactory.getInstance("FoodTypeService", IFoodTypeService.class);
	protected IOrderDetailService orderDetailService = BeanFactory.getInstance("OrderDetailService", IOrderDetailService.class);
	
	protected String uri = "";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("text/html;charset=UTF-8");
		//跳转资源
		//返回值
		Object returnValue = null;
		//获得操作名称
		String methodName = request.getParameter("method");
		if(methodName==null){
			methodName = "listTable";
		}
		
		//获得当前运行类的字节码
		Class clazz = this.getClass();
		try {
			/*
			 * 获得当前执行方法的Method类型
			 * 参数一:方法名
			 * 参数二：参数类型
			 */
			Method method = clazz.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			/*
			 * 执行方法
			 * 参数一：方法所属对象？
			 * 参数二：方法中需要的参数
			 */
			returnValue = method.invoke(this, request, response);
			uri = (String)returnValue;
		}catch (Exception e) {
			e.printStackTrace();
			uri = "app/error/error.html";
		}
		request.getRequestDispatcher(uri).forward(request, response);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
