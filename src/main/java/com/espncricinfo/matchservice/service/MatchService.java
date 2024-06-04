package com.espncricinfo.matchservice.service;

import com.espncricinfo.matchservice.dto.MatchRequestDTO;
import com.espncricinfo.matchservice.dto.MatchResponseDTO;

import java.util.List;

public interface MatchService {
    List<MatchResponseDTO> getAllMatches();
    MatchResponseDTO getMatchById(Long matchId);
    MatchResponseDTO createMatch(MatchRequestDTO matchRequestDTO);
    MatchResponseDTO updateMatch(Long matchId, MatchRequestDTO matchRequestDTO);
    boolean deleteMatch(Long matchId);
}
