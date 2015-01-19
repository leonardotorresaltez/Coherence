package com.demomemorygrid.loaders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import com.demomemorygrid.domain.NodoPersona;
import com.demomemorygrid.domain.NodoPersonaKey;



public class LoadFromFileToDataBase {
	
	
	public static void loadNodosPersona(Connection conn)
	{
		loadFile("./WebContent/data/socialgraph/nodos.csv",new PriocessLine(conn)
		{
		
			public void process(String line)
			{
				String[] datos = line.split(";");
				 
				String id = datos[0];
				String  nombre  = datos[1];
				//System.out.println("Persona [id= " + id 
	            //                     + " , label=" + nombre + "]");
				
				NodoPersonaKey nodoPersonaKey= new NodoPersonaKey(new Integer(id));
				NodoPersona nodoNopersona= new NodoPersona(nodoPersonaKey);
				nodoNopersona.setUsername(nombre);
				try
				{
					insert(nodoNopersona,conn);
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}

			}
			
			public void insert(NodoPersona nodoNopersona,Connection conn) throws SQLException{
				
                PreparedStatement pre=null;
            
                String sql = "insert into NODO_PERSONA (ID,USERNAME,GENERO,ANONACIMIENTO,OCUPACION,DESCRIPCIONTEXTO) "
                		+ "values (?,?,?,?,?,?)";   
                pre=conn.prepareStatement(sql); 
                
                pre.setInt(1, nodoNopersona.getKey().getId());
                pre.setString(2, nodoNopersona.getUsername());
                
                String []generos = new String []{"M","F"};
                pre.setString(3, generos[random.nextInt(2)]);
                
                int anonacimiento = 1970 + random.nextInt(30);
                pre.setInt(4, anonacimiento); //añonacimiento
                
               
                pre.setInt(5, random.nextInt(5)); //ocupacion
                
                String abecedario ="abcdefghijklmnopqxyz";
               
                String descripcion ="";
                int fin1 = random.nextInt(30);
                for ( int i =0 ; i < fin1;i++)
                {
                    String palabra ="";
                	int fin2= random.nextInt(6);
                    for ( int j =0 ; j < fin2;j++)
                    {
                    	palabra+=abecedario.charAt(random.nextInt(20));
                    }
                    
                	descripcion += " "+palabra;
                }
                pre.setString(6, descripcion); //descripcion texto
                
                pre.execute();
			}
			
		});
	}
	
	public static void loadConexiones(Connection conn){
		loadFile("./WebContent/data/socialgraph/lados.csv",new PriocessLine(conn)
		{
		
			public void process(String line)
			{
				String[] datos = line.split(";");
				 
				
				String  source  = datos[0];
				String  target  = datos[1];
				String type = datos[2];
				String id = datos[3];
				String label = datos[4];
				String weigth = datos[5];
				
				
				System.out.println("Conexiones [" 
									+ " source= " + source +","
									+ " target= " + target  +","
									+ " type= " + type  +","
									+ " id= " + id  +","
									+ " label= " + label  +","
	                                 + " weigth=" + weigth + "]");
				
				//NodoPersona nodoNopersona= new NodoPersona(new Integer(id));
				//nodoNopersona.setUsername(nombre);
				try
				{
					insert(datos,conn);
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}

			}
			
			public void insert(String[] datos,Connection conn) throws SQLException{
				
                PreparedStatement pre=null;
            
                String sql = "insert into CONEXIONES (ID,SOURCE,TARGET) "
                		+ "values (?,?,?)";   
                pre=conn.prepareStatement(sql); 
                
                //ID
                pre.setInt(1, new Integer(datos[3]));
                //SOURCE
                pre.setInt(2, new Integer(datos[0]));
                //TARGET
                pre.setInt(3, new Integer(datos[1]));
              
                
                pre.execute();
			}
			
		});		
				
	}

	public static void main(String args[])
	{
		Connection conn= DaoConfig.getConnection();
		
		
		//LOAD NODOS
		loadNodosPersona(conn);
		
		//CONEXIONES DE NODOS
		//loadConexiones(conn);
		
		try{
		conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public static void loadFile(String csvFile, PriocessLine p)
	{
		
		BufferedReader br = null;
		String line = "";
	
		
		try {
			 
			br = new BufferedReader(new FileReader(csvFile));
			line = br.readLine(); // eliminar primera linea
			long count=0;
			while ((line = br.readLine()) != null) {
	 
				p.process(line);
			        // use comma as separator
			   	if (count%100==0)
            	{
            		System.out.println("count = "+count);
            	}
				count++;
				

			}
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	 
		System.out.println("Done");
	}

}
