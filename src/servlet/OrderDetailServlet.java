package servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Food;
import entity.Order;
import entity.OrderDetail;

public class OrderDetailServlet extends BaseServlet {

	protected String list(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		List<OrderDetail> list = orderDetailService.getBy_order_id(id);
		
		for(OrderDetail orderDetail:list)
		{
			int food_id = orderDetail.getFood_id();
			Food food = foodService.getFood(food_id);
			String food_name = food.getFoodName();
			
			orderDetail.setFood_name(food_name);
			orderDetail.setFood_price(food.getPrice());
		}
		request.setAttribute("list", list);
		return uri = "sys/detail/orderDetail/orderDetail.jsp";
	}
	protected String add(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Order order = (Order) session.getAttribute("order");
		List<Food> orderList = (List<Food>) session.getAttribute("orderList");
		
		for(Food food:orderList)
		{
			OrderDetail orderDetail = new OrderDetail();
			
			orderDetail.setDinnerOrder_id(order.getId());
			orderDetail.setFood_id(food.getId());
			orderDetail.setFoodCount(food.getNum());
			orderDetailService.add(orderDetail);
		}
		
		return uri = "/app/detail/jiezhang.jsp";
	}
}
