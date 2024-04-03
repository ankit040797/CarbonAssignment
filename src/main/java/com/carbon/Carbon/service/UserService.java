package com.carbon.Carbon.service;
import com.carbon.Carbon.repo.IUserRepo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserService implements UserDetailsService{
    @Autowired
    IUserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }
}
