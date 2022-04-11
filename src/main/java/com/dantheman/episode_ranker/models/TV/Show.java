package com.dantheman.episode_ranker.models.TV;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Show {
    private Integer id;
    private String name;
    private Integer number_of_seasons;
    private String overview;
    private List<Season> seasons;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Document(collection = "shows")
    public static class Season{
        private Integer id;
        private Integer season_number;
        private String name;
        private String overview;
        private Integer episode_count;
    }
}
