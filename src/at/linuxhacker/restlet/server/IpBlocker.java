package at.linuxhacker.restlet.server;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Status;
import org.restlet.routing.Filter;

public class IpBlocker extends Filter {
	private final Set<String> blockedAdresses;

	public IpBlocker(Context context) {
		super(context);
		
		this.blockedAdresses = new CopyOnWriteArraySet<String>( );
	}

	@Override
	protected int beforeHandle(Request request, Response response) {
		int result = STOP;
		
		if ( this.getBlockedAdresses( ).contains( request.getClientInfo().getAddress( ) ) ) {
			response.setStatus(Status.CLIENT_ERROR_FORBIDDEN, "Your address has been blocked" );
		} else {
			result = CONTINUE;
		}
		
		return result;
	}

	public Set<String> getBlockedAdresses() {
		return blockedAdresses;
	}
	
}
