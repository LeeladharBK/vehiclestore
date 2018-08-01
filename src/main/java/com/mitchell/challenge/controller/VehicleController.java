package com.mitchell.challenge.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitchell.challenge.exception.VehicleNotFoundException;
import com.mitchell.challenge.model.Vehicle;
import com.mitchell.challenge.repository.VehicleRepository;

@RestController
@RequestMapping("/v1")
public class VehicleController {

	@Autowired
	private VehicleRepository veRepo;

	// Get all vehicles
	@GetMapping("/vehicles")
	public ResponseEntity<List<Vehicle>> getAllVehicles() {
		List<Vehicle> list = veRepo.findAll();
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(list);
	}

	// Get a Single Vehicle
	@GetMapping("/vehicles/{id}")
	public ResponseEntity<Vehicle> getVehicleById(@PathVariable(value = "id") Integer id) {
		Optional<Vehicle> vehicle = veRepo.findById(id);
		if (!vehicle.isPresent())
			throw new VehicleNotFoundException("id", id);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(vehicle.get());
	}

	// Create a new Vehicle
	@PostMapping("/vehicles")
	public ResponseEntity<Vehicle> storeVehicle(@Valid @RequestBody Vehicle vehicle) {
		Vehicle savedVehicle = veRepo.save(vehicle);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(savedVehicle);
	}

	// Update single Vehicle
	@PutMapping("/vehicles")
	public ResponseEntity<Vehicle> updateVehicle(@RequestBody Vehicle modifiedVehicle) {
		Vehicle updatedVehicle = veRepo.save(modifiedVehicle);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(updatedVehicle);
	}

	// Delete a Vehicle
	@DeleteMapping("/vehicles/{id}")
	public ResponseEntity<Vehicle> deleteVehicle(@PathVariable(value = "id") Integer id) {
		Optional<Vehicle> vehicle = veRepo.findById(id);
		if (!vehicle.isPresent())
			throw new VehicleNotFoundException("id", id);
		veRepo.delete(vehicle.get());
		return ResponseEntity.ok().build();
	}
}
