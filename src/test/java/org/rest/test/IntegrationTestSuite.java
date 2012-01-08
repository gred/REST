package org.rest.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.rest.integration.foo.FooDiscoverabilityRESTIntegrationTest;
import org.rest.integration.foo.FooRESTIntegrationTest;
import org.rest.integration.foo.FooMimeRESTIntegrationTest;
import org.rest.integration.security.SecurityRESTIntegrationTest;

@RunWith( Suite.class )
@SuiteClasses( { SecurityRESTIntegrationTest.class, FooDiscoverabilityRESTIntegrationTest.class, FooRESTIntegrationTest.class, FooMimeRESTIntegrationTest.class } )
public final class IntegrationTestSuite{
	//
}
