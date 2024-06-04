package com.espncricinfo.matchservice;

import com.espncricinfo.matchservice.controller.MatchController;
import com.espncricinfo.matchservice.dto.MatchRequestDTO;
import com.espncricinfo.matchservice.dto.MatchResponseDTO;
import com.espncricinfo.matchservice.service.MatchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MatchController.class)
public class MatchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MatchService matchService;

    @Autowired
    private ObjectMapper objectMapper;

    private MatchRequestDTO matchRequestDTO;
    private MatchResponseDTO matchResponseDTO;

    @BeforeEach
    void setUp() {
        matchRequestDTO = new MatchRequestDTO();
        matchRequestDTO.setTeam1("India");
        matchRequestDTO.setTeam2("Australia");
        matchRequestDTO.setVenue("Wankhede Stadium, Mumbai");
        matchRequestDTO.setDate("2024-07-15");
        matchRequestDTO.setStatus("Scheduled");
        matchRequestDTO.setTeam1Score(null);
        matchRequestDTO.setTeam2Score(null);
        matchRequestDTO.setWinner(null);
        matchRequestDTO.setFormat("ODI");

        matchResponseDTO = new MatchResponseDTO();
        matchResponseDTO.setId(1L);
        matchResponseDTO.setTeam1("India");
        matchResponseDTO.setTeam2("Australia");
        matchResponseDTO.setVenue("Wankhede Stadium, Mumbai");
        matchResponseDTO.setDate("2024-07-15");
        matchResponseDTO.setStatus("Scheduled");
        matchResponseDTO.setTeam1Score(null);
        matchResponseDTO.setTeam2Score(null);
        matchResponseDTO.setWinner(null);
        matchResponseDTO.setFormat("ODI");
    }

    @Test
    void testGetAllMatches() throws Exception {
        Mockito.when(matchService.getAllMatches()).thenReturn(Collections.singletonList(matchResponseDTO));

        mockMvc.perform(get("/api/matches/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].team1", is(matchResponseDTO.getTeam1())))
                .andExpect(jsonPath("$[0].team2", is(matchResponseDTO.getTeam2())));
    }

    @Test
    void testGetMatchById() throws Exception {
        Mockito.when(matchService.getMatchById(anyLong())).thenReturn(matchResponseDTO);

        mockMvc.perform(get("/api/matches/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.team1", is(matchResponseDTO.getTeam1())))
                .andExpect(jsonPath("$.team2", is(matchResponseDTO.getTeam2())));
    }

    @Test
    void testCreateMatch() throws Exception {
        Mockito.when(matchService.createMatch(any(MatchRequestDTO.class))).thenReturn(matchResponseDTO);

        mockMvc.perform(post("/api/matches/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(matchRequestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.team1", is(matchResponseDTO.getTeam1())))
                .andExpect(jsonPath("$.team2", is(matchResponseDTO.getTeam2())));
    }

    @Test
    void testUpdateMatch() throws Exception {
        Mockito.when(matchService.updateMatch(anyLong(), any(MatchRequestDTO.class))).thenReturn(matchResponseDTO);

        mockMvc.perform(put("/api/matches/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(matchRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.team1", is(matchResponseDTO.getTeam1())))
                .andExpect(jsonPath("$.team2", is(matchResponseDTO.getTeam2())));
    }

    @Test
    void testDeleteMatch() throws Exception {
        mockMvc.perform(delete("/api/matches/{matchId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
