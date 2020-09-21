package br.com.edielqueiroz.communicator.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.edielqueiroz.communicator.model.Scheduling;
import br.com.edielqueiroz.communicator.repository.SchedulingRepository;

@Service
public class SchedulingServiceImpl implements SchedulingService {

	private final SchedulingRepository repository;

	public SchedulingServiceImpl(final SchedulingRepository repository) {
		this.repository = repository;
	}

	@Override
	public Scheduling schedule(Scheduling scheduling) {
		return repository.save(scheduling);
	}

	@Override
	public Optional<Scheduling> findById(Long id) {
		return repository.findById(id);
	}

}
