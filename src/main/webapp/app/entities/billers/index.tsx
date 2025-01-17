import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import BILLERS from './billers';
import BILLERSDetail from './billers-detail';
import BILLERSUpdate from './billers-update';
import BILLERSDeleteDialog from './billers-delete-dialog';

const BILLERSRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<BILLERS />} />
    <Route path="new" element={<BILLERSUpdate />} />
    <Route path=":id">
      <Route index element={<BILLERSDetail />} />
      <Route path="edit" element={<BILLERSUpdate />} />
      <Route path="delete" element={<BILLERSDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default BILLERSRoutes;
