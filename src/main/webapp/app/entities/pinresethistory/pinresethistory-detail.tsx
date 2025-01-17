import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './pinresethistory.reducer';

export const PINRESETHISTORYDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const pINRESETHISTORYEntity = useAppSelector(state => state.pINRESETHISTORY.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="pINRESETHISTORYDetailsHeading">
          <Translate contentKey="bbMobileBankingAdminApp.pINRESETHISTORY.detail.title">PINRESETHISTORY</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{pINRESETHISTORYEntity.id}</dd>
          <dt>
            <span id="pHONENUMBER">
              <Translate contentKey="bbMobileBankingAdminApp.pINRESETHISTORY.pHONENUMBER">P HONENUMBER</Translate>
            </span>
          </dt>
          <dd>{pINRESETHISTORYEntity.pHONENUMBER}</dd>
          <dt>
            <span id="cUSTOMERNAME">
              <Translate contentKey="bbMobileBankingAdminApp.pINRESETHISTORY.cUSTOMERNAME">C USTOMERNAME</Translate>
            </span>
          </dt>
          <dd>{pINRESETHISTORYEntity.cUSTOMERNAME}</dd>
          <dt>
            <span id="pINBLOCKEDON">
              <Translate contentKey="bbMobileBankingAdminApp.pINRESETHISTORY.pINBLOCKEDON">P INBLOCKEDON</Translate>
            </span>
          </dt>
          <dd>{pINRESETHISTORYEntity.pINBLOCKEDON}</dd>
          <dt>
            <span id="pINBLOCKREMARKS">
              <Translate contentKey="bbMobileBankingAdminApp.pINRESETHISTORY.pINBLOCKREMARKS">P INBLOCKREMARKS</Translate>
            </span>
          </dt>
          <dd>{pINRESETHISTORYEntity.pINBLOCKREMARKS}</dd>
          <dt>
            <span id="pINRESETBY">
              <Translate contentKey="bbMobileBankingAdminApp.pINRESETHISTORY.pINRESETBY">P INRESETBY</Translate>
            </span>
          </dt>
          <dd>{pINRESETHISTORYEntity.pINRESETBY}</dd>
          <dt>
            <span id="pINRESETON">
              <Translate contentKey="bbMobileBankingAdminApp.pINRESETHISTORY.pINRESETON">P INRESETON</Translate>
            </span>
          </dt>
          <dd>{pINRESETHISTORYEntity.pINRESETON}</dd>
          <dt>
            <span id="pINRESETAPPROVEDBY">
              <Translate contentKey="bbMobileBankingAdminApp.pINRESETHISTORY.pINRESETAPPROVEDBY">P INRESETAPPROVEDBY</Translate>
            </span>
          </dt>
          <dd>{pINRESETHISTORYEntity.pINRESETAPPROVEDBY}</dd>
          <dt>
            <span id="pINRESETAPPROVEDON">
              <Translate contentKey="bbMobileBankingAdminApp.pINRESETHISTORY.pINRESETAPPROVEDON">P INRESETAPPROVEDON</Translate>
            </span>
          </dt>
          <dd>{pINRESETHISTORYEntity.pINRESETAPPROVEDON}</dd>
          <dt>
            <span id="pINRESETREMARKS">
              <Translate contentKey="bbMobileBankingAdminApp.pINRESETHISTORY.pINRESETREMARKS">P INRESETREMARKS</Translate>
            </span>
          </dt>
          <dd>{pINRESETHISTORYEntity.pINRESETREMARKS}</dd>
          <dt>
            <span id="bRANCHCODE">
              <Translate contentKey="bbMobileBankingAdminApp.pINRESETHISTORY.bRANCHCODE">B RANCHCODE</Translate>
            </span>
          </dt>
          <dd>{pINRESETHISTORYEntity.bRANCHCODE}</dd>
        </dl>
        <Button tag={Link} to="/pinresethistory" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/pinresethistory/${pINRESETHISTORYEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default PINRESETHISTORYDetail;
