package com.firstclub.membership.dto;

import jakarta.validation.constraints.NotNull;

public record ChangeTierRequest(
        @NotNull Long targetTierId
) {
}
