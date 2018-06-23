package servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.DinnerTable;
import java.io.IOException;  
import java.lang.reflect.Method;

import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  

import utils.EncoderHandler;
  
public class CodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;  
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String content = request.getParameter("id");  
        EncoderHandler encoder = new EncoderHandler();  
        encoder.encoderQRCoder(content, response); 
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	this.doGet(req, resp);
	}
}
