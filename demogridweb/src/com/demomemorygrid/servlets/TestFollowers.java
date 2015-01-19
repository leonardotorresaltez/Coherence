package com.demomemorygrid.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demomemorygrid.domain.NodoPersona;
import com.demomemorygrid.service.CoherenceService;
import com.demomemorygrid.service.DatabaseService;

/**
 * Servlet implementation class TestFollowers
 */
@WebServlet("/TestFollowers")
public class TestFollowers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestFollowers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter writer = response.getWriter();
		writer.append("<h1>TestFollowers</h1>");
		long ini=0;
		Integer contador= new Integer(request.getParameter("contador"));
		if ( ("database".equals(request.getParameter("backend")) || null==request.getParameter("backend")))
		{
			ini= System.currentTimeMillis();
			writer.append("<p>BaseDatos");
			List<NodoPersona> nodosPersona= new DatabaseService().followers(contador);
			printListaPersona(nodosPersona,writer,contador);
			writer.append("<p>Tiempo="+(System.currentTimeMillis()-ini));
		}
		writer.append("<p>");
		
		if ( ("coherence".equals(request.getParameter("backend"))  || null==request.getParameter("backend")))
		{
			ini= System.currentTimeMillis();
			writer.append("<p>Coherence");
			List<NodoPersona> nodosPersona= new CoherenceService().followers(contador);
			printListaPersona(nodosPersona,writer,contador);
			writer.append("<p>Tiempo="+(System.currentTimeMillis()-ini));
		}		
	}

	
	private void printListaPersona(List<NodoPersona> nodosPersona,
			PrintWriter writer,Integer id) {
		for( NodoPersona persona :nodosPersona )
		{
			if( id == persona.getKey().getId())	writer.append("<span style='color: red;' />");
				
			writer.append("<p>[ID="+persona.getKey().getId()+"]"+
					          "[USUARIO="+persona.getUsername()+"]"+
							  "[GENERO="+persona.getGenero()+"]"+
					         "[AÑO NACIMIENTO="+persona.getAnoNacimiento()+"]"+
					         "[DESCRIPCIONTEXTO="+persona.getDescripcionTexto()+"]"+
					         "[OCUPACION="+persona.getOcupacion()+"]");
			if( id == persona.getKey().getId()) writer.append("</span>");
			
		}
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet( request,  response);
	}

}
