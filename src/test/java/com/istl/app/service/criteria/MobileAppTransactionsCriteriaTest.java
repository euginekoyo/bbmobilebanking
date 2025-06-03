package com.istl.app.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class MobileAppTransactionsCriteriaTest {

    @Test
    void newMobileAppTransactionsCriteriaHasAllFiltersNullTest() {
        var mobileAppTransactionsCriteria = new MobileAppTransactionsCriteria();
        assertThat(mobileAppTransactionsCriteria).is(criteriaFiltersAre(Objects::isNull));
    }

    @Test
    void mobileAppTransactionsCriteriaFluentMethodsCreatesFiltersTest() {
        var mobileAppTransactionsCriteria = new MobileAppTransactionsCriteria();

        setAllFilters(mobileAppTransactionsCriteria);

        assertThat(mobileAppTransactionsCriteria).is(criteriaFiltersAre(Objects::nonNull));
    }

    @Test
    void mobileAppTransactionsCriteriaCopyCreatesNullFilterTest() {
        var mobileAppTransactionsCriteria = new MobileAppTransactionsCriteria();
        var copy = mobileAppTransactionsCriteria.copy();

        assertThat(mobileAppTransactionsCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(Objects::isNull)),
            criteria -> assertThat(criteria).isEqualTo(mobileAppTransactionsCriteria)
        );
    }

    @Test
    void mobileAppTransactionsCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var mobileAppTransactionsCriteria = new MobileAppTransactionsCriteria();
        setAllFilters(mobileAppTransactionsCriteria);

        var copy = mobileAppTransactionsCriteria.copy();

        assertThat(mobileAppTransactionsCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(Objects::nonNull)),
            criteria -> assertThat(criteria).isEqualTo(mobileAppTransactionsCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var mobileAppTransactionsCriteria = new MobileAppTransactionsCriteria();

        assertThat(mobileAppTransactionsCriteria).hasToString("MobileAppTransactionsCriteria{}");
    }

    private static void setAllFilters(MobileAppTransactionsCriteria mobileAppTransactionsCriteria) {
        mobileAppTransactionsCriteria.id();
        mobileAppTransactionsCriteria.channel();
        mobileAppTransactionsCriteria.channelIp();
        mobileAppTransactionsCriteria.channelReference();
        mobileAppTransactionsCriteria.channelTimestamp();
        mobileAppTransactionsCriteria.clientId();
        mobileAppTransactionsCriteria.createdAt();
        mobileAppTransactionsCriteria.debitAccount();
        mobileAppTransactionsCriteria.direction();
        mobileAppTransactionsCriteria.errorDescription();
        mobileAppTransactionsCriteria.geolocation();
        mobileAppTransactionsCriteria.hostCode();
        mobileAppTransactionsCriteria.phoneNumber();
        mobileAppTransactionsCriteria.responseCode();
        mobileAppTransactionsCriteria.responseMessage();
        mobileAppTransactionsCriteria.transactionCode();
        mobileAppTransactionsCriteria.transactionType();
        mobileAppTransactionsCriteria.userAgent();
        mobileAppTransactionsCriteria.userAgentVersion();
        mobileAppTransactionsCriteria.amount();
        mobileAppTransactionsCriteria.chargeamount();
        mobileAppTransactionsCriteria.creditRccount();
        mobileAppTransactionsCriteria.cbsReference();
        mobileAppTransactionsCriteria.distinct();
    }

    private static Condition<MobileAppTransactionsCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId()) &&
                condition.apply(criteria.getChannel()) &&
                condition.apply(criteria.getChannelIp()) &&
                condition.apply(criteria.getChannelReference()) &&
                condition.apply(criteria.getChannelTimestamp()) &&
                condition.apply(criteria.getClientId()) &&
                condition.apply(criteria.getCreatedAt()) &&
                condition.apply(criteria.getDebitAccount()) &&
                condition.apply(criteria.getDirection()) &&
                condition.apply(criteria.getErrorDescription()) &&
                condition.apply(criteria.getGeolocation()) &&
                condition.apply(criteria.getHostCode()) &&
                condition.apply(criteria.getPhoneNumber()) &&
                condition.apply(criteria.getResponseCode()) &&
                condition.apply(criteria.getResponseMessage()) &&
                condition.apply(criteria.getTransactionCode()) &&
                condition.apply(criteria.getTransactionType()) &&
                condition.apply(criteria.getUserAgent()) &&
                condition.apply(criteria.getUserAgentVersion()) &&
                condition.apply(criteria.getAmount()) &&
                condition.apply(criteria.getChargeamount()) &&
                condition.apply(criteria.getCreditRccount()) &&
                condition.apply(criteria.getCbsReference()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<MobileAppTransactionsCriteria> copyFiltersAre(
        MobileAppTransactionsCriteria copy,
        BiFunction<Object, Object, Boolean> condition
    ) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId(), copy.getId()) &&
                condition.apply(criteria.getChannel(), copy.getChannel()) &&
                condition.apply(criteria.getChannelIp(), copy.getChannelIp()) &&
                condition.apply(criteria.getChannelReference(), copy.getChannelReference()) &&
                condition.apply(criteria.getChannelTimestamp(), copy.getChannelTimestamp()) &&
                condition.apply(criteria.getClientId(), copy.getClientId()) &&
                condition.apply(criteria.getCreatedAt(), copy.getCreatedAt()) &&
                condition.apply(criteria.getDebitAccount(), copy.getDebitAccount()) &&
                condition.apply(criteria.getDirection(), copy.getDirection()) &&
                condition.apply(criteria.getErrorDescription(), copy.getErrorDescription()) &&
                condition.apply(criteria.getGeolocation(), copy.getGeolocation()) &&
                condition.apply(criteria.getHostCode(), copy.getHostCode()) &&
                condition.apply(criteria.getPhoneNumber(), copy.getPhoneNumber()) &&
                condition.apply(criteria.getResponseCode(), copy.getResponseCode()) &&
                condition.apply(criteria.getResponseMessage(), copy.getResponseMessage()) &&
                condition.apply(criteria.getTransactionCode(), copy.getTransactionCode()) &&
                condition.apply(criteria.getTransactionType(), copy.getTransactionType()) &&
                condition.apply(criteria.getUserAgent(), copy.getUserAgent()) &&
                condition.apply(criteria.getUserAgentVersion(), copy.getUserAgentVersion()) &&
                condition.apply(criteria.getAmount(), copy.getAmount()) &&
                condition.apply(criteria.getChargeamount(), copy.getChargeamount()) &&
                condition.apply(criteria.getCreditRccount(), copy.getCreditRccount()) &&
                condition.apply(criteria.getCbsReference(), copy.getCbsReference()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
