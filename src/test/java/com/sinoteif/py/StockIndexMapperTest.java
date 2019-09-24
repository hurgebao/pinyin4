package com.sinoteif.py;

import com.alibaba.fastjson.JSON;
import com.sinoteif.py.mapper.StockIndexMapper;
import com.sinoteif.py.vo.StockIndex;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2019/9/23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StockIndexMapperTest {
    @Autowired
    private StockIndexMapper stockIndexMapper;
    @Test
    public void testInsertSelective(){
        StockIndex stockIndex=new StockIndex();
        stockIndex.setId(1);
        stockIndex.setStockNameFirstLetter("ZGSH");
        stockIndex.setStockCode("601288");
        stockIndex.setStockName("中国石化");
        int count=stockIndexMapper.insertSelective(stockIndex);
        System.out.println(stockIndex);
    }
    @Test
    public void testUpdateByPrimaryKeySelective(){
        StockIndex stockIndex=new StockIndex();
        stockIndex.setId(1);
        stockIndex.setStockNameFirstLetter("NYYH");
        stockIndex.setStockCode("601288");
        stockIndex.setStockName("农业银行");
        stockIndex.setCreateTime(new Date(System.currentTimeMillis()));
        stockIndex.setUpdateTime(new Date(System.currentTimeMillis()));
        int count=stockIndexMapper.updateByPrimaryKeySelective(stockIndex);
        System.out.println(stockIndex);
    }

    @Test
    public void testSelectAllStock(){
        List<StockIndex> list=stockIndexMapper.selectAllStock();
        System.out.println("~~~~~~~~~~~~~~"+ JSON.toJSONString(list));
    }
    @Test
    public void testSelectStockByPrefix(){
        List<Map<String,String>> list=stockIndexMapper.selectStockByPrefix("W");
        System.out.println("selectStockByPrefix"+JSON.toJSONString(list));
    }
    @Test
    public void testselectTotalNum(){
        int count=stockIndexMapper.selectTotalNum();
        System.out.println("++++++++++++++count="+count);
    }
}
