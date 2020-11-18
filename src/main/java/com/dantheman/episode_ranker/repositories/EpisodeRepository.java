package com.dantheman.episode_ranker.repositories;

import com.dantheman.episode_ranker.models.Show;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EpisodeRepository extends MongoRepository<Show, String> {
}
