package br.com.edielqueiroz.communicator.api.representation;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SchedulingRepresentation implements Serializable {

	private static final long serialVersionUID = 6231827090330152533L;

	private String id;

	@JsonProperty("date_time")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@NotNull
	private LocalDateTime dateTime;

	@NotEmpty
	@Valid
	private Set<MessageRepresentation> messages;

	private String status;

}
