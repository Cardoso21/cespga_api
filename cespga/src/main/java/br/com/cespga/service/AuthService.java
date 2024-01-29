package br.com.cespga.service;

import br.com.cespga.date.vo.v1.security.CredenciaisContaVO;
import br.com.cespga.date.vo.v1.security.TokenVO;
import br.com.cespga.repositories.UserRepository;
import br.com.cespga.security.Jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AuthService {
    private static final Logger logger = Logger.getLogger(AuthService.class.getName());

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UserRepository userRepository;

    @Autowired
    public AuthService(
            AuthenticationManager authenticationManager,
            JwtTokenProvider tokenProvider,
            UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.userRepository = userRepository;
    }

    public ResponseEntity<TokenVO> signin(CredenciaisContaVO data) {
        try {
            String username = data.getUsername();
            String password = data.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            var user = userRepository.findByUserName(username);

            if (user != null) {
                TokenVO tokenResponse = tokenProvider.createAccessToken(username, user.getRoles());
                return ResponseEntity.ok(tokenResponse);
            } else {
                throw new UsernameNotFoundException("Username " + username + " not found!");
            }
        } catch (BadCredentialsException e) {
            logger.log(Level.WARNING, "Erro durante autenticação: Credenciais inválidas", e);
            throw new BadCredentialsException("Invalid username/password supplied!", e);
        }
    }

    public ResponseEntity<TokenVO> refreshToken(String username, String refreshToken) {
        var user = userRepository.findByUserName(username);

        if (user != null) {
            TokenVO tokenResponse = tokenProvider.refreshToken(refreshToken);
            return ResponseEntity.ok(tokenResponse);
        } else {
            throw new UsernameNotFoundException("Username " + username + " not found!");
        }
    }
}
