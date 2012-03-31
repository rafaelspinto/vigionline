package vigionline.common.database;

import java.sql.SQLException;
import java.util.List;

import vigionline.common.database.mapper.LocationMapper;
import vigionline.common.database.mapper.PermissionMapper;
import vigionline.common.database.mapper.RoleMapper;
import vigionline.common.database.mapper.UserMapper;
import vigionline.common.model.Location;
import vigionline.common.model.Permission;
import vigionline.common.model.Role;
import vigionline.common.model.User;

public class MySqlDatabase implements IDatabase {

	@Override
	public int createUser(User user) throws SQLException {
		return new UserMapper().insert(user);
	}

	@Override
	public List<User> getUsers() throws SQLException {
		return new UserMapper().getAll();
	}

	@Override
	public User getUser(int idUser) throws SQLException {
		return new UserMapper().getById(idUser);
	}

	@Override
	public int updateUser(User user) throws SQLException {
		return new UserMapper().update(user);
	}

	@Override
	public int deleteUser(int idUser) throws SQLException {
		return new UserMapper().delete(idUser);
	}

	@Override
	public List<User> getUsersByRole(int idRole) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createRole(Role role) throws SQLException {
		return new RoleMapper().insert(role);
	}

	@Override
	public List<Role> getRoles() throws SQLException {
		return new RoleMapper().getAll();
	}

	@Override
	public Role getRole(int idRole) throws SQLException {
		return new RoleMapper().getById(idRole);
	}

	@Override
	public int updateRole(Role role) throws SQLException {
		return new RoleMapper().update(role);
	}

	@Override
	public int deleteRole(int idRole) throws SQLException {
		return new RoleMapper().delete(idRole);
	}

	@Override
	public int createPermission(Permission permission) throws SQLException {
		return new PermissionMapper().insert(permission);
	}

	@Override
	public List<Permission> getPermissions() throws SQLException {
		return new PermissionMapper().getAll();
	}

	@Override
	public Permission getPermission(int idPermission) throws SQLException {
		return new PermissionMapper().getById(idPermission);
	}

	@Override
	public int updatePermission(Permission permission) throws SQLException {
		return new PermissionMapper().update(permission);
	}

	@Override
	public int deletePermission(int idPermission) throws SQLException {
		return new PermissionMapper().delete(idPermission);
	}

	@Override
	public List<Permission> getPermissionsByRole(int idRole)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Permission> getPermissionsByCamera(int idCamera)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createLocation(Location location) throws SQLException {
		return new LocationMapper().insert(location);
	}

	@Override
	public List<Location> getLocations() throws SQLException {
		return new LocationMapper().getAll();
	}

	@Override
	public Location getLocation(int idLocation) throws SQLException {
		return new LocationMapper().getById(idLocation);
	}

	@Override
	public int updateLocation(Location location) throws SQLException {
		return new LocationMapper().update(location);
	}

	@Override
	public int deleteLocation(int idLocation) throws SQLException {
		return new LocationMapper().delete(idLocation);
	}
}
