package com.istl.app.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class MessageTemplateTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static MessageTemplate getMessageTemplateSample1() {
        return new MessageTemplate()
            .id(1L)
            .messagetype("messagetype1")
            .description("description1")
            .messageenglish("messageenglish1")
            .messagesomali("messagesomali1");
    }

    public static MessageTemplate getMessageTemplateSample2() {
        return new MessageTemplate()
            .id(2L)
            .messagetype("messagetype2")
            .description("description2")
            .messageenglish("messageenglish2")
            .messagesomali("messagesomali2");
    }

    public static MessageTemplate getMessageTemplateRandomSampleGenerator() {
        return new MessageTemplate()
            .id(longCount.incrementAndGet())
            .messagetype(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString())
            .messageenglish(UUID.randomUUID().toString())
            .messagesomali(UUID.randomUUID().toString());
    }
}
