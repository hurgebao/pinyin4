package com.sinoteif.py;

import com.sinoteif.py.utils.Pinyin4JUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by admin on 2019/9/19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Pinyin4JUtilsTest {
    @Test
    public void testconverterToFirstSpell(){
        String s1 = Pinyin4JUtils.converterToFirstSpell("重庆石化");
        String s2 = Pinyin4JUtils.converterToFirstSpell("多喜爱");
        String s3 = Pinyin4JUtils.converterToFirstSpell("万科a");
        System.out.println("converterToFirstSpell:s1 "+s1);
        System.out.println("converterToFirstSpell:s2 "+s2);
        System.out.println("converterToFirstSpell:s3 "+s3);

    }
    @Test
    public void testconverterToSpell(){
        String s1 = Pinyin4JUtils.converterToSpell("重庆石化");
        String s2 = Pinyin4JUtils.converterToSpell("多喜爱");
        String s3 = Pinyin4JUtils.converterToSpell("万科a");
        System.out.println("converterToSpell s1:"+s1);
        System.out.println("converterToSpell s2:"+s2);
        System.out.println("converterToSpell s3:"+s3);
    }
}
