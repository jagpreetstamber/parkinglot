package com.bootcamp.stubs;

import com.bootcamp.FbiAgent;

public class FbiAgentStub extends FbiAgent{

  private boolean paperWorkStarted;

  @Override
  protected void startPaperWork() {
    paperWorkStarted = true;
  }

  public boolean isPaperWorkStarted() {
    return paperWorkStarted;
  }
}
