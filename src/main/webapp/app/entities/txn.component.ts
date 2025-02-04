import { defineComponent, provide } from 'vue';

import TransactionsService from './transactions/transactions.service';
import UserService from '@/entities/user/user.service';
// jhipster-needle-add-entity-service-to-entities-component-import - JHipster will import entities services here

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Txn',
  setup() {
    provide('userService', () => new UserService());
    provide('transactionsService', () => new TransactionsService());
    // jhipster-needle-add-entity-service-to-entities-component - JHipster will import entities services here
  },
});
