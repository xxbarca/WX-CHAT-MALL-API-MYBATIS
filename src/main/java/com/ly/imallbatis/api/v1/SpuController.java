package com.ly.imallbatis.api.v1;

import com.github.pagehelper.PageInfo;
import com.ly.imallbatis.bo.PageCounter;
import com.ly.imallbatis.exception.http.NotFoundException;
import com.ly.imallbatis.model.Spu;
import com.ly.imallbatis.service.SpuService;
import com.ly.imallbatis.util.CommonUtil;
import com.ly.imallbatis.vo.SpuSimplifyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/spu")
public class SpuController {

    @Autowired
    private SpuService spuService;

    /**
     * spu详情数据查询
     * */
    @GetMapping("/id/{id}/detail")
    public Spu getDetail(@PathVariable @Positive Long id) {
        Spu spu = spuService.getSpu(id);
        if (spu == null) {
            throw new NotFoundException(30003);
        }
        return spu;
    }


    /**
     * spu列表数据, 瀑布流
     * */
    @GetMapping("/latest")
    public PageInfo<SpuSimplifyVO> getLatestSpuList(@RequestParam(defaultValue = "0") Integer start,
                                          @RequestParam(defaultValue = "10") Integer count) {

        PageCounter pageCounter = CommonUtil.convertToPageParameter(start, count);
        PageInfo<SpuSimplifyVO> pageInfo = spuService.getLatestPagingSpu(pageCounter.getPage(), pageCounter.getCount());
        return pageInfo;
    }

    // TODO 没搞明白, 需要数据
    @GetMapping("/by/category/{id}")
    public PageInfo<Spu> getByCategoryId(@PathVariable @Positive(message = "{id.positive}") Long id,
                                         @RequestParam(name = "is_root", defaultValue = "false") Boolean isRoot,
                                         @RequestParam(name = "start", defaultValue = "0") Integer start,
                                         @RequestParam(name = "count", defaultValue = "10") Integer count) {
        PageCounter pageCounter = CommonUtil.convertToPageParameter(start, count);
        PageInfo<Spu> pageInfo = spuService.getByCategory(id, isRoot, pageCounter.getPage(), pageCounter.getCount());
        return pageInfo;
    }


}
