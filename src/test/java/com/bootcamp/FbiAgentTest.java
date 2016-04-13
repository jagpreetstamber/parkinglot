package com.bootcamp;

import com.bootcamp.event.CarNotFoundEvent;
import com.bootcamp.stubs.FbiAgentStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class FbiAgentTest {

  @Test
  public void testPaperWorkStartsWhenCarIsNotFound() throws Exception {
    FbiAgentStub agent = new FbiAgentStub();
    Car missingCar = new Car();
    CarNotFoundEvent event = new CarNotFoundEvent(missingCar);

    agent.notifyParty(event);

    assertTrue(agent.isPaperWorkStarted());
  }
}