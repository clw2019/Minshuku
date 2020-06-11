package com.clw.controller;

import com.clw.pojo.HomeData;
import com.clw.pojo.HomeDetailData;
import com.clw.pojo.MyHomePojo;
import com.clw.pojo.PublishPojo;
import com.clw.service.HomeService;
import com.clw.utils.CommonPage;
import com.clw.utils.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/home")
public class HomeController {

    @Resource
    private HomeService homeService;

    /**
     * 房屋信息列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/data")
    public CommonResult<CommonPage<HomeData>> getHomeData(@RequestParam(value = "currentPage", defaultValue = "1") Integer pageNum,
                                                          @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize,
                                                          @RequestParam(value = "address", required = false) String address) {
        List<HomeData> homeDataList = homeService.getHomeData(pageNum, pageSize, address);
        return CommonResult.success(CommonPage.resultPage(homeDataList));
    }

    /**
     * 房屋详情
     * @param id
     * @return
     */
    @GetMapping("/detail")
    public CommonResult getDetail(Integer id) {
        List<HomeDetailData> detailList = homeService.getDetail(id);
        return CommonResult.success(detailList);
    }

    /**
     * 发布房源
     * @param params
     * @return
     */
    @PostMapping("/publish")
    public CommonResult publishHome(@RequestBody PublishPojo params) {
        log.info("PublishPojo -----> [{}]", params);
        return homeService.publishHome(params);
    }

    /**
     * 分页查询我的房源
     * @param pageNum
     * @param pageSize
     * @param hostId
     * @return
     */
    @GetMapping("/myhome")
    public CommonResult<CommonPage<MyHomePojo>> selectMyHomeByHostId(@RequestParam(value = "currentPage", defaultValue = "1") Integer pageNum,
                                                                      @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize,
                                                                      @RequestParam("hostId") Integer hostId) {
        log.info("[{}]", hostId);
        return homeService.selectMyHomeByHostId(pageNum, pageSize, hostId);
    }

    //@GetMapping("/select")
    //public CommonResult<CommonPage<HomeData>> selectHome(@RequestParam(value = "page", defaultValue = "1") Integer pageNum,
    //                                                      @RequestParam(value = "pageSize", defaultValue = "12") Integer pageSize) {
    //    List<HomeData> homeDataList = homeService.getHomeData(pageNum, pageSize);
    //    return CommonResult.success(CommonPage.resultPage(homeDataList));
    //}
}
