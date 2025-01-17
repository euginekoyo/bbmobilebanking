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

import { getEntities } from './billers.reducer';

export const BILLERS = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const bILLERSList = useAppSelector(state => state.bILLERS.entities);
  const loading = useAppSelector(state => state.bILLERS.loading);

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
      <h2 id="billers-heading" data-cy="BILLERSHeading">
        <Translate contentKey="bbMobileBankingAdminApp.bILLERS.home.title">BILLERS</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="bbMobileBankingAdminApp.bILLERS.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/billers/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="bbMobileBankingAdminApp.bILLERS.home.createLabel">Create new BILLERS</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {bILLERSList && bILLERSList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bILLERS.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('bILLERID')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bILLERS.bILLERID">B ILLERID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('bILLERID')} />
                </th>
                <th className="hand" onClick={sort('dESCRIPTION')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bILLERS.dESCRIPTION">D ESCRIPTION</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dESCRIPTION')} />
                </th>
                <th className="hand" onClick={sort('bILLERCOLLECTIONACCOUNT')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bILLERS.bILLERCOLLECTIONACCOUNT">B ILLERCOLLECTIONACCOUNT</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('bILLERCOLLECTIONACCOUNT')} />
                </th>
                <th className="hand" onClick={sort('dATECREATED')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bILLERS.dATECREATED">D ATECREATED</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dATECREATED')} />
                </th>
                <th className="hand" onClick={sort('cREATEDBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bILLERS.cREATEDBY">C REATEDBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cREATEDBY')} />
                </th>
                <th className="hand" onClick={sort('aPPROVED')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bILLERS.aPPROVED">A PPROVED</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aPPROVED')} />
                </th>
                <th className="hand" onClick={sort('aPPROVEDBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bILLERS.aPPROVEDBY">A PPROVEDBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aPPROVEDBY')} />
                </th>
                <th className="hand" onClick={sort('aPPROVEDDATE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bILLERS.aPPROVEDDATE">A PPROVEDDATE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aPPROVEDDATE')} />
                </th>
                <th className="hand" onClick={sort('cHARGABLEPRODUCTID')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bILLERS.cHARGABLEPRODUCTID">C HARGABLEPRODUCTID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cHARGABLEPRODUCTID')} />
                </th>
                <th className="hand" onClick={sort('nONCHARGABLEPRODUCTID')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bILLERS.nONCHARGABLEPRODUCTID">N ONCHARGABLEPRODUCTID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('nONCHARGABLEPRODUCTID')} />
                </th>
                <th className="hand" onClick={sort('uSDBILLERCOLLECTIONACCOUNT')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bILLERS.uSDBILLERCOLLECTIONACCOUNT">U SDBILLERCOLLECTIONACCOUNT</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('uSDBILLERCOLLECTIONACCOUNT')} />
                </th>
                <th className="hand" onClick={sort('eNABLEDUPLICATECHECK')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bILLERS.eNABLEDUPLICATECHECK">E NABLEDUPLICATECHECK</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('eNABLEDUPLICATECHECK')} />
                </th>
                <th className="hand" onClick={sort('rEMARKS')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bILLERS.rEMARKS">R EMARKS</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rEMARKS')} />
                </th>
                <th className="hand" onClick={sort('sESSIONID')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bILLERS.sESSIONID">S ESSIONID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('sESSIONID')} />
                </th>
                <th className="hand" onClick={sort('rEWORKBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bILLERS.rEWORKBY">R EWORKBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rEWORKBY')} />
                </th>
                <th className="hand" onClick={sort('sTATUS')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bILLERS.sTATUS">S TATUS</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('sTATUS')} />
                </th>
                <th className="hand" onClick={sort('aCTIVE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bILLERS.aCTIVE">A CTIVE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aCTIVE')} />
                </th>
                <th className="hand" onClick={sort('rEWORK')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bILLERS.rEWORK">R EWORK</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rEWORK')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {bILLERSList.map((bILLERS, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/billers/${bILLERS.id}`} color="link" size="sm">
                      {bILLERS.id}
                    </Button>
                  </td>
                  <td>{bILLERS.bILLERID}</td>
                  <td>{bILLERS.dESCRIPTION}</td>
                  <td>{bILLERS.bILLERCOLLECTIONACCOUNT}</td>
                  <td>{bILLERS.dATECREATED ? <TextFormat type="date" value={bILLERS.dATECREATED} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{bILLERS.cREATEDBY}</td>
                  <td>{bILLERS.aPPROVED}</td>
                  <td>{bILLERS.aPPROVEDBY}</td>
                  <td>{bILLERS.aPPROVEDDATE ? <TextFormat type="date" value={bILLERS.aPPROVEDDATE} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{bILLERS.cHARGABLEPRODUCTID}</td>
                  <td>{bILLERS.nONCHARGABLEPRODUCTID}</td>
                  <td>{bILLERS.uSDBILLERCOLLECTIONACCOUNT}</td>
                  <td>{bILLERS.eNABLEDUPLICATECHECK}</td>
                  <td>{bILLERS.rEMARKS}</td>
                  <td>{bILLERS.sESSIONID}</td>
                  <td>{bILLERS.rEWORKBY}</td>
                  <td>{bILLERS.sTATUS}</td>
                  <td>{bILLERS.aCTIVE}</td>
                  <td>{bILLERS.rEWORK}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/billers/${bILLERS.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/billers/${bILLERS.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/billers/${bILLERS.id}/delete`)}
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
              <Translate contentKey="bbMobileBankingAdminApp.bILLERS.home.notFound">No BILLERS found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default BILLERS;
