package org.loadbalancer;

import org.apache.camel.builder.RouteBuilder;

public class LoadBalancerRoute extends RouteBuilder {

    private static final String SERVER1_URL = "http://localhost:8080";
    private static final String SERVER2_URL = "http://localhost:8081";

    @Override
	public void configure() throws Exception {
		from("jetty:http://0.0.0.0:15000?matchOnUriPrefix=true")
		.to("log:org.loadbalancer.IN?showAll=true&multiline=true")
		.loadBalance()
		.roundRobin()
		.to(SERVER1_URL + "?bridgeEndpoint=true", SERVER2_URL + "?bridgeEndpoint=true");

	}

}
