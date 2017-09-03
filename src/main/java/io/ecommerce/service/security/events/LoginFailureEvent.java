package io.ecommerce.service.security.events;

import org.springframework.context.ApplicationEvent;

/**
 * @author Prajesh Ananthan
 *         Created on 3/9/2017.
 */
public class LoginFailureEvent extends ApplicationEvent {
  /**
   * Create a new ApplicationEvent.
   *
   * @param source the object on which the event initially occurred (never {@code null})
   */
  public LoginFailureEvent(Object source) {
    super(source);
  }
}
