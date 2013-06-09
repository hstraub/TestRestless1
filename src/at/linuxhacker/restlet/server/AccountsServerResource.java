package at.linuxhacker.restlet.server;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.restlet.resource.ServerResource;

import at.linuxhacker.restlet.common.AccountsResource;

public class AccountsServerResource extends ServerResource implements
		AccountsResource {
	
	private static final List<String> accounts = new CopyOnWriteArrayList<String>( );
	
	public static List<String> getAccounts( ) {
		
		return accounts;
		
	}
	
	public static String get( int accountId ) {
		return accounts.get( accountId );
	}

	@Override
	public String represent( ) {
		StringBuilder result = new StringBuilder( );
		
		for ( String account : getAccounts( ) ) {
			result.append(  account + "\n" );
		}
		
		return result.toString( );
		
	}

	@Override
	public String add( String account ) {

		getAccounts( ).add( account );
		
		return Integer.toString( getAccounts( ).indexOf( account ) ); 
		
	}

}
