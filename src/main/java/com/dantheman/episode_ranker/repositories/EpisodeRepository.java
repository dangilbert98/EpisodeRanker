package com.dantheman.episode_ranker.repositories;

import com.dantheman.episode_ranker.models.TV.Episode;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EpisodeRepository extends MongoRepository<Episode, String> {
    boolean existsById(Integer episode_id);
    Episode getById(Integer id);
}
