package com.dev.ecommerce.dto.response;

import com.dev.ecommerce.entity.enums.Role;

public record UserListResponseDTO(
    Long id,
    String name,
    String email,
    String phoneNumber,
    Role role) {

}
