package ma.enset.manytomany.service;

import ma.enset.manytomany.entities.Roles;
import ma.enset.manytomany.entities.Users;

public interface UserService {
    Users addNewUser(Users user);
    Roles addNewRole(Roles role);
    Users findUserByUsername(String username);
    Roles findRoleByRolename(String rolename);
    void addRoleToUser(String username, String rolename);
    Users authentification (String username, String password);

}
