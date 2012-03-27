package vigionline.common.database;

public class DatabaseLocator {

	private static IDatabase _repo = null;
	public static IDatabase Get()	{ 		return _repo;	}
}
