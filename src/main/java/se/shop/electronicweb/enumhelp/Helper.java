package se.shop.electronicweb.enumhelp;

public enum Helper {
    ADMIN_REG("Admin created, welcome"),
    LOGIN_GRANTED("Login granted, welcome:"),
    NEW_LOGIN_CREATED("Login credentials created, welcome");

    private String message;

    Helper(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
