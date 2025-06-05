package com.istl.app.web.rest;

import com.istl.app.domain.mobileapp.Transactions;
import com.istl.app.repository.mobileapp.TransactionsRepository;
import com.istl.app.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.istl.app.domain.mobileapp.Transactions}.
 */
@RestController
@RequestMapping("/api/transactions")
@Transactional
public class TransactionsResource {

    private static final Logger LOG = LoggerFactory.getLogger(TransactionsResource.class);

    private static final String ENTITY_NAME = "transactions";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TransactionsRepository transactionsRepository;

    public TransactionsResource(TransactionsRepository transactionsRepository) {
        this.transactionsRepository = transactionsRepository;
    }

    /**
     * {@code POST  /transactions} : Create a new transactions.
     *
     * @param transactions the transactions to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new transactions, or with status {@code 400 (Bad Request)} if the transactions has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Transactions> createTransactions(@Valid @RequestBody Transactions transactions) throws URISyntaxException {
        LOG.debug("REST request to save Transactions : {}", transactions);
        if (transactions.getId() != null) {
            throw new BadRequestAlertException("A new transactions cannot already have an ID", ENTITY_NAME, "idexists");
        }
        transactions = transactionsRepository.save(transactions);
        return ResponseEntity.created(new URI("/api/transactions/" + transactions.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, transactions.getId().toString()))
            .body(transactions);
    }

    /**
     * {@code PUT  /transactions/:id} : Updates an existing transactions.
     *
     * @param id the id of the transactions to save.
     * @param transactions the transactions to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated transactions,
     * or with status {@code 400 (Bad Request)} if the transactions is not valid,
     * or with status {@code 500 (Internal Server Error)} if the transactions couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Transactions> updateTransactions(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Transactions transactions
    ) throws URISyntaxException {
        LOG.debug("REST request to update Transactions : {}, {}", id, transactions);
        if (transactions.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, transactions.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!transactionsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        transactions = transactionsRepository.save(transactions);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, transactions.getId().toString()))
            .body(transactions);
    }

    /**
     * {@code PATCH  /transactions/:id} : Partial updates given fields of an existing transactions, field will ignore if it is null
     *
     * @param id the id of the transactions to save.
     * @param transactions the transactions to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated transactions,
     * or with status {@code 400 (Bad Request)} if the transactions is not valid,
     * or with status {@code 404 (Not Found)} if the transactions is not found,
     * or with status {@code 500 (Internal Server Error)} if the transactions couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Transactions> partialUpdateTransactions(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Transactions transactions
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Transactions partially : {}, {}", id, transactions);
        if (transactions.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, transactions.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!transactionsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Transactions> result = transactionsRepository
            .findById(transactions.getId())
            .map(existingTransactions -> {
                if (transactions.getProcessed() != null) {
                    existingTransactions.setProcessed(transactions.getProcessed());
                }
                if (transactions.getIncomingbitmap() != null) {
                    existingTransactions.setIncomingbitmap(transactions.getIncomingbitmap());
                }
                if (transactions.getOutgoingbitmap() != null) {
                    existingTransactions.setOutgoingbitmap(transactions.getOutgoingbitmap());
                }
                if (transactions.getInmessage() != null) {
                    existingTransactions.setInmessage(transactions.getInmessage());
                }
                if (transactions.getMessagetocbs() != null) {
                    existingTransactions.setMessagetocbs(transactions.getMessagetocbs());
                }
                if (transactions.getMessagefromcbs() != null) {
                    existingTransactions.setMessagefromcbs(transactions.getMessagefromcbs());
                }
                if (transactions.getCbsprocess() != null) {
                    existingTransactions.setCbsprocess(transactions.getCbsprocess());
                }
                if (transactions.getCbsonline() != null) {
                    existingTransactions.setCbsonline(transactions.getCbsonline());
                }
                if (transactions.getCbsresponse() != null) {
                    existingTransactions.setCbsresponse(transactions.getCbsresponse());
                }
                if (transactions.getResponsemessage() != null) {
                    existingTransactions.setResponsemessage(transactions.getResponsemessage());
                }
                if (transactions.getResponsesent() != null) {
                    existingTransactions.setResponsesent(transactions.getResponsesent());
                }
                if (transactions.getChannel() != null) {
                    existingTransactions.setChannel(transactions.getChannel());
                }
                if (transactions.getOriginaldata() != null) {
                    existingTransactions.setOriginaldata(transactions.getOriginaldata());
                }
                if (transactions.getField39resp() != null) {
                    existingTransactions.setField39resp(transactions.getField39resp());
                }
                if (transactions.getNarration() != null) {
                    existingTransactions.setNarration(transactions.getNarration());
                }
                if (transactions.getAuthorised() != null) {
                    existingTransactions.setAuthorised(transactions.getAuthorised());
                }
                if (transactions.getBranchcode() != null) {
                    existingTransactions.setBranchcode(transactions.getBranchcode());
                }
                if (transactions.getField39original() != null) {
                    existingTransactions.setField39original(transactions.getField39original());
                }
                if (transactions.getMessageclass() != null) {
                    existingTransactions.setMessageclass(transactions.getMessageclass());
                }
                if (transactions.getTxncode() != null) {
                    existingTransactions.setTxncode(transactions.getTxncode());
                }
                if (transactions.getCurrcode() != null) {
                    existingTransactions.setCurrcode(transactions.getCurrcode());
                }
                if (transactions.getDevice() != null) {
                    existingTransactions.setDevice(transactions.getDevice());
                }
                if (transactions.getBranch2() != null) {
                    existingTransactions.setBranch2(transactions.getBranch2());
                }
                if (transactions.getLongerbranch() != null) {
                    existingTransactions.setLongerbranch(transactions.getLongerbranch());
                }
                if (transactions.getDatex() != null) {
                    existingTransactions.setDatex(transactions.getDatex());
                }
                if (transactions.getTimex() != null) {
                    existingTransactions.setTimex(transactions.getTimex());
                }
                if (transactions.getPosted() != null) {
                    existingTransactions.setPosted(transactions.getPosted());
                }
                if (transactions.getAttempts() != null) {
                    existingTransactions.setAttempts(transactions.getAttempts());
                }
                if (transactions.getOriginaldata2() != null) {
                    existingTransactions.setOriginaldata2(transactions.getOriginaldata2());
                }
                if (transactions.getCommission() != null) {
                    existingTransactions.setCommission(transactions.getCommission());
                }
                if (transactions.getResponsecreated() != null) {
                    existingTransactions.setResponsecreated(transactions.getResponsecreated());
                }
                if (transactions.getOnline() != null) {
                    existingTransactions.setOnline(transactions.getOnline());
                }
                if (transactions.getOriginaldata3() != null) {
                    existingTransactions.setOriginaldata3(transactions.getOriginaldata3());
                }
                if (transactions.getToswitch() != null) {
                    existingTransactions.setToswitch(transactions.getToswitch());
                }
                if (transactions.getFromswitch() != null) {
                    existingTransactions.setFromswitch(transactions.getFromswitch());
                }
                if (transactions.getTocbs() != null) {
                    existingTransactions.setTocbs(transactions.getTocbs());
                }
                if (transactions.getFromcbs() != null) {
                    existingTransactions.setFromcbs(transactions.getFromcbs());
                }
                if (transactions.getPostinglegs() != null) {
                    existingTransactions.setPostinglegs(transactions.getPostinglegs());
                }
                if (transactions.getCommissiontxncode() != null) {
                    existingTransactions.setCommissiontxncode(transactions.getCommissiontxncode());
                }
                if (transactions.getHostref() != null) {
                    existingTransactions.setHostref(transactions.getHostref());
                }
                if (transactions.getRequestcreated() != null) {
                    existingTransactions.setRequestcreated(transactions.getRequestcreated());
                }
                if (transactions.getRequestmessage() != null) {
                    existingTransactions.setRequestmessage(transactions.getRequestmessage());
                }
                if (transactions.getOutgoingbitmapflex() != null) {
                    existingTransactions.setOutgoingbitmapflex(transactions.getOutgoingbitmapflex());
                }
                if (transactions.getIncomingbitmapflex() != null) {
                    existingTransactions.setIncomingbitmapflex(transactions.getIncomingbitmapflex());
                }
                if (transactions.getRequestsent() != null) {
                    existingTransactions.setRequestsent(transactions.getRequestsent());
                }
                if (transactions.getMinicbs() != null) {
                    existingTransactions.setMinicbs(transactions.getMinicbs());
                }
                if (transactions.getReversed() != null) {
                    existingTransactions.setReversed(transactions.getReversed());
                }
                if (transactions.getOfflinesenttohost() != null) {
                    existingTransactions.setOfflinesenttohost(transactions.getOfflinesenttohost());
                }
                if (transactions.getOfflineresponse() != null) {
                    existingTransactions.setOfflineresponse(transactions.getOfflineresponse());
                }
                if (transactions.getSourceLongerface() != null) {
                    existingTransactions.setSourceLongerface(transactions.getSourceLongerface());
                }
                if (transactions.getMtirrn() != null) {
                    existingTransactions.setMtirrn(transactions.getMtirrn());
                }
                if (transactions.getHostresponsecode() != null) {
                    existingTransactions.setHostresponsecode(transactions.getHostresponsecode());
                }
                if (transactions.getField48() != null) {
                    existingTransactions.setField48(transactions.getField48());
                }
                if (transactions.getSource() != null) {
                    existingTransactions.setSource(transactions.getSource());
                }

                return existingTransactions;
            })
            .map(transactionsRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, transactions.getId().toString())
        );
    }

    /**
     * {@code GET  /transactions} : get all the transactions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of transactions in body.
     */
    @GetMapping("")
    public List<Transactions> getAllTransactions() {
        LOG.debug("REST request to get all Transactions");
        return transactionsRepository.findAll();
    }

    /**
     * {@code GET  /transactions/:id} : get the "id" transactions.
     *
     * @param id the id of the transactions to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the transactions, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Transactions> getTransactions(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Transactions : {}", id);
        Optional<Transactions> transactions = transactionsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(transactions);
    }

    /**
     * {@code DELETE  /transactions/:id} : delete the "id" transactions.
     *
     * @param id the id of the transactions to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransactions(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Transactions : {}", id);
        transactionsRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
