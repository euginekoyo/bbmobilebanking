import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './branches.reducer';

export const BRANCHESDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const bRANCHESEntity = useAppSelector(state => state.bRANCHES.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="bRANCHESDetailsHeading">
          <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.detail.title">BRANCHES</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{bRANCHESEntity.id}</dd>
          <dt>
            <span id="bRANCHNAME">
              <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.bRANCHNAME">B RANCHNAME</Translate>
            </span>
          </dt>
          <dd>{bRANCHESEntity.bRANCHNAME}</dd>
          <dt>
            <span id="bRANCHCODE">
              <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.bRANCHCODE">B RANCHCODE</Translate>
            </span>
          </dt>
          <dd>{bRANCHESEntity.bRANCHCODE}</dd>
          <dt>
            <span id="aPPROVED">
              <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.aPPROVED">A PPROVED</Translate>
            </span>
          </dt>
          <dd>{bRANCHESEntity.aPPROVED}</dd>
          <dt>
            <span id="eMAIL">
              <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.eMAIL">E MAIL</Translate>
            </span>
          </dt>
          <dd>{bRANCHESEntity.eMAIL}</dd>
          <dt>
            <span id="aDDRESS">
              <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.aDDRESS">A DDRESS</Translate>
            </span>
          </dt>
          <dd>{bRANCHESEntity.aDDRESS}</dd>
          <dt>
            <span id="pHONE">
              <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.pHONE">P HONE</Translate>
            </span>
          </dt>
          <dd>{bRANCHESEntity.pHONE}</dd>
          <dt>
            <span id="lOCATION">
              <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.lOCATION">L OCATION</Translate>
            </span>
          </dt>
          <dd>{bRANCHESEntity.lOCATION}</dd>
          <dt>
            <span id="cONTACTPERSON">
              <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.cONTACTPERSON">C ONTACTPERSON</Translate>
            </span>
          </dt>
          <dd>{bRANCHESEntity.cONTACTPERSON}</dd>
          <dt>
            <span id="rEMARKS">
              <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.rEMARKS">R EMARKS</Translate>
            </span>
          </dt>
          <dd>{bRANCHESEntity.rEMARKS}</dd>
          <dt>
            <span id="cREATEDBY">
              <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.cREATEDBY">C REATEDBY</Translate>
            </span>
          </dt>
          <dd>{bRANCHESEntity.cREATEDBY}</dd>
          <dt>
            <span id="cREATEDON">
              <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.cREATEDON">C REATEDON</Translate>
            </span>
          </dt>
          <dd>{bRANCHESEntity.cREATEDON ? <TextFormat value={bRANCHESEntity.cREATEDON} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="aPPROVEDBY">
              <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.aPPROVEDBY">A PPROVEDBY</Translate>
            </span>
          </dt>
          <dd>{bRANCHESEntity.aPPROVEDBY}</dd>
          <dt>
            <span id="aPPROVEDON">
              <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.aPPROVEDON">A PPROVEDON</Translate>
            </span>
          </dt>
          <dd>{bRANCHESEntity.aPPROVEDON}</dd>
          <dt>
            <span id="cHECKERREMARKS">
              <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.cHECKERREMARKS">C HECKERREMARKS</Translate>
            </span>
          </dt>
          <dd>{bRANCHESEntity.cHECKERREMARKS}</dd>
          <dt>
            <span id="dELETEDBY">
              <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.dELETEDBY">D ELETEDBY</Translate>
            </span>
          </dt>
          <dd>{bRANCHESEntity.dELETEDBY}</dd>
          <dt>
            <span id="dELETEDON">
              <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.dELETEDON">D ELETEDON</Translate>
            </span>
          </dt>
          <dd>{bRANCHESEntity.dELETEDON ? <TextFormat value={bRANCHESEntity.dELETEDON} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="dELETEREMARKS">
              <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.dELETEREMARKS">D ELETEREMARKS</Translate>
            </span>
          </dt>
          <dd>{bRANCHESEntity.dELETEREMARKS}</dd>
          <dt>
            <span id="dELETED">
              <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.dELETED">D ELETED</Translate>
            </span>
          </dt>
          <dd>{bRANCHESEntity.dELETED}</dd>
          <dt>
            <span id="dECLINED">
              <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.dECLINED">D ECLINED</Translate>
            </span>
          </dt>
          <dd>{bRANCHESEntity.dECLINED}</dd>
          <dt>
            <span id="dECLINEDDON">
              <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.dECLINEDDON">D ECLINEDDON</Translate>
            </span>
          </dt>
          <dd>{bRANCHESEntity.dECLINEDDON}</dd>
          <dt>
            <span id="dECLINEDBY">
              <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.dECLINEDBY">D ECLINEDBY</Translate>
            </span>
          </dt>
          <dd>{bRANCHESEntity.dECLINEDBY}</dd>
          <dt>
            <span id="sESSIONID">
              <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.sESSIONID">S ESSIONID</Translate>
            </span>
          </dt>
          <dd>{bRANCHESEntity.sESSIONID}</dd>
          <dt>
            <span id="rEWORKED">
              <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.rEWORKED">R EWORKED</Translate>
            </span>
          </dt>
          <dd>{bRANCHESEntity.rEWORKED}</dd>
          <dt>
            <span id="rEWORKEDBY">
              <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.rEWORKEDBY">R EWORKEDBY</Translate>
            </span>
          </dt>
          <dd>{bRANCHESEntity.rEWORKEDBY}</dd>
          <dt>
            <span id="rEWORKEDON">
              <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.rEWORKEDON">R EWORKEDON</Translate>
            </span>
          </dt>
          <dd>
            {bRANCHESEntity.rEWORKEDON ? <TextFormat value={bRANCHESEntity.rEWORKEDON} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="dISTRICT">
              <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.dISTRICT">D ISTRICT</Translate>
            </span>
          </dt>
          <dd>{bRANCHESEntity.dISTRICT}</dd>
          <dt>
            <span id="rEGION">
              <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.rEGION">R EGION</Translate>
            </span>
          </dt>
          <dd>{bRANCHESEntity.rEGION}</dd>
          <dt>
            <span id="rEGIONNAME">
              <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.rEGIONNAME">R EGIONNAME</Translate>
            </span>
          </dt>
          <dd>{bRANCHESEntity.rEGIONNAME}</dd>
          <dt>
            <span id="rEPORTING">
              <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.rEPORTING">R EPORTING</Translate>
            </span>
          </dt>
          <dd>{bRANCHESEntity.rEPORTING}</dd>
        </dl>
        <Button tag={Link} to="/branches" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/branches/${bRANCHESEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default BRANCHESDetail;
