package cz.upce.vetalmael.security;

public class SecurityConstants {

    public static final String SECRET = "SECRET_KEY";
    public static final long EXPIRATION_TIME = 604800000; // week
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/clients/sign-up";
    public static final String SIGN_IN_URL = "/users/login";

}
