
package de.camel_cdi_proto.route;

import javax.ejb.EJB;
import javax.inject.Inject;

import org.apache.camel.Endpoint;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.Uri;
import org.apache.logging.log4j.Logger;

import de.camel_cdi_proto.service.QueueFiller;

@DoNotDiscover
public class ProtoRoute extends RouteBuilder {
  public static String ZERLEGUNG_ROUTE = "zerlegungRoute";

  @Inject
  @Uri("timer:foo?period={{timer.interval}}&repeatCount=2")
  private Endpoint inputEndpoint;

  @Inject
  @Uri("jms:queue:{{jms.kista.queue}}?jmsMessageType=Text")
  private Endpoint queue1;

  @Inject
  private Logger logger;

  @EJB
  private QueueFiller queueFiller;

  @Override
  public void configure() {
    logger.info("--- starting route configure...");

    from(inputEndpoint).routeId(ZERLEGUNG_ROUTE)
        // .transacted("jtaRequiredPolicy")
        .bean(queueFiller)
        .log(LoggingLevel.INFO, "messages", "query result:  ${body}")
        .split(body())
        .log(LoggingLevel.INFO, "messages", "pusing to queue:  ${body}")
        .to(queue1);
  }
}
