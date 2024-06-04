package com.espncricinfo.matchservice.service.impl;

import com.espncricinfo.matchservice.dto.MatchRequestDTO;
import com.espncricinfo.matchservice.dto.MatchResponseDTO;
import com.espncricinfo.matchservice.exception.MatchNotFoundException;
import com.espncricinfo.matchservice.model.Match;
import com.espncricinfo.matchservice.repository.MatchRepository;
import com.espncricinfo.matchservice.service.MatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Override
    public List<MatchResponseDTO> getAllMatches() {
        return matchRepository.findAll().stream().map(this::convertToResponseDTO).collect(Collectors.toList());
    }

    @Override
    public MatchResponseDTO getMatchById(Long matchId) {
        Match match = matchRepository.findById(matchId).orElseThrow(() -> new MatchNotFoundException("Match not found with ID: " + matchId));
        return convertToResponseDTO(match);
    }

    @Override
    public MatchResponseDTO createMatch(MatchRequestDTO matchRequestDTO) {
        Match match = convertToEntity(matchRequestDTO);
        Match savedMatch = matchRepository.save(match);
        return convertToResponseDTO(savedMatch);
    }

    @Override
    public MatchResponseDTO updateMatch(Long matchId, MatchRequestDTO matchRequestDTO) {
        Match match = matchRepository.findById(matchId).orElseThrow(() -> new MatchNotFoundException("Match not found with ID: " + matchId));
        match.setTeam1(matchRequestDTO.getTeam1());
        match.setTeam2(matchRequestDTO.getTeam2());
        match.setVenue(matchRequestDTO.getVenue());
        match.setDate(matchRequestDTO.getDate());
        match.setStatus(matchRequestDTO.getStatus());
        match.setTeam1Score(matchRequestDTO.getTeam1Score());
        match.setTeam2Score(matchRequestDTO.getTeam2Score());
        match.setWinner(matchRequestDTO.getWinner());
        match.setFormat(matchRequestDTO.getFormat());
        Match updatedMatch = matchRepository.save(match);
        return convertToResponseDTO(updatedMatch);
    }

    @Override
    public boolean deleteMatch(Long matchId) {
        if (!matchRepository.existsById(matchId)) {
            throw new MatchNotFoundException("Match not found with ID: " + matchId);
        }
        matchRepository.deleteById(matchId);
        return true;
    }

    private MatchResponseDTO convertToResponseDTO(Match match) {
        return new MatchResponseDTO(
                match.getId(),
                match.getTeam1(),
                match.getTeam2(),
                match.getVenue(),
                match.getDate(),
                match.getStatus(),
                match.getTeam1Score(),
                match.getTeam2Score(),
                match.getWinner(),
                match.getFormat()
        );
    }

    private Match convertToEntity(MatchRequestDTO matchRequestDTO) {
        return new Match(
                null,
                matchRequestDTO.getTeam1(),
                matchRequestDTO.getTeam2(),
                matchRequestDTO.getVenue(),
                matchRequestDTO.getDate(),
                matchRequestDTO.getStatus(),
                matchRequestDTO.getTeam1Score(),
                matchRequestDTO.getTeam2Score(),
                matchRequestDTO.getWinner(),
                matchRequestDTO.getFormat()
        );
    }
}
