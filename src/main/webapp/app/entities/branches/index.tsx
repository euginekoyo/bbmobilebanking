import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import BRANCHES from './branches';
import BRANCHESDetail from './branches-detail';
import BRANCHESUpdate from './branches-update';
import BRANCHESDeleteDialog from './branches-delete-dialog';

const BRANCHESRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<BRANCHES />} />
    <Route path="new" element={<BRANCHESUpdate />} />
    <Route path=":id">
      <Route index element={<BRANCHESDetail />} />
      <Route path="edit" element={<BRANCHESUpdate />} />
      <Route path="delete" element={<BRANCHESDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default BRANCHESRoutes;
