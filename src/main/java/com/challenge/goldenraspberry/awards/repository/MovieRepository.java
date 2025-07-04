package com.challenge.goldenraspberry.awards.repository;

import com.challenge.goldenraspberry.awards.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByWinnerTrue();
}
