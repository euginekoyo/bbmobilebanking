import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { createEntity, getEntity, reset, updateEntity } from './branches.reducer';

export const BRANCHESUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const bRANCHESEntity = useAppSelector(state => state.bRANCHES.entity);
  const loading = useAppSelector(state => state.bRANCHES.loading);
  const updating = useAppSelector(state => state.bRANCHES.updating);
  const updateSuccess = useAppSelector(state => state.bRANCHES.updateSuccess);

  const handleClose = () => {
    navigate('/branches');
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
    if (values.aPPROVED !== undefined && typeof values.aPPROVED !== 'number') {
      values.aPPROVED = Number(values.aPPROVED);
    }
    values.cREATEDON = convertDateTimeToServer(values.cREATEDON);
    values.dELETEDON = convertDateTimeToServer(values.dELETEDON);
    if (values.dELETED !== undefined && typeof values.dELETED !== 'number') {
      values.dELETED = Number(values.dELETED);
    }
    if (values.dECLINED !== undefined && typeof values.dECLINED !== 'number') {
      values.dECLINED = Number(values.dECLINED);
    }
    if (values.rEWORKED !== undefined && typeof values.rEWORKED !== 'number') {
      values.rEWORKED = Number(values.rEWORKED);
    }
    values.rEWORKEDON = convertDateTimeToServer(values.rEWORKEDON);
    if (values.rEPORTING !== undefined && typeof values.rEPORTING !== 'number') {
      values.rEPORTING = Number(values.rEPORTING);
    }

    const entity = {
      ...bRANCHESEntity,
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
          cREATEDON: displayDefaultDateTime(),
          dELETEDON: displayDefaultDateTime(),
          rEWORKEDON: displayDefaultDateTime(),
        }
      : {
          ...bRANCHESEntity,
          cREATEDON: convertDateTimeFromServer(bRANCHESEntity.cREATEDON),
          dELETEDON: convertDateTimeFromServer(bRANCHESEntity.dELETEDON),
          rEWORKEDON: convertDateTimeFromServer(bRANCHESEntity.rEWORKEDON),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="bbMobileBankingAdminApp.bRANCHES.home.createOrEditLabel" data-cy="BRANCHESCreateUpdateHeading">
            <Translate contentKey="bbMobileBankingAdminApp.bRANCHES.home.createOrEditLabel">Create or edit a BRANCHES</Translate>
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
                  id="branches-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bRANCHES.bRANCHNAME')}
                id="branches-bRANCHNAME"
                name="bRANCHNAME"
                data-cy="bRANCHNAME"
                type="text"
                validate={{
                  maxLength: { value: 4000, message: translate('entity.validation.maxlength', { max: 4000 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bRANCHES.bRANCHCODE')}
                id="branches-bRANCHCODE"
                name="bRANCHCODE"
                data-cy="bRANCHCODE"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 3, message: translate('entity.validation.maxlength', { max: 3 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bRANCHES.aPPROVED')}
                id="branches-aPPROVED"
                name="aPPROVED"
                data-cy="aPPROVED"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bRANCHES.eMAIL')}
                id="branches-eMAIL"
                name="eMAIL"
                data-cy="eMAIL"
                type="text"
                validate={{
                  maxLength: { value: 4000, message: translate('entity.validation.maxlength', { max: 4000 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bRANCHES.aDDRESS')}
                id="branches-aDDRESS"
                name="aDDRESS"
                data-cy="aDDRESS"
                type="text"
                validate={{
                  maxLength: { value: 4000, message: translate('entity.validation.maxlength', { max: 4000 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bRANCHES.pHONE')}
                id="branches-pHONE"
                name="pHONE"
                data-cy="pHONE"
                type="text"
                validate={{
                  maxLength: { value: 12, message: translate('entity.validation.maxlength', { max: 12 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bRANCHES.lOCATION')}
                id="branches-lOCATION"
                name="lOCATION"
                data-cy="lOCATION"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 4000, message: translate('entity.validation.maxlength', { max: 4000 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bRANCHES.cONTACTPERSON')}
                id="branches-cONTACTPERSON"
                name="cONTACTPERSON"
                data-cy="cONTACTPERSON"
                type="text"
                validate={{
                  maxLength: { value: 4000, message: translate('entity.validation.maxlength', { max: 4000 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bRANCHES.rEMARKS')}
                id="branches-rEMARKS"
                name="rEMARKS"
                data-cy="rEMARKS"
                type="text"
                validate={{
                  maxLength: { value: 2000, message: translate('entity.validation.maxlength', { max: 2000 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bRANCHES.cREATEDBY')}
                id="branches-cREATEDBY"
                name="cREATEDBY"
                data-cy="cREATEDBY"
                type="text"
                validate={{
                  maxLength: { value: 20, message: translate('entity.validation.maxlength', { max: 20 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bRANCHES.cREATEDON')}
                id="branches-cREATEDON"
                name="cREATEDON"
                data-cy="cREATEDON"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bRANCHES.aPPROVEDBY')}
                id="branches-aPPROVEDBY"
                name="aPPROVEDBY"
                data-cy="aPPROVEDBY"
                type="text"
                validate={{
                  maxLength: { value: 20, message: translate('entity.validation.maxlength', { max: 20 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bRANCHES.aPPROVEDON')}
                id="branches-aPPROVEDON"
                name="aPPROVEDON"
                data-cy="aPPROVEDON"
                type="text"
                validate={{
                  maxLength: { value: 7, message: translate('entity.validation.maxlength', { max: 7 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bRANCHES.cHECKERREMARKS')}
                id="branches-cHECKERREMARKS"
                name="cHECKERREMARKS"
                data-cy="cHECKERREMARKS"
                type="text"
                validate={{
                  maxLength: { value: 200, message: translate('entity.validation.maxlength', { max: 200 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bRANCHES.dELETEDBY')}
                id="branches-dELETEDBY"
                name="dELETEDBY"
                data-cy="dELETEDBY"
                type="text"
                validate={{
                  maxLength: { value: 20, message: translate('entity.validation.maxlength', { max: 20 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bRANCHES.dELETEDON')}
                id="branches-dELETEDON"
                name="dELETEDON"
                data-cy="dELETEDON"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bRANCHES.dELETEREMARKS')}
                id="branches-dELETEREMARKS"
                name="dELETEREMARKS"
                data-cy="dELETEREMARKS"
                type="text"
                validate={{
                  maxLength: { value: 200, message: translate('entity.validation.maxlength', { max: 200 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bRANCHES.dELETED')}
                id="branches-dELETED"
                name="dELETED"
                data-cy="dELETED"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bRANCHES.dECLINED')}
                id="branches-dECLINED"
                name="dECLINED"
                data-cy="dECLINED"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bRANCHES.dECLINEDDON')}
                id="branches-dECLINEDDON"
                name="dECLINEDDON"
                data-cy="dECLINEDDON"
                type="text"
                validate={{
                  maxLength: { value: 7, message: translate('entity.validation.maxlength', { max: 7 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bRANCHES.dECLINEDBY')}
                id="branches-dECLINEDBY"
                name="dECLINEDBY"
                data-cy="dECLINEDBY"
                type="text"
                validate={{
                  maxLength: { value: 20, message: translate('entity.validation.maxlength', { max: 20 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bRANCHES.sESSIONID')}
                id="branches-sESSIONID"
                name="sESSIONID"
                data-cy="sESSIONID"
                type="text"
                validate={{
                  maxLength: { value: 20, message: translate('entity.validation.maxlength', { max: 20 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bRANCHES.rEWORKED')}
                id="branches-rEWORKED"
                name="rEWORKED"
                data-cy="rEWORKED"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bRANCHES.rEWORKEDBY')}
                id="branches-rEWORKEDBY"
                name="rEWORKEDBY"
                data-cy="rEWORKEDBY"
                type="text"
                validate={{
                  maxLength: { value: 20, message: translate('entity.validation.maxlength', { max: 20 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bRANCHES.rEWORKEDON')}
                id="branches-rEWORKEDON"
                name="rEWORKEDON"
                data-cy="rEWORKEDON"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bRANCHES.dISTRICT')}
                id="branches-dISTRICT"
                name="dISTRICT"
                data-cy="dISTRICT"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bRANCHES.rEGION')}
                id="branches-rEGION"
                name="rEGION"
                data-cy="rEGION"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bRANCHES.rEGIONNAME')}
                id="branches-rEGIONNAME"
                name="rEGIONNAME"
                data-cy="rEGIONNAME"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.bRANCHES.rEPORTING')}
                id="branches-rEPORTING"
                name="rEPORTING"
                data-cy="rEPORTING"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/branches" replace color="info">
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

export default BRANCHESUpdate;
