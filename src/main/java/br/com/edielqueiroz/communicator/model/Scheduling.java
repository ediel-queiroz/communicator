package br.com.edielqueiroz.communicator.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Scheduling {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID uuid;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private LocalDateTime dateTime;
	private Status status;

	public enum Status {
		PENDING, SENT
	}
}
