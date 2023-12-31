package peaksoft.house.gadgetariumb9.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import peaksoft.house.gadgetariumb9.dto.request.authentication.SignInRequest;
import peaksoft.house.gadgetariumb9.dto.request.authentication.SignUpRequest;
import peaksoft.house.gadgetariumb9.dto.response.authentication.AuthenticationResponse;
import peaksoft.house.gadgetariumb9.dto.simple.SimpleResponse;
import peaksoft.house.gadgetariumb9.services.AuthenticationService;
import peaksoft.house.gadgetariumb9.validations.password.Password;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication", description = "Authentication for users")
public class AuthenticationApi {

  private final AuthenticationService authenticationService;

  @PostMapping("/sign-up")
  @Operation(summary = "Sign up", description = "Register new users")
  public AuthenticationResponse signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
    return authenticationService.signUp(signUpRequest);
  }

  @PostMapping("/sign-in")
  @Operation(summary = "Sign in", description = "Login for existing users")
  public AuthenticationResponse signIn(@RequestBody @Valid SignInRequest signInRequest) {
    return authenticationService.signIn(signInRequest);
  }

  @PostMapping("/forgot-password")
  @Operation(summary = "Forgot password", description = "Initiate password recovery")
  public SimpleResponse forgotPassword(
      @RequestParam
      @Valid
      @Email
      String email,
      @RequestParam
      String link) {
    return authenticationService.forgotPassword(email, link);
  }

  @PostMapping("/reset-password/{userId}")
  @Operation(summary = "Reset password", description = "Reset password for a specific user")
  public SimpleResponse resetPassword(
      @RequestParam
      @Valid
      @Password
      String password,
      @PathVariable
      Long userId) {
    return authenticationService.resetPassword(password, userId);
  }
}