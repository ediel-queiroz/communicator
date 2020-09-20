package br.com.edielqueiroz.communicator.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.commons.io.IOUtils;
import org.hamcrest.text.StringContainsInOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
@AutoConfigureMockMvc
public class SchedulingApiTest {

	private SchedulingController controller;
	private MockMvc mockMvc;

	@Autowired
	public SchedulingApiTest(final SchedulingController controller, final MockMvc mockMvc) {
		this.controller = controller;
		this.mockMvc = mockMvc;
	}

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

	@Test
	public void shouldReturnHttp201WithLocationHeader() throws Exception {
		final String jsonFile = "/requests_json/should_return_http_201_with_location_header.json";
		this.mockMvc
				.perform(post("/v1/schedules").contentType(MediaType.APPLICATION_JSON)
						.content(IOUtils.toByteArray(getClass().getResourceAsStream(jsonFile))))
				.andExpect(status().isCreated()).andExpect(header().exists("Location"))
				.andExpect(header().string("Location", StringContainsInOrder.stringContainsInOrder("/v1/schedules/")));
	}

	@Test
	public void shouldReturnHttp201WithoutMessageSubject() throws Exception {
		final String jsonFile = "/requests_json/should_return_http_201_without_message_subject.json";
		this.mockMvc
				.perform(post("/v1/schedules").contentType(MediaType.APPLICATION_JSON)
						.content(IOUtils.toByteArray(getClass().getResourceAsStream(jsonFile))))
				.andExpect(status().isCreated()).andExpect(header().exists("Location"))
				.andExpect(header().string("Location", StringContainsInOrder.stringContainsInOrder("/v1/schedules/")));
	}

	@Test
	public void shouldReturnHttp400InRequestWithoutDateTime() throws Exception {
		final String jsonFile = "/requests_json/should_return_http_400_in_request_without_date_time.json";
		this.mockMvc
				.perform(post("/v1/schedules").contentType(MediaType.APPLICATION_JSON)
						.content(IOUtils.toByteArray(getClass().getResourceAsStream(jsonFile))))
				.andExpect(status().isBadRequest());

	}

	@Test
	public void shouldReturnHttp400InRequestWithoutMessage() throws Exception {
		final String jsonFile = "/requests_json/should_return_http_400_in_request_without_message.json";
		this.mockMvc
				.perform(post("/v1/schedules").contentType(MediaType.APPLICATION_JSON)
						.content(IOUtils.toByteArray(getClass().getResourceAsStream(jsonFile))))
				.andExpect(status().isBadRequest());

	}

	@Test
	public void shouldReturnHttp400InRequestWithoutMessageType() throws Exception {
		final String jsonFile = "/requests_json/should_return_http_400_in_request_without_message_type.json";
		this.mockMvc
				.perform(post("/v1/schedules").contentType(MediaType.APPLICATION_JSON)
						.content(IOUtils.toByteArray(getClass().getResourceAsStream(jsonFile))))
				.andExpect(status().isBadRequest());

	}

	@Test
	public void shouldReturnHttp400InRequestWithoutMessageSender() throws Exception {
		final String jsonFile = "/requests_json/should_return_http_400_in_request_without_message_sender.json";
		this.mockMvc
				.perform(post("/v1/schedules").contentType(MediaType.APPLICATION_JSON)
						.content(IOUtils.toByteArray(getClass().getResourceAsStream(jsonFile))))
				.andExpect(status().isBadRequest());

	}

	@Test
	public void shouldReturnHttp400InRequestWithoutMessageRecipient() throws Exception {
		final String jsonFile = "/requests_json/should_return_http_400_in_request_without_message_recipient.json";
		this.mockMvc
				.perform(post("/v1/schedules").contentType(MediaType.APPLICATION_JSON)
						.content(IOUtils.toByteArray(getClass().getResourceAsStream(jsonFile))))
				.andExpect(status().isBadRequest());

	}

	@Test
	public void shouldReturnHttp400InRequestWithoutMessageContent() throws Exception {
		final String jsonFile = "/requests_json/should_return_http_400_in_request_without_message_content.json";
		this.mockMvc
				.perform(post("/v1/schedules").contentType(MediaType.APPLICATION_JSON)
						.content(IOUtils.toByteArray(getClass().getResourceAsStream(jsonFile))))
				.andExpect(status().isBadRequest());

	}

}
