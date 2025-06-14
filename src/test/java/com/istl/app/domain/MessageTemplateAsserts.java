package com.istl.app.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.istl.app.domain.mobileapp.MessageTemplate;

public class MessageTemplateAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertMessageTemplateAllPropertiesEquals(MessageTemplate expected, MessageTemplate actual) {
        assertMessageTemplateAutoGeneratedPropertiesEquals(expected, actual);
        assertMessageTemplateAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertMessageTemplateAllUpdatablePropertiesEquals(MessageTemplate expected, MessageTemplate actual) {
        assertMessageTemplateUpdatableFieldsEquals(expected, actual);
        assertMessageTemplateUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertMessageTemplateAutoGeneratedPropertiesEquals(MessageTemplate expected, MessageTemplate actual) {
        assertThat(expected)
            .as("Verify MessageTemplate auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertMessageTemplateUpdatableFieldsEquals(MessageTemplate expected, MessageTemplate actual) {
        assertThat(expected)
            .as("Verify MessageTemplate relevant properties")
            .satisfies(e -> assertThat(e.getMessagetype()).as("check messagetype").isEqualTo(actual.getMessagetype()))
            .satisfies(e -> assertThat(e.getDescription()).as("check description").isEqualTo(actual.getDescription()))
            .satisfies(e -> assertThat(e.getMessageenglish()).as("check messageenglish").isEqualTo(actual.getMessageenglish()))
            .satisfies(e -> assertThat(e.getMessagesomali()).as("check messagesomali").isEqualTo(actual.getMessagesomali()))
            .satisfies(e -> assertThat(e.getCreatedon()).as("check createdon").isEqualTo(actual.getCreatedon()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertMessageTemplateUpdatableRelationshipsEquals(MessageTemplate expected, MessageTemplate actual) {
        // empty method
    }
}
