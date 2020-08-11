package com.ly.imallbatis.api.v1;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.imallbatis.dao.SkuMapper;
import com.ly.imallbatis.model.Sku;
import com.ly.imallbatis.model.Spec;
import com.ly.imallbatis.model.Spu;
import com.ly.imallbatis.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SkuController {

    @Autowired
    private SkuService skuService;

    @RequestMapping("/sku")
    public List<Sku> getSkuListByIds(@RequestParam String ids) {
        String[] split = ids.split(",");
        List<Long> longList = Arrays.stream(split).map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
        List<Sku> skuList = skuService.getSkuListByIds(longList);
        skuList.stream().forEach(sku -> {
            List<Spec> specs = JSONObject.parseArray(sku.getSpecsTemp(), Spec.class);
            sku.setSpecs(specs);
        });
        return skuList;
    }
}
