import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { createEntity, getEntity, reset, updateEntity } from './range.reducer';

export const RANGEUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const rANGEEntity = useAppSelector(state => state.rANGE.entity);
  const loading = useAppSelector(state => state.rANGE.loading);
  const updating = useAppSelector(state => state.rANGE.updating);
  const updateSuccess = useAppSelector(state => state.rANGE.updateSuccess);

  const handleClose = () => {
    navigate('/range');
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
    if (values.rANGEFROM !== undefined && typeof values.rANGEFROM !== 'number') {
      values.rANGEFROM = Number(values.rANGEFROM);
    }
    if (values.rANGETO !== undefined && typeof values.rANGETO !== 'number') {
      values.rANGETO = Number(values.rANGETO);
    }
    if (values.aMOUNT !== undefined && typeof values.aMOUNT !== 'number') {
      values.aMOUNT = Number(values.aMOUNT);
    }
    if (values.cHARGEID !== undefined && typeof values.cHARGEID !== 'number') {
      values.cHARGEID = Number(values.cHARGEID);
    }

    const entity = {
      ...rANGEEntity,
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
          ...rANGEEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="bbMobileBankingAdminApp.rANGE.home.createOrEditLabel" data-cy="RANGECreateUpdateHeading">
            <Translate contentKey="bbMobileBankingAdminApp.rANGE.home.createOrEditLabel">Create or edit a RANGE</Translate>
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
                  id="range-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.rANGE.rANGEFROM')}
                id="range-rANGEFROM"
                name="rANGEFROM"
                data-cy="rANGEFROM"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.rANGE.rANGETO')}
                id="range-rANGETO"
                name="rANGETO"
                data-cy="rANGETO"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.rANGE.aMOUNT')}
                id="range-aMOUNT"
                name="aMOUNT"
                data-cy="aMOUNT"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.rANGE.tXNTYPE')}
                id="range-tXNTYPE"
                name="tXNTYPE"
                data-cy="tXNTYPE"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.rANGE.tXNCODE')}
                id="range-tXNCODE"
                name="tXNCODE"
                data-cy="tXNCODE"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.rANGE.cHARGEID')}
                id="range-cHARGEID"
                name="cHARGEID"
                data-cy="cHARGEID"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.rANGE.cHANNEL')}
                id="range-cHANNEL"
                name="cHANNEL"
                data-cy="cHANNEL"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/range" replace color="info">
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

export default RANGEUpdate;
