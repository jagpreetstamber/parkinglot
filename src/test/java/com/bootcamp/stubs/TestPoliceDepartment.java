package com.bootcamp.stubs;

import com.bootcamp.PoliceDepartment;
import com.bootcamp.event.Event;

public class TestPoliceDepartment extends PoliceDepartment {

  private int noOfCarsNotFound;
  private Event event;

  public void notifyParty(Event e) {
    noOfCarsNotFound++;
    event = e;
  }

  public int getNoOfCarsNotFound() {
    return noOfCarsNotFound;
  }

  public Event getEvent() {
    return event;
  }
}
