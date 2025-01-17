import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { createEntity, getEntity, reset, updateEntity } from './billers.reducer';

export const BILLERSUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const bILLERSEntity = useAppSelector(state => state.bILLERS.entity);
  const loading = useAppSelector(state => state.bILLERS.loading);
  const updating = useAppSelector(state => state.bILLERS.updating);
  const updateSuccess = useAppSelector(state => state.bILLERS.updateSuccess);

  const handleClose = () => {
    navigate('/billers');
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
    if (values.eNABLEDUPLICATECHECK !== undefined && typeof values.eNABLEDUPLICATECHECK !== 'number') {
      values.eNABLEDUPLICATECHECK = Number(values.eNABLEDUPLICATECHECK);
    }
    if (values.sTATUS !== undefined && typeof values.sTATUS !== 'number') {
      values.sTATUS = Number(values.sTATUS);
    }
    if (values.aCTIVE !== undefined && typeof values.aCTIVE !== 'number') {
      values.aCTIVE = Number(values.aCTIVE);
    }
    if (values.rEWORK !== undefined && typeof values.rEWORK !== 'number') {
      values.rEWORK = Number(values.rEWORK);
    }

    const entity = {
      ...bILLERSEntity,
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
          ...bILLERSEntity,
          dATECREATED: convertDateTimeFromServer(bILLERSEntity.dATECREATED),
          aPPROVEDDATE: convertDateTimeFromServer(bILLERSEntity.aPPROVEDDATE),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="bbMobileBankingAdminApp.bILLERS.home.createOrEditLabel" data-cy="BILLERSCreateUpdateHeading">
            <Translate contentKey="bbMobileBankingAdminApp.bILLERS.home.createOrEditLabel">Create or edit a BILLERS</Translate>
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
                  id="billers-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bILLERS.bILLERID')}
                id="billers-bILLERID"
                name="bILLERID"
                data-cy="bILLERID"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bILLERS.dESCRIPTION')}
                id="billers-dESCRIPTION"
                name="dESCRIPTION"
                data-cy="dESCRIPTION"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bILLERS.bILLERCOLLECTIONACCOUNT')}
                id="billers-bILLERCOLLECTIONACCOUNT"
                name="bILLERCOLLECTIONACCOUNT"
                data-cy="bILLERCOLLECTIONACCOUNT"
                type="text"
                validate={{
                  maxLength: { value: 20, message: translate('entity.validation.maxlength', { max: 20 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bILLERS.dATECREATED')}
                id="billers-dATECREATED"
                name="dATECREATED"
                data-cy="dATECREATED"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bILLERS.cREATEDBY')}
                id="billers-cREATEDBY"
                name="cREATEDBY"
                data-cy="cREATEDBY"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bILLERS.aPPROVED')}
                id="billers-aPPROVED"
                name="aPPROVED"
                data-cy="aPPROVED"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bILLERS.aPPROVEDBY')}
                id="billers-aPPROVEDBY"
                name="aPPROVEDBY"
                data-cy="aPPROVEDBY"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bILLERS.aPPROVEDDATE')}
                id="billers-aPPROVEDDATE"
                name="aPPROVEDDATE"
                data-cy="aPPROVEDDATE"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bILLERS.cHARGABLEPRODUCTID')}
                id="billers-cHARGABLEPRODUCTID"
                name="cHARGABLEPRODUCTID"
                data-cy="cHARGABLEPRODUCTID"
                type="text"
                validate={{
                  maxLength: { value: 25, message: translate('entity.validation.maxlength', { max: 25 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bILLERS.nONCHARGABLEPRODUCTID')}
                id="billers-nONCHARGABLEPRODUCTID"
                name="nONCHARGABLEPRODUCTID"
                data-cy="nONCHARGABLEPRODUCTID"
                type="text"
                validate={{
                  maxLength: { value: 25, message: translate('entity.validation.maxlength', { max: 25 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bILLERS.uSDBILLERCOLLECTIONACCOUNT')}
                id="billers-uSDBILLERCOLLECTIONACCOUNT"
                name="uSDBILLERCOLLECTIONACCOUNT"
                data-cy="uSDBILLERCOLLECTIONACCOUNT"
                type="text"
                validate={{
                  maxLength: { value: 20, message: translate('entity.validation.maxlength', { max: 20 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bILLERS.eNABLEDUPLICATECHECK')}
                id="billers-eNABLEDUPLICATECHECK"
                name="eNABLEDUPLICATECHECK"
                data-cy="eNABLEDUPLICATECHECK"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bILLERS.rEMARKS')}
                id="billers-rEMARKS"
                name="rEMARKS"
                data-cy="rEMARKS"
                type="text"
                validate={{
                  maxLength: { value: 250, message: translate('entity.validation.maxlength', { max: 250 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bILLERS.sESSIONID')}
                id="billers-sESSIONID"
                name="sESSIONID"
                data-cy="sESSIONID"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bILLERS.rEWORKBY')}
                id="billers-rEWORKBY"
                name="rEWORKBY"
                data-cy="rEWORKBY"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bILLERS.sTATUS')}
                id="billers-sTATUS"
                name="sTATUS"
                data-cy="sTATUS"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bILLERS.aCTIVE')}
                id="billers-aCTIVE"
                name="aCTIVE"
                data-cy="aCTIVE"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bILLERS.rEWORK')}
                id="billers-rEWORK"
                name="rEWORK"
                data-cy="rEWORK"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/billers" replace color="info">
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

export default BILLERSUpdate;
