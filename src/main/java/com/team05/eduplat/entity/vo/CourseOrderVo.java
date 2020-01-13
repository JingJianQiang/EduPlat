package com.team05.eduplat.entity.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @program: EduPlat
 * @description: TODO
 * @author: jian'jun'bin
 * @Date 2019-12-11 9:36
 **/
@Data
public class CourseOrderVo {
    private Long orderId;
    private Long courseId;
    @NotNull(message = "名字不能为空")
    private String name;
    private BigDecimal price;
    private String introduction;
    private String logoImg;
    private Long clicknum;
    @NotNull(message = "用户id不为空")
    private Long userId;
    private String type;
}
