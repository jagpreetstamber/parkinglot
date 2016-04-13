package com.bootcamp.stubs;

import com.bootcamp.FbiAgent;
import com.bootcamp.event.Event;

public class TestFbiAgent extends FbiAgent {

  private int notificationCount;

  public void notifyParty(Event e) {
    notificationCount++;
  }

  public int getNotificationCount() {
    return notificationCount;
  }
}
