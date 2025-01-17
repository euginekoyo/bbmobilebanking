import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import CHARGE from './charge';
import CHARGEDetail from './charge-detail';
import CHARGEUpdate from './charge-update';
import CHARGEDeleteDialog from './charge-delete-dialog';

const CHARGERoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<CHARGE />} />
    <Route path="new" element={<CHARGEUpdate />} />
    <Route path=":id">
      <Route index element={<CHARGEDetail />} />
      <Route path="edit" element={<CHARGEUpdate />} />
      <Route path="delete" element={<CHARGEDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default CHARGERoutes;
