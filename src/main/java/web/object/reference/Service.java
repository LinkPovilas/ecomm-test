package web.object.reference;

public enum Service {
    BARBORA("Barbora"),
    BARBORA_EXPRESS("Barbora express");

    public final String value;

    Service(String service) {
        this.value = service;
    }

    public String getValue() {
        return value;
    }
}
