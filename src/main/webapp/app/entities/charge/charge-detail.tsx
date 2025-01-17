import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './charge.reducer';

export const CHARGEDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const cHARGEEntity = useAppSelector(state => state.cHARGE.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="cHARGEDetailsHeading">
          <Translate contentKey="bbMobileBankingAdminApp.cHARGE.detail.title">CHARGE</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{cHARGEEntity.id}</dd>
          <dt>
            <span id="tXNTYPE">
              <Translate contentKey="bbMobileBankingAdminApp.cHARGE.tXNTYPE">T XNTYPE</Translate>
            </span>
          </dt>
          <dd>{cHARGEEntity.tXNTYPE}</dd>
          <dt>
            <span id="fEEMODE">
              <Translate contentKey="bbMobileBankingAdminApp.cHARGE.fEEMODE">F EEMODE</Translate>
            </span>
          </dt>
          <dd>{cHARGEEntity.fEEMODE}</dd>
          <dt>
            <span id="aMOUNT">
              <Translate contentKey="bbMobileBankingAdminApp.cHARGE.aMOUNT">A MOUNT</Translate>
            </span>
          </dt>
          <dd>{cHARGEEntity.aMOUNT}</dd>
          <dt>
            <span id="dATECREATED">
              <Translate contentKey="bbMobileBankingAdminApp.cHARGE.dATECREATED">D ATECREATED</Translate>
            </span>
          </dt>
          <dd>{cHARGEEntity.dATECREATED ? <TextFormat value={cHARGEEntity.dATECREATED} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="cREATEDBY">
              <Translate contentKey="bbMobileBankingAdminApp.cHARGE.cREATEDBY">C REATEDBY</Translate>
            </span>
          </dt>
          <dd>{cHARGEEntity.cREATEDBY}</dd>
          <dt>
            <span id="aPPROVED">
              <Translate contentKey="bbMobileBankingAdminApp.cHARGE.aPPROVED">A PPROVED</Translate>
            </span>
          </dt>
          <dd>{cHARGEEntity.aPPROVED}</dd>
          <dt>
            <span id="aPPROVEDBY">
              <Translate contentKey="bbMobileBankingAdminApp.cHARGE.aPPROVEDBY">A PPROVEDBY</Translate>
            </span>
          </dt>
          <dd>{cHARGEEntity.aPPROVEDBY}</dd>
          <dt>
            <span id="cHANNEL">
              <Translate contentKey="bbMobileBankingAdminApp.cHARGE.cHANNEL">C HANNEL</Translate>
            </span>
          </dt>
          <dd>{cHARGEEntity.cHANNEL}</dd>
          <dt>
            <span id="tXNCODE">
              <Translate contentKey="bbMobileBankingAdminApp.cHARGE.tXNCODE">T XNCODE</Translate>
            </span>
          </dt>
          <dd>{cHARGEEntity.tXNCODE}</dd>
          <dt>
            <span id="dESCRIPTION">
              <Translate contentKey="bbMobileBankingAdminApp.cHARGE.dESCRIPTION">D ESCRIPTION</Translate>
            </span>
          </dt>
          <dd>{cHARGEEntity.dESCRIPTION}</dd>
          <dt>
            <span id="aPPROVEDDATE">
              <Translate contentKey="bbMobileBankingAdminApp.cHARGE.aPPROVEDDATE">A PPROVEDDATE</Translate>
            </span>
          </dt>
          <dd>
            {cHARGEEntity.aPPROVEDDATE ? <TextFormat value={cHARGEEntity.aPPROVEDDATE} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <Translate contentKey="bbMobileBankingAdminApp.cHARGE.cHARGERANGES">C HARGERANGES</Translate>
          </dt>
          <dd>{cHARGEEntity.cHARGERANGES ? cHARGEEntity.cHARGERANGES.id : ''}</dd>
          <dt>
            <Translate contentKey="bbMobileBankingAdminApp.cHARGE.rANGE">R ANGE</Translate>
          </dt>
          <dd>{cHARGEEntity.rANGE ? cHARGEEntity.rANGE.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/charge" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/charge/${cHARGEEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default CHARGEDetail;
