package me.shufork.common.exceptions;

public abstract class BaseException extends RuntimeException{
    private final String detail;

    /**
     *
     * @param message
     * @param detail 放置一些对使用方由帮助是提示信息,可以是null
     */
    public BaseException(String message,String detail) {
        super(message);
        this.detail = detail;
    }

    /**
     *
     * @param message
     * @param cause
     * @param detail 放置一些对使用方由帮助是提示信息,可以是null
     */
    public BaseException(String message, Throwable cause,String detail) {
        super(message, cause);
        this.detail = detail;
    }

    /**
     *
     * @param cause
     * @param detail 放置一些对使用方由帮助是提示信息,可以是null
     */
    public BaseException(Throwable cause,String detail) {
        super(cause);
        this.detail = detail;
    }

    /**
     * 此异常对应的HTTP 状态码,由全局异常处理器使用,AJAX客户端会得到这个状态码
     * @return
     * @see me.shufork.common.advice.ControllerExceptionAdvice
     */
    public int httpCodeAdvice(){
        return 200;
    }

    public String detailInfo(){return detail;}

    /**
     * 返回业务语义的错误代码
     * @return
     */
    public abstract String errorCode();

}
