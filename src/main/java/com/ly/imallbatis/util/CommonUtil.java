package com.ly.imallbatis.util;

import com.ly.imallbatis.bo.PageCounter;

public class CommonUtil {

    public static PageCounter convertToPageParameter(Integer start, Integer count) {
        int pageNum = start / count;
        PageCounter pageCounter = PageCounter.builder().page(pageNum).count(count).build();
        return pageCounter;
    }
}
