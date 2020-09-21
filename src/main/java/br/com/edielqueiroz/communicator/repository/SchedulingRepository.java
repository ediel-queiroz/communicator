package br.com.edielqueiroz.communicator.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.edielqueiroz.communicator.model.Scheduling;
import br.com.edielqueiroz.communicator.model.Scheduling.Status;

@Repository
public interface SchedulingRepository extends JpaRepository<Scheduling, Long> {

	public List<Scheduling> findByStatus(final Status status);

}
