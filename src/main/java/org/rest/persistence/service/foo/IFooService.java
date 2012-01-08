package org.rest.persistence.service.foo;

import org.rest.model.Foo;
import org.rest.persistence.service.IService;
import org.springframework.data.domain.Page;

public interface IFooService extends IService< Foo >{
	
	Page< Foo > findPaginated( final int page, final int size );
	
}
