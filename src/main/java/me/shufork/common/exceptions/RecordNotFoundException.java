package me.shufork.common.exceptions;

public class RecordNotFoundException extends BaseException {
    public RecordNotFoundException(String scope, String expectValue) {
        super("not exists",String.format("%s(%s)",scope,expectValue));
    }

    @Override
    public String errorCode() {
        return "entity_not_found";
    }

}
