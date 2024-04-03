package com.carbon.Carbon.service;
import com.carbon.Carbon.model.AuthResponse;
import com.carbon.Carbon.model.User;
import com.carbon.Carbon.repo.IUserRepo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthService {
    @Autowired
    IUserRepo userRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;


    public AuthResponse register(User request) {

        // check if user already exist. if exist than authenticate the user
        if(userRepo.findByEmail(request.getUsername()).isPresent()) {
            return new AuthResponse(null, "User already exist");
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));


        user.setRole(request.getRole());

        user = userRepo.save(user);

        String jwt = jwtService.generateToken(user);


        return new AuthResponse(jwt, "User registration was successful");

    }

    public AuthResponse authenticate(User request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = userRepo.findByEmail(request.getUsername()).orElseThrow();
        String jwt = jwtService.generateToken(user);


        return new AuthResponse(jwt, "User login was successful");

    }
}
