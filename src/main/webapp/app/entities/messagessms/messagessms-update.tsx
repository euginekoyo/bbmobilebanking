import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { createEntity, getEntity, reset, updateEntity } from './messagessms.reducer';

export const MESSAGESSMSUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const mESSAGESSMSEntity = useAppSelector(state => state.mESSAGESSMS.entity);
  const loading = useAppSelector(state => state.mESSAGESSMS.loading);
  const updating = useAppSelector(state => state.mESSAGESSMS.updating);
  const updateSuccess = useAppSelector(state => state.mESSAGESSMS.updateSuccess);

  const handleClose = () => {
    navigate('/messagessms');
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
    values.tRNDATETIME = convertDateTimeToServer(values.tRNDATETIME);
    if (values.tRIALS !== undefined && typeof values.tRIALS !== 'number') {
      values.tRIALS = Number(values.tRIALS);
    }
    if (values.pRIORITY !== undefined && typeof values.pRIORITY !== 'number') {
      values.pRIORITY = Number(values.pRIORITY);
    }
    if (values.sENT !== undefined && typeof values.sENT !== 'number') {
      values.sENT = Number(values.sENT);
    }
    if (values.dELIVERED !== undefined && typeof values.dELIVERED !== 'number') {
      values.dELIVERED = Number(values.dELIVERED);
    }
    if (values.eRROREXCEPTION !== undefined && typeof values.eRROREXCEPTION !== 'number') {
      values.eRROREXCEPTION = Number(values.eRROREXCEPTION);
    }
    values.dATECREATED = convertDateTimeToServer(values.dATECREATED);
    if (values.tAXPROCESSED !== undefined && typeof values.tAXPROCESSED !== 'number') {
      values.tAXPROCESSED = Number(values.tAXPROCESSED);
    }

    const entity = {
      ...mESSAGESSMSEntity,
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
          tRNDATETIME: displayDefaultDateTime(),
          dATECREATED: displayDefaultDateTime(),
        }
      : {
          ...mESSAGESSMSEntity,
          tRNDATETIME: convertDateTimeFromServer(mESSAGESSMSEntity.tRNDATETIME),
          dATECREATED: convertDateTimeFromServer(mESSAGESSMSEntity.dATECREATED),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="bbMobileBankingAdminApp.mESSAGESSMS.home.createOrEditLabel" data-cy="MESSAGESSMSCreateUpdateHeading">
            <Translate contentKey="bbMobileBankingAdminApp.mESSAGESSMS.home.createOrEditLabel">Create or edit a MESSAGESSMS</Translate>
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
                  id="messagessms-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.mESSAGESSMS.tRNDATETIME')}
                id="messagessms-tRNDATETIME"
                name="tRNDATETIME"
                data-cy="tRNDATETIME"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.mESSAGESSMS.pHONENUMBER')}
                id="messagessms-pHONENUMBER"
                name="pHONENUMBER"
                data-cy="pHONENUMBER"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.mESSAGESSMS.tRANSACTIONNO')}
                id="messagessms-tRANSACTIONNO"
                name="tRANSACTIONNO"
                data-cy="tRANSACTIONNO"
                type="text"
                validate={{
                  maxLength: { value: 4000, message: translate('entity.validation.maxlength', { max: 4000 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.mESSAGESSMS.aCCOUNTNUMBER')}
                id="messagessms-aCCOUNTNUMBER"
                name="aCCOUNTNUMBER"
                data-cy="aCCOUNTNUMBER"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.mESSAGESSMS.mESSAGE')}
                id="messagessms-mESSAGE"
                name="mESSAGE"
                data-cy="mESSAGE"
                type="text"
                validate={{
                  maxLength: { value: 2000, message: translate('entity.validation.maxlength', { max: 2000 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.mESSAGESSMS.cHANNEL')}
                id="messagessms-cHANNEL"
                name="cHANNEL"
                data-cy="cHANNEL"
                type="text"
                validate={{
                  maxLength: { value: 4000, message: translate('entity.validation.maxlength', { max: 4000 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.mESSAGESSMS.tRIALS')}
                id="messagessms-tRIALS"
                name="tRIALS"
                data-cy="tRIALS"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.mESSAGESSMS.pRIORITY')}
                id="messagessms-pRIORITY"
                name="pRIORITY"
                data-cy="pRIORITY"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.mESSAGESSMS.rESPONSECODE')}
                id="messagessms-rESPONSECODE"
                name="rESPONSECODE"
                data-cy="rESPONSECODE"
                type="text"
                validate={{
                  maxLength: { value: 4, message: translate('entity.validation.maxlength', { max: 4 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.mESSAGESSMS.rESPONSEMSG')}
                id="messagessms-rESPONSEMSG"
                name="rESPONSEMSG"
                data-cy="rESPONSEMSG"
                type="text"
                validate={{
                  maxLength: { value: 4000, message: translate('entity.validation.maxlength', { max: 4000 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.mESSAGESSMS.sENT')}
                id="messagessms-sENT"
                name="sENT"
                data-cy="sENT"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.mESSAGESSMS.dELIVERED')}
                id="messagessms-dELIVERED"
                name="dELIVERED"
                data-cy="dELIVERED"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.mESSAGESSMS.tXNTYPE')}
                id="messagessms-tXNTYPE"
                name="tXNTYPE"
                data-cy="tXNTYPE"
                type="text"
                validate={{
                  maxLength: { value: 200, message: translate('entity.validation.maxlength', { max: 200 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.mESSAGESSMS.eRROREXCEPTION')}
                id="messagessms-eRROREXCEPTION"
                name="eRROREXCEPTION"
                data-cy="eRROREXCEPTION"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.mESSAGESSMS.dATECREATED')}
                id="messagessms-dATECREATED"
                name="dATECREATED"
                data-cy="dATECREATED"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.mESSAGESSMS.dATESENT')}
                id="messagessms-dATESENT"
                name="dATESENT"
                data-cy="dATESENT"
                type="text"
                validate={{
                  maxLength: { value: 7, message: translate('entity.validation.maxlength', { max: 7 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.mESSAGESSMS.rTPSREQTIME')}
                id="messagessms-rTPSREQTIME"
                name="rTPSREQTIME"
                data-cy="rTPSREQTIME"
                type="text"
                validate={{
                  maxLength: { value: 200, message: translate('entity.validation.maxlength', { max: 200 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.mESSAGESSMS.fXGENERATED')}
                id="messagessms-fXGENERATED"
                name="fXGENERATED"
                data-cy="fXGENERATED"
                type="text"
                validate={{
                  maxLength: { value: 20, message: translate('entity.validation.maxlength', { max: 20 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.mESSAGESSMS.tAXPROCESSED')}
                id="messagessms-tAXPROCESSED"
                name="tAXPROCESSED"
                data-cy="tAXPROCESSED"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.mESSAGESSMS.bATCHNUMBER')}
                id="messagessms-bATCHNUMBER"
                name="bATCHNUMBER"
                data-cy="bATCHNUMBER"
                type="text"
                validate={{
                  maxLength: { value: 200, message: translate('entity.validation.maxlength', { max: 200 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.mESSAGESSMS.bATCHNUMBERTAX')}
                id="messagessms-bATCHNUMBERTAX"
                name="bATCHNUMBERTAX"
                data-cy="bATCHNUMBERTAX"
                type="text"
                validate={{
                  maxLength: { value: 200, message: translate('entity.validation.maxlength', { max: 200 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.mESSAGESSMS.rESPONSETIME')}
                id="messagessms-rESPONSETIME"
                name="rESPONSETIME"
                data-cy="rESPONSETIME"
                type="text"
                validate={{
                  maxLength: { value: 200, message: translate('entity.validation.maxlength', { max: 200 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.mESSAGESSMS.pDUSEQID')}
                id="messagessms-pDUSEQID"
                name="pDUSEQID"
                data-cy="pDUSEQID"
                type="text"
                validate={{
                  maxLength: { value: 200, message: translate('entity.validation.maxlength', { max: 200 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.mESSAGESSMS.rEMARKS')}
                id="messagessms-rEMARKS"
                name="rEMARKS"
                data-cy="rEMARKS"
                type="text"
                validate={{
                  maxLength: { value: 300, message: translate('entity.validation.maxlength', { max: 300 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.mESSAGESSMS.rESENDBY')}
                id="messagessms-rESENDBY"
                name="rESENDBY"
                data-cy="rESENDBY"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/messagessms" replace color="info">
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

export default MESSAGESSMSUpdate;
