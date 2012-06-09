package org.loadbalancer;

import org.apache.camel.EndpointInject;
import org.apache.camel.Expression;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;

public class LoadBalancerRoute extends RouteBuilder {


    private String inputPort = "15000";

    private static final String SERVER1_URL = "http://localhost:8080";
    private static final String SERVER2_URL = "http://localhost:8081";


    private String[] destinations;

    @Override
	public void configure() throws Exception {
		//jetty:http://0.0.0.0:"+inputPort+"?matchOnUriPrefix=true"

        destinations = new String[]{"{{output1}}", "{{output2}}"};

        from("{{input}}").routeId("LOADBALANCER")
		.to("log:org.loadbalancer.IN?showAll=true&multiline=true")
		.loadBalance()
		.roundRobin()
                //.to(SERVER1_URL + "?bridgeEndpoint=true", SERVER2_URL + "?bridgeEndpoint=true");
        .to(destinations);
	}

}
