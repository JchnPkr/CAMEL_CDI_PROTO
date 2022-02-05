
package de.camel_cdi_proto.route;

import javax.inject.Inject;

import org.apache.camel.EndpointInject;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.cdi.CdiCamelContext;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.cdi.CamelCdiRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(CamelCdiRunner.class)
public class MyRoutesTest {
  @Inject
  @DoNotDiscover
  private MyRoutes routes;

  @EndpointInject("mock:direct:b")
  private MockEndpoint mockB;

  @EndpointInject("mock:log:messages")
  private MockEndpoint mockLog;

  @Inject
  private CdiCamelContext camelContext;

  void advice() throws Exception {
    AdviceWith.adviceWith(camelContext, MyRoutes.TIMER_ROUTE, a -> {
      a.mockEndpoints("direct:b");
    });

    AdviceWith.adviceWith(camelContext, MyRoutes.QUEUE_ROUTE, a -> {
      a.mockEndpoints("log:messages");
    });
  }

  @Before
  public void setup() throws Exception {
    camelContext.setAutoStartup(false);
    camelContext.addRoutes(routes);
    advice();
    camelContext.getRouteController().startRoute(MyRoutes.TIMER_ROUTE);
    camelContext.getRouteController().startRoute(MyRoutes.QUEUE_ROUTE);
  }

  @Test
  public void routeTest() throws Exception {
    mockB.setExpectedCount(1);
    mockB.expectedBodiesReceived("---from template, Hello from Bean 1 times");

    mockLog.setExpectedCount(1);
    mockLog.expectedBodiesReceived("---from template, Hello from Bean 1 times");

    camelContext.createProducerTemplate().sendBody("direct:a", "---from template, ");

    mockB.assertIsSatisfied();
    mockLog.assertIsSatisfied();
  }
}