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

import { getEntities } from './messagetemplates.reducer';

export const MESSAGETEMPLATES = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const mESSAGETEMPLATESList = useAppSelector(state => state.mESSAGETEMPLATES.entities);
  const loading = useAppSelector(state => state.mESSAGETEMPLATES.loading);

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
      <h2 id="messagetemplates-heading" data-cy="MESSAGETEMPLATESHeading">
        <Translate contentKey="bbMobileBankingAdminApp.mESSAGETEMPLATES.home.title">MESSAGETEMPLATES</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="bbMobileBankingAdminApp.mESSAGETEMPLATES.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/messagetemplates/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="bbMobileBankingAdminApp.mESSAGETEMPLATES.home.createLabel">Create new MESSAGETEMPLATES</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {mESSAGETEMPLATESList && mESSAGETEMPLATESList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="bbMobileBankingAdminApp.mESSAGETEMPLATES.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('mESSAGETYPE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.mESSAGETEMPLATES.mESSAGETYPE">M ESSAGETYPE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('mESSAGETYPE')} />
                </th>
                <th className="hand" onClick={sort('dESCRIPTION')}>
                  <Translate contentKey="bbMobileBankingAdminApp.mESSAGETEMPLATES.dESCRIPTION">D ESCRIPTION</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dESCRIPTION')} />
                </th>
                <th className="hand" onClick={sort('mESSAGEENGLISH')}>
                  <Translate contentKey="bbMobileBankingAdminApp.mESSAGETEMPLATES.mESSAGEENGLISH">M ESSAGEENGLISH</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('mESSAGEENGLISH')} />
                </th>
                <th className="hand" onClick={sort('mESSAGESOMALI')}>
                  <Translate contentKey="bbMobileBankingAdminApp.mESSAGETEMPLATES.mESSAGESOMALI">M ESSAGESOMALI</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('mESSAGESOMALI')} />
                </th>
                <th className="hand" onClick={sort('cREATEDON')}>
                  <Translate contentKey="bbMobileBankingAdminApp.mESSAGETEMPLATES.cREATEDON">C REATEDON</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cREATEDON')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {mESSAGETEMPLATESList.map((mESSAGETEMPLATES, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/messagetemplates/${mESSAGETEMPLATES.id}`} color="link" size="sm">
                      {mESSAGETEMPLATES.id}
                    </Button>
                  </td>
                  <td>{mESSAGETEMPLATES.mESSAGETYPE}</td>
                  <td>{mESSAGETEMPLATES.dESCRIPTION}</td>
                  <td>{mESSAGETEMPLATES.mESSAGEENGLISH}</td>
                  <td>{mESSAGETEMPLATES.mESSAGESOMALI}</td>
                  <td>
                    {mESSAGETEMPLATES.cREATEDON ? (
                      <TextFormat type="date" value={mESSAGETEMPLATES.cREATEDON} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/messagetemplates/${mESSAGETEMPLATES.id}`}
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
                        to={`/messagetemplates/${mESSAGETEMPLATES.id}/edit`}
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
                        onClick={() => (window.location.href = `/messagetemplates/${mESSAGETEMPLATES.id}/delete`)}
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
              <Translate contentKey="bbMobileBankingAdminApp.mESSAGETEMPLATES.home.notFound">No MESSAGETEMPLATES found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default MESSAGETEMPLATES;
