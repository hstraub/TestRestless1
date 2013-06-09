package at.linuxhacker.restlet.server;

import java.util.logging.Level;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.engine.Engine;
import org.restlet.routing.Router;

public class MailServerApplication extends Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Engine.setLogLevel( Level.INFO);
		Component component = new Component( );
		component.getDefaultHost( ).attach( "", new MailServerApplication( ) );
		Server mailServer = new Server( Protocol.HTTP, 8111, component );
		mailServer.start( );
	}

	public MailServerApplication() {
		setName( "MailServerApplication" );
		setDescription( "This is a example applcation" );
		setOwner( "Mail Server Team" );
		setAuthor( "Herbert Straub" );
		getStatusService( ).setContactEmail( "herbert@linuxhacker.at" );
	}

	public MailServerApplication(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Restlet createInboundRoot() {
		// See: http://stackoverflow.com/questions/13147772/restlet-trouble-attaching-resource-class-with-router
		//IpBlocker ipBlocker = new IpBlocker( getContext( ) );
		// Tracer tracer = new Tracer( getContext( ) );

		Router router = new Router( );
		router.attach( "/", RootServerResource.class );
		router.attach( "/accounts/", AccountsServerResource.class );
		router.attach( "/accounts/{accountId}", AccountServerResource.class );
		// router.attach( "/accounts/{accountId}/mails/{mailId}", MailServerDomResource.class );
		router.attach( "/accounts/{accountId}/mails/{mailId}", MailServerSaxResource.class );
		
		return router;
	}
	

}
