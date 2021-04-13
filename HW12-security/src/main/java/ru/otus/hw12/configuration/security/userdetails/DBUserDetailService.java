package ru.otus.hw12.configuration.security.userdetails;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.otus.hw12.dao.domain.User;
import ru.otus.hw12.dao.repository.UserRepository;

@Service
public class DBUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public DBUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new DefaultUserPrinciple(user);
    }
}
