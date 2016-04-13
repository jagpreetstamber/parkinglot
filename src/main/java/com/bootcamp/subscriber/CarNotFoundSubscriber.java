package com.bootcamp.subscriber;

import com.bootcamp.event.CarNotFoundEvent;

public interface CarNotFoundSubscriber extends Subscriber {

  void notifyCarNotFound(CarNotFoundEvent e);
}
