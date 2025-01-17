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

import { getEntities } from './customeraccount.reducer';

export const CUSTOMERACCOUNT = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const cUSTOMERACCOUNTList = useAppSelector(state => state.cUSTOMERACCOUNT.entities);
  const loading = useAppSelector(state => state.cUSTOMERACCOUNT.loading);

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
      <h2 id="customeraccount-heading" data-cy="CUSTOMERACCOUNTHeading">
        <Translate contentKey="bbMobileBankingAdminApp.cUSTOMERACCOUNT.home.title">CUSTOMERACCOUNTS</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="bbMobileBankingAdminApp.cUSTOMERACCOUNT.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/customeraccount/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="bbMobileBankingAdminApp.cUSTOMERACCOUNT.home.createLabel">Create new CUSTOMERACCOUNT</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {cUSTOMERACCOUNTList && cUSTOMERACCOUNTList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMERACCOUNT.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('cUSTOMERID')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMERACCOUNT.cUSTOMERID">C USTOMERID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cUSTOMERID')} />
                </th>
                <th className="hand" onClick={sort('aCCOUNTNUMBER')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMERACCOUNT.aCCOUNTNUMBER">A CCOUNTNUMBER</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aCCOUNTNUMBER')} />
                </th>
                <th className="hand" onClick={sort('aCCOUNTCLASS')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMERACCOUNT.aCCOUNTCLASS">A CCOUNTCLASS</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aCCOUNTCLASS')} />
                </th>
                <th className="hand" onClick={sort('cUSTOMERNUMBER')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMERACCOUNT.cUSTOMERNUMBER">C USTOMERNUMBER</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cUSTOMERNUMBER')} />
                </th>
                <th className="hand" onClick={sort('cIF')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMERACCOUNT.cIF">C IF</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cIF')} />
                </th>
                <th className="hand" onClick={sort('tIMELINKED')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMERACCOUNT.tIMELINKED">T IMELINKED</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('tIMELINKED')} />
                </th>
                <th className="hand" onClick={sort('bLOCKED')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMERACCOUNT.bLOCKED">B LOCKED</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('bLOCKED')} />
                </th>
                <th className="hand" onClick={sort('sTOPPED')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMERACCOUNT.sTOPPED">S TOPPED</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('sTOPPED')} />
                </th>
                <th className="hand" onClick={sort('dORMANT')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMERACCOUNT.dORMANT">D ORMANT</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dORMANT')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {cUSTOMERACCOUNTList.map((cUSTOMERACCOUNT, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/customeraccount/${cUSTOMERACCOUNT.id}`} color="link" size="sm">
                      {cUSTOMERACCOUNT.id}
                    </Button>
                  </td>
                  <td>{cUSTOMERACCOUNT.cUSTOMERID}</td>
                  <td>{cUSTOMERACCOUNT.aCCOUNTNUMBER}</td>
                  <td>{cUSTOMERACCOUNT.aCCOUNTCLASS}</td>
                  <td>{cUSTOMERACCOUNT.cUSTOMERNUMBER}</td>
                  <td>{cUSTOMERACCOUNT.cIF}</td>
                  <td>
                    {cUSTOMERACCOUNT.tIMELINKED ? (
                      <TextFormat type="date" value={cUSTOMERACCOUNT.tIMELINKED} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{cUSTOMERACCOUNT.bLOCKED}</td>
                  <td>{cUSTOMERACCOUNT.sTOPPED}</td>
                  <td>{cUSTOMERACCOUNT.dORMANT}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/customeraccount/${cUSTOMERACCOUNT.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/customeraccount/${cUSTOMERACCOUNT.id}/edit`}
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
                        onClick={() => (window.location.href = `/customeraccount/${cUSTOMERACCOUNT.id}/delete`)}
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
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMERACCOUNT.home.notFound">No CUSTOMERACCOUNTS found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default CUSTOMERACCOUNT;
