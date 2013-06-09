package at.linuxhacker.restlet.client;

import org.restlet.representation.Representation;
import org.restlet.representation.RepresentationInfo;
import org.restlet.resource.ClientResource;

import at.linuxhacker.restlet.common.RootResource;

public class MailClient extends ClientResource {

	/**
	 * @param args
	 */
	public static void main( String[] args ) {
		RootResource mailRoot = ClientResource.create(  "http://localhost:8111/", RootResource.class );
		String result = mailRoot.represent( );
		System.out.println( result );
		
		ClientResource mailClient = new ClientResource( "http://localhost:8111/accounts/homer/mails/123" );
		Representation mailRepresentation = mailClient.get( );
		mailClient.put( mailRepresentation );
	}

}
