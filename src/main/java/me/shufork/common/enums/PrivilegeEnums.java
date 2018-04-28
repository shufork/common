package me.shufork.common.enums;


public enum PrivilegeEnums {

    NONE(0),
    EX(1),
    WT(2),
    XW(3),
    RD(4),
    XR(5),
    WR(6),
    XWR(7);

    private final int value;

    PrivilegeEnums(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static PrivilegeEnums parseOrDefault(final int value, final PrivilegeEnums defValue) {
        for (PrivilegeEnums o : PrivilegeEnums.values()) {
            if (o.getValue() == value) {
                return o;
            }
        }
        return defValue;
    }

    public static PrivilegeEnums parse(final int value) throws IllegalArgumentException {
        PrivilegeEnums o = parseOrDefault(value, null);
        if (o == null) {
            throw new IllegalArgumentException("Invalid value : " + value);
        }
        return o;
    }

}
