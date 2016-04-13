package com.bootcamp;

import com.bootcamp.event.CarNotFoundEvent;
import com.bootcamp.event.Event;

public class FbiAgent implements Subscriber {

  public void notifyParty(Event e) {

    if(e instanceof CarNotFoundEvent) {
      startPaperWork();
    } else {
      System.out.println("Hooray! Parking is 80% populated");
    }
  }

  protected void startPaperWork() {

  }
}
