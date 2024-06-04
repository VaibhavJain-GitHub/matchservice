package com.espncricinfo.matchservice.dto;

import lombok.Data;

@Data
public class MatchRequestDTO {
    private String team1;
    private String team2;
    private String venue;
    private String date;
    private String status;
    private Integer team1Score;
    private Integer team2Score;
    private String winner;
    private String format;
}
