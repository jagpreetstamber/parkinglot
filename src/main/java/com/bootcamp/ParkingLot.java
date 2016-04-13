package com.bootcamp;

import com.bootcamp.event.CarNotFoundEvent;
import com.bootcamp.exception.AlreadyParkedException;
import com.bootcamp.exception.NotParkedException;
import com.bootcamp.exception.ParkingFullException;
import com.bootcamp.exception.ParkingLotException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {

  private int noOfSlots;
  private ParkingOwner owner;
  private List<Integer> occupiedSlots;
  private List<Integer> freeSlots;
  private Map<Car, Integer> parkingSlots;
  private List<Subscriber> eightyPercentSubscribers;
  private List<Subscriber> carNotFoundSubscribers;

  public ParkingLot(int noOfSlots, ParkingOwner owner) {
    this.noOfSlots = noOfSlots;
    this.owner = owner;
    freeSlots = new ArrayList<Integer>();
    occupiedSlots = new ArrayList<Integer>();
    this.parkingSlots = new HashMap<Car, Integer>();
    initializeSlots(noOfSlots);
  }

  public void setCarNotFoundSubscribers(List<Subscriber> carNotFoundSubscribers) {
    this.carNotFoundSubscribers = carNotFoundSubscribers;
  }

  public void setEightyPercentSubscribers(List<Subscriber> eightyPercentSubscribers) {
    this.eightyPercentSubscribers = eightyPercentSubscribers;
  }

  public Response park(Car car) {
    Response response = new Response();

    try {
      isCarAlreadyParked(car);
      isParkingSlotAvailable();

      Integer freeSlot = getFreeSlot();
      freeSlots.remove(freeSlot);
      occupiedSlots.add(freeSlot);

      parkingSlots.put(car, freeSlot);

      response.setSuccess(true);
      response.setMessage("Parked successfully");
      response.setParkingSlotNumber(freeSlot);

      notifyOwnerParkingIsFull();

      notifyAgentOnPark();
    } catch (ParkingLotException e) {
      response.setSuccess(false);
      response.setMessage(e.getMessage());
    }

    return response;
  }

  public Response retrieveCar(Car car) {
    Response response = new Response();

    try {
      Integer slotNo = getSlotForCar(car);
      notifyOwnerParkingLotHasSpace();

      parkingSlots.remove(car);

      freeSlots.add(slotNo);
      occupiedSlots.remove(slotNo);

      notifyAgentOnRetrieve();
      response.setSuccess(true);
      response.setMessage("UnParked successfully");

    } catch (NotParkedException e) {
      response.setSuccess(false);
      response.setMessage(e.getMessage());
    }
    return response;
  }

  private void notifyAgentOnPark() {
    boolean areOccupiedSlotMoreThanEightyPercent = ((occupiedSlots.size() * 100) / noOfSlots) >= 80;
    boolean wasItMoreThanEightyPercentEarlier = (((occupiedSlots.size() - 1) * 100) / noOfSlots) < 80;

    if (areOccupiedSlotMoreThanEightyPercent && wasItMoreThanEightyPercentEarlier) {
      notifyEightyPercentSubscribers();
    }
  }

  private void notifyAgentOnRetrieve() {
    boolean wasItMoreThanEightyPercentEarlier = (((occupiedSlots.size() + 1) * 100) / noOfSlots) >= 80;
    boolean areOccupiedSlotsLessThanEightyPercent = ((occupiedSlots.size() * 100) / noOfSlots) < 80;

    if (wasItMoreThanEightyPercentEarlier && areOccupiedSlotsLessThanEightyPercent) {
      notifyEightyPercentSubscribers();
    }
  }

  private void notifyEightyPercentSubscribers() {
    if (eightyPercentSubscribers != null) {
      for (Subscriber subscriber : eightyPercentSubscribers) {
        subscriber.notifyParty(null);
      }
    }
  }

  private void notifyOwnerParkingLotHasSpace() {
    if (occupiedSlots.size() >= noOfSlots) {
      owner.notifyParkingLotHasSpace();
    }
  }

  private void notifyOwnerParkingIsFull() {
    if (occupiedSlots.size() >= noOfSlots) {
      owner.notifyParkingLotIsFull();
    }
  }

  private void notifyCarNotFoundSubscribers(Car car) {
    CarNotFoundEvent event = new CarNotFoundEvent(car);
    if (carNotFoundSubscribers != null) {
      for (Subscriber subscriber : carNotFoundSubscribers) {
        subscriber.notifyParty(event);
      }
    }
  }

  private Integer getSlotForCar(Car car) throws NotParkedException {
    Integer slotNo = parkingSlots.get(car);
    if (slotNo == null) {
      notifyCarNotFoundSubscribers(car);
      throw new NotParkedException();
    }
    return slotNo;
  }

  private void isCarAlreadyParked(Car car) throws AlreadyParkedException {
    if (parkingSlots.containsKey(car)) {
      throw new AlreadyParkedException();
    }
  }

  private void isParkingSlotAvailable() throws ParkingFullException {
    if (occupiedSlots.size() == noOfSlots) {
      throw new ParkingFullException();
    }
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
