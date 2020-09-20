package br.com.edielqueiroz.communicator.api.representation;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SchedulingRepresentation implements Serializable {

	private static final long serialVersionUID = 6231827090330152533L;

	@NotBlank
	@JsonProperty("date_time")
	private String dateTime;

	@NotEmpty
	@Valid
	private List<MessageRepresentation> messages;

}
