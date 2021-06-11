package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.HttpSession;
import org.craftedsw.tripservicekata.user.User;

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
		User logged = HttpSession.getInstance().getLoggedUser();
		boolean f = false;
		if (logged != null) {
			for (User u1 : u.getFriends()) {
				if (u1.equals(logged)) {
					f = true;
					break;
				}
			}
			if (f) {
				res = TripDAO.findTripsByUser(u);
			}
			return res;
		} else {
			throw new UserNotLoggedInException();
		}
	}
	
}
