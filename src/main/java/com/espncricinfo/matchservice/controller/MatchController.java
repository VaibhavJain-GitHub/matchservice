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
        log.info("Initial Commit");
        MatchResponseDTO match = matchService.getMatchById(matchId);
        log.info("Second Commit");
        return ResponseEntity.ok(match);
    }

    @Operation(summary = "Create a new match")
    @PostMapping("/")
    public ResponseEntity<MatchResponseDTO> createMatch(@RequestBody MatchRequestDTO matchRequestDTO) {
        log.info("Initial Commit");
        MatchResponseDTO createdMatch = matchService.createMatch(matchRequestDTO);
        log.info("Second Commit");
        return ResponseEntity.status(201).body(createdMatch);
    }

    @Operation(summary = "Update match")
    @PutMapping("/{matchId}")
    public ResponseEntity<MatchResponseDTO> updateMatch(@PathVariable Long matchId, @RequestBody MatchRequestDTO matchRequestDTO) {
        MatchResponseDTO updatedMatch = matchService.updateMatch(matchId, matchRequestDTO);
        log.info("Second Commit");
        return ResponseEntity.ok(updatedMatch);
    }

    @Operation(summary = "Delete match")
    @DeleteMapping("/{matchId}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long matchId) {
        matchService.deleteMatch(matchId);
        return ResponseEntity.ok().build();
    }
}
