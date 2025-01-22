package com.istl.app.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ChannelsTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Channels getChannelsSample1() {
        return new Channels().id(1L).channel("channel1").description("description1").bin("bin1");
    }

    public static Channels getChannelsSample2() {
        return new Channels().id(2L).channel("channel2").description("description2").bin("bin2");
    }

    public static Channels getChannelsRandomSampleGenerator() {
        return new Channels()
            .id(longCount.incrementAndGet())
            .channel(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString())
            .bin(UUID.randomUUID().toString());
    }
}
