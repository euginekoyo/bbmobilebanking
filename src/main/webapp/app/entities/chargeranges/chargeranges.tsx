import React, { useEffect, useState } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortDown, faSortUp } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './chargeranges.reducer';

export const CHARGERANGES = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const cHARGERANGESList = useAppSelector(state => state.cHARGERANGES.entities);
  const loading = useAppSelector(state => state.cHARGERANGES.loading);

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
      <h2 id="chargeranges-heading" data-cy="CHARGERANGESHeading">
        <Translate contentKey="bbMobileBankingAdminApp.cHARGERANGES.home.title">CHARGERANGES</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="bbMobileBankingAdminApp.cHARGERANGES.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/chargeranges/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="bbMobileBankingAdminApp.cHARGERANGES.home.createLabel">Create new CHARGERANGES</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {cHARGERANGESList && cHARGERANGESList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cHARGERANGES.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('bILLERID')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cHARGERANGES.bILLERID">B ILLERID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('bILLERID')} />
                </th>
                <th className="hand" onClick={sort('pROCESSINGCODE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cHARGERANGES.pROCESSINGCODE">P ROCESSINGCODE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pROCESSINGCODE')} />
                </th>
                <th className="hand" onClick={sort('mAX')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cHARGERANGES.mAX">M AX</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('mAX')} />
                </th>
                <th className="hand" onClick={sort('mIN')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cHARGERANGES.mIN">M IN</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('mIN')} />
                </th>
                <th className="hand" onClick={sort('aMOUNT')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cHARGERANGES.aMOUNT">A MOUNT</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aMOUNT')} />
                </th>
                <th className="hand" onClick={sort('cREATEDBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cHARGERANGES.cREATEDBY">C REATEDBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cREATEDBY')} />
                </th>
                <th className="hand" onClick={sort('aPPROVEDBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cHARGERANGES.aPPROVEDBY">A PPROVEDBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aPPROVEDBY')} />
                </th>
                <th className="hand" onClick={sort('cREATEDAT')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cHARGERANGES.cREATEDAT">C REATEDAT</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cREATEDAT')} />
                </th>
                <th className="hand" onClick={sort('aPPROVEDON')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cHARGERANGES.aPPROVEDON">A PPROVEDON</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aPPROVEDON')} />
                </th>
                <th className="hand" onClick={sort('aPPROVED')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cHARGERANGES.aPPROVED">A PPROVED</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aPPROVED')} />
                </th>
                <th className="hand" onClick={sort('dECLINED')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cHARGERANGES.dECLINED">D ECLINED</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dECLINED')} />
                </th>
                <th className="hand" onClick={sort('dECLINEDBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cHARGERANGES.dECLINEDBY">D ECLINEDBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dECLINEDBY')} />
                </th>
                <th className="hand" onClick={sort('cHARGEID')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cHARGERANGES.cHARGEID">C HARGEID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cHARGEID')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {cHARGERANGESList.map((cHARGERANGES, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/chargeranges/${cHARGERANGES.id}`} color="link" size="sm">
                      {cHARGERANGES.id}
                    </Button>
                  </td>
                  <td>{cHARGERANGES.bILLERID}</td>
                  <td>{cHARGERANGES.pROCESSINGCODE}</td>
                  <td>{cHARGERANGES.mAX}</td>
                  <td>{cHARGERANGES.mIN}</td>
                  <td>{cHARGERANGES.aMOUNT}</td>
                  <td>{cHARGERANGES.cREATEDBY}</td>
                  <td>{cHARGERANGES.aPPROVEDBY}</td>
                  <td>{cHARGERANGES.cREATEDAT}</td>
                  <td>{cHARGERANGES.aPPROVEDON}</td>
                  <td>{cHARGERANGES.aPPROVED}</td>
                  <td>{cHARGERANGES.dECLINED}</td>
                  <td>{cHARGERANGES.dECLINEDBY}</td>
                  <td>{cHARGERANGES.cHARGEID}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/chargeranges/${cHARGERANGES.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/chargeranges/${cHARGERANGES.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/chargeranges/${cHARGERANGES.id}/delete`)}
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
              <Translate contentKey="bbMobileBankingAdminApp.cHARGERANGES.home.notFound">No CHARGERANGES found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default CHARGERANGES;
