package org.rest.common.event;

import javax.servlet.http.HttpServletResponse;

import org.rest.common.util.HttpConstants;
import org.rest.common.util.RESTURIUtil;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.common.base.Preconditions;

@SuppressWarnings( "rawtypes" )
@Component
final class PaginatedResultsRetrievedEventDiscoverabilityListener implements ApplicationListener< PaginatedResultsRetrievedEvent >{

	// API

	@Override
	public final void onApplicationEvent( final PaginatedResultsRetrievedEvent ev ){
		Preconditions.checkNotNull( ev );

		this.addLinkHeaderOnPagedResourceRetrieval( ev.getUriBuilder(), ev.getResponse(), ev.getClazz(), ev.getPage(), ev.getTotalPages() );
	}

	//

	final void addLinkHeaderOnPagedResourceRetrieval( final UriComponentsBuilder uriBuilder, final HttpServletResponse response, final Class clazz, final int page, final int totalPages ){
		final String resourceName = clazz.getSimpleName().toString().toLowerCase();
		uriBuilder.path( "/admin/" + resourceName );

		String linkHeaderValue = null;

		if( page < totalPages - 1 ){
			final String uriForNextPage = uriBuilder.queryParam( "page", page + 1 ).build().encode().toUriString();
			linkHeaderValue = RESTURIUtil.createLinkHeader( uriForNextPage, RESTURIUtil.REL_NEXT );
		}

		final String uriForLastPage = uriBuilder.replaceQueryParam( "page", totalPages ).build().encode().toUriString();
		linkHeaderValue = linkHeaderValue + ", " + RESTURIUtil.createLinkHeader( uriForLastPage, RESTURIUtil.REL_LAST );

		response.addHeader( HttpConstants.LINK_HEADER, linkHeaderValue );
	}

}
