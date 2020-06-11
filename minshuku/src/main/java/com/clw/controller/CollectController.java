package com.clw.controller;

import com.clw.domain.Collect;
import com.clw.pojo.CollectPojo;
import com.clw.service.CollectService;
import com.clw.utils.CommonPage;
import com.clw.utils.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/4/24 20:12
 */
@Slf4j
@RestController
@RequestMapping("/collect")
public class CollectController {

    @Resource
    private CollectService collectService;

    /**
     * 根据homeId，userId 判断当前房屋是否被当前用户收藏
     * 若收藏，点击则取消，若未收藏，则收藏
     *
     * @param homeId
     * @param userId
     * @return
     */
    @RequestMapping("/insertOrDelete")
    public CommonResult collect(@RequestParam("homeId") Integer homeId, @RequestParam("userId") Integer userId) {
        log.info("homeId : [{}]", homeId);
        log.info("userId : [{}]", userId);
        CommonResult result = collectService.collect(homeId, userId);
        return result;
    }

    /**
     * 根据homeId跟新数据库的收藏数量
     *
     * @param homeId
     * @return
     */
    @RequestMapping("/collection")
    public CommonResult updateCollection(@RequestParam("homeId") Integer homeId, @RequestParam("num") Integer num) {
        log.info("homeId : [{}]", homeId);
        log.info("num : [{}]", num);
        CommonResult result = collectService.updateCollection(homeId, num);
        return result;
    }

    /**
     * 根据userId查找收藏内容
     * @param pageNum
     * @param pageSize
     * @param userId
     * @return
     */
    @RequestMapping("/myCollect")
    public CommonResult<CommonPage<CollectPojo>> selectCollectByUserId(@RequestParam(value = "currentPage", defaultValue = "1") Integer pageNum,
                                                                   @RequestParam(value = "pageSize", defaultValue = "2") Integer pageSize,
                                                                   @RequestParam("userId") Integer userId) {
        log.info("[{}]", userId);
        return collectService.selectCollectByUserId(pageNum, pageSize, userId);
    }
}
