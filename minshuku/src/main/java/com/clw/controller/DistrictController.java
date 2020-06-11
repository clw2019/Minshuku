package com.clw.controller;

import com.clw.pojo.DistrictPojo;
import com.clw.service.DistrictService;
import com.clw.utils.CommonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/4/23 21:31
 */
@RestController
@RequestMapping("/district")
public class DistrictController {
    @Resource
    private DistrictService districtService;

    /**
     * 返回三级地址
     * @return
     */
    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public CommonResult selectDistrict() {
        List<DistrictPojo> districtList = districtService.selectDistrict();
        return CommonResult.success(districtList);
    }

    @RequestMapping(value = "/select1", method = RequestMethod.GET)
    public CommonResult selectAllDistrict() {
        List<DistrictPojo> districtList = districtService.selectAllDistrict();
        return CommonResult.success(districtList);
    }
}
