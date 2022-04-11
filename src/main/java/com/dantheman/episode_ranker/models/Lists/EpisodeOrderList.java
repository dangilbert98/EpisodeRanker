package com.dantheman.episode_ranker.models.Lists;

import com.dantheman.episode_ranker.models.TV.Episode;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "ep_list")
public class EpisodeOrderList {
    private String username;
    private Integer show;
    private List<Integer> episodeList;
}
