package br.com.edielqueiroz.communicator.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID uuid;
	private Type type;
	private String sender;
	private String recipient;
	private String subject;
	private String content;
	private Status status;

	@ManyToOne
	@JoinColumn(name = "scheduling_uuid")
	private Scheduling scheduling;

	public enum Type {
		EMAIL, SMS, PUSH, WHATSAPP
	}

	public enum Status {
		PENDING, SENT
	}

}
