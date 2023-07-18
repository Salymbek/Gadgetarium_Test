//package peaksoft.house.gadgetariumb9.api;
//
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import peaksoft.house.gadgetariumb9.dto.request.UserRequest;
//import peaksoft.house.gadgetariumb9.dto.response.TokenResponse;
//import peaksoft.house.gadgetariumb9.entities.Mailing;
//import peaksoft.house.gadgetariumb9.entities.User;
//import peaksoft.house.gadgetariumb9.repository.UserRepository;
//import peaksoft.house.gadgetariumb9.service.impl.MailingService;
//import peaksoft.house.gadgetariumb9.service.UserService;
//
//@RestController
//@RequestMapping("/api/user")
//public class UserApi {
//
//  private final UserService userService;
//
//  public UserApi(UserService userService) {
//    this.userService = userService;
//  }
//
////  @PreAuthorize("permitAll()")
////  @PostMapping("/login")
////  public TokenResponse login(@RequestBody UserRequest request) {
////    return userService.authenticate(request);
////  }
//
//
//}
