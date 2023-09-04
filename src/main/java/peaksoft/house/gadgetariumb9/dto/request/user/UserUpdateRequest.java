package peaksoft.house.gadgetariumb9.dto.request.user;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import peaksoft.house.gadgetariumb9.validations.password.Password;
import peaksoft.house.gadgetariumb9.validations.phoneNumber.PhoneNumber;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {

  private String firstName;

  private String lastName;

  @Email(message = "Wrong format email")
  private String email;

  @PhoneNumber(message = "Wrong format phone number")
  private String phoneNumber;

  private String address;

  private String oldPassword;

  @Password(message = "Wrong format password")
  private String newPassword;

  private String imageLink;

}