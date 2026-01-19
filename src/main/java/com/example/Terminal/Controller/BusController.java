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

import com.example.Terminal.Entity.Bus;
import com.example.Terminal.Entity.Passenger;
import com.example.Terminal.Repository.BusRepository;
import com.example.Terminal.Repository.PassengerRepository;
import com.example.Terminal.Repository.TicketRepository;

@RestController
@RequestMapping("buses")
public class BusController {

	@Autowired
	PassengerRepository passRep;
	
	@Autowired
	BusRepository busRep;
	
	@Autowired
	TicketRepository tickRep;

	@GetMapping
	public List<Bus> getBuses(){
		return busRep.findAll();
	}
	
	@PostMapping("save")
	public String saveBus(@RequestBody Bus b) {
		busRep.save(b);
		return "Bus saved!";
	}
	
	@GetMapping("addPassenger/{busId}/{passId}")
	public String addPassenger(@PathVariable Long busId, @PathVariable Long passId) {
		Bus b = busRep.findById(busId).get();
		Passenger p = passRep.findById(passId).get();
		b.addPassenger(p);
		busRep.save(b);
		
		return "Passenger added!";
	}
	
	@GetMapping("removePassenger/{passId}")
	public String removePassenger(@PathVariable Long passId) {
		Passenger p = passRep.findById(passId).get();
		p.setBus(null);
		passRep.save(p);
		
		return "Passenger removed!";
	}
	
	@DeleteMapping("{busId}")
	public String deleteBus(@PathVariable Long busId) {
		busRep.deleteById(busId);
		return "Bus Deleted!";
	}
	
	
	
	
	
	
}
