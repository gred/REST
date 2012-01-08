package org.rest.persistence.dao.bar;

import org.rest.model.Bar;
import org.rest.model.Foo;
import org.rest.persistence.dao.generic.IDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBarDAO extends JpaRepository< Bar, Long >, IDAO< Bar >{
	
	Foo findByName( final String name );
	
}
