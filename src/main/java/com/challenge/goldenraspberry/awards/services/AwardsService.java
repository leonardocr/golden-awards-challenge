package com.challenge.goldenraspberry.awards.services;

import com.challenge.goldenraspberry.awards.dto.AwardIntervalDTO;
import com.challenge.goldenraspberry.awards.dto.AwardResultDTO;
import com.challenge.goldenraspberry.awards.entity.Movie;
import com.challenge.goldenraspberry.awards.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AwardsService {

    private final MovieRepository repository;

    public AwardsService(MovieRepository repository) {
        this.repository = repository;
    }

    public AwardResultDTO getAwardIntervals() {
        List<Movie> movieList = repository.findByWinnerTrue();

        Map<String, List<Integer>> winners = getProducerWinners(movieList);

        List<AwardIntervalDTO> intervals = getIntervals(winners);

        int minInterval = intervals.stream()
                .mapToInt(AwardIntervalDTO::interval)
                .min()
                .orElse(0);

        int maxInterval = intervals.stream()
                .mapToInt(AwardIntervalDTO::interval)
                .max()
                .orElse(0);

        List<AwardIntervalDTO> min = intervals.stream()
                .filter(dto -> dto.interval() == minInterval)
                .toList();

        List<AwardIntervalDTO> max = intervals.stream()
                .filter(dto -> dto.interval() == maxInterval)
                .toList();

        return new AwardResultDTO(min, max);
    }

    private Map<String, List<Integer>> getProducerWinners(List<Movie> movieList) {
        Map<String, List<Integer>> producerWins = new HashMap<>();

        for (Movie movie : movieList) {
            String[] producers = movie.getProducers().split("\\s*,\\s*|\\s+and\\s+");

            for (String prod : producers) {
                String producer = prod.trim();

                if(producer.isEmpty())
                    continue;

                producerWins.computeIfAbsent(producer, p -> new ArrayList<>()).add(movie.getYear());
            }
        }

        return producerWins;
    }

    private List<AwardIntervalDTO> getIntervals(Map<String, List<Integer>> winners) {
        List<AwardIntervalDTO> intervals = new ArrayList<>();

        for (Map.Entry<String, List<Integer>> entry : winners.entrySet()) {
            List<Integer> years = entry.getValue().stream().sorted().toList();

            if (years.size() < 2)
                continue;

            for (int i = 1; i < years.size(); i++) {
                int interval = years.get(i) - years.get(i - 1);

                intervals.add(new AwardIntervalDTO(
                        entry.getKey(),
                        interval,
                        years.get(i - 1),
                        years.get(i)
                ));
            }
        }

        return intervals;
    }
}
