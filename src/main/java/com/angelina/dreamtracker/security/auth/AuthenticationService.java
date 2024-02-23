package com.angelina.dreamtracker.security.auth;

import com.angelina.dreamtracker.exception.AccountAlreadyExists;
import com.angelina.dreamtracker.model.Role;
import com.angelina.dreamtracker.model.User;
import com.angelina.dreamtracker.repository.UserRepository;
import com.angelina.dreamtracker.security.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) throws AccountAlreadyExists {

        if (userRepository.existsByEmail(request.email()))
            throw new AccountAlreadyExists("There already exists an account with this email.");

        var user = User.builder()
                .nickname(request.nickname())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Username couldn't be found."));
        var jwtToken = jwtService.generateToken(user);
        var userId = user.getId();
        var nickname = user.getNickname();

        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .userId(userId)
                .nickname(nickname)
                .build();
    }
}
