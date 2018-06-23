package servlet;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import utils.EncoderHandler;

import entity.DinnerTable;
import entity.Order;
import entity.OrderCondition;
import entity.PageBean;

public class OrderServlet extends BaseServlet {
	protected String add(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		DinnerTable table = (DinnerTable) session.getAttribute("dinnerTable");
		int id = 0;
		if(table!=null)
			id = table.getId();
		Date orderDate = new Date();
		Order order = new Order();
		
		order.setDinnerTable_id(id);
		order.setOrderDate(orderDate);
		String t = request.getParameter("totalPrice");
		
		double totalPrice = Double.parseDouble(t);
		
		order.setTotalPrice(totalPrice);
		order.setOrderStatus(0);
		orderService.add(order);
		
		table.setOrderDate(orderDate);
		
		Order order1 = orderService.getOrderBy_orderDate(orderDate);
		session.setAttribute("order", order1);
		
		/*String content = request.getParameter("id");  
        EncoderHandler encoder = new EncoderHandler();  
        encoder.encoderQRCoder(content, response); */
		
		return uri = "/tableServlet?method=clientUpdate";
	}

	protected String update(HttpServletRequest request, HttpServletResponse response) {
		Order order = new Order();
		int id = Integer.parseInt(request.getParameter("id"));
		
		int orderStatus = 1;
		order.setId(id);
		order.setOrderStatus(orderStatus);
		orderService.update(order);
		return uri = "orderServlet?method=list";
	}

	protected String list(HttpServletRequest request, HttpServletResponse response){
		
		HttpSession session = request.getSession();
		PageBean<Order> pb = new PageBean<Order>();
		String curPage = request.getParameter("currentPage");
		if(curPage == null || "".equals(curPage.trim())){
			pb.setCurrentPage(1);
		}
		else{
			pb.setCurrentPage(Integer.parseInt(curPage));
		}
		
		OrderCondition orderCondition = (OrderCondition) session.getAttribute("orderCondition");
		
		//init
		if( (!"sel".equals(request.getParameter("myTag"))) && (!"y".equals(request.getParameter("myTag"))) ){
			orderCondition = null;
		}
		//sel
		if("y".equals(request.getParameter("myTag"))){
			try {
				orderCondition = new OrderCondition();
				BeanUtils.populate(orderCondition, request.getParameterMap());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//init_sel
		else{
			orderService.getLimitOrders(pb);
		}
		//sel_sel
		if(orderCondition != null)
		{
			orderService.selectOrders(pb, orderCondition);
			session.setAttribute("orderCondition", orderCondition);
		}
		
		request.setAttribute("pb", pb);
		List<Order> list = pb.getPageData();
		for(Order order:list)
		{
			order.setDinnerTable_name(tableService.getTableBy_id(order.getDinnerTable_id()).getTableName());
		}
		request.setAttribute("list", list);
		return uri = "sys/detail/order/list.jsp";
	}
	
	
	
}
