import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './transactions.reducer';

export const TRANSACTIONSDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const tRANSACTIONSEntity = useAppSelector(state => state.tRANSACTIONS.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="tRANSACTIONSDetailsHeading">
          <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.detail.title">TRANSACTIONS</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.id}</dd>
          <dt>
            <span id="pROCESSED">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.pROCESSED">P ROCESSED</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.pROCESSED}</dd>
          <dt>
            <span id="iNCOMINGBITMAP">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.iNCOMINGBITMAP">I NCOMINGBITMAP</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.iNCOMINGBITMAP}</dd>
          <dt>
            <span id="oUTGOINGBITMAP">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.oUTGOINGBITMAP">O UTGOINGBITMAP</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.oUTGOINGBITMAP}</dd>
          <dt>
            <span id="iNMESSAGE">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.iNMESSAGE">I NMESSAGE</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.iNMESSAGE}</dd>
          <dt>
            <span id="mESSAGETOCBS">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.mESSAGETOCBS">M ESSAGETOCBS</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.mESSAGETOCBS}</dd>
          <dt>
            <span id="mESSAGEFROMCBS">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.mESSAGEFROMCBS">M ESSAGEFROMCBS</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.mESSAGEFROMCBS}</dd>
          <dt>
            <span id="cBSPROCESS">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.cBSPROCESS">C BSPROCESS</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.cBSPROCESS}</dd>
          <dt>
            <span id="cBSONLINE">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.cBSONLINE">C BSONLINE</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.cBSONLINE}</dd>
          <dt>
            <span id="cBSRESPONSE">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.cBSRESPONSE">C BSRESPONSE</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.cBSRESPONSE}</dd>
          <dt>
            <span id="rESPONSEMESSAGE">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.rESPONSEMESSAGE">R ESPONSEMESSAGE</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.rESPONSEMESSAGE}</dd>
          <dt>
            <span id="rESPONSESENT">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.rESPONSESENT">R ESPONSESENT</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.rESPONSESENT}</dd>
          <dt>
            <span id="cHANNEL">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.cHANNEL">C HANNEL</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.cHANNEL}</dd>
          <dt>
            <span id="oRIGINALDATA">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.oRIGINALDATA">O RIGINALDATA</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.oRIGINALDATA}</dd>
          <dt>
            <span id="fIELD39RESP">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.fIELD39RESP">F IELD 39 RESP</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.fIELD39RESP}</dd>
          <dt>
            <span id="nARRATION">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.nARRATION">N ARRATION</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.nARRATION}</dd>
          <dt>
            <span id="aUTHORISED">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.aUTHORISED">A UTHORISED</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.aUTHORISED}</dd>
          <dt>
            <span id="bRANCHCODE">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.bRANCHCODE">B RANCHCODE</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.bRANCHCODE}</dd>
          <dt>
            <span id="fIELD39ORIGINAL">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.fIELD39ORIGINAL">F IELD 39 ORIGINAL</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.fIELD39ORIGINAL}</dd>
          <dt>
            <span id="mESSAGECLASS">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.mESSAGECLASS">M ESSAGECLASS</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.mESSAGECLASS}</dd>
          <dt>
            <span id="tXNCODE">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.tXNCODE">T XNCODE</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.tXNCODE}</dd>
          <dt>
            <span id="cURRCODE">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.cURRCODE">C URRCODE</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.cURRCODE}</dd>
          <dt>
            <span id="dEVICE">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.dEVICE">D EVICE</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.dEVICE}</dd>
          <dt>
            <span id="bRANCH2">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.bRANCH2">B RANCH 2</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.bRANCH2}</dd>
          <dt>
            <span id="longERBRANCH">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.longERBRANCH">Long ERBRANCH</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.longERBRANCH}</dd>
          <dt>
            <span id="dATEX">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.dATEX">D ATEX</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.dATEX ? <TextFormat value={tRANSACTIONSEntity.dATEX} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="tIMEX">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.tIMEX">T IMEX</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.tIMEX}</dd>
          <dt>
            <span id="pOSTED">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.pOSTED">P OSTED</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.pOSTED}</dd>
          <dt>
            <span id="aTTEMPTS">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.aTTEMPTS">A TTEMPTS</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.aTTEMPTS}</dd>
          <dt>
            <span id="oRIGINALDATA2">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.oRIGINALDATA2">O RIGINALDATA 2</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.oRIGINALDATA2}</dd>
          <dt>
            <span id="cOMMISSION">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.cOMMISSION">C OMMISSION</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.cOMMISSION}</dd>
          <dt>
            <span id="rESPONSECREATED">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.rESPONSECREATED">R ESPONSECREATED</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.rESPONSECREATED}</dd>
          <dt>
            <span id="oNLINE">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.oNLINE">O NLINE</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.oNLINE}</dd>
          <dt>
            <span id="oRIGINALDATA3">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.oRIGINALDATA3">O RIGINALDATA 3</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.oRIGINALDATA3}</dd>
          <dt>
            <span id="tOSWITCH">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.tOSWITCH">T OSWITCH</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.tOSWITCH}</dd>
          <dt>
            <span id="fROMSWITCH">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.fROMSWITCH">F ROMSWITCH</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.fROMSWITCH}</dd>
          <dt>
            <span id="tOCBS">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.tOCBS">T OCBS</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.tOCBS}</dd>
          <dt>
            <span id="fROMCBS">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.fROMCBS">F ROMCBS</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.fROMCBS}</dd>
          <dt>
            <span id="pOSTINGLEGS">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.pOSTINGLEGS">P OSTINGLEGS</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.pOSTINGLEGS}</dd>
          <dt>
            <span id="cOMMISSIONTXNCODE">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.cOMMISSIONTXNCODE">C OMMISSIONTXNCODE</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.cOMMISSIONTXNCODE}</dd>
          <dt>
            <span id="hOSTREF">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.hOSTREF">H OSTREF</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.hOSTREF}</dd>
          <dt>
            <span id="rEQUESTCREATED">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.rEQUESTCREATED">R EQUESTCREATED</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.rEQUESTCREATED}</dd>
          <dt>
            <span id="rEQUESTMESSAGE">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.rEQUESTMESSAGE">R EQUESTMESSAGE</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.rEQUESTMESSAGE}</dd>
          <dt>
            <span id="oUTGOINGBITMAPFLEX">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.oUTGOINGBITMAPFLEX">O UTGOINGBITMAPFLEX</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.oUTGOINGBITMAPFLEX}</dd>
          <dt>
            <span id="iNCOMINGBITMAPFLEX">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.iNCOMINGBITMAPFLEX">I NCOMINGBITMAPFLEX</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.iNCOMINGBITMAPFLEX}</dd>
          <dt>
            <span id="rEQUESTSENT">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.rEQUESTSENT">R EQUESTSENT</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.rEQUESTSENT}</dd>
          <dt>
            <span id="mINICBS">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.mINICBS">M INICBS</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.mINICBS}</dd>
          <dt>
            <span id="rEVERSED">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.rEVERSED">R EVERSED</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.rEVERSED}</dd>
          <dt>
            <span id="oFFLINESENTTOHOST">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.oFFLINESENTTOHOST">O FFLINESENTTOHOST</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.oFFLINESENTTOHOST}</dd>
          <dt>
            <span id="oFFLINERESPONSE">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.oFFLINERESPONSE">O FFLINERESPONSE</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.oFFLINERESPONSE}</dd>
          <dt>
            <span id="sOURCELongERFACE">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.sOURCELongERFACE">S OURCE Long ERFACE</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.sOURCELongERFACE}</dd>
          <dt>
            <span id="mTIRRN">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.mTIRRN">M TIRRN</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.mTIRRN}</dd>
          <dt>
            <span id="hOSTRESPONSECODE">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.hOSTRESPONSECODE">H OSTRESPONSECODE</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.hOSTRESPONSECODE}</dd>
          <dt>
            <span id="fIELD48">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.fIELD48">F IELD 48</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.fIELD48}</dd>
          <dt>
            <span id="sOURCE">
              <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.sOURCE">S OURCE</Translate>
            </span>
          </dt>
          <dd>{tRANSACTIONSEntity.sOURCE}</dd>
        </dl>
        <Button tag={Link} to="/transactions" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/transactions/${tRANSACTIONSEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default TRANSACTIONSDetail;
