package com.clw.service.impl;

import com.clw.pojo.DistrictPojo;
import com.clw.utils.TreeUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.clw.mapper.DistrictMapper;
import com.clw.domain.District;
import com.clw.service.DistrictService;

import java.util.ArrayList;
import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService{

    @Resource
    private TreeUtils treeUtils;

    @Resource
    private DistrictMapper districtMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return districtMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(District record) {
        return districtMapper.insert(record);
    }

    @Override
    public int insertSelective(District record) {
        return districtMapper.insertSelective(record);
    }

    @Override
    public District selectByPrimaryKey(Integer id) {
        return districtMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(District record) {
        return districtMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(District record) {
        return districtMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<DistrictPojo> selectDistrict() {
        //查询所有
        List<DistrictPojo> allMenu = districtMapper.selectDistrict();
        //System.out.println("allmenu");
        //根节点
        List<DistrictPojo> rootMenu = new ArrayList<>();
        for (DistrictPojo d : allMenu) {
            //System.out.println("...........");
            if (d.getPid() == 1) {
                rootMenu.add(d);
                //System.out.println("00000");
            }
        }
        for (DistrictPojo d : rootMenu) {
            List<DistrictPojo> childList = treeUtils.getChild(d.getId(), allMenu);
            d.setDistrictList(childList);
            //System.out.println("11111");
        }

        return rootMenu;

    }

    @Override
    public List<DistrictPojo> selectAllDistrict() {
        return districtMapper.selectAllDistrict();
    }

}
