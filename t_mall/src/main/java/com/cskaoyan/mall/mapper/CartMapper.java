package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Cart;
import com.cskaoyan.mall.bean.CartExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {
    long countByExample(CartExample example);

    int deleteByExample(CartExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    List<Cart> selectByExample(CartExample example);

    Cart selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Cart record, @Param("example") CartExample example);

    int updateByExample(@Param("record") Cart record, @Param("example") CartExample example);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

    int selectSumNumber(@Param("userId") Integer userId);

    int selectSumPrice(@Param("userId") Integer userId);

    List<Cart> selectByUserId(@Param("userId") Integer userId);

    int updateCheckById(@Param("id") Integer id, @Param("checked") Boolean checked);

    int selectHasCart(@Param("goodsId") Integer goodsId, @Param("productId") Integer productId, @Param("userId") Integer userId);


    int updateNumber(@Param("number") Integer number, @Param("id") Integer id);

    Cart selectCart(@Param("goodsId") Integer goodsId, @Param("productId") Integer productId, @Param("userId") Integer userId);

    Cart selectByIdAndProductId(@Param("productId") Integer productId, @Param("userId") int userId);

    Integer deleteCart(@Param("productId") Integer productId, @Param("userId") int userId);

    int insertAll(@Param("cart") Cart cart);

    List<Cart> selectByUserIdAndChecked(@Param("userId") Integer userId);

    int deleteGoodsSubmitted();

    List<Integer> selectGoodsIdById(@Param("cartId") int cartId);

    List<Cart> selectByCartId(@Param("cartId") Integer cartId);
}
