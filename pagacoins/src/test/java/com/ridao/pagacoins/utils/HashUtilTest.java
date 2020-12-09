package com.ridao.pagacoins.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HashUtilTest {

    @BeforeEach
    public void setup () {
    }

    @Test
    public void encodeTest() {
        String expectedHash = "2W4J";
        Long id = 1L;
        assertEquals(expectedHash, HashIdUtil.encode(id));
    }

    @Test
    public void decodeTest() {
        Long expectedId = 1L;
        String hash = "2W4J";
        assertEquals(expectedId, HashIdUtil.decode(hash));
    }


}