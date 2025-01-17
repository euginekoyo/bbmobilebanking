import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import PINRESETHISTORY from './pinresethistory';
import PINRESETHISTORYDetail from './pinresethistory-detail';
import PINRESETHISTORYUpdate from './pinresethistory-update';
import PINRESETHISTORYDeleteDialog from './pinresethistory-delete-dialog';

const PINRESETHISTORYRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<PINRESETHISTORY />} />
    <Route path="new" element={<PINRESETHISTORYUpdate />} />
    <Route path=":id">
      <Route index element={<PINRESETHISTORYDetail />} />
      <Route path="edit" element={<PINRESETHISTORYUpdate />} />
      <Route path="delete" element={<PINRESETHISTORYDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default PINRESETHISTORYRoutes;
