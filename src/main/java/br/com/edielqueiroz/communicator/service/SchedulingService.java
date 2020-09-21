package br.com.edielqueiroz.communicator.service;

import java.util.Optional;

import br.com.edielqueiroz.communicator.model.Scheduling;

public interface SchedulingService {

	public Scheduling schedule(final Scheduling scheduling);

	public Optional<Scheduling> findById(Long id);

}
