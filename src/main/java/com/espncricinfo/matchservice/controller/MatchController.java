package com.espncricinfo.matchservice.controller;

import com.espncricinfo.matchservice.dto.MatchRequestDTO;
import com.espncricinfo.matchservice.dto.MatchResponseDTO;
import com.espncricinfo.matchservice.service.MatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
@Tag(name = "Match Controller", description = "CRUD operations for Matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @Operation(summary = "Get all matches")
    @GetMapping("/")
    public List<MatchResponseDTO> getAllMatches() {
        return matchService.getAllMatches();
    }

    @Operation(summary = "Get match by ID")
    @GetMapping("/{matchId}")
    public ResponseEntity<MatchResponseDTO> getMatchById(@PathVariable Long matchId) {
        MatchResponseDTO match = matchService.getMatchById(matchId);
        return ResponseEntity.ok(match);
    }

    @Operation(summary = "Create a new match")
    @PostMapping("/")
    public ResponseEntity<MatchResponseDTO> createMatch(@RequestBody MatchRequestDTO matchRequestDTO) {
        MatchResponseDTO createdMatch = matchService.createMatch(matchRequestDTO);
        return ResponseEntity.status(201).body(createdMatch);
    }

    @Operation(summary = "Update match")
    @PutMapping("/{matchId}")
    public ResponseEntity<MatchResponseDTO> updateMatch(@PathVariable Long matchId, @RequestBody MatchRequestDTO matchRequestDTO) {
        MatchResponseDTO updatedMatch = matchService.updateMatch(matchId, matchRequestDTO);
        return ResponseEntity.ok(updatedMatch);
    }

    @Operation(summary = "Delete match")
    @DeleteMapping("/{matchId}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long matchId) {
        matchService.deleteMatch(matchId);
        return ResponseEntity.ok().build();
    }
}
