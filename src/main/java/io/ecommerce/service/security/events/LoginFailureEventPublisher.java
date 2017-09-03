package io.ecommerce.service.security.events;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * @author Prajesh Ananthan
 *         Created on 3/9/2017.
 */
@Component
public class LoginFailureEventPublisher implements ApplicationEventPublisherAware {

  private ApplicationEventPublisher publisher;

  @Override
  public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
    this.publisher = applicationEventPublisher;
  }

  public void publish(LoginFailureEvent event) {
    this.publisher.publishEvent(event);
  }
}