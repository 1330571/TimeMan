package com.xys.timemgr.utils;

import com.xys.timemgr.provider.StringAble;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DataSource implements StringAble {
    @Override
    public String[] getStringArrays() {
        return new String[]{"AB", "CD", "EF"};
    }
}

class DataConvertTest {


    @Test
    void concatString() {
        DataSource dataSource = new DataSource();
        String result = DataConvert.concatString(dataSource);
        assertEquals("AB_CD_EF", result);
    }

    @Test
    void splitString() {
        String[] result = DataConvert.splitString("AB_CD_EF_");
        String[] answer = {"AB", "CD", "EF"};
        assertArrayEquals(answer, result);
    }

    @Test
    void parseAttributeAndValue() {
        String testStr = "name_xys_power_25_def_20";
        HashMap<String, String> hashMap = new HashMap<>() {{
            put("name", "xys");
            put("power", "25");
            put("def", "20");
        }};
        assertEquals(hashMap, DataConvert.getStringPair(testStr));
    }
}
