import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, isNumber, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { createEntity, getEntity, reset, updateEntity } from './limits.reducer';

export const LIMITSUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const lIMITSEntity = useAppSelector(state => state.lIMITS.entity);
  const loading = useAppSelector(state => state.lIMITS.loading);
  const updating = useAppSelector(state => state.lIMITS.updating);
  const updateSuccess = useAppSelector(state => state.lIMITS.updateSuccess);

  const handleClose = () => {
    navigate('/limits');
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
    if (values.tRANSACTIONLIMIT !== undefined && typeof values.tRANSACTIONLIMIT !== 'number') {
      values.tRANSACTIONLIMIT = Number(values.tRANSACTIONLIMIT);
    }
    if (values.dAILYLIMIT !== undefined && typeof values.dAILYLIMIT !== 'number') {
      values.dAILYLIMIT = Number(values.dAILYLIMIT);
    }
    if (values.rEWORK !== undefined && typeof values.rEWORK !== 'number') {
      values.rEWORK = Number(values.rEWORK);
    }

    const entity = {
      ...lIMITSEntity,
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
          ...lIMITSEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="bbMobileBankingAdminApp.lIMITS.home.createOrEditLabel" data-cy="LIMITSCreateUpdateHeading">
            <Translate contentKey="bbMobileBankingAdminApp.lIMITS.home.createOrEditLabel">Create or edit a LIMITS</Translate>
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
                  id="limits-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.lIMITS.tRANSACTIONTYPE')}
                id="limits-tRANSACTIONTYPE"
                name="tRANSACTIONTYPE"
                data-cy="tRANSACTIONTYPE"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.lIMITS.pROCODE')}
                id="limits-pROCODE"
                name="pROCODE"
                data-cy="pROCODE"
                type="text"
                validate={{
                  maxLength: { value: 6, message: translate('entity.validation.maxlength', { max: 6 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.lIMITS.cHANNEL')}
                id="limits-cHANNEL"
                name="cHANNEL"
                data-cy="cHANNEL"
                type="text"
                validate={{
                  maxLength: { value: 30, message: translate('entity.validation.maxlength', { max: 30 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.lIMITS.tRANSACTIONLIMIT')}
                id="limits-tRANSACTIONLIMIT"
                name="tRANSACTIONLIMIT"
                data-cy="tRANSACTIONLIMIT"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  min: { value: 1, message: translate('entity.validation.min', { min: 1 }) },
                  max: { value: 10, message: translate('entity.validation.max', { max: 10 }) },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.lIMITS.dAILYLIMIT')}
                id="limits-dAILYLIMIT"
                name="dAILYLIMIT"
                data-cy="dAILYLIMIT"
                type="text"
                validate={{
                  min: { value: 1, message: translate('entity.validation.min', { min: 1 }) },
                  max: { value: 10, message: translate('entity.validation.max', { max: 10 }) },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.lIMITS.rEGISTEREDBY')}
                id="limits-rEGISTEREDBY"
                name="rEGISTEREDBY"
                data-cy="rEGISTEREDBY"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.lIMITS.rEGISTEREDDATE')}
                id="limits-rEGISTEREDDATE"
                name="rEGISTEREDDATE"
                data-cy="rEGISTEREDDATE"
                type="text"
                validate={{
                  maxLength: { value: 7, message: translate('entity.validation.maxlength', { max: 7 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.lIMITS.aPPROVED')}
                id="limits-aPPROVED"
                name="aPPROVED"
                data-cy="aPPROVED"
                type="text"
                validate={{
                  maxLength: { value: 2, message: translate('entity.validation.maxlength', { max: 2 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.lIMITS.aPPROVEDBY')}
                id="limits-aPPROVEDBY"
                name="aPPROVEDBY"
                data-cy="aPPROVEDBY"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.lIMITS.aPPROVEDDATE')}
                id="limits-aPPROVEDDATE"
                name="aPPROVEDDATE"
                data-cy="aPPROVEDDATE"
                type="text"
                validate={{
                  maxLength: { value: 7, message: translate('entity.validation.maxlength', { max: 7 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.lIMITS.uPDATEDBY')}
                id="limits-uPDATEDBY"
                name="uPDATEDBY"
                data-cy="uPDATEDBY"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.lIMITS.uPDATEDDATE')}
                id="limits-uPDATEDDATE"
                name="uPDATEDDATE"
                data-cy="uPDATEDDATE"
                type="text"
                validate={{
                  maxLength: { value: 7, message: translate('entity.validation.maxlength', { max: 7 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.lIMITS.rEWORK')}
                id="limits-rEWORK"
                name="rEWORK"
                data-cy="rEWORK"
                type="text"
                validate={{
                  min: { value: 1, message: translate('entity.validation.min', { min: 1 }) },
                  max: { value: 10, message: translate('entity.validation.max', { max: 10 }) },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.lIMITS.rEWORKBY')}
                id="limits-rEWORKBY"
                name="rEWORKBY"
                data-cy="rEWORKBY"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.lIMITS.sESSIONID')}
                id="limits-sESSIONID"
                name="sESSIONID"
                data-cy="sESSIONID"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/limits" replace color="info">
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

export default LIMITSUpdate;
