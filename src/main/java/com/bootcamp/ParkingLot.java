package com.bootcamp;

import com.bootcamp.event.CarNotFoundEvent;
import com.bootcamp.subscriber.CarNotFoundSubscriber;
import com.bootcamp.subscriber.EightyPercentParkingSubscriber;
import com.bootcamp.subscriber.ParkingOwner;

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
  private List<EightyPercentParkingSubscriber> eightyPercentSubscribers;
  private List<CarNotFoundSubscriber> carNotFoundSubscribers;

  public ParkingLot(int noOfSlots, ParkingOwner owner) {
    this.noOfSlots = noOfSlots;
    this.owner = owner;
    freeSlots = new ArrayList<>();
    occupiedSlots = new ArrayList<>();
    this.parkingSlots = new HashMap<>();
    initializeSlots(noOfSlots);
  }

  public void setCarNotFoundSubscribers(List<CarNotFoundSubscriber> carNotFoundSubscribers) {
    this.carNotFoundSubscribers = carNotFoundSubscribers;
  }

  public void setEightyPercentSubscribers(List<EightyPercentParkingSubscriber> eightyPercentSubscribers) {
    this.eightyPercentSubscribers = eightyPercentSubscribers;
  }

  public Response park(Car car) {
    Response response = new Response();

    if (isCarParked(car)) {
      response.setSuccess(false);
      response.setMessage("Car Already Parked");
    } else if (isParkingFull()) {
      response.setSuccess(false);
      response.setMessage("Parking lot is full");
    } else {

      Integer freeSlot = getFreeSlot();
      freeSlots.remove(freeSlot);
      occupiedSlots.add(freeSlot);

      parkingSlots.put(car, freeSlot);

      response.setSuccess(true);
      response.setMessage("Parked successfully");
      response.setParkingSlotNumber(freeSlot);

      notifyOwnerParkingIsFull();
      notifyAgentOnPark();
    }

    return response;
  }

  public Response retrieveCar(Car car) {
    Response response = new Response();

    if (isCarParked(car)) {
      Integer slotNo = getSlotForCar(car);
      notifyOwnerParkingLotHasSpace();

      parkingSlots.remove(car);
      freeSlots.add(slotNo);
      occupiedSlots.remove(slotNo);

      notifyAgentOnRetrieve();
      response.setSuccess(true);
      response.setMessage("UnParked successfully");
    } else {
      notifyCarNotFoundSubscribers(car);
      response.setSuccess(false);
      response.setMessage("Car not Parked");
    }

    return response;
  }


  public boolean isParkingFull() {
    return !isParkingAvailable();
  }

  public int getFreeSpace() {
    return freeSlots.size();
  }


  public boolean isParkingAvailable() {
    return !(occupiedSlots.size() == noOfSlots);
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
      for (EightyPercentParkingSubscriber subscriber : eightyPercentSubscribers) {
        subscriber.notifyParkingEightyPercentFull();
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
      for (CarNotFoundSubscriber subscriber : carNotFoundSubscribers) {
        subscriber.notifyCarNotFound(event);
      }
    }
  }

  private Integer getSlotForCar(Car car) {
    return parkingSlots.get(car);
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
