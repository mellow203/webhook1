package service;

import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * context 정보
 */
@Component
public class AppContext {

	public static final MultiValueMap<String, String> HEADER_SETTING = new LinkedMultiValueMap<>();
	public static final RestTemplate REST_TEMPLATE = new RestTemplate();

	static {
		HEADER_SETTING.set("Content-Type", "application/json;charset=UTF-8");
	}

}
