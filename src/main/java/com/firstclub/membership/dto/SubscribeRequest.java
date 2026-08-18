package com.firstclub.membership.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SubscribeRequest(
        @NotBlank String userId,
        @NotNull Long planId,
        @NotNull Long tierId
) {
}
