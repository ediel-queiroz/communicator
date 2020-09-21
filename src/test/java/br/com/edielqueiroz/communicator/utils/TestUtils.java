package br.com.edielqueiroz.communicator.utils;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtils {

	public static String asJsonString(final Object object) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(object);
	}

	public static <T> List<T> asObject(final String json, Class<T> clazz)
			throws JsonMappingException, JsonProcessingException {
		final ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, clazz));

	}

}
