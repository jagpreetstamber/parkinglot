package com.bootcamp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ParkingLotTest {

  private ParkingOwner owner;

  public ParkingLotTest() {
    this.owner = new ParkingOwner();
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

    Response response = parkingLot.park(car);
    response = parkingLot.park(car);

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
    Response response = parkingLot.park(car);

    response = parkingLot.retrieveCar(car);

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
    TestParkingOwner owner = new TestParkingOwner();
    ParkingLot parkingLot = new ParkingLot(1, owner);

    Car car = new Car();

    Response response = parkingLot.park(car);

    assertTrue(response.isSuccess());
    assertEquals(owner.getFullNotificationCount(), expected);
  }

  @Test
  public void testOwnerMultipleNotificationWhenParkingLotIsFull() throws Exception {
    int expected = 3;
    TestParkingOwner owner = new TestParkingOwner();
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
    TestParkingOwner owner = new TestParkingOwner();
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
    TestParkingOwner owner = new TestParkingOwner();
    ParkingLot parkingLot = new ParkingLot(1, owner);

    Car car = new Car();

    parkingLot.park(car);
    parkingLot.retrieveCar(car);

    assertEquals(owner.getHasSpaceNotificationCount(), expected);
  }

  @Test
  public void testOwnerMultipleNotificationWhenParkingLotHasSpace() throws Exception {
    int expected = 3;
    TestParkingOwner owner = new TestParkingOwner();
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
    TestParkingOwner owner = new TestParkingOwner();
    ParkingLot parkingLot = new ParkingLot(1, owner);

    Car car = new Car();
    Car car1 = new Car();

    parkingLot.park(car);
    parkingLot.retrieveCar(car1);

    assertEquals(owner.getHasSpaceNotificationCount(), expected);
  }
}