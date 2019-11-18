package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.GoodsStatInfo;
import com.cskaoyan.mall.bean.OrderGoods;
import com.cskaoyan.mall.bean.OrderGoodsExample;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface OrderGoodsMapper {
    long countByExample(OrderGoodsExample example);

    int deleteByExample(OrderGoodsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderGoods record);

    int insertSelective(OrderGoods record);

    List<OrderGoods> selectByExample(OrderGoodsExample example);

    OrderGoods selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderGoods record, @Param("example") OrderGoodsExample example);

    int updateByExample(@Param("record") OrderGoods record, @Param("example") OrderGoodsExample example);

    int updateByPrimaryKeySelective(OrderGoods record);

    int updateByPrimaryKey(OrderGoods record);

<<<<<<< HEAD
    int queryOrderNumByAddTime(@Param("addTime") Date addTime);

    int queryGoodsNumByAddTime(@Param("addTime") Date addTime);

    List<OrderGoods> selectByOrderId(@Param("orderId")Integer orderId);
}
=======
    List<GoodsStatInfo> queryGoodsStatInfo();
}
>>>>>>> 67414d9ef85f2392070b11cf34e2c218440ea498
