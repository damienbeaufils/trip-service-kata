package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class TripServiceTest {

    private static final User UNUSED_USER = null;
    private static final User ANONYMOUS_USER = null;
    private static final User REGISTERED_USER = new User();
    private static final User ANOTHER_USER = new User();
    private static final Trip MONTREAL_TO_PARIS = new Trip();
    private static final Trip MONTREAL_TO_TORONTO = new Trip();

    private TripService tripService;
    private User loggedInUser;

    @BeforeEach
    void setUp() {
        tripService = new TestableTripService();
    }

    @Test
    void should_throw_an_exception_when_user_not_logged_in() {
        // given
        loggedInUser = ANONYMOUS_USER;

        // when
        Throwable throwable = catchThrowable(() -> tripService.getTripsByUser(UNUSED_USER));

        // then
        assertThat(throwable).isInstanceOf(UserNotLoggedInException.class);
    }

    @Test
    void should_not_return_any_trips_when_users_are_not_friends() {
        // given
        loggedInUser = REGISTERED_USER;

        User user = UserBuilder.aUser()
                .friendsWith(ANOTHER_USER)
                .withTrips(MONTREAL_TO_PARIS)
                .build();

        // when
        List<Trip> foundTrips = tripService.getTripsByUser(user);

        // then
        assertThat(foundTrips).isEmpty();
    }

    @Test
    void should_return_friend_trips_when_users_are_friends() {
        // given
        loggedInUser = REGISTERED_USER;

        User user = UserBuilder.aUser()
                .withTrips(MONTREAL_TO_PARIS, MONTREAL_TO_TORONTO)
                .friendsWith(ANOTHER_USER, REGISTERED_USER)
                .build();

        // when
        List<Trip> foundTrips = tripService.getTripsByUser(user);

        // then
        assertThat(foundTrips).containsExactly(MONTREAL_TO_PARIS, MONTREAL_TO_TORONTO);
    }

    private class TestableTripService extends TripService {
        @Override
        User getLoggedInUser() {
            return loggedInUser;
        }

        @Override
        List<Trip> tripsBy(User u) {
            return u.trips();
        }
    }

}