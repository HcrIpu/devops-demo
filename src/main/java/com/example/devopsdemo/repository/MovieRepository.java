package com.example.devopsdemo.repository;

import com.example.devopsdemo.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
