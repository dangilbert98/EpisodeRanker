package com.dantheman.episode_ranker.services;

import com.dantheman.episode_ranker.exceptions.EntityNotFoundException;
import com.dantheman.episode_ranker.models.Lists.EpisodeOrderListResponse;
import com.dantheman.episode_ranker.models.TV.Episode;
import com.dantheman.episode_ranker.models.Lists.EpisodeOrderList;
import com.dantheman.episode_ranker.repositories.EpisodeOrderListRepository;
import com.dantheman.episode_ranker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public EpisodeOrderListResponse getEpisodeOrderList(String username, Integer show_id) throws EntityNotFoundException{
        EpisodeOrderList episodeOrderList = episodeOrderListRepository.getByUsernameAndShow(username, show_id);
        List<Episode> eps = getEpisodesbyIds(episodeOrderList.getEpisodeList());
        String show_name = episodesService.getShowDetails(show_id).getName();
        return new EpisodeOrderListResponse(username,show_id,show_name, eps);
    }

    public List<EpisodeOrderListResponse> getEpisodeOrderListsByUsername(String username) throws EntityNotFoundException {
        List<EpisodeOrderList> episodeOrderLists = episodeOrderListRepository.getAllByUsername(username);
        List<EpisodeOrderListResponse> responseList = new ArrayList<>();
        for (EpisodeOrderList episodeOrderList: episodeOrderLists) {
            responseList.add(new EpisodeOrderListResponse(episodeOrderList.getUsername(),episodeOrderList.getShow(), episodesService.getShowDetails(episodeOrderList.getShow()).getName(), getEpisodesbyIds(episodeOrderList.getEpisodeList())));
        }
        return responseList;
    }

    public List<EpisodeOrderListResponse> getEpisodeOrderListsByShow(Integer show_id) throws EntityNotFoundException {
        List<EpisodeOrderList> episodeOrderLists = episodeOrderListRepository.getAllByShow(show_id);
        List<EpisodeOrderListResponse> responseList = new ArrayList<>();
        for (EpisodeOrderList episodeOrderList: episodeOrderLists) {
            responseList.add(new EpisodeOrderListResponse(episodeOrderList.getUsername(),episodeOrderList.getShow(), episodesService.getShowDetails(episodeOrderList.getShow()).getName(), getEpisodesbyIds(episodeOrderList.getEpisodeList())));
        }
        return responseList;
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

    private List<Episode> getEpisodesbyIds(List<Integer> ids){
        List<Episode> eps = new ArrayList<Episode>();
        for (Integer ep : ids) {
            eps.add(episodesService.getEpisodeById(ep));
        }
        return eps;
    }
}
