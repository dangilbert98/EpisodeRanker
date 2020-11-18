package com.dantheman.episode_ranker.controllers;

import com.dantheman.episode_ranker.exceptions.EntityNotFoundException;
import com.dantheman.episode_ranker.models.Episode;
import com.dantheman.episode_ranker.models.Show;
import com.dantheman.episode_ranker.services.EpisodesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/show")
@CrossOrigin
public class EpisodesController {
    private  EpisodesService episodesService;
    @Autowired
    public EpisodesController(EpisodesService episodesService) {
        this.episodesService = episodesService;
    }

    @GetMapping
    public ResponseEntity<Show> getShow(@RequestParam String id)
            throws EntityNotFoundException {
    return ResponseEntity.status(HttpStatus.OK).body(episodesService.getShowDetails(id));
    }

    @GetMapping("/episodes")
    public ResponseEntity<List<Episode>> getEpisodes(@RequestParam String id)
            throws EntityNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(episodesService.getEpisodes(id));
    }
}
