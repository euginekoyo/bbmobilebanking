import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import MESSAGESSMS from './messagessms';
import MESSAGESSMSDetail from './messagessms-detail';
import MESSAGESSMSUpdate from './messagessms-update';
import MESSAGESSMSDeleteDialog from './messagessms-delete-dialog';

const MESSAGESSMSRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<MESSAGESSMS />} />
    <Route path="new" element={<MESSAGESSMSUpdate />} />
    <Route path=":id">
      <Route index element={<MESSAGESSMSDetail />} />
      <Route path="edit" element={<MESSAGESSMSUpdate />} />
      <Route path="delete" element={<MESSAGESSMSDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default MESSAGESSMSRoutes;
