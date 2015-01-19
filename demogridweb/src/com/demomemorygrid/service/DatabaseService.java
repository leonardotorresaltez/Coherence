package com.demomemorygrid.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.demomemorygrid.domain.NodoPersona;
import com.demomemorygrid.domain.NodoPersonaKey;
import com.demomemorygrid.domain.Ocupacion;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class DatabaseService extends AbstractService {

	private InitialContext cxt ;
	private DataSource ds=null ;
	
	public DatabaseService()
	{
		try
		{ 
		  cxt = new InitialContext();
		 ds = (DataSource) cxt.lookup( "java:/comp/env/jdbc/social_profiles_db" );
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public int count()  {
		// TODO Auto-generated method stub
		int count =0;
		Connection conn = null;
		try
		{

			conn = ds.getConnection();
			 String sqlNodo= "SELECT count(*) FROM nodo_persona";
	            
	            PreparedStatement pre=null;
	            ResultSet rs = null;

	            pre=conn.prepareStatement(sqlNodo); 
	            rs = pre.executeQuery();
	            
            
	            while(rs.next())
	            {
	            	count=rs.getInt(1);
	            }
	             
	            	 
	            	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			try
			{
				if (conn!=null && !conn.isClosed() ) conn.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return count;
	}

	@Override
	public long menorEdad() {
		// TODO Auto-generated method stub
		int count =0;
		Connection conn = null;
		try
		{

			conn = ds.getConnection();
			 String sqlNodo= "SELECT max(ANONACIMIENTO) FROM nodo_persona";
	            
	            PreparedStatement pre=null;
	            ResultSet rs = null;

	            pre=conn.prepareStatement(sqlNodo); 
	            rs = pre.executeQuery();
	            
            
	            while(rs.next())
	            {
	            	count=rs.getInt(1);
	            }
	             
	            	 
	            	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			try
			{
				if (conn!=null && !conn.isClosed() ) conn.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return (long)count;
	}
	
	
	public List<NodoPersona> followers(int idPersona) {
		// TODO Auto-generated method stub
		
		List<NodoPersona> result = new ArrayList<NodoPersona>(); 
	
		Connection conn = null;
		try
		{

			conn = ds.getConnection();
			 String sqlNodo= "SELECT nodo_persona.* FROM nodo_persona,conexiones "+
							 "where "+
							"conexiones.target = nodo_persona.id "+
							"and " +
							"conexiones.source=? " +
							"union "+
							" SELECT nodo_persona.* FROM nodo_persona where nodo_persona.id=? ";
	            
	            PreparedStatement pre=null;
	            ResultSet rs = null;

	            pre=conn.prepareStatement(sqlNodo);
	            pre.setInt(1, idPersona);
	            pre.setInt(2, idPersona);
	            rs = pre.executeQuery();
	            
            
	            while(rs.next())
	            {
	            	
	            	int id = rs.getInt("ID");
	            	String genero = rs.getString("GENERO");
	            	int ano= rs.getInt("ANONACIMIENTO");
	            	String descripcionTexto = rs.getString("DESCRIPCIONTEXTO");
	            	int ocupacion= rs.getInt("OCUPACION");
	            	String username = rs.getString("USERNAME");
	            	
	            	NodoPersona persona = new NodoPersona(new NodoPersonaKey(id));
	            	persona.setGenero(genero);
	            	persona.setAnoNacimiento(ano);
	            	persona.setDescripcionTexto(descripcionTexto);
	            	Ocupacion ocup = Ocupacion.values()[ocupacion];
	            	persona.setOcupacion(ocup);
	            	persona.setUsername(username);

	            	result.add(persona);
	            	
	            }
	             
	            	 
	            	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			try
			{
				if (conn!=null && !conn.isClosed() ) conn.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return result;
	}	

}
