package br.com.cespga.service;

import br.com.cespga.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private Logger logger = Logger.getLogger(UserService.class.getName());

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Nome do usuario"+username);
        var user = userRepository.findByUserName(username);
        if(user != null){
            return user;
        }else {
            throw new UsernameNotFoundException("Usuario"+username+"não encontrado");
        }
    }
}
