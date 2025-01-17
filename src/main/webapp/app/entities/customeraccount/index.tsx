import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import CUSTOMERACCOUNT from './customeraccount';
import CUSTOMERACCOUNTDetail from './customeraccount-detail';
import CUSTOMERACCOUNTUpdate from './customeraccount-update';
import CUSTOMERACCOUNTDeleteDialog from './customeraccount-delete-dialog';

const CUSTOMERACCOUNTRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<CUSTOMERACCOUNT />} />
    <Route path="new" element={<CUSTOMERACCOUNTUpdate />} />
    <Route path=":id">
      <Route index element={<CUSTOMERACCOUNTDetail />} />
      <Route path="edit" element={<CUSTOMERACCOUNTUpdate />} />
      <Route path="delete" element={<CUSTOMERACCOUNTDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default CUSTOMERACCOUNTRoutes;
