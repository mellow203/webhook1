package service.utils.sqs;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.amazon.sqs.javamessaging.SQSConnection;
import com.amazonaws.services.sqs.AmazonSQS;

import lombok.Data;
import lombok.extern.log4j.Log4j;

/**
 * Queue Message 수집
 */
@Log4j
@Component
@Data
public class SQSMessageCollector {

	@Value("${aws.sqs.se-request-message-name}")
	private String sqsRequestQueueName;

	@Autowired
	private AmazonSQS amazonSQS;

	@Autowired
	private SQSConnection sqsConnection;

	BlockingQueue<String> messageBlockingQueue = new ArrayBlockingQueue<>(1024);

	@PostConstruct
	private void messageCollector() throws JMSException {

		Session session = sqsConnection.createSession(false, Session.CLIENT_ACKNOWLEDGE);

		MessageConsumer consumer = sessZion.createConsumer(session.createQueue(sqsRequestQueueName));
		consumer.setMessageListener(message -> {
			try {
				log.info("Service GateWay Message :: " + ((TextMessage) message).getText());
				messageBlockingQueue.add(((TextMessage) message).getText());
				message.acknowledge();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		});

		sqsConnection.start();

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*@PreDestroy
	private void destory() {
		try {
			log.info("Collector Service STOP");
			sqsConnection.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}*/

}
