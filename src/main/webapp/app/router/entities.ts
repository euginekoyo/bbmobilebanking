import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore
const Entities = () => import('@/entities/entities.vue');

const CBSTransactions = () => import('@/entities/cbs-transactions/cbs-transactions.vue');
const CBSTransactionsUpdate = () => import('@/entities/cbs-transactions/cbs-transactions-update.vue');
const CBSTransactionsDetails = () => import('@/entities/cbs-transactions/cbs-transactions-details.vue');

const SPSIncomingTransactions = () => import('@/entities/sps-incoming-transactions/sps-incoming-transactions.vue');
const SPSIncomingTransactionsUpdate = () => import('@/entities/sps-incoming-transactions/sps-incoming-transactions-update.vue');
const SPSIncomingTransactionsDetails = () => import('@/entities/sps-incoming-transactions/sps-incoming-transactions-details.vue');

const SPSOutgoingTransactions = () => import('@/entities/sps-outgoing-transactions/sps-outgoing-transactions.vue');
const SPSOutgoingTransactionsUpdate = () => import('@/entities/sps-outgoing-transactions/sps-outgoing-transactions-update.vue');
const SPSOutgoingTransactionsDetails = () => import('@/entities/sps-outgoing-transactions/sps-outgoing-transactions-details.vue');

const SPSParticipatingCodes = () => import('@/entities/sps-participating-codes/sps-participating-codes.vue');
const SPSParticipatingCodesUpdate = () => import('@/entities/sps-participating-codes/sps-participating-codes-update.vue');
const SPSParticipatingCodesDetails = () => import('@/entities/sps-participating-codes/sps-participating-codes-details.vue');

const Billers = () => import('@/entities/billers/billers.vue');
const BillersUpdate = () => import('@/entities/billers/billers-update.vue');
const BillersDetails = () => import('@/entities/billers/billers-details.vue');

const Branches = () => import('@/entities/branches/branches.vue');
const BranchesUpdate = () => import('@/entities/branches/branches-update.vue');
const BranchesDetails = () => import('@/entities/branches/branches-details.vue');

const Channels = () => import('@/entities/channels/channels.vue');
const ChannelsUpdate = () => import('@/entities/channels/channels-update.vue');
const ChannelsDetails = () => import('@/entities/channels/channels-details.vue');

const Charge = () => import('@/entities/charge/charge.vue');
const ChargeUpdate = () => import('@/entities/charge/charge-update.vue');
const ChargeDetails = () => import('@/entities/charge/charge-details.vue');

const ChargeRange = () => import('@/entities/charge-range/charge-range.vue');
const ChargeRangeUpdate = () => import('@/entities/charge-range/charge-range-update.vue');
const ChargeRangeDetails = () => import('@/entities/charge-range/charge-range-details.vue');

const Customer = () => import('@/entities/customer/customer.vue');
const CustomerUpdate = () => import('@/entities/customer/customer-update.vue');
const CustomerDetails = () => import('@/entities/customer/customer-details.vue');

const CustomerAccount = () => import('@/entities/customer-account/customer-account.vue');
const CustomerAccountUpdate = () => import('@/entities/customer-account/customer-account-update.vue');
const CustomerAccountDetails = () => import('@/entities/customer-account/customer-account-details.vue');

const Limits = () => import('@/entities/limits/limits.vue');
const LimitsUpdate = () => import('@/entities/limits/limits-update.vue');
const LimitsDetails = () => import('@/entities/limits/limits-details.vue');

const MessagesSms = () => import('@/entities/messages-sms/messages-sms.vue');
const MessagesSmsUpdate = () => import('@/entities/messages-sms/messages-sms-update.vue');
const MessagesSmsDetails = () => import('@/entities/messages-sms/messages-sms-details.vue');

const MessageTemplate = () => import('@/entities/message-template/message-template.vue');
const MessageTemplateUpdate = () => import('@/entities/message-template/message-template-update.vue');
const MessageTemplateDetails = () => import('@/entities/message-template/message-template-details.vue');

const BlockCustomer = () => import('@/entities/customer/blockCustomer/blockCustomer.vue');
const UnBlockCustomer = () => import('@/entities/customer/blockCustomer/unBlockCustomer.vue');

const PinRest = () => import('@/entities/customer/PinReset/pinRest.vue');
const ApprovePinReset = () => import('@/entities/customer/PinReset/approvePinReset.vue');
const PinResetHistory = () => import('@/entities/pin-reset-history/pin-reset-history.vue');
const PinResetHistoryUpdate = () => import('@/entities/pin-reset-history/pin-reset-history-update.vue');
const PinResetHistoryDetails = () => import('@/entities/pin-reset-history/pin-reset-history-details.vue');

const Range = () => import('@/entities/range/range.vue');
const RangeUpdate = () => import('@/entities/range/range-update.vue');
const RangeDetails = () => import('@/entities/range/range-details.vue');

const ServiceManagement = () => import('@/entities/service-management/service-management.vue');
const ServiceManagementUpdate = () => import('@/entities/service-management/service-management-update.vue');
const ServiceManagementDetails = () => import('@/entities/service-management/service-management-details.vue');

const Transactions = () => import('@/entities/transactions/transactions.vue');
const TransactionsUpdate = () => import('@/entities/transactions/transactions-update.vue');
const TransactionsDetails = () => import('@/entities/transactions/transactions-details.vue');

const MobileAppTransactions = () => import('@/entities/mobile-app-transactions/mobile-app-transactions.vue');
const MobileAppTransactionsUpdate = () => import('@/entities/mobile-app-transactions/mobile-app-transactions-update.vue');
const MobileAppTransactionsDetails = () => import('@/entities/mobile-app-transactions/mobile-app-transactions-details.vue');

const Notification = () => import('@/entities/notification/notification.vue');

// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default {
  path: '/',
  component: Entities,
  children: [
    {
      path: 'cbs-transactions',
      name: 'CBSTransactions',
      component: CBSTransactions,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'cbs-transactions/new',
      name: 'CBSTransactionsCreate',
      component: CBSTransactionsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'cbs-transactions/:cBSTransactionsId/edit',
      name: 'CBSTransactionsEdit',
      component: CBSTransactionsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'cbs-transactions/:cBSTransactionsId/view',
      name: 'CBSTransactionsView',
      component: CBSTransactionsDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'sps-incoming-transactions',
      name: 'SPSIncomingTransactions',
      component: SPSIncomingTransactions,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'sps-incoming-transactions/new',
      name: 'SPSIncomingTransactionsCreate',
      component: SPSIncomingTransactionsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'sps-incoming-transactions/:sPSIncomingTransactionsId/edit',
      name: 'SPSIncomingTransactionsEdit',
      component: SPSIncomingTransactionsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'sps-incoming-transactions/:sPSIncomingTransactionsId/view',
      name: 'SPSIncomingTransactionsView',
      component: SPSIncomingTransactionsDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'sps-outgoing-transactions',
      name: 'SPSOutgoingTransactions',
      component: SPSOutgoingTransactions,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'sps-outgoing-transactions/new',
      name: 'SPSOutgoingTransactionsCreate',
      component: SPSOutgoingTransactionsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'sps-outgoing-transactions/:sPSOutgoingTransactionsId/edit',
      name: 'SPSOutgoingTransactionsEdit',
      component: SPSOutgoingTransactionsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'sps-outgoing-transactions/:sPSOutgoingTransactionsId/view',
      name: 'SPSOutgoingTransactionsView',
      component: SPSOutgoingTransactionsDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'sps-participating-codes',
      name: 'SPSParticipatingCodes',
      component: SPSParticipatingCodes,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'sps-participating-codes/new',
      name: 'SPSParticipatingCodesCreate',
      component: SPSParticipatingCodesUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'sps-participating-codes/:sPSParticipatingCodesId/edit',
      name: 'SPSParticipatingCodesEdit',
      component: SPSParticipatingCodesUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'sps-participating-codes/:sPSParticipatingCodesId/view',
      name: 'SPSParticipatingCodesView',
      component: SPSParticipatingCodesDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'billers',
      name: 'Billers',
      component: Billers,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'billers/new',
      name: 'BillersCreate',
      component: BillersUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'billers/:billersId/edit',
      name: 'BillersEdit',
      component: BillersUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'billers/:billersId/view',
      name: 'BillersView',
      component: BillersDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'branches',
      name: 'Branches',
      component: Branches,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'branches/new',
      name: 'BranchesCreate',
      component: BranchesUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'branches/:branchesId/edit',
      name: 'BranchesEdit',
      component: BranchesUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'branches/:branchesId/view',
      name: 'BranchesView',
      component: BranchesDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'channels',
      name: 'Channels',
      component: Channels,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'channels/new',
      name: 'ChannelsCreate',
      component: ChannelsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'channels/:channelsId/edit',
      name: 'ChannelsEdit',
      component: ChannelsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'channels/:channelsId/view',
      name: 'ChannelsView',
      component: ChannelsDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'charge',
      name: 'Charge',
      component: Charge,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'charge/new',
      name: 'ChargeCreate',
      component: ChargeUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'charge/:chargeId/edit',
      name: 'ChargeEdit',
      component: ChargeUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'charge/:chargeId/view',
      name: 'ChargeView',
      component: ChargeDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'charge-range',
      name: 'ChargeRange',
      component: ChargeRange,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'charge-range/new',
      name: 'ChargeRangeCreate',
      component: ChargeRangeUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'charge-range/:chargeRangeId/edit',
      name: 'ChargeRangeEdit',
      component: ChargeRangeUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'charge-range/:chargeRangeId/view',
      name: 'ChargeRangeView',
      component: ChargeRangeDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'customer',
      name: 'Customer',
      component: Customer,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'customer/new',
      name: 'CustomerCreate',
      component: CustomerUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'customer/:customerId/edit',
      name: 'CustomerEdit',
      component: CustomerUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'customer/:customerId/view',
      name: 'CustomerView',
      component: CustomerDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'customer-account',
      name: 'CustomerAccount',
      component: CustomerAccount,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'customer-account/new',
      name: 'CustomerAccountCreate',
      component: CustomerAccountUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'customer-account/:customerAccountId/edit',
      name: 'CustomerAccountEdit',
      component: CustomerAccountUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'customer-account/:customerAccountId/view',
      name: 'CustomerAccountView',
      component: CustomerAccountDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'limits',
      name: 'Limits',
      component: Limits,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'limits/new',
      name: 'LimitsCreate',
      component: LimitsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'limits/:limitsId/edit',
      name: 'LimitsEdit',
      component: LimitsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'limits/:limitsId/view',
      name: 'LimitsView',
      component: LimitsDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'messages-sms',
      name: 'MessagesSms',
      component: MessagesSms,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'messages-sms/new',
      name: 'MessagesSmsCreate',
      component: MessagesSmsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'messages-sms/:messagesSmsId/edit',
      name: 'MessagesSmsEdit',
      component: MessagesSmsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'messages-sms/:messagesSmsId/view',
      name: 'MessagesSmsView',
      component: MessagesSmsDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'message-template',
      name: 'MessageTemplate',
      component: MessageTemplate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'message-template/new',
      name: 'MessageTemplateCreate',
      component: MessageTemplateUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'message-template/:messageTemplateId/edit',
      name: 'MessageTemplateEdit',
      component: MessageTemplateUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'message-template/:messageTemplateId/view',
      name: 'MessageTemplateView',
      component: MessageTemplateDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'reset-pin',
      name: 'ResetPin',
      component: PinRest,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'approve-pin-reset',
      name: 'ApprovePinReset',
      component: ApprovePinReset,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'block-customer',
      name: 'BlockCustomer',
      component: BlockCustomer,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'unblock-customer',
      name: 'UnBlockCustomer',
      component: UnBlockCustomer,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'pin-reset-history',
      name: 'PinResetHistory',
      component: PinResetHistory,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'pin-reset-history/new',
      name: 'PinResetHistoryCreate',
      component: PinResetHistoryUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'pin-reset-history/:pinResetHistoryId/edit',
      name: 'PinResetHistoryEdit',
      component: PinResetHistoryUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'pin-reset-history/:pinResetHistoryId/view',
      name: 'PinResetHistoryView',
      component: PinResetHistoryDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'range',
      name: 'Range',
      component: Range,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'range/new',
      name: 'RangeCreate',
      component: RangeUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'range/:rangeId/edit',
      name: 'RangeEdit',
      component: RangeUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'range/:rangeId/view',
      name: 'RangeView',
      component: RangeDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'service-management',
      name: 'ServiceManagement',
      component: ServiceManagement,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'service-management/new',
      name: 'ServiceManagementCreate',
      component: ServiceManagementUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'service-management/:serviceManagementId/edit',
      name: 'ServiceManagementEdit',
      component: ServiceManagementUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'service-management/:serviceManagementId/view',
      name: 'ServiceManagementView',
      component: ServiceManagementDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'transactions',
      name: 'Transactions',
      component: Transactions,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'transactions/new',
      name: 'TransactionsCreate',
      component: TransactionsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'transactions/:transactionsId/edit',
      name: 'TransactionsEdit',
      component: TransactionsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'transactions/:transactionsId/view',
      name: 'TransactionsView',
      component: TransactionsDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'mobile-app-transactions',
      name: 'MobileAppTransactions',
      component: MobileAppTransactions,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'mobile-app-transactions/new',
      name: 'MobileAppTransactionsCreate',
      component: MobileAppTransactionsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'mobile-app-transactions/:mobile-app-transactionsId/edit',
      name: 'MobileAppTransactionsEdit',
      component: MobileAppTransactionsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'mobile-app-transactions/:mobile-app-transactionsId/view',
      name: 'MobileAppTransactionsView',
      component: MobileAppTransactionsDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'notification',
      name: 'Notification',
      component: Notification,
      meta: { authorities: [Authority.USER] },
    },
    // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
  ],
};
