package com.dantheman.episode_ranker.services;

import com.dantheman.episode_ranker.exceptions.EntityNotFoundException;
import com.dantheman.episode_ranker.models.TV.Episode;
import com.dantheman.episode_ranker.models.TV.Show;
import com.dantheman.episode_ranker.repositories.EpisodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class EpisodesService {

  private RestTemplate restTemplate;
  private EpisodeRepository episodeRepository;

  @Autowired
  public EpisodesService(RestTemplate restTemplate, EpisodeRepository episodeRepository) {
    this.restTemplate = restTemplate;
    this.episodeRepository = episodeRepository;
  }

  public Show getShowDetails(Integer id) throws EntityNotFoundException {
    String show = id.toString();
    String url =
        "https://api.themoviedb.org/3/tv/"
            + show
            + "?api_key=f8ca671fc5e479fe6b738061a3faa31d&language=en-US";

    ResponseEntity<Show> response =
        restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>("parameters"), Show.class);

    Show responseBody = response.getBody();
    if (responseBody == null) throw new EntityNotFoundException("Episode");
    return responseBody;
  }

  public List<Episode> getEpisodes(Integer id) throws EntityNotFoundException {
    Show show = getShowDetails(id);
    List<Episode> episodes = new ArrayList<Episode>();
    int num_seasons = show.getNumber_of_seasons();
    for (int s = 1; s <= num_seasons; s++) {
      int num_eps = 0;
      List<Show.Season> seasons = show.getSeasons();
      for (Show.Season season : seasons) {
        if (season.getSeason_number() == s) {
          num_eps = season.getEpisode_count();
          break;
        }
      }
      for (int e = 1; e <= num_eps; e++) {
        episodes.add(getEpisode(id, s, e));
      }
    }
    if (episodes.size() == 0) throw new EntityNotFoundException("Episode");
    return episodes;
  }

  public Episode getEpisodeById(Integer episode_id){
    return episodeRepository.getById(episode_id);
  }

  public Episode getEpisode(Integer id, int season_number, int episode_number)
      throws EntityNotFoundException {

    String url =
        "https://api.themoviedb.org/3/tv/"
            + id
            + "/season/"
            + season_number
            + "/episode/"
            + episode_number
            + "?api_key=f8ca671fc5e479fe6b738061a3faa31d&language=en-US";
    ResponseEntity<Episode> response =
        restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>("parameters"), Episode.class);

    Episode responseBody = response.getBody();
    if (responseBody == null) throw new EntityNotFoundException("Episode");

    if(!episodeRepository.existsById(responseBody.getId())){
      episodeRepository.save(responseBody);
    }

    return responseBody;
  }
}
