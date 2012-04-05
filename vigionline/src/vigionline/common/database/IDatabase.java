package vigionline.common.database;

import java.sql.SQLException;
import java.util.List;

import vigionline.common.model.Location;
import vigionline.common.model.Manufacturer;
import vigionline.common.model.Permission;
import vigionline.common.model.Role;
import vigionline.common.model.User;

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
}
