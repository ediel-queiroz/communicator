package br.com.edielqueiroz.communicator.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.commons.io.IOUtils;
import org.hamcrest.Matchers;
import org.hamcrest.text.StringContainsInOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.edielqueiroz.communicator.api.representation.SchedulingRepresentation;
import br.com.edielqueiroz.communicator.utils.TestUtils;
import br.com.edielqueiroz.communicator.utils.TestsConstants;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "/sql_data/clear_data.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
public class SchedulingApiTests {

	private SchedulingController controller;
	private MockMvc mockMvc;

	@Autowired
	public SchedulingApiTests(final SchedulingController controller, final MockMvc mockMvc) {
		this.controller = controller;
		this.mockMvc = mockMvc;
	}

	@Test
	public void contextLoads() {
		assertThat(controller).isNotNull();
		assertThat(mockMvc).isNotNull();
	}

	@Test
	public void shouldScheduleCommunicationWithSuccess() throws Exception {
		final String jsonPath = TestsConstants.REQUESTS_JSON_API + "/should_schedule_communication_with_success.json";
		final byte[] jsonFile = IOUtils.toByteArray(getClass().getResourceAsStream(jsonPath));

		String location = this.mockMvc
				.perform(post(TestsConstants.API_URI).contentType(MediaType.APPLICATION_JSON).content(jsonFile))
				.andExpect(status().isCreated()).andExpect(header().exists(TestsConstants.LOCATION_HEADER))
				.andExpect(header().string(TestsConstants.LOCATION_HEADER,
						StringContainsInOrder.stringContainsInOrder(TestsConstants.API_URI + "/")))
				.andReturn().getResponse().getHeader(TestsConstants.LOCATION_HEADER);

		this.mockMvc.perform(get(location)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").exists()).andExpect(jsonPath("$.date_time").value("2020-09-25T10:15:30"))
				.andExpect(jsonPath("$.status").value("PENDING"));
	}

	@Test
	@Sql(scripts = "/sql_data/should_find_all_schedules_insert.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void shouldFindAllSchedules() throws Exception {
		this.mockMvc.perform(get(TestsConstants.API_URI)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", Matchers.hasSize(2)));

	}

	@Test
	@Sql(scripts = "/sql_data/clear_data.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void shouldFindAllSchedulesWithoutResults() throws Exception {
		this.mockMvc.perform(get(TestsConstants.API_URI)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", Matchers.hasSize(0))).andReturn().getResponse().getContentAsString();

	}

	@Test
	@Sql(scripts = "/sql_data/should_find_schedules_by_status_insert.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void shouldFindSchedulesByStatus() throws Exception {
		final String filter = "?status=PENDING";
		this.mockMvc.perform(get(TestsConstants.API_URI + filter)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", Matchers.hasSize(2)));

	}

	@Test
	@Sql(scripts = "/sql_data/should_delete_one_scheduling_with_success_insert.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void shouldDeleteOneSchedulingWithSuccess() throws Exception {
		final String filter = "?status=PENDING";

		final String schedulesJson = this.mockMvc.perform(get(TestsConstants.API_URI + filter))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)).andReturn()
				.getResponse().getContentAsString();

		String schedulingIdPath = "/" + TestUtils.asObject(schedulesJson, SchedulingRepresentation.class).stream()
				.map(s -> s.getId()).findFirst().get();

		this.mockMvc.perform(delete(TestsConstants.API_URI + schedulingIdPath)).andExpect(status().isNoContent());
		this.mockMvc.perform(get(TestsConstants.API_URI)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", Matchers.hasSize(1)));

	}

}
