package org.rest.persistence.dao.generic.hibernate;

import java.io.Serializable;

import org.rest.persistence.dao.generic.IGenericDAO;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

// @Repository
@Profile( "hibernate" )
@Scope( BeanDefinition.SCOPE_PROTOTYPE )
public class GenericHibernateDAO< T extends Serializable > extends AbstractHibernateDAO< T > implements IGenericDAO< T >{
	
	public GenericHibernateDAO(){
		super();
	}
	
}
