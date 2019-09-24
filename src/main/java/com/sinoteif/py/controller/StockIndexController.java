package com.sinoteif.py.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sinoteif.py.mapper.StockIndexMapper;
import com.sinoteif.py.utils.Pinyin4JUtils;
import com.sinoteif.py.utils.PinyinUtils;
import com.sinoteif.py.vo.StockIndex;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by admin on 2019/9/23.
 */
@RestController
public class StockIndexController {
    public static final Map<String,String> STOCK_EXCHANGE;
    private static Logger logger= LoggerFactory.getLogger(StockIndexController.class);
    private static final Pattern NUM_PATTERN=Pattern.compile("[0-9]{0,6}");
    private static final Pattern CHAR_PATTERN=Pattern.compile("[a-z]+|[A-Z]+");
    private String inited="0";
    static {
        STOCK_EXCHANGE = new HashMap<String,String>();
        STOCK_EXCHANGE.put("600", "sh");
        STOCK_EXCHANGE.put("601", "sh");
        STOCK_EXCHANGE.put("603", "sh");

        STOCK_EXCHANGE.put("000", "sz");
        STOCK_EXCHANGE.put("001", "sz");
        STOCK_EXCHANGE.put("002", "sz");
        STOCK_EXCHANGE.put("300", "sz");
    }
    @Resource
    private RedisTemplate redisTemplate;
    @Autowired
    private StockIndexMapper stockIndexMapper;

    @GetMapping("/stockIndex/{stockPreLetter}")
    public List<Map<String,String>> l2(@PathVariable("stockPreLetter") String stockPreLetter){
        logger.debug("stockPreLetter{}", stockPreLetter);
        Matcher numMatcher=NUM_PATTERN.matcher(stockPreLetter);
        Matcher charMatcher=CHAR_PATTERN.matcher(stockPreLetter);

        if(!numMatcher.find() && !charMatcher.find()){
            return new ArrayList<Map<String,String>>();
        }
        List<Map<String,String>> list=null;
        if(numMatcher.find()){
            list= stockIndexMapper.selectStockByStockCode(stockPreLetter);
        }
        if(charMatcher.find()){
            list= stockIndexMapper.selectStockByPrefix(stockPreLetter);
        }
        logger.debug("stockList{}", JSON.toJSONString(list));
        return list;
    }
    @GetMapping("/stockIndex/initAll")
    public Object initAll()  {
        if("1".equals(inited)){
            return "stock index is initing,please try again later ";
        }
        Integer count= stockIndexMapper.selectTotalNum();
        if(count>0){
            logger.info("stock index was inited,please clean and retry again");
            return "stock index was inited,please clean and retry again";
        }
        inited="1";
        logger.info("begin init stock index --------");
        Long startTime=System.currentTimeMillis();
        Integer i=0;
        try {
            i= initdb();
            Long endTime=System.currentTimeMillis();
            String msg= "init " +i+" records,and cost "+(endTime-startTime)+" ms";
            logger.info("end init stock index: "+msg);
            return msg;
        } catch (Exception e) {
            logger.error("init exception {}",e);
            return "init exception "+e.getMessage();
        }finally {
            inited="0";
        }
    }

    private Integer initdb() throws BadHanyuPinyinOutputFormatCombination {
        Integer i=0;
        Set<String> set=redisTemplate.keys("*");
        for(String stockCode:set){
            if(null != stockCode && stockCode.length() == 6) {
                String stockType = STOCK_EXCHANGE.get(stockCode.substring(0, 3));
                if(null == stockType) {
                    continue;
                }
            }
            Object stockInfo= redisTemplate.opsForValue().get(stockCode);
            if (stockInfo!=null){
                JSONObject jsonObject=  JSON.parseObject(stockInfo.toString());
                String stock=jsonObject.getString("code");
                String stockName=jsonObject.getString("chName");
                String stockNameFL= PinyinUtils.getAlpha2(stockName);
                String[] names=stockNameFL.split(",");
                for(String name:names){
                    StockIndex stockIndex=new StockIndex();
                    stockIndex.setStockCode(stock);
                    stockIndex.setStockName(stockName);
                    stockIndex.setStockNameFirstLetter(name);
                    stockIndexMapper.insertSelective(stockIndex);
                    i++;
                }
//                System.out.println(stockName+","+stockNameFL);

            }
        }
        return  i;
    }
    @GetMapping("/stockIndex/update")
    public Object update(){
        if("1".equals(inited)){
            return "stock index is updating,please try again later ";
        }
        inited="1";
        logger.info("begin update stock index --------");
        Long startTime=System.currentTimeMillis();
        try{
            List<StockIndex> list=stockIndexMapper.selectAllStock();
            int i=0;
            if(list.size()<=0){
                i= this.initdb();
            }else{
                Set<String> set=new HashSet<String>();
                for(StockIndex stockIndex:list){
                    set.add(stockIndex.getStockCode() + stockIndex.getStockNameFirstLetter());
                }
                i= this.updatedb(set);
            }
            Long endTime=System.currentTimeMillis();
            String msg= "update " +i+" records,and cost "+(endTime-startTime)+" ms";
            logger.info("end update stock index: "+msg);
            return msg;
        } catch (Exception e) {
            logger.error("update exception "+e.getMessage());
            return "update exception "+e.getMessage();
        }finally {
            inited="0";
        }
    }
    private Integer updatedb(Set<String> stockIndexSet) throws BadHanyuPinyinOutputFormatCombination {
        Integer i=0;
        Set<String> tmpSet=redisTemplate.keys("*");
        for(String stockCode:tmpSet){
            if(null != stockCode && stockCode.length() == 6) {
                String stockType = STOCK_EXCHANGE.get(stockCode.substring(0, 3));
                if(null == stockType) {
                    continue;
                }
            }
            Object stockInfo= redisTemplate.opsForValue().get(stockCode);
            if (stockInfo!=null){
                JSONObject jsonObject=  JSON.parseObject(stockInfo.toString());
                String stock=jsonObject.getString("code");
                String stockName=jsonObject.getString("chName");
                String stockNameFL= PinyinUtils.getAlpha2(stockName);
                String[] names=stockNameFL.split(",");
                for(String name:names){
                    StockIndex stockIndex=new StockIndex();
                    stockIndex.setStockCode(stock);
                    stockIndex.setStockName(stockName);
                    stockIndex.setStockNameFirstLetter(name);
                    if(!stockIndexSet.contains(stock+name)){
                        stockIndexMapper.insertSelective(stockIndex);
                        i++;
                    }
                }
            }
        }
        return  i;
    }
}
