package dev.jsx.servicestests;

import com.uni.daos.TeamDAO;
import com.uni.daos.TeamRequestDAO;
import com.uni.daos.UserDAO;
import com.uni.dtos.LoginCredentials;
import com.uni.entities.ImUser;
import com.uni.entities.Team;
import com.uni.entities.TeamRequest;
import com.uni.exceptions.DatabaseConnectionException;
import com.uni.exceptions.NoUsernameFoundException;
import com.uni.exceptions.PasswordMismatchException;
import com.uni.services.RegistrationServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@ExtendWith(MockitoExtension.class)
public class RegistrationServiceTest {
    @InjectMocks
    private RegistrationServiceImpl registrationService;

    @Mock
    private TeamDAO teamDAO;

    @Mock
    private UserDAO userDAO;

    @Mock
    private TeamRequestDAO teamRequestDAO;

    @Test
    @DisplayName("Register team")
    public void registerTeamTest() {
        Team team = new Team("Rapids", 1, "basketball", "suspended");
        when(teamDAO.save(team)).thenReturn(new Team("Rapids", 1, "basketball", "suspended"));

        Team newTeam = registrationService.registerTeam(team);
        verify(teamDAO, times(1)).save(team);
        verifyNoMoreInteractions(teamDAO);

        assertEquals(team, newTeam);
    }

    @Test
    @DisplayName("Get All Teams")
    public void getAllTeamsTest() {
        List<Team> teams = Arrays.asList(
                new Team("Rapids", 1, "basketball", "suspended"),
                new Team("Earthquakes", 2, "softball", "suspended")
        );

        when(teamDAO.findAll()).thenReturn(teams);

        List<Team> retreivedTeams = registrationService.getAllTeams();
        verify(teamDAO, times(1)).findAll();
        verifyNoMoreInteractions(teamDAO);

        assertEquals(teams, retreivedTeams);
    }

    @Test
    @DisplayName("Get User From Login Credentials - Positive")
    public void getUserFromLoginCredentialsTest() {
        LoginCredentials credentials = new LoginCredentials("elasticshark", "pass123");
        ImUser imUser = new ImUser(0, "elasticshark", "pass123", "admin", 70, 155, "", true);

        when(userDAO.getByUsername("elasticshark")).thenReturn(imUser);

        ImUser retrievedUser = registrationService.getUserFromLoginCredentials(credentials);
        verify(userDAO, times(1)).getByUsername("elasticshark");
        verifyNoMoreInteractions(userDAO);

        assertEquals(imUser, retrievedUser);
    }

    @Test()
    @DisplayName("Get User From Login Credentials - Negative - Wrong password")
    public void getUserFromLoginCredentialsWrongPasswordTest() {
        LoginCredentials credentials = new LoginCredentials("elasticshark", "password");
        ImUser imUser = new ImUser(0, "elasticshark", "pass123", "admin", 70, 155, "", true);

        when(userDAO.getByUsername("elasticshark")).thenReturn(imUser);

        PasswordMismatchException exception = assertThrows(
                PasswordMismatchException.class,
                () -> registrationService.getUserFromLoginCredentials(credentials)
        );

        verify(userDAO, times(1)).getByUsername("elasticshark");
        verifyNoMoreInteractions(userDAO);

        assertEquals("Incorrect password for user", exception.getMessage());
    }

    @Test()
    @DisplayName("Get User From Login Credentials - Negative - Username doesn't exist")
    public void getUserFromLoginCredentialsUsernameDoesntExistTest() {
        LoginCredentials credentials = new LoginCredentials("elasticshark", "pass123");

        when(userDAO.getByUsername("elasticshark")).thenThrow(NoUsernameFoundException.class);

        assertThrows(
                NoUsernameFoundException.class,
                () -> registrationService.getUserFromLoginCredentials(credentials)
        );

        verify(userDAO, times(1)).getByUsername("elasticshark");
        verifyNoMoreInteractions(userDAO);
    }

    @Test()
    @DisplayName("Register User - Positive")
    public void registerUserTest() {
        ImUser registrationInfo = new ImUser(0, "elasticshark", "pass123", "admin", 70, 155, "", true);

        when(userDAO.save(registrationInfo)).thenReturn(registrationInfo);

        ImUser registerResult = registrationService.registerUser(registrationInfo);

        verify(userDAO, times(1)).save(registrationInfo);
        verifyNoMoreInteractions(userDAO);

        // Check that the new record matches every field except ID and Role, since the DAO will
        // potentially set those itself
        assertEquals(registrationInfo.getUsername(), registerResult.getUsername());
        assertEquals(registrationInfo.getPassword(), registerResult.getPassword());
        assertEquals(registrationInfo.getHeightInches(), registerResult.getHeightInches());
        assertEquals(registrationInfo.getWeightLbs(), registerResult.getWeightLbs());
        assertEquals(registrationInfo.getProfilePic(), registerResult.getProfilePic());
        assertEquals(registrationInfo.isHideBiometrics(), registerResult.isHideBiometrics());
    }

    @Test()
    @DisplayName("Register User - Negative - DB Connection Exception")
    public void registerUserDbConnectionExceptionTest() {
        ImUser registrationInfo = new ImUser(0, "elasticshark", "pass123", "admin", 70, 155, "", true);

        when(userDAO.save(registrationInfo)).thenThrow(DatabaseConnectionException.class);

        assertThrows(
                DatabaseConnectionException.class,
                () -> registrationService.registerUser(registrationInfo)
        );

        verify(userDAO, times(1)).save(registrationInfo);
        verifyNoMoreInteractions(userDAO);
    }

    @Test()
    @DisplayName("Update User")
    public void updateUserTest() {
        ImUser updateInfo = new ImUser(0, "elasticshark", "pass123", "admin", 70, 155, "", true);

        // when(userDAO.save(updateInfo)).thenReturn(updateInfo);

        ImUser updateResult = registrationService.updateUser(updateInfo);

        verify(userDAO, times(1)).update(updateInfo);
        verifyNoMoreInteractions(userDAO);

        assertEquals(updateInfo.getUserId(), updateResult.getUserId());
        assertEquals(updateInfo.getUsername(), updateResult.getUsername());
        assertEquals(updateInfo.getPassword(), updateResult.getPassword());
        assertEquals(updateInfo.getHeightInches(), updateResult.getHeightInches());
        assertEquals(updateInfo.getWeightLbs(), updateResult.getWeightLbs());
        assertEquals(updateInfo.getProfilePic(), updateResult.getProfilePic());
        assertEquals(updateInfo.getRole(), updateResult.getRole());
        assertEquals(updateInfo.isHideBiometrics(), updateResult.isHideBiometrics());
    }

    @Test()
    @DisplayName("Update Role")
    public void updateRoleTest() {
        doNothing().when(userDAO).updateRole(1, "player");

        registrationService.updateRole(1, "player");

        verify(userDAO, times(1)).updateRole(1, "player");
        verifyNoMoreInteractions(userDAO);
    }

    @Test()
    @DisplayName("Filter Team Requests By Player")
    public void filterTeamRequestsByPlayerTest() {
        List<TeamRequest> teamRequests = Arrays.asList(
                new TeamRequest(1, "The Ballers", 1, "pending"),
                new TeamRequest(1, "The Not Ballers", 1, "pending"),
                new TeamRequest(1, "Aliens", 1, "pending"),
                new TeamRequest(1, "The Ballers", 2, "pending"),
                new TeamRequest(1, "The Ballers", 3, "pending"),
                new TeamRequest(1, "The Not Ballers", 2, "pending"),
                new TeamRequest(1, "Aliens", 3, "pending"),
                new TeamRequest(1, "The Ballers", 4, "pending")
        );

        when(teamRequestDAO.findAll()).thenReturn(teamRequests);

        List<TeamRequest> foundRequests = registrationService.filterTeamRequestsByPlayer(1);

        verify(teamRequestDAO, times(1)).findAll();
        verifyNoMoreInteractions(teamRequestDAO);

        List<TeamRequest> expectedRequests = teamRequests
                .stream()
                .filter(request -> request.getRequesterId() == 1)
                .collect(Collectors.toList());

        assertEquals(expectedRequests, foundRequests);
    }

    @Test()
    @DisplayName("Get Team By Team Name")
    public void getTeamByTeamNameTest() {
        List<Team> teams = Arrays.asList(
                new Team("Aliens", 1, "basketball", "suspended"),
                new Team("Da Bois", 1, "basketball", "accepted"),
                new Team("Ravers Fantasy", 1, "softball", "denied"),
                new Team("The Ascended", 1, "softball", "denied"),
                new Team("Ardor of the Seven Seas", 1, "basketball", "accepted")
        );

        when(teamDAO.findAll()).thenReturn(teams);

        Team foundTeam = registrationService.getTeamByTeamName("Aliens");

        verify(teamDAO, times(1)).findAll();
        verifyNoMoreInteractions(teamDAO);

        assertEquals(teams.get(0), foundTeam);
    }

    @Test()
    @DisplayName("Retrieve Players By Team")
    public void retrievePlayersByTeamTest() {
        List<ImUser> users = Arrays.asList(
                new ImUser(1, "jairo", "jenkins", "player", 20, 20, "", true),
                new ImUser(2, "john", "jerky", "player", 20, 20, "", true),
                new ImUser(3, "jerry", "johnson", "player", 20, 20, "", true)
        );

        when(userDAO.retrieveUserByTeam("Jorbinthal Etiquette")).thenReturn(users);

        List<ImUser> foundUsers = registrationService.retrievePlayersByTeam("Jorbinthal Etiquette");

        verify(userDAO, times(1)).retrieveUserByTeam("Jorbinthal Etiquette");
        verifyNoMoreInteractions(userDAO);

        assertEquals(users, foundUsers);
    }

    @Test()
    @DisplayName("Retrieve All Users")
    public void retrieveAllUsersTest() {
        List<ImUser> users = Arrays.asList(
                new ImUser(1, "jairo", "jenkins", "player", 20, 20, "", true),
                new ImUser(2, "john", "jerky", "player", 20, 20, "", true),
                new ImUser(3, "jerry", "johnson", "player", 20, 20, "", true)
        );

        when(userDAO.findAll()).thenReturn(users);

        List<ImUser> foundUsers = registrationService.retrieveAllUsers();

        verify(userDAO, times(1)).findAll();
        verifyNoMoreInteractions(userDAO);

        assertEquals(users, foundUsers);
    }

    @Test()
    @DisplayName("Get All Team Requests")
    public void getAllTeamRequestsTest() {
        List<TeamRequest> teamRequests = Arrays.asList(
                new TeamRequest(1, "The Ballers", 1, "pending"),
                new TeamRequest(2, "Not The Ballers", 1, "pending"),
                new TeamRequest(3, "The Ballers", 2, "pending")
        );

        when(teamRequestDAO.findAll()).thenReturn(teamRequests);

        List<TeamRequest> foundTeamRequests = registrationService.getAllTeamRequests();

        verify(teamRequestDAO, times(1)).findAll();
        verifyNoMoreInteractions(teamRequestDAO);

        assertEquals(teamRequests, foundTeamRequests);
    }

    @Test()
    @DisplayName("Filter Team Requests By Team")
    public void filterTeamRequestsByTeam() {
        List<TeamRequest> teamRequests = Arrays.asList(
                new TeamRequest(1, "The Ballers", 1, "pending"),
                new TeamRequest(2, "Not The Ballers", 1, "pending"),
                new TeamRequest(3, "The Ballers", 2, "pending")
                );

        when(teamRequestDAO.findAll()).thenReturn(teamRequests);

        List<TeamRequest> foundTeamRequests = registrationService.filterTeamRequestsByTeam("The Ballers");

        verify(teamRequestDAO, times(1)).findAll();
        verifyNoMoreInteractions(teamRequestDAO);

        assertEquals(Arrays.asList(teamRequests.get(0), teamRequests.get(2)), foundTeamRequests);
    }

    @Test()
    @DisplayName("Create Request")
    public void createRequestTest() {
        TeamRequest teamRequest = new TeamRequest(1, "The Ballers", 1, null);
        TeamRequest expectedTeamRequest = new TeamRequest(1, "The Ballers", 1, "pending");

        when(teamRequestDAO.save(teamRequest)).thenReturn(expectedTeamRequest);

        TeamRequest createdTeamRequest = registrationService.createRequest(teamRequest);

        verify(teamRequestDAO, times(1)).save(teamRequest);
        verifyNoMoreInteractions(teamRequestDAO);

        assertEquals(expectedTeamRequest, createdTeamRequest);
    }

    @Test()
    @DisplayName("Approve Request")
    public void approveRequestTest() {
        List<TeamRequest> teamRequests = Arrays.asList(
                new TeamRequest(1, "The Ballers", 1, "pending"),
                new TeamRequest(2, "The Ballers", 2, "accepted"),
                new TeamRequest(3, "The Ballers", 3, "declined")
        );

        when(teamRequestDAO.findAll()).thenReturn(teamRequests);
        doNothing().when(teamRequestDAO).update(teamRequests.get(0));

        registrationService.approveRequest(1);

        verify(teamRequestDAO, times(1)).findAll();
        verify(teamRequestDAO, times(1)).update(teamRequests.get(0));
        verifyNoMoreInteractions(teamRequestDAO);

        assertEquals("accepted", teamRequests.get(0).getTeamRequestStatus());
    }

    @Test()
    @DisplayName("Deny Request")
    public void denyRequestTest() {
        List<TeamRequest> teamRequests = Arrays.asList(
                new TeamRequest(1, "The Ballers", 1, "pending"),
                new TeamRequest(2, "The Ballers", 2, "accepted"),
                new TeamRequest(3, "The Ballers", 3, "declined")
        );

        when(teamRequestDAO.findAll()).thenReturn(teamRequests);
        doNothing().when(teamRequestDAO).update(teamRequests.get(0));

        registrationService.denyRequest(1);

        verify(teamRequestDAO, times(1)).findAll();
        verify(teamRequestDAO, times(1)).update(teamRequests.get(0));
        verifyNoMoreInteractions(teamRequestDAO);

        assertEquals("denied", teamRequests.get(0).getTeamRequestStatus());
    }
}
