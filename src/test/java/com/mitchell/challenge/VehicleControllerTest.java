package com.mitchell.challenge;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.mitchell.challenge.controller.VehicleController;
import com.mitchell.challenge.exception.VehicleNotFoundException;
import com.mitchell.challenge.model.Vehicle;
import com.mitchell.challenge.repository.VehicleRepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VehicleStoreSolutionApplication.class)
public class VehicleControllerTest {

	@Autowired
	VehicleController vehicleController;

	@Autowired
	VehicleRepository vehicleRepo;

	@Before
	public void setupVehicle() {
		Vehicle vehicle = new Vehicle();

		vehicle.setYear(1999);
		vehicle.setMake("test-make");
		vehicle.setModel("test-model");

		vehicleRepo.save(vehicle);
	}

	@Test
	public void getAllVehiclesTest() {
		ResponseEntity<List<Vehicle>> resEntity = vehicleController.getAllVehicles();

		List<Vehicle> vehicle = (List<Vehicle>) resEntity.getBody();

		assertThat(resEntity.getStatusCode().toString()).isNotNull().isEqualTo("200");
		assertThat(vehicle.get(0).getMake()).isEqualTo("test-make");
	}

	@Test
	public void getVehicleByIdTest() {
		ResponseEntity<Vehicle> resEntity = vehicleController.getVehicleById(1);

		Vehicle vehicle = (Vehicle) resEntity.getBody();

		assertThat(resEntity.getStatusCode().toString()).isNotNull().isEqualTo("200");
		assertThat(vehicle.getMake()).isEqualTo("test-make");
	}

	@Test
	public void storeVehicleTest() {
		Vehicle vehicle = new Vehicle();

		vehicle.setYear(1999);
		vehicle.setMake("test-make");
		vehicle.setModel("test-model");

		ResponseEntity<Vehicle> resEntity = vehicleController.storeVehicle(vehicle);
		Vehicle storedVehicle = (Vehicle) resEntity.getBody();

		assertThat(storedVehicle).isNotNull();
		assertThat(resEntity.getStatusCode().toString()).isNotNull().isEqualTo("200");
		assertThat(storedVehicle.getMake()).isEqualTo(vehicle.getMake());
	}

	@Test
	public void updateVehicleTest() {
		Vehicle vehicle = new Vehicle();
		vehicle.setYear(1998);
		vehicle.setModel("test-model");
		vehicle.setMake("test-make");

		Vehicle savedVehicle = vehicleRepo.save(vehicle);
		savedVehicle.setMake("update-test-make");

		ResponseEntity<Vehicle> resEntity = vehicleController.updateVehicle(savedVehicle);
		Vehicle updatedVehicle = (Vehicle) resEntity.getBody();

		assertThat(updatedVehicle).isNotNull();
		assertThat(resEntity.getStatusCode().toString()).isNotNull().isEqualTo("200");
		assertThat(updatedVehicle.getMake()).isEqualTo(vehicle.getMake());
	}

	@Test(expected = VehicleNotFoundException.class)
	public void deleteVehicleTest() {
		ResponseEntity<Vehicle> resEntity1 = vehicleController.getVehicleById(1);
		Vehicle vehicle = (Vehicle) resEntity1.getBody();

		assertThat(vehicle).isNotNull();

		ResponseEntity<Vehicle> resEntity2 = vehicleController.deleteVehicle(1);

		Optional<Vehicle> deletedVehicle = vehicleRepo.findById(1);
		if (!deletedVehicle.isPresent())
			throw new VehicleNotFoundException("id", 1);

		assertThat(resEntity2.getStatusCode().toString()).isNotNull().isEqualTo("200");
		assertThat(vehicle.getMake()).isEqualTo("test-make");
	}

}
