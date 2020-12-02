package com.dantheman.episode_ranker.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "episodes")
public class Episode {
    @Id private Integer id;
    private Integer season_number;
    private Integer episode_number;
    private String name;
    private String overview;
}
