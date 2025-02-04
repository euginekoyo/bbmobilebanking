import { defineComponent, provide } from 'vue';

import CBSTransactionsService from './cbs-transactions/cbs-transactions.service';
import SPSIncomingTransactionsService from './sps-incoming-transactions/sps-incoming-transactions.service';
import SPSOutgoingTransactionsService from './sps-outgoing-transactions/sps-outgoing-transactions.service';
import SPSParticipatingCodesService from './sps-participating-codes/sps-participating-codes.service';

import UserService from '@/entities/user/user.service';
// jhipster-needle-add-entity-service-to-entities-component-import - JHipster will import entities services here

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Recon',
  setup() {
    provide('userService', () => new UserService());
    provide('cBSTransactionsService', () => new CBSTransactionsService());
    provide('sPSIncomingTransactionsService', () => new SPSIncomingTransactionsService());
    provide('sPSOutgoingTransactionsService', () => new SPSOutgoingTransactionsService());
    provide('sPSParticipatingCodesService', () => new SPSParticipatingCodesService());

    // jhipster-needle-add-entity-service-to-entities-component - JHipster will import entities services here
  },
});
