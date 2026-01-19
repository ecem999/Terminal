package com.example.Terminal.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Terminal.Entity.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

	public List<Passenger> findByBusId(Long id);
}
