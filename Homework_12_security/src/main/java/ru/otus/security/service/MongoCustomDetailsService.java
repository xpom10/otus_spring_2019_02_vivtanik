package ru.otus.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.otus.security.domain.User;
import ru.otus.security.repository.UserRepository;

import java.util.Optional;

@Service
@SuppressWarnings("deprecation")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class MongoCustomDetailsService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = repository.findById(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .password(NoOpPasswordEncoder.getInstance().encode(user.getPassword()))
                    .roles(user.getRoles()).build();
        } else {
            throw new UsernameNotFoundException(String.format("Username %s not found", username));
        }
    }
}
