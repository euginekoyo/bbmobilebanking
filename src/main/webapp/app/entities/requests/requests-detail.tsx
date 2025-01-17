import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './requests.reducer';

export const REQUESTSDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const rEQUESTSEntity = useAppSelector(state => state.rEQUESTS.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="rEQUESTSDetailsHeading">
          <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.detail.title">REQUESTS</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{rEQUESTSEntity.id}</dd>
          <dt>
            <span id="mOBILENUMBER">
              <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.mOBILENUMBER">M OBILENUMBER</Translate>
            </span>
          </dt>
          <dd>{rEQUESTSEntity.mOBILENUMBER}</dd>
          <dt>
            <span id="aCCOUNTNO">
              <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.aCCOUNTNO">A CCOUNTNO</Translate>
            </span>
          </dt>
          <dd>{rEQUESTSEntity.aCCOUNTNO}</dd>
          <dt>
            <span id="cURRENCY">
              <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.cURRENCY">C URRENCY</Translate>
            </span>
          </dt>
          <dd>{rEQUESTSEntity.cURRENCY}</dd>
          <dt>
            <span id="cIF">
              <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.cIF">C IF</Translate>
            </span>
          </dt>
          <dd>{rEQUESTSEntity.cIF}</dd>
          <dt>
            <span id="rEQUESTTYPE">
              <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.rEQUESTTYPE">R EQUESTTYPE</Translate>
            </span>
          </dt>
          <dd>{rEQUESTSEntity.rEQUESTTYPE}</dd>
          <dt>
            <span id="rEQUESTCHARGE">
              <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.rEQUESTCHARGE">R EQUESTCHARGE</Translate>
            </span>
          </dt>
          <dd>{rEQUESTSEntity.rEQUESTCHARGE}</dd>
          <dt>
            <span id="rEQUESTSTATUS">
              <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.rEQUESTSTATUS">R EQUESTSTATUS</Translate>
            </span>
          </dt>
          <dd>{rEQUESTSEntity.rEQUESTSTATUS}</dd>
          <dt>
            <span id="dATEREQUESTED">
              <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.dATEREQUESTED">D ATEREQUESTED</Translate>
            </span>
          </dt>
          <dd>
            {rEQUESTSEntity.dATEREQUESTED ? <TextFormat value={rEQUESTSEntity.dATEREQUESTED} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="tRNREFNO">
              <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.tRNREFNO">T RNREFNO</Translate>
            </span>
          </dt>
          <dd>{rEQUESTSEntity.tRNREFNO}</dd>
          <dt>
            <span id="nOOFBOOKS">
              <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.nOOFBOOKS">N OOFBOOKS</Translate>
            </span>
          </dt>
          <dd>{rEQUESTSEntity.nOOFBOOKS}</dd>
          <dt>
            <span id="nOOFLEAVES">
              <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.nOOFLEAVES">N OOFLEAVES</Translate>
            </span>
          </dt>
          <dd>{rEQUESTSEntity.nOOFLEAVES}</dd>
          <dt>
            <span id="aPPROVED">
              <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.aPPROVED">A PPROVED</Translate>
            </span>
          </dt>
          <dd>{rEQUESTSEntity.aPPROVED}</dd>
          <dt>
            <span id="cHANNEL">
              <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.cHANNEL">C HANNEL</Translate>
            </span>
          </dt>
          <dd>{rEQUESTSEntity.cHANNEL}</dd>
          <dt>
            <span id="aPPROVEDBY">
              <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.aPPROVEDBY">A PPROVEDBY</Translate>
            </span>
          </dt>
          <dd>{rEQUESTSEntity.aPPROVEDBY}</dd>
          <dt>
            <span id="aPPROVEDON">
              <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.aPPROVEDON">A PPROVEDON</Translate>
            </span>
          </dt>
          <dd>
            {rEQUESTSEntity.aPPROVEDON ? <TextFormat value={rEQUESTSEntity.aPPROVEDON} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="cHECKERREMARKS">
              <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.cHECKERREMARKS">C HECKERREMARKS</Translate>
            </span>
          </dt>
          <dd>{rEQUESTSEntity.cHECKERREMARKS}</dd>
          <dt>
            <span id="rESPCODE">
              <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.rESPCODE">R ESPCODE</Translate>
            </span>
          </dt>
          <dd>{rEQUESTSEntity.rESPCODE}</dd>
          <dt>
            <span id="rESPDESCRIPTION">
              <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.rESPDESCRIPTION">R ESPDESCRIPTION</Translate>
            </span>
          </dt>
          <dd>{rEQUESTSEntity.rESPDESCRIPTION}</dd>
          <dt>
            <span id="dATERESPONDED">
              <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.dATERESPONDED">D ATERESPONDED</Translate>
            </span>
          </dt>
          <dd>
            {rEQUESTSEntity.dATERESPONDED ? <TextFormat value={rEQUESTSEntity.dATERESPONDED} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="cUSTOMERNAME">
              <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.cUSTOMERNAME">C USTOMERNAME</Translate>
            </span>
          </dt>
          <dd>{rEQUESTSEntity.cUSTOMERNAME}</dd>
          <dt>
            <span id="rEJECTED">
              <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.rEJECTED">R EJECTED</Translate>
            </span>
          </dt>
          <dd>{rEQUESTSEntity.rEJECTED}</dd>
          <dt>
            <span id="rEJECTEDBY">
              <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.rEJECTEDBY">R EJECTEDBY</Translate>
            </span>
          </dt>
          <dd>{rEQUESTSEntity.rEJECTEDBY}</dd>
          <dt>
            <span id="rEJECTEDON">
              <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.rEJECTEDON">R EJECTEDON</Translate>
            </span>
          </dt>
          <dd>
            {rEQUESTSEntity.rEJECTEDON ? <TextFormat value={rEQUESTSEntity.rEJECTEDON} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
        </dl>
        <Button tag={Link} to="/requests" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/requests/${rEQUESTSEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default REQUESTSDetail;
