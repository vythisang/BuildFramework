package com.laptrinhjavaweb.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/admin-building"})
public class BuildingController extends  HttpServlet{

	private static final long serialVersionUID = 8964262518551795756L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		String url="";
		if(action != null && action.equals("LIST")) {
			url = "/views/admin/building/list.jsp";
		}else if( action != null && action.equals("EDIT")) {
			url = "/views/admin/building/edit.jsp";
		}
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, resp);
		
	
	}
	
	
}
