import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import LINKEDACCOUNTS from './linkedaccounts';
import LINKEDACCOUNTSDetail from './linkedaccounts-detail';
import LINKEDACCOUNTSUpdate from './linkedaccounts-update';
import LINKEDACCOUNTSDeleteDialog from './linkedaccounts-delete-dialog';

const LINKEDACCOUNTSRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<LINKEDACCOUNTS />} />
    <Route path="new" element={<LINKEDACCOUNTSUpdate />} />
    <Route path=":id">
      <Route index element={<LINKEDACCOUNTSDetail />} />
      <Route path="edit" element={<LINKEDACCOUNTSUpdate />} />
      <Route path="delete" element={<LINKEDACCOUNTSDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default LINKEDACCOUNTSRoutes;
