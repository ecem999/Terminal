package com.example.Terminal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Terminal.Entity.Passenger;
import com.example.Terminal.Entity.Ticket;
import com.example.Terminal.Repository.BusRepository;
import com.example.Terminal.Repository.PassengerRepository;
import com.example.Terminal.Repository.TicketRepository;

@RestController
@RequestMapping("passengers")
public class PassengerController {

	@Autowired
	PassengerRepository passRep;
	
	@Autowired
	BusRepository busRep;
	
	@Autowired
	TicketRepository tickRep;
	
	@GetMapping
	public List<Passenger> getPassengers(){
		return passRep.findAll();
	}
	
	@GetMapping("byBus/{id}")
	public List<Passenger> getPassengersByBus(@PathVariable Long id){
		return passRep.findByBusId(id);
	}
	
	@PostMapping("save")
	public String savePassenger(@RequestBody Passenger p) {
		passRep.save(p);
		return "Passenger Saved!";
	}
	
	@PostMapping("setTicket/{id}")
	public String setTicket(@RequestBody Ticket t, @PathVariable Long id) {
		Passenger p = passRep.findById(id).get();
		p.setTicket(t);
		t.setPassenger(p);
		passRep.save(p);
		return "Ticket assigned!";
	}
	
	@DeleteMapping("{id}")
	public String deletePassenger(@PathVariable Long id) {
		passRep.deleteById(id);
		return "Passenger Deleted!";
	}
}
