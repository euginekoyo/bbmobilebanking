import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './limits.reducer';

export const LIMITSDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const lIMITSEntity = useAppSelector(state => state.lIMITS.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="lIMITSDetailsHeading">
          <Translate contentKey="bbMobileBankingAdminApp.lIMITS.detail.title">LIMITS</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{lIMITSEntity.id}</dd>
          <dt>
            <span id="tRANSACTIONTYPE">
              <Translate contentKey="bbMobileBankingAdminApp.lIMITS.tRANSACTIONTYPE">T RANSACTIONTYPE</Translate>
            </span>
          </dt>
          <dd>{lIMITSEntity.tRANSACTIONTYPE}</dd>
          <dt>
            <span id="pROCODE">
              <Translate contentKey="bbMobileBankingAdminApp.lIMITS.pROCODE">P ROCODE</Translate>
            </span>
          </dt>
          <dd>{lIMITSEntity.pROCODE}</dd>
          <dt>
            <span id="cHANNEL">
              <Translate contentKey="bbMobileBankingAdminApp.lIMITS.cHANNEL">C HANNEL</Translate>
            </span>
          </dt>
          <dd>{lIMITSEntity.cHANNEL}</dd>
          <dt>
            <span id="tRANSACTIONLIMIT">
              <Translate contentKey="bbMobileBankingAdminApp.lIMITS.tRANSACTIONLIMIT">T RANSACTIONLIMIT</Translate>
            </span>
          </dt>
          <dd>{lIMITSEntity.tRANSACTIONLIMIT}</dd>
          <dt>
            <span id="dAILYLIMIT">
              <Translate contentKey="bbMobileBankingAdminApp.lIMITS.dAILYLIMIT">D AILYLIMIT</Translate>
            </span>
          </dt>
          <dd>{lIMITSEntity.dAILYLIMIT}</dd>
          <dt>
            <span id="rEGISTEREDBY">
              <Translate contentKey="bbMobileBankingAdminApp.lIMITS.rEGISTEREDBY">R EGISTEREDBY</Translate>
            </span>
          </dt>
          <dd>{lIMITSEntity.rEGISTEREDBY}</dd>
          <dt>
            <span id="rEGISTEREDDATE">
              <Translate contentKey="bbMobileBankingAdminApp.lIMITS.rEGISTEREDDATE">R EGISTEREDDATE</Translate>
            </span>
          </dt>
          <dd>{lIMITSEntity.rEGISTEREDDATE}</dd>
          <dt>
            <span id="aPPROVED">
              <Translate contentKey="bbMobileBankingAdminApp.lIMITS.aPPROVED">A PPROVED</Translate>
            </span>
          </dt>
          <dd>{lIMITSEntity.aPPROVED}</dd>
          <dt>
            <span id="aPPROVEDBY">
              <Translate contentKey="bbMobileBankingAdminApp.lIMITS.aPPROVEDBY">A PPROVEDBY</Translate>
            </span>
          </dt>
          <dd>{lIMITSEntity.aPPROVEDBY}</dd>
          <dt>
            <span id="aPPROVEDDATE">
              <Translate contentKey="bbMobileBankingAdminApp.lIMITS.aPPROVEDDATE">A PPROVEDDATE</Translate>
            </span>
          </dt>
          <dd>{lIMITSEntity.aPPROVEDDATE}</dd>
          <dt>
            <span id="uPDATEDBY">
              <Translate contentKey="bbMobileBankingAdminApp.lIMITS.uPDATEDBY">U PDATEDBY</Translate>
            </span>
          </dt>
          <dd>{lIMITSEntity.uPDATEDBY}</dd>
          <dt>
            <span id="uPDATEDDATE">
              <Translate contentKey="bbMobileBankingAdminApp.lIMITS.uPDATEDDATE">U PDATEDDATE</Translate>
            </span>
          </dt>
          <dd>{lIMITSEntity.uPDATEDDATE}</dd>
          <dt>
            <span id="rEWORK">
              <Translate contentKey="bbMobileBankingAdminApp.lIMITS.rEWORK">R EWORK</Translate>
            </span>
          </dt>
          <dd>{lIMITSEntity.rEWORK}</dd>
          <dt>
            <span id="rEWORKBY">
              <Translate contentKey="bbMobileBankingAdminApp.lIMITS.rEWORKBY">R EWORKBY</Translate>
            </span>
          </dt>
          <dd>{lIMITSEntity.rEWORKBY}</dd>
          <dt>
            <span id="sESSIONID">
              <Translate contentKey="bbMobileBankingAdminApp.lIMITS.sESSIONID">S ESSIONID</Translate>
            </span>
          </dt>
          <dd>{lIMITSEntity.sESSIONID}</dd>
        </dl>
        <Button tag={Link} to="/limits" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/limits/${lIMITSEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default LIMITSDetail;
