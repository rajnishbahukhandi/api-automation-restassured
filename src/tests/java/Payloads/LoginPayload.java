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
}
