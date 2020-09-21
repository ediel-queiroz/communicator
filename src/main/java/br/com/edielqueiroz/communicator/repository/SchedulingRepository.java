package br.com.edielqueiroz.communicator.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.edielqueiroz.communicator.model.Scheduling;

@Repository
public interface SchedulingRepository extends PagingAndSortingRepository<Scheduling, Long> {

}
