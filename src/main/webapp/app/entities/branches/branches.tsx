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

import { getEntities } from './branches.reducer';

export const BRANCHES = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const bRANCHESList = useAppSelector(state => state.bRANCHES.entities);
  const loading = useAppSelector(state => state.bRANCHES.loading);

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
      <h2 id="branches-heading" data-cy="BRANCHESHeading">
        <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.home.title">BRANCHES</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/branches/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.home.createLabel">Create new BRANCHES</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {bRANCHESList && bRANCHESList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('bRANCHNAME')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.bRANCHNAME">B RANCHNAME</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('bRANCHNAME')} />
                </th>
                <th className="hand" onClick={sort('bRANCHCODE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.bRANCHCODE">B RANCHCODE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('bRANCHCODE')} />
                </th>
                <th className="hand" onClick={sort('aPPROVED')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.aPPROVED">A PPROVED</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aPPROVED')} />
                </th>
                <th className="hand" onClick={sort('eMAIL')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.eMAIL">E MAIL</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('eMAIL')} />
                </th>
                <th className="hand" onClick={sort('aDDRESS')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.aDDRESS">A DDRESS</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aDDRESS')} />
                </th>
                <th className="hand" onClick={sort('pHONE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.pHONE">P HONE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pHONE')} />
                </th>
                <th className="hand" onClick={sort('lOCATION')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.lOCATION">L OCATION</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('lOCATION')} />
                </th>
                <th className="hand" onClick={sort('cONTACTPERSON')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.cONTACTPERSON">C ONTACTPERSON</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cONTACTPERSON')} />
                </th>
                <th className="hand" onClick={sort('rEMARKS')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.rEMARKS">R EMARKS</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rEMARKS')} />
                </th>
                <th className="hand" onClick={sort('cREATEDBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.cREATEDBY">C REATEDBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cREATEDBY')} />
                </th>
                <th className="hand" onClick={sort('cREATEDON')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.cREATEDON">C REATEDON</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cREATEDON')} />
                </th>
                <th className="hand" onClick={sort('aPPROVEDBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.aPPROVEDBY">A PPROVEDBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aPPROVEDBY')} />
                </th>
                <th className="hand" onClick={sort('aPPROVEDON')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.aPPROVEDON">A PPROVEDON</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aPPROVEDON')} />
                </th>
                <th className="hand" onClick={sort('cHECKERREMARKS')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.cHECKERREMARKS">C HECKERREMARKS</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cHECKERREMARKS')} />
                </th>
                <th className="hand" onClick={sort('dELETEDBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.dELETEDBY">D ELETEDBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dELETEDBY')} />
                </th>
                <th className="hand" onClick={sort('dELETEDON')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.dELETEDON">D ELETEDON</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dELETEDON')} />
                </th>
                <th className="hand" onClick={sort('dELETEREMARKS')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.dELETEREMARKS">D ELETEREMARKS</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dELETEREMARKS')} />
                </th>
                <th className="hand" onClick={sort('dELETED')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.dELETED">D ELETED</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dELETED')} />
                </th>
                <th className="hand" onClick={sort('dECLINED')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.dECLINED">D ECLINED</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dECLINED')} />
                </th>
                <th className="hand" onClick={sort('dECLINEDDON')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.dECLINEDDON">D ECLINEDDON</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dECLINEDDON')} />
                </th>
                <th className="hand" onClick={sort('dECLINEDBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.dECLINEDBY">D ECLINEDBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dECLINEDBY')} />
                </th>
                <th className="hand" onClick={sort('sESSIONID')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.sESSIONID">S ESSIONID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('sESSIONID')} />
                </th>
                <th className="hand" onClick={sort('rEWORKED')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.rEWORKED">R EWORKED</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rEWORKED')} />
                </th>
                <th className="hand" onClick={sort('rEWORKEDBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.rEWORKEDBY">R EWORKEDBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rEWORKEDBY')} />
                </th>
                <th className="hand" onClick={sort('rEWORKEDON')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.rEWORKEDON">R EWORKEDON</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rEWORKEDON')} />
                </th>
                <th className="hand" onClick={sort('dISTRICT')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.dISTRICT">D ISTRICT</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dISTRICT')} />
                </th>
                <th className="hand" onClick={sort('rEGION')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.rEGION">R EGION</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rEGION')} />
                </th>
                <th className="hand" onClick={sort('rEGIONNAME')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.rEGIONNAME">R EGIONNAME</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rEGIONNAME')} />
                </th>
                <th className="hand" onClick={sort('rEPORTING')}>
                  <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.rEPORTING">R EPORTING</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rEPORTING')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {bRANCHESList.map((bRANCHES, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/branches/${bRANCHES.id}`} color="link" size="sm">
                      {bRANCHES.id}
                    </Button>
                  </td>
                  <td>{bRANCHES.bRANCHNAME}</td>
                  <td>{bRANCHES.bRANCHCODE}</td>
                  <td>{bRANCHES.aPPROVED}</td>
                  <td>{bRANCHES.eMAIL}</td>
                  <td>{bRANCHES.aDDRESS}</td>
                  <td>{bRANCHES.pHONE}</td>
                  <td>{bRANCHES.lOCATION}</td>
                  <td>{bRANCHES.cONTACTPERSON}</td>
                  <td>{bRANCHES.rEMARKS}</td>
                  <td>{bRANCHES.cREATEDBY}</td>
                  <td>{bRANCHES.cREATEDON ? <TextFormat type="date" value={bRANCHES.cREATEDON} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{bRANCHES.aPPROVEDBY}</td>
                  <td>{bRANCHES.aPPROVEDON}</td>
                  <td>{bRANCHES.cHECKERREMARKS}</td>
                  <td>{bRANCHES.dELETEDBY}</td>
                  <td>{bRANCHES.dELETEDON ? <TextFormat type="date" value={bRANCHES.dELETEDON} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{bRANCHES.dELETEREMARKS}</td>
                  <td>{bRANCHES.dELETED}</td>
                  <td>{bRANCHES.dECLINED}</td>
                  <td>{bRANCHES.dECLINEDDON}</td>
                  <td>{bRANCHES.dECLINEDBY}</td>
                  <td>{bRANCHES.sESSIONID}</td>
                  <td>{bRANCHES.rEWORKED}</td>
                  <td>{bRANCHES.rEWORKEDBY}</td>
                  <td>{bRANCHES.rEWORKEDON ? <TextFormat type="date" value={bRANCHES.rEWORKEDON} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{bRANCHES.dISTRICT}</td>
                  <td>{bRANCHES.rEGION}</td>
                  <td>{bRANCHES.rEGIONNAME}</td>
                  <td>{bRANCHES.rEPORTING}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/branches/${bRANCHES.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/branches/${bRANCHES.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/branches/${bRANCHES.id}/delete`)}
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
              <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.home.notFound">No BRANCHES found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default BRANCHES;
