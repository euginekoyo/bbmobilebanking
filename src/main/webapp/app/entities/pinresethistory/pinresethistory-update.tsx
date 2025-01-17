import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { createEntity, getEntity, reset, updateEntity } from './pinresethistory.reducer';

export const PINRESETHISTORYUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const pINRESETHISTORYEntity = useAppSelector(state => state.pINRESETHISTORY.entity);
  const loading = useAppSelector(state => state.pINRESETHISTORY.loading);
  const updating = useAppSelector(state => state.pINRESETHISTORY.updating);
  const updateSuccess = useAppSelector(state => state.pINRESETHISTORY.updateSuccess);

  const handleClose = () => {
    navigate('/pinresethistory');
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

    const entity = {
      ...pINRESETHISTORYEntity,
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
          ...pINRESETHISTORYEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="bbMobileBankingAdminApp.pINRESETHISTORY.home.createOrEditLabel" data-cy="PINRESETHISTORYCreateUpdateHeading">
            <Translate contentKey="bbMobileBankingAdminApp.pINRESETHISTORY.home.createOrEditLabel">
              Create or edit a PINRESETHISTORY
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
                  id="pinresethistory-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.pINRESETHISTORY.pHONENUMBER')}
                id="pinresethistory-pHONENUMBER"
                name="pHONENUMBER"
                data-cy="pHONENUMBER"
                type="text"
                validate={{
                  maxLength: { value: 20, message: translate('entity.validation.maxlength', { max: 20 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.pINRESETHISTORY.cUSTOMERNAME')}
                id="pinresethistory-cUSTOMERNAME"
                name="cUSTOMERNAME"
                data-cy="cUSTOMERNAME"
                type="text"
                validate={{
                  maxLength: { value: 150, message: translate('entity.validation.maxlength', { max: 150 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.pINRESETHISTORY.pINBLOCKEDON')}
                id="pinresethistory-pINBLOCKEDON"
                name="pINBLOCKEDON"
                data-cy="pINBLOCKEDON"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.pINRESETHISTORY.pINBLOCKREMARKS')}
                id="pinresethistory-pINBLOCKREMARKS"
                name="pINBLOCKREMARKS"
                data-cy="pINBLOCKREMARKS"
                type="text"
                validate={{
                  maxLength: { value: 200, message: translate('entity.validation.maxlength', { max: 200 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.pINRESETHISTORY.pINRESETBY')}
                id="pinresethistory-pINRESETBY"
                name="pINRESETBY"
                data-cy="pINRESETBY"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.pINRESETHISTORY.pINRESETON')}
                id="pinresethistory-pINRESETON"
                name="pINRESETON"
                data-cy="pINRESETON"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.pINRESETHISTORY.pINRESETAPPROVEDBY')}
                id="pinresethistory-pINRESETAPPROVEDBY"
                name="pINRESETAPPROVEDBY"
                data-cy="pINRESETAPPROVEDBY"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.pINRESETHISTORY.pINRESETAPPROVEDON')}
                id="pinresethistory-pINRESETAPPROVEDON"
                name="pINRESETAPPROVEDON"
                data-cy="pINRESETAPPROVEDON"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.pINRESETHISTORY.pINRESETREMARKS')}
                id="pinresethistory-pINRESETREMARKS"
                name="pINRESETREMARKS"
                data-cy="pINRESETREMARKS"
                type="text"
                validate={{
                  maxLength: { value: 200, message: translate('entity.validation.maxlength', { max: 200 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.pINRESETHISTORY.bRANCHCODE')}
                id="pinresethistory-bRANCHCODE"
                name="bRANCHCODE"
                data-cy="bRANCHCODE"
                type="text"
                validate={{
                  maxLength: { value: 20, message: translate('entity.validation.maxlength', { max: 20 }) },
                }}
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/pinresethistory" replace color="info">
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

export default PINRESETHISTORYUpdate;
