package org.ibankapp.base.jmscore;

import javax.jms.Destination;
import javax.jms.JMSException;

public interface IJmsCoreService {

  void SendMessage(Destination destination, byte[] message);

  byte[] ReceiveMessage(Destination destination) throws JMSException;

}
