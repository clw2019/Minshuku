package com.clw.utils;

import com.clw.pojo.DistrictPojo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/4/24 9:57
 */
@Component
public class TreeUtils {
    public List<DistrictPojo> getChild(Integer id, List<DistrictPojo> districtList) {
        List<DistrictPojo> childList = new ArrayList<>();
        for (DistrictPojo district : districtList) {
            if (district.getPid().equals(id)) {
                childList.add(district);
            }
        }
        for (DistrictPojo district : childList) {
            district.setDistrictList(getChild(district.getId(), districtList));
        }

        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }
}
