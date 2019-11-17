package com.cskaoyan.mall;

import com.cskaoyan.mall.bean.GoodsProduct;
import com.cskaoyan.mall.mapper.GoodsProductMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MallApplicationTests {

    @Autowired
    GoodsProductMapper goodsProductMapper;

    @Test
    void contextLoads() {
        List<GoodsProduct> products = goodsProductMapper.selectByGoodsId(1006013);
        int i = 0;
    }

}
