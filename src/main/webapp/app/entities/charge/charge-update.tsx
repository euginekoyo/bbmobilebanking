import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities as getCHARGERANGES } from 'app/entities/chargeranges/chargeranges.reducer';
import { getEntities as getRANGES } from 'app/entities/range/range.reducer';
import { createEntity, getEntity, reset, updateEntity } from './charge.reducer';

export const CHARGEUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const cHARGERANGES = useAppSelector(state => state.cHARGERANGES.entities);
  const rANGES = useAppSelector(state => state.rANGE.entities);
  const cHARGEEntity = useAppSelector(state => state.cHARGE.entity);
  const loading = useAppSelector(state => state.cHARGE.loading);
  const updating = useAppSelector(state => state.cHARGE.updating);
  const updateSuccess = useAppSelector(state => state.cHARGE.updateSuccess);

  const handleClose = () => {
    navigate('/charge');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getCHARGERANGES({}));
    dispatch(getRANGES({}));
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
    if (values.fEEMODE !== undefined && typeof values.fEEMODE !== 'number') {
      values.fEEMODE = Number(values.fEEMODE);
    }
    if (values.aMOUNT !== undefined && typeof values.aMOUNT !== 'number') {
      values.aMOUNT = Number(values.aMOUNT);
    }
    values.dATECREATED = convertDateTimeToServer(values.dATECREATED);
    if (values.tXNCODE !== undefined && typeof values.tXNCODE !== 'number') {
      values.tXNCODE = Number(values.tXNCODE);
    }
    values.aPPROVEDDATE = convertDateTimeToServer(values.aPPROVEDDATE);

    const entity = {
      ...cHARGEEntity,
      ...values,
      cHARGERANGES: cHARGERANGES.find(it => it.id.toString() === values.cHARGERANGES?.toString()),
      rANGE: rANGES.find(it => it.id.toString() === values.rANGE?.toString()),
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
          ...cHARGEEntity,
          dATECREATED: convertDateTimeFromServer(cHARGEEntity.dATECREATED),
          aPPROVEDDATE: convertDateTimeFromServer(cHARGEEntity.aPPROVEDDATE),
          cHARGERANGES: cHARGEEntity?.cHARGERANGES?.id,
          rANGE: cHARGEEntity?.rANGE?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="bbMobileBankingAdminApp.cHARGE.home.createOrEditLabel" data-cy="CHARGECreateUpdateHeading">
            <Translate contentKey="bbMobileBankingAdminApp.cHARGE.home.createOrEditLabel">Create or edit a CHARGE</Translate>
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
                  id="charge-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cHARGE.tXNTYPE')}
                id="charge-tXNTYPE"
                name="tXNTYPE"
                data-cy="tXNTYPE"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 150, message: translate('entity.validation.maxlength', { max: 150 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cHARGE.fEEMODE')}
                id="charge-fEEMODE"
                name="fEEMODE"
                data-cy="fEEMODE"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cHARGE.aMOUNT')}
                id="charge-aMOUNT"
                name="aMOUNT"
                data-cy="aMOUNT"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cHARGE.dATECREATED')}
                id="charge-dATECREATED"
                name="dATECREATED"
                data-cy="dATECREATED"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cHARGE.cREATEDBY')}
                id="charge-cREATEDBY"
                name="cREATEDBY"
                data-cy="cREATEDBY"
                type="text"
                validate={{
                  maxLength: { value: 150, message: translate('entity.validation.maxlength', { max: 150 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cHARGE.aPPROVED')}
                id="charge-aPPROVED"
                name="aPPROVED"
                data-cy="aPPROVED"
                type="text"
                validate={{
                  maxLength: { value: 150, message: translate('entity.validation.maxlength', { max: 150 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cHARGE.aPPROVEDBY')}
                id="charge-aPPROVEDBY"
                name="aPPROVEDBY"
                data-cy="aPPROVEDBY"
                type="text"
                validate={{
                  maxLength: { value: 150, message: translate('entity.validation.maxlength', { max: 150 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cHARGE.cHANNEL')}
                id="charge-cHANNEL"
                name="cHANNEL"
                data-cy="cHANNEL"
                type="text"
                validate={{
                  maxLength: { value: 150, message: translate('entity.validation.maxlength', { max: 150 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cHARGE.tXNCODE')}
                id="charge-tXNCODE"
                name="tXNCODE"
                data-cy="tXNCODE"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cHARGE.dESCRIPTION')}
                id="charge-dESCRIPTION"
                name="dESCRIPTION"
                data-cy="dESCRIPTION"
                type="text"
                validate={{
                  maxLength: { value: 64, message: translate('entity.validation.maxlength', { max: 64 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cHARGE.aPPROVEDDATE')}
                id="charge-aPPROVEDDATE"
                name="aPPROVEDDATE"
                data-cy="aPPROVEDDATE"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                id="charge-cHARGERANGES"
                name="cHARGERANGES"
                data-cy="cHARGERANGES"
                label={translate('bbMobileBankingAdminApp.cHARGE.cHARGERANGES')}
                type="select"
              >
                <option value="" key="0" />
                {cHARGERANGES
                  ? cHARGERANGES.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="charge-rANGE"
                name="rANGE"
                data-cy="rANGE"
                label={translate('bbMobileBankingAdminApp.cHARGE.rANGE')}
                type="select"
              >
                <option value="" key="0" />
                {rANGES
                  ? rANGES.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/charge" replace color="info">
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

export default CHARGEUpdate;
