package service.dto;

import java.util.List;
import java.util.Map;

import lombok.Data;

/**
 * Queue message DTO
 */
@Data
public class WittMessage {

	private PathParameters pathParameters;
	private String channel;
	private String accessToken;
	private String httpMethod;
	private String tid;
	private String requestPath;
	private String requestUri;

	private Map<String, Object> data;

	private Channel accounts;


	@Data
	public static class PathParameters {

		private String version;

	}


	@Data
	public static class Channel {

		private List<String> fb;
		private List<String> gg;

	}


}
