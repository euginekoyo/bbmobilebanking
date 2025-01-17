import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './customer.reducer';

export const CUSTOMERDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const cUSTOMEREntity = useAppSelector(state => state.cUSTOMER.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="cUSTOMERDetailsHeading">
          <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.detail.title">CUSTOMER</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.id}</dd>
          <dt>
            <span id="cUSTOMERNAME">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.cUSTOMERNAME">C USTOMERNAME</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.cUSTOMERNAME}</dd>
          <dt>
            <span id="pHONENUMBER">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pHONENUMBER">P HONENUMBER</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.pHONENUMBER}</dd>
          <dt>
            <span id="cARDNUMBER">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.cARDNUMBER">C ARDNUMBER</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.cARDNUMBER}</dd>
          <dt>
            <span id="aCCOUNTNUMBER">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.aCCOUNTNUMBER">A CCOUNTNUMBER</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.aCCOUNTNUMBER}</dd>
          <dt>
            <span id="lANG">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.lANG">L ANG</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.lANG}</dd>
          <dt>
            <span id="pIN">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pIN">P IN</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.pIN}</dd>
          <dt>
            <span id="fIRSTLOGIN">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.fIRSTLOGIN">F IRSTLOGIN</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.fIRSTLOGIN}</dd>
          <dt>
            <span id="aCTIVE">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.aCTIVE">A CTIVE</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.aCTIVE}</dd>
          <dt>
            <span id="rEGISTERED">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.rEGISTERED">R EGISTERED</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.rEGISTERED}</dd>
          <dt>
            <span id="cSTDELETE">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.cSTDELETE">C STDELETE</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.cSTDELETE}</dd>
          <dt>
            <span id="rEGDATE">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.rEGDATE">R EGDATE</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.rEGDATE ? <TextFormat value={cUSTOMEREntity.rEGDATE} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="aLERTENABLED">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.aLERTENABLED">A LERTENABLED</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.aLERTENABLED}</dd>
          <dt>
            <span id="rEMARK">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.rEMARK">R EMARK</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.rEMARK}</dd>
          <dt>
            <span id="iMSI">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.iMSI">I MSI</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.iMSI}</dd>
          <dt>
            <span id="pARTIALLYREGISTERED">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pARTIALLYREGISTERED">P ARTIALLYREGISTERED</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.pARTIALLYREGISTERED}</dd>
          <dt>
            <span id="pARTIALDATE">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pARTIALDATE">P ARTIALDATE</Translate>
            </span>
          </dt>
          <dd>
            {cUSTOMEREntity.pARTIALDATE ? <TextFormat value={cUSTOMEREntity.pARTIALDATE} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="rEGISTERDATE">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.rEGISTERDATE">R EGISTERDATE</Translate>
            </span>
          </dt>
          <dd>
            {cUSTOMEREntity.rEGISTERDATE ? <TextFormat value={cUSTOMEREntity.rEGISTERDATE} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="aPPROVED">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.aPPROVED">A PPROVED</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.aPPROVED}</dd>
          <dt>
            <span id="aPPROVEDBY">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.aPPROVEDBY">A PPROVEDBY</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.aPPROVEDBY}</dd>
          <dt>
            <span id="aPPROVEDDATE">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.aPPROVEDDATE">A PPROVEDDATE</Translate>
            </span>
          </dt>
          <dd>
            {cUSTOMEREntity.aPPROVEDDATE ? <TextFormat value={cUSTOMEREntity.aPPROVEDDATE} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="dECLINED">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.dECLINED">D ECLINED</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.dECLINED}</dd>
          <dt>
            <span id="dECLINEDBY">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.dECLINEDBY">D ECLINEDBY</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.dECLINEDBY}</dd>
          <dt>
            <span id="dECLINEDDATE">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.dECLINEDDATE">D ECLINEDDATE</Translate>
            </span>
          </dt>
          <dd>
            {cUSTOMEREntity.dECLINEDDATE ? <TextFormat value={cUSTOMEREntity.dECLINEDDATE} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="cHECKERREMARKS">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.cHECKERREMARKS">C HECKERREMARKS</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.cHECKERREMARKS}</dd>
          <dt>
            <span id="pOSTALADDRESS">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pOSTALADDRESS">P OSTALADDRESS</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.pOSTALADDRESS}</dd>
          <dt>
            <span id="rESIDENCE">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.rESIDENCE">R ESIDENCE</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.rESIDENCE}</dd>
          <dt>
            <span id="dOB">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.dOB">D OB</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.dOB ? <TextFormat value={cUSTOMEREntity.dOB} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="cREATEDBY">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.cREATEDBY">C REATEDBY</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.cREATEDBY}</dd>
          <dt>
            <span id="eMAILADDRESS">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.eMAILADDRESS">E MAILADDRESS</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.eMAILADDRESS}</dd>
          <dt>
            <span id="iDENTIFICATIONID">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.iDENTIFICATIONID">I DENTIFICATIONID</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.iDENTIFICATIONID}</dd>
          <dt>
            <span id="aDDACCOUNT">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.aDDACCOUNT">A DDACCOUNT</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.aDDACCOUNT}</dd>
          <dt>
            <span id="aCLINKINGINSTITUTION">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.aCLINKINGINSTITUTION">A CLINKINGINSTITUTION</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.aCLINKINGINSTITUTION}</dd>
          <dt>
            <span id="dEACTIVATED">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.dEACTIVATED">D EACTIVATED</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.dEACTIVATED}</dd>
          <dt>
            <span id="dEACTIVATEDBY">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.dEACTIVATEDBY">D EACTIVATEDBY</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.dEACTIVATEDBY}</dd>
          <dt>
            <span id="dEACTIVATEDON">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.dEACTIVATEDON">D EACTIVATEDON</Translate>
            </span>
          </dt>
          <dd>
            {cUSTOMEREntity.dEACTIVATEDON ? <TextFormat value={cUSTOMEREntity.dEACTIVATEDON} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="pHONENOCHANGED">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pHONENOCHANGED">P HONENOCHANGED</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.pHONENOCHANGED}</dd>
          <dt>
            <span id="pHONENOCHANGEDBY">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pHONENOCHANGEDBY">P HONENOCHANGEDBY</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.pHONENOCHANGEDBY}</dd>
          <dt>
            <span id="pHONENOCHANGEDON">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pHONENOCHANGEDON">P HONENOCHANGEDON</Translate>
            </span>
          </dt>
          <dd>
            {cUSTOMEREntity.pHONENOCHANGEDON ? (
              <TextFormat value={cUSTOMEREntity.pHONENOCHANGEDON} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="oRIGINALPHONENO">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.oRIGINALPHONENO">O RIGINALPHONENO</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.oRIGINALPHONENO}</dd>
          <dt>
            <span id="nEWPHONENO">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.nEWPHONENO">N EWPHONENO</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.nEWPHONENO}</dd>
          <dt>
            <span id="rESET">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.rESET">R ESET</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.rESET}</dd>
          <dt>
            <span id="rESETINGINSTITUTION">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.rESETINGINSTITUTION">R ESETINGINSTITUTION</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.rESETINGINSTITUTION}</dd>
          <dt>
            <span id="pINRESETREMARK">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pINRESETREMARK">P INRESETREMARK</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.pINRESETREMARK}</dd>
          <dt>
            <span id="rESETBY">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.rESETBY">R ESETBY</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.rESETBY}</dd>
          <dt>
            <span id="rESETON">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.rESETON">R ESETON</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.rESETON ? <TextFormat value={cUSTOMEREntity.rESETON} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="uNBLOCKINGINSTITUTION">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.uNBLOCKINGINSTITUTION">U NBLOCKINGINSTITUTION</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.uNBLOCKINGINSTITUTION}</dd>
          <dt>
            <span id="pINBLOCK">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pINBLOCK">P INBLOCK</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.pINBLOCK}</dd>
          <dt>
            <span id="pINBLOCKBY">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pINBLOCKBY">P INBLOCKBY</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.pINBLOCKBY}</dd>
          <dt>
            <span id="pINBLOCKREMARKS">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pINBLOCKREMARKS">P INBLOCKREMARKS</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.pINBLOCKREMARKS}</dd>
          <dt>
            <span id="bLOCKINGINSTITUTION">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.bLOCKINGINSTITUTION">B LOCKINGINSTITUTION</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.bLOCKINGINSTITUTION}</dd>
          <dt>
            <span id="pINBLOCKON">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pINBLOCKON">P INBLOCKON</Translate>
            </span>
          </dt>
          <dd>
            {cUSTOMEREntity.pINBLOCKON ? <TextFormat value={cUSTOMEREntity.pINBLOCKON} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="aPPROVEDON">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.aPPROVEDON">A PPROVEDON</Translate>
            </span>
          </dt>
          <dd>
            {cUSTOMEREntity.aPPROVEDON ? <TextFormat value={cUSTOMEREntity.aPPROVEDON} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="pINUNBLOCKBY">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pINUNBLOCKBY">P INUNBLOCKBY</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.pINUNBLOCKBY}</dd>
          <dt>
            <span id="lOGGEDIN">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.lOGGEDIN">L OGGEDIN</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.lOGGEDIN}</dd>
          <dt>
            <span id="tRIALS">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.tRIALS">T RIALS</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.tRIALS}</dd>
          <dt>
            <span id="iDTYPE">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.iDTYPE">I DTYPE</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.iDTYPE}</dd>
          <dt>
            <span id="iDNUMBER">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.iDNUMBER">I DNUMBER</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.iDNUMBER}</dd>
          <dt>
            <span id="gENDER">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.gENDER">G ENDER</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.gENDER}</dd>
          <dt>
            <span id="cIF">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.cIF">C IF</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.cIF}</dd>
          <dt>
            <span id="dATEOFBIRTH">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.dATEOFBIRTH">D ATEOFBIRTH</Translate>
            </span>
          </dt>
          <dd>
            {cUSTOMEREntity.dATEOFBIRTH ? <TextFormat value={cUSTOMEREntity.dATEOFBIRTH} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="rEMARKS">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.rEMARKS">R EMARKS</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.rEMARKS}</dd>
          <dt>
            <span id="rESETIMSI">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.rESETIMSI">R ESETIMSI</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.rESETIMSI}</dd>
          <dt>
            <span id="iMSIRESETBY">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.iMSIRESETBY">I MSIRESETBY</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.iMSIRESETBY}</dd>
          <dt>
            <span id="fIRSTNAME">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.fIRSTNAME">F IRSTNAME</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.fIRSTNAME}</dd>
          <dt>
            <span id="sECONDNAME">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.sECONDNAME">S ECONDNAME</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.sECONDNAME}</dd>
          <dt>
            <span id="lASTNAME">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.lASTNAME">L ASTNAME</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.lASTNAME}</dd>
          <dt>
            <span id="pINBLOCKTIME">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pINBLOCKTIME">P INBLOCKTIME</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.pINBLOCKTIME}</dd>
          <dt>
            <span id="cUSTOMERSTATUS">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.cUSTOMERSTATUS">C USTOMERSTATUS</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.cUSTOMERSTATUS}</dd>
          <dt>
            <span id="uSERNAME">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.uSERNAME">U SERNAME</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.uSERNAME}</dd>
          <dt>
            <span id="pASSWORD">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pASSWORD">P ASSWORD</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.pASSWORD}</dd>
          <dt>
            <span id="dEVICEID">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.dEVICEID">D EVICEID</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.dEVICEID}</dd>
          <dt>
            <span id="cHANNEL">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.cHANNEL">C HANNEL</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.cHANNEL}</dd>
          <dt>
            <span id="pASSRESET">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pASSRESET">P ASSRESET</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.pASSRESET}</dd>
          <dt>
            <span id="pASSRESETBY">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pASSRESETBY">P ASSRESETBY</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.pASSRESETBY}</dd>
          <dt>
            <span id="pASSRESETON">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pASSRESETON">P ASSRESETON</Translate>
            </span>
          </dt>
          <dd>
            {cUSTOMEREntity.pASSRESETON ? <TextFormat value={cUSTOMEREntity.pASSRESETON} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="pASSBLOCK">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pASSBLOCK">P ASSBLOCK</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.pASSBLOCK}</dd>
          <dt>
            <span id="pASSBLOCKBY">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pASSBLOCKBY">P ASSBLOCKBY</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.pASSBLOCKBY}</dd>
          <dt>
            <span id="pASSBLOCKON">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pASSBLOCKON">P ASSBLOCKON</Translate>
            </span>
          </dt>
          <dd>
            {cUSTOMEREntity.pASSBLOCKON ? <TextFormat value={cUSTOMEREntity.pASSBLOCKON} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="pINMARKBLOCK">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pINMARKBLOCK">P INMARKBLOCK</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.pINMARKBLOCK}</dd>
          <dt>
            <span id="pASSMARKBLOCK">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pASSMARKBLOCK">P ASSMARKBLOCK</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.pASSMARKBLOCK}</dd>
          <dt>
            <span id="pASSRESETREMARKS">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pASSRESETREMARKS">P ASSRESETREMARKS</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.pASSRESETREMARKS}</dd>
          <dt>
            <span id="pASSBLOCKREMARKS">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pASSBLOCKREMARKS">P ASSBLOCKREMARKS</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.pASSBLOCKREMARKS}</dd>
          <dt>
            <span id="pASSUNBLOCKBY">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pASSUNBLOCKBY">P ASSUNBLOCKBY</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.pASSUNBLOCKBY}</dd>
          <dt>
            <span id="pASSTRIALS">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.pASSTRIALS">P ASSTRIALS</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.pASSTRIALS}</dd>
          <dt>
            <span id="aPPACTIVE">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.aPPACTIVE">A PPACTIVE</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.aPPACTIVE}</dd>
          <dt>
            <span id="lASTLOGIN">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.lASTLOGIN">L ASTLOGIN</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.lASTLOGIN}</dd>
          <dt>
            <span id="aPPMARKEDDISABLE">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.aPPMARKEDDISABLE">A PPMARKEDDISABLE</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.aPPMARKEDDISABLE}</dd>
          <dt>
            <span id="dISABLEBY">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.dISABLEBY">D ISABLEBY</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.dISABLEBY}</dd>
          <dt>
            <span id="aPPROVEDISABLEBY">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.aPPROVEDISABLEBY">A PPROVEDISABLEBY</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.aPPROVEDISABLEBY}</dd>
          <dt>
            <span id="aPPMARKEDENABLE">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.aPPMARKEDENABLE">A PPMARKEDENABLE</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.aPPMARKEDENABLE}</dd>
          <dt>
            <span id="eNABLEBY">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.eNABLEBY">E NABLEBY</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.eNABLEBY}</dd>
          <dt>
            <span id="aPPROVEDENABLEBY">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.aPPROVEDENABLEBY">A PPROVEDENABLEBY</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.aPPROVEDENABLEBY}</dd>
          <dt>
            <span id="mARKEDDEACTIVATE">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.mARKEDDEACTIVATE">M ARKEDDEACTIVATE</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.mARKEDDEACTIVATE}</dd>
          <dt>
            <span id="aPPFIRSTLOGIN">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.aPPFIRSTLOGIN">A PPFIRSTLOGIN</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.aPPFIRSTLOGIN}</dd>
          <dt>
            <span id="aTMTRIALS">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.aTMTRIALS">A TMTRIALS</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.aTMTRIALS}</dd>
          <dt>
            <span id="sHORCUTS">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.sHORCUTS">S HORCUTS</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.sHORCUTS}</dd>
          <dt>
            <span id="mARKEDACTIVATE">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.mARKEDACTIVATE">M ARKEDACTIVATE</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.mARKEDACTIVATE}</dd>
          <dt>
            <span id="tOWN">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.tOWN">T OWN</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.tOWN}</dd>
          <dt>
            <span id="aPPROVEDDISABLEON">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.aPPROVEDDISABLEON">A PPROVEDDISABLEON</Translate>
            </span>
          </dt>
          <dd>
            {cUSTOMEREntity.aPPROVEDDISABLEON ? (
              <TextFormat value={cUSTOMEREntity.aPPROVEDDISABLEON} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="dISABLEDON">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.dISABLEDON">D ISABLEDON</Translate>
            </span>
          </dt>
          <dd>
            {cUSTOMEREntity.dISABLEDON ? <TextFormat value={cUSTOMEREntity.dISABLEDON} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="rESETAPPROVEON">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.rESETAPPROVEON">R ESETAPPROVEON</Translate>
            </span>
          </dt>
          <dd>
            {cUSTOMEREntity.rESETAPPROVEON ? (
              <TextFormat value={cUSTOMEREntity.rESETAPPROVEON} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="dELETEDBY">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.dELETEDBY">D ELETEDBY</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.dELETEDBY}</dd>
          <dt>
            <span id="qUESTIONSASKED">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.qUESTIONSASKED">Q UESTIONSASKED</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.qUESTIONSASKED}</dd>
          <dt>
            <span id="qUESTIONSTRIALS">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.qUESTIONSTRIALS">Q UESTIONSTRIALS</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.qUESTIONSTRIALS}</dd>
          <dt>
            <span id="qUESTIONSANSWERED">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.qUESTIONSANSWERED">Q UESTIONSANSWERED</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.qUESTIONSANSWERED}</dd>
          <dt>
            <span id="vALIDOTP">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.vALIDOTP">V ALIDOTP</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.vALIDOTP}</dd>
          <dt>
            <span id="aCTIVATEDBY">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.aCTIVATEDBY">A CTIVATEDBY</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.aCTIVATEDBY}</dd>
          <dt>
            <span id="aCTIVATEDON">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.aCTIVATEDON">A CTIVATEDON</Translate>
            </span>
          </dt>
          <dd>
            {cUSTOMEREntity.aCTIVATEDON ? <TextFormat value={cUSTOMEREntity.aCTIVATEDON} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="bRANCHCODE">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.bRANCHCODE">B RANCHCODE</Translate>
            </span>
          </dt>
          <dd>{cUSTOMEREntity.bRANCHCODE}</dd>
        </dl>
        <Button tag={Link} to="/customer" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/customer/${cUSTOMEREntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default CUSTOMERDetail;
