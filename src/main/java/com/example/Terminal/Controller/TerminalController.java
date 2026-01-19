package com.example.Terminal.Controller;

import java.util.List;
import java.util.Optional;

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

@RestController
@RequestMapping("/api")
public class TerminalController {

	@Autowired
	BusRepository busRep;
	
	@Autowired
	PassengerRepository passRep;
	
	@GetMapping("/buses")
	public List<Bus> getBuses() {
		return busRep.findAll();
	}
	
	@GetMapping("/bus/{busId}")
	public Optional<Bus> getABus(@PathVariable Long busId){
		return busRep.findById(busId);
	}
	
	@GetMapping("/company/buses/{companyId}")
	public List<Bus> getBusesByCompanyId(@PathVariable Long companyId){
		return busRep.findByCompanyId(companyId);
	}
	
	@GetMapping("/bus/passengers/{busId}")
	public List<Passenger> getPassengersByBusId(@PathVariable Long busId){
		
	    Bus bus = busRep.findById(busId).orElseThrow();
	    return bus.getPassengers();
	}
	
	@PostMapping("/bus/passenger/{busId}")
	public String newPassenger(@PathVariable Long busId , @RequestBody Passenger passenger) {
		
		Optional<Bus> bus = busRep.findById(busId);
		
		if(bus.isPresent()) {
			
			passenger.setBus(bus.get());
			
			passRep.save(passenger);
			return "Passenger saved to the bus!";
		}
		else {
			
			return "Error : Bus with id"+ busId +"couldnt find.";
		}
	}
	
	@DeleteMapping("/bus/passenger/{busId}")
	public String removePassengersByBusId(@PathVariable Long busId) {
		
		passRep.deleteByBusId(busId);
		return "All passengers for Bus ID : " + busId + "successfully removed!";
	}
}
