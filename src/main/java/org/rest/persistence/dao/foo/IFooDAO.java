package org.rest.persistence.dao.foo;

import org.rest.model.Foo;
import org.rest.persistence.dao.generic.IDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFooDAO extends JpaRepository< Foo, Long >, IDAO< Foo >{
	
	Foo findByName( final String name );
	
}
