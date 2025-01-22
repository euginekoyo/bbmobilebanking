package com.istl.app.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class MessagesSmsTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static MessagesSms getMessagesSmsSample1() {
        return new MessagesSms()
            .id(1L)
            .phonenumber("phonenumber1")
            .transactionno("transactionno1")
            .accountnumber("accountnumber1")
            .message("message1")
            .channel("channel1")
            .trials(1L)
            .priority(1L)
            .responsecode("responsecode1")
            .responsemsg("responsemsg1")
            .sent(1L)
            .delivered(1L)
            .txntype("txntype1")
            .errorexception(1L)
            .datesent("datesent1")
            .rtpsreqtime("rtpsreqtime1")
            .fxgenerated("fxgenerated1")
            .taxprocessed(1L)
            .batchnumber("batchnumber1")
            .batchnumbertax("batchnumbertax1")
            .responsetime("responsetime1")
            .pduseqid("pduseqid1")
            .remarks("remarks1")
            .resendby("resendby1");
    }

    public static MessagesSms getMessagesSmsSample2() {
        return new MessagesSms()
            .id(2L)
            .phonenumber("phonenumber2")
            .transactionno("transactionno2")
            .accountnumber("accountnumber2")
            .message("message2")
            .channel("channel2")
            .trials(2L)
            .priority(2L)
            .responsecode("responsecode2")
            .responsemsg("responsemsg2")
            .sent(2L)
            .delivered(2L)
            .txntype("txntype2")
            .errorexception(2L)
            .datesent("datesent2")
            .rtpsreqtime("rtpsreqtime2")
            .fxgenerated("fxgenerated2")
            .taxprocessed(2L)
            .batchnumber("batchnumber2")
            .batchnumbertax("batchnumbertax2")
            .responsetime("responsetime2")
            .pduseqid("pduseqid2")
            .remarks("remarks2")
            .resendby("resendby2");
    }

    public static MessagesSms getMessagesSmsRandomSampleGenerator() {
        return new MessagesSms()
            .id(longCount.incrementAndGet())
            .phonenumber(UUID.randomUUID().toString())
            .transactionno(UUID.randomUUID().toString())
            .accountnumber(UUID.randomUUID().toString())
            .message(UUID.randomUUID().toString())
            .channel(UUID.randomUUID().toString())
            .trials(longCount.incrementAndGet())
            .priority(longCount.incrementAndGet())
            .responsecode(UUID.randomUUID().toString())
            .responsemsg(UUID.randomUUID().toString())
            .sent(longCount.incrementAndGet())
            .delivered(longCount.incrementAndGet())
            .txntype(UUID.randomUUID().toString())
            .errorexception(longCount.incrementAndGet())
            .datesent(UUID.randomUUID().toString())
            .rtpsreqtime(UUID.randomUUID().toString())
            .fxgenerated(UUID.randomUUID().toString())
            .taxprocessed(longCount.incrementAndGet())
            .batchnumber(UUID.randomUUID().toString())
            .batchnumbertax(UUID.randomUUID().toString())
            .responsetime(UUID.randomUUID().toString())
            .pduseqid(UUID.randomUUID().toString())
            .remarks(UUID.randomUUID().toString())
            .resendby(UUID.randomUUID().toString());
    }
}
