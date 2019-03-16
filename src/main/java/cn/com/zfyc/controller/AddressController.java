package cn.com.zfyc.controller;

import cn.com.zfyc.bean.AddressEntity;
import cn.com.zfyc.bean.RestfulRecord;
import cn.com.zfyc.constants.WebMessageConstants;
import cn.com.zfyc.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author created by putc on 2019/3/8
 */
@RestController
public class AddressController {


    @Autowired
    private AddressService addressService;

    @PostMapping("/address/save")
    public RestfulRecord save(@RequestBody AddressEntity addressEntity){
        Integer saveResult = addressService.save(addressEntity);
        if(null == saveResult || saveResult <=0 ){
            return new RestfulRecord(500,"地址新增失败", WebMessageConstants.FAIL);
        }
        return new RestfulRecord(200,"地址新增成功", WebMessageConstants.SUCCESS);
    }

    @PostMapping("/address/update")
    public RestfulRecord update(@RequestBody AddressEntity addressEntity){
        Integer saveResult = addressService.update(addressEntity);
        if(null == saveResult || saveResult <=0 ){
            return new RestfulRecord(500,"地址更新失败", WebMessageConstants.FAIL);
        }
        return new RestfulRecord(200,"地址更新成功", WebMessageConstants.SUCCESS);
    }

    @GetMapping("/address/listAll/{createUser}")
    public RestfulRecord listAllAddress(@PathVariable String createUser){
        return new RestfulRecord(addressService.listAllAddress(createUser));
    }

    @PostMapping("/address/delete")
    public RestfulRecord delete(@RequestParam Integer addressId){
        Integer result = addressService.delete(addressId);
        if(null == result || result <= 0){
            return new RestfulRecord(500,"地址信息删除失败", WebMessageConstants.FAIL);
        }
        return new RestfulRecord(200,"地址信息删除成功", WebMessageConstants.SUCCESS);
    }
}

