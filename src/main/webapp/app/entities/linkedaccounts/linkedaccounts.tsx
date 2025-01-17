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

import { getEntities } from './linkedaccounts.reducer';

export const LINKEDACCOUNTS = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const lINKEDACCOUNTSList = useAppSelector(state => state.lINKEDACCOUNTS.entities);
  const loading = useAppSelector(state => state.lINKEDACCOUNTS.loading);

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
      <h2 id="linkedaccounts-heading" data-cy="LINKEDACCOUNTSHeading">
        <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.home.title">LINKEDACCOUNTS</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/linkedaccounts/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.home.createLabel">Create new LINKEDACCOUNTS</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {lINKEDACCOUNTSList && lINKEDACCOUNTSList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('aCOUNTNAME')}>
                  <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.aCOUNTNAME">A COUNTNAME</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aCOUNTNAME')} />
                </th>
                <th className="hand" onClick={sort('aCCOUNTCLASS')}>
                  <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.aCCOUNTCLASS">A CCOUNTCLASS</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aCCOUNTCLASS')} />
                </th>
                <th className="hand" onClick={sort('aCCOUNTCURRENCY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.aCCOUNTCURRENCY">A CCOUNTCURRENCY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aCCOUNTCURRENCY')} />
                </th>
                <th className="hand" onClick={sort('aCCOUNTNUMBER')}>
                  <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.aCCOUNTNUMBER">A CCOUNTNUMBER</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aCCOUNTNUMBER')} />
                </th>
                <th className="hand" onClick={sort('cIF')}>
                  <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.cIF">C IF</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cIF')} />
                </th>
                <th className="hand" onClick={sort('tIMELINKED')}>
                  <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.tIMELINKED">T IMELINKED</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('tIMELINKED')} />
                </th>
                <th className="hand" onClick={sort('pHONENUMBER')}>
                  <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.pHONENUMBER">P HONENUMBER</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pHONENUMBER')} />
                </th>
                <th className="hand" onClick={sort('aPPROVEDON')}>
                  <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.aPPROVEDON">A PPROVEDON</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aPPROVEDON')} />
                </th>
                <th className="hand" onClick={sort('aPPROVED')}>
                  <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.aPPROVED">A PPROVED</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aPPROVED')} />
                </th>
                <th className="hand" onClick={sort('dECLINED')}>
                  <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.dECLINED">D ECLINED</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dECLINED')} />
                </th>
                <th className="hand" onClick={sort('dECLINEDON')}>
                  <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.dECLINEDON">D ECLINEDON</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dECLINEDON')} />
                </th>
                <th className="hand" onClick={sort('rEMARKS')}>
                  <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.rEMARKS">R EMARKS</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rEMARKS')} />
                </th>
                <th className="hand" onClick={sort('lINKEDBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.lINKEDBY">L INKEDBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('lINKEDBY')} />
                </th>
                <th className="hand" onClick={sort('aPPROVEDBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.aPPROVEDBY">A PPROVEDBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aPPROVEDBY')} />
                </th>
                <th className="hand" onClick={sort('lINKED')}>
                  <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.lINKED">L INKED</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('lINKED')} />
                </th>
                <th className="hand" onClick={sort('aCTIVE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.aCTIVE">A CTIVE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aCTIVE')} />
                </th>
                <th className="hand" onClick={sort('dELINKEDBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.dELINKEDBY">D ELINKEDBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dELINKEDBY')} />
                </th>
                <th className="hand" onClick={sort('dELINKEDON')}>
                  <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.dELINKEDON">D ELINKEDON</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dELINKEDON')} />
                </th>
                <th className="hand" onClick={sort('dELINKED')}>
                  <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.dELINKED">D ELINKED</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dELINKED')} />
                </th>
                <th className="hand" onClick={sort('aCCOUNTALIAS')}>
                  <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.aCCOUNTALIAS">A CCOUNTALIAS</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aCCOUNTALIAS')} />
                </th>
                <th className="hand" onClick={sort('sHORTCUTS')}>
                  <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.sHORTCUTS">S HORTCUTS</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('sHORTCUTS')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {lINKEDACCOUNTSList.map((lINKEDACCOUNTS, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/linkedaccounts/${lINKEDACCOUNTS.id}`} color="link" size="sm">
                      {lINKEDACCOUNTS.id}
                    </Button>
                  </td>
                  <td>{lINKEDACCOUNTS.aCOUNTNAME}</td>
                  <td>{lINKEDACCOUNTS.aCCOUNTCLASS}</td>
                  <td>{lINKEDACCOUNTS.aCCOUNTCURRENCY}</td>
                  <td>{lINKEDACCOUNTS.aCCOUNTNUMBER}</td>
                  <td>{lINKEDACCOUNTS.cIF}</td>
                  <td>
                    {lINKEDACCOUNTS.tIMELINKED ? (
                      <TextFormat type="date" value={lINKEDACCOUNTS.tIMELINKED} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{lINKEDACCOUNTS.pHONENUMBER}</td>
                  <td>
                    {lINKEDACCOUNTS.aPPROVEDON ? (
                      <TextFormat type="date" value={lINKEDACCOUNTS.aPPROVEDON} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{lINKEDACCOUNTS.aPPROVED}</td>
                  <td>{lINKEDACCOUNTS.dECLINED}</td>
                  <td>
                    {lINKEDACCOUNTS.dECLINEDON ? (
                      <TextFormat type="date" value={lINKEDACCOUNTS.dECLINEDON} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{lINKEDACCOUNTS.rEMARKS}</td>
                  <td>{lINKEDACCOUNTS.lINKEDBY}</td>
                  <td>{lINKEDACCOUNTS.aPPROVEDBY}</td>
                  <td>{lINKEDACCOUNTS.lINKED}</td>
                  <td>{lINKEDACCOUNTS.aCTIVE}</td>
                  <td>{lINKEDACCOUNTS.dELINKEDBY}</td>
                  <td>
                    {lINKEDACCOUNTS.dELINKEDON ? (
                      <TextFormat type="date" value={lINKEDACCOUNTS.dELINKEDON} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{lINKEDACCOUNTS.dELINKED}</td>
                  <td>{lINKEDACCOUNTS.aCCOUNTALIAS}</td>
                  <td>{lINKEDACCOUNTS.sHORTCUTS}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/linkedaccounts/${lINKEDACCOUNTS.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/linkedaccounts/${lINKEDACCOUNTS.id}/edit`}
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
                        onClick={() => (window.location.href = `/linkedaccounts/${lINKEDACCOUNTS.id}/delete`)}
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
              <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.home.notFound">No LINKEDACCOUNTS found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default LINKEDACCOUNTS;
