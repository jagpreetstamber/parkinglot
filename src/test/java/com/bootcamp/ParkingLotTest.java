package com.bootcamp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jagpreet on 13/04/16.
 */
public class ParkingLotTest {

  @Test
  public void testParkingIsSuccessful() throws Exception {
    ParkingLot parkingLot = new ParkingLot(2);

    Car car = new Car();
    Response response = parkingLot.park(car);

    assertTrue(response.isSuccess());
  }

  @Test
  public void testParkingCarTwice() throws Exception {
    String expected = "Car Already Parked";
    Car car = new Car();
    ParkingLot parkingLot = new ParkingLot(2);

    Response response = parkingLot.park(car);
    response = parkingLot.park(car);

    assertFalse(response.isSuccess());
    assertEquals(expected, response.getMessage());
  }

  @Test
  public void testParkingIsFull() throws Exception {

    ParkingLot parkingLot = new ParkingLot(1);
    String expected = "Parking lot is full";
    Car car = new Car();
    Car car1 = new Car();

    Response response = parkingLot.park(car);
    response = parkingLot.park(car1);

    assertFalse(response.isSuccess());
    assertEquals(expected, response.getMessage());
  }

  @Test
  public void testRetrieveIsSuccessful() throws Exception {
    ParkingLot parkingLot = new ParkingLot(2);

    Car car = new Car();
    Response response = parkingLot.park(car);

    response = parkingLot.retrieveCar(car);

    assertTrue(response.isSuccess());
  }

  @Test
  public void testRetrieveACarThatIsNotParked() throws Exception {
    String expected = "Car not Parked";
    ParkingLot parkingLot = new ParkingLot(2);
    Car car = new Car();

    Response response = parkingLot.retrieveCar(car);

    assertFalse(response.isSuccess());
    assertEquals(expected, response.getMessage());
  }
}