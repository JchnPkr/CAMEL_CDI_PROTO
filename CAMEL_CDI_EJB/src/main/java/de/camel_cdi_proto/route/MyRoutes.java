
package de.camel_cdi_proto.route;

import javax.inject.Inject;

import org.apache.camel.Endpoint;
import org.apache.camel.EndpointInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.logging.log4j.Logger;

import de.camel_cdi_proto.service.CounterBean;

@DoNotDiscover
public class MyRoutes extends RouteBuilder {
  public static String TIMER_ROUTE = "timerRoute";
  public static String QUEUE_ROUTE = "queueRoute";

  @EndpointInject("direct:a")
  private Endpoint inputEndpoint;

  @EndpointInject("direct:b")
  private Endpoint queue1;

  @EndpointInject("log:messages")
  private Endpoint resultEndpoint;

  @Inject
  private Logger logger;

  @Inject
  CounterBean counterBean;

  @Override
  public void configure() {
    logger.info("--- starting demo route configure...");

    // Injected endpoints can't be mocked reliably, so use their uri
    from(inputEndpoint.getEndpointUri()).routeId(TIMER_ROUTE)
        .bean(counterBean)
        .to(queue1.getEndpointUri());

    from(queue1.getEndpointUri()).routeId(QUEUE_ROUTE)
        .to("log:messages");
  }
}
