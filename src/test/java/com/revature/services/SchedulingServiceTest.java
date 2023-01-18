package com.revature.services;

import com.uni.daos.GameDAO;
import com.uni.daos.SeasonDAO;
import com.uni.daos.VenueDAO;
import com.uni.entities.Game;
import com.uni.entities.Season;
import com.uni.entities.Venue;
import com.uni.services.SchedulingService;
import com.uni.services.SchedulingServiceImpl;
import io.cucumber.java.sl.In;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class SchedulingServiceTest {

    @Mock
    private VenueDAO venueDAOMock;
    @Mock
    private GameDAO gameDAOMock;
    @Mock
    private SeasonDAO seasonDAOMock;
    @InjectMocks
    private SchedulingService schedulingService = new SchedulingServiceImpl(venueDAOMock,gameDAOMock,seasonDAOMock);

    private List<Venue> mockVenues = Stream.of(
            new Venue("Stade"),
            new Venue("terrain"),
            new Venue("desert")
    ).collect(Collectors.toList());

    private List<Game> mockGames = Stream.of(
            new Game(1,"Stade","season 1","homeTeam","awayTeam",44,21,130,"team1win"),
            new Game(2,"terrain","season 1","Lakers","Bulls",130,213,10,"Bulls win"),
            new Game(3,"Stade","season 2","homeTeam2","awayTeam2",442,21,30,"team3win"),
            new Game(4,"Stade","season 4","homeTeam3","awayTeam3",414,210,10,"team4win")
    ).collect(Collectors.toList());

    private List<Season> mockSeasons = Stream.of(
            new Season("Season1"),
            new Season("Season 2"),
            new Season("Season 3")
    ).collect(Collectors.toList());
    @DisplayName("Get all venues")
    @Test
    public void getAllVenues(){
        when(venueDAOMock.findAll()).thenReturn(mockVenues);
        List<Venue> actual = schedulingService.getAllVenues();
        assertEquals(mockVenues.size(), actual.size());

    }

    @DisplayName("Get all Games")
    @Test
    public void getAllGames(){
        when(gameDAOMock.findAll()).thenReturn(mockGames);
        List<Game> actual = schedulingService.getAllGames();
        assertEquals(mockGames.size(), actual.size());

    }

    @DisplayName("Get all Seasons")
    @Test
    public void getAllSeasons(){
        when(seasonDAOMock.findAll()).thenReturn(mockSeasons);
        List<Season> actual = schedulingService.getAllSeasons();
        assertEquals(mockSeasons.size(), actual.size());
    }

    @DisplayName("Schedule Game")
    @Test
    public void scheduleGame(){
        Game testGame = new Game(2,"terrain","season 1","Lakers","Bulls",130,213,10,"Bulls win");
        when(gameDAOMock.save(testGame)).thenReturn(testGame);
        Game actualGame = schedulingService.scheduleGame(testGame);
        assertEquals(testGame.getOutcome(), actualGame.getOutcome());
    }

    @DisplayName("Schedule Season")
    @Test
    public void scheduleSeason(){
        Season testSeason = new Season("Season1");
        when(seasonDAOMock.save(testSeason)).thenReturn(testSeason);
        Season actualSeason = schedulingService.scheduleSeason(testSeason);
        assertEquals(testSeason.getTitle(), actualSeason.getTitle());
    }
}
