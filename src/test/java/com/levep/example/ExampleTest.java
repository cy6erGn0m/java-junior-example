package com.levep.example;

import com.levelp.example.Example;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class ExampleTest {
    private Example example = new Example();
    private File temp;

    @Before
    public void prepare() throws Exception {
        example.setValue(777);
        temp = File.createTempFile("test", "test");
    }

    @After
    public void cleanup() {
        if (temp != null) {
            temp.delete();
        }
    }

    @Test
    public void testSum() {
        assertEquals(3, example.sum(1, 2));
    }

    @Test
    public void testSum2() {
        assertEquals(3, example.sum(1, 2));
    }

    @Test
    public void testSum3() {
        assertEquals(3, example.sum(1, 2));
    }
}
