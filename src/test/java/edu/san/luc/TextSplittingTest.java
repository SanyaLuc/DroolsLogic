package edu.san.luc;

import org.junit.Assert;

import java.util.Collections;
import java.util.HashSet;

/**
 * Created by sanya on 12.05.15.
 */
public class TextSplittingTest {

    @org.junit.Test
    public void testHashSet() throws Exception {
        HashSet<String> set1 = new HashSet<String>(Collections.singleton("map"));
        HashSet<String> set2 = new HashSet<String>(Collections.singleton("map1"));

        Assert.assertEquals(set1, set2);
    }

    @org.junit.Test
    public void testName() throws Exception {
        String[] result = parseFileToParams("abc[section_name_1]def[section_name_2]gji");
        Assert.assertEquals("abc", result[0]);
        Assert.assertEquals("def", result[1]);
        Assert.assertEquals("gji", result[2]);
    }

    public static String[] parseFileToParams(String decoded) {
        String[] sections = {"[section_name_1]", "[section_name_2]"};
        String[] params = new String[sections.length + 1];
        int sectionStart = 0;
        for (int i = 0; i < sections.length; i++) {
            int sectionEnd = decoded.indexOf(sections[i], sectionStart);
            params[i] = decoded.substring(sectionStart, sectionEnd);
            sectionStart = sectionEnd + sections[i].length();
        }
        params[sections.length] = decoded.substring(sectionStart, decoded.length());
        return params;
    }
}
