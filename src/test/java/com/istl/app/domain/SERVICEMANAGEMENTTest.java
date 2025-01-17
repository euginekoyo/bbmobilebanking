package com.istl.app.domain;

import static com.istl.app.domain.SERVICEMANAGEMENTTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.istl.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SERVICEMANAGEMENTTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SERVICEMANAGEMENT.class);
        SERVICEMANAGEMENT sERVICEMANAGEMENT1 = getSERVICEMANAGEMENTSample1();
        SERVICEMANAGEMENT sERVICEMANAGEMENT2 = new SERVICEMANAGEMENT();
        assertThat(sERVICEMANAGEMENT1).isNotEqualTo(sERVICEMANAGEMENT2);

        sERVICEMANAGEMENT2.setId(sERVICEMANAGEMENT1.getId());
        assertThat(sERVICEMANAGEMENT1).isEqualTo(sERVICEMANAGEMENT2);

        sERVICEMANAGEMENT2 = getSERVICEMANAGEMENTSample2();
        assertThat(sERVICEMANAGEMENT1).isNotEqualTo(sERVICEMANAGEMENT2);
    }
}
