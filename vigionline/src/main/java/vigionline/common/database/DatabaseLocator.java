package vigionline.common.database;

public class DatabaseLocator {

	private static IDatabase _repo = new MySqlDatabase();
	public static IDatabase Get()	{ 		return _repo;	}
}
