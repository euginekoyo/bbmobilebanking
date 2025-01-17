import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './customeraccount.reducer';

export const CUSTOMERACCOUNTDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const cUSTOMERACCOUNTEntity = useAppSelector(state => state.cUSTOMERACCOUNT.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="cUSTOMERACCOUNTDetailsHeading">
          <Translate contentKey="bbMobileBankingAdminApp.cUSTOMERACCOUNT.detail.title">CUSTOMERACCOUNT</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{cUSTOMERACCOUNTEntity.id}</dd>
          <dt>
            <span id="cUSTOMERID">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMERACCOUNT.cUSTOMERID">C USTOMERID</Translate>
            </span>
          </dt>
          <dd>{cUSTOMERACCOUNTEntity.cUSTOMERID}</dd>
          <dt>
            <span id="aCCOUNTNUMBER">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMERACCOUNT.aCCOUNTNUMBER">A CCOUNTNUMBER</Translate>
            </span>
          </dt>
          <dd>{cUSTOMERACCOUNTEntity.aCCOUNTNUMBER}</dd>
          <dt>
            <span id="aCCOUNTCLASS">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMERACCOUNT.aCCOUNTCLASS">A CCOUNTCLASS</Translate>
            </span>
          </dt>
          <dd>{cUSTOMERACCOUNTEntity.aCCOUNTCLASS}</dd>
          <dt>
            <span id="cUSTOMERNUMBER">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMERACCOUNT.cUSTOMERNUMBER">C USTOMERNUMBER</Translate>
            </span>
          </dt>
          <dd>{cUSTOMERACCOUNTEntity.cUSTOMERNUMBER}</dd>
          <dt>
            <span id="cIF">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMERACCOUNT.cIF">C IF</Translate>
            </span>
          </dt>
          <dd>{cUSTOMERACCOUNTEntity.cIF}</dd>
          <dt>
            <span id="tIMELINKED">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMERACCOUNT.tIMELINKED">T IMELINKED</Translate>
            </span>
          </dt>
          <dd>
            {cUSTOMERACCOUNTEntity.tIMELINKED ? (
              <TextFormat value={cUSTOMERACCOUNTEntity.tIMELINKED} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="bLOCKED">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMERACCOUNT.bLOCKED">B LOCKED</Translate>
            </span>
          </dt>
          <dd>{cUSTOMERACCOUNTEntity.bLOCKED}</dd>
          <dt>
            <span id="sTOPPED">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMERACCOUNT.sTOPPED">S TOPPED</Translate>
            </span>
          </dt>
          <dd>{cUSTOMERACCOUNTEntity.sTOPPED}</dd>
          <dt>
            <span id="dORMANT">
              <Translate contentKey="bbMobileBankingAdminApp.cUSTOMERACCOUNT.dORMANT">D ORMANT</Translate>
            </span>
          </dt>
          <dd>{cUSTOMERACCOUNTEntity.dORMANT}</dd>
        </dl>
        <Button tag={Link} to="/customeraccount" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/customeraccount/${cUSTOMERACCOUNTEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default CUSTOMERACCOUNTDetail;
