package br.com.edielqueiroz.communicator.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrePersist;

import lombok.Data;

@Data
@Entity
public class Message extends BaseEntity {

	private static final long serialVersionUID = 5256470338208528101L;

	@Enumerated(EnumType.STRING)
	private Type type;
	private String sender;
	private String recipient;
	private String subject;
	private String content;
	@Enumerated(EnumType.STRING)
	private Status status;

	private Scheduling scheduling;

	public enum Type {
		EMAIL, SMS, PUSH, WHATSAPP
	}

	public enum Status {
		PENDING, SENT
	}

	@PrePersist
	public void prePersist() {
		this.status = Status.PENDING;
	}

}
