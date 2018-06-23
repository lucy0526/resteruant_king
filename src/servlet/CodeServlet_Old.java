package servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.DinnerTable;
import java.io.IOException;  

import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  

import utils.EncoderHandler;
  
public class CodeServlet_Old extends BaseServlet {
	private static final long serialVersionUID = 1L;  
	protected String getTables(HttpServletRequest request, HttpServletResponse response) {
		 String content = request.getParameter("id");  
	        EncoderHandler encoder = new EncoderHandler();  
	        encoder.encoderQRCoder(content, response); 
		return uri = "/sys/detail/table/list.jsp";
	}
}
