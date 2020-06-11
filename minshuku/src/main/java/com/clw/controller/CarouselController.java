package com.clw.controller;

import com.clw.domain.Carousel;
import com.clw.service.CarouselService;
import com.clw.utils.CommonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/carousel")
public class CarouselController {

    @Resource
    private CarouselService carouselService;

    /**
     * 轮播图接口
     * @return
     */
    @RequestMapping("/data")
    public CommonResult<List<Carousel>> getCarousel() {
        List<Carousel> carouselList = carouselService.getCarousel();
        return CommonResult.success(carouselList);
    }
}
