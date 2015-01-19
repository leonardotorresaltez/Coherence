package com.demomemorygrid.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demomemorygrid.loaders.LoadFromDataBaseToCoherence;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

/**
 * Servlet implementation class LoaderServlet
 */
@WebServlet(value="/LoaderServlet",loadOnStartup=1)
public class LoaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoaderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		//LoadFromDataBaseToCoherence.main(null);
		NamedCache cacheNodoPersona = CacheFactory.getCache("NodoPersona-cache");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//init(null);
		LoadFromDataBaseToCoherence.main(null);
		PrintWriter writer = response.getWriter();
		writer.append("<h1>Cargado OK</h1>");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
