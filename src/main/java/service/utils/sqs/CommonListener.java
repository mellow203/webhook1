package service.utils.sqs;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.adwitt.service.QueueServing;

import lombok.extern.slf4j.Slf4j;

/**
 * Queue 처리 로직 호출
 */
@Slf4j
@Component
public class CommonListener {

	@Value("${thread-pool.service-size}")
	private int servicePS;

	@Autowired
	private SQSMessageCollector sqsMessageCollector;

	private boolean active;
	private ExecutorService executorService;


	@PostConstruct
	public void serviceListener() {

		executorService = Executors.newFixedThreadPool(servicePS);
		active = true;

		BlockingQueue<String> queue = sqsMessageCollector.getMessageBlockingQueue();

		new Thread(() -> {
			while (active) {
				try {
					String message = queue.take();
				//	executorService.execute(() -> );
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			//  종료 되기전에 처리 해야할 로직
			if (!queue.isEmpty()) {

			}

		}).start();
	}


/*	@PreDestroy
	private void destroy() {
		active = false;
		log.info("Listener Service STOP");
	}*/

}
