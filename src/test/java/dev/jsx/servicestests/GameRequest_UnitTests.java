package dev.jsx.servicestests;

import com.uni.daos.GameDAO;
import com.uni.daos.GameRequestDAO;
import com.uni.daos.SeasonDAO;
import com.uni.daos.VenueDAO;
import com.uni.entities.Game;
import com.uni.entities.GameRequest;
import com.uni.services.GameRequestImpl;
import com.uni.services.GameRequestService;
import com.uni.services.SchedulingServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GameRequest_UnitTests {


    @InjectMocks
    private GameRequestImpl gameRequestService;

    @Mock
    private GameRequestDAO gameRequestDAOMock;

    private List<GameRequest> mockGameRequests = Stream.of(
            new GameRequest(1, 4, 7, "Terror Dome", "season Winter"),
            new GameRequest(2, 7, 4, "Thunder Dome", "season Summer")
            ).collect(Collectors.toList());




    @DisplayName("Get all Game Requests")
    @Test
    public void getAllGames(){
        when(gameRequestDAOMock.findAll()).thenReturn(mockGameRequests);
        List<GameRequest> actual = gameRequestService.getAllGamesAndReferees();
        assertEquals(mockGameRequests, actual);
    }


    @DisplayName("Create request")
    @Test
    public void createRequest(){
        GameRequest test = new GameRequest(10, 1, 10, "SuperBowl", "Spring");
        when(gameRequestDAOMock.save(test)).thenReturn(test);
        GameRequest actual = gameRequestService.createRequest(test);
        assertEquals(test, actual);
    }



    @DisplayName("Delete Game Request")
    @Test
    public void deleteAllGames() {
        when(gameRequestDAOMock.delete(10,10)).thenReturn(true);
        gameRequestService.deleteRequest(10,10);
    }

}
