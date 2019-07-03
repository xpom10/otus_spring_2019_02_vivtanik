package ru.otus.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.otus.security.domain.LibraryUser;
import ru.otus.security.repository.UserRepository;

import java.util.Optional;

@Service
@SuppressWarnings("deprecation")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class MongoCustomDetailsService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<LibraryUser> optionalUser = repository.findById(username);
        return optionalUser
                .map(LibraryUser::toUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));
    }
}
