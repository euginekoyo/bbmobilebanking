import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import RANGE from './range';
import RANGEDetail from './range-detail';
import RANGEUpdate from './range-update';
import RANGEDeleteDialog from './range-delete-dialog';

const RANGERoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<RANGE />} />
    <Route path="new" element={<RANGEUpdate />} />
    <Route path=":id">
      <Route index element={<RANGEDetail />} />
      <Route path="edit" element={<RANGEUpdate />} />
      <Route path="delete" element={<RANGEDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default RANGERoutes;
