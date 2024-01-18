package com.project4.library.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class BorrowedBookRequest {
    @NotNull(message = "userId cannot be null")
    @NotEmpty(message = "userId cannot be empty")
    private Long userId;

    @NotNull(message = "bookId cannot be null")
    @NotEmpty(message = "bookId cannot be empty")
    private Long bookId;
}
