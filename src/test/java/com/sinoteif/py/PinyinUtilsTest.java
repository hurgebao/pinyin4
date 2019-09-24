package com.sinoteif.py;

import com.sinoteif.py.utils.PinyinUtils;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
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
public class PinyinUtilsTest {
    @Test
    public void testgetAlpha() throws BadHanyuPinyinOutputFormatCombination {
        String s1 = PinyinUtils.getAlpha("中国石化");
        String s2 = PinyinUtils.getAlpha("多喜爱");
        String s3 = PinyinUtils.getAlpha("万科A");
        Assert.assertEquals("首拼音字母错误-s1","ZGSH",s1);
        Assert.assertEquals("首拼音字母错误-s2","DXA",s2);
        Assert.assertEquals("首拼音字母错误-s3","WKA",s3);

    }
    @Test
    public void testgetPingYin(){
        String s1 = PinyinUtils.getPingYin("重庆石化");
        String s2 = PinyinUtils.getPingYin("多喜爱");
        String s3 = PinyinUtils.getPingYin("万科a");
        System.out.println("getPingYin:s1 "+s1);
        System.out.println("getPingYin:s2 "+s2);
        System.out.println("getPingYin:s3 "+s3);

    }
    @Test
    public void testconverterToFirstSpell(){
        String s1 = PinyinUtils.converterToFirstSpell("重庆石化");
        String s2 = PinyinUtils.converterToFirstSpell("多喜爱");
        String s3 = PinyinUtils.converterToFirstSpell("万科a");
        System.out.println("converterToFirstSpell s1:"+s1);
        System.out.println("converterToFirstSpell s2:"+s2);
        System.out.println("converterToFirstSpell s3:"+s3);
    }
}