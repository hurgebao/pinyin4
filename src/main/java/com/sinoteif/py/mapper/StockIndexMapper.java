package com.sinoteif.py.mapper;

import com.sinoteif.py.vo.DictVO;
import com.sinoteif.py.vo.StockIndex;

import java.util.List;
import java.util.Map;

public interface StockIndexMapper {

	List<StockIndex> selectAllStock();
	int insertSelective(StockIndex stockIndex);
	int updateByPrimaryKeySelective(StockIndex stockIndex);
	List<Map<String,String>> selectStockByPrefix(String stockPreLetter);

	List<Map<String,String>> selectStockByStockCode(String stockCode);
}