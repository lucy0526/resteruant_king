package servlet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Condition;
import entity.DinnerTable;
import entity.Food;
import entity.FoodType;
import entity.PageBean;

public class IndexServlet extends BaseServlet {
	protected String listTable(HttpServletRequest request, HttpServletResponse response) {
		List<DinnerTable> list = tableService.getTableBy_TableStatus();
		request.setAttribute("listDinnerTable", list);
		uri = "/app/index.jsp";
		return uri;
	}
	/**
	 * ������ҳ����ʾ��Ʒ����ϵ
	 * @param request
	 * @param response
	 * @return
	 */
	protected String indexDetail(HttpServletRequest request, HttpServletResponse response) {
		/*
		 * 1����ȡ����id�����浽session�����ɶ�����ʱ��ʹ�ã�
		 * ע�⣺ÿ�λỰֻ��Ҫִ��һ�μ��ɣ�����Ҫ������
		 * ����������session�л�ȡ����û�в�������
		 * ����оͲ�ִ��������ѯ
		 */
		HttpSession session = request.getSession();
		DinnerTable table = (DinnerTable) session.getAttribute("dinnerTable");
		
		
		if(request.getParameter("table_id") != null)
		{
			int table_id = Integer.parseInt(request.getParameter("table_id"));
			
			if(table == null || table.getId()!=table_id){
				DinnerTable dt = tableService.getTableBy_id(table_id);
				//������session
				request.getSession().setAttribute("dinnerTable", dt);
				session.setAttribute("orderList", null);
			}
		}
		
		
		
		
		/*2����ҳ ��ѯ���еĲ�Ʒ������
		 */
		PageBean<Food> pb = new PageBean<Food>();
		String curPage = request.getParameter("currentPage");
		//��һ�η��ʣ����õ�ǰҳΪ1
		if(curPage == null || "".equals(curPage.trim())){
			pb.setCurrentPage(1);
		}
		else{
			pb.setCurrentPage(Integer.parseInt(curPage));
		}
		//��������
		Condition condition = new Condition();
		//��ҳ����1����ϵid
		String foodType_id = request.getParameter("foodType_id");
		
		if(foodType_id != null && !"".equals(foodType_id.trim())){
			condition.setFoodType_id(Integer.parseInt(foodType_id));
			request.setAttribute("curFoodType_id", foodType_id);
		}
		//��ҳ����2��������
		String foodName = request.getParameter("foodName");
		if(foodName != null){
			condition.setFoodName(foodName);
		}
		pb.setCondition(condition);//���������õ�ҳ�������
		
		foodService.getAll(pb);
//		request.setAttribute("pb", pb);//��ҳ����󱣴�
		session.setAttribute("pb", pb);
		
		/*
		 * 3����ѯ���еĲ�ϵ������
		 */
		List<FoodType> listFoodType = foodTypeService.getFoodTypes();
		request.setAttribute("listFoodType", listFoodType);
		
		uri = "/app/detail/caidan.jsp";
		return uri;
	}

	protected String foodDetail(HttpServletRequest request, HttpServletResponse response){
		int id = Integer.parseInt(request.getParameter("id"));
		Food food = foodService.getFood(id);
		request.setAttribute("food", food);
		
		List<FoodType> listFoodType = foodTypeService.getFoodTypes();
		request.setAttribute("listFoodType", listFoodType);
		uri = "/app/detail/caidan.jsp";
		
		return uri;
	}
	protected String clientCart(HttpServletRequest request, HttpServletResponse response){
		int id = Integer.parseInt(request.getParameter("id"));
		Food food = foodService.getFood(id);
		request.setAttribute("food", food);
		
		List<FoodType> listFoodType = foodTypeService.getFoodTypes();
		request.setAttribute("listFoodType", listFoodType);
		
		uri = "/app/detail/clientCart.jsp";
		return uri;
	}
	protected String clientOrderList(HttpServletRequest request, HttpServletResponse response){
		int id = Integer.parseInt(request.getParameter("id"));
		int num = Integer.parseInt(request.getParameter("num"));
		Food food = foodService.getFood(id);
		food.setNum(num);
		
		HttpSession session = request.getSession();
		List<Food> orderList = (List<Food>) session.getAttribute("orderList");
		if(orderList==null)
		{
			orderList = new ArrayList<Food>();
			orderList.add(food);
			request.getSession().setAttribute("orderList", orderList);
		}
		else
			orderList.add(food);
		
		double totalPrice = 0;
		for(Food f:orderList)
		{
			totalPrice += f.getPrice()*f.getNum();
		}
		totalPrice = (double)Math.round(totalPrice*100)/100;  
		request.setAttribute("totalPrice", totalPrice);
		
		List<FoodType> listFoodType = foodTypeService.getFoodTypes();
		request.setAttribute("listFoodType", listFoodType);
		
		String tag = request.getParameter("tag");
		request.setAttribute("foodNum", num);
		uri = "/app/detail/caidan.jsp";
		return uri;
	}
	protected String orderListDele(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		List<Food> orderList = (List<Food>) session.getAttribute("orderList");
		int id = Integer.parseInt(request.getParameter("id"));
		Iterator<Food> it = orderList.iterator();
		while(it.hasNext())
		{
			Food f = it.next();
			if(f.getId()==id)
				it.remove();
		}
		
		double totalPrice = 0;
		for(Food f:orderList)
		{
			totalPrice += f.getPrice()*f.getNum();
		}
		totalPrice = (double)Math.round(totalPrice*100)/100;  
		request.setAttribute("totalPrice", totalPrice);
		
		List<FoodType> listFoodType = foodTypeService.getFoodTypes();
		request.setAttribute("listFoodType", listFoodType);
		
		uri = "/app/detail/clientOrderList.jsp";
		return uri;
	}
	protected String orderList(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		List<Food> orderList = (List<Food>) session.getAttribute("orderList");
		if(orderList == null)
			orderList = new ArrayList<Food>();
		double totalPrice = 0;
		for(Food f:orderList)
		{
			totalPrice += f.getPrice()*f.getNum();
		}
		totalPrice = (double)Math.round(totalPrice*100)/100;  
		request.setAttribute("totalPrice", totalPrice);
		
		List<FoodType> listFoodType = foodTypeService.getFoodTypes();
		request.setAttribute("listFoodType", listFoodType);
		uri = "/app/detail/clientOrderList.jsp";
		return uri;
	}
}
