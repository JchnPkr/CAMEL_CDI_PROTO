
package de.camel_cdi_proto.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import org.apache.logging.log4j.Logger;

import de.camel_cdi_proto.dao.IDemoDAO;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class QueueFiller {
  @Inject
  private Logger logger;

  @Inject
  private IDemoDAO demoDAO;

  public List<Long> getData() {
    List<Long> data = new ArrayList<Long>();

      this.logger.debug("getting data from db");
      data = demoDAO.getData();

    this.logger.info("retrieved data size", data.size());

    return data;
  }
}