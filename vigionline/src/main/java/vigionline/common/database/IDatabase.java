package vigionline.common.database;

import java.util.List;

import vigionline.common.model.Permission;
import vigionline.common.model.PermissionType;
import vigionline.common.model.Role;
import vigionline.common.model.User;

public interface IDatabase {
	/** User **/
	public boolean createUser(User user);
	public List<User> getUsers();
	public User getUser(int idUser);
	public boolean updateUser(User user);
	public boolean deleteUser(int idUser);
	public List<User> getUsersByRole(int idRole);

	/** Role **/
	public boolean createRole(Role role);
	public List<Role> getRoles();
	public Role getRole(int idRole);
	public boolean updateRole(Role role);
	public boolean deleteRole(int idRole);

	/** Permission **/
	public boolean createPermission(Permission permission);
	public List<Permission> getPermission();
	public Permission getPermission(int idPermissionType);
	public boolean updatePermission(PermissionType permission);
	public boolean deletePermission(int idPermission);
	public List<Permission> getPermissionsByRole(int idRole);
	public List<Permission> getPermissionsByCamera(int idCamera);
}
