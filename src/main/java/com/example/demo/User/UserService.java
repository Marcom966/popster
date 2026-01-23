package com.example.demo.User;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UtenteRepository utenteRepository;

    @Autowired
    public UserService(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    public List<UserOfPopster> getStudents(){
        return utenteRepository.findAll();
    }


    public void addNewUser(UserOfPopster user) {
        Optional<UserOfPopster> userByEmailOptional = utenteRepository.findUserByEmail(user.geteMail());
        if(userByEmailOptional.isPresent()){
            throw new IllegalStateException("Email aldready taken");
        }
        utenteRepository.save(user);

    }
    public UserOfPopster getSpecificUser(String user_name){
        Optional<UserOfPopster> userName = utenteRepository.findUserByUserName(user_name);
        return userName.orElseThrow(() -> new IllegalStateException("Username not found"));
    }

    public void deleteUser(Long id){
        boolean exist = utenteRepository.existsById(id);
        if(!exist){
            throw new IllegalStateException("student with id"+id+"does not exist");
        }
        utenteRepository.deleteById(id);
    }

    @Transactional
    public void updateUser(Long userId, UpdateUserRequest req){
        UserOfPopster user  = utenteRepository.findById(userId).orElseThrow(()-> new IllegalStateException("user with id: "+userId+"does not exist"));
        if(req.getName() != null){
            user.setName(req.getName());
        }
        if(req.getSurname() != null){
            user.setSurname(req.getSurname());
        }
        if(req.getBirth() != null){
            user.setBirth(req.getBirth());
        }
        if(req.geteMail() != null && !Objects.equals(req.geteMail(), user.geteMail())){
            utenteRepository.findUserByEmail(req.geteMail()).ifPresent(u -> {
                        throw new IllegalStateException("email already exist");
                    });
            user.seteMail(req.geteMail());
        };
        if(req.getUser_name() != null&& !Objects.equals(req.getUser_name(), user.getUser_name())){
            utenteRepository.findUserByUserName(req.getUser_name()).ifPresent(u -> {
                throw new IllegalStateException("username already exist");
            });
            user.setUser_name(req.getUser_name());
        }
        if (req.getPassword() != null){
            user.setPassword(req.getPassword());
        }

    }
}
