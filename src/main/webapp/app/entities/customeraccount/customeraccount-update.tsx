import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, isNumber, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { createEntity, getEntity, reset, updateEntity } from './customeraccount.reducer';

export const CUSTOMERACCOUNTUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const cUSTOMERACCOUNTEntity = useAppSelector(state => state.cUSTOMERACCOUNT.entity);
  const loading = useAppSelector(state => state.cUSTOMERACCOUNT.loading);
  const updating = useAppSelector(state => state.cUSTOMERACCOUNT.updating);
  const updateSuccess = useAppSelector(state => state.cUSTOMERACCOUNT.updateSuccess);

  const handleClose = () => {
    navigate('/customeraccount');
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
    if (values.cUSTOMERID !== undefined && typeof values.cUSTOMERID !== 'number') {
      values.cUSTOMERID = Number(values.cUSTOMERID);
    }
    values.tIMELINKED = convertDateTimeToServer(values.tIMELINKED);
    if (values.bLOCKED !== undefined && typeof values.bLOCKED !== 'number') {
      values.bLOCKED = Number(values.bLOCKED);
    }
    if (values.sTOPPED !== undefined && typeof values.sTOPPED !== 'number') {
      values.sTOPPED = Number(values.sTOPPED);
    }
    if (values.dORMANT !== undefined && typeof values.dORMANT !== 'number') {
      values.dORMANT = Number(values.dORMANT);
    }

    const entity = {
      ...cUSTOMERACCOUNTEntity,
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
          tIMELINKED: displayDefaultDateTime(),
        }
      : {
          ...cUSTOMERACCOUNTEntity,
          tIMELINKED: convertDateTimeFromServer(cUSTOMERACCOUNTEntity.tIMELINKED),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="bbMobileBankingAdminApp.cUSTOMERACCOUNT.home.createOrEditLabel" data-cy="CUSTOMERACCOUNTCreateUpdateHeading">
            <Translate contentKey="bbMobileBankingAdminApp.cUSTOMERACCOUNT.home.createOrEditLabel">
              Create or edit a CUSTOMERACCOUNT
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
                  id="customeraccount-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMERACCOUNT.cUSTOMERID')}
                id="customeraccount-cUSTOMERID"
                name="cUSTOMERID"
                data-cy="cUSTOMERID"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMERACCOUNT.aCCOUNTNUMBER')}
                id="customeraccount-aCCOUNTNUMBER"
                name="aCCOUNTNUMBER"
                data-cy="aCCOUNTNUMBER"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 11, message: translate('entity.validation.maxlength', { max: 11 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMERACCOUNT.aCCOUNTCLASS')}
                id="customeraccount-aCCOUNTCLASS"
                name="aCCOUNTCLASS"
                data-cy="aCCOUNTCLASS"
                type="text"
                validate={{
                  maxLength: { value: 10, message: translate('entity.validation.maxlength', { max: 10 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMERACCOUNT.cUSTOMERNUMBER')}
                id="customeraccount-cUSTOMERNUMBER"
                name="cUSTOMERNUMBER"
                data-cy="cUSTOMERNUMBER"
                type="text"
                validate={{
                  maxLength: { value: 20, message: translate('entity.validation.maxlength', { max: 20 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMERACCOUNT.cIF')}
                id="customeraccount-cIF"
                name="cIF"
                data-cy="cIF"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 20, message: translate('entity.validation.maxlength', { max: 20 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMERACCOUNT.tIMELINKED')}
                id="customeraccount-tIMELINKED"
                name="tIMELINKED"
                data-cy="tIMELINKED"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMERACCOUNT.bLOCKED')}
                id="customeraccount-bLOCKED"
                name="bLOCKED"
                data-cy="bLOCKED"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMERACCOUNT.sTOPPED')}
                id="customeraccount-sTOPPED"
                name="sTOPPED"
                data-cy="sTOPPED"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMERACCOUNT.dORMANT')}
                id="customeraccount-dORMANT"
                name="dORMANT"
                data-cy="dORMANT"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/customeraccount" replace color="info">
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

export default CUSTOMERACCOUNTUpdate;
