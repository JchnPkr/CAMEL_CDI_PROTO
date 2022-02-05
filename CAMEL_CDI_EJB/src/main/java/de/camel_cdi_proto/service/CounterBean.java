
package de.camel_cdi_proto.service;

import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
@Named("counterBean")
public class CounterBean {
  private int counter;

  public String countUpHello(String body) {
    if (body == null) {
      body = "";
    }

    String beanExtendedBody = new StringBuilder(body).append("Hello from Bean " + ++counter + " times").toString();

    return beanExtendedBody;
  }
}
