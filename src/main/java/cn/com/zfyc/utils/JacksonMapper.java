package cn.com.zfyc.utils;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by Deng Jialong 2017/02/17
 */
public enum JacksonMapper {
    // JacksonMapper实例
    INSTANCE;
    private static final ObjectMapper MAPPER = new ObjectMapper().configure( JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true );
    private final static Log log = LogFactory.get();


    public Map< String, ? > readJsonToObject( String jsonData ) {
        Map< String, ? > map = new HashMap<>();
        try {
            map = MAPPER.readValue( jsonData, Map.class );
        } catch ( JsonParseException e ) {
            log.error( e, "Jackson发生解析异常，详细信息", jsonData );
        } catch ( JsonMappingException e ) {
            log.error( e, "Jackson发生映射异常，详细信息：{}", jsonData );
        } catch ( IOException e ) {
            log.error( e, "Jackson处理数据{}时发生转换错误，详细信息：{}", jsonData );
        }
        return map;
    }

    public List< Map > readJsonToList( String jsonData ) {
        List< Map > list = new ArrayList<>();
        try {
            list = MAPPER.readValue( jsonData, List.class );
        } catch ( JsonParseException e ) {
            log.error( e, "Jackson发生解析异常，详细信息", jsonData );
        } catch ( JsonMappingException e ) {
            log.error( e, "Jackson发生映射异常，详细信息：{}", jsonData );
        } catch ( IOException e ) {
            log.error( e, "Jackson处理数据{}时发生转换错误，详细信息：{}", jsonData );
        }
        return list;
    }

    public String writeObjectToJson( Object obj ) {
        try {
            return MAPPER.writeValueAsString( obj );
        } catch ( JsonProcessingException e ) {
            log.error( e, "对象{}转换为json数据异常!", obj );
        }
        return "{}";
    }


}
