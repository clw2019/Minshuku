package com.clw.service.impl;

import com.clw.utils.CommonResult;
import com.clw.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.clw.mapper.ImgMapper;
import com.clw.domain.Img;
import com.clw.service.ImgService;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

@Slf4j
@Service
public class ImgServiceImpl implements ImgService{
    @Value("${upload.upload-folder}")
    private String uploadFolder;

    @Resource
    private ImgMapper imgMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return imgMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Img record) {
        return imgMapper.insert(record);
    }

    @Override
    public int insertSelective(Img record) {
        return imgMapper.insertSelective(record);
    }

    @Override
    public Img selectByPrimaryKey(Integer id) {
        return imgMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Img record) {
        return imgMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Img record) {
        return imgMapper.updateByPrimaryKey(record);
    }

    @Override
    public CommonResult upload(MultipartHttpServletRequest request) {
        log.info("uploadService is loading....");
            Iterator<String> fileNames = request.getFileNames();
            while (fileNames.hasNext()) {
                String fileName = fileNames.next();
                System.out.println("fileName =......... " + fileName); //elementUI里为file
                //获取文件的名字
                MultipartFile multipartFile = request.getFile(fileName);
                String originalFilename = multipartFile.getOriginalFilename();
                System.out.println("originalFilename == > " + originalFilename);
                //获取文件的后缀名
                String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
                System.out.println("suffix ==> " + suffix);
                //拼接新的文件名，避免重复覆盖
                String newFileName = UUIDUtil.getUUID() + suffix;
                System.out.println("newFileName -- " + newFileName);
                //String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
                //System.out.println("path ==> " + path);
                ////String realPath = path + "static/upload/img";
                //String realPath = "E:/学习/Vue/后端/minshuku/src/main/resources/static/upload/img/";
                //94f2d431144044fabbbbbc43d40c1309.jpg
                File file = new File(uploadFolder + newFileName);
                if(!file.getParentFile().exists()){
                    file.getParentFile().mkdirs();//创建父级文件路径
                    try {
                        file.createNewFile();//创建文件
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(file.exists());
                }

                //上传
                try {
                    multipartFile.transferTo(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String imgUrl = "http://localhost:8000/minshuku/img/bb7c62dbc57e4e198b0df088db30a3b7.jpg";
                String picUrl = "localhost:8000/minshuku/upload/img/" + newFileName;

                System.out.println(picUrl);
            }
            return null;
    }

    @Override
    public CommonResult upload1(MultipartFile[] files) {
        StringBuffer buffer = new StringBuffer();
        if (files !=null && files.length > 0) {
            for (MultipartFile multipartFile: files) {
                System.out.println("1 ===> " + multipartFile.getName());
                System.out.println("1 ===> " + multipartFile.getOriginalFilename());
                //获取文件的名字
                String originalFilename = multipartFile.getOriginalFilename();
                //获取文件名的后缀
                String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
                //生成新的文件名
                String newFileName = UUIDUtil.getUUID() + suffix;
                System.out.println("newFileName -- " + newFileName);
                //创建文件夹
                File file = new File(uploadFolder + newFileName);
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                //上传
                try {
                    multipartFile.transferTo(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String picUrl = "http://localhost:8000/minshuku/img/" + newFileName;
                System.out.println(picUrl);
                buffer.append(picUrl + ",");
                System.out.println("buffer   " + buffer);
            }
            buffer.deleteCharAt(buffer.length() - 1);
            System.out.println("buffer   " + buffer);
        }
        return CommonResult.success(buffer);
    }
}
