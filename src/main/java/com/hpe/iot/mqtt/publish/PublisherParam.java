package com.hpe.iot.mqtt.publish;

import java.io.File;
import java.io.FileReader;
import org.apache.commons.text.*;;

public class PublisherParam {
	private String url;
	private String message;
	private String topic;
	private String count;
	private String interval;
	private String dataFile;
	private String qos;
	private String clientId;
	private String username;
	private String password;

	public PublisherParam() {
		url = "";
		message = "";
		topic = "";
		count = "1";
		interval = "1000";
		dataFile = "";
		qos = "0";
		clientId = "client-" + new RandomStringGenerator.Builder().withinRange('a', 'z').build().generate(20);
		username = "";
		password = "";
	}	
	
	public String getUser() {
		return username;
	}

	public void setUser(String user) throws Exception {
		this.username = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrl() throws Exception {
		if(url.equals(""))
			throw new Exception("url error");
		return url;
	}

	public void setUrl(String url) throws Exception {
		if(url.equals(""))
			throw new Exception("url error!");
		this.url = "tcp://" + url;
		
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getTopic() throws Exception {
		if(topic.equals(""))
			throw new Exception("topic error!");
		return topic;
	}
	
	public void setTopic(String topic) throws Exception {
		if(topic.equals(""))
			throw new Exception("topic error");
		this.topic = topic;
	}
	
	public String getCount() {
		return count;
	}
	
	public void setCount(String count) throws Exception {
		if (count.isEmpty() || count.matches("[0-9]+")==false)
			throw new Exception("count error!");
		this.count = count;
	}
	
	public String getInterval() {
		return interval;
	}
	
	public void setInterval(String interval) throws Exception {
		if (interval.isEmpty() || interval.matches("[0-9]+")==false)
			throw new Exception("timer interval error!");
		this.interval = interval;
	}
	
	public String getDataFile() {
		return dataFile;
	}

	public void setDataFile(String file) throws Exception {
		if (file.isEmpty())
			throw new Exception("error: file name is empty!");
		this.dataFile = file;
	}
	
	public String getQos() {
		return qos;
	}
	
	public void setQos(String qos) throws Exception {
		if (qos.isEmpty() || qos.matches("[0-2]")==false)
			throw new Exception("qos[0,1,2] error!");
		this.qos = qos;
	}	
	
	
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) throws Exception {
		if (clientId.isEmpty())
			throw new Exception("error: clientId is empty!");
		this.clientId = clientId;
	}

	@Override
	public String toString() {
		return "PublisherParam [url=" + url + ", message=" + message + ", topic=" + topic + ", count=" + count
				+ ", interval=" + interval + ", dataFile=" + dataFile + ", qos=" + qos + ", clientId=" + clientId
				+ ", username=" + username + ", password=" + password + "]";
	}


	
}
