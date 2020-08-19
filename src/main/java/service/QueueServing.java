package service;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;

import com.adwitt.service.dto.WittMessage;
import com.adwitt.service.utils.JacksonUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by YeoJin on 2017-06-15.
 */
@Slf4j
@Component
public class QueueServing {

	public void call(String message) {

		WittMessage wittMessage = JacksonUtils.fromJson(message, WittMessage.class);

		String innerCallPath = "http://localhost:8082/" + wittMessage.getRequestUri();
		AppContext.REST_TEMPLATE
				.postForEntity(innerCallPath, new HttpEntity<>(message, AppContext.HEADER_SETTING), String.class);

	}
}
