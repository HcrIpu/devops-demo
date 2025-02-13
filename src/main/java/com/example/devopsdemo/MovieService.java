package com.example.devopsdemo;

import com.example.devopsdemo.model.Movie;
import com.example.devopsdemo.repository.MovieRepository;
import generated.MovieDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MovieService {
    private final MovieRepository movieRepository;

    /**
     * Get all Movies from the DB
     *
     * @return List of DTOs for all Movies
     */
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    /**
     * Get a Movie by ID
     *
     * @param id ID to search
     * @return Movie as DTO
     */
    public Movie getMovieById(int id) {
        Optional<Movie> movieOptional = this.movieRepository.findById((long) id);
        return movieOptional.orElse(null);
    }

    /**
     * Persist the passed DTO in the DB
     *
     * @param movieDto DTO with data to persist
     * @return DTO with saved data
     */
    public Movie save(MovieDto movieDto) {
        if (movieDto != null) {
            Movie movie = Movie.builder()
                    .title(movieDto.getTitle())
                    .genre(movieDto.getGenre())
                    .rating(movieDto.getRating().intValue())
                    .build();
           return movieRepository.save(movie);
        }
        return null;
    }

    /**
     * Deletes a Movie
     *
     * @param id ID of the Movie to be deleted
     */
    public void deleteMovieById(int id) {
        movieRepository.deleteById((long) id);
    }

    /**
     * Updates a Movie with the Data from the DTO
     *
     * @param movieDto DTO to update from
     * @return Updated Movie
     */
    public Movie updateMovie(MovieDto movieDto) {
        if (movieDto.getId() == null) {
            return null;
        }

        Movie updateTarget = this.getMovieById(movieDto.getId().intValue());
        if (updateTarget != null) {
            updateTarget.setTitle(movieDto.getTitle());
            updateTarget.setGenre(movieDto.getGenre());
            updateTarget.setRating(movieDto.getRating().intValue());

            return movieRepository.save(updateTarget);
        }

        return null;
    }
}
