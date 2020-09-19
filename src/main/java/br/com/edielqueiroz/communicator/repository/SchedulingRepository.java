package br.com.edielqueiroz.communicator.repository;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.edielqueiroz.communicator.model.Scheduling;

public interface SchedulingRepository extends PagingAndSortingRepository<Scheduling, UUID> {

}
