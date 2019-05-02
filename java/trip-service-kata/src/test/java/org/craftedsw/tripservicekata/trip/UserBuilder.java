package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.user.User;

import java.util.Arrays;

class UserBuilder {
    private Trip[] trips;
    private User[] friends;

    public static UserBuilder aUser() {
        return new UserBuilder();
    }

    public UserBuilder withTrips(Trip... trips) {
        this.trips = trips;
        return this;
    }

    public UserBuilder friendsWith(User... friends) {
        this.friends = friends;
        return this;
    }

    public User build() {
        User user = new User();
        Arrays.stream(trips).forEach(user::addTrip);
        Arrays.stream(friends).forEach(user::addFriend);
        return user;
    }
}
