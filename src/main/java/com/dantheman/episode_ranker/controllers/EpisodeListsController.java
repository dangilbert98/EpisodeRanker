package com.dantheman.episode_ranker.controllers;

import com.dantheman.episode_ranker.exceptions.EntityNotFoundException;
import com.dantheman.episode_ranker.models.Lists.EpisodeOrderListResponse;
import com.dantheman.episode_ranker.models.TV.Episode;
import com.dantheman.episode_ranker.models.Lists.EpisodeOrderList;
import com.dantheman.episode_ranker.services.EpisodeListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lists")
@CrossOrigin
public class EpisodeListsController {
    private EpisodeListService episodeListService;
    @Autowired
    public EpisodeListsController(EpisodeListService episodeListService) {
        this.episodeListService = episodeListService;
    }

    @GetMapping
    public ResponseEntity<EpisodeOrderListResponse> getListByUserAndShow(@RequestParam String username, @RequestParam Integer show)
            throws EntityNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(episodeListService.getEpisodeOrderList(username, show));
    }

    @PostMapping
    public ResponseEntity<String> setEpisodeList(@RequestParam String username, @RequestParam Integer show, @RequestParam List<Integer> episodes) throws EntityNotFoundException{
        episodeListService.addEpisodeOrderList(username, show, episodes);
        return ResponseEntity.status(HttpStatus.OK).body("List Added");
    }

    @GetMapping("/user")
    public ResponseEntity<List<EpisodeOrderListResponse>> getListsByUser(@RequestParam String username)
            throws EntityNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(episodeListService.getEpisodeOrderListsByUsername(username));
    }

    @GetMapping("/show")
    public ResponseEntity<List<EpisodeOrderListResponse>> getListsByShow(@RequestParam Integer show)
            throws EntityNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(episodeListService.getEpisodeOrderListsByShow(show));
    }

    /*@GetMapping("/episodes")
    public ResponseEntity<List<Episode>> getEpisodes(@RequestParam String id)
            throws EntityNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(episodesService.getEpisodes(id));
    }*/
}
