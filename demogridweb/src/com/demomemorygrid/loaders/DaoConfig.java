package com.demomemorygrid.loaders;






import java.sql.*;

public class DaoConfig {

    
    public static String driver = "com.mysql.jdbc.Driver";
    public static String protocol = "jdbc:mysql://localhost:3306/social_profiles_db";    
    
	private static Connection  conn = null;

	public static Connection getConnection() {

           
           
           
          try{
               if ((conn!=null)&&(!conn.isClosed())) return conn;
              
                Class.forName(driver).newInstance();//new com.ibm.db2.jcc.DB2Driver(); 
                System.out.println("El driver JDBC esta cargado");



                 conn =DriverManager.getConnection(protocol  , "root" ,"");
                 System.out.println("La conexion a la base de datos a sido obtenida");

                 return conn;
          }catch(Exception e)
          {
              e.printStackTrace();
        	  System.out.println("Error en la conexion");
          }
          return conn;

	}
	
	public static void main(String args[])
	{
		getConnection();
	}


}