package me.shufork.common.enums;

public enum BuildInRoleNameEnums {

    ROLE_USER("ROLE_USER"), ROLE_ADMIN("ROLE_ADMIN");

    private final String value;

    BuildInRoleNameEnums(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static BuildInRoleNameEnums parseOrDefault(final String value, final BuildInRoleNameEnums defValue) {
        for (BuildInRoleNameEnums o : BuildInRoleNameEnums.values()) {
            if (o.getValue().equals(value)) {
                return o;
            }
        }
        return defValue;
    }

    public static BuildInRoleNameEnums parse(final String value) throws IllegalArgumentException {
        BuildInRoleNameEnums o = parseOrDefault(value, null);
        if (o == null) {
            throw new IllegalArgumentException("Invalid value : " + value);
        }
        return o;
    }

}
