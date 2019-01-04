package com.joseph.api.service;

import com.joseph.api.dto.GoodsDTO;

import java.util.List;

public interface GoodsService {

    List<GoodsDTO> listGoods(GoodsDTO goodsDTO);

    Integer insertGoods(GoodsDTO goodsDTO);

    Integer updateGoods(GoodsDTO goodsDTO);

    Integer deleteGoods(GoodsDTO goodsDTO);
}
