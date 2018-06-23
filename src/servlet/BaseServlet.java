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
 * ʹ��ͨ�õ�Servlet�࣬����Servlet���̳и���
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
		//��ת��Դ
		//����ֵ
		Object returnValue = null;
		//��ò�������
		String methodName = request.getParameter("method");
		if(methodName==null){
			methodName = "listTable";
		}
		
		//��õ�ǰ��������ֽ���
		Class clazz = this.getClass();
		try {
			/*
			 * ��õ�ǰִ�з�����Method����
			 * ����һ:������
			 * ����������������
			 */
			Method method = clazz.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			/*
			 * ִ�з���
			 * ����һ��������������
			 * ����������������Ҫ�Ĳ���
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
