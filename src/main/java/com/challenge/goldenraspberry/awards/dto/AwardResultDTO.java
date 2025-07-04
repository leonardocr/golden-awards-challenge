package com.challenge.goldenraspberry.awards.dto;

import java.util.List;

public record AwardResultDTO(
        List<AwardIntervalDTO> min,
        List<AwardIntervalDTO> max
) {
}
