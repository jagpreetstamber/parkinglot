package com.bootcamp;

import com.bootcamp.event.CarNotFoundEvent;
import com.bootcamp.stubs.TestFbiAgent;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class FbiAgentTest {

  @Test
  public void testPaperWorkStartsWhenCarIsNotFound() throws Exception {
    TestFbiAgent agent = new TestFbiAgent();
    Car missingCar = new Car();
    CarNotFoundEvent event = new CarNotFoundEvent(missingCar);

    agent.notifyCarNotFound(event);

    assertTrue(agent.isPaperWorkStarted());
  }
}