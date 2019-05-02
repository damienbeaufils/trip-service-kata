package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {

	/**
	 * find trips of given user
	 * @param u the user
	 * @return trips found
	 * @throws UserNotLoggedInException
	 */
	public List<Trip> getTripsByUser(User u) throws UserNotLoggedInException {
		List<Trip> res = new ArrayList<Trip>();
		User logged = getLoggedInUser();
		boolean f = false;
		if (logged != null) {
			for (User u1 : u.getFriends()) {
				if (u1.equals(logged)) {
					f = true;
					break;
				}
			}
			if (f) {
				res = tripsBy(u);
			}
			return res;
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
