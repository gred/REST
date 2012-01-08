package org.rest.persistence.service;

import java.io.Serializable;
import java.util.List;

public interface IService< T extends Serializable >{
	
	// find/get
	
	T findOne( final long id );
	
	List< T > findAll();
	
	// save/create/persist
	
	T save( final T entity );
	
	// update/merge
	
	void update( final T entity );
	
	// delete
	
	void delete( final long entityId );
	
	void deleteAll();
	
	void delete( final List< T > entities );
	
}
