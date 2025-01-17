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

import { getEntities } from './requests.reducer';

export const REQUESTS = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const rEQUESTSList = useAppSelector(state => state.rEQUESTS.entities);
  const loading = useAppSelector(state => state.rEQUESTS.loading);

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
      <h2 id="requests-heading" data-cy="REQUESTSHeading">
        <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.home.title">REQUESTS</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/requests/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.home.createLabel">Create new REQUESTS</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {rEQUESTSList && rEQUESTSList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('mOBILENUMBER')}>
                  <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.mOBILENUMBER">M OBILENUMBER</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('mOBILENUMBER')} />
                </th>
                <th className="hand" onClick={sort('aCCOUNTNO')}>
                  <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.aCCOUNTNO">A CCOUNTNO</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aCCOUNTNO')} />
                </th>
                <th className="hand" onClick={sort('cURRENCY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.cURRENCY">C URRENCY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cURRENCY')} />
                </th>
                <th className="hand" onClick={sort('cIF')}>
                  <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.cIF">C IF</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cIF')} />
                </th>
                <th className="hand" onClick={sort('rEQUESTTYPE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.rEQUESTTYPE">R EQUESTTYPE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rEQUESTTYPE')} />
                </th>
                <th className="hand" onClick={sort('rEQUESTCHARGE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.rEQUESTCHARGE">R EQUESTCHARGE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rEQUESTCHARGE')} />
                </th>
                <th className="hand" onClick={sort('rEQUESTSTATUS')}>
                  <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.rEQUESTSTATUS">R EQUESTSTATUS</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rEQUESTSTATUS')} />
                </th>
                <th className="hand" onClick={sort('dATEREQUESTED')}>
                  <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.dATEREQUESTED">D ATEREQUESTED</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dATEREQUESTED')} />
                </th>
                <th className="hand" onClick={sort('tRNREFNO')}>
                  <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.tRNREFNO">T RNREFNO</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('tRNREFNO')} />
                </th>
                <th className="hand" onClick={sort('nOOFBOOKS')}>
                  <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.nOOFBOOKS">N OOFBOOKS</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('nOOFBOOKS')} />
                </th>
                <th className="hand" onClick={sort('nOOFLEAVES')}>
                  <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.nOOFLEAVES">N OOFLEAVES</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('nOOFLEAVES')} />
                </th>
                <th className="hand" onClick={sort('aPPROVED')}>
                  <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.aPPROVED">A PPROVED</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aPPROVED')} />
                </th>
                <th className="hand" onClick={sort('cHANNEL')}>
                  <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.cHANNEL">C HANNEL</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cHANNEL')} />
                </th>
                <th className="hand" onClick={sort('aPPROVEDBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.aPPROVEDBY">A PPROVEDBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aPPROVEDBY')} />
                </th>
                <th className="hand" onClick={sort('aPPROVEDON')}>
                  <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.aPPROVEDON">A PPROVEDON</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aPPROVEDON')} />
                </th>
                <th className="hand" onClick={sort('cHECKERREMARKS')}>
                  <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.cHECKERREMARKS">C HECKERREMARKS</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cHECKERREMARKS')} />
                </th>
                <th className="hand" onClick={sort('rESPCODE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.rESPCODE">R ESPCODE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rESPCODE')} />
                </th>
                <th className="hand" onClick={sort('rESPDESCRIPTION')}>
                  <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.rESPDESCRIPTION">R ESPDESCRIPTION</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rESPDESCRIPTION')} />
                </th>
                <th className="hand" onClick={sort('dATERESPONDED')}>
                  <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.dATERESPONDED">D ATERESPONDED</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dATERESPONDED')} />
                </th>
                <th className="hand" onClick={sort('cUSTOMERNAME')}>
                  <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.cUSTOMERNAME">C USTOMERNAME</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cUSTOMERNAME')} />
                </th>
                <th className="hand" onClick={sort('rEJECTED')}>
                  <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.rEJECTED">R EJECTED</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rEJECTED')} />
                </th>
                <th className="hand" onClick={sort('rEJECTEDBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.rEJECTEDBY">R EJECTEDBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rEJECTEDBY')} />
                </th>
                <th className="hand" onClick={sort('rEJECTEDON')}>
                  <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.rEJECTEDON">R EJECTEDON</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rEJECTEDON')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {rEQUESTSList.map((rEQUESTS, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/requests/${rEQUESTS.id}`} color="link" size="sm">
                      {rEQUESTS.id}
                    </Button>
                  </td>
                  <td>{rEQUESTS.mOBILENUMBER}</td>
                  <td>{rEQUESTS.aCCOUNTNO}</td>
                  <td>{rEQUESTS.cURRENCY}</td>
                  <td>{rEQUESTS.cIF}</td>
                  <td>{rEQUESTS.rEQUESTTYPE}</td>
                  <td>{rEQUESTS.rEQUESTCHARGE}</td>
                  <td>{rEQUESTS.rEQUESTSTATUS}</td>
                  <td>
                    {rEQUESTS.dATEREQUESTED ? <TextFormat type="date" value={rEQUESTS.dATEREQUESTED} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{rEQUESTS.tRNREFNO}</td>
                  <td>{rEQUESTS.nOOFBOOKS}</td>
                  <td>{rEQUESTS.nOOFLEAVES}</td>
                  <td>{rEQUESTS.aPPROVED}</td>
                  <td>{rEQUESTS.cHANNEL}</td>
                  <td>{rEQUESTS.aPPROVEDBY}</td>
                  <td>{rEQUESTS.aPPROVEDON ? <TextFormat type="date" value={rEQUESTS.aPPROVEDON} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{rEQUESTS.cHECKERREMARKS}</td>
                  <td>{rEQUESTS.rESPCODE}</td>
                  <td>{rEQUESTS.rESPDESCRIPTION}</td>
                  <td>
                    {rEQUESTS.dATERESPONDED ? <TextFormat type="date" value={rEQUESTS.dATERESPONDED} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{rEQUESTS.cUSTOMERNAME}</td>
                  <td>{rEQUESTS.rEJECTED}</td>
                  <td>{rEQUESTS.rEJECTEDBY}</td>
                  <td>{rEQUESTS.rEJECTEDON ? <TextFormat type="date" value={rEQUESTS.rEJECTEDON} format={APP_DATE_FORMAT} /> : null}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/requests/${rEQUESTS.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/requests/${rEQUESTS.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/requests/${rEQUESTS.id}/delete`)}
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
              <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.home.notFound">No REQUESTS found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default REQUESTS;
