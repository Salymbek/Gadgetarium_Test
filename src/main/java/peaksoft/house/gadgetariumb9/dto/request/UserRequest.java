package peaksoft.house.gadgetariumb9.dto.request;

import lombok.Builder;
import peaksoft.house.gadgetariumb9.enums.Role;

@Builder
public record UserRequest (
    String firstName,
    String lastName,
    String phoneNumber,
    String email,
    String password,
    Role role,
    String address,
    String image
) {
}