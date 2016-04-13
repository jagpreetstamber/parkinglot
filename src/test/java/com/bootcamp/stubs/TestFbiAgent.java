package com.bootcamp.stubs;

import com.bootcamp.FbiAgent;

public class TestFbiAgent extends FbiAgent {

  private int notificationCount;
  private boolean paperWorkStarted;

  @Override
  protected void startPaperWork() {
    paperWorkStarted = true;
  }

  @Override
  public void notifyParkingEightyPercentFull() {
    notificationCount++;
  }

  public int getNotificationCount() {
    return notificationCount;
  }

  public boolean isPaperWorkStarted() {
    return paperWorkStarted;
  }
}
