package com.sinoteif.py.mapper;

import com.sinoteif.py.vo.DictVO;
import com.sinoteif.py.vo.StockIndex;

import java.util.List;

public interface StockIndexMapper {

	List<StockIndex> selectAllStock();
	int insertSelective(StockIndex stockIndex);
	int updateByPrimaryKeySelective(StockIndex stockIndex);
	List<StockIndex> selectStockByPrefix(String stockPreLetter);
	
}