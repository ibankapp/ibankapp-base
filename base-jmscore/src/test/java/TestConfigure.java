import com.mockrunner.jms.ConfigurationManager;
import com.mockrunner.jms.DestinationManager;
import com.mockrunner.mock.jms.MockQueue;
import com.mockrunner.mock.jms.MockQueueConnectionFactory;
import org.ibankapp.base.jmscore.IJmsCoreService;
import org.ibankapp.base.jmscore.impl.JmsCoreService;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;

@Configurable
public class TestConfigure {

  @Bean
  public IJmsCoreService jmsCoreService() {
    return new JmsCoreService();
  }

  @Bean
  public DestinationManager destinationManager() {
    return new DestinationManager();
  }

  @Bean
  public MockQueue mockQueue() {
    return destinationManager().createQueue("demoQueue");
  }

  @Bean
  public ConfigurationManager configurationManager() {
    return new ConfigurationManager();
  }

  @Bean
  public MockQueueConnectionFactory jmsQueueConnectionFactory() {
    return new MockQueueConnectionFactory(destinationManager(), configurationManager());
  }

  @Bean
  public JmsTemplate jmsTemplate() {
    return new JmsTemplate(jmsQueueConnectionFactory());
  }
}
