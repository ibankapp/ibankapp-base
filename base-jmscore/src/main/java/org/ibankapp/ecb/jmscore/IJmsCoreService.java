package org.ibankapp.ecb.jmscore;

import javax.jms.Destination;
import javax.jms.JMSException;


//版本变更说明
//Version1.0,:描述
/**
 * <br/>
 * ClassName:IJmsService.java <br/>
 * Package: <br/>
 * Description:TODO(描述类的作用)
 * 
 * @author
 * @since 1.0
 */
public interface IJmsCoreService {
    
	public void SendMessage(Destination destination, byte[] message);

	public byte[] ReceiveMessage(Destination destination) throws JMSException;

}
