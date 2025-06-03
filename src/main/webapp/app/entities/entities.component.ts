import { defineComponent, provide } from 'vue';

import UserService from '@/entities/user/user.service';
import BillersService from './billers/billers.service';
import BranchesService from './branches/branches.service';
import ChannelsService from './channels/channels.service';
import ChargeService from './charge/charge.service';
import ChargeRangeService from './charge-range/charge-range.service';
import CustomerService from './customer/customer.service';
import CustomerAccountService from './customer-account/customer-account.service';
import LimitsService from './limits/limits.service';
import MessagesSmsService from './messages-sms/messages-sms.service';
import MessageTemplateService from './message-template/message-template.service';
import PinResetHistoryService from './pin-reset-history/pin-reset-history.service';
import RangeService from './range/range.service';
import ServiceManagementService from './service-management/service-management.service';
// jhipster-needle-add-entity-service-to-entities-component-import - JHipster will import entities services here

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Entities',
  setup() {
    provide('userService', () => new UserService());
    // jhipster-needle-add-entity-service-to-entities-component - JHipster will import entities services here
    provide('billersService', () => new BillersService());
    provide('branchesService', () => new BranchesService());
    provide('channelsService', () => new ChannelsService());
    provide('chargeService', () => new ChargeService());
    provide('chargeRangeService', () => new ChargeRangeService());
    provide('customerService', () => new CustomerService());
    provide('customerAccountService', () => new CustomerAccountService());
    provide('limitsService', () => new LimitsService());
    provide('messagesSmsService', () => new MessagesSmsService());
    provide('messageTemplateService', () => new MessageTemplateService());
    provide('pinResetHistoryService', () => new PinResetHistoryService());
    provide('rangeService', () => new RangeService());
    provide('serviceManagementService', () => new ServiceManagementService());
  },
});
