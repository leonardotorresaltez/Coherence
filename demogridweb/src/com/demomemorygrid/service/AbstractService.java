package com.demomemorygrid.service;

import java.util.List;

import com.demomemorygrid.domain.NodoPersona;

public abstract class AbstractService {

	public abstract int count() ;
	public abstract long menorEdad();
	public abstract List<NodoPersona> followers(int idPersona);
}
