package com.bootcamp;

import com.bootcamp.event.CarNotFoundEvent;
import com.bootcamp.stubs.TestFbiAgent;
import com.bootcamp.stubs.TestParkingOwner;
import com.bootcamp.stubs.TestPoliceDepartment;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ParkingLotTest {

  private TestParkingOwner owner;
  private List<Subscriber> eightyPercentSubscribers;
  private List<Subscriber> carNotFoundSubscribers;

  @Before
  public void setUp() throws Exception {
    this.owner = new TestParkingOwner();
    this.eightyPercentSubscribers = new ArrayList<Subscriber>();
    this.carNotFoundSubscribers = new ArrayList<Subscriber>();
  }

  @Test
  public void testParkingIsSuccessful() throws Exception {
    ParkingLot parkingLot = new ParkingLot(2, owner);

    Car car = new Car();
    Response response = parkingLot.park(car);

    assertTrue(response.isSuccess());
  }

  @Test
  public void testParkingCarTwice() throws Exception {
    String expected = "Car Already Parked";
    Car car = new Car();
    ParkingLot parkingLot = new ParkingLot(2, owner);

    parkingLot.park(car);
    Response response = parkingLot.park(car);

    assertFalse(response.isSuccess());
    assertEquals(expected, response.getMessage());
  }

  @Test
  public void testParkingIsFull() throws Exception {

    ParkingLot parkingLot = new ParkingLot(0, owner);
    String expected = "Parking lot is full";
    Car car = new Car();

    Response response = parkingLot.park(car);

    assertFalse(response.isSuccess());
    assertEquals(expected, response.getMessage());
  }

  @Test
  public void testRetrieveIsSuccessful() throws Exception {
    ParkingLot parkingLot = new ParkingLot(2, owner);
    Car car = new Car();
    parkingLot.park(car);

    Response response = parkingLot.retrieveCar(car);

    assertTrue(response.isSuccess());
  }

  @Test
  public void testRetrieveACarThatIsNotParked() throws Exception {
    String expected = "Car not Parked";
    ParkingLot parkingLot = new ParkingLot(2, owner);
    Car car = new Car();

    Response response = parkingLot.retrieveCar(car);

    assertFalse(response.isSuccess());
    assertEquals(expected, response.getMessage());
  }

  @Test
  public void testOwnerNotificationWhenParkingLotIsFull() throws Exception {
    int expected = 1;
    ParkingLot parkingLot = new ParkingLot(1, owner);

    Car car = new Car();

    Response response = parkingLot.park(car);

    assertTrue(response.isSuccess());
    assertEquals(owner.getFullNotificationCount(), expected);
  }

  @Test
  public void testOwnerMultipleNotificationWhenParkingLotIsFull() throws Exception {
    int expected = 3;
    ParkingLot parkingLot = new ParkingLot(1, owner);

    Car car = new Car();

    parkingLot.park(car);
    parkingLot.retrieveCar(car);
    parkingLot.park(car);
    parkingLot.retrieveCar(car);
    parkingLot.park(car);

    assertEquals(owner.getFullNotificationCount(), expected);
  }

  @Test
  public void testOwnerShouldNotGetNotificationIfParkingIsFullAndSomeoneTriesToPark() throws Exception {
    int expected = 1;
    ParkingLot parkingLot = new ParkingLot(1, owner);

    Car car = new Car();
    Car car1 = new Car();

    parkingLot.park(car);
    parkingLot.park(car1);

    assertEquals(owner.getFullNotificationCount(), expected);
  }

  @Test
  public void testOwnerNotificationWhenParkingLotIsHasSpace() throws Exception {
    int expected = 1;
    ParkingLot parkingLot = new ParkingLot(1, owner);

    Car car = new Car();

    parkingLot.park(car);
    parkingLot.retrieveCar(car);

    assertEquals(owner.getHasSpaceNotificationCount(), expected);
  }

  @Test
  public void testOwnerMultipleNotificationWhenParkingLotHasSpace() throws Exception {
    int expected = 3;
    ParkingLot parkingLot = new ParkingLot(1, owner);

    Car car = new Car();

    parkingLot.park(car);
    parkingLot.retrieveCar(car);
    parkingLot.park(car);
    parkingLot.retrieveCar(car);
    parkingLot.park(car);
    parkingLot.retrieveCar(car);

    assertEquals(owner.getHasSpaceNotificationCount(), expected);
  }

  @Test
  public void testOwnerShouldNotGetNotificationIfParkingIsFullAndSomeoneTriesToRetriveUnparkedCar() throws Exception {
    int expected = 0;
    ParkingLot parkingLot = new ParkingLot(1, owner);

    Car car = new Car();
    Car car1 = new Car();

    parkingLot.park(car);
    parkingLot.retrieveCar(car1);

    assertEquals(owner.getHasSpaceNotificationCount(), expected);
  }

  @Test
  public void testFbiAgentShouldGetNotificationIfParkingIs80PercentFull() throws Exception {
    int expected = 1;
    TestFbiAgent agent = new TestFbiAgent();
    eightyPercentSubscribers.add(agent);
    ParkingLot parkingLot = new ParkingLot(5, owner);
    parkingLot.setEightyPercentSubscribers(eightyPercentSubscribers);

    Car car = new Car();
    Car car1 = new Car();
    Car car2 = new Car();
    Car car3 = new Car();
    Car car4 = new Car();

    parkingLot.park(car);
    parkingLot.park(car1);
    parkingLot.park(car2);
    parkingLot.park(car3);
    parkingLot.park(car4);

    assertEquals(expected, agent.getNotificationCount());
  }

  @Test
  public void testFbiAgentShouldGetNotificationIfParkingIs80PercentOnRetreive() throws Exception {
    int expected = 2;
    TestFbiAgent agent = new TestFbiAgent();
    eightyPercentSubscribers.add(agent);
    ParkingLot parkingLot = new ParkingLot(5, owner);
    parkingLot.setEightyPercentSubscribers(eightyPercentSubscribers);

    Car car = new Car();
    Car car1 = new Car();
    Car car2 = new Car();
    Car car3 = new Car();
    Car car4 = new Car();

    parkingLot.park(car);
    parkingLot.park(car1);
    parkingLot.park(car2);
    parkingLot.park(car3);
    parkingLot.park(car4);

    parkingLot.retrieveCar(car);
    parkingLot.retrieveCar(car1);

    assertEquals(expected, agent.getNotificationCount());
  }

  @Test
  public void testFbiAgentShouldNotGetMMultipleNotificationIfParkingIs80PercentOnRetreive() throws Exception {
    int expected = 2;
    TestFbiAgent agent = new TestFbiAgent();
    eightyPercentSubscribers.add(agent);
    ParkingLot parkingLot = new ParkingLot(5, owner);
    parkingLot.setEightyPercentSubscribers(eightyPercentSubscribers);

    Car car = new Car();
    Car car1 = new Car();
    Car car2 = new Car();
    Car car3 = new Car();
    Car car4 = new Car();

    parkingLot.park(car);
    parkingLot.park(car1);
    parkingLot.park(car2);
    parkingLot.park(car3);
    parkingLot.park(car4);

    parkingLot.retrieveCar(car);
    parkingLot.retrieveCar(car1);
    parkingLot.retrieveCar(car2);
    parkingLot.retrieveCar(car3);
    parkingLot.retrieveCar(car4);

    assertEquals(expected, agent.getNotificationCount());
  }

  @Test
  public void testPoliceDepartmentGetsNotificationIfCarIsNotFound() throws Exception {
    int expected = 1;
    ParkingLot parkingLot = new ParkingLot(5, owner);
    TestPoliceDepartment police = new TestPoliceDepartment();
    carNotFoundSubscribers.add(police);
    parkingLot.setCarNotFoundSubscribers(carNotFoundSubscribers);

    Car car = new Car();

    parkingLot.retrieveCar(car);

    assertEquals(expected, police.getNoOfCarsNotFound());
  }

  @Test
  public void testPoliceDepartmentGetsMultipleNotificationIfMultipleCarsNotFound() throws Exception {
    int expected = 2;
    ParkingLot parkingLot = new ParkingLot(5, owner);
    TestPoliceDepartment police = new TestPoliceDepartment();
    carNotFoundSubscribers.add(police);
    parkingLot.setCarNotFoundSubscribers(carNotFoundSubscribers);

    Car car = new Car();
    Car car1 = new Car();

    parkingLot.retrieveCar(car);
    parkingLot.retrieveCar(car1);

    assertEquals(expected, police.getNoOfCarsNotFound());
  }

  @Test
  public void testCorrectEventIsPassedToPoliceDepartment() throws Exception {
    int expected = 1;
    ParkingLot parkingLot = new ParkingLot(5, owner);
    TestPoliceDepartment police = new TestPoliceDepartment();
    carNotFoundSubscribers.add(police);
    parkingLot.setCarNotFoundSubscribers(carNotFoundSubscribers);
    Car exceptedCar = new Car();

    parkingLot.retrieveCar(exceptedCar);
    CarNotFoundEvent event = (CarNotFoundEvent) police.getEvent();
    Car actualCar = event.getCar();

    assertEquals(expected, police.getNoOfCarsNotFound());
    assertEquals(exceptedCar, actualCar);
  }
}