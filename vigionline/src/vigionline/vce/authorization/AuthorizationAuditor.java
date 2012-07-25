package vigionline.vce.authorization;

import javax.ws.rs.WebApplicationException;

import vigionline.vce.database.DatabaseLocator;
import vigionline.vce.database.IDatabase;

public class AuthorizationAuditor {

	public static boolean validateForCamera(String username, int idCamera) {
		IDatabase database = DatabaseLocator.Get();
		return database.isCameraAllowedToUser(idCamera, username);
	}

	public static boolean validateForDivision(String username, int idDivision) {
		IDatabase database = DatabaseLocator.Get();
		return database.isDivisionAllowedToUser(idDivision, username);
	}

	public static void enforceForCamera(String username, int idCamera) {
		if (!validateForCamera(username, idCamera))
			throw new WebApplicationException(403);
	}

	public static void enforceForDivision(String username, int idDivision) {
		if (!validateForDivision(username, idDivision))
			throw new WebApplicationException(403);
	}

	public static void enforce(String username, int idCamera, int idDivision) {
		enforceForCamera(username, idCamera);
		enforceForDivision(username, idDivision);
	}
}
