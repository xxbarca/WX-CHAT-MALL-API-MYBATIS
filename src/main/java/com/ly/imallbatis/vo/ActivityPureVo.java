package com.ly.imallbatis.vo;

import com.ly.imallbatis.model.Activity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Setter
@Getter
public class ActivityPureVo {
    private Long id;
    private String title;
    private Date start_time;
    private Date end_time;
    private Boolean online;
    private String entrance_img;
    private String remark;

    public ActivityPureVo(Activity activity) {
        BeanUtils.copyProperties(activity, this);
    }
}
