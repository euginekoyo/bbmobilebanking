import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './billers.reducer';

export const BILLERSDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const bILLERSEntity = useAppSelector(state => state.bILLERS.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="bILLERSDetailsHeading">
          <Translate contentKey="bbMobileBankingAdminApp.bILLERS.detail.title">BILLERS</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{bILLERSEntity.id}</dd>
          <dt>
            <span id="bILLERID">
              <Translate contentKey="bbMobileBankingAdminApp.bILLERS.bILLERID">B ILLERID</Translate>
            </span>
          </dt>
          <dd>{bILLERSEntity.bILLERID}</dd>
          <dt>
            <span id="dESCRIPTION">
              <Translate contentKey="bbMobileBankingAdminApp.bILLERS.dESCRIPTION">D ESCRIPTION</Translate>
            </span>
          </dt>
          <dd>{bILLERSEntity.dESCRIPTION}</dd>
          <dt>
            <span id="bILLERCOLLECTIONACCOUNT">
              <Translate contentKey="bbMobileBankingAdminApp.bILLERS.bILLERCOLLECTIONACCOUNT">B ILLERCOLLECTIONACCOUNT</Translate>
            </span>
          </dt>
          <dd>{bILLERSEntity.bILLERCOLLECTIONACCOUNT}</dd>
          <dt>
            <span id="dATECREATED">
              <Translate contentKey="bbMobileBankingAdminApp.bILLERS.dATECREATED">D ATECREATED</Translate>
            </span>
          </dt>
          <dd>
            {bILLERSEntity.dATECREATED ? <TextFormat value={bILLERSEntity.dATECREATED} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="cREATEDBY">
              <Translate contentKey="bbMobileBankingAdminApp.bILLERS.cREATEDBY">C REATEDBY</Translate>
            </span>
          </dt>
          <dd>{bILLERSEntity.cREATEDBY}</dd>
          <dt>
            <span id="aPPROVED">
              <Translate contentKey="bbMobileBankingAdminApp.bILLERS.aPPROVED">A PPROVED</Translate>
            </span>
          </dt>
          <dd>{bILLERSEntity.aPPROVED}</dd>
          <dt>
            <span id="aPPROVEDBY">
              <Translate contentKey="bbMobileBankingAdminApp.bILLERS.aPPROVEDBY">A PPROVEDBY</Translate>
            </span>
          </dt>
          <dd>{bILLERSEntity.aPPROVEDBY}</dd>
          <dt>
            <span id="aPPROVEDDATE">
              <Translate contentKey="bbMobileBankingAdminApp.bILLERS.aPPROVEDDATE">A PPROVEDDATE</Translate>
            </span>
          </dt>
          <dd>
            {bILLERSEntity.aPPROVEDDATE ? <TextFormat value={bILLERSEntity.aPPROVEDDATE} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="cHARGABLEPRODUCTID">
              <Translate contentKey="bbMobileBankingAdminApp.bILLERS.cHARGABLEPRODUCTID">C HARGABLEPRODUCTID</Translate>
            </span>
          </dt>
          <dd>{bILLERSEntity.cHARGABLEPRODUCTID}</dd>
          <dt>
            <span id="nONCHARGABLEPRODUCTID">
              <Translate contentKey="bbMobileBankingAdminApp.bILLERS.nONCHARGABLEPRODUCTID">N ONCHARGABLEPRODUCTID</Translate>
            </span>
          </dt>
          <dd>{bILLERSEntity.nONCHARGABLEPRODUCTID}</dd>
          <dt>
            <span id="uSDBILLERCOLLECTIONACCOUNT">
              <Translate contentKey="bbMobileBankingAdminApp.bILLERS.uSDBILLERCOLLECTIONACCOUNT">U SDBILLERCOLLECTIONACCOUNT</Translate>
            </span>
          </dt>
          <dd>{bILLERSEntity.uSDBILLERCOLLECTIONACCOUNT}</dd>
          <dt>
            <span id="eNABLEDUPLICATECHECK">
              <Translate contentKey="bbMobileBankingAdminApp.bILLERS.eNABLEDUPLICATECHECK">E NABLEDUPLICATECHECK</Translate>
            </span>
          </dt>
          <dd>{bILLERSEntity.eNABLEDUPLICATECHECK}</dd>
          <dt>
            <span id="rEMARKS">
              <Translate contentKey="bbMobileBankingAdminApp.bILLERS.rEMARKS">R EMARKS</Translate>
            </span>
          </dt>
          <dd>{bILLERSEntity.rEMARKS}</dd>
          <dt>
            <span id="sESSIONID">
              <Translate contentKey="bbMobileBankingAdminApp.bILLERS.sESSIONID">S ESSIONID</Translate>
            </span>
          </dt>
          <dd>{bILLERSEntity.sESSIONID}</dd>
          <dt>
            <span id="rEWORKBY">
              <Translate contentKey="bbMobileBankingAdminApp.bILLERS.rEWORKBY">R EWORKBY</Translate>
            </span>
          </dt>
          <dd>{bILLERSEntity.rEWORKBY}</dd>
          <dt>
            <span id="sTATUS">
              <Translate contentKey="bbMobileBankingAdminApp.bILLERS.sTATUS">S TATUS</Translate>
            </span>
          </dt>
          <dd>{bILLERSEntity.sTATUS}</dd>
          <dt>
            <span id="aCTIVE">
              <Translate contentKey="bbMobileBankingAdminApp.bILLERS.aCTIVE">A CTIVE</Translate>
            </span>
          </dt>
          <dd>{bILLERSEntity.aCTIVE}</dd>
          <dt>
            <span id="rEWORK">
              <Translate contentKey="bbMobileBankingAdminApp.bILLERS.rEWORK">R EWORK</Translate>
            </span>
          </dt>
          <dd>{bILLERSEntity.rEWORK}</dd>
        </dl>
        <Button tag={Link} to="/billers" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/billers/${bILLERSEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default BILLERSDetail;
