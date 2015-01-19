package com.demomemorygrid.loaders;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;







import java.util.HashMap;
import java.util.Map;

import com.demomemorygrid.domain.NodoPersona;
import com.demomemorygrid.domain.NodoPersonaKey;
import com.demomemorygrid.domain.Ocupacion;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.extractor.ChainedExtractor;
import com.tangosol.util.extractor.ReflectionExtractor;

public class LoadFromDataBaseToCoherence {
	
	public static void main(String args[])
	{
		Connection conn= DaoConfig.getConnection();
		
		loadRecordsFromDB(conn);
		
		try{
			conn.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	}
	
	public static void loadRecordsFromDB(Connection conn)
	{
		
		
        try         
        {
            String sqlNodo= "SELECT ID, GENERO, ANONACIMIENTO, OCUPACION, DESCRIPCIONTEXTO, PARENT, DISTANCE, USERNAME FROM nodo_persona";
            
            PreparedStatement pre=null;
            ResultSet rs = null;

            pre=conn.prepareStatement(sqlNodo); 
            rs = pre.executeQuery();
            
        	NamedCache cacheNodoPersona = CacheFactory.getCache("NodoPersona-cache");
        	cacheNodoPersona.clear();
            Map hashmap=new HashMap();
            long count=0;
            while(rs.next())
            {
                
            	 
            	Integer id =  new Integer(rs.getInt("ID"));
            	NodoPersona nodoPersona = new NodoPersona(new NodoPersonaKey(id));
            	
                String genero =  rs.getString("GENERO");
                nodoPersona.setGenero(genero);
                
                String anonacimiento =  rs.getString("ANONACIMIENTO");
                nodoPersona.setAnoNacimiento(new Integer(anonacimiento));
                
                Integer indexocupacion = new Integer(rs.getInt("OCUPACION"));
                Ocupacion ocup = Ocupacion.values()[indexocupacion];
                nodoPersona.setOcupacion(ocup);
                
                String descripcionTexto =  rs.getString("DESCRIPCIONTEXTO");
                nodoPersona.setDescripcionTexto(descripcionTexto);
                
                
                String username =  rs.getString("USERNAME");
                nodoPersona.setUsername(username);
                
                
                {
                	String sqlConn= "SELECT ID, SOURCE, TARGET FROM conexiones WHERE SOURCE= "+id;
                	PreparedStatement pre2=null;
	                ResultSet rsConexiones = null;
	
	                pre2=conn.prepareStatement(sqlConn); 
	                rsConexiones = pre2.executeQuery();
	                
	                while(rsConexiones.next())
	                {
	                	Integer target =  new Integer(rsConexiones.getInt("TARGET"));
	                	nodoPersona.addConexion(target);
	                }
                }
                
//            	System.out.println("NodoPersona [" 
//						+ " id= " + nodoPersona.getKey().getId() +","
//						+ " genero= " + nodoPersona.getGenero()  +","
//						+ " username = " + nodoPersona.getUsername()  +","
//						+ " ANONACIMIENTO= " + nodoPersona.getAnoNacimiento()  +","
//						+ " OCUPACION= " + nodoPersona.getOcupacion()  +","
//						+ " conexiones= " + nodoPersona.getConexiones()  +","
//						+ " DESCRIPCIONTEXTO= " + nodoPersona.getDescripcionTexto()  + "]");
                      
            	hashmap.put(nodoPersona.getKey(), nodoPersona);
        	
            	if (count%100==0)
            	{
            		cacheNodoPersona.putAll(hashmap);	
            		//System.out.println("count = "+count);
            		System.out.println("size= "+cacheNodoPersona.size());
            		
            		hashmap.clear();
            	}
            	count++;
            }
            
            cacheNodoPersona.putAll(hashmap);	
            hashmap.clear();
            
            System.out.println("Creando los índices..");
            cacheNodoPersona.addIndex(new ReflectionExtractor("getGenero"), false, null);
            cacheNodoPersona.addIndex(new ReflectionExtractor("getAnoNacimiento"), false, null);
            cacheNodoPersona.addIndex(new ReflectionExtractor("getOcupacion"), false, null);
            System.out.println("Realizado");
         
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
		
	
	}
	


}
