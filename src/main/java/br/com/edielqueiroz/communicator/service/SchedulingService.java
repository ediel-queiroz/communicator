package br.com.edielqueiroz.communicator.service;

import java.util.List;
import java.util.Optional;

import br.com.edielqueiroz.communicator.model.Scheduling;
import br.com.edielqueiroz.communicator.model.Scheduling.Status;

public interface SchedulingService {

	public Scheduling schedule(final Scheduling scheduling);

	public Optional<Scheduling> findById(Long id);

	public Iterable<Scheduling> findAll();

	public void deleteById(final Long id);

	public List<Scheduling> findByStatus(final Status status);

}
