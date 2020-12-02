package com.dantheman.episode_ranker.services;

import com.dantheman.episode_ranker.exceptions.EntityExistsException;
import com.dantheman.episode_ranker.exceptions.EntityNotFoundException;
import com.dantheman.episode_ranker.models.Episode;
import com.dantheman.episode_ranker.models.EpisodeOrderList;
import com.dantheman.episode_ranker.models.Show;
import com.dantheman.episode_ranker.models.User;
import com.dantheman.episode_ranker.repositories.EpisodeOrderListRepository;
import com.dantheman.episode_ranker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class EpisodeListService {
    private EpisodeOrderListRepository episodeOrderListRepository;
    private UserRepository userRepository;
    private EpisodesService episodesService;
    @Autowired
    public EpisodeListService(EpisodeOrderListRepository episodeOrderListRepository, EpisodesService episodesService, UserRepository userRepository) {
        this.episodeOrderListRepository = episodeOrderListRepository;
        this.episodesService = episodesService;
        this.userRepository = userRepository;
    }

    public List<Episode> getEpisodeOrderList(String username, Integer show_id) {
        EpisodeOrderList episodeOrderList = episodeOrderListRepository.getByUsernameAndShow(username, show_id);
        List<Episode> eps = new ArrayList<Episode>();
        for (Integer ep : episodeOrderList.getEpisodeList()) {
            eps.add(episodesService.getEpisodeById(ep));
        }
        return eps;
    }

    public List<EpisodeOrderList> getUEpisodeOrderListsByUsername(String username) {
        List<EpisodeOrderList> episodeOrderLists = episodeOrderListRepository.getAllByUsername(username);
        return episodeOrderLists;
    }

    public List<EpisodeOrderList> getUEpisodeOrderListsByShow(Integer show_id) {
        List<EpisodeOrderList> episodeOrderLists = episodeOrderListRepository.getAllByShow(show_id);
        return episodeOrderLists;
    }

    public void addEpisodeOrderList(String username, Integer show_id, List<Integer> episodeList) throws EntityNotFoundException {
        if(!userRepository.existsByUsername(username)){
            throw new EntityNotFoundException("User does not exist");
        }
        EpisodeOrderList episodeOrderList = new EpisodeOrderList(username, show_id, episodeList);
        if(episodeOrderListRepository.existsByUsernameAndShow(username, show_id)){
            episodeOrderListRepository.deleteByUsernameAndShow(username, show_id);
        }
        episodeOrderListRepository.save(episodeOrderList);
    }
}
