import React, { useEffect, useState } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortDown, faSortUp } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './range.reducer';

export const RANGE = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const rANGEList = useAppSelector(state => state.rANGE.entities);
  const loading = useAppSelector(state => state.rANGE.loading);

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
      <h2 id="range-heading" data-cy="RANGEHeading">
        <Translate contentKey="bbMobileBankingAdminApp.rANGE.home.title">RANGES</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="bbMobileBankingAdminApp.rANGE.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/range/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="bbMobileBankingAdminApp.rANGE.home.createLabel">Create new RANGE</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {rANGEList && rANGEList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="bbMobileBankingAdminApp.rANGE.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('rANGEFROM')}>
                  <Translate contentKey="bbMobileBankingAdminApp.rANGE.rANGEFROM">R ANGEFROM</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rANGEFROM')} />
                </th>
                <th className="hand" onClick={sort('rANGETO')}>
                  <Translate contentKey="bbMobileBankingAdminApp.rANGE.rANGETO">R ANGETO</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rANGETO')} />
                </th>
                <th className="hand" onClick={sort('aMOUNT')}>
                  <Translate contentKey="bbMobileBankingAdminApp.rANGE.aMOUNT">A MOUNT</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aMOUNT')} />
                </th>
                <th className="hand" onClick={sort('tXNTYPE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.rANGE.tXNTYPE">T XNTYPE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('tXNTYPE')} />
                </th>
                <th className="hand" onClick={sort('tXNCODE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.rANGE.tXNCODE">T XNCODE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('tXNCODE')} />
                </th>
                <th className="hand" onClick={sort('cHARGEID')}>
                  <Translate contentKey="bbMobileBankingAdminApp.rANGE.cHARGEID">C HARGEID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cHARGEID')} />
                </th>
                <th className="hand" onClick={sort('cHANNEL')}>
                  <Translate contentKey="bbMobileBankingAdminApp.rANGE.cHANNEL">C HANNEL</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cHANNEL')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {rANGEList.map((rANGE, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/range/${rANGE.id}`} color="link" size="sm">
                      {rANGE.id}
                    </Button>
                  </td>
                  <td>{rANGE.rANGEFROM}</td>
                  <td>{rANGE.rANGETO}</td>
                  <td>{rANGE.aMOUNT}</td>
                  <td>{rANGE.tXNTYPE}</td>
                  <td>{rANGE.tXNCODE}</td>
                  <td>{rANGE.cHARGEID}</td>
                  <td>{rANGE.cHANNEL}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/range/${rANGE.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/range/${rANGE.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/range/${rANGE.id}/delete`)}
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
              <Translate contentKey="bbMobileBankingAdminApp.rANGE.home.notFound">No RANGES found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default RANGE;
