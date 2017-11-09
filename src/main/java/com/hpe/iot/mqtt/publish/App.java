package com.hpe.iot.mqtt.publish;

import org.apache.commons.cli.CommandLineParser;  
import org.apache.commons.cli.BasicParser;  
import org.apache.commons.cli.Options;  
import org.apache.commons.cli.CommandLine;  
import org.apache.commons.cli.ParseException;  
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;

public class App {

	public static void main(String[] args) throws Exception  {
		
		Options options = new Options( );  
		options.addOption("h", "help", false, "Print this usage information");  
		options.addOption("C", "count", true, "Times that Message will be published message ");
		options.addOption("i", "interval", true, "Time interval in ms between two message, default 1000");
		options.addOption("d", "data", true, "Message string");  
		options.addOption("f", "file", true, "file name in which message is sent ");  
		options.addOption("c", "clientid", true, "client id, default random");  
		options.addOption("u", "username", true, "user name for authorication"); 
		options.addOption("p", "password", true, "password for authorication"); 
		options.addOption("q", "qos", true, "set qos value");
		
	    Option optionAddress = new Option("a", "address", true, "Broker address, like host:port");
		optionAddress.setRequired(false);
		options.addOption(optionAddress);
	    Option optionTopic = new Option("t", "topic", true, "topic to publish");
	    optionTopic.setRequired(false);
		options.addOption(optionTopic);
		
		PublisherParam publisherParam = new PublisherParam();
		CommandLineParser parser = new BasicParser();  	
		
		try {
			CommandLine commandLine = parser.parse(options, args); 	
		    if( commandLine.hasOption('h') ) {  
		    	HelpFormatter formatter = new HelpFormatter();
		    	formatter.printHelp( "mqtt_pub [options]", "Publish a mqtt message to broker\nOptions:", options, "" );
			    System.exit(0);  
			} 

		    if( commandLine.hasOption('a') ) {  
		    	publisherParam.setUrl(commandLine.getOptionValue('a'));
			} 	   
		    
		    if( commandLine.hasOption('C') ) {  
		    	publisherParam.setCount(commandLine.getOptionValue('C'));
			} 	    
		    

		    if( commandLine.hasOption('i') ) {  
		    	publisherParam.setInterval(commandLine.getOptionValue('i'));
			} 	    
		    
		    if( commandLine.hasOption('d') ) {  
		    	publisherParam.setMessage(commandLine.getOptionValue('d'));
			} 	
		    
		    if( commandLine.hasOption('f') ) {  
		    	publisherParam.setDataFile(commandLine.getOptionValue('f'));
			} 
		    
		    if( commandLine.hasOption('t') ) {  
		    	publisherParam.setTopic(commandLine.getOptionValue('t'));
			} 
		    
		    
		    if( commandLine.hasOption('c') ) {  
		    	publisherParam.setClientId(commandLine.getOptionValue('c'));
			} 	    
		    
		    
		    if( commandLine.hasOption('q') ) {  
		    	publisherParam.setQos(commandLine.getOptionValue('q'));
			} 
		    
		    if( commandLine.hasOption('u') ) {  
		    	publisherParam.setUser(commandLine.getOptionValue('u'));
			} 		    
		    
		    if( commandLine.hasOption('p') ) {  
		    	publisherParam.setPassword(commandLine.getOptionValue('p'));
			} 
		    
		    
		    
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			System.out.println("Failed and exited");
			System.exit(1);
		}
		
		
		Publisher publisher = new Publisher();
		publisher.start(publisherParam);
	}

}
