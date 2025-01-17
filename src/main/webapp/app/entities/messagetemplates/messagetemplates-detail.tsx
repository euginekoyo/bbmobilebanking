import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './messagetemplates.reducer';

export const MESSAGETEMPLATESDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const mESSAGETEMPLATESEntity = useAppSelector(state => state.mESSAGETEMPLATES.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="mESSAGETEMPLATESDetailsHeading">
          <Translate contentKey="bbMobileBankingAdminApp.mESSAGETEMPLATES.detail.title">MESSAGETEMPLATES</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{mESSAGETEMPLATESEntity.id}</dd>
          <dt>
            <span id="mESSAGETYPE">
              <Translate contentKey="bbMobileBankingAdminApp.mESSAGETEMPLATES.mESSAGETYPE">M ESSAGETYPE</Translate>
            </span>
          </dt>
          <dd>{mESSAGETEMPLATESEntity.mESSAGETYPE}</dd>
          <dt>
            <span id="dESCRIPTION">
              <Translate contentKey="bbMobileBankingAdminApp.mESSAGETEMPLATES.dESCRIPTION">D ESCRIPTION</Translate>
            </span>
          </dt>
          <dd>{mESSAGETEMPLATESEntity.dESCRIPTION}</dd>
          <dt>
            <span id="mESSAGEENGLISH">
              <Translate contentKey="bbMobileBankingAdminApp.mESSAGETEMPLATES.mESSAGEENGLISH">M ESSAGEENGLISH</Translate>
            </span>
          </dt>
          <dd>{mESSAGETEMPLATESEntity.mESSAGEENGLISH}</dd>
          <dt>
            <span id="mESSAGESOMALI">
              <Translate contentKey="bbMobileBankingAdminApp.mESSAGETEMPLATES.mESSAGESOMALI">M ESSAGESOMALI</Translate>
            </span>
          </dt>
          <dd>{mESSAGETEMPLATESEntity.mESSAGESOMALI}</dd>
          <dt>
            <span id="cREATEDON">
              <Translate contentKey="bbMobileBankingAdminApp.mESSAGETEMPLATES.cREATEDON">C REATEDON</Translate>
            </span>
          </dt>
          <dd>
            {mESSAGETEMPLATESEntity.cREATEDON ? (
              <TextFormat value={mESSAGETEMPLATESEntity.cREATEDON} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
        </dl>
        <Button tag={Link} to="/messagetemplates" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/messagetemplates/${mESSAGETEMPLATESEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default MESSAGETEMPLATESDetail;
