package com.bootcamp;

import com.bootcamp.event.Event;

public class FbiAgent implements Subscriber {

  public void notifyParty(Event e) {
    System.out.println("Hooray! Parking is 80% populated");
  }
}
