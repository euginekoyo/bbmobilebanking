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

import { getEntities } from './customer.reducer';

export const CUSTOMER = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const cUSTOMERList = useAppSelector(state => state.cUSTOMER.entities);
  const loading = useAppSelector(state => state.cUSTOMER.loading);

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
      <h2 id="customer-heading" data-cy="CUSTOMERHeading">
        <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.home.title">CUSTOMERS</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/customer/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.home.createLabel">Create new CUSTOMER</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {cUSTOMERList && cUSTOMERList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('cUSTOMERNAME')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.cUSTOMERNAME">C USTOMERNAME</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cUSTOMERNAME')} />
                </th>
                <th className="hand" onClick={sort('pHONENUMBER')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pHONENUMBER">P HONENUMBER</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pHONENUMBER')} />
                </th>
                <th className="hand" onClick={sort('cARDNUMBER')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.cARDNUMBER">C ARDNUMBER</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cARDNUMBER')} />
                </th>
                <th className="hand" onClick={sort('aCCOUNTNUMBER')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.aCCOUNTNUMBER">A CCOUNTNUMBER</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aCCOUNTNUMBER')} />
                </th>
                <th className="hand" onClick={sort('lANG')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.lANG">L ANG</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('lANG')} />
                </th>
                <th className="hand" onClick={sort('pIN')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pIN">P IN</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pIN')} />
                </th>
                <th className="hand" onClick={sort('fIRSTLOGIN')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.fIRSTLOGIN">F IRSTLOGIN</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('fIRSTLOGIN')} />
                </th>
                <th className="hand" onClick={sort('aCTIVE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.aCTIVE">A CTIVE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aCTIVE')} />
                </th>
                <th className="hand" onClick={sort('rEGISTERED')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.rEGISTERED">R EGISTERED</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rEGISTERED')} />
                </th>
                <th className="hand" onClick={sort('cSTDELETE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.cSTDELETE">C STDELETE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cSTDELETE')} />
                </th>
                <th className="hand" onClick={sort('rEGDATE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.rEGDATE">R EGDATE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rEGDATE')} />
                </th>
                <th className="hand" onClick={sort('aLERTENABLED')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.aLERTENABLED">A LERTENABLED</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aLERTENABLED')} />
                </th>
                <th className="hand" onClick={sort('rEMARK')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.rEMARK">R EMARK</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rEMARK')} />
                </th>
                <th className="hand" onClick={sort('iMSI')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.iMSI">I MSI</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('iMSI')} />
                </th>
                <th className="hand" onClick={sort('pARTIALLYREGISTERED')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pARTIALLYREGISTERED">P ARTIALLYREGISTERED</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pARTIALLYREGISTERED')} />
                </th>
                <th className="hand" onClick={sort('pARTIALDATE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pARTIALDATE">P ARTIALDATE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pARTIALDATE')} />
                </th>
                <th className="hand" onClick={sort('rEGISTERDATE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.rEGISTERDATE">R EGISTERDATE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rEGISTERDATE')} />
                </th>
                <th className="hand" onClick={sort('aPPROVED')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.aPPROVED">A PPROVED</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aPPROVED')} />
                </th>
                <th className="hand" onClick={sort('aPPROVEDBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.aPPROVEDBY">A PPROVEDBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aPPROVEDBY')} />
                </th>
                <th className="hand" onClick={sort('aPPROVEDDATE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.aPPROVEDDATE">A PPROVEDDATE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aPPROVEDDATE')} />
                </th>
                <th className="hand" onClick={sort('dECLINED')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.dECLINED">D ECLINED</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dECLINED')} />
                </th>
                <th className="hand" onClick={sort('dECLINEDBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.dECLINEDBY">D ECLINEDBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dECLINEDBY')} />
                </th>
                <th className="hand" onClick={sort('dECLINEDDATE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.dECLINEDDATE">D ECLINEDDATE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dECLINEDDATE')} />
                </th>
                <th className="hand" onClick={sort('cHECKERREMARKS')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.cHECKERREMARKS">C HECKERREMARKS</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cHECKERREMARKS')} />
                </th>
                <th className="hand" onClick={sort('pOSTALADDRESS')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pOSTALADDRESS">P OSTALADDRESS</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pOSTALADDRESS')} />
                </th>
                <th className="hand" onClick={sort('rESIDENCE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.rESIDENCE">R ESIDENCE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rESIDENCE')} />
                </th>
                <th className="hand" onClick={sort('dOB')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.dOB">D OB</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dOB')} />
                </th>
                <th className="hand" onClick={sort('cREATEDBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.cREATEDBY">C REATEDBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cREATEDBY')} />
                </th>
                <th className="hand" onClick={sort('eMAILADDRESS')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.eMAILADDRESS">E MAILADDRESS</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('eMAILADDRESS')} />
                </th>
                <th className="hand" onClick={sort('iDENTIFICATIONID')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.iDENTIFICATIONID">I DENTIFICATIONID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('iDENTIFICATIONID')} />
                </th>
                <th className="hand" onClick={sort('aDDACCOUNT')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.aDDACCOUNT">A DDACCOUNT</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aDDACCOUNT')} />
                </th>
                <th className="hand" onClick={sort('aCLINKINGINSTITUTION')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.aCLINKINGINSTITUTION">A CLINKINGINSTITUTION</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aCLINKINGINSTITUTION')} />
                </th>
                <th className="hand" onClick={sort('dEACTIVATED')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.dEACTIVATED">D EACTIVATED</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dEACTIVATED')} />
                </th>
                <th className="hand" onClick={sort('dEACTIVATEDBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.dEACTIVATEDBY">D EACTIVATEDBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dEACTIVATEDBY')} />
                </th>
                <th className="hand" onClick={sort('dEACTIVATEDON')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.dEACTIVATEDON">D EACTIVATEDON</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dEACTIVATEDON')} />
                </th>
                <th className="hand" onClick={sort('pHONENOCHANGED')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pHONENOCHANGED">P HONENOCHANGED</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pHONENOCHANGED')} />
                </th>
                <th className="hand" onClick={sort('pHONENOCHANGEDBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pHONENOCHANGEDBY">P HONENOCHANGEDBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pHONENOCHANGEDBY')} />
                </th>
                <th className="hand" onClick={sort('pHONENOCHANGEDON')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pHONENOCHANGEDON">P HONENOCHANGEDON</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pHONENOCHANGEDON')} />
                </th>
                <th className="hand" onClick={sort('oRIGINALPHONENO')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.oRIGINALPHONENO">O RIGINALPHONENO</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('oRIGINALPHONENO')} />
                </th>
                <th className="hand" onClick={sort('nEWPHONENO')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.nEWPHONENO">N EWPHONENO</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('nEWPHONENO')} />
                </th>
                <th className="hand" onClick={sort('rESET')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.rESET">R ESET</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rESET')} />
                </th>
                <th className="hand" onClick={sort('rESETINGINSTITUTION')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.rESETINGINSTITUTION">R ESETINGINSTITUTION</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rESETINGINSTITUTION')} />
                </th>
                <th className="hand" onClick={sort('pINRESETREMARK')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pINRESETREMARK">P INRESETREMARK</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pINRESETREMARK')} />
                </th>
                <th className="hand" onClick={sort('rESETBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.rESETBY">R ESETBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rESETBY')} />
                </th>
                <th className="hand" onClick={sort('rESETON')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.rESETON">R ESETON</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rESETON')} />
                </th>
                <th className="hand" onClick={sort('uNBLOCKINGINSTITUTION')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.uNBLOCKINGINSTITUTION">U NBLOCKINGINSTITUTION</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('uNBLOCKINGINSTITUTION')} />
                </th>
                <th className="hand" onClick={sort('pINBLOCK')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pINBLOCK">P INBLOCK</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pINBLOCK')} />
                </th>
                <th className="hand" onClick={sort('pINBLOCKBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pINBLOCKBY">P INBLOCKBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pINBLOCKBY')} />
                </th>
                <th className="hand" onClick={sort('pINBLOCKREMARKS')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pINBLOCKREMARKS">P INBLOCKREMARKS</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pINBLOCKREMARKS')} />
                </th>
                <th className="hand" onClick={sort('bLOCKINGINSTITUTION')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.bLOCKINGINSTITUTION">B LOCKINGINSTITUTION</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('bLOCKINGINSTITUTION')} />
                </th>
                <th className="hand" onClick={sort('pINBLOCKON')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pINBLOCKON">P INBLOCKON</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pINBLOCKON')} />
                </th>
                <th className="hand" onClick={sort('aPPROVEDON')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.aPPROVEDON">A PPROVEDON</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aPPROVEDON')} />
                </th>
                <th className="hand" onClick={sort('pINUNBLOCKBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pINUNBLOCKBY">P INUNBLOCKBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pINUNBLOCKBY')} />
                </th>
                <th className="hand" onClick={sort('lOGGEDIN')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.lOGGEDIN">L OGGEDIN</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('lOGGEDIN')} />
                </th>
                <th className="hand" onClick={sort('tRIALS')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.tRIALS">T RIALS</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('tRIALS')} />
                </th>
                <th className="hand" onClick={sort('iDTYPE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.iDTYPE">I DTYPE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('iDTYPE')} />
                </th>
                <th className="hand" onClick={sort('iDNUMBER')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.iDNUMBER">I DNUMBER</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('iDNUMBER')} />
                </th>
                <th className="hand" onClick={sort('gENDER')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.gENDER">G ENDER</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('gENDER')} />
                </th>
                <th className="hand" onClick={sort('cIF')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.cIF">C IF</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cIF')} />
                </th>
                <th className="hand" onClick={sort('dATEOFBIRTH')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.dATEOFBIRTH">D ATEOFBIRTH</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dATEOFBIRTH')} />
                </th>
                <th className="hand" onClick={sort('rEMARKS')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.rEMARKS">R EMARKS</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rEMARKS')} />
                </th>
                <th className="hand" onClick={sort('rESETIMSI')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.rESETIMSI">R ESETIMSI</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rESETIMSI')} />
                </th>
                <th className="hand" onClick={sort('iMSIRESETBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.iMSIRESETBY">I MSIRESETBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('iMSIRESETBY')} />
                </th>
                <th className="hand" onClick={sort('fIRSTNAME')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.fIRSTNAME">F IRSTNAME</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('fIRSTNAME')} />
                </th>
                <th className="hand" onClick={sort('sECONDNAME')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.sECONDNAME">S ECONDNAME</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('sECONDNAME')} />
                </th>
                <th className="hand" onClick={sort('lASTNAME')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.lASTNAME">L ASTNAME</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('lASTNAME')} />
                </th>
                <th className="hand" onClick={sort('pINBLOCKTIME')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pINBLOCKTIME">P INBLOCKTIME</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pINBLOCKTIME')} />
                </th>
                <th className="hand" onClick={sort('cUSTOMERSTATUS')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.cUSTOMERSTATUS">C USTOMERSTATUS</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cUSTOMERSTATUS')} />
                </th>
                <th className="hand" onClick={sort('uSERNAME')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.uSERNAME">U SERNAME</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('uSERNAME')} />
                </th>
                <th className="hand" onClick={sort('pASSWORD')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pASSWORD">P ASSWORD</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pASSWORD')} />
                </th>
                <th className="hand" onClick={sort('dEVICEID')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.dEVICEID">D EVICEID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dEVICEID')} />
                </th>
                <th className="hand" onClick={sort('cHANNEL')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.cHANNEL">C HANNEL</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cHANNEL')} />
                </th>
                <th className="hand" onClick={sort('pASSRESET')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pASSRESET">P ASSRESET</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pASSRESET')} />
                </th>
                <th className="hand" onClick={sort('pASSRESETBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pASSRESETBY">P ASSRESETBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pASSRESETBY')} />
                </th>
                <th className="hand" onClick={sort('pASSRESETON')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pASSRESETON">P ASSRESETON</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pASSRESETON')} />
                </th>
                <th className="hand" onClick={sort('pASSBLOCK')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pASSBLOCK">P ASSBLOCK</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pASSBLOCK')} />
                </th>
                <th className="hand" onClick={sort('pASSBLOCKBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pASSBLOCKBY">P ASSBLOCKBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pASSBLOCKBY')} />
                </th>
                <th className="hand" onClick={sort('pASSBLOCKON')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pASSBLOCKON">P ASSBLOCKON</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pASSBLOCKON')} />
                </th>
                <th className="hand" onClick={sort('pINMARKBLOCK')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pINMARKBLOCK">P INMARKBLOCK</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pINMARKBLOCK')} />
                </th>
                <th className="hand" onClick={sort('pASSMARKBLOCK')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pASSMARKBLOCK">P ASSMARKBLOCK</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pASSMARKBLOCK')} />
                </th>
                <th className="hand" onClick={sort('pASSRESETREMARKS')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pASSRESETREMARKS">P ASSRESETREMARKS</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pASSRESETREMARKS')} />
                </th>
                <th className="hand" onClick={sort('pASSBLOCKREMARKS')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pASSBLOCKREMARKS">P ASSBLOCKREMARKS</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pASSBLOCKREMARKS')} />
                </th>
                <th className="hand" onClick={sort('pASSUNBLOCKBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pASSUNBLOCKBY">P ASSUNBLOCKBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pASSUNBLOCKBY')} />
                </th>
                <th className="hand" onClick={sort('pASSTRIALS')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pASSTRIALS">P ASSTRIALS</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pASSTRIALS')} />
                </th>
                <th className="hand" onClick={sort('aPPACTIVE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.aPPACTIVE">A PPACTIVE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aPPACTIVE')} />
                </th>
                <th className="hand" onClick={sort('lASTLOGIN')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.lASTLOGIN">L ASTLOGIN</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('lASTLOGIN')} />
                </th>
                <th className="hand" onClick={sort('aPPMARKEDDISABLE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.aPPMARKEDDISABLE">A PPMARKEDDISABLE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aPPMARKEDDISABLE')} />
                </th>
                <th className="hand" onClick={sort('dISABLEBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.dISABLEBY">D ISABLEBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dISABLEBY')} />
                </th>
                <th className="hand" onClick={sort('aPPROVEDISABLEBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.aPPROVEDISABLEBY">A PPROVEDISABLEBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aPPROVEDISABLEBY')} />
                </th>
                <th className="hand" onClick={sort('aPPMARKEDENABLE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.aPPMARKEDENABLE">A PPMARKEDENABLE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aPPMARKEDENABLE')} />
                </th>
                <th className="hand" onClick={sort('eNABLEBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.eNABLEBY">E NABLEBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('eNABLEBY')} />
                </th>
                <th className="hand" onClick={sort('aPPROVEDENABLEBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.aPPROVEDENABLEBY">A PPROVEDENABLEBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aPPROVEDENABLEBY')} />
                </th>
                <th className="hand" onClick={sort('mARKEDDEACTIVATE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.mARKEDDEACTIVATE">M ARKEDDEACTIVATE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('mARKEDDEACTIVATE')} />
                </th>
                <th className="hand" onClick={sort('aPPFIRSTLOGIN')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.aPPFIRSTLOGIN">A PPFIRSTLOGIN</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aPPFIRSTLOGIN')} />
                </th>
                <th className="hand" onClick={sort('aTMTRIALS')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.aTMTRIALS">A TMTRIALS</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aTMTRIALS')} />
                </th>
                <th className="hand" onClick={sort('sHORCUTS')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.sHORCUTS">S HORCUTS</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('sHORCUTS')} />
                </th>
                <th className="hand" onClick={sort('mARKEDACTIVATE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.mARKEDACTIVATE">M ARKEDACTIVATE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('mARKEDACTIVATE')} />
                </th>
                <th className="hand" onClick={sort('tOWN')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.tOWN">T OWN</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('tOWN')} />
                </th>
                <th className="hand" onClick={sort('aPPROVEDDISABLEON')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.aPPROVEDDISABLEON">A PPROVEDDISABLEON</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aPPROVEDDISABLEON')} />
                </th>
                <th className="hand" onClick={sort('dISABLEDON')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.dISABLEDON">D ISABLEDON</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dISABLEDON')} />
                </th>
                <th className="hand" onClick={sort('rESETAPPROVEON')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.rESETAPPROVEON">R ESETAPPROVEON</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rESETAPPROVEON')} />
                </th>
                <th className="hand" onClick={sort('dELETEDBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.dELETEDBY">D ELETEDBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dELETEDBY')} />
                </th>
                <th className="hand" onClick={sort('qUESTIONSASKED')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.qUESTIONSASKED">Q UESTIONSASKED</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('qUESTIONSASKED')} />
                </th>
                <th className="hand" onClick={sort('qUESTIONSTRIALS')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.qUESTIONSTRIALS">Q UESTIONSTRIALS</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('qUESTIONSTRIALS')} />
                </th>
                <th className="hand" onClick={sort('qUESTIONSANSWERED')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.qUESTIONSANSWERED">Q UESTIONSANSWERED</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('qUESTIONSANSWERED')} />
                </th>
                <th className="hand" onClick={sort('vALIDOTP')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.vALIDOTP">V ALIDOTP</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('vALIDOTP')} />
                </th>
                <th className="hand" onClick={sort('aCTIVATEDBY')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.aCTIVATEDBY">A CTIVATEDBY</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aCTIVATEDBY')} />
                </th>
                <th className="hand" onClick={sort('aCTIVATEDON')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.aCTIVATEDON">A CTIVATEDON</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('aCTIVATEDON')} />
                </th>
                <th className="hand" onClick={sort('bRANCHCODE')}>
                  <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.bRANCHCODE">B RANCHCODE</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('bRANCHCODE')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {cUSTOMERList.map((cUSTOMER, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/customer/${cUSTOMER.id}`} color="link" size="sm">
                      {cUSTOMER.id}
                    </Button>
                  </td>
                  <td>{cUSTOMER.cUSTOMERNAME}</td>
                  <td>{cUSTOMER.pHONENUMBER}</td>
                  <td>{cUSTOMER.cARDNUMBER}</td>
                  <td>{cUSTOMER.aCCOUNTNUMBER}</td>
                  <td>{cUSTOMER.lANG}</td>
                  <td>{cUSTOMER.pIN}</td>
                  <td>{cUSTOMER.fIRSTLOGIN}</td>
                  <td>{cUSTOMER.aCTIVE}</td>
                  <td>{cUSTOMER.rEGISTERED}</td>
                  <td>{cUSTOMER.cSTDELETE}</td>
                  <td>{cUSTOMER.rEGDATE ? <TextFormat type="date" value={cUSTOMER.rEGDATE} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{cUSTOMER.aLERTENABLED}</td>
                  <td>{cUSTOMER.rEMARK}</td>
                  <td>{cUSTOMER.iMSI}</td>
                  <td>{cUSTOMER.pARTIALLYREGISTERED}</td>
                  <td>{cUSTOMER.pARTIALDATE ? <TextFormat type="date" value={cUSTOMER.pARTIALDATE} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>
                    {cUSTOMER.rEGISTERDATE ? <TextFormat type="date" value={cUSTOMER.rEGISTERDATE} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{cUSTOMER.aPPROVED}</td>
                  <td>{cUSTOMER.aPPROVEDBY}</td>
                  <td>
                    {cUSTOMER.aPPROVEDDATE ? <TextFormat type="date" value={cUSTOMER.aPPROVEDDATE} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{cUSTOMER.dECLINED}</td>
                  <td>{cUSTOMER.dECLINEDBY}</td>
                  <td>
                    {cUSTOMER.dECLINEDDATE ? <TextFormat type="date" value={cUSTOMER.dECLINEDDATE} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{cUSTOMER.cHECKERREMARKS}</td>
                  <td>{cUSTOMER.pOSTALADDRESS}</td>
                  <td>{cUSTOMER.rESIDENCE}</td>
                  <td>{cUSTOMER.dOB ? <TextFormat type="date" value={cUSTOMER.dOB} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{cUSTOMER.cREATEDBY}</td>
                  <td>{cUSTOMER.eMAILADDRESS}</td>
                  <td>{cUSTOMER.iDENTIFICATIONID}</td>
                  <td>{cUSTOMER.aDDACCOUNT}</td>
                  <td>{cUSTOMER.aCLINKINGINSTITUTION}</td>
                  <td>{cUSTOMER.dEACTIVATED}</td>
                  <td>{cUSTOMER.dEACTIVATEDBY}</td>
                  <td>
                    {cUSTOMER.dEACTIVATEDON ? <TextFormat type="date" value={cUSTOMER.dEACTIVATEDON} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{cUSTOMER.pHONENOCHANGED}</td>
                  <td>{cUSTOMER.pHONENOCHANGEDBY}</td>
                  <td>
                    {cUSTOMER.pHONENOCHANGEDON ? (
                      <TextFormat type="date" value={cUSTOMER.pHONENOCHANGEDON} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{cUSTOMER.oRIGINALPHONENO}</td>
                  <td>{cUSTOMER.nEWPHONENO}</td>
                  <td>{cUSTOMER.rESET}</td>
                  <td>{cUSTOMER.rESETINGINSTITUTION}</td>
                  <td>{cUSTOMER.pINRESETREMARK}</td>
                  <td>{cUSTOMER.rESETBY}</td>
                  <td>{cUSTOMER.rESETON ? <TextFormat type="date" value={cUSTOMER.rESETON} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{cUSTOMER.uNBLOCKINGINSTITUTION}</td>
                  <td>{cUSTOMER.pINBLOCK}</td>
                  <td>{cUSTOMER.pINBLOCKBY}</td>
                  <td>{cUSTOMER.pINBLOCKREMARKS}</td>
                  <td>{cUSTOMER.bLOCKINGINSTITUTION}</td>
                  <td>{cUSTOMER.pINBLOCKON ? <TextFormat type="date" value={cUSTOMER.pINBLOCKON} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{cUSTOMER.aPPROVEDON ? <TextFormat type="date" value={cUSTOMER.aPPROVEDON} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{cUSTOMER.pINUNBLOCKBY}</td>
                  <td>{cUSTOMER.lOGGEDIN}</td>
                  <td>{cUSTOMER.tRIALS}</td>
                  <td>{cUSTOMER.iDTYPE}</td>
                  <td>{cUSTOMER.iDNUMBER}</td>
                  <td>{cUSTOMER.gENDER}</td>
                  <td>{cUSTOMER.cIF}</td>
                  <td>{cUSTOMER.dATEOFBIRTH ? <TextFormat type="date" value={cUSTOMER.dATEOFBIRTH} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{cUSTOMER.rEMARKS}</td>
                  <td>{cUSTOMER.rESETIMSI}</td>
                  <td>{cUSTOMER.iMSIRESETBY}</td>
                  <td>{cUSTOMER.fIRSTNAME}</td>
                  <td>{cUSTOMER.sECONDNAME}</td>
                  <td>{cUSTOMER.lASTNAME}</td>
                  <td>{cUSTOMER.pINBLOCKTIME}</td>
                  <td>{cUSTOMER.cUSTOMERSTATUS}</td>
                  <td>{cUSTOMER.uSERNAME}</td>
                  <td>{cUSTOMER.pASSWORD}</td>
                  <td>{cUSTOMER.dEVICEID}</td>
                  <td>{cUSTOMER.cHANNEL}</td>
                  <td>{cUSTOMER.pASSRESET}</td>
                  <td>{cUSTOMER.pASSRESETBY}</td>
                  <td>{cUSTOMER.pASSRESETON ? <TextFormat type="date" value={cUSTOMER.pASSRESETON} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{cUSTOMER.pASSBLOCK}</td>
                  <td>{cUSTOMER.pASSBLOCKBY}</td>
                  <td>{cUSTOMER.pASSBLOCKON ? <TextFormat type="date" value={cUSTOMER.pASSBLOCKON} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{cUSTOMER.pINMARKBLOCK}</td>
                  <td>{cUSTOMER.pASSMARKBLOCK}</td>
                  <td>{cUSTOMER.pASSRESETREMARKS}</td>
                  <td>{cUSTOMER.pASSBLOCKREMARKS}</td>
                  <td>{cUSTOMER.pASSUNBLOCKBY}</td>
                  <td>{cUSTOMER.pASSTRIALS}</td>
                  <td>{cUSTOMER.aPPACTIVE}</td>
                  <td>{cUSTOMER.lASTLOGIN}</td>
                  <td>{cUSTOMER.aPPMARKEDDISABLE}</td>
                  <td>{cUSTOMER.dISABLEBY}</td>
                  <td>{cUSTOMER.aPPROVEDISABLEBY}</td>
                  <td>{cUSTOMER.aPPMARKEDENABLE}</td>
                  <td>{cUSTOMER.eNABLEBY}</td>
                  <td>{cUSTOMER.aPPROVEDENABLEBY}</td>
                  <td>{cUSTOMER.mARKEDDEACTIVATE}</td>
                  <td>{cUSTOMER.aPPFIRSTLOGIN}</td>
                  <td>{cUSTOMER.aTMTRIALS}</td>
                  <td>{cUSTOMER.sHORCUTS}</td>
                  <td>{cUSTOMER.mARKEDACTIVATE}</td>
                  <td>{cUSTOMER.tOWN}</td>
                  <td>
                    {cUSTOMER.aPPROVEDDISABLEON ? (
                      <TextFormat type="date" value={cUSTOMER.aPPROVEDDISABLEON} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{cUSTOMER.dISABLEDON ? <TextFormat type="date" value={cUSTOMER.dISABLEDON} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>
                    {cUSTOMER.rESETAPPROVEON ? <TextFormat type="date" value={cUSTOMER.rESETAPPROVEON} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{cUSTOMER.dELETEDBY}</td>
                  <td>{cUSTOMER.qUESTIONSASKED}</td>
                  <td>{cUSTOMER.qUESTIONSTRIALS}</td>
                  <td>{cUSTOMER.qUESTIONSANSWERED}</td>
                  <td>{cUSTOMER.vALIDOTP}</td>
                  <td>{cUSTOMER.aCTIVATEDBY}</td>
                  <td>{cUSTOMER.aCTIVATEDON ? <TextFormat type="date" value={cUSTOMER.aCTIVATEDON} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{cUSTOMER.bRANCHCODE}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/customer/${cUSTOMER.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/customer/${cUSTOMER.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/customer/${cUSTOMER.id}/delete`)}
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
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.home.notFound">No CUSTOMERS found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default CUSTOMER;
