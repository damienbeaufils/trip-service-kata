package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;

import java.util.List;

import static java.util.Collections.emptyList;

public class TripService {

	private final TripDAO tripDao;

	public TripService() {
		this(new TripDAO());
	}

	public TripService(TripDAO tripDao) {
		this.tripDao = tripDao;
	}

	public List<Trip> getFriendTrips(User loggedInUser, User friend) throws UserNotLoggedInException {
		if (loggedInUser == null) {
			throw new UserNotLoggedInException();
		}

		if (friend.isFriendWith(loggedInUser)) {
			return tripDao.tripsBy(friend);
		}
		return emptyList();
	}

}
