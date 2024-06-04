package com.espncricinfo.matchservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchResponseDTO {
    private Long id;
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
