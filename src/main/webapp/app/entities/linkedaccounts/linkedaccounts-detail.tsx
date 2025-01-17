import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './linkedaccounts.reducer';

export const LINKEDACCOUNTSDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const lINKEDACCOUNTSEntity = useAppSelector(state => state.lINKEDACCOUNTS.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="lINKEDACCOUNTSDetailsHeading">
          <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.detail.title">LINKEDACCOUNTS</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{lINKEDACCOUNTSEntity.id}</dd>
          <dt>
            <span id="aCOUNTNAME">
              <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.aCOUNTNAME">A COUNTNAME</Translate>
            </span>
          </dt>
          <dd>{lINKEDACCOUNTSEntity.aCOUNTNAME}</dd>
          <dt>
            <span id="aCCOUNTCLASS">
              <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.aCCOUNTCLASS">A CCOUNTCLASS</Translate>
            </span>
          </dt>
          <dd>{lINKEDACCOUNTSEntity.aCCOUNTCLASS}</dd>
          <dt>
            <span id="aCCOUNTCURRENCY">
              <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.aCCOUNTCURRENCY">A CCOUNTCURRENCY</Translate>
            </span>
          </dt>
          <dd>{lINKEDACCOUNTSEntity.aCCOUNTCURRENCY}</dd>
          <dt>
            <span id="aCCOUNTNUMBER">
              <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.aCCOUNTNUMBER">A CCOUNTNUMBER</Translate>
            </span>
          </dt>
          <dd>{lINKEDACCOUNTSEntity.aCCOUNTNUMBER}</dd>
          <dt>
            <span id="cIF">
              <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.cIF">C IF</Translate>
            </span>
          </dt>
          <dd>{lINKEDACCOUNTSEntity.cIF}</dd>
          <dt>
            <span id="tIMELINKED">
              <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.tIMELINKED">T IMELINKED</Translate>
            </span>
          </dt>
          <dd>
            {lINKEDACCOUNTSEntity.tIMELINKED ? (
              <TextFormat value={lINKEDACCOUNTSEntity.tIMELINKED} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="pHONENUMBER">
              <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.pHONENUMBER">P HONENUMBER</Translate>
            </span>
          </dt>
          <dd>{lINKEDACCOUNTSEntity.pHONENUMBER}</dd>
          <dt>
            <span id="aPPROVEDON">
              <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.aPPROVEDON">A PPROVEDON</Translate>
            </span>
          </dt>
          <dd>
            {lINKEDACCOUNTSEntity.aPPROVEDON ? (
              <TextFormat value={lINKEDACCOUNTSEntity.aPPROVEDON} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="aPPROVED">
              <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.aPPROVED">A PPROVED</Translate>
            </span>
          </dt>
          <dd>{lINKEDACCOUNTSEntity.aPPROVED}</dd>
          <dt>
            <span id="dECLINED">
              <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.dECLINED">D ECLINED</Translate>
            </span>
          </dt>
          <dd>{lINKEDACCOUNTSEntity.dECLINED}</dd>
          <dt>
            <span id="dECLINEDON">
              <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.dECLINEDON">D ECLINEDON</Translate>
            </span>
          </dt>
          <dd>
            {lINKEDACCOUNTSEntity.dECLINEDON ? (
              <TextFormat value={lINKEDACCOUNTSEntity.dECLINEDON} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="rEMARKS">
              <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.rEMARKS">R EMARKS</Translate>
            </span>
          </dt>
          <dd>{lINKEDACCOUNTSEntity.rEMARKS}</dd>
          <dt>
            <span id="lINKEDBY">
              <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.lINKEDBY">L INKEDBY</Translate>
            </span>
          </dt>
          <dd>{lINKEDACCOUNTSEntity.lINKEDBY}</dd>
          <dt>
            <span id="aPPROVEDBY">
              <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.aPPROVEDBY">A PPROVEDBY</Translate>
            </span>
          </dt>
          <dd>{lINKEDACCOUNTSEntity.aPPROVEDBY}</dd>
          <dt>
            <span id="lINKED">
              <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.lINKED">L INKED</Translate>
            </span>
          </dt>
          <dd>{lINKEDACCOUNTSEntity.lINKED}</dd>
          <dt>
            <span id="aCTIVE">
              <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.aCTIVE">A CTIVE</Translate>
            </span>
          </dt>
          <dd>{lINKEDACCOUNTSEntity.aCTIVE}</dd>
          <dt>
            <span id="dELINKEDBY">
              <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.dELINKEDBY">D ELINKEDBY</Translate>
            </span>
          </dt>
          <dd>{lINKEDACCOUNTSEntity.dELINKEDBY}</dd>
          <dt>
            <span id="dELINKEDON">
              <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.dELINKEDON">D ELINKEDON</Translate>
            </span>
          </dt>
          <dd>
            {lINKEDACCOUNTSEntity.dELINKEDON ? (
              <TextFormat value={lINKEDACCOUNTSEntity.dELINKEDON} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="dELINKED">
              <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.dELINKED">D ELINKED</Translate>
            </span>
          </dt>
          <dd>{lINKEDACCOUNTSEntity.dELINKED}</dd>
          <dt>
            <span id="aCCOUNTALIAS">
              <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.aCCOUNTALIAS">A CCOUNTALIAS</Translate>
            </span>
          </dt>
          <dd>{lINKEDACCOUNTSEntity.aCCOUNTALIAS}</dd>
          <dt>
            <span id="sHORTCUTS">
              <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.sHORTCUTS">S HORTCUTS</Translate>
            </span>
          </dt>
          <dd>{lINKEDACCOUNTSEntity.sHORTCUTS}</dd>
        </dl>
        <Button tag={Link} to="/linkedaccounts" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/linkedaccounts/${lINKEDACCOUNTSEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default LINKEDACCOUNTSDetail;
