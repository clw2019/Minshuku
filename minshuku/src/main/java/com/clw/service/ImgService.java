package com.clw.service;

import com.clw.domain.Img;
import com.clw.utils.CommonResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface ImgService{


    int deleteByPrimaryKey(Integer id);

    int insert(Img record);

    int insertSelective(Img record);

    Img selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Img record);

    int updateByPrimaryKey(Img record);

    CommonResult upload(MultipartHttpServletRequest request);

    CommonResult upload1(MultipartFile[] files);
}
