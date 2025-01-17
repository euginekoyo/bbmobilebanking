import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import CHARGERANGES from './chargeranges';
import CHARGERANGESDetail from './chargeranges-detail';
import CHARGERANGESUpdate from './chargeranges-update';
import CHARGERANGESDeleteDialog from './chargeranges-delete-dialog';

const CHARGERANGESRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<CHARGERANGES />} />
    <Route path="new" element={<CHARGERANGESUpdate />} />
    <Route path=":id">
      <Route index element={<CHARGERANGESDetail />} />
      <Route path="edit" element={<CHARGERANGESUpdate />} />
      <Route path="delete" element={<CHARGERANGESDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default CHARGERANGESRoutes;
