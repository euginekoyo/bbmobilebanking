import React, { useEffect, useState } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { TextFormat, Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortDown, faSortUp } from '@fortawesome/free-solid-svg-icons';
import { APP_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './servicemanagement.reducer';

export const SERVICEMANAGEMENT = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const sERVICEMANAGEMENTList = useAppSelector(state => state.sERVICEMANAGEMENT.entities);
  const loading = useAppSelector(state => state.sERVICEMANAGEMENT.loading);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        sort: `${sortState.sort},${sortState.order}`,
      }),
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?sort=${sortState.sort},${sortState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [sortState.order, sortState.sort]);

  const sort = p => () => {
    setSortState({
      ...sortState,
      order: sortState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handleSyncList = () => {
    sortEntities();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = sortState.sort;
    const order = sortState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    }
    return order === ASC ? faSortUp : faSortDown;
  };

  return (
    <div>
      <h2 id="servicemanagement-heading" data-cy="SERVICEMANAGEMENTHeading">
        <Translate contentKey="bbMobileBankingAdminApp.sERVICEMANAGEMENT.home.title">SERVICEMANAGEMENTS</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="bbMobileBankingAdminApp.sERVICEMANAGEMENT.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/servicemanagement/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="bbMobileBankingAdminApp.sERVICEMANAGEMENT.home.createLabel">Create new SERVICEMANAGEMENT</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {sERVICEMANAGEMENTList && sERVICEMANAGEMENTList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="bbMobileBankingAdminApp.sERVICEMANAGEMENT.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('pROCESSINGCODE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.sERVICEMANAGEMENT.pROCESSINGCODE">P ROCESSINGCODE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pROCESSINGCODE')} />
                </th>
                <th className="hand" onClick={sort('aCTIVE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.sERVICEMANAGEMENT.aCTIVE">A CTIVE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aCTIVE')} />
                </th>
                <th className="hand" onClick={sort('cREATEDBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.sERVICEMANAGEMENT.cREATEDBY">C REATEDBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cREATEDBY')} />
                </th>
                <th className="hand" onClick={sort('dATECREATED')}>
                  <Translate contentKey="bbMobileBankingAdminApp.sERVICEMANAGEMENT.dATECREATED">D ATECREATED</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dATECREATED')} />
                </th>
                <th className="hand" onClick={sort('aPPROVED')}>
                  <Translate contentKey="bbMobileBankingAdminApp.sERVICEMANAGEMENT.aPPROVED">A PPROVED</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aPPROVED')} />
                </th>
                <th className="hand" onClick={sort('aPPROVEDBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.sERVICEMANAGEMENT.aPPROVEDBY">A PPROVEDBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aPPROVEDBY')} />
                </th>
                <th className="hand" onClick={sort('aPPROVEDDATE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.sERVICEMANAGEMENT.aPPROVEDDATE">A PPROVEDDATE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aPPROVEDDATE')} />
                </th>
                <th className="hand" onClick={sort('aDAPTORTYPE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.sERVICEMANAGEMENT.aDAPTORTYPE">A DAPTORTYPE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aDAPTORTYPE')} />
                </th>
                <th className="hand" onClick={sort('dESTINATION')}>
                  <Translate contentKey="bbMobileBankingAdminApp.sERVICEMANAGEMENT.dESTINATION">D ESTINATION</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dESTINATION')} />
                </th>
                <th className="hand" onClick={sort('tHIRDPARTYRESPONSE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.sERVICEMANAGEMENT.tHIRDPARTYRESPONSE">T HIRDPARTYRESPONSE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('tHIRDPARTYRESPONSE')} />
                </th>
                <th className="hand" onClick={sort('tELCO')}>
                  <Translate contentKey="bbMobileBankingAdminApp.sERVICEMANAGEMENT.tELCO">T ELCO</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('tELCO')} />
                </th>
                <th className="hand" onClick={sort('dESCRIPTION')}>
                  <Translate contentKey="bbMobileBankingAdminApp.sERVICEMANAGEMENT.dESCRIPTION">D ESCRIPTION</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dESCRIPTION')} />
                </th>
                <th className="hand" onClick={sort('rEMARKS')}>
                  <Translate contentKey="bbMobileBankingAdminApp.sERVICEMANAGEMENT.rEMARKS">R EMARKS</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rEMARKS')} />
                </th>
                <th className="hand" onClick={sort('sESSIONID')}>
                  <Translate contentKey="bbMobileBankingAdminApp.sERVICEMANAGEMENT.sESSIONID">S ESSIONID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('sESSIONID')} />
                </th>
                <th className="hand" onClick={sort('rEWORKBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.sERVICEMANAGEMENT.rEWORKBY">R EWORKBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rEWORKBY')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {sERVICEMANAGEMENTList.map((sERVICEMANAGEMENT, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/servicemanagement/${sERVICEMANAGEMENT.id}`} color="link" size="sm">
                      {sERVICEMANAGEMENT.id}
                    </Button>
                  </td>
                  <td>{sERVICEMANAGEMENT.pROCESSINGCODE}</td>
                  <td>{sERVICEMANAGEMENT.aCTIVE}</td>
                  <td>{sERVICEMANAGEMENT.cREATEDBY}</td>
                  <td>
                    {sERVICEMANAGEMENT.dATECREATED ? (
                      <TextFormat type="date" value={sERVICEMANAGEMENT.dATECREATED} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{sERVICEMANAGEMENT.aPPROVED}</td>
                  <td>{sERVICEMANAGEMENT.aPPROVEDBY}</td>
                  <td>
                    {sERVICEMANAGEMENT.aPPROVEDDATE ? (
                      <TextFormat type="date" value={sERVICEMANAGEMENT.aPPROVEDDATE} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{sERVICEMANAGEMENT.aDAPTORTYPE}</td>
                  <td>{sERVICEMANAGEMENT.dESTINATION}</td>
                  <td>{sERVICEMANAGEMENT.tHIRDPARTYRESPONSE}</td>
                  <td>{sERVICEMANAGEMENT.tELCO}</td>
                  <td>{sERVICEMANAGEMENT.dESCRIPTION}</td>
                  <td>{sERVICEMANAGEMENT.rEMARKS}</td>
                  <td>{sERVICEMANAGEMENT.sESSIONID}</td>
                  <td>{sERVICEMANAGEMENT.rEWORKBY}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/servicemanagement/${sERVICEMANAGEMENT.id}`}
                        color="info"
                        size="sm"
                        data-cy="entityDetailsButton"
                      >
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/servicemanagement/${sERVICEMANAGEMENT.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/servicemanagement/${sERVICEMANAGEMENT.id}/delete`)}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="bbMobileBankingAdminApp.sERVICEMANAGEMENT.home.notFound">No SERVICEMANAGEMENTS found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default SERVICEMANAGEMENT;
