package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

import java.util.List;

import static java.util.Collections.emptyList;

public class TripService {

	/**
	 * find trips of given user
	 * @param user the user
	 * @return trips found
	 * @throws UserNotLoggedInException
	 */
	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		User loggedInUser = getLoggedInUser();
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

	User getLoggedInUser() {
		return UserSession.getInstance().getLoggedUser();
	}

}
