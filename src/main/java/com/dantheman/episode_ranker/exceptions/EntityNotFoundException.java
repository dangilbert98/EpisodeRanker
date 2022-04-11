package com.dantheman.episode_ranker.exceptions;

public class EntityNotFoundException extends Exception {
  public EntityNotFoundException(String message) {
    super(message + " not found.");
  }
}
