package com.demomemorygrid.domain;


import java.util.*;

import com.tangosol.io.pof.annotation.Portable;
import com.tangosol.io.pof.annotation.PortableProperty;

@Portable
public class NodoPersona implements  Entidad<NodoPersonaKey>{

 
	  @PortableProperty(0)
	  private String genero;
	  
	  @PortableProperty(1)
	  private int anoNacimiento;
	  
	  @PortableProperty(2)
	  private Ocupacion ocupacion;
	  
	  @PortableProperty(3)
	  private List<Intereses> intereses;
	  
	  @PortableProperty(4)
	  private String descripcionTexto ; 
	  
	  @PortableProperty(5)
	  private NodoPersonaKey key ;
	  
	  @PortableProperty(6)
	  private int parent = Integer.MAX_VALUE;

	  @PortableProperty(7)
	  private int distance = Integer.MAX_VALUE;
		  
	  @PortableProperty(8)
	  private List<Integer> conexiones = null;
	  
	  @PortableProperty(9)  
	  private Estado estado = Estado.WHITE;
	  
	  
	  @PortableProperty(9)  
	  private String username ;
	  
	  public NodoPersona( ) {
		  conexiones=new ArrayList<Integer>();
	  }
	  
	  public NodoPersona(NodoPersonaKey key) {
		    this.key = key;
		    conexiones=new ArrayList<Integer>();
		  
	  }
	  

	  public NodoPersonaKey getKey()
	  {
	    return this.key;
	  }
	  
	  public void addConexion(Integer target)
	  {
		  conexiones.add(target);
	  }
	  
	public int getParent() {
		
		return parent;
		
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}




	public List<Integer> getConexiones() {
		return conexiones;
	}

	public void setConexiones(List<Integer> conexiones) {
		this.conexiones = conexiones;
	}





	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}




	public Ocupacion getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(Ocupacion ocupacion) {
		this.ocupacion = ocupacion;
	}

	public List<Intereses> getIntereses() {
		return intereses;
	}

	public void setIntereses(List<Intereses> intereses) {
		this.intereses = intereses;
	}

	public String getDescripcionTexto() {
		return descripcionTexto;
	}

	public void setDescripcionTexto(String descripcionTexto) {
		this.descripcionTexto = descripcionTexto;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAnoNacimiento() {
		return anoNacimiento;
	}

	public void setAnoNacimiento(int anoNacimiento) {
		this.anoNacimiento = anoNacimiento;
	}
	  
}
