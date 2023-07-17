package peaksoft.house.gadgetariumb9.service.impl;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.house.gadgetariumb9.cfg.JwtService;
import peaksoft.house.gadgetariumb9.dto.request.UserRequest;
import peaksoft.house.gadgetariumb9.dto.response.TokenResponse;
import peaksoft.house.gadgetariumb9.entities.User;
import peaksoft.house.gadgetariumb9.enums.Role;
import peaksoft.house.gadgetariumb9.exception.NotFoundException;
import peaksoft.house.gadgetariumb9.repository.UserRepository;
import peaksoft.house.gadgetariumb9.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder encoder;
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;


  @Autowired
  public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder, AuthenticationManager authenticationManager, JwtService jwtService) {
    this.userRepository = userRepository;
    this.encoder = encoder;
    this.authenticationManager = authenticationManager;
    this.jwtService = jwtService;
  }


  @PostConstruct
  public void init() {
    User user = new User();
    user.setFirstName("Admin");
    user.setLastName("admin");
    user.setEmail("khadzhakeldyevv@gmail.com");
    user.setPassword(encoder.encode("admin"));
    user.setRole(Role.ADMIN);
    user.setAddress("Address");
    user.setImage("image");
    user.setSubscription(true);
    if (!userRepository.existsByEmail("khadzhakeldyevv@gmail.com")) {
      userRepository.save(user);
    }
  }

  @Override
  public TokenResponse authenticate(UserRequest userRequest) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            userRequest.email(),
            userRequest.password()
        )
    );

        User user = userRepository.findByEmail(userRequest.email())
        .orElseThrow(() -> new NotFoundException(String.format
            ("User with email: %s doesn't exists", userRequest.email())));
    String token = jwtService.generateToken(user);

    return TokenResponse.builder()
        .token(token)
        .email(user.getEmail())
        .build();
  }
}