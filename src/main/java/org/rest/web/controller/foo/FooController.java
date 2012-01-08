package org.rest.web.controller.foo;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.rest.common.event.PaginatedResultsRetrievedEvent;
import org.rest.common.event.ResourceCreatedEvent;
import org.rest.common.event.SingleResourceRetrievedEvent;
import org.rest.common.util.RestPreconditions;
import org.rest.model.Foo;
import org.rest.persistence.service.foo.IFooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@Transactional( propagation = Propagation.REQUIRES_NEW )
public class FooController{
	
	@Autowired
	IFooService service;
	
	@Autowired
	ApplicationEventPublisher eventPublisher;
	
	// API
	
	@RequestMapping( value = "admin/foo",params = "page",method = RequestMethod.GET )
	@ResponseBody
	public List< Foo > findPaginated( @RequestParam( value = "page" ) final int page, final UriComponentsBuilder uriBuilder, final HttpServletResponse response ){
		final Page< Foo > resultPage = this.service.findPaginated( page, 10 );
		
		this.eventPublisher.publishEvent( new PaginatedResultsRetrievedEvent< Foo >( Foo.class, uriBuilder, response, page, resultPage.getTotalPages() ) );
		
		return resultPage.getContent();
	}
	
	@RequestMapping( value = "admin/foo",method = RequestMethod.GET )
	@ResponseBody
	public List< Foo > findAll(){
		return this.service.findAll();
	}
	
	@RequestMapping( value = "admin/foo/{id}",method = RequestMethod.GET )
	@ResponseBody
	public Foo find( @PathVariable( "id" ) final Long id, final UriComponentsBuilder uriBuilder, final HttpServletResponse response ){
		final Foo resourceById = RestPreconditions.checkNotNull( this.service.findOne( id ) );
		
		this.eventPublisher.publishEvent( new SingleResourceRetrievedEvent< Foo >( Foo.class, uriBuilder, response ) );
		return resourceById;
	}
	
	@RequestMapping( value = "admin/foo",method = RequestMethod.POST )
	@ResponseStatus( HttpStatus.CREATED )
	public void create( @RequestBody final Foo resource, final UriComponentsBuilder uriBuilder, final HttpServletResponse response ){
		RestPreconditions.checkRequestElementNotNull( resource );
		this.service.save( resource );
		
		this.eventPublisher.publishEvent( new ResourceCreatedEvent< Foo >( Foo.class, uriBuilder, response, resource.getId() ) );
	}
	
	@RequestMapping( value = "admin/foo",method = RequestMethod.PUT )
	@ResponseStatus( HttpStatus.OK )
	public void update( @RequestBody final Foo resource ){
		RestPreconditions.checkRequestElementNotNull( resource );
		RestPreconditions.checkNotNull( this.service.findOne( resource.getId() ) );
		this.service.update( resource );
	}
	
	@RequestMapping( value = "admin/foo/{id}",method = RequestMethod.DELETE )
	@ResponseStatus( HttpStatus.NO_CONTENT )
	public void delete( @PathVariable( "id" ) final Long id ){
		this.service.delete( id );
	}
	
}
