import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.test.junit4.CamelSpringTestSupport;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
import org.loadbalancer.LoadBalancerRoute;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: sebastien
 * Date: 09/06/12
 */
public class LoadBalancerRouteTest extends CamelSpringTestSupport {

    @EndpointInject(uri = "{{output1}}")
    protected MockEndpoint resultEndpoint1;

    @EndpointInject(uri = "{{output2}}")
    protected MockEndpoint resultEndpoint2;

    @Produce(uri = "{{input}}")
    protected ProducerTemplate template;

    @Test
    public void testSendMatchingMessage() throws Exception {
        String expectedBody = "<matched/>";

        resultEndpoint1.expectedBodiesReceived(expectedBody);
        resultEndpoint2.expectedBodiesReceived(expectedBody);

        template.sendBody(expectedBody);
        template.sendBody(expectedBody);

        resultEndpoint1.assertIsSatisfied();
        resultEndpoint2.assertIsSatisfied();
    }


    @Override
    protected RouteBuilder createRouteBuilder() {
        return new LoadBalancerRoute();
    }

    @Override
    protected AbstractXmlApplicationContext createApplicationContext() {
        return new ClassPathXmlApplicationContext("classpath:META-INF/spring/camel-context.xml");
    }

}
