import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import SERVICEMANAGEMENT from './servicemanagement';
import SERVICEMANAGEMENTDetail from './servicemanagement-detail';
import SERVICEMANAGEMENTUpdate from './servicemanagement-update';
import SERVICEMANAGEMENTDeleteDialog from './servicemanagement-delete-dialog';

const SERVICEMANAGEMENTRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<SERVICEMANAGEMENT />} />
    <Route path="new" element={<SERVICEMANAGEMENTUpdate />} />
    <Route path=":id">
      <Route index element={<SERVICEMANAGEMENTDetail />} />
      <Route path="edit" element={<SERVICEMANAGEMENTUpdate />} />
      <Route path="delete" element={<SERVICEMANAGEMENTDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default SERVICEMANAGEMENTRoutes;
