package com.challenge.goldenraspberry.awards.dto;

public record AwardIntervalDTO(
        String producer,
        int interval,
        int previousWin,
        int followingWin
) { }
