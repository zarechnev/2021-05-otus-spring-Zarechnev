package ru.otus.zarechnev.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.otus.zarechnev.library.domain.LibraryUser;
import ru.otus.zarechnev.library.repository.LibraryUserRepository;

@RequiredArgsConstructor
@Service
public class LibraryUserDetailsService implements UserDetailsService {

    private final LibraryUserRepository libraryUserRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        LibraryUser user = libraryUserRepository.findFirstByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new User(
                user.getLogin(),
                user.getPassword(),
                AuthorityUtils.createAuthorityList(user.getAuthority())
        );
    }
}
