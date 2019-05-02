package org.craftedsw.tripservicekata.user;

import org.craftedsw.tripservicekata.trip.UserBuilder;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    private static final User ALICE = new User();
    private static final User BOB = new User();

    @Test
    void should_return_false_when_users_are_not_friends() {
        // given
        User user = UserBuilder.aUser().friendsWith(ALICE).build();

        // when
        Boolean areFriends = user.isFriendWith(BOB);

        // then
        assertThat(areFriends).isFalse();
    }

    @Test
    void should_return_true_when_users_are_friends() {
        // given
        User user = UserBuilder.aUser()
                .friendsWith(ALICE, BOB)
                .build();

        // when
        Boolean areFriends = user.isFriendWith(BOB);

        // then
        assertThat(areFriends).isTrue();
    }
}