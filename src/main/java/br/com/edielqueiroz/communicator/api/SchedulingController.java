package br.com.edielqueiroz.communicator.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.edielqueiroz.communicator.api.representation.SchedulingRepresentation;

@RestController
@RequestMapping(path = "/v1/schedules")
public class SchedulingController {

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> createScheduling(@RequestBody @Valid final SchedulingRepresentation scheduling)
			throws URISyntaxException {

		return ResponseEntity.created(new URI("/v1/schedules/" + UUID.randomUUID())).build();

	}

}
