package Payloads;

public class RegisterPayload {
    public static String registerSuccess() {
        return """
                {
                    "email": "eve.holt@reqres.in",
                    "password": "pistol"
                }
                """;
    }

    public static String registerFailure() {
        return """
                {
                    "email": "sydney@fife",
                    "password": "pass"
                }
                """;
    }

    public static String registerMissingPassword() {
        return """
                {
                    "email": "sydney@fife"  
                }
                """;
    }

    public static String registerMissingEmail() {
        return """
                {
                    "password": "pistol"
                }
                """;
    }

    public static String registerEmpty() {
        return """
                {
                }
                """;
    }

}
