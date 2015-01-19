package com.demomemorygrid.domain;

import com.tangosol.io.pof.annotation.Portable;
import com.tangosol.io.pof.annotation.PortableProperty;

@Portable
public class NodoPersonaKey {


	
	@PortableProperty(0)
	private int id;

	public NodoPersonaKey(int id){
		this.id=id;
	}
	
	public NodoPersonaKey()
	{
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NodoPersonaKey other = (NodoPersonaKey) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NodoPersonaKey [id=" + id + "]";
	}
	
}
