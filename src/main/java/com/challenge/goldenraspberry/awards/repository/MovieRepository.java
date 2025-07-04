package com.challenge.goldenraspberry.awards.repository;

import com.challenge.goldenraspberry.awards.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
