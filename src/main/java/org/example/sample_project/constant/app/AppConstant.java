package org.example.sample_project.constant.app;

public final class AppConstant {
  // Private constructor to prevent instantiation
  private AppConstant() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  // JWT
  public static final String JWT_SCHEME_NAME = "bearerAuth";
  public static final String JWT_BEARER_FORMAT = "JWT";
  public static final String JWT_SCHEME = "bearer";

  // MEDIA TYPE
  public static final String APPLICATION_JSON = "application/json";

  // KEYCLOAK
  public static final String KEYCLOAK_SCHEME_NAME = "keycloak";

  // Severlet Request Attribute
  public static final String REQUEST_ID = "requestId";
}
