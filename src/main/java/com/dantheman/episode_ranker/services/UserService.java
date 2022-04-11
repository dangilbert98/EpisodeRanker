package com.dantheman.episode_ranker.services;

import com.dantheman.episode_ranker.exceptions.EntityExistsException;
import com.dantheman.episode_ranker.exceptions.EntityNotFoundException;
import com.dantheman.episode_ranker.models.Lists.EpisodeOrderList;
import com.dantheman.episode_ranker.models.User;
import com.dantheman.episode_ranker.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Optional;

@Service
public class UserService {
  private UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User getUser(String username) throws EntityNotFoundException {
    Optional<User> optionalUser = userRepository.findById(username);
    return optionalUser.orElseThrow(
        () -> new EntityNotFoundException("User with username" + username));
  }

  public void addUser(String username, String password) throws EntityExistsException {
    //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    User user = new User(username, password, new LinkedList<EpisodeOrderList>());
    validateUser(user);
    userRepository.save(user);
  }

  private void validateUser(User user) throws EntityExistsException {
    if (userRepository.existsByUsername(user.getUsername())) {
      throw new EntityExistsException("User with username " + user.getUsername());
    }
  }
}
