package org.rest.persistence.service.common;

import java.io.Serializable;
import java.util.List;

import org.rest.persistence.dao.generic.IDAO;
import org.rest.persistence.service.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;

@Transactional( propagation = Propagation.MANDATORY )
public abstract class AbstractService< T extends Serializable > implements IService< T >{
	protected final Logger logger = LoggerFactory.getLogger( this.getClass() );
	
	public AbstractService(){
		super();
	}
	
	// API
	
	// find/get
	
	@Override
	@Transactional( readOnly = true )
	public T findOne( final long id ){
		return this.getDao().findOne( id );
	}
	
	@Override
	@Transactional( readOnly = true )
	public List< T > findAll(){
		return this.getDao().findAll();
	}
	
	// save/create/persist
	
	@Override
	public T save( final T entity ){
		Preconditions.checkNotNull( entity );
		
		final T persistedEntity = this.getDao().save( entity );
		
		return persistedEntity;
	}
	
	// update/merge
	
	@Override
	public void update( final T entity ){
		Preconditions.checkNotNull( entity );
		
		this.getDao().save( entity );
	}
	
	// delete
	
	@Override
	public void deleteAll(){
		this.getDao().deleteAll();
	}
	@Override
	public void delete( final List< T > entities ){
		Preconditions.checkNotNull( entities );
		
		this.getDao().delete( entities );
	}
	@Override
	public void delete( final long id ){
		// final T entity = this.findOne( id );
		// RestPreconditions.checkNotNull( entity );

		this.getDao().delete( id );
	}
	
	//
	protected abstract IDAO< T > getDao();
	
}
