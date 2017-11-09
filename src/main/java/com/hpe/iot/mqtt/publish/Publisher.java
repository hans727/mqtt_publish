package com.hpe.iot.mqtt.publish;

import java.io.File;
import java.io.FileFilter;

//import java.nio.file.Files;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Publisher {

	int filePos = -1;
	File files[];
	void start(PublisherParam params) {

		System.out.println(params.toString());
		
	    try {
		    String topic        = params.getTopic();
		    String content      = params.getMessage();
		    int qos             = Integer.valueOf(params.getQos());
		    String broker       = params.getUrl();
		    String clientId     = params.getClientId();
		    String username     = params.getUser();
		    String password     = params.getPassword();
		    int count           = Integer.valueOf(params.getCount());
		    int interval        = Integer.valueOf(params.getInterval());
		    String dataFile		= params.getDataFile();
		    
	    	
		    MemoryPersistence persistence = new MemoryPersistence();
	        MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
	        MqttConnectOptions connOpts = new MqttConnectOptions();
	        connOpts.setCleanSession(true);
	        if(!username.isEmpty() && !password.isEmpty()) {
	        	connOpts.setUserName(username);	    
	        	connOpts.setPassword(password.toCharArray());	        	
	        }

	        System.out.println("Connecting to broker: "+broker);
	        sampleClient.connect(connOpts);
	        System.out.println("Connected");
	        MqttMessage message = new MqttMessage(content.getBytes());
	        message.setQos(qos);
	        for(int i=0 ; i<count ; i++ ){
       	
	        	if(dataFile == ""){
				    //System.out.println("Publishing message: "+content);	 
	    	        message.setPayload(content.getBytes());
			        //sampleClient.publish(topic, message);
			        Thread.sleep(interval);	        		
	        	} else {
	    	        message.setPayload(getMessageFromFile(dataFile).getBytes());	
				    //System.out.println("Publishing message: "+content);	 
	        	}
		        sampleClient.publish(topic, message);
		        System.out.println("Message published");
		        Thread.sleep(interval);	        
	        }

	        
	        sampleClient.disconnect();
	        System.out.println("Disconnected and finished");
	        System.exit(0);
	    } catch(MqttException me) {
	        System.out.println("failed! "+me.getMessage());
	        //me.printStackTrace();
	        System.exit(1);
	    } catch (Exception e) {
	    	System.out.println("failed! " + e.getMessage());
			//e.printStackTrace();
	    	System.exit(2);
		}
	}
	
	 private String getMessageFromFile(String pathname) throws Exception {
		 
		System.out.println(pathname);
		File path =  new File(pathname);		 
		
		if(pathname=="" || !path.exists()) {
			
	       	throw new Exception("error: -f switch value invalid - " + pathname);			
	       	
		} else if(path.isFile()) {
			
			return  new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(pathname)));
			
		} else {
			if(filePos == -1){
			    FileFilter filter = new FileFilter() {
			        @Override
			        public boolean accept(File path) {
			           return path.isFile();
			        }
			    };		 
			    files = path.listFiles(filter);   
			    filePos = 0;
			}
					
		    if(files.length == 0)
		    	throw new Exception("error: not any file in directory - " + pathname);
		    
		    File f = files[filePos];
		    filePos = (filePos + 1)%files.length;

			return  new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(f.getPath())));			        
		}

	}
}