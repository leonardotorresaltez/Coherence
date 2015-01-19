package com.demomemorygrid.loaders;

import java.sql.Connection;
import java.util.Random;


import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class PriocessLine{
	
	public Random random ;
	Connection conn;
	
	public PriocessLine(Connection conn)
	{
		random = new Random();
		this.conn=conn;
	}
	public void process(String line)
	{


	}
	
}
