package servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.IDinnerTableService;
import service.IOrderService;
import entity.DinnerTable;
import entity.Order;
import factory.BeanFactory;

public class DinnerTableServlet extends BaseServlet {

	protected String getTables(HttpServletRequest request, HttpServletResponse response) {
		String tableName = request.getParameter("keyword");
		
		List<DinnerTable> list = tableService.getTables(tableName);
		
		request.setAttribute("list", list);
		return uri = "/sys/detail/table/list.jsp";
	}
	protected String update(HttpServletRequest request, HttpServletResponse response) {
		int tableStatus = Integer.parseInt(request.getParameter("tableStatus"));
		if(tableStatus==1)
			tableStatus=0;
		else
			tableStatus=1;
		int id = Integer.parseInt(request.getParameter("id"));
		Date orderDate = new Date();
		DinnerTable table = new DinnerTable();
		table.setId(id);
		table.setTableStatus(tableStatus);
		table.setOrderDate(orderDate);
		tableService.update(table);
		
		return uri = "/tableServlet?method=list";
	}
	protected String delete(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		Order order = orderService.getOrderBy_dinnerTable_id(id);
		if(order==null){
			tableService.delete(id);
			uri = "/tableServlet?method=list";
		}
		else uri = "sys/error/deleteTable.html";
		
		return uri;
	}
	protected String add(HttpServletRequest request, HttpServletResponse response) {
		DinnerTable table = new DinnerTable();
		String tableName = request.getParameter("tableName");
		int tableStatus = 0;
		Date orderDate = new Date();
		table.setOrderDate(orderDate);
		table.setTableName(tableName);
		table.setTableStatus(tableStatus);
		tableService.add(table);
		return uri = "/tableServlet?method=list";
	}
	protected String  list(HttpServletRequest request, HttpServletResponse response) {
		List<DinnerTable> list = tableService.getTables();
		request.setAttribute("list", list);
		return uri = "/sys/detail/table/list.jsp";
	}

	protected String clientUpdate(HttpServletRequest request, HttpServletResponse response) {
		DinnerTable table = new DinnerTable();
		
		HttpSession session = request.getSession();
		table = (DinnerTable) session.getAttribute("dinnerTable");
		
		int tableStatus = 1;
		table.setTableStatus(tableStatus);
		tableService.update(table);
		
		return uri = "/orderDetailServlet?method=add";
	}

}
