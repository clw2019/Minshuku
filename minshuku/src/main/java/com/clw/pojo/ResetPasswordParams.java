package com.clw.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/5/16 17:26
 */
@Getter
@Setter
@ToString
public class ResetPasswordParams {
        List<Integer> userIdList;
}
