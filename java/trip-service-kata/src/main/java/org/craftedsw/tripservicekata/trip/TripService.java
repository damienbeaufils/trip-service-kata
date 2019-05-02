package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;

import java.util.List;

import static java.util.Collections.emptyList;

public class TripService {

	/**
	 * find trips of given user
	 * @param user the user
	 * @param loggedInUser
	 * @return trips found
	 * @throws UserNotLoggedInException
	 */
	public List<Trip> getTripsByUser(User user, User loggedInUser) throws UserNotLoggedInException {
		if (loggedInUser == null) {
			throw new UserNotLoggedInException();
		}

		if (user.isFriendWith(loggedInUser)) {
			return tripsBy(user);
		}
		return emptyList();
	}

	List<Trip> tripsBy(User u) {
		return TripDAO.findTripsByUser(u);
	}

}
