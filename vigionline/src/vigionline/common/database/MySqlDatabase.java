package vigionline.common.database;

import java.sql.SQLException;
import java.util.List;

import vigionline.common.database.mapper.ActionMapper;
import vigionline.common.database.mapper.CameraMapper;
import vigionline.common.database.mapper.DivisionMapper;
import vigionline.common.database.mapper.LocationMapper;
import vigionline.common.database.mapper.ManufacturerMapper;
import vigionline.common.database.mapper.ModelMapper;
import vigionline.common.database.mapper.PermissionMapper;
import vigionline.common.database.mapper.RoleMapper;
import vigionline.common.database.mapper.UserDivisionMapper;
import vigionline.common.database.mapper.UserMapper;
import vigionline.common.database.mapper.UserRoleMapper;
import vigionline.common.model.Action;
import vigionline.common.model.Camera;
import vigionline.common.model.Division;
import vigionline.common.model.Location;
import vigionline.common.model.Manufacturer;
import vigionline.common.model.Model;
import vigionline.common.model.Permission;
import vigionline.common.model.Role;
import vigionline.common.model.User;
import vigionline.common.model.UserDivision;
import vigionline.common.model.UserRole;

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

	@Override
	public List<Manufacturer> getManufacturers() throws SQLException {
		return new ManufacturerMapper().getAll();
	}

	@Override
	public Manufacturer getManufacturer(int idManufacturer) throws SQLException {
		return new ManufacturerMapper().getById(idManufacturer);
	}

	@Override
	public int updateManufacturer(Manufacturer manufacturer)
			throws SQLException {
		return new ManufacturerMapper().update(manufacturer);
	}

	@Override
	public int createManufacturer(Manufacturer manufacturer)
			throws SQLException {
		return new ManufacturerMapper().insert(manufacturer);
	}

	@Override
	public int deleteManufacturer(int idManufacturer) throws SQLException {
		return new ManufacturerMapper().delete(idManufacturer);
	}

	@Override
	public List<Model> getModelsByManufacturer(int idManufacturer)
			throws SQLException {
		return new ModelMapper().getModelsByManufacturer(idManufacturer);
	}

	@Override
	public List<Model> getModels() throws SQLException {
		return new ModelMapper().getAll();
	}

	@Override
	public Model getModel(int idModel) throws SQLException {
		return new ModelMapper().getById(idModel);
	}

	@Override
	public int updateModel(Model model) throws SQLException {
		return new ModelMapper().update(model);
	}

	@Override
	public int deleteModel(int idModel) throws SQLException {
		return new ModelMapper().delete(idModel);
	}

	@Override
	public List<Action> getActions() throws SQLException {
		return new ActionMapper().getAll();
	}

	@Override
	public Action getAction(int idAction) throws SQLException {
		return new ActionMapper().getById(idAction);
	}

	@Override
	public int createModel(Model model) throws SQLException {
		return new ModelMapper().insert(model);
	}

	@Override
	public int createAction(Action action) throws SQLException {
		return new ActionMapper().insert(action);
	}

	@Override
	public int updateAction(Action action) throws SQLException {
		return new ActionMapper().update(action);
	}

	@Override
	public int deleteAction(int idAction) throws SQLException {
		return new ActionMapper().delete(idAction);
	}

	@Override
	public List<Action> getActionsForModel(int idModel) throws SQLException {
		return new ActionMapper().getByIdModel(idModel);
	}

	@Override
	public List<Camera> getCameras() throws SQLException {
		return new CameraMapper().getAll();
	}

	@Override
	public List<Camera> getCamerasByUsername(String username)
			throws SQLException {
		return new CameraMapper().getByUsername(username);
	}

	@Override
	public List<Camera> getCamerasByDivision(int idDivision)
			throws SQLException {
		return new CameraMapper().getByDivision(idDivision);
	}

	@Override
	public Camera getCamera(int idCamera) throws SQLException {
		return new CameraMapper().getById(idCamera);
	}

	@Override
	public int updateCamera(Camera camera) throws SQLException {
		return new CameraMapper().update(camera);
	}

	@Override
	public int deleteCamera(int idCamera) throws SQLException {
		return new CameraMapper().delete(idCamera);
	}

	@Override
	public int createCamera(Camera camera) throws SQLException {
		return new CameraMapper().insert(camera);
	}

	@Override
	public List<Role> getRolesForUser(int idUser) throws SQLException {
		return new RoleMapper().getByUserId(idUser);
	}

	@Override
	public List<UserRole> getUsersRoles() throws SQLException {
		return new UserRoleMapper().getAll();
	}

	@Override
	public List<Division> getDivisions() throws SQLException {
		return new DivisionMapper().getAll();
	}

	@Override
	public Division getDivision(int idDivision) throws SQLException {
		return new DivisionMapper().getById(idDivision);
	}

	@Override
	public int createDivision(Division division) throws SQLException {
		return new DivisionMapper().insert(division);
	}

	@Override
	public int updateDivision(Division division) throws SQLException {
		return new DivisionMapper().update(division);
	}

	@Override
	public int deleteDivision(int idDivision) throws SQLException {
		return new DivisionMapper().delete(idDivision);
	}

	@Override
	public List<Division> getDivisionsForUser(int idUser) throws SQLException {
		return new DivisionMapper().getByUserId(idUser);
	}

	@Override
	public int createUserRole(UserRole ur) throws SQLException {
		return new UserRoleMapper().insert(ur);
	}

	@Override
	public int createUserDivision(UserDivision ud) throws SQLException {
		return new UserDivisionMapper().insert(ud);
	}

	@Override
	public int clearPermissionsForDivision(int idDivision) throws SQLException {
		return new PermissionMapper().deleteByDivision(idDivision);
	}

	@Override
	public List<Camera> getCamerasByLocation(int idLocation)
			throws SQLException {
		return new CameraMapper().getByLocation(idLocation);
	}

	@Override
	public int clearDivisionsForUser(int idUser) throws SQLException {
		return new UserDivisionMapper().deleteByUser(idUser);
	}

	@Override
	public int clearRolesForUser(String username) throws SQLException {
		return new UserRoleMapper().deleteByUser(username);
	}
}
