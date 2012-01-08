package org.rest.service.foo.impl;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.rest.model.Foo;
import org.rest.persistence.service.foo.IFooService;
import org.rest.spring.application.ApplicationTestConfig;
import org.rest.spring.persistence.jpa.PersistenceJPAConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = { ApplicationTestConfig.class, PersistenceJPAConfig.class },loader = AnnotationConfigContextLoader.class )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class FooServiceIntegrationTest{
	
	@Autowired
	IFooService service;
	
	// create
	
	@Test
	public final void whenSaveIsPerformed_thenNoException(){
		this.service.save( new Foo( randomAlphabetic( 8 ) ) );
	}
	
	@Test( expected = DataAccessException.class )
	public final void whenAUniqueConstraintIsBroken_thenSpringSpecificExceptionIsThrown(){
		final String name = randomAlphabetic( 8 );
		this.service.save( new Foo( name ) );
		this.service.save( new Foo( name ) );
	}
	
}
