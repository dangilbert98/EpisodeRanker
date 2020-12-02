package com.dantheman.episode_ranker.repositories;

import com.dantheman.episode_ranker.models.EpisodeOrderList;
import com.dantheman.episode_ranker.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EpisodeOrderListRepository extends MongoRepository<EpisodeOrderList, String> {
    List<EpisodeOrderList> getAllByUsername(String username);
    List<EpisodeOrderList> getAllByShow(Integer show);
    boolean existsByUsernameAndShow(String username, Integer show);
    EpisodeOrderList getByUsernameAndShow(String username, Integer show);
    void deleteByUsernameAndShow(String username, Integer show);
}
