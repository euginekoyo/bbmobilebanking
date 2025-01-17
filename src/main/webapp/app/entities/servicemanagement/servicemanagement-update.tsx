import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { createEntity, getEntity, reset, updateEntity } from './servicemanagement.reducer';

export const SERVICEMANAGEMENTUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const sERVICEMANAGEMENTEntity = useAppSelector(state => state.sERVICEMANAGEMENT.entity);
  const loading = useAppSelector(state => state.sERVICEMANAGEMENT.loading);
  const updating = useAppSelector(state => state.sERVICEMANAGEMENT.updating);
  const updateSuccess = useAppSelector(state => state.sERVICEMANAGEMENT.updateSuccess);

  const handleClose = () => {
    navigate('/servicemanagement');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    if (values.id !== undefined && typeof values.id !== 'number') {
      values.id = Number(values.id);
    }
    values.dATECREATED = convertDateTimeToServer(values.dATECREATED);
    if (values.aPPROVED !== undefined && typeof values.aPPROVED !== 'number') {
      values.aPPROVED = Number(values.aPPROVED);
    }
    values.aPPROVEDDATE = convertDateTimeToServer(values.aPPROVEDDATE);
    if (values.tHIRDPARTYRESPONSE !== undefined && typeof values.tHIRDPARTYRESPONSE !== 'number') {
      values.tHIRDPARTYRESPONSE = Number(values.tHIRDPARTYRESPONSE);
    }

    const entity = {
      ...sERVICEMANAGEMENTEntity,
      ...values,
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {
          dATECREATED: displayDefaultDateTime(),
          aPPROVEDDATE: displayDefaultDateTime(),
        }
      : {
          ...sERVICEMANAGEMENTEntity,
          dATECREATED: convertDateTimeFromServer(sERVICEMANAGEMENTEntity.dATECREATED),
          aPPROVEDDATE: convertDateTimeFromServer(sERVICEMANAGEMENTEntity.aPPROVEDDATE),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="bbMobileBankingAdminApp.sERVICEMANAGEMENT.home.createOrEditLabel" data-cy="SERVICEMANAGEMENTCreateUpdateHeading">
            <Translate contentKey="bbMobileBankingAdminApp.sERVICEMANAGEMENT.home.createOrEditLabel">
              Create or edit a SERVICEMANAGEMENT
            </Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="servicemanagement-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.sERVICEMANAGEMENT.pROCESSINGCODE')}
                id="servicemanagement-pROCESSINGCODE"
                name="pROCESSINGCODE"
                data-cy="pROCESSINGCODE"
                type="text"
                validate={{
                  maxLength: { value: 20, message: translate('entity.validation.maxlength', { max: 20 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.sERVICEMANAGEMENT.aCTIVE')}
                id="servicemanagement-aCTIVE"
                name="aCTIVE"
                data-cy="aCTIVE"
                type="text"
                validate={{
                  maxLength: { value: 20, message: translate('entity.validation.maxlength', { max: 20 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.sERVICEMANAGEMENT.cREATEDBY')}
                id="servicemanagement-cREATEDBY"
                name="cREATEDBY"
                data-cy="cREATEDBY"
                type="text"
                validate={{
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.sERVICEMANAGEMENT.dATECREATED')}
                id="servicemanagement-dATECREATED"
                name="dATECREATED"
                data-cy="dATECREATED"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.sERVICEMANAGEMENT.aPPROVED')}
                id="servicemanagement-aPPROVED"
                name="aPPROVED"
                data-cy="aPPROVED"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.sERVICEMANAGEMENT.aPPROVEDBY')}
                id="servicemanagement-aPPROVEDBY"
                name="aPPROVEDBY"
                data-cy="aPPROVEDBY"
                type="text"
                validate={{
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.sERVICEMANAGEMENT.aPPROVEDDATE')}
                id="servicemanagement-aPPROVEDDATE"
                name="aPPROVEDDATE"
                data-cy="aPPROVEDDATE"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.sERVICEMANAGEMENT.aDAPTORTYPE')}
                id="servicemanagement-aDAPTORTYPE"
                name="aDAPTORTYPE"
                data-cy="aDAPTORTYPE"
                type="text"
                validate={{
                  maxLength: { value: 20, message: translate('entity.validation.maxlength', { max: 20 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.sERVICEMANAGEMENT.dESTINATION')}
                id="servicemanagement-dESTINATION"
                name="dESTINATION"
                data-cy="dESTINATION"
                type="text"
                validate={{
                  maxLength: { value: 20, message: translate('entity.validation.maxlength', { max: 20 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.sERVICEMANAGEMENT.tHIRDPARTYRESPONSE')}
                id="servicemanagement-tHIRDPARTYRESPONSE"
                name="tHIRDPARTYRESPONSE"
                data-cy="tHIRDPARTYRESPONSE"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.sERVICEMANAGEMENT.tELCO')}
                id="servicemanagement-tELCO"
                name="tELCO"
                data-cy="tELCO"
                type="text"
                validate={{
                  maxLength: { value: 20, message: translate('entity.validation.maxlength', { max: 20 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.sERVICEMANAGEMENT.dESCRIPTION')}
                id="servicemanagement-dESCRIPTION"
                name="dESCRIPTION"
                data-cy="dESCRIPTION"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.sERVICEMANAGEMENT.rEMARKS')}
                id="servicemanagement-rEMARKS"
                name="rEMARKS"
                data-cy="rEMARKS"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.sERVICEMANAGEMENT.sESSIONID')}
                id="servicemanagement-sESSIONID"
                name="sESSIONID"
                data-cy="sESSIONID"
                type="text"
                validate={{
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.sERVICEMANAGEMENT.rEWORKBY')}
                id="servicemanagement-rEWORKBY"
                name="rEWORKBY"
                data-cy="rEWORKBY"
                type="text"
                validate={{
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/servicemanagement" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default SERVICEMANAGEMENTUpdate;
