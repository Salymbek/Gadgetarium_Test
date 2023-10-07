package peaksoft.house.gadgetariumb9.services.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.house.gadgetariumb9.config.security.JwtService;
import peaksoft.house.gadgetariumb9.dto.request.user.UserUpdateRequest;
import peaksoft.house.gadgetariumb9.dto.response.order.OrderHistoryResponse;
import peaksoft.house.gadgetariumb9.dto.response.order.OrderInfoByUserResponse;
import peaksoft.house.gadgetariumb9.dto.response.user.UserFavoritesResponse;
import peaksoft.house.gadgetariumb9.dto.response.user.UserResponse;
import peaksoft.house.gadgetariumb9.dto.simple.SimpleResponse;
import peaksoft.house.gadgetariumb9.exceptions.AlreadyExistException;
import peaksoft.house.gadgetariumb9.exceptions.BadCredentialException;
import peaksoft.house.gadgetariumb9.exceptions.NotFoundException;
import peaksoft.house.gadgetariumb9.models.User;
import peaksoft.house.gadgetariumb9.repositories.UserRepository;
import peaksoft.house.gadgetariumb9.services.FavoriteService;
import peaksoft.house.gadgetariumb9.services.OrderService;
import peaksoft.house.gadgetariumb9.services.UserService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final JwtService jwtService;

  private final PasswordEncoder passwordEncoder;

  private final FavoriteService favoriteService;

  private final OrderService orderService;

  @Override
  public SimpleResponse userUpdate(UserUpdateRequest userUpdateRequest) {
    User user = jwtService.getAuthenticationUser();
    if (userRepository.existsByEmailAndIdNot(userUpdateRequest.getEmail(), user.getId())) {
      throw new AlreadyExistException("User with email: %s already exists".formatted(userUpdateRequest.getEmail()));
    }
    if (userUpdateRequest.getOldPassword() != null && !userUpdateRequest.getOldPassword().isBlank()) {
      if (!passwordEncoder.matches(userUpdateRequest.getOldPassword(), user.getPassword())) {
        log.error("Wrong password");
        throw new BadCredentialException("Wrong password!");
      }
    }
    if (userUpdateRequest.getFirstName() != null && !userUpdateRequest.getFirstName().isBlank()) {
      user.setFirstName(userUpdateRequest.getFirstName());
    }
    if (userUpdateRequest.getLastName() != null && !userUpdateRequest.getLastName().isBlank()) {
      user.setLastName(userUpdateRequest.getLastName());
    }
    if (userUpdateRequest.getEmail() != null && !userUpdateRequest.getEmail().isBlank()) {
      user.setEmail(userUpdateRequest.getEmail());
    }
    if (userUpdateRequest.getPhoneNumber() != null && !userUpdateRequest.getPhoneNumber().isBlank()) {
      User existingUserWithPhoneNumber = userRepository.findByPhoneNumber(userUpdateRequest.getPhoneNumber());
      if (existingUserWithPhoneNumber != null && !existingUserWithPhoneNumber.getId().equals(user.getId())) {
        log.error("Phone number already exists");
        throw new AlreadyExistException("Phone number already exists!");
      }
      user.setPhoneNumber(userUpdateRequest.getPhoneNumber());
    }
    if (userUpdateRequest.getAddress() != null && !userUpdateRequest.getAddress().isBlank()) {
      user.setAddress(userUpdateRequest.getAddress());
    }
    if (userUpdateRequest.getNewPassword() != null && !userUpdateRequest.getNewPassword().isBlank()) {
      user.setPassword(passwordEncoder.encode(userUpdateRequest.getNewPassword()));
    }
    if (userUpdateRequest.getImageLink() != null && !userUpdateRequest.getImageLink().isBlank()) {
      user.setImage(userUpdateRequest.getImageLink());
    }
    userRepository.save(user);
    log.info("User successfully updated");
    return SimpleResponse
        .builder()
        .status(HttpStatus.OK)
        .message("User successfully updated")
        .build();
  }

  @Override
  public UserResponse getUser() {
    User user = jwtService.getAuthenticationUser();
    return userRepository.getUserById(user.getId()).orElseThrow(() -> {
      log.error("User not found");
      return new NotFoundException("User not found");
    });
  }

  @Override
  public List<UserFavoritesResponse> getFavoritesByUser() {
    User user = jwtService.getAuthenticationUser();
    return favoriteService.getAllFavoriteByUserId(user.getId());
  }

  @Override
  public List<OrderHistoryResponse> getOrdersByUser() {
    User user = jwtService.getAuthenticationUser();
    return orderService.getOrdersByUserId(user.getId());
  }

  @Override
  public Map<String,String> getPhoneNumber(){
    User user = jwtService.getAuthenticationUser();
    Map<String,String> getUser = new HashMap<>();
    getUser.put(user.getPhoneNumber(),user.getImage());
    return getUser;
  }

  @Override
  public OrderInfoByUserResponse getOrderInfoByUser(Long orderId) {
    User user = jwtService.getAuthenticationUser();
    return orderService.getOrderByUser(orderId, user.getId());
  }
}