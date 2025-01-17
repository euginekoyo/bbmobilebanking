import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './channels.reducer';

export const CHANNELSDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const cHANNELSEntity = useAppSelector(state => state.cHANNELS.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="cHANNELSDetailsHeading">
          <Translate contentKey="bbMobileBankingAdminApp.cHANNELS.detail.title">CHANNELS</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{cHANNELSEntity.id}</dd>
          <dt>
            <span id="cHANNEL">
              <Translate contentKey="bbMobileBankingAdminApp.cHANNELS.cHANNEL">C HANNEL</Translate>
            </span>
          </dt>
          <dd>{cHANNELSEntity.cHANNEL}</dd>
          <dt>
            <span id="dESCRIPTION">
              <Translate contentKey="bbMobileBankingAdminApp.cHANNELS.dESCRIPTION">D ESCRIPTION</Translate>
            </span>
          </dt>
          <dd>{cHANNELSEntity.dESCRIPTION}</dd>
          <dt>
            <span id="bIN">
              <Translate contentKey="bbMobileBankingAdminApp.cHANNELS.bIN">B IN</Translate>
            </span>
          </dt>
          <dd>{cHANNELSEntity.bIN}</dd>
        </dl>
        <Button tag={Link} to="/channels" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/channels/${cHANNELSEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default CHANNELSDetail;
