import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import REQUESTS from './requests';
import REQUESTSDetail from './requests-detail';
import REQUESTSUpdate from './requests-update';
import REQUESTSDeleteDialog from './requests-delete-dialog';

const REQUESTSRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<REQUESTS />} />
    <Route path="new" element={<REQUESTSUpdate />} />
    <Route path=":id">
      <Route index element={<REQUESTSDetail />} />
      <Route path="edit" element={<REQUESTSUpdate />} />
      <Route path="delete" element={<REQUESTSDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default REQUESTSRoutes;
