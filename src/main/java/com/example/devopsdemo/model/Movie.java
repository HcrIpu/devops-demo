package com.example.devopsdemo.model;

import generated.MovieDto;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@Builder
@Entity
@Table(name = "movies")
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String genre;
    private int rating;

    /**
     * Convert the Model class to its DTO version
     *
     * @return Dto filled with the data of this object
     */
    public MovieDto getMovieDto() {
        return MovieDto.builder()
                .id((long) this.id)
                .title(this.title)
                .genre(this.genre)
                .rating((long) this.rating)
                .build();
    }
}
