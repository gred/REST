package org.rest.persistence.dao.generic;

import java.io.Serializable;


public interface IGenericDAO< T extends Serializable > extends IDAO< T >{
	
	void setClazz( final Class< T > clazzToSet );
	
}
