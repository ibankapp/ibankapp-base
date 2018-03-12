package org.ibankapp.base.jmscore.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibankapp.base.exception.BaseException;
import org.ibankapp.base.jmscore.IJmsCoreService;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.JmsUtils;
import org.springframework.jms.support.destination.JmsDestinationAccessor;

import javax.annotation.Resource;
import javax.jms.*;


public class JmsCoreService implements IJmsCoreService {

  private static Log log = LogFactory.getLog(JmsCoreService.class);

  @Resource
  private JmsTemplate jmsTemplate;

  @Override
  public void sendMessage(Destination destination, final byte[] message) {
    jmsTemplate.send(destination, new MessageCreator() {
      public Message createMessage(Session session) {
        BytesMessage msg;

        try {
          msg = session.createBytesMessage();
          msg.writeBytes(message);
          log.debug("报文发送完成");
        } catch (JMSException e) {
          throw JmsUtils.convertJmsAccessException(e);
        }
        return msg;
      }
    });
  }

  @Override
  public byte[] receiveMessage(Destination destination) {
    return receiveMessage(destination, JmsDestinationAccessor.RECEIVE_TIMEOUT_INDEFINITE_WAIT);
  }

  @Override
  public byte[] receiveMessage(Destination destination, long timeout) {
    byte[] msg = null;

    jmsTemplate.setReceiveTimeout(timeout);
    Message message = jmsTemplate.receive(destination);

    try {
      if (message instanceof TextMessage) {
        msg = ((TextMessage) message).getText().getBytes();
      } else if (message instanceof BytesMessage) {
        BytesMessage bMsg = (BytesMessage) message;
        msg = new byte[(int) bMsg.getBodyLength()];
        bMsg.readBytes(msg);
      } else if (message instanceof ObjectMessage) {
        ObjectMessage oMsg = (ObjectMessage) message;
        msg = (byte[]) oMsg.getObject();
      }
    } catch (JMSException e) {
      throw new BaseException("E-BASE-000001", "接收报文发生错误").initCause(e);
    }

    return msg;

  }

}
