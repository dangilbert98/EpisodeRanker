package com.dantheman.episode_ranker.models;

import com.dantheman.episode_ranker.models.Lists.EpisodeOrderList;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "users")
public class User {
    @Id private String username;
    private String password;
    private List<EpisodeOrderList> episodeOrderLists;

}
