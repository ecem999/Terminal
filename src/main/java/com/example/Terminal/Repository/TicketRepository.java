package com.example.Terminal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Terminal.Entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
