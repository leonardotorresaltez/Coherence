package com.demomemorygrid.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demomemorygrid.service.AbstractService;
import com.demomemorygrid.service.CoherenceService;
import com.demomemorygrid.service.DatabaseService;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.InvocableMap.EntryAggregator;
import com.tangosol.util.aggregator.Count;
import com.tangosol.util.filter.AlwaysFilter;

/**
 * Servlet implementation class TestCount
 */
@WebServlet("/TestCount")
public class TestCount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestCount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter writer = response.getWriter();
		writer.append("<h1>TestCount</h1>");
		long ini=0;
		
		if ( ("database".equals(request.getParameter("backend")) || null==request.getParameter("backend")))
		{
			ini= System.currentTimeMillis();
			writer.append("<p>BaseDatos="+new DatabaseService().count());
			writer.append("<p>Tiempo="+(System.currentTimeMillis()-ini));
		}
		writer.append("<p>");
		
		if ( ("coherence".equals(request.getParameter("backend"))  || null==request.getParameter("backend")))
		{
			ini= System.currentTimeMillis();
			writer.append("<p>Coherence="+new CoherenceService().count());
			writer.append("<p>Tiempo="+(System.currentTimeMillis()-ini));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet( request,  response);
	}

}
