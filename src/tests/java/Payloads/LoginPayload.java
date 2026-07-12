package Payloads;

public class LoginPayload {
    public static String loginSuccess() {
        return """
                {
                    "email": "eve.holt@reqres.in",
                    "password": "cityslicka"
                }
                """;
    }

    public static String loginFailure() {
        return """
                {
                    "email": "eve.holt@reqres.in",
                    "password": "wrongpassword"
                }
                """;
    }

    public static String loginMissingPassword() {
        return """
                {
                    "email": "eve.holt@reqres.in"
                }
                """;
    }

    public static String loginMissingEmail() {
        return """
                {
                    "password": "cityslicka"
                }
                """;
    }

    public static String loginEmpty() {
        return """
                {
                }
                """;
    }

    public static String loginInvalidEmailFormat() {
        return """
                {
                    "email": "invalid-email-format",
                    "password": "cityslicka"
                }
                """;
    }

    public static String loginInvalidPasswordFormat() {
        return """
                {
                    "email": "eve.holt@reqres.in",
                    "password": "invalid-password-format"
                }
                """;
    }

    public static String loginWithExtraFieldsShouldReturnToken() {
        return """
                {
                    "email": "eve.holt@reqres.in",
                    "password": "cityslicka",
                    "extraField": "extraValue"
                }
                """;
    }

    public static String loginBoundaryMaxLengthFieldsTesting() {
        return """
                {
                    "email": "eve.holt@reqres.in",
                    "password": "cityslicka"
                }
                """;
    }

    public static String loginInvalidEmailDomain() {
        return """
                {
                    "email": "eve.holt@invalid-domain.com",
                    "password": "cityslicka"
                }
                """;
    }

    public static String loginInvalidEmailCaseSensitivity() {
        return """
                {
                    "email": "eve.holt@REQRES.IN",
                    "password": "cityslicka"
                }
                """;
    }

    public static String loginInvalidEmailMissingAtSymbol() {
        return """
                {
                    "email": "eve.holtreqres.in",
                    "password": "cityslicka"
                }
                """;
    }

    public static String loginInvalidEmailMissingDomain() {
        return """
                {
                    "email": "eve.holt@",
                    "password": "cityslicka"
                }
                """;
    }

    public static String loginInvalidEmailMultipleAtSymbols() {
        return """
                {
                    "email": "eve.holt@@reqres.in",
                    "password": "cityslicka"
                }
                """;
    }

    public static String loginInvalidEmailLeadingDot() {
        return """
                {
                    "email": ".eve.holt@reqres.in",
                    "password": "cityslicka"
                }
                """;
    }

    public static String loginInvalidEmailSpecialCharactersInDomain() {
        return """
                {
                    "email": "eve.holt@reqres!.in",
                    "password": "cityslicka"
                }
                """;
    }

    public static String loginInvalidEmailWithSpaces() {
        return """
                {
                    "email": "eve.holt @reqres.in",
                    "password": "cityslicka"
                }
                """;
    }
}