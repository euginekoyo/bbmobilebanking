import React from 'react';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import BILLERS from './billers';
import BRANCHES from './branches';
import CHANNELS from './channels';
import CHARGE from './charge';
import CHARGERANGES from './chargeranges';
import CUSTOMER from './customer';
import CUSTOMERACCOUNT from './customeraccount';
import LIMITS from './limits';
import LINKEDACCOUNTS from './linkedaccounts';
import MESSAGESSMS from './messagessms';
import MESSAGETEMPLATES from './messagetemplates';
import PINRESETHISTORY from './pinresethistory';
import RANGE from './range';
import REQUESTS from './requests';
import SERVICEMANAGEMENT from './servicemanagement';
import TRANSACTIONS from './transactions';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="billers/*" element={<BILLERS />} />
        <Route path="branches/*" element={<BRANCHES />} />
        <Route path="channels/*" element={<CHANNELS />} />
        <Route path="charge/*" element={<CHARGE />} />
        <Route path="chargeranges/*" element={<CHARGERANGES />} />
        <Route path="customer/*" element={<CUSTOMER />} />
        <Route path="customeraccount/*" element={<CUSTOMERACCOUNT />} />
        <Route path="limits/*" element={<LIMITS />} />
        <Route path="linkedaccounts/*" element={<LINKEDACCOUNTS />} />
        <Route path="messagessms/*" element={<MESSAGESSMS />} />
        <Route path="messagetemplates/*" element={<MESSAGETEMPLATES />} />
        <Route path="pinresethistory/*" element={<PINRESETHISTORY />} />
        <Route path="range/*" element={<RANGE />} />
        <Route path="requests/*" element={<REQUESTS />} />
        <Route path="servicemanagement/*" element={<SERVICEMANAGEMENT />} />
        <Route path="transactions/*" element={<TRANSACTIONS />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
