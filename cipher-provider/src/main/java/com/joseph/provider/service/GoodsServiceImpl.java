package com.joseph.provider.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.joseph.api.dto.GoodsDTO;
import com.joseph.api.service.GoodsService;
import com.joseph.api.service.OrderService;
import com.joseph.provider.dao.mapper.GoodsMapper;
import com.joseph.provider.domain.GoodsDO;
import com.joseph.provider.redis.NullCacheObject;
import com.joseph.provider.redis.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private RedisClient redisClient;

    @Override
    public List<GoodsDTO> listGoods(GoodsDTO goodsDTO) {
        Object obj=redisClient.getObj("goodsList");
        if(obj==null){
            GoodsDO goodsDO=new GoodsDO();
            goodsDO.setGoodsName(goodsDTO.getGoodsName());
            obj=buildGoodsDtoList(goodsMapper.listGoods(goodsDO));
            if(obj == null){
                obj=new NullCacheObject();
            }
            redisClient.setObj("goodsList",obj);
        }

        if(obj instanceof NullCacheObject){
            return null;
        }
        return (List<GoodsDTO>) obj;
    }

    @Override
    public Integer insertGoods(GoodsDTO goodsDTO) {
        try{
            GoodsDO goodsDO=new GoodsDO();
            //goodsDO.setGoodsId(goodsDTO.getg);
            goodsDO.setGoodsName(goodsDTO.getGoodsName());
            goodsDO.setGoodsStockNum(goodsDTO.getGoodsStockNum());
            goodsMapper.insertGoods(goodsDO);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public Integer updateGoods(GoodsDTO goodsDTO) {
        try{
            GoodsDO goodsDO=new GoodsDO();
            goodsDO.setGoodsId(123);
            goodsDO.setGoodsName(goodsDTO.getGoodsName());
            goodsDO.setGoodsStockNum(goodsDTO.getGoodsStockNum());
            goodsMapper.updateGoods(goodsDO);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public Integer deleteGoods(GoodsDTO goodsDTO) {
        return null;
    }

    public List<GoodsDTO> buildGoodsDtoList(List<GoodsDO> goodsDOList){
        List<GoodsDTO> goodsDTOList=new ArrayList<GoodsDTO>();
        if(goodsDOList != null){
            for (GoodsDO goods: goodsDOList){
                GoodsDTO dto=new GoodsDTO();
                dto.setGoodsName(goods.getGoodsName());
                dto.setGoodsStockNum(goods.getGoodsStockNum());
                goodsDTOList.add(dto);
            }
        }
        return goodsDTOList;
    }
}
