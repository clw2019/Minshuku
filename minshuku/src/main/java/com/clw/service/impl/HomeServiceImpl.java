package com.clw.service.impl;

import com.clw.domain.Carousel;
import com.clw.domain.HomeDetail;
import com.clw.domain.Img;
import com.clw.mapper.CarouselMapper;
import com.clw.mapper.HomeDetailMapper;
import com.clw.mapper.ImgMapper;
import com.clw.pojo.*;
import com.clw.utils.CommonPage;
import com.clw.utils.CommonResult;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.clw.domain.Home;
import com.clw.mapper.HomeMapper;
import com.clw.service.HomeService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
@Service
public class HomeServiceImpl implements HomeService {

    @Resource
    private HomeMapper homeMapper;
    @Resource
    private HomeDetailMapper homeDetailMapper;
    @Resource
    private ImgMapper imgMapper;
    @Resource
    private CarouselMapper carouselMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return homeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Home record) {
        return homeMapper.insert(record);
    }

    @Override
    public int insertSelective(Home record) {
        return homeMapper.insertSelective(record);
    }

    @Override
    public Home selectByPrimaryKey(Integer id) {
        return homeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Home record) {
        return homeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Home record) {
        return homeMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<HomeData> getHomeData(Integer pageNum, Integer pageSize, String address) {
        PageHelper.startPage(pageNum, pageSize);
        return homeMapper.getHomeData(address);
    }

    @Override
    public List<HomeDetailData> getDetail(Integer id) {
        List<HomeDetailData> detailList = homeMapper.getDetail(id);
        //for (HomeDetailData list : detailList.)
        return detailList;
    }

    @Override
    public CommonResult<CommonPage<AdminHome>> getHome(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<AdminHome> adminHomeList = homeMapper.getHome();
        return adminHomeList == null ? CommonResult.fail(100, "房屋信息为空") : CommonResult.success(CommonPage.resultPage(adminHomeList));
    }

    @Override
    public CommonResult<CommonPage<AdminHome>> getHomeByLike(Integer pageNum, Integer pageSize, String homeName) {
        PageHelper.startPage(pageNum, pageSize);
        List<AdminHome> adminHomeList = homeMapper.getHomeByLike(homeName);
        return adminHomeList == null ? CommonResult.fail(100, "房屋信息为空") : CommonResult.success(CommonPage.resultPage(adminHomeList));
    }

    @Override
    public CommonResult updateHomeStatus(Integer homeId) {
        Integer result = homeMapper.updateHomeStatus(homeId);
        return result == 0 ? CommonResult.fail(100, "状态更新失败") : CommonResult.success(result);
    }

    @Override
    public CommonResult publishHome(PublishPojo params) {
        System.out.println("+++++++++++++++++++  " + params.getHomeId());
        Home home = new Home();
        HomeDetail homeDetail = new HomeDetail();
        Img img = new Img();
        //新增
        if (params.getHomeId() == null || params.getHomeId().equals("")) {
            //Home home = new Home();
            //HomeDetail homeDetail = new HomeDetail();
            //Img img = new Img();
            home.setHostId(params.getHostId());
            home.setTitle(params.getTitle());
            home.setDescribe(params.getDescribe());
            home.setPrice(params.getPrice());
            home.setAddress(params.getAddress() + params.getDetailAddress());
            home.setArea(params.getArea());
            home.setLivepeople(params.getLivepeople());
            //home.setCollection(0);
            home.setStatus(0);
            home.setCreateTime(new Date());
            home.setUpdateTime(new Date());
            int x = homeMapper.insertSelective(home);
            System.out.println("插入的主键ID为--->>>  " + home.getId());
            homeDetail.setHomeId(home.getId());
            homeDetail.setSuite(params.getSuite());
            homeDetail.setBedroom(params.getBedroom());
            homeDetail.setLivingroom(params.getLivingroom());
            homeDetail.setKitchen(params.getKitchen());
            homeDetail.setToilet(params.getToilet());
            int y = homeDetailMapper.insertSelective(homeDetail);
            String[] strings = params.getImg().split("\\,");
            int z = 0;
            for (String str : strings) {
                img.setHomeId(home.getId());
                img.setImgurl(str);
                z = imgMapper.insertSelective(img);
            }
            return x == 1 && y == 1 && z == 1 ? CommonResult.success("插入成功") : CommonResult.fail(100, "插入失败");
        }
        //修改
        //Home home = new Home();
        //HomeDetail homeDetail = new HomeDetail();
        //Img img = new Img();
        home.setId(params.getHomeId());
        home.setHostId(params.getHostId());
        home.setTitle(params.getTitle());
        home.setDescribe(params.getDescribe());
        home.setPrice(params.getPrice());
        //params.getDetailAddress() 为null时  北京市辖区东城区null
        home.setAddress(params.getAddress() + params.getDetailAddress() + "");
        home.setArea(params.getArea());
        home.setLivepeople(params.getLivepeople());
        //home.setCollection(0);
        home.setStatus(0);
        //home.setCreateTime(new Date());
        //home.setUpdateTime(new Date());
        int xx = homeMapper.updateByPrimaryKeySelective(home);
        System.out.println("插入的主键ID为--->>>  " + home.getId());
        homeDetail.setHomeId(home.getId());
        homeDetail.setSuite(params.getSuite());
        homeDetail.setBedroom(params.getBedroom());
        homeDetail.setLivingroom(params.getLivingroom());
        homeDetail.setKitchen(params.getKitchen());
        homeDetail.setToilet(params.getToilet());
        Integer yy = homeDetailMapper.updateByHomeId(homeDetail);
        Integer zz = imgMapper.deleteByHomeId(params.getHomeId());
        String[] strings = params.getImg().split("\\,");
        int i = 0;
        for (String str : strings) {
            img.setHomeId(home.getId());
            img.setImgurl(str);
            i = imgMapper.insertSelective(img);
        }
        return xx == 1 && yy == 1 && zz >= 1 && i == 1 ? CommonResult.success("更新成功") : CommonResult.fail(100, "更新失败");
    }

    @Override
    public CommonResult<CommonPage<MyHomePojo>> selectMyHomeByHostId(Integer pageNum, Integer pageSize, Integer hostId) {
        PageHelper.startPage(pageNum, pageSize);
        List<MyHomePojo> myHomePojoList = homeMapper.selectMyHomeByHostId(hostId);
        return myHomePojoList == null ? CommonResult.fail(100, "您还未发布房源") : CommonResult.success(CommonPage.resultPage(myHomePojoList));
    }

    @Override
    public CommonResult updateHomeFlag(Integer homeId, Integer flag, String imgUrl) {
        Integer result = homeMapper.updateHomeFlag(homeId, flag);
        if (result != 1) {
            return CommonResult.fail(100, "操作失败");
        }
        if (flag == 0) {
            // 加入轮播
            Carousel carousel = new Carousel();
            carousel.setHomeId(homeId);
            carousel.setLink("http://localhost:8080/detail/" + homeId);
            carousel.setImgurl(imgUrl);
            Integer a = carouselMapper.insertSelective(carousel);
            return a == 1 ? CommonResult.success(a) : CommonResult.fail(100, "加入轮播失败");
        }
        if (flag == 1) {
            // 取消轮播
            Integer b = carouselMapper.deleteByHomeId(homeId);
            return b == 1 ? CommonResult.success(b) : CommonResult.fail(100, "取消轮播失败");
        }
        return null;
    }
}

