package br.com.edielqueiroz.communicator.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.edielqueiroz.communicator.api.representation.SchedulingRepresentation;
import br.com.edielqueiroz.communicator.model.Scheduling;
import br.com.edielqueiroz.communicator.model.Scheduling.Status;
import br.com.edielqueiroz.communicator.service.SchedulingService;

@RestController
@RequestMapping(path = "/v1/schedules")
public class SchedulingController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SchedulingController.class);

	private ModelMapper modelMapper;
	private SchedulingService service;

	public SchedulingController(final SchedulingService service, final ModelMapper modelMapper) {
		this.service = service;
		this.modelMapper = modelMapper;
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> createScheduling(@RequestBody @Valid final SchedulingRepresentation schedulingRepresentation)
			throws URISyntaxException {

		try {
			LOGGER.info("[BEGIN] Creating scheduling...");
			Scheduling schedulingToCreate = convertToEntity(schedulingRepresentation);
			Scheduling createdScheduling = service.schedule(schedulingToCreate);

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(createdScheduling.getId()).toUri();
			return ResponseEntity.created(location).build();
		} finally {
			LOGGER.info("[END] Creating scheduling...");
		}

	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<SchedulingRepresentation> findScheduling(@PathVariable final Long id) {
		try {
			LOGGER.info("[BEGIN] Finding scheduling by id [{}]", id);
			Optional<Scheduling> scheduling = service.findById(id);
			SchedulingRepresentation representation = convertToRepresentation(scheduling.get());

			return ResponseEntity.ok(representation);
		} catch (IllegalArgumentException e) {
			LOGGER.error(e.getMessage(), e);
			return ResponseEntity.badRequest().build();
		} catch (NoSuchElementException e) {
			LOGGER.error(e.getMessage(), e);
			return ResponseEntity.notFound().build();
		} finally {
			LOGGER.info("[END] Finding scheduling by id");
		}
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<SchedulingRepresentation>> findSchedules(@RequestParam(required = false) final String status) {

		try {
			LOGGER.info("[BEGIN] Finding schedules...");
			Iterable<Scheduling> schedules;

			if (hasStatus(status)) {
				schedules = service.findByStatus(Status.valueOf(status));
			} else {
				schedules = service.findAll();
			}

			List<SchedulingRepresentation> representations = StreamSupport.stream(schedules.spliterator(), false)
					.map(s -> convertToRepresentation(s)).collect(Collectors.toList());

			return ResponseEntity.ok(representations);

		} catch (IllegalArgumentException | NullPointerException e) {
			LOGGER.error(e.getMessage(), e);
			return ResponseEntity.badRequest().build();
		} finally {
			LOGGER.info("[END] Finding schedules...");
		}

	}

	@DeleteMapping(path = "/{id}")
	ResponseEntity<?> deleteScheduling(@PathVariable final Long id) {
		try {
			LOGGER.info("[BEGIN] Deleting scheduling [ {} ]", id);
			service.deleteById(id);

			return ResponseEntity.noContent().build();
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error(e.getMessage(), e);
			return ResponseEntity.badRequest().build();
		} finally {
			LOGGER.info("[END] Scheduling [ {} ] deleted", id);
		}
	}

	private Scheduling convertToEntity(final SchedulingRepresentation representation) {
		return modelMapper.map(representation, Scheduling.class);
	}

	private SchedulingRepresentation convertToRepresentation(final Scheduling scheduling) {
		return modelMapper.map(scheduling, SchedulingRepresentation.class);
	}

	private boolean hasStatus(final String status) {
		return StringUtils.isNotBlank(status);
	}

}
