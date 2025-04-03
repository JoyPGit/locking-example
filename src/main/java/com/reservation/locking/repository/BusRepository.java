package com.reservation.locking.repository;

import com.reservation.locking.entity.BusDetails;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusRepository extends CrudRepository<BusDetails, Long> {
    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    Optional<BusDetails> findWithLockById(long id); // change in service
}
