import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './range.reducer';

export const RANGEDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const rANGEEntity = useAppSelector(state => state.rANGE.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="rANGEDetailsHeading">
          <Translate contentKey="bbMobileBankingAdminApp.rANGE.detail.title">RANGE</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{rANGEEntity.id}</dd>
          <dt>
            <span id="rANGEFROM">
              <Translate contentKey="bbMobileBankingAdminApp.rANGE.rANGEFROM">R ANGEFROM</Translate>
            </span>
          </dt>
          <dd>{rANGEEntity.rANGEFROM}</dd>
          <dt>
            <span id="rANGETO">
              <Translate contentKey="bbMobileBankingAdminApp.rANGE.rANGETO">R ANGETO</Translate>
            </span>
          </dt>
          <dd>{rANGEEntity.rANGETO}</dd>
          <dt>
            <span id="aMOUNT">
              <Translate contentKey="bbMobileBankingAdminApp.rANGE.aMOUNT">A MOUNT</Translate>
            </span>
          </dt>
          <dd>{rANGEEntity.aMOUNT}</dd>
          <dt>
            <span id="tXNTYPE">
              <Translate contentKey="bbMobileBankingAdminApp.rANGE.tXNTYPE">T XNTYPE</Translate>
            </span>
          </dt>
          <dd>{rANGEEntity.tXNTYPE}</dd>
          <dt>
            <span id="tXNCODE">
              <Translate contentKey="bbMobileBankingAdminApp.rANGE.tXNCODE">T XNCODE</Translate>
            </span>
          </dt>
          <dd>{rANGEEntity.tXNCODE}</dd>
          <dt>
            <span id="cHARGEID">
              <Translate contentKey="bbMobileBankingAdminApp.rANGE.cHARGEID">C HARGEID</Translate>
            </span>
          </dt>
          <dd>{rANGEEntity.cHARGEID}</dd>
          <dt>
            <span id="cHANNEL">
              <Translate contentKey="bbMobileBankingAdminApp.rANGE.cHANNEL">C HANNEL</Translate>
            </span>
          </dt>
          <dd>{rANGEEntity.cHANNEL}</dd>
        </dl>
        <Button tag={Link} to="/range" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/range/${rANGEEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default RANGEDetail;
