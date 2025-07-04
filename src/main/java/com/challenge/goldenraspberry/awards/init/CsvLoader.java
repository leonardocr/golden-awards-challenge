package com.challenge.goldenraspberry.awards.init;

import com.challenge.goldenraspberry.awards.entity.Movie;
import com.challenge.goldenraspberry.awards.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class CsvLoader {

    private final MovieRepository repository;

    public void loadCsv() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new ClassPathResource("movielist.csv").getInputStream(), StandardCharsets.UTF_8))) {

            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] fields = line.split(";", -1);

                Movie movie = Movie.builder()
                        .year(Integer.parseInt(fields[0]))
                        .title(fields[1])
                        .studios(fields[2])
                        .producers(fields[3])
                        .winner("yes".equalsIgnoreCase(fields[4].trim()))
                        .build();

                repository.save(movie);
            }

        } catch (Exception e) {
            throw new RuntimeException("Load CSV file error", e);
        }
    }
}
