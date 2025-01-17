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

import { getEntities } from './messagessms.reducer';

export const MESSAGESSMS = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const mESSAGESSMSList = useAppSelector(state => state.mESSAGESSMS.entities);
  const loading = useAppSelector(state => state.mESSAGESSMS.loading);

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
      <h2 id="messagessms-heading" data-cy="MESSAGESSMSHeading">
        <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.home.title">MESSAGESSMS</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/messagessms/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.home.createLabel">Create new MESSAGESSMS</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {mESSAGESSMSList && mESSAGESSMSList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('tRNDATETIME')}>
                  <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.tRNDATETIME">T RNDATETIME</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('tRNDATETIME')} />
                </th>
                <th className="hand" onClick={sort('pHONENUMBER')}>
                  <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.pHONENUMBER">P HONENUMBER</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pHONENUMBER')} />
                </th>
                <th className="hand" onClick={sort('tRANSACTIONNO')}>
                  <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.tRANSACTIONNO">T RANSACTIONNO</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('tRANSACTIONNO')} />
                </th>
                <th className="hand" onClick={sort('aCCOUNTNUMBER')}>
                  <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.aCCOUNTNUMBER">A CCOUNTNUMBER</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aCCOUNTNUMBER')} />
                </th>
                <th className="hand" onClick={sort('mESSAGE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.mESSAGE">M ESSAGE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('mESSAGE')} />
                </th>
                <th className="hand" onClick={sort('cHANNEL')}>
                  <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.cHANNEL">C HANNEL</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cHANNEL')} />
                </th>
                <th className="hand" onClick={sort('tRIALS')}>
                  <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.tRIALS">T RIALS</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('tRIALS')} />
                </th>
                <th className="hand" onClick={sort('pRIORITY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.pRIORITY">P RIORITY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pRIORITY')} />
                </th>
                <th className="hand" onClick={sort('rESPONSECODE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.rESPONSECODE">R ESPONSECODE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rESPONSECODE')} />
                </th>
                <th className="hand" onClick={sort('rESPONSEMSG')}>
                  <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.rESPONSEMSG">R ESPONSEMSG</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rESPONSEMSG')} />
                </th>
                <th className="hand" onClick={sort('sENT')}>
                  <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.sENT">S ENT</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('sENT')} />
                </th>
                <th className="hand" onClick={sort('dELIVERED')}>
                  <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.dELIVERED">D ELIVERED</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dELIVERED')} />
                </th>
                <th className="hand" onClick={sort('tXNTYPE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.tXNTYPE">T XNTYPE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('tXNTYPE')} />
                </th>
                <th className="hand" onClick={sort('eRROREXCEPTION')}>
                  <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.eRROREXCEPTION">E RROREXCEPTION</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('eRROREXCEPTION')} />
                </th>
                <th className="hand" onClick={sort('dATECREATED')}>
                  <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.dATECREATED">D ATECREATED</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dATECREATED')} />
                </th>
                <th className="hand" onClick={sort('dATESENT')}>
                  <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.dATESENT">D ATESENT</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dATESENT')} />
                </th>
                <th className="hand" onClick={sort('rTPSREQTIME')}>
                  <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.rTPSREQTIME">R TPSREQTIME</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rTPSREQTIME')} />
                </th>
                <th className="hand" onClick={sort('fXGENERATED')}>
                  <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.fXGENERATED">F XGENERATED</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('fXGENERATED')} />
                </th>
                <th className="hand" onClick={sort('tAXPROCESSED')}>
                  <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.tAXPROCESSED">T AXPROCESSED</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('tAXPROCESSED')} />
                </th>
                <th className="hand" onClick={sort('bATCHNUMBER')}>
                  <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.bATCHNUMBER">B ATCHNUMBER</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('bATCHNUMBER')} />
                </th>
                <th className="hand" onClick={sort('bATCHNUMBERTAX')}>
                  <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.bATCHNUMBERTAX">B ATCHNUMBERTAX</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('bATCHNUMBERTAX')} />
                </th>
                <th className="hand" onClick={sort('rESPONSETIME')}>
                  <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.rESPONSETIME">R ESPONSETIME</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rESPONSETIME')} />
                </th>
                <th className="hand" onClick={sort('pDUSEQID')}>
                  <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.pDUSEQID">P DUSEQID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pDUSEQID')} />
                </th>
                <th className="hand" onClick={sort('rEMARKS')}>
                  <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.rEMARKS">R EMARKS</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rEMARKS')} />
                </th>
                <th className="hand" onClick={sort('rESENDBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.rESENDBY">R ESENDBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rESENDBY')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {mESSAGESSMSList.map((mESSAGESSMS, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/messagessms/${mESSAGESSMS.id}`} color="link" size="sm">
                      {mESSAGESSMS.id}
                    </Button>
                  </td>
                  <td>
                    {mESSAGESSMS.tRNDATETIME ? <TextFormat type="date" value={mESSAGESSMS.tRNDATETIME} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{mESSAGESSMS.pHONENUMBER}</td>
                  <td>{mESSAGESSMS.tRANSACTIONNO}</td>
                  <td>{mESSAGESSMS.aCCOUNTNUMBER}</td>
                  <td>{mESSAGESSMS.mESSAGE}</td>
                  <td>{mESSAGESSMS.cHANNEL}</td>
                  <td>{mESSAGESSMS.tRIALS}</td>
                  <td>{mESSAGESSMS.pRIORITY}</td>
                  <td>{mESSAGESSMS.rESPONSECODE}</td>
                  <td>{mESSAGESSMS.rESPONSEMSG}</td>
                  <td>{mESSAGESSMS.sENT}</td>
                  <td>{mESSAGESSMS.dELIVERED}</td>
                  <td>{mESSAGESSMS.tXNTYPE}</td>
                  <td>{mESSAGESSMS.eRROREXCEPTION}</td>
                  <td>
                    {mESSAGESSMS.dATECREATED ? <TextFormat type="date" value={mESSAGESSMS.dATECREATED} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{mESSAGESSMS.dATESENT}</td>
                  <td>{mESSAGESSMS.rTPSREQTIME}</td>
                  <td>{mESSAGESSMS.fXGENERATED}</td>
                  <td>{mESSAGESSMS.tAXPROCESSED}</td>
                  <td>{mESSAGESSMS.bATCHNUMBER}</td>
                  <td>{mESSAGESSMS.bATCHNUMBERTAX}</td>
                  <td>{mESSAGESSMS.rESPONSETIME}</td>
                  <td>{mESSAGESSMS.pDUSEQID}</td>
                  <td>{mESSAGESSMS.rEMARKS}</td>
                  <td>{mESSAGESSMS.rESENDBY}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/messagessms/${mESSAGESSMS.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/messagessms/${mESSAGESSMS.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/messagessms/${mESSAGESSMS.id}/delete`)}
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
              <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.home.notFound">No MESSAGESSMS found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default MESSAGESSMS;
