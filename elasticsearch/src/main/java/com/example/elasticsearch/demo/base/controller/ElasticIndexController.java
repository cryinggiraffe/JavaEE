package com.example.elasticsearch.demo.base.controller;

import com.example.elasticsearch.demo.base.entity.ResponseResult;
import com.example.elasticsearch.demo.base.enums.ResponseCode;
import com.example.elasticsearch.demo.base.service.BaseElasticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ElasticIndexController {

    @Autowired
    BaseElasticService baseElasticService;

    @GetMapping(value = "/")
    public ResponseResult index(String index){
        return new ResponseResult();
    }

    @RequestMapping(value = "/edit")
    public ResponseResult indexExist(@RequestParam("index") String index){

        ResponseResult response = new ResponseResult();
        try {
            if(!baseElasticService.isExistsIndex(index)){
                System.out.print(index);
                response.setCode(ResponseCode.RESOURCE_NOT_EXIST.getCode());
                response.setMsg(ResponseCode.RESOURCE_NOT_EXIST.getMsg());
            } else {
                response.setMsg(index);
            }
        } catch (Exception e) {
            response.setCode(ResponseCode.NETWORK_ERROR.getCode());
            //response.setMsg(" 调用ElasticSearch 失败！");
            response.setMsg(e.getMessage());
            response.setStatus(false);
        }
        return response;
    }

}
