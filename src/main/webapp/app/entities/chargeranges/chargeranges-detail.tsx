import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './chargeranges.reducer';

export const CHARGERANGESDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const cHARGERANGESEntity = useAppSelector(state => state.cHARGERANGES.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="cHARGERANGESDetailsHeading">
          <Translate contentKey="bbMobileBankingAdminApp.cHARGERANGES.detail.title">CHARGERANGES</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{cHARGERANGESEntity.id}</dd>
          <dt>
            <span id="bILLERID">
              <Translate contentKey="bbMobileBankingAdminApp.cHARGERANGES.bILLERID">B ILLERID</Translate>
            </span>
          </dt>
          <dd>{cHARGERANGESEntity.bILLERID}</dd>
          <dt>
            <span id="pROCESSINGCODE">
              <Translate contentKey="bbMobileBankingAdminApp.cHARGERANGES.pROCESSINGCODE">P ROCESSINGCODE</Translate>
            </span>
          </dt>
          <dd>{cHARGERANGESEntity.pROCESSINGCODE}</dd>
          <dt>
            <span id="mAX">
              <Translate contentKey="bbMobileBankingAdminApp.cHARGERANGES.mAX">M AX</Translate>
            </span>
          </dt>
          <dd>{cHARGERANGESEntity.mAX}</dd>
          <dt>
            <span id="mIN">
              <Translate contentKey="bbMobileBankingAdminApp.cHARGERANGES.mIN">M IN</Translate>
            </span>
          </dt>
          <dd>{cHARGERANGESEntity.mIN}</dd>
          <dt>
            <span id="aMOUNT">
              <Translate contentKey="bbMobileBankingAdminApp.cHARGERANGES.aMOUNT">A MOUNT</Translate>
            </span>
          </dt>
          <dd>{cHARGERANGESEntity.aMOUNT}</dd>
          <dt>
            <span id="cREATEDBY">
              <Translate contentKey="bbMobileBankingAdminApp.cHARGERANGES.cREATEDBY">C REATEDBY</Translate>
            </span>
          </dt>
          <dd>{cHARGERANGESEntity.cREATEDBY}</dd>
          <dt>
            <span id="aPPROVEDBY">
              <Translate contentKey="bbMobileBankingAdminApp.cHARGERANGES.aPPROVEDBY">A PPROVEDBY</Translate>
            </span>
          </dt>
          <dd>{cHARGERANGESEntity.aPPROVEDBY}</dd>
          <dt>
            <span id="cREATEDAT">
              <Translate contentKey="bbMobileBankingAdminApp.cHARGERANGES.cREATEDAT">C REATEDAT</Translate>
            </span>
          </dt>
          <dd>{cHARGERANGESEntity.cREATEDAT}</dd>
          <dt>
            <span id="aPPROVEDON">
              <Translate contentKey="bbMobileBankingAdminApp.cHARGERANGES.aPPROVEDON">A PPROVEDON</Translate>
            </span>
          </dt>
          <dd>{cHARGERANGESEntity.aPPROVEDON}</dd>
          <dt>
            <span id="aPPROVED">
              <Translate contentKey="bbMobileBankingAdminApp.cHARGERANGES.aPPROVED">A PPROVED</Translate>
            </span>
          </dt>
          <dd>{cHARGERANGESEntity.aPPROVED}</dd>
          <dt>
            <span id="dECLINED">
              <Translate contentKey="bbMobileBankingAdminApp.cHARGERANGES.dECLINED">D ECLINED</Translate>
            </span>
          </dt>
          <dd>{cHARGERANGESEntity.dECLINED}</dd>
          <dt>
            <span id="dECLINEDBY">
              <Translate contentKey="bbMobileBankingAdminApp.cHARGERANGES.dECLINEDBY">D ECLINEDBY</Translate>
            </span>
          </dt>
          <dd>{cHARGERANGESEntity.dECLINEDBY}</dd>
          <dt>
            <span id="cHARGEID">
              <Translate contentKey="bbMobileBankingAdminApp.cHARGERANGES.cHARGEID">C HARGEID</Translate>
            </span>
          </dt>
          <dd>{cHARGERANGESEntity.cHARGEID}</dd>
        </dl>
        <Button tag={Link} to="/chargeranges" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/chargeranges/${cHARGERANGESEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default CHARGERANGESDetail;
