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

import { getEntities } from './charge.reducer';

export const CHARGE = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const cHARGEList = useAppSelector(state => state.cHARGE.entities);
  const loading = useAppSelector(state => state.cHARGE.loading);

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
      <h2 id="charge-heading" data-cy="CHARGEHeading">
        <Translate contentKey="bbMobileBankingAdminApp.cHARGE.home.title">CHARGES</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="bbMobileBankingAdminApp.cHARGE.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/charge/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="bbMobileBankingAdminApp.cHARGE.home.createLabel">Create new CHARGE</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {cHARGEList && cHARGEList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cHARGE.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('tXNTYPE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cHARGE.tXNTYPE">T XNTYPE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('tXNTYPE')} />
                </th>
                <th className="hand" onClick={sort('fEEMODE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cHARGE.fEEMODE">F EEMODE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('fEEMODE')} />
                </th>
                <th className="hand" onClick={sort('aMOUNT')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cHARGE.aMOUNT">A MOUNT</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aMOUNT')} />
                </th>
                <th className="hand" onClick={sort('dATECREATED')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cHARGE.dATECREATED">D ATECREATED</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dATECREATED')} />
                </th>
                <th className="hand" onClick={sort('cREATEDBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cHARGE.cREATEDBY">C REATEDBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cREATEDBY')} />
                </th>
                <th className="hand" onClick={sort('aPPROVED')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cHARGE.aPPROVED">A PPROVED</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aPPROVED')} />
                </th>
                <th className="hand" onClick={sort('aPPROVEDBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cHARGE.aPPROVEDBY">A PPROVEDBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aPPROVEDBY')} />
                </th>
                <th className="hand" onClick={sort('cHANNEL')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cHARGE.cHANNEL">C HANNEL</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cHANNEL')} />
                </th>
                <th className="hand" onClick={sort('tXNCODE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cHARGE.tXNCODE">T XNCODE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('tXNCODE')} />
                </th>
                <th className="hand" onClick={sort('dESCRIPTION')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cHARGE.dESCRIPTION">D ESCRIPTION</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dESCRIPTION')} />
                </th>
                <th className="hand" onClick={sort('aPPROVEDDATE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cHARGE.aPPROVEDDATE">A PPROVEDDATE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aPPROVEDDATE')} />
                </th>
                <th>
                  <Translate contentKey="bbMobileBankingAdminApp.cHARGE.cHARGERANGES">C HARGERANGES</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="bbMobileBankingAdminApp.cHARGE.rANGE">R ANGE</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {cHARGEList.map((cHARGE, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/charge/${cHARGE.id}`} color="link" size="sm">
                      {cHARGE.id}
                    </Button>
                  </td>
                  <td>{cHARGE.tXNTYPE}</td>
                  <td>{cHARGE.fEEMODE}</td>
                  <td>{cHARGE.aMOUNT}</td>
                  <td>{cHARGE.dATECREATED ? <TextFormat type="date" value={cHARGE.dATECREATED} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{cHARGE.cREATEDBY}</td>
                  <td>{cHARGE.aPPROVED}</td>
                  <td>{cHARGE.aPPROVEDBY}</td>
                  <td>{cHARGE.cHANNEL}</td>
                  <td>{cHARGE.tXNCODE}</td>
                  <td>{cHARGE.dESCRIPTION}</td>
                  <td>{cHARGE.aPPROVEDDATE ? <TextFormat type="date" value={cHARGE.aPPROVEDDATE} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{cHARGE.cHARGERANGES ? <Link to={`/chargeranges/${cHARGE.cHARGERANGES.id}`}>{cHARGE.cHARGERANGES.id}</Link> : ''}</td>
                  <td>{cHARGE.rANGE ? <Link to={`/range/${cHARGE.rANGE.id}`}>{cHARGE.rANGE.id}</Link> : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/charge/${cHARGE.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/charge/${cHARGE.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/charge/${cHARGE.id}/delete`)}
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
              <Translate contentKey="bbMobileBankingAdminApp.cHARGE.home.notFound">No CHARGES found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default CHARGE;
