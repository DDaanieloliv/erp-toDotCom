package com.example.erp_todotcom.rest.dto;

import com.example.erp_todotcom.domain.entity.Role;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

public record FeedItemDto(
        UUID userId,
        String username,
        Instant creatianTimeStamp,
        String password,
        Set<Role> roles) {
}
