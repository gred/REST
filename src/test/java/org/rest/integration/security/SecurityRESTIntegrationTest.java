package org.rest.integration.security;

import static com.jayway.restassured.RestAssured.given;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;
import org.rest.common.util.HttpConstants;
import org.rest.integration.ExamplePaths;
import org.rest.integration.test.AbstractRESTIntegrationTest;
import org.rest.model.Foo;
import org.springframework.beans.factory.annotation.Autowired;

import com.jayway.restassured.response.Response;

public class SecurityRESTIntegrationTest extends AbstractRESTIntegrationTest{
	
	@Autowired
	ExamplePaths paths;
	
	@Autowired
	SecurityComponent securityComponent;
	
	// tests
	
	@Test
	public final void givenUnauthenticated_whenAResourceIsCreated_then401IsReceived(){
		// Given
		// When
		final Response response = given().contentType( HttpConstants.MIME_JSON ).body( new Foo( randomAlphabetic( 6 ) ) ).post( this.paths.getFooURL() );
		
		// Then
		assertThat( response.getStatusCode(), is( 401 ) );
	}
	
	@Test
	public final void givenAuthenticatedByBasicAuth_whenAResourceIsCreated_then201IsReceived(){
		// Given
		// When
		final Response response = given().auth().preemptive().basic( SecurityComponent.ADMIN_USERNAME, SecurityComponent.ADMIN_PASSWORD ).contentType( HttpConstants.MIME_JSON ).body( new Foo( randomAlphabetic( 6 ) ) ).post( this.paths.getFooURL() );
		
		// Then
		assertThat( response.getStatusCode(), is( 201 ) );
	}
	@Test
	public final void givenAuthenticatedByDigestAuth_whenAResourceIsCreated_then201IsReceived(){
		// Given
		// When
		final Response response = given().auth().digest( SecurityComponent.ADMIN_USERNAME, SecurityComponent.ADMIN_PASSWORD ).contentType( HttpConstants.MIME_JSON ).body( new Foo( randomAlphabetic( 6 ) ) ).post( this.paths.getFooURL() );
		
		// Then
		assertThat( response.getStatusCode(), is( 201 ) );
	}
	
}
