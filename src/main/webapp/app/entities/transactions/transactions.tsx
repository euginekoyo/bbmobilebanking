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

import { getEntities } from './transactions.reducer';

export const TRANSACTIONS = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const tRANSACTIONSList = useAppSelector(state => state.tRANSACTIONS.entities);
  const loading = useAppSelector(state => state.tRANSACTIONS.loading);

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
      <h2 id="transactions-heading" data-cy="TRANSACTIONSHeading">
        <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.home.title">TRANSACTIONS</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/transactions/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.home.createLabel">Create new TRANSACTIONS</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {tRANSACTIONSList && tRANSACTIONSList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('pROCESSED')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.pROCESSED">P ROCESSED</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pROCESSED')} />
                </th>
                <th className="hand" onClick={sort('iNCOMINGBITMAP')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.iNCOMINGBITMAP">I NCOMINGBITMAP</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('iNCOMINGBITMAP')} />
                </th>
                <th className="hand" onClick={sort('oUTGOINGBITMAP')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.oUTGOINGBITMAP">O UTGOINGBITMAP</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('oUTGOINGBITMAP')} />
                </th>
                <th className="hand" onClick={sort('iNMESSAGE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.iNMESSAGE">I NMESSAGE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('iNMESSAGE')} />
                </th>
                <th className="hand" onClick={sort('mESSAGETOCBS')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.mESSAGETOCBS">M ESSAGETOCBS</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('mESSAGETOCBS')} />
                </th>
                <th className="hand" onClick={sort('mESSAGEFROMCBS')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.mESSAGEFROMCBS">M ESSAGEFROMCBS</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('mESSAGEFROMCBS')} />
                </th>
                <th className="hand" onClick={sort('cBSPROCESS')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.cBSPROCESS">C BSPROCESS</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cBSPROCESS')} />
                </th>
                <th className="hand" onClick={sort('cBSONLINE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.cBSONLINE">C BSONLINE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cBSONLINE')} />
                </th>
                <th className="hand" onClick={sort('cBSRESPONSE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.cBSRESPONSE">C BSRESPONSE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cBSRESPONSE')} />
                </th>
                <th className="hand" onClick={sort('rESPONSEMESSAGE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.rESPONSEMESSAGE">R ESPONSEMESSAGE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rESPONSEMESSAGE')} />
                </th>
                <th className="hand" onClick={sort('rESPONSESENT')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.rESPONSESENT">R ESPONSESENT</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rESPONSESENT')} />
                </th>
                <th className="hand" onClick={sort('cHANNEL')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.cHANNEL">C HANNEL</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cHANNEL')} />
                </th>
                <th className="hand" onClick={sort('oRIGINALDATA')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.oRIGINALDATA">O RIGINALDATA</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('oRIGINALDATA')} />
                </th>
                <th className="hand" onClick={sort('fIELD39RESP')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.fIELD39RESP">F IELD 39 RESP</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('fIELD39RESP')} />
                </th>
                <th className="hand" onClick={sort('nARRATION')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.nARRATION">N ARRATION</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('nARRATION')} />
                </th>
                <th className="hand" onClick={sort('aUTHORISED')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.aUTHORISED">A UTHORISED</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aUTHORISED')} />
                </th>
                <th className="hand" onClick={sort('bRANCHCODE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.bRANCHCODE">B RANCHCODE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('bRANCHCODE')} />
                </th>
                <th className="hand" onClick={sort('fIELD39ORIGINAL')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.fIELD39ORIGINAL">F IELD 39 ORIGINAL</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('fIELD39ORIGINAL')} />
                </th>
                <th className="hand" onClick={sort('mESSAGECLASS')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.mESSAGECLASS">M ESSAGECLASS</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('mESSAGECLASS')} />
                </th>
                <th className="hand" onClick={sort('tXNCODE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.tXNCODE">T XNCODE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('tXNCODE')} />
                </th>
                <th className="hand" onClick={sort('cURRCODE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.cURRCODE">C URRCODE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cURRCODE')} />
                </th>
                <th className="hand" onClick={sort('dEVICE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.dEVICE">D EVICE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dEVICE')} />
                </th>
                <th className="hand" onClick={sort('bRANCH2')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.bRANCH2">B RANCH 2</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('bRANCH2')} />
                </th>
                <th className="hand" onClick={sort('longERBRANCH')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.longERBRANCH">Long ERBRANCH</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('longERBRANCH')} />
                </th>
                <th className="hand" onClick={sort('dATEX')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.dATEX">D ATEX</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dATEX')} />
                </th>
                <th className="hand" onClick={sort('tIMEX')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.tIMEX">T IMEX</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('tIMEX')} />
                </th>
                <th className="hand" onClick={sort('pOSTED')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.pOSTED">P OSTED</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pOSTED')} />
                </th>
                <th className="hand" onClick={sort('aTTEMPTS')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.aTTEMPTS">A TTEMPTS</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aTTEMPTS')} />
                </th>
                <th className="hand" onClick={sort('oRIGINALDATA2')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.oRIGINALDATA2">O RIGINALDATA 2</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('oRIGINALDATA2')} />
                </th>
                <th className="hand" onClick={sort('cOMMISSION')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.cOMMISSION">C OMMISSION</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cOMMISSION')} />
                </th>
                <th className="hand" onClick={sort('rESPONSECREATED')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.rESPONSECREATED">R ESPONSECREATED</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rESPONSECREATED')} />
                </th>
                <th className="hand" onClick={sort('oNLINE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.oNLINE">O NLINE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('oNLINE')} />
                </th>
                <th className="hand" onClick={sort('oRIGINALDATA3')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.oRIGINALDATA3">O RIGINALDATA 3</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('oRIGINALDATA3')} />
                </th>
                <th className="hand" onClick={sort('tOSWITCH')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.tOSWITCH">T OSWITCH</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('tOSWITCH')} />
                </th>
                <th className="hand" onClick={sort('fROMSWITCH')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.fROMSWITCH">F ROMSWITCH</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('fROMSWITCH')} />
                </th>
                <th className="hand" onClick={sort('tOCBS')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.tOCBS">T OCBS</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('tOCBS')} />
                </th>
                <th className="hand" onClick={sort('fROMCBS')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.fROMCBS">F ROMCBS</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('fROMCBS')} />
                </th>
                <th className="hand" onClick={sort('pOSTINGLEGS')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.pOSTINGLEGS">P OSTINGLEGS</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pOSTINGLEGS')} />
                </th>
                <th className="hand" onClick={sort('cOMMISSIONTXNCODE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.cOMMISSIONTXNCODE">C OMMISSIONTXNCODE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cOMMISSIONTXNCODE')} />
                </th>
                <th className="hand" onClick={sort('hOSTREF')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.hOSTREF">H OSTREF</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('hOSTREF')} />
                </th>
                <th className="hand" onClick={sort('rEQUESTCREATED')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.rEQUESTCREATED">R EQUESTCREATED</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rEQUESTCREATED')} />
                </th>
                <th className="hand" onClick={sort('rEQUESTMESSAGE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.rEQUESTMESSAGE">R EQUESTMESSAGE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rEQUESTMESSAGE')} />
                </th>
                <th className="hand" onClick={sort('oUTGOINGBITMAPFLEX')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.oUTGOINGBITMAPFLEX">O UTGOINGBITMAPFLEX</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('oUTGOINGBITMAPFLEX')} />
                </th>
                <th className="hand" onClick={sort('iNCOMINGBITMAPFLEX')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.iNCOMINGBITMAPFLEX">I NCOMINGBITMAPFLEX</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('iNCOMINGBITMAPFLEX')} />
                </th>
                <th className="hand" onClick={sort('rEQUESTSENT')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.rEQUESTSENT">R EQUESTSENT</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rEQUESTSENT')} />
                </th>
                <th className="hand" onClick={sort('mINICBS')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.mINICBS">M INICBS</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('mINICBS')} />
                </th>
                <th className="hand" onClick={sort('rEVERSED')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.rEVERSED">R EVERSED</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rEVERSED')} />
                </th>
                <th className="hand" onClick={sort('oFFLINESENTTOHOST')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.oFFLINESENTTOHOST">O FFLINESENTTOHOST</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('oFFLINESENTTOHOST')} />
                </th>
                <th className="hand" onClick={sort('oFFLINERESPONSE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.oFFLINERESPONSE">O FFLINERESPONSE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('oFFLINERESPONSE')} />
                </th>
                <th className="hand" onClick={sort('sOURCELongERFACE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.sOURCELongERFACE">S OURCE Long ERFACE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('sOURCELongERFACE')} />
                </th>
                <th className="hand" onClick={sort('mTIRRN')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.mTIRRN">M TIRRN</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('mTIRRN')} />
                </th>
                <th className="hand" onClick={sort('hOSTRESPONSECODE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.hOSTRESPONSECODE">H OSTRESPONSECODE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('hOSTRESPONSECODE')} />
                </th>
                <th className="hand" onClick={sort('fIELD48')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.fIELD48">F IELD 48</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('fIELD48')} />
                </th>
                <th className="hand" onClick={sort('sOURCE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.sOURCE">S OURCE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('sOURCE')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {tRANSACTIONSList.map((tRANSACTIONS, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/transactions/${tRANSACTIONS.id}`} color="link" size="sm">
                      {tRANSACTIONS.id}
                    </Button>
                  </td>
                  <td>{tRANSACTIONS.pROCESSED}</td>
                  <td>{tRANSACTIONS.iNCOMINGBITMAP}</td>
                  <td>{tRANSACTIONS.oUTGOINGBITMAP}</td>
                  <td>{tRANSACTIONS.iNMESSAGE}</td>
                  <td>{tRANSACTIONS.mESSAGETOCBS}</td>
                  <td>{tRANSACTIONS.mESSAGEFROMCBS}</td>
                  <td>{tRANSACTIONS.cBSPROCESS}</td>
                  <td>{tRANSACTIONS.cBSONLINE}</td>
                  <td>{tRANSACTIONS.cBSRESPONSE}</td>
                  <td>{tRANSACTIONS.rESPONSEMESSAGE}</td>
                  <td>{tRANSACTIONS.rESPONSESENT}</td>
                  <td>{tRANSACTIONS.cHANNEL}</td>
                  <td>{tRANSACTIONS.oRIGINALDATA}</td>
                  <td>{tRANSACTIONS.fIELD39RESP}</td>
                  <td>{tRANSACTIONS.nARRATION}</td>
                  <td>{tRANSACTIONS.aUTHORISED}</td>
                  <td>{tRANSACTIONS.bRANCHCODE}</td>
                  <td>{tRANSACTIONS.fIELD39ORIGINAL}</td>
                  <td>{tRANSACTIONS.mESSAGECLASS}</td>
                  <td>{tRANSACTIONS.tXNCODE}</td>
                  <td>{tRANSACTIONS.cURRCODE}</td>
                  <td>{tRANSACTIONS.dEVICE}</td>
                  <td>{tRANSACTIONS.bRANCH2}</td>
                  <td>{tRANSACTIONS.longERBRANCH}</td>
                  <td>{tRANSACTIONS.dATEX ? <TextFormat type="date" value={tRANSACTIONS.dATEX} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{tRANSACTIONS.tIMEX}</td>
                  <td>{tRANSACTIONS.pOSTED}</td>
                  <td>{tRANSACTIONS.aTTEMPTS}</td>
                  <td>{tRANSACTIONS.oRIGINALDATA2}</td>
                  <td>{tRANSACTIONS.cOMMISSION}</td>
                  <td>{tRANSACTIONS.rESPONSECREATED}</td>
                  <td>{tRANSACTIONS.oNLINE}</td>
                  <td>{tRANSACTIONS.oRIGINALDATA3}</td>
                  <td>{tRANSACTIONS.tOSWITCH}</td>
                  <td>{tRANSACTIONS.fROMSWITCH}</td>
                  <td>{tRANSACTIONS.tOCBS}</td>
                  <td>{tRANSACTIONS.fROMCBS}</td>
                  <td>{tRANSACTIONS.pOSTINGLEGS}</td>
                  <td>{tRANSACTIONS.cOMMISSIONTXNCODE}</td>
                  <td>{tRANSACTIONS.hOSTREF}</td>
                  <td>{tRANSACTIONS.rEQUESTCREATED}</td>
                  <td>{tRANSACTIONS.rEQUESTMESSAGE}</td>
                  <td>{tRANSACTIONS.oUTGOINGBITMAPFLEX}</td>
                  <td>{tRANSACTIONS.iNCOMINGBITMAPFLEX}</td>
                  <td>{tRANSACTIONS.rEQUESTSENT}</td>
                  <td>{tRANSACTIONS.mINICBS}</td>
                  <td>{tRANSACTIONS.rEVERSED}</td>
                  <td>{tRANSACTIONS.oFFLINESENTTOHOST}</td>
                  <td>{tRANSACTIONS.oFFLINERESPONSE}</td>
                  <td>{tRANSACTIONS.sOURCELongERFACE}</td>
                  <td>{tRANSACTIONS.mTIRRN}</td>
                  <td>{tRANSACTIONS.hOSTRESPONSECODE}</td>
                  <td>{tRANSACTIONS.fIELD48}</td>
                  <td>{tRANSACTIONS.sOURCE}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/transactions/${tRANSACTIONS.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/transactions/${tRANSACTIONS.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/transactions/${tRANSACTIONS.id}/delete`)}
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
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.home.notFound">No TRANSACTIONS found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default TRANSACTIONS;
