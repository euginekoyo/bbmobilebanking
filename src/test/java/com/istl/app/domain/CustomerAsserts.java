package com.istl.app.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.istl.app.domain.mobileapp.Customer;

public class CustomerAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCustomerAllPropertiesEquals(Customer expected, Customer actual) {
        assertCustomerAutoGeneratedPropertiesEquals(expected, actual);
        assertCustomerAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCustomerAllUpdatablePropertiesEquals(Customer expected, Customer actual) {
        assertCustomerUpdatableFieldsEquals(expected, actual);
        assertCustomerUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCustomerAutoGeneratedPropertiesEquals(Customer expected, Customer actual) {
        assertThat(expected)
            .as("Verify Customer auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCustomerUpdatableFieldsEquals(Customer expected, Customer actual) {
        assertThat(expected)
            .as("Verify Customer relevant properties")
            .satisfies(e -> assertThat(e.getCustomername()).as("check customername").isEqualTo(actual.getCustomername()))
            .satisfies(e -> assertThat(e.getPhonenumber()).as("check phonenumber").isEqualTo(actual.getPhonenumber()))
            .satisfies(e -> assertThat(e.getCardnumber()).as("check cardnumber").isEqualTo(actual.getCardnumber()))
            .satisfies(e -> assertThat(e.getAccountnumber()).as("check accountnumber").isEqualTo(actual.getAccountnumber()))
            .satisfies(e -> assertThat(e.getLang()).as("check lang").isEqualTo(actual.getLang()))
            .satisfies(e -> assertThat(e.getPin()).as("check pin").isEqualTo(actual.getPin()))
            .satisfies(e -> assertThat(e.getFirstlogin()).as("check firstlogin").isEqualTo(actual.getFirstlogin()))
            .satisfies(e -> assertThat(e.getActive()).as("check active").isEqualTo(actual.getActive()))
            .satisfies(e -> assertThat(e.getRegistered()).as("check registered").isEqualTo(actual.getRegistered()))
            .satisfies(e -> assertThat(e.getCstdelete()).as("check cstdelete").isEqualTo(actual.getCstdelete()))
            .satisfies(e -> assertThat(e.getRegdate()).as("check regdate").isEqualTo(actual.getRegdate()))
            .satisfies(e -> assertThat(e.getAlertenabled()).as("check alertenabled").isEqualTo(actual.getAlertenabled()))
            .satisfies(e -> assertThat(e.getRemark()).as("check remark").isEqualTo(actual.getRemark()))
            .satisfies(e -> assertThat(e.getImsi()).as("check imsi").isEqualTo(actual.getImsi()))
            .satisfies(e ->
                assertThat(e.getPartiallyregistered()).as("check partiallyregistered").isEqualTo(actual.getPartiallyregistered())
            )
            .satisfies(e -> assertThat(e.getPartialdate()).as("check partialdate").isEqualTo(actual.getPartialdate()))
            .satisfies(e -> assertThat(e.getRegisterdate()).as("check registerdate").isEqualTo(actual.getRegisterdate()))
            .satisfies(e -> assertThat(e.getApproved()).as("check approved").isEqualTo(actual.getApproved()))
            .satisfies(e -> assertThat(e.getApprovedby()).as("check approvedby").isEqualTo(actual.getApprovedby()))
            .satisfies(e -> assertThat(e.getApproveddate()).as("check approveddate").isEqualTo(actual.getApproveddate()))
            .satisfies(e -> assertThat(e.getDeclined()).as("check declined").isEqualTo(actual.getDeclined()))
            .satisfies(e -> assertThat(e.getDeclinedby()).as("check declinedby").isEqualTo(actual.getDeclinedby()))
            .satisfies(e -> assertThat(e.getDeclineddate()).as("check declineddate").isEqualTo(actual.getDeclineddate()))
            .satisfies(e -> assertThat(e.getCheckerremarks()).as("check checkerremarks").isEqualTo(actual.getCheckerremarks()))
            .satisfies(e -> assertThat(e.getPostaladdress()).as("check postaladdress").isEqualTo(actual.getPostaladdress()))
            .satisfies(e -> assertThat(e.getResidence()).as("check residence").isEqualTo(actual.getResidence()))
            .satisfies(e -> assertThat(e.getDob()).as("check dob").isEqualTo(actual.getDob()))
            .satisfies(e -> assertThat(e.getCreatedby()).as("check createdby").isEqualTo(actual.getCreatedby()))
            .satisfies(e -> assertThat(e.getEmailaddress()).as("check emailaddress").isEqualTo(actual.getEmailaddress()))
            .satisfies(e -> assertThat(e.getIdentificationid()).as("check identificationid").isEqualTo(actual.getIdentificationid()))
            .satisfies(e -> assertThat(e.getAddaccount()).as("check addaccount").isEqualTo(actual.getAddaccount()))
            .satisfies(e ->
                assertThat(e.getAclinkinginstitution()).as("check aclinkinginstitution").isEqualTo(actual.getAclinkinginstitution())
            )
            .satisfies(e -> assertThat(e.getDeactivated()).as("check deactivated").isEqualTo(actual.getDeactivated()))
            .satisfies(e -> assertThat(e.getDeactivatedby()).as("check deactivatedby").isEqualTo(actual.getDeactivatedby()))
            .satisfies(e -> assertThat(e.getDeactivatedon()).as("check deactivatedon").isEqualTo(actual.getDeactivatedon()))
            .satisfies(e -> assertThat(e.getPhonenochanged()).as("check phonenochanged").isEqualTo(actual.getPhonenochanged()))
            .satisfies(e -> assertThat(e.getPhonenochangedby()).as("check phonenochangedby").isEqualTo(actual.getPhonenochangedby()))
            .satisfies(e -> assertThat(e.getPhonenochangedon()).as("check phonenochangedon").isEqualTo(actual.getPhonenochangedon()))
            .satisfies(e -> assertThat(e.getOriginalphoneno()).as("check originalphoneno").isEqualTo(actual.getOriginalphoneno()))
            .satisfies(e -> assertThat(e.getNewphoneno()).as("check newphoneno").isEqualTo(actual.getNewphoneno()))
            .satisfies(e -> assertThat(e.getReset()).as("check reset").isEqualTo(actual.getReset()))
            .satisfies(e ->
                assertThat(e.getResetinginstitution()).as("check resetinginstitution").isEqualTo(actual.getResetinginstitution())
            )
            .satisfies(e -> assertThat(e.getPinresetremark()).as("check pinresetremark").isEqualTo(actual.getPinresetremark()))
            .satisfies(e -> assertThat(e.getResetby()).as("check resetby").isEqualTo(actual.getResetby()))
            .satisfies(e -> assertThat(e.getReseton()).as("check reseton").isEqualTo(actual.getReseton()))
            .satisfies(e ->
                assertThat(e.getUnblockinginstitution()).as("check unblockinginstitution").isEqualTo(actual.getUnblockinginstitution())
            )
            .satisfies(e -> assertThat(e.getPinblock()).as("check pinblock").isEqualTo(actual.getPinblock()))
            .satisfies(e -> assertThat(e.getPinblockby()).as("check pinblockby").isEqualTo(actual.getPinblockby()))
            .satisfies(e -> assertThat(e.getPinblockremarks()).as("check pinblockremarks").isEqualTo(actual.getPinblockremarks()))
            .satisfies(e ->
                assertThat(e.getBlockinginstitution()).as("check blockinginstitution").isEqualTo(actual.getBlockinginstitution())
            )
            .satisfies(e -> assertThat(e.getPinblockon()).as("check pinblockon").isEqualTo(actual.getPinblockon()))
            .satisfies(e -> assertThat(e.getApprovedon()).as("check approvedon").isEqualTo(actual.getApprovedon()))
            .satisfies(e -> assertThat(e.getPinunblockby()).as("check pinunblockby").isEqualTo(actual.getPinunblockby()))
            .satisfies(e -> assertThat(e.getLoggedin()).as("check loggedin").isEqualTo(actual.getLoggedin()))
            .satisfies(e -> assertThat(e.getTrials()).as("check trials").isEqualTo(actual.getTrials()))
            .satisfies(e -> assertThat(e.getIdtype()).as("check idtype").isEqualTo(actual.getIdtype()))
            .satisfies(e -> assertThat(e.getIdnumber()).as("check idnumber").isEqualTo(actual.getIdnumber()))
            .satisfies(e -> assertThat(e.getGender()).as("check gender").isEqualTo(actual.getGender()))
            .satisfies(e -> assertThat(e.getCif()).as("check cif").isEqualTo(actual.getCif()))
            .satisfies(e -> assertThat(e.getDateofbirth()).as("check dateofbirth").isEqualTo(actual.getDateofbirth()))
            .satisfies(e -> assertThat(e.getRemarks()).as("check remarks").isEqualTo(actual.getRemarks()))
            .satisfies(e -> assertThat(e.getResetimsi()).as("check resetimsi").isEqualTo(actual.getResetimsi()))
            .satisfies(e -> assertThat(e.getImsiresetby()).as("check imsiresetby").isEqualTo(actual.getImsiresetby()))
            .satisfies(e -> assertThat(e.getFirstname()).as("check firstname").isEqualTo(actual.getFirstname()))
            .satisfies(e -> assertThat(e.getSecondname()).as("check secondname").isEqualTo(actual.getSecondname()))
            .satisfies(e -> assertThat(e.getLastname()).as("check lastname").isEqualTo(actual.getLastname()))
            .satisfies(e -> assertThat(e.getPinblocktime()).as("check pinblocktime").isEqualTo(actual.getPinblocktime()))
            .satisfies(e -> assertThat(e.getCustomerstatus()).as("check customerstatus").isEqualTo(actual.getCustomerstatus()))
            .satisfies(e -> assertThat(e.getUsername()).as("check username").isEqualTo(actual.getUsername()))
            .satisfies(e -> assertThat(e.getPassword()).as("check password").isEqualTo(actual.getPassword()))
            .satisfies(e -> assertThat(e.getDeviceid()).as("check deviceid").isEqualTo(actual.getDeviceid()))
            .satisfies(e -> assertThat(e.getChannel()).as("check channel").isEqualTo(actual.getChannel()))
            .satisfies(e -> assertThat(e.getPassreset()).as("check passreset").isEqualTo(actual.getPassreset()))
            .satisfies(e -> assertThat(e.getPassresetby()).as("check passresetby").isEqualTo(actual.getPassresetby()))
            .satisfies(e -> assertThat(e.getPassreseton()).as("check passreseton").isEqualTo(actual.getPassreseton()))
            .satisfies(e -> assertThat(e.getPassblock()).as("check passblock").isEqualTo(actual.getPassblock()))
            .satisfies(e -> assertThat(e.getPassblockby()).as("check passblockby").isEqualTo(actual.getPassblockby()))
            .satisfies(e -> assertThat(e.getPassblockon()).as("check passblockon").isEqualTo(actual.getPassblockon()))
            .satisfies(e -> assertThat(e.getPinmarkblock()).as("check pinmarkblock").isEqualTo(actual.getPinmarkblock()))
            .satisfies(e -> assertThat(e.getPassmarkblock()).as("check passmarkblock").isEqualTo(actual.getPassmarkblock()))
            .satisfies(e -> assertThat(e.getPassresetremarks()).as("check passresetremarks").isEqualTo(actual.getPassresetremarks()))
            .satisfies(e -> assertThat(e.getPassblockremarks()).as("check passblockremarks").isEqualTo(actual.getPassblockremarks()))
            .satisfies(e -> assertThat(e.getPassunblockby()).as("check passunblockby").isEqualTo(actual.getPassunblockby()))
            .satisfies(e -> assertThat(e.getPasstrials()).as("check passtrials").isEqualTo(actual.getPasstrials()))
            .satisfies(e -> assertThat(e.getAppactive()).as("check appactive").isEqualTo(actual.getAppactive()))
            .satisfies(e -> assertThat(e.getLastlogin()).as("check lastlogin").isEqualTo(actual.getLastlogin()))
            .satisfies(e -> assertThat(e.getAppmarkeddisable()).as("check appmarkeddisable").isEqualTo(actual.getAppmarkeddisable()))
            .satisfies(e -> assertThat(e.getDisableby()).as("check disableby").isEqualTo(actual.getDisableby()))
            .satisfies(e -> assertThat(e.getApprovedisableby()).as("check approvedisableby").isEqualTo(actual.getApprovedisableby()))
            .satisfies(e -> assertThat(e.getAppmarkedenable()).as("check appmarkedenable").isEqualTo(actual.getAppmarkedenable()))
            .satisfies(e -> assertThat(e.getEnableby()).as("check enableby").isEqualTo(actual.getEnableby()))
            .satisfies(e -> assertThat(e.getApprovedenableby()).as("check approvedenableby").isEqualTo(actual.getApprovedenableby()))
            .satisfies(e -> assertThat(e.getMarkeddeactivate()).as("check markeddeactivate").isEqualTo(actual.getMarkeddeactivate()))
            .satisfies(e -> assertThat(e.getAppfirstlogin()).as("check appfirstlogin").isEqualTo(actual.getAppfirstlogin()))
            .satisfies(e -> assertThat(e.getAtmtrials()).as("check atmtrials").isEqualTo(actual.getAtmtrials()))
            .satisfies(e -> assertThat(e.getShorcuts()).as("check shorcuts").isEqualTo(actual.getShorcuts()))
            .satisfies(e -> assertThat(e.getMarkedactivate()).as("check markedactivate").isEqualTo(actual.getMarkedactivate()))
            .satisfies(e -> assertThat(e.getTown()).as("check town").isEqualTo(actual.getTown()))
            .satisfies(e -> assertThat(e.getApproveddisableon()).as("check approveddisableon").isEqualTo(actual.getApproveddisableon()))
            .satisfies(e -> assertThat(e.getDisabledon()).as("check disabledon").isEqualTo(actual.getDisabledon()))
            .satisfies(e -> assertThat(e.getResetapproveon()).as("check resetapproveon").isEqualTo(actual.getResetapproveon()))
            .satisfies(e -> assertThat(e.getDeletedby()).as("check deletedby").isEqualTo(actual.getDeletedby()))
            .satisfies(e -> assertThat(e.getQuestionsasked()).as("check questionsasked").isEqualTo(actual.getQuestionsasked()))
            .satisfies(e -> assertThat(e.getQuestionstrials()).as("check questionstrials").isEqualTo(actual.getQuestionstrials()))
            .satisfies(e -> assertThat(e.getQuestionsanswered()).as("check questionsanswered").isEqualTo(actual.getQuestionsanswered()))
            .satisfies(e -> assertThat(e.getValidotp()).as("check validotp").isEqualTo(actual.getValidotp()))
            .satisfies(e -> assertThat(e.getActivatedby()).as("check activatedby").isEqualTo(actual.getActivatedby()))
            .satisfies(e -> assertThat(e.getActivatedon()).as("check activatedon").isEqualTo(actual.getActivatedon()))
            .satisfies(e -> assertThat(e.getBranchcode()).as("check branchcode").isEqualTo(actual.getBranchcode()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCustomerUpdatableRelationshipsEquals(Customer expected, Customer actual) {
        // empty method
    }
}
