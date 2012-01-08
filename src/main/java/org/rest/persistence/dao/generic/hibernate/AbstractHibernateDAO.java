package org.rest.persistence.dao.generic.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.rest.persistence.dao.generic.IDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;

@SuppressWarnings( "unchecked" )
@Transactional( propagation = Propagation.SUPPORTS )
public abstract class AbstractHibernateDAO< T extends Serializable > implements IDAO< T >{
	private Class< T > clazz;
	
	@Autowired
	SessionFactory sessionFactory;
	
	public AbstractHibernateDAO(){
		super();
	}
	
	//
	
	public final void setClazz( final Class< T > clazzToSet ){
		Preconditions.checkNotNull( clazzToSet );
		this.clazz = clazzToSet;
	}
	
	// get
	
	@Override
	@Transactional( readOnly = true )
	public T findOne( final Long id ){
		Preconditions.checkArgument( id != null );
		
		return (T) this.getCurrentSession().get( this.clazz, id );
	}
	
	@Override
	@Transactional( readOnly = true )
	public List< T > findAll(){
		return this.getCurrentSession().createQuery( "from " + this.clazz.getName() ).list();
	}
	
	// create/persist
	
	@Override
	public T save( final T entity ){
		Preconditions.checkNotNull( entity );
		
		this.getCurrentSession().persist( entity );
		
		return entity;
	}
	
	// update
	
	@Override
	public void update( final T entity ){
		Preconditions.checkNotNull( entity );
		
		this.getCurrentSession().merge( entity );
	}
	
	// delete
	
	public void delete( final T entity ){
		Preconditions.checkNotNull( entity );
		
		this.getCurrentSession().delete( entity );
	}
	
	@Override
	public void delete( final long entityId ){
		final T entity = this.findOne( entityId );
		Preconditions.checkState( entity != null );
		
		this.delete( entity );
	}
	
	@Override
	public void delete( final List< T > entities ){
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void deleteAll(){
		throw new UnsupportedOperationException();
	}

	// util
	
	protected Session getCurrentSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
}
