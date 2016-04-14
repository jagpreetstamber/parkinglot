package com.bootcamp;

import com.bootcamp.subscriber.ParkingOwner;
import org.junit.Test;

import java.util.Arrays;
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
    Car car = new Car(Car.CarType.HATCHBACK, false);

    Optional<ParkingLot> parkingLotOptional = attendant.getAvailableParkingLot(Car.CarType.HATCHBACK, false);
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

    Optional<ParkingLot> availableLot = attendant.getAvailableParkingLot(Car.CarType.HATCHBACK, false);

    assertEquals(Optional.empty(), availableLot);
  }

  @Test
  public void testParkingAttendantAllocatesLotsInRoundRobin() throws Exception {

    ParkingOwner owner = new ParkingOwner();
    ParkingLot firstFreeLot = new ParkingLot(2, owner);
    ParkingLot secondFreeLot = new ParkingLot(2, owner);
    ParkingAttendant attendant = new ParkingAttendant(Arrays.asList(firstFreeLot, secondFreeLot));

    ParkingLot firstAvailableLot = attendant.getAvailableParkingLot(Car.CarType.HATCHBACK, false).get();
    ParkingLot secondAvailableLot = attendant.getAvailableParkingLot(Car.CarType.HATCHBACK, false).get();

    assertEquals(firstFreeLot, firstAvailableLot);
    assertEquals(secondFreeLot, secondAvailableLot);
  }

  @Test
  public void testParkingAttendantAllocatesLotsIfOnlyOneIsAvailable() throws Exception {

    ParkingOwner owner = new ParkingOwner();
    ParkingLot freeLot = new ParkingLot(2, owner);
    ParkingLot fullLot = new ParkingLot(0, owner);
    ParkingAttendant attendant = new ParkingAttendant(Arrays.asList(freeLot, fullLot));

    ParkingLot firstAvailableLot = attendant.getAvailableParkingLot(Car.CarType.HATCHBACK, false).get();
    ParkingLot secondAvailableLot = attendant.getAvailableParkingLot(Car.CarType.HATCHBACK, false).get();

    assertEquals(freeLot, firstAvailableLot);
    assertEquals(freeLot, secondAvailableLot);
  }

  @Test
  public void testParkingAttendantAllocatesLotsEvenly() throws Exception {

    ParkingOwner owner = new ParkingOwner();
    ParkingLot firstFreeLot = new ParkingLot(2, owner);
    ParkingLot fullLot = new ParkingLot(0, owner);
    ParkingLot secondFreeLot = new ParkingLot(2, owner);
    ParkingAttendant attendant = new ParkingAttendant(Arrays.asList(firstFreeLot, fullLot, secondFreeLot));


    ParkingLot firstAvailableLot = attendant.getAvailableParkingLot(Car.CarType.HATCHBACK, false).get();
    ParkingLot secondAvailableLot = attendant.getAvailableParkingLot(Car.CarType.HATCHBACK, false).get();
    ParkingLot thirdAvailableLot = attendant.getAvailableParkingLot(Car.CarType.HATCHBACK, false).get();

    assertEquals(firstFreeLot, firstAvailableLot);
    assertEquals(secondFreeLot, secondAvailableLot);
    assertEquals(firstFreeLot, thirdAvailableLot);
  }

  @Test
  public void testParkingAttendantAllocatesMoreSpaceLotsForLargeCars() throws Exception {

    ParkingOwner owner = new ParkingOwner();
    ParkingLot firstFreeLot = new ParkingLot(1, owner);
    ParkingLot secondFreeLot = new ParkingLot(2, owner);
    ParkingLot thirdFreeLot = new ParkingLot(4, owner);
    ParkingAttendant attendant = new ParkingAttendant(Arrays.asList(firstFreeLot, secondFreeLot, thirdFreeLot));
    Car car1 = new Car(Car.CarType.SEDAN, false);
    Car car2 = new Car(Car.CarType.SEDAN, false);
    Car car3 = new Car(Car.CarType.SEDAN, false);

    ParkingLot firstAvailableLot = attendant.getAvailableParkingLot(car1.getType(), false).get();
    firstAvailableLot.park(car1);
    ParkingLot secondAvailableLot = attendant.getAvailableParkingLot(car2.getType(), false).get();
    secondAvailableLot.park(car2);
    ParkingLot thirdAvailableLot = attendant.getAvailableParkingLot(car3.getType(), false).get();

    assertEquals(thirdFreeLot, firstAvailableLot);
    assertEquals(thirdFreeLot, secondAvailableLot);
    assertEquals(secondFreeLot, thirdAvailableLot);
  }

  @Test
  public void testParkingAttendantAllocatesMoreSpaceLotsForLargeCarsAndRoundRobinForSmallCars() throws Exception {

    ParkingOwner owner = new ParkingOwner();
    ParkingLot firstFreeLot = new ParkingLot(1, owner);
    ParkingLot secondFreeLot = new ParkingLot(2, owner);
    ParkingLot thirdFreeLot = new ParkingLot(4, owner);
    ParkingAttendant attendant = new ParkingAttendant(Arrays.asList(firstFreeLot, secondFreeLot, thirdFreeLot));
    Car car1 = new Car(Car.CarType.HATCHBACK, false);
    Car car2 = new Car(Car.CarType.SEDAN, false);
    Car car3 = new Car(Car.CarType.HATCHBACK, false);

    ParkingLot firstAvailableLot = attendant.getAvailableParkingLot(car1.getType(), false).get();
    firstAvailableLot.park(car1);
    ParkingLot secondAvailableLot = attendant.getAvailableParkingLot(car2.getType(), false).get();
    secondAvailableLot.park(car2);
    ParkingLot thirdAvailableLot = attendant.getAvailableParkingLot(car3.getType(), false).get();

    assertEquals(firstFreeLot, firstAvailableLot);
    assertEquals(thirdFreeLot, secondAvailableLot);
    assertEquals(secondFreeLot, thirdAvailableLot);
  }

  @Test
  public void testParkingAttendantAllocatesMoreSpaceLotsForLargeCarsInSequence() throws Exception {

    ParkingOwner owner = new ParkingOwner();
    ParkingLot firstFreeLot = new ParkingLot(1, owner);
    ParkingLot secondFreeLot = new ParkingLot(1, owner);
    ParkingLot thirdFreeLot = new ParkingLot(1, owner);
    ParkingAttendant attendant = new ParkingAttendant(Arrays.asList(firstFreeLot, secondFreeLot, thirdFreeLot));
    Car car1 = new Car(Car.CarType.SEDAN, false);
    Car car2 = new Car(Car.CarType.SEDAN, false);
    Car car3 = new Car(Car.CarType.SEDAN, false);

    ParkingLot firstAvailableLot = attendant.getAvailableParkingLot(car1.getType(), false).get();
    firstAvailableLot.park(car1);
    ParkingLot secondAvailableLot = attendant.getAvailableParkingLot(car2.getType(), false).get();
    secondAvailableLot.park(car2);
    ParkingLot thirdAvailableLot = attendant.getAvailableParkingLot(car3.getType(), false).get();

    assertEquals(firstFreeLot, firstAvailableLot);
    assertEquals(secondFreeLot, secondAvailableLot);
    assertEquals(thirdFreeLot, thirdAvailableLot);
  }

  @Test
  public void testParkingAttendantAllocatesFirstLotForHandicappedAndMoreSpaceForLargeCars() throws Exception {

    ParkingOwner owner = new ParkingOwner();
    ParkingLot firstFreeLot = new ParkingLot(2, owner);
    ParkingLot secondFreeLot = new ParkingLot(2, owner);
    ParkingLot thirdFreeLot = new ParkingLot(3, owner);
    ParkingAttendant attendant = new ParkingAttendant(Arrays.asList(firstFreeLot, secondFreeLot, thirdFreeLot));
    Car car1 = new Car(Car.CarType.SEDAN, true);
    Car car2 = new Car(Car.CarType.SEDAN, false);

    ParkingLot firstAvailableLot = attendant.getAvailableParkingLot(car1.getType(), car1.isHandicapped()).get();
    firstAvailableLot.park(car1);
    ParkingLot secondAvailableLot = attendant.getAvailableParkingLot(car2.getType(), car2.isHandicapped()).get();
    secondAvailableLot.park(car2);

    assertEquals(firstFreeLot, firstAvailableLot);
    assertEquals(thirdFreeLot, secondAvailableLot);
  }

  @Test
  public void testParkingAttendantAllocatesFirstLotForHandicappedAndRoundRobinForSmallCars() throws Exception {

    ParkingOwner owner = new ParkingOwner();
    ParkingLot firstFreeLot = new ParkingLot(2, owner);
    ParkingLot secondFreeLot = new ParkingLot(2, owner);
    ParkingLot thirdFreeLot = new ParkingLot(3, owner);
    ParkingAttendant attendant = new ParkingAttendant(Arrays.asList(firstFreeLot, secondFreeLot, thirdFreeLot));
    Car car1 = new Car(Car.CarType.HATCHBACK, false);
    Car car2 = new Car(Car.CarType.HATCHBACK, true);

    ParkingLot firstAvailableLot = attendant.getAvailableParkingLot(car1.getType(), car1.isHandicapped()).get();
    firstAvailableLot.park(car1);
    ParkingLot secondAvailableLot = attendant.getAvailableParkingLot(car2.getType(), car2.isHandicapped()).get();
    secondAvailableLot.park(car2);

    assertEquals(firstFreeLot, firstAvailableLot);
    assertEquals(firstFreeLot, secondAvailableLot);
  }

  @Test
  public void testParkingAttendantAllocatesFirstLotForLargeHandicappedAndRoundRobinForSmallCars() throws Exception {

    ParkingOwner owner = new ParkingOwner();
    ParkingLot firstFreeLot = new ParkingLot(2, owner);
    ParkingLot secondFreeLot = new ParkingLot(2, owner);
    ParkingLot thirdFreeLot = new ParkingLot(3, owner);
    ParkingAttendant attendant = new ParkingAttendant(Arrays.asList(firstFreeLot, secondFreeLot, thirdFreeLot));
    Car car1 = new Car(Car.CarType.SEDAN, true);
    Car car2 = new Car(Car.CarType.HATCHBACK, true);

    ParkingLot firstAvailableLot = attendant.getAvailableParkingLot(car1.getType(), car1.isHandicapped()).get();
    firstAvailableLot.park(car1);
    ParkingLot secondAvailableLot = attendant.getAvailableParkingLot(car2.getType(), car2.isHandicapped()).get();
    secondAvailableLot.park(car2);

    assertEquals(firstFreeLot, firstAvailableLot);
    assertEquals(firstFreeLot, secondAvailableLot);
  }
}