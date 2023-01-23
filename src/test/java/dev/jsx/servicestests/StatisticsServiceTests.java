package dev.jsx.servicestests;

import com.uni.daos.StatBasketballDAO;
import com.uni.daos.UserDAO;
import com.uni.dtos.PlayerCard;
import com.uni.entities.ImUser;
import com.uni.entities.StatBasketball;
import com.uni.services.StatisticsServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StatisticsServiceTests {
    @InjectMocks
    private StatisticsServiceImpl statisticsService;

    @Mock
    UserDAO userDAO;

    @Mock
    StatBasketballDAO statBasketballDAO;

    @Test()
    @DisplayName("Get Player Card by User ID")
    public void getPlayerCardByUserIdTest() {
        ImUser user = new ImUser(1, "SirMixAlot", "pantsss", "player", 20, 20, "", true);
        List<StatBasketball> basketballStats = Arrays.asList(
                new StatBasketball(1, 1, 1, "The Ballers", 0, 0, 0, 0),
                new StatBasketball(2, 2, 1, "The Ballers", 0, 0, 0, 0),
                new StatBasketball(3, 1, 2, "The Ballers", 0, 0, 0, 0),
                new StatBasketball(4, 2, 2, "The Ballers", 0, 0, 0, 0)
        );

        when(userDAO.findById(1)).thenReturn(user);
        when(statBasketballDAO.findAll()).thenReturn(basketballStats);

        PlayerCard playerCard = statisticsService.getPlayerCardByUserId(1);

        verify(userDAO, times(1)).findById(1);
        verify(statBasketballDAO, times(1)).findAll();
        verifyNoMoreInteractions(userDAO);
        verifyNoMoreInteractions(statBasketballDAO);

        assertEquals(user.getUserId(), playerCard.getId());
        assertEquals(user.getUsername(), playerCard.getUsername());
        assertEquals(basketballStats, playerCard.getBasketballStats());
    }

    @Test()
    @DisplayName("Get All Basketball Stats By Game ID")
    public void getAllBasketballStatsByGameIdTest() {
        List<StatBasketball> basketballStats = Arrays.asList(
                new StatBasketball(1, 1, 1, "The Ballers", 0, 0, 0, 0),
                new StatBasketball(2, 2, 1, "The Ballers", 0, 0, 0, 0),
                new StatBasketball(3, 1, 2, "The Ballers", 0, 0, 0, 0),
                new StatBasketball(4, 2, 2, "The Ballers", 0, 0, 0, 0)
        );

        List<StatBasketball> expectedStats = Arrays.asList(
                basketballStats.get(0),
                basketballStats.get(1)
        );

        when(statBasketballDAO.findAllByGameId(1)).thenReturn(expectedStats);

        List<StatBasketball> result = statisticsService.getAllBasketballStatsByGameId(1);

        verify(statBasketballDAO, times(1)).findAllByGameId(1);
        verifyNoMoreInteractions(statBasketballDAO);

        assertEquals(expectedStats, result);
    }

    @Test()
    @DisplayName("Add Basketball Stat")
    public void addBasketballStatTest() {
        List<StatBasketball> basketballStats = Arrays.asList(
                new StatBasketball(1, 1, 1, "The Ballers", 0, 0, 0, 0),
                new StatBasketball(2, 2, 1, "The Ballers", 0, 0, 0, 0),
                new StatBasketball(3, 1, 2, "The Ballers", 0, 0, 0, 0),
                new StatBasketball(4, 2, 2, "The Ballers", 0, 0, 0, 0)
        );

        StatBasketball basketballStat = new StatBasketball(0, 3, 1, "The Ballers", 0, 0, 0, 0);
        StatBasketball createdStat = new StatBasketball(1, 3, 1, "The Ballers", 0, 0, 0, 0);

        when(statBasketballDAO.findAll()).thenReturn(basketballStats);
        when(statBasketballDAO.save(basketballStat)).thenReturn(createdStat);

        StatBasketball result = statisticsService.addOrUpdateBasketballStat(basketballStat);

        verify(statBasketballDAO, times(1)).findAll();
        verify(statBasketballDAO, times(1)).save(basketballStat);

        assertEquals(createdStat, result);
    }

    @Test()
    @DisplayName("Update Basketball Stat")
    public void updateBasketballStatTest() {
        List<StatBasketball> basketballStats = Arrays.asList(
                new StatBasketball(1, 1, 1, "The Ballers", 0, 0, 0, 0),
                new StatBasketball(2, 2, 1, "The Ballers", 0, 0, 0, 0),
                new StatBasketball(3, 1, 2, "The Ballers", 0, 0, 0, 0),
                new StatBasketball(4, 2, 2, "The Ballers", 0, 0, 0, 0)
        );

        StatBasketball basketballStat = new StatBasketball(1, 1, 1, "The Ballers", 1, 1, 1, 1);

        when(statBasketballDAO.findAll()).thenReturn(basketballStats);

        StatBasketball result = statisticsService.addOrUpdateBasketballStat(basketballStat);

        verify(statBasketballDAO, times(1)).findAll();
        verify(statBasketballDAO, times(1)).update(basketballStats.get(0));

        assertEquals(basketballStats.get(0), result);
    }
}
