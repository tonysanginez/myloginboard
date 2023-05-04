package com.tasm.task;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

//@Configuration
//@EnableScheduling
public class JcmTask {
	
	private static final Logger logger = LogManager.getLogger(JcmTask.class.getName());
	
	//@Scheduled(cron = "0 * * ? * *") // 0 5 * * * ?
	public void scheduleTaskUsingCronExpression() {
	    long now = System.currentTimeMillis() / 1000;
	    System.out.println("schedule tasks using cron jobs - " + now);
	    
	    try {
	    	JsonFactory factoria = new JsonFactory();
			JsonGenerator generator = factoria.createGenerator(System.out);
			generator.writeStartObject();
	        generator.writeStringField("nombre", "pedro");
	        generator.writeStringField("apellidos", "gomez");
	        generator.writeNumberField("edad",30);
	        generator.writeEndObject();
	        generator.flush();
		} catch (IOException e) {
			logger.error(e);
		}
	}
	
}