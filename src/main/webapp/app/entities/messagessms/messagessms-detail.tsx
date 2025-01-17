import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './messagessms.reducer';

export const MESSAGESSMSDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const mESSAGESSMSEntity = useAppSelector(state => state.mESSAGESSMS.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="mESSAGESSMSDetailsHeading">
          <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.detail.title">MESSAGESSMS</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{mESSAGESSMSEntity.id}</dd>
          <dt>
            <span id="tRNDATETIME">
              <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.tRNDATETIME">T RNDATETIME</Translate>
            </span>
          </dt>
          <dd>
            {mESSAGESSMSEntity.tRNDATETIME ? (
              <TextFormat value={mESSAGESSMSEntity.tRNDATETIME} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="pHONENUMBER">
              <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.pHONENUMBER">P HONENUMBER</Translate>
            </span>
          </dt>
          <dd>{mESSAGESSMSEntity.pHONENUMBER}</dd>
          <dt>
            <span id="tRANSACTIONNO">
              <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.tRANSACTIONNO">T RANSACTIONNO</Translate>
            </span>
          </dt>
          <dd>{mESSAGESSMSEntity.tRANSACTIONNO}</dd>
          <dt>
            <span id="aCCOUNTNUMBER">
              <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.aCCOUNTNUMBER">A CCOUNTNUMBER</Translate>
            </span>
          </dt>
          <dd>{mESSAGESSMSEntity.aCCOUNTNUMBER}</dd>
          <dt>
            <span id="mESSAGE">
              <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.mESSAGE">M ESSAGE</Translate>
            </span>
          </dt>
          <dd>{mESSAGESSMSEntity.mESSAGE}</dd>
          <dt>
            <span id="cHANNEL">
              <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.cHANNEL">C HANNEL</Translate>
            </span>
          </dt>
          <dd>{mESSAGESSMSEntity.cHANNEL}</dd>
          <dt>
            <span id="tRIALS">
              <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.tRIALS">T RIALS</Translate>
            </span>
          </dt>
          <dd>{mESSAGESSMSEntity.tRIALS}</dd>
          <dt>
            <span id="pRIORITY">
              <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.pRIORITY">P RIORITY</Translate>
            </span>
          </dt>
          <dd>{mESSAGESSMSEntity.pRIORITY}</dd>
          <dt>
            <span id="rESPONSECODE">
              <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.rESPONSECODE">R ESPONSECODE</Translate>
            </span>
          </dt>
          <dd>{mESSAGESSMSEntity.rESPONSECODE}</dd>
          <dt>
            <span id="rESPONSEMSG">
              <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.rESPONSEMSG">R ESPONSEMSG</Translate>
            </span>
          </dt>
          <dd>{mESSAGESSMSEntity.rESPONSEMSG}</dd>
          <dt>
            <span id="sENT">
              <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.sENT">S ENT</Translate>
            </span>
          </dt>
          <dd>{mESSAGESSMSEntity.sENT}</dd>
          <dt>
            <span id="dELIVERED">
              <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.dELIVERED">D ELIVERED</Translate>
            </span>
          </dt>
          <dd>{mESSAGESSMSEntity.dELIVERED}</dd>
          <dt>
            <span id="tXNTYPE">
              <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.tXNTYPE">T XNTYPE</Translate>
            </span>
          </dt>
          <dd>{mESSAGESSMSEntity.tXNTYPE}</dd>
          <dt>
            <span id="eRROREXCEPTION">
              <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.eRROREXCEPTION">E RROREXCEPTION</Translate>
            </span>
          </dt>
          <dd>{mESSAGESSMSEntity.eRROREXCEPTION}</dd>
          <dt>
            <span id="dATECREATED">
              <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.dATECREATED">D ATECREATED</Translate>
            </span>
          </dt>
          <dd>
            {mESSAGESSMSEntity.dATECREATED ? (
              <TextFormat value={mESSAGESSMSEntity.dATECREATED} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="dATESENT">
              <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.dATESENT">D ATESENT</Translate>
            </span>
          </dt>
          <dd>{mESSAGESSMSEntity.dATESENT}</dd>
          <dt>
            <span id="rTPSREQTIME">
              <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.rTPSREQTIME">R TPSREQTIME</Translate>
            </span>
          </dt>
          <dd>{mESSAGESSMSEntity.rTPSREQTIME}</dd>
          <dt>
            <span id="fXGENERATED">
              <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.fXGENERATED">F XGENERATED</Translate>
            </span>
          </dt>
          <dd>{mESSAGESSMSEntity.fXGENERATED}</dd>
          <dt>
            <span id="tAXPROCESSED">
              <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.tAXPROCESSED">T AXPROCESSED</Translate>
            </span>
          </dt>
          <dd>{mESSAGESSMSEntity.tAXPROCESSED}</dd>
          <dt>
            <span id="bATCHNUMBER">
              <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.bATCHNUMBER">B ATCHNUMBER</Translate>
            </span>
          </dt>
          <dd>{mESSAGESSMSEntity.bATCHNUMBER}</dd>
          <dt>
            <span id="bATCHNUMBERTAX">
              <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.bATCHNUMBERTAX">B ATCHNUMBERTAX</Translate>
            </span>
          </dt>
          <dd>{mESSAGESSMSEntity.bATCHNUMBERTAX}</dd>
          <dt>
            <span id="rESPONSETIME">
              <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.rESPONSETIME">R ESPONSETIME</Translate>
            </span>
          </dt>
          <dd>{mESSAGESSMSEntity.rESPONSETIME}</dd>
          <dt>
            <span id="pDUSEQID">
              <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.pDUSEQID">P DUSEQID</Translate>
            </span>
          </dt>
          <dd>{mESSAGESSMSEntity.pDUSEQID}</dd>
          <dt>
            <span id="rEMARKS">
              <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.rEMARKS">R EMARKS</Translate>
            </span>
          </dt>
          <dd>{mESSAGESSMSEntity.rEMARKS}</dd>
          <dt>
            <span id="rESENDBY">
              <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.rESENDBY">R ESENDBY</Translate>
            </span>
          </dt>
          <dd>{mESSAGESSMSEntity.rESENDBY}</dd>
        </dl>
        <Button tag={Link} to="/messagessms" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/messagessms/${mESSAGESSMSEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default MESSAGESSMSDetail;
