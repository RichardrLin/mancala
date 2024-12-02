package us.jonathans.use_case.notify_user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import us.jonathans.data_access.leaderboard.LeaderboardRepository;
import us.jonathans.entity.leaderboard.Leaderboard;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotifyUserInteractorTest {

    @Mock
    private LeaderboardRepository mockLeaderboardRepository;
    @Mock
    private NotifyUserOutputBoundary mockOutputBoundary;
    private NotifyUserInteractor interactor;

    @BeforeEach
    void setUp() {
        // Initialized the interactor with mocked dependencies
        interactor = new NotifyUserInteractor(mockLeaderboardRepository, mockOutputBoundary);
    }

    @Test
    void testExecute_UserNotOnLeaderboard() {
        // Test data for a user who has not posted their results to the leaderboard
        String username = "Jonathon";
        String phoneNumber = "+9876543210";
        int rank = -1;

        // Returns the user's rank which would be -1
        Leaderboard mockLeaderboard = mock(Leaderboard.class);
        when(mockLeaderboard.getRank(username)).thenReturn(rank);
        when(mockLeaderboardRepository.getLeaderboard()).thenReturn(mockLeaderboard);

        // Set up input data for the interactor
        NotifyUserInputData inputData = new NotifyUserInputData(phoneNumber, username);

        interactor.execute(inputData);

        // Verifies that the correct message is sent to the user given their rank
        String expectedMessage = "Congratulations on finishing your Jancala game Jonathon!\n" +
                "\nConsider posting your results to the leaderboard to know your ranking next time!";
        verify(mockOutputBoundary).notifyUser(phoneNumber, expectedMessage);
    }

    @Test
    void testExecute_UserRankFirst() {
        // Test data for a user who is ranked first on the leaderboard
        String username = "Ivan";
        String phoneNumber = "+1122334455";
        int rank = 1;

        // Returns the user's rank which would be 1
        Leaderboard mockLeaderboard = mock(Leaderboard.class);
        when(mockLeaderboard.getRank(username)).thenReturn(rank);
        when(mockLeaderboardRepository.getLeaderboard()).thenReturn(mockLeaderboard);

        // Set up input data for the interactor
        NotifyUserInputData inputData = new NotifyUserInputData(phoneNumber, username);

        interactor.execute(inputData);

        // Verifies that the correct message is sent to the user given their rank
        String expectedMessage = "Congratulations on finishing your Jancala game Ivan or shall I say Jancala Grandmaster! \n" +
                "\nYou are currently ranked 1st on the leaderboard!";
        verify(mockOutputBoundary).notifyUser(phoneNumber, expectedMessage);
    }

    @Test
    void testExecute_UserRankSecond() {
        // Test data for a user and their rank on the leaderboard
        String username = "Richard";
        String phoneNumber = "+1234567890";
        int rank = 2;

        // Returns the user's rank which would be 2
        Leaderboard mockLeaderboard = mock(Leaderboard.class);
        when(mockLeaderboard.getRank(username)).thenReturn(rank);
        when(mockLeaderboardRepository.getLeaderboard()).thenReturn(mockLeaderboard);

        // Set up input data for the interactor
        NotifyUserInputData inputData = new NotifyUserInputData(phoneNumber, username);

        interactor.execute(inputData);

        // Verifies that the correct message is sent to the user given their rank
        String expectedMessage = "Congratulations on finishing your Jancala game Richard! \n" +
                "\nYou are currently ranked 2nd on the leaderboard!";
        verify(mockOutputBoundary).notifyUser(phoneNumber, expectedMessage);
    }

    @Test
    void testExecute_UserRankThird() {
        // Arrange
        String username = "Mina";
        String phoneNumber = "+3344556677";
        int rank = 3;

        // Returns the user's rank which would be 3
        Leaderboard mockLeaderboard = mock(Leaderboard.class);
        when(mockLeaderboard.getRank(username)).thenReturn(rank);
        when(mockLeaderboardRepository.getLeaderboard()).thenReturn(mockLeaderboard);

        // Set up input data for the interactor
        NotifyUserInputData inputData = new NotifyUserInputData(phoneNumber, username);

        interactor.execute(inputData);

        // Verifies that the correct message is sent to the user given their rank
        String expectedMessage = "Congratulations on finishing your Jancala game Mina! \n" +
                "\nYou are currently ranked 3rd on the leaderboard!";
        verify(mockOutputBoundary).notifyUser(phoneNumber, expectedMessage);
    }

    @Test
    void testExecute_UserRankGreaterThanThree() {
        // Arrange
        String username = "Fiza";
        String phoneNumber = "+1112223333";
        int rank = 5;

        // Returns the user's rank which would be 5
        Leaderboard mockLeaderboard = mock(Leaderboard.class);
        when(mockLeaderboard.getRank(username)).thenReturn(rank);
        when(mockLeaderboardRepository.getLeaderboard()).thenReturn(mockLeaderboard);

        // Set up input data for the interactor
        NotifyUserInputData inputData = new NotifyUserInputData(phoneNumber, username);

        interactor.execute(inputData);

        // Verifies that the correct message is sent to the user given their rank
        String expectedMessage = "Congratulations on finishing your Jancala game Fiza!\n" +
                "\nYou are currently ranked 5th on the leaderboard!";
        verify(mockOutputBoundary).notifyUser(phoneNumber, expectedMessage);
    }
}