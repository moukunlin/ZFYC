package cn.com.zfyc.bean;

import cn.com.zfyc.utils.ExceptionUtil;
import cn.com.zfyc.utils.JacksonMapper;

import java.io.Serializable;

/**
 * 通用 Restful 返回值封装类
 *
 * @author Leucippus
 * @version 0.1
 * @since 2018/5/14 14:54
 */
public class RestfulRecord implements Serializable {
    private int code = 200;
    private String msg;
    private Object data;
    private String exceptionStackTrace;

    private Exception exception;

    public void success() {
        this.code = 200;
    }

    public void error() {
       this.code = 500;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg( String msg ) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData( Object data ) {
        this.data = data;
    }

    public void setCode( int code ) {
        this.code = code;
    }

    public void setExceptionStackTrace( String exceptionStackTrace ) {
        this.exceptionStackTrace = exceptionStackTrace;
    }

    public Exception getException() {
        return exception;
    }

    public String getExceptionStackTrace() {
        return exceptionStackTrace;
    }

    public void setException( Exception exception ) {
        error();
        this.exception = exception;
        this.exceptionStackTrace = ExceptionUtil.getStackTrace( exception );
    }

    public int getCode() {
        return code;
    }

    public RestfulRecord() {
    }

    public RestfulRecord(String msg, Object data, Exception exception ) {
        this.msg = msg;
        this.data = data;
        this.exception = exception;
    }

    public RestfulRecord(int code, String msg, Object data, Exception exception ) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.exception = exception;
        this.exceptionStackTrace = ExceptionUtil.getStackTrace( exception );
    }

    public RestfulRecord(int code ) {
        this.code = code;
    }

    public RestfulRecord(int code, Object data ) {
        this.code = code;
        this.data = data;
    }

    public RestfulRecord(boolean isSuccess ) {
        this.code = isSuccess ? 200 : 500;
    }

    public RestfulRecord(int code, String msg ) {
        this.code = code;
        this.msg = msg;
    }

    public RestfulRecord(boolean isSuccess, String msg ) {
        this.code = isSuccess ? 200 : 500;
        this.msg = msg;
    }

    public RestfulRecord(int code, String msg, Object data ) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public RestfulRecord(boolean isSuccess, String msg, Object data ) {
        this.code = isSuccess ? 200 : 500;
        this.msg = msg;
        this.data = data;
    }


    public RestfulRecord(int code, String msg, Exception exception ) {
        this.code = code;
        this.msg = msg;
        this.exception = exception;
        this.exceptionStackTrace = ExceptionUtil.getStackTrace( exception );
    }

    public RestfulRecord(boolean isSuccess, String msg, Exception exception ) {
        this.code = isSuccess ? 200 : 500;
        this.msg = msg;
        this.exception = exception;
        this.exceptionStackTrace = ExceptionUtil.getStackTrace( exception );
    }
    public boolean isSuccess(){
        return code==200;
    }

    @Override
    public String toString() {
        return JacksonMapper.INSTANCE.writeObjectToJson( this );
    }

    public static void main( String[] args ) {
        RestfulRecord record = new RestfulRecord();
        System.out.println(record);
    }
}
