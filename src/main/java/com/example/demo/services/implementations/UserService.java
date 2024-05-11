package com.example.demo.services.implementations;

import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements IUserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database: {}", username);

            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.get().getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            });
            // Return the user details, including the username, password, and authorities
            return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(), authorities);
        }
    }

    public User getUserById(Integer userId) {
        return userRepository.findById(userId).orElseThrow( ()->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));
    }
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow( ()->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));
    }
    public User addNewUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Role addNewRole(Role role){
        return roleRepository.save(role);
    }

    public void updateUser(Integer userId, User user){
        Optional<User> maybeUser = userRepository.findById(userId);
        if(maybeUser.isPresent()){
            User userFound = maybeUser.get();
            String username = user.getUsername();
            String password = user.getPassword();
            userFound.setUsername(username);
            userFound.setPassword(password);
            userRepository.save(userFound);
        }else{
            throw new UserNotFoundException("User with id " + userId + " not found");
        }
    }

    public void deleteUser(Integer userId){
        Optional<User> maybeUser = userRepository.findById(userId);
        if(maybeUser.isPresent()){
            userRepository.delete(maybeUser.get());
        }else{
            throw new UserNotFoundException("User with id " + userId + " not found");
        }
    }

    public void addRoleToUser(String username, String roleName) {

        Optional<User> user = userRepository.findByUsername(username);
        Optional<Role> role = roleRepository.findByName(roleName);

        if(user.isPresent() && role.isPresent()) {
            User foundUser = user.get();
            Role foundRole = role.get();
            foundUser.getRoles().add(foundRole);
            userRepository.save(foundUser);
        }
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
