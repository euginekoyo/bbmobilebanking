package com.istl.app.domain;

import static com.istl.app.domain.MessagesSmsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.istl.app.domain.mobileapp.MessagesSms;
import com.istl.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class MessagesSmsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MessagesSms.class);
        MessagesSms messagesSms1 = getMessagesSmsSample1();
        MessagesSms messagesSms2 = new MessagesSms();
        assertThat(messagesSms1).isNotEqualTo(messagesSms2);

        messagesSms2.setId(messagesSms1.getId());
        assertThat(messagesSms1).isEqualTo(messagesSms2);

        messagesSms2 = getMessagesSmsSample2();
        assertThat(messagesSms1).isNotEqualTo(messagesSms2);
    }
}
