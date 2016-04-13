package com.bootcamp;

import com.bootcamp.event.Event;

public interface Subscriber {
  void notifyParty(Event e);
}
