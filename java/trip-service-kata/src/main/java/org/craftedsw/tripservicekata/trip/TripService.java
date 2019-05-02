package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {

	/**
	 * find trips of given user
	 * @param user the user
	 * @return trips found
	 * @throws UserNotLoggedInException
	 */
	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		List<Trip> foundTrips = new ArrayList<Trip>();
		User loggedInUser = getLoggedInUser();
		if (loggedInUser != null) {
			if (user.isFriendWith(loggedInUser)) {
				foundTrips = tripsBy(user);
			}
			return foundTrips;
		} else {
			throw new UserNotLoggedInException();
		}
	}

	List<Trip> tripsBy(User u) {
		return TripDAO.findTripsByUser(u);
	}

	User getLoggedInUser() {
		return UserSession.getInstance().getLoggedUser();
	}

}
