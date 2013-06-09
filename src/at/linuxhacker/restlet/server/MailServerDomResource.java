package at.linuxhacker.restlet.server;

import java.io.IOException;

import org.restlet.data.Reference;
import org.restlet.ext.xml.DomRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class MailServerDomResource extends ServerResource {

	@Get( "xml" )
	public Representation toXml( ) throws IOException {
		
		DomRepresentation result = new DomRepresentation( );
		result.setIndenting( true );
		Document doc = result.getDocument( );
		
		Node mailNode = doc.createElement( "mail" );
		doc.appendChild( mailNode );
		
		Node statusNode = doc.createElement( "status" );
		statusNode.setTextContent( "received" );
		mailNode.appendChild( statusNode );
		
		Node subjectNode = doc.createElement( "subject" );
		subjectNode.setTextContent( "Mail to self" );
		mailNode.appendChild( subjectNode );
		
		Node contentNode = doc.createElement( "content" );
		contentNode.setTextContent( "bla" );
		mailNode.appendChild( contentNode );
		
		Node accountRefNode = doc.createElement( "accountRef" );
		accountRefNode.setTextContent( new Reference( getReference( ), ".." ).getTargetRef( ).toString( ) );
		mailNode.appendChild( accountRefNode );
		
		return result;
	}
	
	@Put( "xml" )
	public void store( DomRepresentation mailRepresentation ) throws IOException {
		
		Document doc = mailRepresentation.getDocument( );
		Element mailElement = doc.getDocumentElement( );
		
		Element statusElement = (Element) mailElement.getElementsByTagName( "status" ).item(  0 );
		Element subjectElement = (Element) mailElement.getElementsByTagName( "subject" ).item( 0 );
		Element contentElement = (Element) mailElement.getElementsByTagName( "content" ).item( 0 );
		Element accountRefElement = (Element) mailElement.getElementsByTagName( "accountRef" ).item( 0 );
		
		System.out.println( "Status: " + statusElement.getTextContent( ) );
		System.out.println( "Subject: " + subjectElement.getTextContent( ) );
		System.out.println( "Content: " + contentElement.getTextContent( ) );
		System.out.println( "AccountRef: " + accountRefElement.getTextContent( ) );
		
		
	}
}
