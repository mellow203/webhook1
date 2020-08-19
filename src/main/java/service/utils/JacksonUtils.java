package service.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

/**
 * 설명
 */
public class JacksonUtils {

	public static final ObjectMapper JACKSON;

	static {
		JACKSON = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE)
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public static String toJson(Object obj) {
		try {
			return JACKSON.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			throw new IllegalStateException(e);
		}
	}

	public static <T> T fromJson(String json, Class<T> t) {
		try {
			return JACKSON.readValue(json, t);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> T fromJson(String json, TypeReference<T> t) {
		try {
			return JACKSON.readValue(json, t);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

}
