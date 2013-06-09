package at.linuxhacker.restlet.common;

import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

public interface AccountResource {
	
	@Get( "txt" )
	public String represent( );
	
	@Put( "txt" )
	public void store( String account );
	
	@Delete( "txt" )
	public void remove ( );

}
