package com.istl.app.service;

import com.istl.app.domain.mobileapp.MobileAppTransactions;
import com.istl.app.repository.mobileapp.MobileAppTransactionsRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MobileAppTransactionsService {

    private static final Logger LOG = LoggerFactory.getLogger(MobileAppTransactionsService.class);

    private final MobileAppTransactionsRepository mobileAppTransactionsRepository;

    public MobileAppTransactionsService(MobileAppTransactionsRepository mobileAppTransactionsRepository) {
        this.mobileAppTransactionsRepository = mobileAppTransactionsRepository;
    }

    /**
     * Save a mobileAppTransactions.
     *
     * @param mobileAppTransactions the entity to save.
     * @return the persisted entity.
     */
    public MobileAppTransactions save(MobileAppTransactions mobileAppTransactions) {
        LOG.debug("Request to save MobileAppTransactions : {}", mobileAppTransactions);
        return mobileAppTransactionsRepository.save(mobileAppTransactions);
    }

    /**
     * Update a mobileAppTransactions.
     *
     * @param mobileAppTransactions the entity to save.
     * @return the persisted entity.
     */
    public MobileAppTransactions update(MobileAppTransactions mobileAppTransactions) {
        LOG.debug("Request to update MobileAppTransactions : {}", mobileAppTransactions);
        return mobileAppTransactionsRepository.save(mobileAppTransactions);
    }

    /**
     * Partially update a mobileAppTransactions.
     *
     * @param mobileAppTransactions the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<MobileAppTransactions> partialUpdate(MobileAppTransactions mobileAppTransactions) {
        LOG.debug("Request to partially update MobileAppTransactions : {}", mobileAppTransactions);

        return mobileAppTransactionsRepository
            .findById(mobileAppTransactions.getId())
            .map(existingMobileAppTransactions -> {
                if (mobileAppTransactions.getChannel() != null) {
                    existingMobileAppTransactions.setChannel(mobileAppTransactions.getChannel());
                }
                if (mobileAppTransactions.getChannelIp() != null) {
                    existingMobileAppTransactions.setChannelIp(mobileAppTransactions.getChannelIp());
                }
                if (mobileAppTransactions.getChannelReference() != null) {
                    existingMobileAppTransactions.setChannelReference(mobileAppTransactions.getChannelReference());
                }
                if (mobileAppTransactions.getChannelTimestamp() != null) {
                    existingMobileAppTransactions.setChannelTimestamp(mobileAppTransactions.getChannelTimestamp());
                }
                if (mobileAppTransactions.getClientId() != null) {
                    existingMobileAppTransactions.setClientId(mobileAppTransactions.getClientId());
                }
                if (mobileAppTransactions.getCreatedAt() != null) {
                    existingMobileAppTransactions.setCreatedAt(mobileAppTransactions.getCreatedAt());
                }
                if (mobileAppTransactions.getDebitAccount() != null) {
                    existingMobileAppTransactions.setDebitAccount(mobileAppTransactions.getDebitAccount());
                }
                if (mobileAppTransactions.getDirection() != null) {
                    existingMobileAppTransactions.setDirection(mobileAppTransactions.getDirection());
                }
                if (mobileAppTransactions.getErrorDescription() != null) {
                    existingMobileAppTransactions.setErrorDescription(mobileAppTransactions.getErrorDescription());
                }
                if (mobileAppTransactions.getGeolocation() != null) {
                    existingMobileAppTransactions.setGeolocation(mobileAppTransactions.getGeolocation());
                }
                if (mobileAppTransactions.getHostCode() != null) {
                    existingMobileAppTransactions.setHostCode(mobileAppTransactions.getHostCode());
                }
                if (mobileAppTransactions.getPhoneNumber() != null) {
                    existingMobileAppTransactions.setPhoneNumber(mobileAppTransactions.getPhoneNumber());
                }
                if (mobileAppTransactions.getResponseCode() != null) {
                    existingMobileAppTransactions.setResponseCode(mobileAppTransactions.getResponseCode());
                }
                if (mobileAppTransactions.getResponseMessage() != null) {
                    existingMobileAppTransactions.setResponseMessage(mobileAppTransactions.getResponseMessage());
                }
                if (mobileAppTransactions.getTransactionCode() != null) {
                    existingMobileAppTransactions.setTransactionCode(mobileAppTransactions.getTransactionCode());
                }
                if (mobileAppTransactions.getTransactionType() != null) {
                    existingMobileAppTransactions.setTransactionType(mobileAppTransactions.getTransactionType());
                }
                if (mobileAppTransactions.getUserAgent() != null) {
                    existingMobileAppTransactions.setUserAgent(mobileAppTransactions.getUserAgent());
                }
                if (mobileAppTransactions.getUserAgentVersion() != null) {
                    existingMobileAppTransactions.setUserAgentVersion(mobileAppTransactions.getUserAgentVersion());
                }
                if (mobileAppTransactions.getAmount() != null) {
                    existingMobileAppTransactions.setAmount(mobileAppTransactions.getAmount());
                }
                if (mobileAppTransactions.getChargeamount() != null) {
                    existingMobileAppTransactions.setChargeamount(mobileAppTransactions.getChargeamount());
                }
                if (mobileAppTransactions.getCreditAccount() != null) {
                    existingMobileAppTransactions.setCreditAccount(mobileAppTransactions.getCreditAccount());
                }
                if (mobileAppTransactions.getCbsReference() != null) {
                    existingMobileAppTransactions.setCbsReference(mobileAppTransactions.getCbsReference());
                }

                return existingMobileAppTransactions;
            })
            .map(mobileAppTransactionsRepository::save);
    }

    /**
     * Get one mobileAppTransactions by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MobileAppTransactions> findOne(Long id) {
        LOG.debug("Request to get MobileAppTransactions : {}", id);
        return mobileAppTransactionsRepository.findById(id);
    }

    /**
     * Delete the mobileAppTransactions by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete MobileAppTransactions : {}", id);
        mobileAppTransactionsRepository.deleteById(id);
    }
}
