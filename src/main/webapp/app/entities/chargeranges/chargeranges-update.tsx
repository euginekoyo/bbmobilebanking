import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, isNumber, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { createEntity, getEntity, reset, updateEntity } from './chargeranges.reducer';

export const CHARGERANGESUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const cHARGERANGESEntity = useAppSelector(state => state.cHARGERANGES.entity);
  const loading = useAppSelector(state => state.cHARGERANGES.loading);
  const updating = useAppSelector(state => state.cHARGERANGES.updating);
  const updateSuccess = useAppSelector(state => state.cHARGERANGES.updateSuccess);

  const handleClose = () => {
    navigate('/chargeranges');
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
    if (values.mAX !== undefined && typeof values.mAX !== 'number') {
      values.mAX = Number(values.mAX);
    }
    if (values.mIN !== undefined && typeof values.mIN !== 'number') {
      values.mIN = Number(values.mIN);
    }
    if (values.aMOUNT !== undefined && typeof values.aMOUNT !== 'number') {
      values.aMOUNT = Number(values.aMOUNT);
    }
    if (values.aPPROVED !== undefined && typeof values.aPPROVED !== 'number') {
      values.aPPROVED = Number(values.aPPROVED);
    }
    if (values.dECLINED !== undefined && typeof values.dECLINED !== 'number') {
      values.dECLINED = Number(values.dECLINED);
    }
    if (values.cHARGEID !== undefined && typeof values.cHARGEID !== 'number') {
      values.cHARGEID = Number(values.cHARGEID);
    }

    const entity = {
      ...cHARGERANGESEntity,
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
      ? {}
      : {
          ...cHARGERANGESEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="bbMobileBankingAdminApp.cHARGERANGES.home.createOrEditLabel" data-cy="CHARGERANGESCreateUpdateHeading">
            <Translate contentKey="bbMobileBankingAdminApp.cHARGERANGES.home.createOrEditLabel">Create or edit a CHARGERANGES</Translate>
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
                  id="chargeranges-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cHARGERANGES.bILLERID')}
                id="chargeranges-bILLERID"
                name="bILLERID"
                data-cy="bILLERID"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 20, message: translate('entity.validation.maxlength', { max: 20 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cHARGERANGES.pROCESSINGCODE')}
                id="chargeranges-pROCESSINGCODE"
                name="pROCESSINGCODE"
                data-cy="pROCESSINGCODE"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 20, message: translate('entity.validation.maxlength', { max: 20 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cHARGERANGES.mAX')}
                id="chargeranges-mAX"
                name="mAX"
                data-cy="mAX"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cHARGERANGES.mIN')}
                id="chargeranges-mIN"
                name="mIN"
                data-cy="mIN"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cHARGERANGES.aMOUNT')}
                id="chargeranges-aMOUNT"
                name="aMOUNT"
                data-cy="aMOUNT"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cHARGERANGES.cREATEDBY')}
                id="chargeranges-cREATEDBY"
                name="cREATEDBY"
                data-cy="cREATEDBY"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cHARGERANGES.aPPROVEDBY')}
                id="chargeranges-aPPROVEDBY"
                name="aPPROVEDBY"
                data-cy="aPPROVEDBY"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cHARGERANGES.cREATEDAT')}
                id="chargeranges-cREATEDAT"
                name="cREATEDAT"
                data-cy="cREATEDAT"
                type="text"
                validate={{
                  maxLength: { value: 30, message: translate('entity.validation.maxlength', { max: 30 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cHARGERANGES.aPPROVEDON')}
                id="chargeranges-aPPROVEDON"
                name="aPPROVEDON"
                data-cy="aPPROVEDON"
                type="text"
                validate={{
                  maxLength: { value: 20, message: translate('entity.validation.maxlength', { max: 20 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cHARGERANGES.aPPROVED')}
                id="chargeranges-aPPROVED"
                name="aPPROVED"
                data-cy="aPPROVED"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cHARGERANGES.dECLINED')}
                id="chargeranges-dECLINED"
                name="dECLINED"
                data-cy="dECLINED"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cHARGERANGES.dECLINEDBY')}
                id="chargeranges-dECLINEDBY"
                name="dECLINEDBY"
                data-cy="dECLINEDBY"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cHARGERANGES.cHARGEID')}
                id="chargeranges-cHARGEID"
                name="cHARGEID"
                data-cy="cHARGEID"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/chargeranges" replace color="info">
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

export default CHARGERANGESUpdate;
