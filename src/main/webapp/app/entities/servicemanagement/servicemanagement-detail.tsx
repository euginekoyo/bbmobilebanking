import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './servicemanagement.reducer';

export const SERVICEMANAGEMENTDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const sERVICEMANAGEMENTEntity = useAppSelector(state => state.sERVICEMANAGEMENT.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="sERVICEMANAGEMENTDetailsHeading">
          <Translate contentKey="bbMobileBankingAdminApp.sERVICEMANAGEMENT.detail.title">SERVICEMANAGEMENT</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{sERVICEMANAGEMENTEntity.id}</dd>
          <dt>
            <span id="pROCESSINGCODE">
              <Translate contentKey="bbMobileBankingAdminApp.sERVICEMANAGEMENT.pROCESSINGCODE">P ROCESSINGCODE</Translate>
            </span>
          </dt>
          <dd>{sERVICEMANAGEMENTEntity.pROCESSINGCODE}</dd>
          <dt>
            <span id="aCTIVE">
              <Translate contentKey="bbMobileBankingAdminApp.sERVICEMANAGEMENT.aCTIVE">A CTIVE</Translate>
            </span>
          </dt>
          <dd>{sERVICEMANAGEMENTEntity.aCTIVE}</dd>
          <dt>
            <span id="cREATEDBY">
              <Translate contentKey="bbMobileBankingAdminApp.sERVICEMANAGEMENT.cREATEDBY">C REATEDBY</Translate>
            </span>
          </dt>
          <dd>{sERVICEMANAGEMENTEntity.cREATEDBY}</dd>
          <dt>
            <span id="dATECREATED">
              <Translate contentKey="bbMobileBankingAdminApp.sERVICEMANAGEMENT.dATECREATED">D ATECREATED</Translate>
            </span>
          </dt>
          <dd>
            {sERVICEMANAGEMENTEntity.dATECREATED ? (
              <TextFormat value={sERVICEMANAGEMENTEntity.dATECREATED} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="aPPROVED">
              <Translate contentKey="bbMobileBankingAdminApp.sERVICEMANAGEMENT.aPPROVED">A PPROVED</Translate>
            </span>
          </dt>
          <dd>{sERVICEMANAGEMENTEntity.aPPROVED}</dd>
          <dt>
            <span id="aPPROVEDBY">
              <Translate contentKey="bbMobileBankingAdminApp.sERVICEMANAGEMENT.aPPROVEDBY">A PPROVEDBY</Translate>
            </span>
          </dt>
          <dd>{sERVICEMANAGEMENTEntity.aPPROVEDBY}</dd>
          <dt>
            <span id="aPPROVEDDATE">
              <Translate contentKey="bbMobileBankingAdminApp.sERVICEMANAGEMENT.aPPROVEDDATE">A PPROVEDDATE</Translate>
            </span>
          </dt>
          <dd>
            {sERVICEMANAGEMENTEntity.aPPROVEDDATE ? (
              <TextFormat value={sERVICEMANAGEMENTEntity.aPPROVEDDATE} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="aDAPTORTYPE">
              <Translate contentKey="bbMobileBankingAdminApp.sERVICEMANAGEMENT.aDAPTORTYPE">A DAPTORTYPE</Translate>
            </span>
          </dt>
          <dd>{sERVICEMANAGEMENTEntity.aDAPTORTYPE}</dd>
          <dt>
            <span id="dESTINATION">
              <Translate contentKey="bbMobileBankingAdminApp.sERVICEMANAGEMENT.dESTINATION">D ESTINATION</Translate>
            </span>
          </dt>
          <dd>{sERVICEMANAGEMENTEntity.dESTINATION}</dd>
          <dt>
            <span id="tHIRDPARTYRESPONSE">
              <Translate contentKey="bbMobileBankingAdminApp.sERVICEMANAGEMENT.tHIRDPARTYRESPONSE">T HIRDPARTYRESPONSE</Translate>
            </span>
          </dt>
          <dd>{sERVICEMANAGEMENTEntity.tHIRDPARTYRESPONSE}</dd>
          <dt>
            <span id="tELCO">
              <Translate contentKey="bbMobileBankingAdminApp.sERVICEMANAGEMENT.tELCO">T ELCO</Translate>
            </span>
          </dt>
          <dd>{sERVICEMANAGEMENTEntity.tELCO}</dd>
          <dt>
            <span id="dESCRIPTION">
              <Translate contentKey="bbMobileBankingAdminApp.sERVICEMANAGEMENT.dESCRIPTION">D ESCRIPTION</Translate>
            </span>
          </dt>
          <dd>{sERVICEMANAGEMENTEntity.dESCRIPTION}</dd>
          <dt>
            <span id="rEMARKS">
              <Translate contentKey="bbMobileBankingAdminApp.sERVICEMANAGEMENT.rEMARKS">R EMARKS</Translate>
            </span>
          </dt>
          <dd>{sERVICEMANAGEMENTEntity.rEMARKS}</dd>
          <dt>
            <span id="sESSIONID">
              <Translate contentKey="bbMobileBankingAdminApp.sERVICEMANAGEMENT.sESSIONID">S ESSIONID</Translate>
            </span>
          </dt>
          <dd>{sERVICEMANAGEMENTEntity.sESSIONID}</dd>
          <dt>
            <span id="rEWORKBY">
              <Translate contentKey="bbMobileBankingAdminApp.sERVICEMANAGEMENT.rEWORKBY">R EWORKBY</Translate>
            </span>
          </dt>
          <dd>{sERVICEMANAGEMENTEntity.rEWORKBY}</dd>
        </dl>
        <Button tag={Link} to="/servicemanagement" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/servicemanagement/${sERVICEMANAGEMENTEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default SERVICEMANAGEMENTDetail;
