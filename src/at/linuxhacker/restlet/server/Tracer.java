package at.linuxhacker.restlet.server;

import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.MediaType;

public class Tracer extends Restlet {

	public Tracer(Context context) {
		super(context);
	}
	
	@Override
	public void handle( Request request, Response response ) {
		String entity = "Method: " + request.getMethod( )
				+ "\nResource: " + request.getResourceRef( )
				+ "\nIP address: " + request.getClientInfo( ).getAddress( )
				+ "\nAgent name: " + request.getClientInfo( ).getAgentName( )
				+ "\nAgent version: " + request.getClientInfo( ).getAgentVersion( )
				+ "\nUser: " + request.getClientInfo( ).getUser( )
				+ "\n";
		response.setEntity( entity, MediaType.TEXT_PLAIN );
	}

}
