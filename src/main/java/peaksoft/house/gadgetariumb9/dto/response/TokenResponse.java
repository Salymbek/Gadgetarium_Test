package peaksoft.house.gadgetariumb9.dto.response;

import lombok.Builder;

@Builder
public record TokenResponse(
    String email,
    String token
) {
}
