package com.bootcamp;

import com.bootcamp.exception.AlreadyParkedException;
import com.bootcamp.exception.NotParkedException;
import com.bootcamp.exception.ParkingFullException;
import com.bootcamp.exception.ParkingLotException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jagpreet on 13/04/16.
 */
public class ParkingLot {

  private int noOfSlots;
  private List<Integer> occupiedSlots;
  private List<Integer> freeSlots;
  private Map<Car, Integer> parkingSlots;

  public ParkingLot(int noOfSlots) {
    this.noOfSlots = noOfSlots;
    freeSlots = new ArrayList<Integer>();
    occupiedSlots = new ArrayList<Integer>();
    this.parkingSlots = new HashMap<Car, Integer>();
    initializeSlots(noOfSlots);
  }

  public Response park(Car car) {
    Response response = new Response();

    try {
      checkIfCarIsParked(car);
      isParkingSlotAvailable();

      Integer freeSlot = getFreeSlot();
      freeSlots.remove(freeSlot);
      occupiedSlots.add(freeSlot);

      parkingSlots.put(car, freeSlot);

      response.setSuccess(true);
      response.setMessage("Parked successfully");
      response.setParkingSlotNumber(freeSlot);

    } catch (ParkingLotException e) {
      response.setSuccess(false);
      response.setMessage(e.getMessage());
    }

    return response;
  }

  public Response retrieveCar(Car car) {
    Response response = new Response();

    try {
      checkIfCarIsNotParked(car);

      Integer slotNo = parkingSlots.get(car);
      parkingSlots.remove(car);

      freeSlots.add(slotNo);
      occupiedSlots.remove(slotNo);

      response.setSuccess(true);
      response.setMessage("UnParked successfully");

    } catch (NotParkedException e) {
      response.setSuccess(false);
      response.setMessage(e.getMessage());
    }
    return response;
  }

  private void checkIfCarIsParked(Car car) throws AlreadyParkedException {
    if (isCarParked(car)) {
      throw new AlreadyParkedException();
    }
  }

  private void checkIfCarIsNotParked(Car car) throws NotParkedException {
    if (!isCarParked(car)) {
      throw new NotParkedException();
    }
  }

  private void isParkingSlotAvailable() throws ParkingFullException {
    if (occupiedSlots.size() == noOfSlots) {
      throw new ParkingFullException();
    }
  }

  private boolean isCarParked(Car car) {
    return parkingSlots.containsKey(car);
  }

  private Integer getFreeSlot() {
    return freeSlots.get(0);
  }

  private void initializeSlots(int noOfSlots) {
    for (int i = 0; i < noOfSlots; i++) {
      freeSlots.add(i);
    }
  }
}
