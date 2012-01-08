package org.rest.persistence.service.bar;

import org.rest.model.Bar;
import org.rest.persistence.service.IService;
import org.springframework.data.domain.Page;

public interface IBarService extends IService< Bar >{
	
	Page< Bar > findPaginated( final int page, final int size );

}
