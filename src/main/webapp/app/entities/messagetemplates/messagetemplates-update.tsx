import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { createEntity, getEntity, reset, updateEntity } from './messagetemplates.reducer';

export const MESSAGETEMPLATESUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const mESSAGETEMPLATESEntity = useAppSelector(state => state.mESSAGETEMPLATES.entity);
  const loading = useAppSelector(state => state.mESSAGETEMPLATES.loading);
  const updating = useAppSelector(state => state.mESSAGETEMPLATES.updating);
  const updateSuccess = useAppSelector(state => state.mESSAGETEMPLATES.updateSuccess);

  const handleClose = () => {
    navigate('/messagetemplates');
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
    values.cREATEDON = convertDateTimeToServer(values.cREATEDON);

    const entity = {
      ...mESSAGETEMPLATESEntity,
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
        }
      : {
          ...mESSAGETEMPLATESEntity,
          cREATEDON: convertDateTimeFromServer(mESSAGETEMPLATESEntity.cREATEDON),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="bbMobileBankingAdminApp.mESSAGETEMPLATES.home.createOrEditLabel" data-cy="MESSAGETEMPLATESCreateUpdateHeading">
            <Translate contentKey="bbMobileBankingAdminApp.mESSAGETEMPLATES.home.createOrEditLabel">
              Create or edit a MESSAGETEMPLATES
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
                  id="messagetemplates-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.mESSAGETEMPLATES.mESSAGETYPE')}
                id="messagetemplates-mESSAGETYPE"
                name="mESSAGETYPE"
                data-cy="mESSAGETYPE"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.mESSAGETEMPLATES.dESCRIPTION')}
                id="messagetemplates-dESCRIPTION"
                name="dESCRIPTION"
                data-cy="dESCRIPTION"
                type="text"
                validate={{
                  maxLength: { value: 200, message: translate('entity.validation.maxlength', { max: 200 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.mESSAGETEMPLATES.mESSAGEENGLISH')}
                id="messagetemplates-mESSAGEENGLISH"
                name="mESSAGEENGLISH"
                data-cy="mESSAGEENGLISH"
                type="text"
                validate={{
                  maxLength: { value: 4000, message: translate('entity.validation.maxlength', { max: 4000 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.mESSAGETEMPLATES.mESSAGESOMALI')}
                id="messagetemplates-mESSAGESOMALI"
                name="mESSAGESOMALI"
                data-cy="mESSAGESOMALI"
                type="text"
                validate={{
                  maxLength: { value: 4000, message: translate('entity.validation.maxlength', { max: 4000 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.mESSAGETEMPLATES.cREATEDON')}
                id="messagetemplates-cREATEDON"
                name="cREATEDON"
                data-cy="cREATEDON"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/messagetemplates" replace color="info">
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

export default MESSAGETEMPLATESUpdate;
