import React, { useEffect, useState } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortDown, faSortUp } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './pinresethistory.reducer';

export const PINRESETHISTORY = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const pINRESETHISTORYList = useAppSelector(state => state.pINRESETHISTORY.entities);
  const loading = useAppSelector(state => state.pINRESETHISTORY.loading);

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
      <h2 id="pinresethistory-heading" data-cy="PINRESETHISTORYHeading">
        <Translate contentKey="bbMobileBankingAdminApp.pINRESETHISTORY.home.title">PINRESETHISTORIES</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="bbMobileBankingAdminApp.pINRESETHISTORY.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/pinresethistory/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="bbMobileBankingAdminApp.pINRESETHISTORY.home.createLabel">Create new PINRESETHISTORY</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {pINRESETHISTORYList && pINRESETHISTORYList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="bbMobileBankingAdminApp.pINRESETHISTORY.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('pHONENUMBER')}>
                  <Translate contentKey="bbMobileBankingAdminApp.pINRESETHISTORY.pHONENUMBER">P HONENUMBER</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pHONENUMBER')} />
                </th>
                <th className="hand" onClick={sort('cUSTOMERNAME')}>
                  <Translate contentKey="bbMobileBankingAdminApp.pINRESETHISTORY.cUSTOMERNAME">C USTOMERNAME</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cUSTOMERNAME')} />
                </th>
                <th className="hand" onClick={sort('pINBLOCKEDON')}>
                  <Translate contentKey="bbMobileBankingAdminApp.pINRESETHISTORY.pINBLOCKEDON">P INBLOCKEDON</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pINBLOCKEDON')} />
                </th>
                <th className="hand" onClick={sort('pINBLOCKREMARKS')}>
                  <Translate contentKey="bbMobileBankingAdminApp.pINRESETHISTORY.pINBLOCKREMARKS">P INBLOCKREMARKS</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pINBLOCKREMARKS')} />
                </th>
                <th className="hand" onClick={sort('pINRESETBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.pINRESETHISTORY.pINRESETBY">P INRESETBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pINRESETBY')} />
                </th>
                <th className="hand" onClick={sort('pINRESETON')}>
                  <Translate contentKey="bbMobileBankingAdminApp.pINRESETHISTORY.pINRESETON">P INRESETON</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pINRESETON')} />
                </th>
                <th className="hand" onClick={sort('pINRESETAPPROVEDBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.pINRESETHISTORY.pINRESETAPPROVEDBY">P INRESETAPPROVEDBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pINRESETAPPROVEDBY')} />
                </th>
                <th className="hand" onClick={sort('pINRESETAPPROVEDON')}>
                  <Translate contentKey="bbMobileBankingAdminApp.pINRESETHISTORY.pINRESETAPPROVEDON">P INRESETAPPROVEDON</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pINRESETAPPROVEDON')} />
                </th>
                <th className="hand" onClick={sort('pINRESETREMARKS')}>
                  <Translate contentKey="bbMobileBankingAdminApp.pINRESETHISTORY.pINRESETREMARKS">P INRESETREMARKS</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pINRESETREMARKS')} />
                </th>
                <th className="hand" onClick={sort('bRANCHCODE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.pINRESETHISTORY.bRANCHCODE">B RANCHCODE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('bRANCHCODE')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {pINRESETHISTORYList.map((pINRESETHISTORY, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/pinresethistory/${pINRESETHISTORY.id}`} color="link" size="sm">
                      {pINRESETHISTORY.id}
                    </Button>
                  </td>
                  <td>{pINRESETHISTORY.pHONENUMBER}</td>
                  <td>{pINRESETHISTORY.cUSTOMERNAME}</td>
                  <td>{pINRESETHISTORY.pINBLOCKEDON}</td>
                  <td>{pINRESETHISTORY.pINBLOCKREMARKS}</td>
                  <td>{pINRESETHISTORY.pINRESETBY}</td>
                  <td>{pINRESETHISTORY.pINRESETON}</td>
                  <td>{pINRESETHISTORY.pINRESETAPPROVEDBY}</td>
                  <td>{pINRESETHISTORY.pINRESETAPPROVEDON}</td>
                  <td>{pINRESETHISTORY.pINRESETREMARKS}</td>
                  <td>{pINRESETHISTORY.bRANCHCODE}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/pinresethistory/${pINRESETHISTORY.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/pinresethistory/${pINRESETHISTORY.id}/edit`}
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
                        onClick={() => (window.location.href = `/pinresethistory/${pINRESETHISTORY.id}/delete`)}
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
              <Translate contentKey="bbMobileBankingAdminApp.pINRESETHISTORY.home.notFound">No PINRESETHISTORIES found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default PINRESETHISTORY;
