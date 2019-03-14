package cn.com.zfyc.intercepter;

import cn.com.zfyc.bean.RestfulRecord;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * controller 返回值为 json 类型的依然会经过组装。
 */
@ControllerAdvice
public class ResponseHandler implements ResponseBodyAdvice {
    private final static Log logger = LogFactory.get();


    /**
     * <del>固定为 json 解析规则</del>
     */
    @Override
    public boolean supports(@Nullable MethodParameter methodParameter, @Nullable Class converterType ) {
        //  return converterType == JsonbHttpMessageConverter.class;
        return true;
    }

    @Nullable
    @Override
    public Object beforeBodyWrite(@Nullable Object o, @NonNull MethodParameter methodParameter, @NonNull MediaType mediaType, @NonNull Class selectedConverterType, @NonNull ServerHttpRequest serverHttpRequest, @NonNull ServerHttpResponse serverHttpResponse ) {
        if ( o != null ) {
            if ( o instanceof RestfulRecord ) {
                return o;
            }
            RestfulRecord restfulRecord = new RestfulRecord();
            if ( o instanceof Exception ) {
                restfulRecord.setCode( 500 );
                restfulRecord.setMsg( ( ( Exception ) o ).getMessage() );
                restfulRecord.setException( ( Exception ) o );
            } else if (o instanceof String) {
                restfulRecord.setData( o );
                return restfulRecord.toString();
            } else {
                restfulRecord.setData( o );
            }
            return restfulRecord;
        }
        return null;
    }

    @ExceptionHandler
    @ResponseBody
    public RestfulRecord dealException(HttpServletRequest request, @NonNull Exception e){
        return new RestfulRecord(500,e.getMessage(),e.getCause());
    }
}
