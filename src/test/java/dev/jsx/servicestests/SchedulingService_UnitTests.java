package dev.jsx.servicestests;

import com.uni.daos.*;
import com.uni.entities.Game;
import com.uni.entities.Season;
import com.uni.entities.Venue;
import com.uni.services.SchedulingServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class SchedulingService_UnitTests {

    @InjectMocks
    private SchedulingServiceImpl schedulingService;

    @Mock
    private VenueDAO venueDAOMock;

    @Mock
    private GameDAO gameDAOMock;

    @Mock
    private SeasonDAO seasonDAOMock;

    private List<Game> mockGames = Stream.of(
      new Game(1, "Thunder Dome","Winter Season","homeTeam","awayTeam",0, 0, 0, "scheduled"),
      new Game(2, "Swamp Bowl","Fall Season","homeTeam","awayTeam", 12, 9, 23, "scheduled")
    ).collect(Collectors.toList());

    private List<Venue> mockVenues = Stream.of(
            new Venue("Thunder Dome"),
            new Venue("UFC Super Cage"),
            new Venue("Swamp Bowl")
            ).collect(Collectors.toList());

    private List<Season> mockSeasons = Stream.of(
            new Season("Winter Season"),
            new Season("Spring Season"),
            new Season("Summer Season"),
            new Season("Fall Season")
    ).collect(Collectors.toList());



    @DisplayName("Get all Venues")
    @Test
    public void getAllVenues() {
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
        Game testGame = new Game(30,"Thunder Dome","Summer Season","homeT",
                "awayT",50,40,8,"not good :)");
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
        assertEquals(testSeason.getTitle(),actualSeason.getTitle());
    }



}
