package org.rest.dao.spring;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.rest.model.Foo;
import org.rest.persistence.dao.foo.IFooDAO;
import org.rest.spring.application.ApplicationTestConfig;
import org.rest.spring.persistence.jpa.PersistenceJPAConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = { ApplicationTestConfig.class, PersistenceJPAConfig.class },loader = AnnotationConfigContextLoader.class )
public class FooDAOIntegrationTest{
	
	@Autowired
	IFooDAO dao;
	
	//
	@Test
	public final void whenSaveIsPerformed_thenNoException(){
		this.dao.save( new Foo( randomAlphabetic( 8 ) ) );
	}
	
	// find by
	
	@Test
	public final void givenEntityDoesNotExist_whenFindingEntityByName_thenEntityNotFound(){
		// Given
		final String name = randomAlphabetic( 8 );
		
		// When
		final Foo entityByName = this.dao.findByName( name );
		
		// Then
		assertNull( entityByName );
	}
	
}
