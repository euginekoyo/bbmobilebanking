import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import TRANSACTIONS from './transactions';
import TRANSACTIONSDetail from './transactions-detail';
import TRANSACTIONSUpdate from './transactions-update';
import TRANSACTIONSDeleteDialog from './transactions-delete-dialog';

const TRANSACTIONSRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<TRANSACTIONS />} />
    <Route path="new" element={<TRANSACTIONSUpdate />} />
    <Route path=":id">
      <Route index element={<TRANSACTIONSDetail />} />
      <Route path="edit" element={<TRANSACTIONSUpdate />} />
      <Route path="delete" element={<TRANSACTIONSDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default TRANSACTIONSRoutes;
