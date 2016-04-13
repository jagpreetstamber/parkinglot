package com.bootcamp;

import com.bootcamp.event.Event;

public interface Subscriber<T extends Event> {
  void notifyParty(T e);
}
