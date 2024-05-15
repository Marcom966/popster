package com.example.demo.User;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public void deleteUser(Long id){
        boolean exist = utenteRepository.existsById(id);
        if(!exist){
            throw new IllegalStateException("student with id"+id+"does not exist");
        }
        utenteRepository.deleteById(id);
    }

    @Transactional
    public void updateUser(Long userId, String name, String eMail){
        UserOfPopster user  = utenteRepository.findById(userId).orElseThrow(()-> new IllegalStateException("user with id: "+userId+"does not exist"));
        if(name!=null&&name.length()>0&&!Objects.equals(user.getName(), name)){
            user.setName(name);
        }
        if(eMail!=null&&eMail.length()>0&&!Objects.equals(user.geteMail(), eMail)){
            Optional<UserOfPopster> userOpt = utenteRepository.findUserByEmail(eMail);
            if(userOpt.isPresent()){
                throw new IllegalStateException("email taken");
            }
        user.seteMail(eMail);
        }

    }
}
