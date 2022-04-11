package com.dantheman.episode_ranker.models.Lists;

import com.dantheman.episode_ranker.models.TV.Episode;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EpisodeOrderListResponse {
    private String username;
    private Integer show;
    private String show_name;
    private List<Episode> episodeList;
}
