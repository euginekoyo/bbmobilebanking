import { type ComputedRef, defineComponent, inject } from 'vue';
import { useI18n } from 'vue-i18n';

import type LoginService from '@/account/login.service';

import spsgraph from './dashboard/spsgraph.component.vue';
import cbspiechart from './dashboard/cbspiechart.components.vue';
import pinresetbarchart from './dashboard/pinresetbarchart.component.vue';
import servicereqbarchart from './dashboard/servicereqbarchart.components.vue';
import servicereqpiechart from './dashboard/servicereqpiechart.component.vue';
import txnsdonut from './dashboard/txnsdonut.component.vue';
import txnsgraph from './dashboard/txnsgraph.components.vue';
import usersbarchart from './dashboard/usersbarchart.component.vue';

export default defineComponent({
  components: {
    spsgraph,
    cbspiechart,
    pinresetbarchart,
    servicereqbarchart,
    servicereqpiechart,
    txnsdonut,
    txnsgraph,
    usersbarchart,
  },
  compatConfig: { MODE: 3 },
  setup() {
    const loginService = inject<LoginService>('loginService');

    const authenticated = inject<ComputedRef<boolean>>('authenticated');
    const username = inject<ComputedRef<string>>('currentUsername');

    const openLogin = () => {
      loginService.openLogin();
    };

    return {
      authenticated,
      username,
      openLogin,
      t$: useI18n().t,
    };
  },
});
