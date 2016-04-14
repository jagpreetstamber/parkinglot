package com.bootcamp;

import com.bootcamp.subscriber.ParkingOwner;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ParkingAttendantTest {

  @Test
  public void testParkingAttendantGivesFreeParkingLot() throws Exception {
    ParkingOwner owner = new ParkingOwner();
    ParkingLot freeLot = new ParkingLot(1, owner);
    ParkingLot fullLot = new ParkingLot(0, owner);
    ParkingAttendant attendant = new ParkingAttendant();
    attendant.add(freeLot);
    attendant.add(fullLot);
    Car car = new Car();

    Optional<ParkingLot> parkingLotOptional = attendant.getAvailableParkingLot();
    ParkingLot availableLot = parkingLotOptional.get();
    Response response = availableLot.park(car);

    assertEquals(freeLot, availableLot);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testParkingAttendantWhenAllLotsAreFull() throws Exception {
    ParkingOwner owner = new ParkingOwner();
    ParkingLot fullLot = new ParkingLot(0, owner);
    ParkingAttendant attendant = new ParkingAttendant();
    attendant.add(fullLot);

    Optional<ParkingLot> availableLot = attendant.getAvailableParkingLot();

    assertEquals(Optional.empty(), availableLot);
  }
}