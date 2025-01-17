import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { createEntity, getEntity, reset, updateEntity } from './requests.reducer';

export const REQUESTSUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const rEQUESTSEntity = useAppSelector(state => state.rEQUESTS.entity);
  const loading = useAppSelector(state => state.rEQUESTS.loading);
  const updating = useAppSelector(state => state.rEQUESTS.updating);
  const updateSuccess = useAppSelector(state => state.rEQUESTS.updateSuccess);

  const handleClose = () => {
    navigate('/requests');
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
    if (values.rEQUESTCHARGE !== undefined && typeof values.rEQUESTCHARGE !== 'number') {
      values.rEQUESTCHARGE = Number(values.rEQUESTCHARGE);
    }
    values.dATEREQUESTED = convertDateTimeToServer(values.dATEREQUESTED);
    if (values.nOOFBOOKS !== undefined && typeof values.nOOFBOOKS !== 'number') {
      values.nOOFBOOKS = Number(values.nOOFBOOKS);
    }
    if (values.aPPROVED !== undefined && typeof values.aPPROVED !== 'number') {
      values.aPPROVED = Number(values.aPPROVED);
    }
    values.aPPROVEDON = convertDateTimeToServer(values.aPPROVEDON);
    values.dATERESPONDED = convertDateTimeToServer(values.dATERESPONDED);
    if (values.rEJECTED !== undefined && typeof values.rEJECTED !== 'number') {
      values.rEJECTED = Number(values.rEJECTED);
    }
    values.rEJECTEDON = convertDateTimeToServer(values.rEJECTEDON);

    const entity = {
      ...rEQUESTSEntity,
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
          dATEREQUESTED: displayDefaultDateTime(),
          aPPROVEDON: displayDefaultDateTime(),
          dATERESPONDED: displayDefaultDateTime(),
          rEJECTEDON: displayDefaultDateTime(),
        }
      : {
          ...rEQUESTSEntity,
          dATEREQUESTED: convertDateTimeFromServer(rEQUESTSEntity.dATEREQUESTED),
          aPPROVEDON: convertDateTimeFromServer(rEQUESTSEntity.aPPROVEDON),
          dATERESPONDED: convertDateTimeFromServer(rEQUESTSEntity.dATERESPONDED),
          rEJECTEDON: convertDateTimeFromServer(rEQUESTSEntity.rEJECTEDON),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="bbMobileBankingAdminApp.rEQUESTS.home.createOrEditLabel" data-cy="REQUESTSCreateUpdateHeading">
            <Translate contentKey="bbMobileBankingAdminApp.rEQUESTS.home.createOrEditLabel">Create or edit a REQUESTS</Translate>
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
                  id="requests-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.rEQUESTS.mOBILENUMBER')}
                id="requests-mOBILENUMBER"
                name="mOBILENUMBER"
                data-cy="mOBILENUMBER"
                type="text"
                validate={{
                  maxLength: { value: 20, message: translate('entity.validation.maxlength', { max: 20 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.rEQUESTS.aCCOUNTNO')}
                id="requests-aCCOUNTNO"
                name="aCCOUNTNO"
                data-cy="aCCOUNTNO"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.rEQUESTS.cURRENCY')}
                id="requests-cURRENCY"
                name="cURRENCY"
                data-cy="cURRENCY"
                type="text"
                validate={{
                  maxLength: { value: 10, message: translate('entity.validation.maxlength', { max: 10 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.rEQUESTS.cIF')}
                id="requests-cIF"
                name="cIF"
                data-cy="cIF"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.rEQUESTS.rEQUESTTYPE')}
                id="requests-rEQUESTTYPE"
                name="rEQUESTTYPE"
                data-cy="rEQUESTTYPE"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.rEQUESTS.rEQUESTCHARGE')}
                id="requests-rEQUESTCHARGE"
                name="rEQUESTCHARGE"
                data-cy="rEQUESTCHARGE"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.rEQUESTS.rEQUESTSTATUS')}
                id="requests-rEQUESTSTATUS"
                name="rEQUESTSTATUS"
                data-cy="rEQUESTSTATUS"
                type="text"
                validate={{
                  maxLength: { value: 10, message: translate('entity.validation.maxlength', { max: 10 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.rEQUESTS.dATEREQUESTED')}
                id="requests-dATEREQUESTED"
                name="dATEREQUESTED"
                data-cy="dATEREQUESTED"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.rEQUESTS.tRNREFNO')}
                id="requests-tRNREFNO"
                name="tRNREFNO"
                data-cy="tRNREFNO"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.rEQUESTS.nOOFBOOKS')}
                id="requests-nOOFBOOKS"
                name="nOOFBOOKS"
                data-cy="nOOFBOOKS"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.rEQUESTS.nOOFLEAVES')}
                id="requests-nOOFLEAVES"
                name="nOOFLEAVES"
                data-cy="nOOFLEAVES"
                type="text"
                validate={{
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.rEQUESTS.aPPROVED')}
                id="requests-aPPROVED"
                name="aPPROVED"
                data-cy="aPPROVED"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.rEQUESTS.cHANNEL')}
                id="requests-cHANNEL"
                name="cHANNEL"
                data-cy="cHANNEL"
                type="text"
                validate={{
                  maxLength: { value: 10, message: translate('entity.validation.maxlength', { max: 10 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.rEQUESTS.aPPROVEDBY')}
                id="requests-aPPROVEDBY"
                name="aPPROVEDBY"
                data-cy="aPPROVEDBY"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.rEQUESTS.aPPROVEDON')}
                id="requests-aPPROVEDON"
                name="aPPROVEDON"
                data-cy="aPPROVEDON"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.rEQUESTS.cHECKERREMARKS')}
                id="requests-cHECKERREMARKS"
                name="cHECKERREMARKS"
                data-cy="cHECKERREMARKS"
                type="text"
                validate={{
                  maxLength: { value: 200, message: translate('entity.validation.maxlength', { max: 200 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.rEQUESTS.rESPCODE')}
                id="requests-rESPCODE"
                name="rESPCODE"
                data-cy="rESPCODE"
                type="text"
                validate={{
                  maxLength: { value: 20, message: translate('entity.validation.maxlength', { max: 20 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.rEQUESTS.rESPDESCRIPTION')}
                id="requests-rESPDESCRIPTION"
                name="rESPDESCRIPTION"
                data-cy="rESPDESCRIPTION"
                type="text"
                validate={{
                  maxLength: { value: 200, message: translate('entity.validation.maxlength', { max: 200 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.rEQUESTS.dATERESPONDED')}
                id="requests-dATERESPONDED"
                name="dATERESPONDED"
                data-cy="dATERESPONDED"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.rEQUESTS.cUSTOMERNAME')}
                id="requests-cUSTOMERNAME"
                name="cUSTOMERNAME"
                data-cy="cUSTOMERNAME"
                type="text"
                validate={{
                  maxLength: { value: 150, message: translate('entity.validation.maxlength', { max: 150 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.rEQUESTS.rEJECTED')}
                id="requests-rEJECTED"
                name="rEJECTED"
                data-cy="rEJECTED"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.rEQUESTS.rEJECTEDBY')}
                id="requests-rEJECTEDBY"
                name="rEJECTEDBY"
                data-cy="rEJECTEDBY"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.rEQUESTS.rEJECTEDON')}
                id="requests-rEJECTEDON"
                name="rEJECTEDON"
                data-cy="rEJECTEDON"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/requests" replace color="info">
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

export default REQUESTSUpdate;
