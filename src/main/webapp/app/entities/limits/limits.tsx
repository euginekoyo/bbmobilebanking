import React, { useEffect, useState } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortDown, faSortUp } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './limits.reducer';

export const LIMITS = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const lIMITSList = useAppSelector(state => state.lIMITS.entities);
  const loading = useAppSelector(state => state.lIMITS.loading);

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
      <h2 id="limits-heading" data-cy="LIMITSHeading">
        <Translate contentKey="bbMobileBankingAdminApp.lIMITS.home.title">LIMITS</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="bbMobileBankingAdminApp.lIMITS.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/limits/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="bbMobileBankingAdminApp.lIMITS.home.createLabel">Create new LIMITS</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {lIMITSList && lIMITSList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="bbMobileBankingAdminApp.lIMITS.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('tRANSACTIONTYPE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.lIMITS.tRANSACTIONTYPE">T RANSACTIONTYPE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('tRANSACTIONTYPE')} />
                </th>
                <th className="hand" onClick={sort('pROCODE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.lIMITS.pROCODE">P ROCODE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pROCODE')} />
                </th>
                <th className="hand" onClick={sort('cHANNEL')}>
                  <Translate contentKey="bbMobileBankingAdminApp.lIMITS.cHANNEL">C HANNEL</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cHANNEL')} />
                </th>
                <th className="hand" onClick={sort('tRANSACTIONLIMIT')}>
                  <Translate contentKey="bbMobileBankingAdminApp.lIMITS.tRANSACTIONLIMIT">T RANSACTIONLIMIT</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('tRANSACTIONLIMIT')} />
                </th>
                <th className="hand" onClick={sort('dAILYLIMIT')}>
                  <Translate contentKey="bbMobileBankingAdminApp.lIMITS.dAILYLIMIT">D AILYLIMIT</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dAILYLIMIT')} />
                </th>
                <th className="hand" onClick={sort('rEGISTEREDBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.lIMITS.rEGISTEREDBY">R EGISTEREDBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rEGISTEREDBY')} />
                </th>
                <th className="hand" onClick={sort('rEGISTEREDDATE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.lIMITS.rEGISTEREDDATE">R EGISTEREDDATE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rEGISTEREDDATE')} />
                </th>
                <th className="hand" onClick={sort('aPPROVED')}>
                  <Translate contentKey="bbMobileBankingAdminApp.lIMITS.aPPROVED">A PPROVED</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aPPROVED')} />
                </th>
                <th className="hand" onClick={sort('aPPROVEDBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.lIMITS.aPPROVEDBY">A PPROVEDBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aPPROVEDBY')} />
                </th>
                <th className="hand" onClick={sort('aPPROVEDDATE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.lIMITS.aPPROVEDDATE">A PPROVEDDATE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aPPROVEDDATE')} />
                </th>
                <th className="hand" onClick={sort('uPDATEDBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.lIMITS.uPDATEDBY">U PDATEDBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('uPDATEDBY')} />
                </th>
                <th className="hand" onClick={sort('uPDATEDDATE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.lIMITS.uPDATEDDATE">U PDATEDDATE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('uPDATEDDATE')} />
                </th>
                <th className="hand" onClick={sort('rEWORK')}>
                  <Translate contentKey="bbMobileBankingAdminApp.lIMITS.rEWORK">R EWORK</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rEWORK')} />
                </th>
                <th className="hand" onClick={sort('rEWORKBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.lIMITS.rEWORKBY">R EWORKBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rEWORKBY')} />
                </th>
                <th className="hand" onClick={sort('sESSIONID')}>
                  <Translate contentKey="bbMobileBankingAdminApp.lIMITS.sESSIONID">S ESSIONID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('sESSIONID')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {lIMITSList.map((lIMITS, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/limits/${lIMITS.id}`} color="link" size="sm">
                      {lIMITS.id}
                    </Button>
                  </td>
                  <td>{lIMITS.tRANSACTIONTYPE}</td>
                  <td>{lIMITS.pROCODE}</td>
                  <td>{lIMITS.cHANNEL}</td>
                  <td>{lIMITS.tRANSACTIONLIMIT}</td>
                  <td>{lIMITS.dAILYLIMIT}</td>
                  <td>{lIMITS.rEGISTEREDBY}</td>
                  <td>{lIMITS.rEGISTEREDDATE}</td>
                  <td>{lIMITS.aPPROVED}</td>
                  <td>{lIMITS.aPPROVEDBY}</td>
                  <td>{lIMITS.aPPROVEDDATE}</td>
                  <td>{lIMITS.uPDATEDBY}</td>
                  <td>{lIMITS.uPDATEDDATE}</td>
                  <td>{lIMITS.rEWORK}</td>
                  <td>{lIMITS.rEWORKBY}</td>
                  <td>{lIMITS.sESSIONID}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/limits/${lIMITS.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/limits/${lIMITS.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/limits/${lIMITS.id}/delete`)}
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
              <Translate contentKey="bbMobileBankingAdminApp.lIMITS.home.notFound">No LIMITS found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default LIMITS;
