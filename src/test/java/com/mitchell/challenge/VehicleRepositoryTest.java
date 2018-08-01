package com.mitchell.challenge;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.mitchell.challenge.model.Vehicle;
import com.mitchell.challenge.repository.VehicleRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VehicleStoreSolutionApplication.class)
public class VehicleRepositoryTest {

	@Autowired
	private VehicleRepository veRepo;

	@Before
	public void setupVehicle() {
		Vehicle vehicle = new Vehicle();

		vehicle.setYear(1997);
		vehicle.setMake("test-Make");
		vehicle.setModel("test-Model");

		veRepo.save(vehicle);
	}

	@Test
	public void findByIdTest() {
		Optional<Vehicle> foundVehicle = veRepo.findById(1);

		assertThat(foundVehicle.get().getMake().toString()).isEqualTo("test-Make");
		assertThat(foundVehicle.get().getModel().toString()).isEqualTo("test-Model");
	}

	@Test
	public void postVehicleTest() {
		Vehicle vehicle = new Vehicle();

		vehicle.setYear(1999);
		vehicle.setMake("post-test");
		vehicle.setModel("post-test");

		Vehicle storeVehicle = veRepo.save(vehicle);

		assertThat(storeVehicle).isNotNull();
		assertThat(storeVehicle.getMake()).isEqualTo(vehicle.getMake());
		assertThat(storeVehicle.getModel()).isEqualTo(vehicle.getModel());
	}

	@Test
	public void putVehicleTest() {

		Vehicle vehicle = new Vehicle();

		vehicle.setYear(1999);
		vehicle.setModel("put-test");
		vehicle.setMake("put-test");

		Vehicle originalVehicle = new Vehicle();
		originalVehicle.setModel(vehicle.getModel());

		Vehicle storedVehicle = veRepo.save(vehicle);

		assertThat(veRepo.findById(storedVehicle.getId())).isNotNull();

		storedVehicle.setMake("put-make");

		Vehicle updatedVehicle = veRepo.save(storedVehicle);

		assertThat(updatedVehicle).isNotNull();
		assertThat(updatedVehicle.getId()).isEqualTo(storedVehicle.getId());
		assertThat(updatedVehicle.getMake()).isNotEqualTo(originalVehicle.getMake());
		assertThat(updatedVehicle.getMake()).isEqualTo(vehicle.getMake());
	}

	@Test
	public void deleteVehicleTest() {
		Vehicle vehicle = new Vehicle();

		vehicle.setYear(1999);
		vehicle.setModel("delete-test");
		vehicle.setMake("delete-test");

		Vehicle storedVehicle = veRepo.save(vehicle);

		assertThat(veRepo.findById(storedVehicle.getId())).isNotNull();

		veRepo.deleteById(storedVehicle.getId());
		assertThat(veRepo.findById(storedVehicle.getId()).isPresent()).isEqualTo(false);

	}
}
