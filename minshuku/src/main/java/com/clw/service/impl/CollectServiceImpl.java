package com.clw.service.impl;

import com.clw.pojo.CollectPojo;
import com.clw.utils.CommonPage;
import com.clw.utils.CommonResult;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.clw.domain.Collect;
import com.clw.mapper.CollectMapper;
import com.clw.service.CollectService;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class CollectServiceImpl implements CollectService {

    @Resource
    private CollectMapper collectMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return collectMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Collect record) {
        return collectMapper.insert(record);
    }

    @Override
    public int insertSelective(Collect record) {
        return collectMapper.insertSelective(record);
    }

    @Override
    public Collect selectByPrimaryKey(Integer id) {
        return collectMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Collect record) {
        return collectMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Collect record) {
        return collectMapper.updateByPrimaryKey(record);
    }

    @Override
    public CommonResult collect(Integer homeId, Integer userId) {
        Collect collect = collectMapper.selectByHomeIdAndUserId(homeId, userId);
        log.info("[{}]....", collect);
        //当前房屋是否被当前用户收藏
        if (collect == null) {
            //未收藏，点击则收藏
            Collect collect1 = new Collect();
            collect1.setId(null);
            collect1.setHomeId(homeId);
            collect1.setUserId(userId);
            collect1.setCreateTime(new Date());
            collect1.setUpdateTime(new Date());
            int i = collectMapper.insert(collect1);
            return CommonResult.success(i);

        }
        //已收藏，点击则取消收藏
        int i = collectMapper.deleteByPrimaryKey(collect.getId());
        return CommonResult.success(i);
        //return CommonResult.fail(100, "该房屋已被收藏");
    }

    @Override
    public CommonResult updateCollection(Integer homeId, Integer num) {
        Integer i = collectMapper.updateCollection(homeId, num);
        return CommonResult.success(i);
    }

    @Override
    public CommonResult<CommonPage<CollectPojo>> selectCollectByUserId(Integer pageNum, Integer pageSize, Integer userId) {
        PageHelper.startPage(pageNum, pageSize);
        List<CollectPojo> collectPojoList = collectMapper.selectCollectByUserId(userId);
        return collectPojoList == null ? CommonResult.fail(100, "暂无数据") : CommonResult.success(CommonPage.resultPage(collectPojoList));
    }
}


