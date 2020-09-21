package br.com.edielqueiroz.communicator.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.commons.io.IOUtils;
import org.hamcrest.text.StringContainsInOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import br.com.edielqueiroz.communicator.utils.TestsConstants;

@SpringBootTest
@AutoConfigureMockMvc
public class SchedulingApiSchemaTests {

	private SchedulingController controller;
	private MockMvc mockMvc;

	@Autowired
	public SchedulingApiSchemaTests(final SchedulingController controller, final MockMvc mockMvc) {
		this.controller = controller;
		this.mockMvc = mockMvc;
	}

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
		assertThat(mockMvc).isNotNull();
	}

	@Test
	public void shouldReturnHttp201WithLocationHeader() throws Exception {
		final String jsonFile = TestsConstants.REQUESTS_JSON_SCHEMA_VALIDATION
				+ "/should_return_http_201_with_location_header.json";
		this.mockMvc
				.perform(post(TestsConstants.API_URI).contentType(MediaType.APPLICATION_JSON)
						.content(IOUtils.toByteArray(getClass().getResourceAsStream(jsonFile))))
				.andExpect(status().isCreated()).andExpect(header().exists(TestsConstants.LOCATION_HEADER))
				.andExpect(header().string(TestsConstants.LOCATION_HEADER,
						StringContainsInOrder.stringContainsInOrder(TestsConstants.API_URI + "/")));
	}

	@Test
	public void shouldReturnHttp201WithoutMessageSubject() throws Exception {
		final String jsonFile = TestsConstants.REQUESTS_JSON_SCHEMA_VALIDATION
				+ "/should_return_http_201_without_message_subject.json";
		this.mockMvc
				.perform(post(TestsConstants.API_URI).contentType(MediaType.APPLICATION_JSON)
						.content(IOUtils.toByteArray(getClass().getResourceAsStream(jsonFile))))
				.andExpect(status().isCreated()).andExpect(header().exists(TestsConstants.LOCATION_HEADER))
				.andExpect(header().string(TestsConstants.LOCATION_HEADER,
						StringContainsInOrder.stringContainsInOrder(TestsConstants.API_URI + "/")));
	}

	@Test
	public void shouldReturnHttp400InRequestWithoutDateTime() throws Exception {
		final String jsonFile = TestsConstants.REQUESTS_JSON_SCHEMA_VALIDATION
				+ "/should_return_http_400_in_request_without_date_time.json";
		this.mockMvc
				.perform(post(TestsConstants.API_URI).contentType(MediaType.APPLICATION_JSON)
						.content(IOUtils.toByteArray(getClass().getResourceAsStream(jsonFile))))
				.andExpect(status().isBadRequest()).andDo(print());

	}

	@Test
	public void shouldReturnHttp400InRequestWithoutMessage() throws Exception {
		final String jsonFile = TestsConstants.REQUESTS_JSON_SCHEMA_VALIDATION
				+ "/should_return_http_400_in_request_without_message.json";
		this.mockMvc
				.perform(post(TestsConstants.API_URI).contentType(MediaType.APPLICATION_JSON)
						.content(IOUtils.toByteArray(getClass().getResourceAsStream(jsonFile))))
				.andExpect(status().isBadRequest());

	}

	@Test
	public void shouldReturnHttp400InRequestWithoutMessageType() throws Exception {
		final String jsonFile = TestsConstants.REQUESTS_JSON_SCHEMA_VALIDATION
				+ "/should_return_http_400_in_request_without_message_type.json";
		this.mockMvc
				.perform(post(TestsConstants.API_URI).contentType(MediaType.APPLICATION_JSON)
						.content(IOUtils.toByteArray(getClass().getResourceAsStream(jsonFile))))
				.andExpect(status().isBadRequest());

	}

	@Test
	public void shouldReturnHttp400InRequestWithoutMessageSender() throws Exception {
		final String jsonFile = TestsConstants.REQUESTS_JSON_SCHEMA_VALIDATION
				+ "/should_return_http_400_in_request_without_message_sender.json";
		this.mockMvc
				.perform(post(TestsConstants.API_URI).contentType(MediaType.APPLICATION_JSON)
						.content(IOUtils.toByteArray(getClass().getResourceAsStream(jsonFile))))
				.andExpect(status().isBadRequest());

	}

	@Test
	public void shouldReturnHttp400InRequestWithoutMessageRecipient() throws Exception {
		final String jsonFile = TestsConstants.REQUESTS_JSON_SCHEMA_VALIDATION
				+ "/should_return_http_400_in_request_without_message_recipient.json";
		this.mockMvc
				.perform(post(TestsConstants.API_URI).contentType(MediaType.APPLICATION_JSON)
						.content(IOUtils.toByteArray(getClass().getResourceAsStream(jsonFile))))
				.andExpect(status().isBadRequest());

	}

	@Test
	public void shouldReturnHttp400InRequestWithoutMessageContent() throws Exception {
		final String jsonFile = TestsConstants.REQUESTS_JSON_SCHEMA_VALIDATION
				+ "/should_return_http_400_in_request_without_message_content.json";
		this.mockMvc
				.perform(post(TestsConstants.API_URI).contentType(MediaType.APPLICATION_JSON)
						.content(IOUtils.toByteArray(getClass().getResourceAsStream(jsonFile))))
				.andExpect(status().isBadRequest());

	}

}
