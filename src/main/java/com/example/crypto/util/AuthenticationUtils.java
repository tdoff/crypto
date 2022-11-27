package com.example.crypto.util;

import org.springframework.security.core.context.*;

import java.util.*;

import static java.util.Objects.nonNull;

public final class AuthenticationUtils {

    private AuthenticationUtils() {
        throw new UnsupportedOperationException("Impossible instantiate utility class");
    }

    /**
     * Returns username if there is authenticated user, otherwise - returns empty {@code Optional} <br/>
     * For JWT-based authentication returns {@code sub} claim value
     */
    public static Optional<String> getCurrentUsername() {
        final var authentication = SecurityContextHolder.getContext().getAuthentication();
        return nonNull(authentication) ? Optional.of(authentication.getName()) : Optional.empty();
    }

}
