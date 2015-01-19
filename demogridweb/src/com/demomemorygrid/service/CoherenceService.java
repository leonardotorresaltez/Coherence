package com.demomemorygrid.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.demomemorygrid.domain.NodoPersona;
import com.demomemorygrid.domain.NodoPersonaKey;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.Filter;
import com.tangosol.util.InvocableMap.EntryAggregator;
import com.tangosol.util.aggregator.Count;
import com.tangosol.util.aggregator.LongMax;
import com.tangosol.util.extractor.ReflectionExtractor;
import com.tangosol.util.filter.AlwaysFilter;

public class CoherenceService extends AbstractService{

	public static NamedCache cacheNodoPersona = CacheFactory.getCache("NodoPersona-cache");
	
	
	@Override
	public int count() {
		// TODO Auto-generated method stub
		
		EntryAggregator aggregator = new Count(); 
	
		Object result = cacheNodoPersona.aggregate(AlwaysFilter.INSTANCE, aggregator);	
		return new Integer(result.toString());
	
	}
	
	public long menorEdad() {
		// TODO Auto-generated method stub
		
		EntryAggregator aggregator = new LongMax(new ReflectionExtractor("getAnoNacimiento")); 
	
		Object result = cacheNodoPersona.aggregate(AlwaysFilter.INSTANCE, aggregator);
		System.out.println("resutlado menorEdad =" + result);
		return new Integer(result.toString());
	
	}

	@Override
	public List<NodoPersona> followers(int idPersona) {
		
		List<NodoPersona> personaresults= new ArrayList<NodoPersona>();
		// TODO Auto-generated method stub
		NodoPersona source = (NodoPersona)cacheNodoPersona.get(new NodoPersonaKey(idPersona));
		List<NodoPersonaKey> listaNodosPersona =new ArrayList<NodoPersonaKey>();
		for ( Integer target : source.getConexiones())
		{
			listaNodosPersona.add(new NodoPersonaKey(target) );
		}
		personaresults.add(source);
		
		Map results = cacheNodoPersona.getAll(listaNodosPersona);
		Set<Entry> entries = results.entrySet();
		//personaresults.addAll(entries);
		for (Entry entry : entries) {
			personaresults.add((NodoPersona)entry.getValue());
		}
		
		
		return personaresults;
	}
	public static void main(String args[])
	{
		Filter filter=com.tangosol.util.QueryHelper.createFilter("descripcionTexto LIKE '%hmxa%' OR descripcionTexto LIKE '%dxk%' group by anoNacimiento"); 
		System.out.println(cacheNodoPersona.entrySet(filter).size());
	}

}
