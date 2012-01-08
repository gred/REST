package org.rest.persistence.service.foo.impl;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.rest.model.Foo;
import org.rest.persistence.dao.foo.IFooDAO;

public class FooServiceUnitTest{
	
	FooService instance;
	
	private IFooDAO daoMock;
	
	@Before
	public final void before(){
		this.instance = new FooService();
		
		this.daoMock = mock( IFooDAO.class );
		this.instance.dao = this.daoMock;
	}
	
	//
	
	@Test
	public final void whenServiceIsInitialized_thenNoException(){
		// When
		// Then
	}
	
	// create
	
	@Test
	public final void whenCreateIsTriggered_thenNoException(){
		// When
		this.instance.save( new Foo( "testName" ) );
		
		// Then
	}
	
	@Test( expected = NullPointerException.class )
	public final void whenCreateIsTriggeredForNullEntity_thenException(){
		// When
		this.instance.save( null );
		
		// Then
	}
	
	@Test
	public final void whenCreateIsTriggered_thenEntityIsCreated(){
		// When
		this.instance.save( new Foo( "testName" ) );
		
		// Then
		verify( this.daoMock ).save( any( Foo.class ) );
	}
	
	// get
	
	@Test
	public final void whenGetIsTriggered_thenNoException(){
		this.configureGet( 1l );
		
		// When
		this.instance.findOne( 1l );
		
		// Then
	}
	
	@Test( expected = NullPointerException.class )
	public final void whenGetIsTriggeredForNullId_thenException(){
		// When
		this.instance.save( null );
		
		// Then
	}
	
	@Test
	public final void whenGetIsTriggered_thenEntityIsRetrieved(){
		this.configureGet( 1l );
		// When
		this.instance.findOne( 1l );
		
		// Then
		verify( this.daoMock ).findOne( 1l );
	}
	
	// update
	
	@Test
	public final void whenUpdateIsTriggered_thenNoException(){
		// When
		this.instance.update( new Foo( "testName" ) );
		
		// Then
	}
	
	@Test( expected = NullPointerException.class )
	public final void whenUpdateIsTriggeredForNullEntity_thenException(){
		// When
		this.instance.update( null );
		
		// Then
	}
	
	@Test
	public final void whenUpdateIsTriggered_thenEntityIsUpdated(){
		// When
		final Foo entity = new Foo( "testName" );
		this.instance.update( entity );
		
		// Then
		verify( this.daoMock ).save( entity );
	}
	
	// delete
	
	@Test
	public final void whenDeleteIsTriggered_thenNoException(){
		// When
		this.instance.delete( 1 );
		
		// Then
	}
	
	@Test
	public final void whenDeleteIsTriggered_thenEntityIsDeleted(){
		// When
		this.instance.delete( 1 );
		
		// Then
		verify( this.daoMock ).delete( 1 );
	}
	
	// getAll
	
	@Test
	public final void whenGetAllIsTriggered_thenNoException(){
		// When
		this.instance.findAll();
		
		// Then
	}
	
	@Test
	public final void whenGetAllIsTriggered_thenAllEntitiesAreRetrieved(){
		// When
		this.instance.findAll();
		
		// Then
		verify( this.daoMock ).findAll();
	}
	
	// mocking behavior
	
	final Foo configureGet( final long id ){
		final Foo entity = new Foo();
		entity.setId( id );
		when( this.daoMock.findOne( id ) ).thenReturn( entity );
		return entity;
	}
	
}
