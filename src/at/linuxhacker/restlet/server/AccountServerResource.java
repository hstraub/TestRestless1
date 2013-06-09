package at.linuxhacker.restlet.server;

import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import at.linuxhacker.restlet.common.AccountResource;

public class AccountServerResource extends ServerResource implements
		AccountResource {
	
	private int accountId;
	
	@Override
	protected void doInit( ) throws ResourceException {

		this.accountId = Integer.parseInt( getAttribute( "accountId" ) );
	}

	@Override
	public String represent( ) {

		return AccountsServerResource.get( this.accountId );
		
	}

	@Override
	public void store( String account ) {
		
		AccountsServerResource.getAccounts( ).set( this.accountId, account );

	}

	@Override
	public void remove( ) {
		
		AccountsServerResource.getAccounts( ).remove( this.accountId );

	}

}
