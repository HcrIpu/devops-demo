package com.example.devopsdemo;

import com.example.devopsdemo.model.Movie;
import com.example.devopsdemo.repository.MovieRepository;
import generated.MovieDto;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class MovieServiceTest {

    @Mock
    private MovieRepository movieRepositoryMock = Mockito.mock(MovieRepository.class);

    private MovieService movieService;

    @BeforeEach
    void setUp() {
        this.movieService = new MovieService(movieRepositoryMock);
    }

    @Test
    void getAllMovies_MoviesReturned() {
        // given
        List<Movie> movies = new ArrayList<>();
        movies.add(Movie.builder()
                .id(1)
                .title("Test 1")
                .rating(5)
                .genre("Test Genre")
                .build());

        movies.add(Movie.builder()
                .id(2)
                .title("Test 2")
                .rating(10)
                .genre("Test Genre")
                .build());

        Mockito.when(movieRepositoryMock.findAll()).thenReturn(movies);

        // when
        List<Movie> result = movieService.getAllMovies();

        // then
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void getAllMovies_NoMoviesReturned() {
        // given
        List<Movie> movies = new ArrayList<>();
        Mockito.when(movieRepositoryMock.findAll()).thenReturn(movies);

        // when
        List<Movie> result = movieService.getAllMovies();

        // then
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void getMovieById_MovieReturned() {
        // given
        Movie movie = Movie.builder()
                .id(1)
                .title("Test 1")
                .rating(5)
                .genre("Test Genre")
                .build();

        Optional<Movie> optionalMovie = Optional.of(movie);
        Mockito.when(movieRepositoryMock.findById(anyLong())).thenReturn(optionalMovie);

        // when
        Movie result = movieService.getMovieById(1);

        // then
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Test 1", result.getTitle());
        assertEquals("Test Genre", result.getGenre());
        assertEquals(5, result.getRating());
    }

    @Test
    void getMovieById_NoMovieReturned() {
        // given
        Optional<Movie> optionalMovie = Optional.empty();
        Mockito.when(movieRepositoryMock.findById(anyLong())).thenReturn(optionalMovie);

        // when
        Movie result = movieService.getMovieById(1);

        // then
        assertNull(result);
    }

    @Test
    void save_MovieSaved() {
        // given
        MovieDto dto = MovieDto.builder().title("Test 1").genre("Test Genre").rating(10L).build();
        Movie movie = Movie.builder()
                .id(1)
                .title("Test 1")
                .rating(5)
                .genre("Test Genre")
                .build();

        Mockito.when(movieRepositoryMock.save(any(Movie.class))).thenReturn(movie);

        // when
        Movie result = movieService.save(dto);

        // then
        assertNotNull(result);
        assertEquals(movie, result);
    }

    @Test
    void save_NoMovieSaved() {
        // when
        Movie result = movieService.save(null);

        // then
        assertNull(result);
    }

    @Test
    void deleteMovieById_MovieDeleted() {
        // given
        // when
        movieService.deleteMovieById(1);

        // then
        verify(movieRepositoryMock, times(1)).deleteById(anyLong());
    }

    @Test
    void updateMovie_MovieUpdated() {
        // given
        MovieDto dto = MovieDto.builder().id(1L).title("Test 2").genre("Test Genre 2").rating(7L).build();
        Movie movieBeforeSave = Movie.builder()
                .id(1)
                .title("Test 1")
                .rating(10)
                .genre("Test Genre")
                .build();
        Movie movieAfterSafe = Movie.builder()
                .id(1)
                .title("Test 2")
                .rating(7)
                .genre("Test Genre 2")
                .build();
        Mockito.when(movieRepositoryMock.findById(anyLong())).thenReturn(Optional.of(movieBeforeSave));
        Mockito.when(movieRepositoryMock.save(any(Movie.class))).thenReturn(movieAfterSafe);

        // when
        Movie result = movieService.updateMovie(dto);

        // then
        assertNotNull(result);
        assertEquals(movieAfterSafe, result);
    }

    @Test
    void updateMovie_NoMovieUpdated() {
        // given
        MovieDto dto = MovieDto.builder().id(20L).title("Test 2").genre("Test Genre 2").rating(7L).build();
        Mockito.when(movieRepositoryMock.findById(anyLong())).thenReturn(Optional.empty());

        // when
        Movie result = movieService.updateMovie(dto);

        // then
        assertNull(result);
    }

    @Test
    void updateMovie_NoMovieFoundForUpdate() {
        // given
        MovieDto dto = MovieDto.builder().id(null).build();
        Mockito.when(movieRepositoryMock.findById(anyLong())).thenReturn(Optional.empty());

        // when
        Movie result = movieService.updateMovie(dto);

        // then
        assertNull(result);
    }
}