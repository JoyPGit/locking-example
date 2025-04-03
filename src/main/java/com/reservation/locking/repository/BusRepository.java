package com.reservation.locking.repository;

import com.reservation.locking.entity.BusDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRepository extends CrudRepository<BusDetails, Long> {

}
