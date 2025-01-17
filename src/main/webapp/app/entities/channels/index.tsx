import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import CHANNELS from './channels';
import CHANNELSDetail from './channels-detail';
import CHANNELSUpdate from './channels-update';
import CHANNELSDeleteDialog from './channels-delete-dialog';

const CHANNELSRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<CHANNELS />} />
    <Route path="new" element={<CHANNELSUpdate />} />
    <Route path=":id">
      <Route index element={<CHANNELSDetail />} />
      <Route path="edit" element={<CHANNELSUpdate />} />
      <Route path="delete" element={<CHANNELSDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default CHANNELSRoutes;
