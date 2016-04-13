package com.bootcamp;

public class TestFbiAgent extends FbiAgent {

  private int notificationCount;

  public void notifyParty() {
    notificationCount++;
  }

  public int getNotificationCount() {
    return notificationCount;
  }
}
