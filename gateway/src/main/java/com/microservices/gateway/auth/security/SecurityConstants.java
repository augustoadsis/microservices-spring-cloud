package com.microservices.gateway.auth.security;

public class SecurityConstants {
    public static final String SECRET = "fda5f2765eddcf2dbc48b65056d42fe3";
    public static final long EXPIRATION_TIME = 31_536_000_000L; // 365 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
