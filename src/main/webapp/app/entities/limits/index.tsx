import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import LIMITS from './limits';
import LIMITSDetail from './limits-detail';
import LIMITSUpdate from './limits-update';
import LIMITSDeleteDialog from './limits-delete-dialog';

const LIMITSRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<LIMITS />} />
    <Route path="new" element={<LIMITSUpdate />} />
    <Route path=":id">
      <Route index element={<LIMITSDetail />} />
      <Route path="edit" element={<LIMITSUpdate />} />
      <Route path="delete" element={<LIMITSDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default LIMITSRoutes;
