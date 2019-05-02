package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;

import java.util.List;

import static java.util.Collections.emptyList;

public class TripService {

	private final TripDAO tripDao;

	public TripService() {
		tripDao = new TripDAO();
	}

	public TripService(TripDAO tripDao) {
		this.tripDao = tripDao;
	}

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
			return tripDao.tripsBy(user);
		}
		return emptyList();
	}

}
