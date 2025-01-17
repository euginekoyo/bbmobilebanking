import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import CUSTOMER from './customer';
import CUSTOMERDetail from './customer-detail';
import CUSTOMERUpdate from './customer-update';
import CUSTOMERDeleteDialog from './customer-delete-dialog';

const CUSTOMERRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<CUSTOMER />} />
    <Route path="new" element={<CUSTOMERUpdate />} />
    <Route path=":id">
      <Route index element={<CUSTOMERDetail />} />
      <Route path="edit" element={<CUSTOMERUpdate />} />
      <Route path="delete" element={<CUSTOMERDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default CUSTOMERRoutes;
