package com.istl.app.domain;

import static com.istl.app.domain.ChannelsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.istl.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ChannelsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Channels.class);
        Channels channels1 = getChannelsSample1();
        Channels channels2 = new Channels();
        assertThat(channels1).isNotEqualTo(channels2);

        channels2.setId(channels1.getId());
        assertThat(channels1).isEqualTo(channels2);

        channels2 = getChannelsSample2();
        assertThat(channels1).isNotEqualTo(channels2);
    }
}
