package com.sinoteif.py.controller;

import com.sinoteif.py.mapper.DictMapper;
import com.sinoteif.py.vo.DictVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by admin on 2019/9/20.
 */
@RestController
public class DictController {
    @Autowired
    private DictMapper dictMapper;
    @GetMapping("dict")
    public List<DictVO> getAllDict(){
       return dictMapper.selectAllConnectType();
    }
}
