package br.com.edielqueiroz.communicator.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Data;

@Data
@Entity
@SQLDelete(sql = "UPDATE scheduling SET status='DELETED' WHERE id=?")
@Where(clause = "status <> 'DELETED'")
public class Scheduling extends BaseEntity {

	private static final long serialVersionUID = 6268407084151018275L;

	@Column(name = "date_time")
	private LocalDateTime dateTime;

	@Enumerated(EnumType.STRING)
	private Status status;

	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "scheduling_id")
	private Set<Message> messages;

	public enum Status {
		PENDING, SENT, DELETED
	}

	@PrePersist
	public void prePersist() {
		this.status = Status.PENDING;
	}

}
