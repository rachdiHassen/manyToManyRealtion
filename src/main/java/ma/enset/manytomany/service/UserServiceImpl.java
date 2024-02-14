package ma.enset.manytomany.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.enset.manytomany.entities.Roles;
import ma.enset.manytomany.entities.Users;
import ma.enset.manytomany.repositories.RolesRepository;
import ma.enset.manytomany.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private UsersRepository usersRepository;
    private RolesRepository rolesRepository;
    @Override
    public Users addNewUser(Users user) {
        user.setUserId(UUID.randomUUID().toString());
        return usersRepository.save(user);
    }

    @Override
    public Roles addNewRole(Roles role) {
        return rolesRepository.save(role);
    }

    @Override
    public Users findUserByUsername(String username) {
        return usersRepository.findByUserName(username);
    }

    @Override
    public Roles findRoleByRolename(String rolename) {
        return rolesRepository.findByRoleName(rolename);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        Users user =usersRepository.findByUserName(username);
        Roles role=rolesRepository.findByRoleName(rolename);
        // LE test !=null pour tester qu'il nya pas un NullPointerException et non pas vide
        if (user.getRoles()!=null){
            user.getRoles().add(role);
            role.getUsers().add(user);
        }
    }

    @Override
    public Users authentification(String username, String password) {
        Users user=usersRepository.findByUserName(username);
        if(user==null) throw new RuntimeException("bad authentification");
        if(user.getPassword().equals(password)){
            return user;
        }
        throw new RuntimeException("bad authentification");
    }
}
