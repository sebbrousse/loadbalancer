package org.loadbalancer;

import org.apache.camel.spring.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoadBalancer {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(LoadBalancer.class);
	
	public static void main(String[] args) {
		try {
			Main.main(args);
		} catch (Exception e) {
			LOGGER.error("Error : ", e);
		}
	}

}
