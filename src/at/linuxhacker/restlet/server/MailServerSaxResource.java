package at.linuxhacker.restlet.server;

import java.io.IOException;
import java.util.List;

import org.restlet.data.MediaType;
import org.restlet.data.Reference;
import org.restlet.ext.xml.SaxRepresentation;
import org.restlet.ext.xml.XmlWriter;
import org.restlet.representation.Representation;
import org.restlet.representation.Variant;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MailServerSaxResource extends ServerResource {
	
	@Override
	protected void doInit( ) throws ResourceException {
		List<Variant> variants = getVariants( );
		for ( Variant variant : variants ) {
			System.out.println( "variant: " + variant.toString( ) );
		}
	}

	@Get( "xml" )
	public Representation getMailXml( ) throws IOException {
		
		SaxRepresentation result = new SaxRepresentation( ) {
			
			public void write( XmlWriter writer ) throws IOException {
				try {
					writer.startDocument( );
					writer.startElement( "mail" );
					
					writer.startElement( "status" );
					writer.characters( "received" );
					writer.endElement( "status" );
					
					writer.startElement( "subject" );
					writer.characters( "Mail to self" );
					writer.endElement( "subject" );
					
					writer.startElement( "content" );
					writer.characters( "bla" );
					writer.endElement( "content" );
					
					writer.startElement( "accountRef" );
					writer.characters( new Reference( getReference( ), ".." ).getTargetRef( ).toString( ) );
					writer.endElement( "accountRef" );
					
					writer.endElement( "mail" );
					writer.endDocument( );
				} catch( SAXException e ) {
					throw new IOException( e.getMessage( ) );
				}
			};
		};
		
		return result;
		
	}
	
	@Get( "txt" )
	public String getMailTxt( ) {
		return "Das ist ein Test";
	}

	@Put( "xml" )
	public void store( SaxRepresentation mailRepresentation ) throws IOException {
		
		mailRepresentation.parse( new DefaultHandler( ) {

			@Override
			public void characters( char[] ch, int start, int length )
					throws SAXException {
				System.out.print(  new String( ch, start, length) );
			}

			@Override
			public void endElement( String uri, String localName, String qName )
					throws SAXException {
				System.out.println( );
			}

			@Override
			public void startElement( String uri, String localName,
					String qName, Attributes attributes ) throws SAXException {
				if ( "status".equals( localName ) ) {
					System.out.print( "Status: " );
				} else if ( "subject".equals( localName ) ) {
					System.out.print( "Subject: " );
				} else if ( "content".equals( localName ) ) {
					System.out.print(  "Content: " );
				} else if ( "accountRef".equals( localName ) ) {
					System.out.print(  "AccountRef: " );
				}
			}
			
		} );
	}
}
