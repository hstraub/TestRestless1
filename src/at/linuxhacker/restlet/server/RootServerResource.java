package at.linuxhacker.restlet.server;

import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Options;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import at.linuxhacker.restlet.common.RootResource;

public class RootServerResource extends ServerResource implements RootResource {

	public RootServerResource() {
		setNegotiated( false );
		//setExisting( false );
	}

	@Override
	protected void doInit( ) throws ResourceException {
		System.out.println( "in doInit" );
	}

	@Override
	protected void doCatch( Throwable throwable ) {
		System.out.println( "in doCatch" );
	}

	@Override
	protected void doRelease( ) throws ResourceException {
		System.out.println( "in doRelease" );
	}

	public String represent( ) {
		return "Hallo, dass ist die RootResource";
	}
	
	public String describe( ) {
		throw new RuntimeException( "Not yet implemented." );
	}


}
