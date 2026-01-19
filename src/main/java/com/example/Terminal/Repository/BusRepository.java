package com.example.Terminal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Terminal.Entity.Bus;

public interface BusRepository extends JpaRepository<Bus, Long> {

}
