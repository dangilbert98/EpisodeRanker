package com.dantheman.episode_ranker.exceptions;

public class EntityExistsException extends Exception {
  public EntityExistsException(String message) {
    super(message + " already exists.");
  }
}
