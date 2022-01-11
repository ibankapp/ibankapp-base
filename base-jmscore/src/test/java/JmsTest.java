import com.mockrunner.mock.jms.MockQueue;
import org.ibankapp.base.jmscore.IJmsCoreService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.Destination;
import javax.jms.JMSException;
import java.nio.charset.StandardCharsets;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {TestConfigure.class})
@ContextConfiguration("classpath:mock-jms.xml")
public class JmsTest {

  @Autowired
  private IJmsCoreService jmsCoreService;

  @Autowired
  private MockQueue mockQueue;

  @Test
  public void TestSendByDestinationAndReceiveByDestination() {
    String xml = "abc";
    Destination destination = mockQueue;
    jmsCoreService.sendMessage(destination, xml.getBytes(StandardCharsets.UTF_8));

    try {
      byte[] bRecv = jmsCoreService.receiveMessage(destination);
      String sRecv = new String(bRecv);
      Assert.assertEquals("abc", sRecv);
    } catch (JMSException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void TestSendByDestinationAndReceiveByQueueName() throws JMSException {
    String xml = "abc";
    MockQueue destination = mockQueue;
    System.out.println(destination.getQueueName());
    jmsCoreService.sendMessage(destination, xml.getBytes(StandardCharsets.UTF_8));

    byte[] bRecv = jmsCoreService.receiveMessage("demoMockRunnerQueue",1);
    String sRecv = new String(bRecv);
    Assert.assertEquals("abc", sRecv);
  }

  @Test
  public void TestSendByQueueNameAndReceiveByQueueName() {
    String xml = "abc";
    jmsCoreService.sendMessage("demoMockRunnerQueue", xml.getBytes(StandardCharsets.UTF_8));

    byte[] bRecv = jmsCoreService.receiveMessage("demoMockRunnerQueue",1000);
    String sRecv = new String(bRecv);
    Assert.assertEquals("abc", sRecv);
  }

}