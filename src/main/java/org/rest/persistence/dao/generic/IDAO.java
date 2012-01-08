package org.rest.persistence.dao.generic;

import java.io.Serializable;
import java.util.List;

/**
 * @author eugenp
 */
public interface IDAO< T extends Serializable >{
	
	// find
	
	T findOne( final Long id );
	
	List< T > findAll();
	
	// save/create/persist
	
	T save( final T entity );
	
	void update( final T entity );
	
	// delete
	
	void delete( final long entityId );
	
	void deleteAll();
	
	void delete( final List< T > entities );
	
}
