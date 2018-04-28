package me.shufork.common.enums;

public enum ErrorCodeEnums {

    OK("OK"), BAD_REQUEST("BAD_REQUEST"), SERVER_ERROR("SERVER_ERROR"),RPC_FAILED("RPC_FAILED");

    private final String value;

    ErrorCodeEnums(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ErrorCodeEnums parseOrDefault(final String value, final ErrorCodeEnums defValue) {
        for (ErrorCodeEnums o : ErrorCodeEnums.values()) {
            if (o.getValue().equals(value)) {
                return o;
            }
        }
        return defValue;
    }

    public static ErrorCodeEnums parse(final String value) throws IllegalArgumentException {
        ErrorCodeEnums o = parseOrDefault(value, null);
        if (o == null) {
            throw new IllegalArgumentException("Invalid value : " + value);
        }
        return o;
    }

}
