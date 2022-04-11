package com.dantheman.episode_ranker.models.TV;

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
public class Season {
    private List<Episode> episodes;
    private String _id;
    private String name;
    private Integer season_number;
}
