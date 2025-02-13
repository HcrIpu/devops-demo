package com.example.devopsdemo.controller;

import com.example.devopsdemo.MovieService;
import com.example.devopsdemo.model.Movie;
import generated.MovieApi;
import generated.MovieDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class MovieController implements MovieApi {

    private final MovieService movieService;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return MovieApi.super.getRequest();
    }

    @Override
    public ResponseEntity<MovieDto> createMovie(MovieDto movieDto) {
        Movie movie = this.movieService.save(movieDto);
        return ResponseEntity.status(201).body(movie.getMovieDto());
    }

    @Override
    public ResponseEntity<Void> deleteMovie(Integer movieId) {
        this.movieService.deleteMovieById(movieId);
        return ResponseEntity.status(204).build();
    }

    @Override
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        List<Movie> movies = this.movieService.getAllMovies();
        List<MovieDto> movieDtos = new ArrayList<>();
        for (Movie movie : movies) {
            movieDtos.add(movie.getMovieDto());
        }

        if (movieDtos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movieDtos);
    }

    @Override
    public ResponseEntity<MovieDto> getMovie(Integer movieId) {
        Movie movie = this.movieService.getMovieById(movieId);
        if (movie == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movie.getMovieDto());
    }

    @Override
    public ResponseEntity<MovieDto> updateMovie(MovieDto movieDto) {
        Movie updatedMovie = this.movieService.updateMovie(movieDto);
        if (updatedMovie == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedMovie.getMovieDto());
    }
}
