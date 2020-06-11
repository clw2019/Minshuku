package com.clw.controller;

import com.clw.service.ImgService;
import com.clw.utils.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/5/5 14:06
 */
@Slf4j
@RestController
public class UploadController {

    @Resource
    private ImgService imgService;

    //@PostMapping("/upload")
    //public CommonResult upload(@RequestParam("file") MultipartHttpServletRequest request) {
    //    log.info("MultipartHttpServletRequest --- [{}]", request);
    //    return imgService.upload(request);
    //}

    @PostMapping("/upload1")
    public CommonResult upload1(@RequestParam("file") MultipartFile[] files) {
        log.info("MultipartHttpServletRequest --- [{}]", files);
        return imgService.upload1(files);
    }
}
