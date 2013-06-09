package at.linuxhacker.restlet.common;

import org.restlet.resource.Get;
import org.restlet.resource.Options;

public interface RootResource {
	@Get( "txt" )
	public String represent( );
	
	@Options( "txt" )
	public String describe( );
}
