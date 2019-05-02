package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.CollaboratorCallException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class TripDAOTest {

    @Test
    void should_throw_a_collaborator_call_exception() {
        // given
        TripDAO tripDAO = new TripDAO();
        User user = new User();

        // when
        Throwable throwable = catchThrowable(() -> tripDAO.tripsBy(user));

        // then
        assertThat(throwable).isInstanceOf(CollaboratorCallException.class)
        .hasMessage("TripDAO should not be invoked on an unit test.");
    }
}