package com.dantheman.episode_ranker.repositories;

import com.dantheman.episode_ranker.models.Show;
import com.dantheman.episode_ranker.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    boolean existsByUsername(String username);
}
