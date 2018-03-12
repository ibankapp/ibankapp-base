import java.io.UnsupportedEncodingException;
import javax.jms.Destination;
import javax.jms.JMSException;

import org.ibankapp.base.jmscore.IJmsCoreService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.mockrunner.mock.jms.MockQueue;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:mock-jms.xml")
public class JmsTest {
  @Autowired
  private IJmsCoreService jmsCoreService;
  @Autowired
  private MockQueue mockQueue;

  @Test
  public void TestSendAndReceiveMessage()
  {
    String xml = "abc";
    Destination destination = mockQueue;
    try {
      jmsCoreService.sendMessage(destination,xml.getBytes("UTF-8"));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }

    try {
      byte[] bRecv = jmsCoreService.receiveMessage(destination);
      String sRecv = new String(bRecv);
      Assert.assertEquals("abc",sRecv);
    } catch (JMSException e) {
      e.printStackTrace();
    }
  }

}