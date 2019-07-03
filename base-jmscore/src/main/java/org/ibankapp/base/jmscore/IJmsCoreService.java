package org.ibankapp.base.jmscore;

import javax.jms.Destination;
import javax.jms.JMSException;

public interface IJmsCoreService {

  void sendMessage(Destination destination, byte[] message);

  void sendMessage(String queueName, byte[] message);

  byte[] receiveMessage(Destination destination) throws JMSException;

  byte[] receiveMessage(String queueName);

  byte[] receiveMessage(Destination destination, long timeout);

  byte[] receiveMessage(String queueName, long timeout);

}
