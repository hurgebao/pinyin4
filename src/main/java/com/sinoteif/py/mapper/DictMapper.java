package com.sinoteif.py.mapper;

import com.sinoteif.py.vo.DictVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
public interface DictMapper {

	List<DictVO> selectAllConnectType();
	
}