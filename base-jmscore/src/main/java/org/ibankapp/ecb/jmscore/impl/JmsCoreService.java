package org.ibankapp.ecb.jmscore.impl;

import javax.annotation.Resource;
import javax.jms.BytesMessage;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibankapp.ecb.jmscore.IJmsCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.JmsUtils;


//版本变更说明
//Version1.0:描述
/**
 * <br/>
 * ClassName:JmsService.java <br/>
 * Package: <br/>
 * Description:TODO(描述类的作用)
 *
 * @author
 * @since 1.0
 */
public class JmsCoreService implements IJmsCoreService {

	@Resource
	JmsTemplate jmsTemplate;

	private static Log log = LogFactory.getLog(JmsCoreService.class);

	/**
		 * <br/>
		 * Title:TODO(方法名) <br/>
		 * Description:TODO(描述方法的作用)
		 *
		 * @param
		 * @see {PACKAGE_NAME}#方法名
		 */
	@Override
	public void SendMessage(Destination destination, final byte[] message) {
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

	/**
		 * <br/>
		 * Title:TODO(方法名) <br/>
		 * Description:TODO(描述方法的作用)
		 *
		 * @param
		 * @see {PACKAGE_NAME}#方法名
		 */
	@Override
	public byte[] ReceiveMessage(Destination destination) {
		byte[] msg = (byte[]) null;
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
			throw JmsUtils.convertJmsAccessException(e);
		}

		return msg;

	}

}
