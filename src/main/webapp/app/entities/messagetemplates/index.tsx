import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import MESSAGETEMPLATES from './messagetemplates';
import MESSAGETEMPLATESDetail from './messagetemplates-detail';
import MESSAGETEMPLATESUpdate from './messagetemplates-update';
import MESSAGETEMPLATESDeleteDialog from './messagetemplates-delete-dialog';

const MESSAGETEMPLATESRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<MESSAGETEMPLATES />} />
    <Route path="new" element={<MESSAGETEMPLATESUpdate />} />
    <Route path=":id">
      <Route index element={<MESSAGETEMPLATESDetail />} />
      <Route path="edit" element={<MESSAGETEMPLATESUpdate />} />
      <Route path="delete" element={<MESSAGETEMPLATESDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default MESSAGETEMPLATESRoutes;
