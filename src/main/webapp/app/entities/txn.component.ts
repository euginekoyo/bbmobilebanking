import { defineComponent, provide } from 'vue';

import UserService from '@/entities/user/user.service';
import MobileAppTransactions from './mobile-app-transactions/mobile-app-transactions.service';

// jhipster-needle-add-entity-service-to-entities-component-import - JHipster will import entities services here

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Txn',
  setup() {
    provide('userService', () => new UserService());
    provide('mobileAppTransactions', () => new MobileAppTransactions());

    // jhipster-needle-add-entity-service-to-entities-component - JHipster will import entities services here
  },
});
