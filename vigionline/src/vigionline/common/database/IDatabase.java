package vigionline.common.database;

import java.sql.SQLException;
import java.util.List;

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

public interface IDatabase {
	/** User **/
	public int createUser(User user) throws SQLException;
	public List<User> getUsers() throws SQLException;
	public User getUser(int idUser) throws SQLException;
	public int updateUser(User user) throws SQLException;
	public int deleteUser(int idUser) throws SQLException;
	public List<User> getUsersByRole(int idRole) throws SQLException;

	/** Role **/
	public int createRole(Role role) throws SQLException;
	public List<Role> getRoles() throws SQLException;
	public Role getRole(int idRole) throws SQLException;
	public int updateRole(Role role) throws SQLException;
	public int deleteRole(int idRole) throws SQLException;
	public List<Role> getRolesForUser(int idUser) throws SQLException;

	/** Permission **/
	public int createPermission(Permission permission) throws SQLException;
	public List<Permission> getPermissions() throws SQLException;
	public Permission getPermission(int idPermission) throws SQLException;
	public int updatePermission(Permission permission) throws SQLException;
	public int deletePermission(int idPermission) throws SQLException;
	public List<Permission> getPermissionsByRole(int idRole) throws SQLException;
	public List<Permission> getPermissionsByCamera(int idCamera) throws SQLException;
	
	/** Location **/
	public int createLocation(Location location) throws SQLException; 
	public List<Location> getLocations() throws SQLException;
	public Location getLocation(int idLocation) throws SQLException;
	public int updateLocation(Location location) throws SQLException;
	public int deleteLocation(int idLocation) throws SQLException;
	
	/** Manufacturer **/
	public List<Manufacturer> getManufacturers() throws SQLException;
	public Manufacturer getManufacturer(int idManufacturer) throws SQLException;
	public int updateManufacturer(Manufacturer manufacturer) throws SQLException;
	public int createManufacturer(Manufacturer manufacturer) throws SQLException;
	public int deleteManufacturer(int idManufacturer) throws SQLException;
	
	/** Models **/
	public List<Model> getModelsByManufacturer(int idManufacturer) throws SQLException;
	public List<Model> getModels() throws SQLException;
	public Model getModel(int idModel) throws SQLException;
	public int createModel(Model model) throws SQLException;
	public int updateModel(Model model) throws SQLException;
	public int deleteModel(int idModel) throws SQLException;
	
	/** Actions **/
	public List<Action> getActions() throws SQLException;
	public Action getAction(int idAction) throws SQLException;
	public int createAction(Action action) throws SQLException;
	public int updateAction(Action action) throws SQLException;
	public int deleteAction(int idAction) throws SQLException;
	public List<Action> getActionsForModel(int idModel) throws SQLException;

	/** Cameras **/
	public List<Camera> getCameras() throws SQLException;
	public List<Camera> getCamerasByUsername(String username) throws SQLException;
	public List<Camera> getCamerasByDivision(int idDivision) throws SQLException;
	public List<Camera> getCamerasByLocation(int idLocation) throws SQLException;
	public Camera getCamera(int idCamera) throws SQLException;
	public int updateCamera(Camera camera) throws SQLException;
	public int deleteCamera(int idCamera) throws SQLException;
	public int createCamera(Camera camera) throws SQLException;
	
	/** UserRole **/
	public List<UserRole> getUsersRoles() throws SQLException;
	public int createUserRole(UserRole ur) throws SQLException;
	
	/** Division **/
	public List<Division> getDivisions() throws SQLException;
	public Division getDivision(int idDivision) throws SQLException;
	public int createDivision(Division division) throws SQLException;
	public int updateDivision(Division division) throws SQLException;
	public int deleteDivision(int idDivision) throws SQLException;
	public List<Division> getDivisionsForUser(int idUser) throws SQLException;
	
	/** UserDivision **/
	public int createUserDivision(UserDivision ud) throws SQLException;
	public int clearPermissionsForDivision(int idDivision) throws SQLException;
	public int clearDivisionsForUser(int idUser) throws SQLException;
}
