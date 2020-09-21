package br.com.edielqueiroz.communicator.api.representation;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class MessageRepresentation implements Serializable {

	private static final long serialVersionUID = 8418159074310112810L;

	@NotBlank
	private String type;

	@NotBlank
	private String sender;

	@NotBlank
	private String recipient;

	private String subject;

	@NotBlank
	private String content;

	private String status;

}
