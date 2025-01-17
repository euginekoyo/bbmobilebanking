import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { createEntity, getEntity, reset, updateEntity } from './linkedaccounts.reducer';

export const LINKEDACCOUNTSUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const lINKEDACCOUNTSEntity = useAppSelector(state => state.lINKEDACCOUNTS.entity);
  const loading = useAppSelector(state => state.lINKEDACCOUNTS.loading);
  const updating = useAppSelector(state => state.lINKEDACCOUNTS.updating);
  const updateSuccess = useAppSelector(state => state.lINKEDACCOUNTS.updateSuccess);

  const handleClose = () => {
    navigate('/linkedaccounts');
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
    values.tIMELINKED = convertDateTimeToServer(values.tIMELINKED);
    values.aPPROVEDON = convertDateTimeToServer(values.aPPROVEDON);
    values.dECLINEDON = convertDateTimeToServer(values.dECLINEDON);
    values.dELINKEDON = convertDateTimeToServer(values.dELINKEDON);

    const entity = {
      ...lINKEDACCOUNTSEntity,
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
          aPPROVEDON: displayDefaultDateTime(),
          dECLINEDON: displayDefaultDateTime(),
          dELINKEDON: displayDefaultDateTime(),
        }
      : {
          ...lINKEDACCOUNTSEntity,
          tIMELINKED: convertDateTimeFromServer(lINKEDACCOUNTSEntity.tIMELINKED),
          aPPROVEDON: convertDateTimeFromServer(lINKEDACCOUNTSEntity.aPPROVEDON),
          dECLINEDON: convertDateTimeFromServer(lINKEDACCOUNTSEntity.dECLINEDON),
          dELINKEDON: convertDateTimeFromServer(lINKEDACCOUNTSEntity.dELINKEDON),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="bbMobileBankingAdminApp.lINKEDACCOUNTS.home.createOrEditLabel" data-cy="LINKEDACCOUNTSCreateUpdateHeading">
            <Translate contentKey="bbMobileBankingAdminApp.lINKEDACCOUNTS.home.createOrEditLabel">
              Create or edit a LINKEDACCOUNTS
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
                  id="linkedaccounts-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.lINKEDACCOUNTS.aCOUNTNAME')}
                id="linkedaccounts-aCOUNTNAME"
                name="aCOUNTNAME"
                data-cy="aCOUNTNAME"
                type="text"
                validate={{
                  maxLength: { value: 200, message: translate('entity.validation.maxlength', { max: 200 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.lINKEDACCOUNTS.aCCOUNTCLASS')}
                id="linkedaccounts-aCCOUNTCLASS"
                name="aCCOUNTCLASS"
                data-cy="aCCOUNTCLASS"
                type="text"
                validate={{
                  maxLength: { value: 20, message: translate('entity.validation.maxlength', { max: 20 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.lINKEDACCOUNTS.aCCOUNTCURRENCY')}
                id="linkedaccounts-aCCOUNTCURRENCY"
                name="aCCOUNTCURRENCY"
                data-cy="aCCOUNTCURRENCY"
                type="text"
                validate={{
                  maxLength: { value: 20, message: translate('entity.validation.maxlength', { max: 20 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.lINKEDACCOUNTS.aCCOUNTNUMBER')}
                id="linkedaccounts-aCCOUNTNUMBER"
                name="aCCOUNTNUMBER"
                data-cy="aCCOUNTNUMBER"
                type="text"
                validate={{
                  maxLength: { value: 20, message: translate('entity.validation.maxlength', { max: 20 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.lINKEDACCOUNTS.cIF')}
                id="linkedaccounts-cIF"
                name="cIF"
                data-cy="cIF"
                type="text"
                validate={{
                  maxLength: { value: 20, message: translate('entity.validation.maxlength', { max: 20 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.lINKEDACCOUNTS.tIMELINKED')}
                id="linkedaccounts-tIMELINKED"
                name="tIMELINKED"
                data-cy="tIMELINKED"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.lINKEDACCOUNTS.pHONENUMBER')}
                id="linkedaccounts-pHONENUMBER"
                name="pHONENUMBER"
                data-cy="pHONENUMBER"
                type="text"
                validate={{
                  maxLength: { value: 200, message: translate('entity.validation.maxlength', { max: 200 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.lINKEDACCOUNTS.aPPROVEDON')}
                id="linkedaccounts-aPPROVEDON"
                name="aPPROVEDON"
                data-cy="aPPROVEDON"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.lINKEDACCOUNTS.aPPROVED')}
                id="linkedaccounts-aPPROVED"
                name="aPPROVED"
                data-cy="aPPROVED"
                type="text"
                validate={{
                  maxLength: { value: 1, message: translate('entity.validation.maxlength', { max: 1 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.lINKEDACCOUNTS.dECLINED')}
                id="linkedaccounts-dECLINED"
                name="dECLINED"
                data-cy="dECLINED"
                type="text"
                validate={{
                  maxLength: { value: 1, message: translate('entity.validation.maxlength', { max: 1 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.lINKEDACCOUNTS.dECLINEDON')}
                id="linkedaccounts-dECLINEDON"
                name="dECLINEDON"
                data-cy="dECLINEDON"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.lINKEDACCOUNTS.rEMARKS')}
                id="linkedaccounts-rEMARKS"
                name="rEMARKS"
                data-cy="rEMARKS"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 250, message: translate('entity.validation.maxlength', { max: 250 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.lINKEDACCOUNTS.lINKEDBY')}
                id="linkedaccounts-lINKEDBY"
                name="lINKEDBY"
                data-cy="lINKEDBY"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.lINKEDACCOUNTS.aPPROVEDBY')}
                id="linkedaccounts-aPPROVEDBY"
                name="aPPROVEDBY"
                data-cy="aPPROVEDBY"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.lINKEDACCOUNTS.lINKED')}
                id="linkedaccounts-lINKED"
                name="lINKED"
                data-cy="lINKED"
                type="text"
                validate={{
                  maxLength: { value: 1, message: translate('entity.validation.maxlength', { max: 1 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.lINKEDACCOUNTS.aCTIVE')}
                id="linkedaccounts-aCTIVE"
                name="aCTIVE"
                data-cy="aCTIVE"
                type="text"
                validate={{
                  maxLength: { value: 1, message: translate('entity.validation.maxlength', { max: 1 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.lINKEDACCOUNTS.dELINKEDBY')}
                id="linkedaccounts-dELINKEDBY"
                name="dELINKEDBY"
                data-cy="dELINKEDBY"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.lINKEDACCOUNTS.dELINKEDON')}
                id="linkedaccounts-dELINKEDON"
                name="dELINKEDON"
                data-cy="dELINKEDON"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.lINKEDACCOUNTS.dELINKED')}
                id="linkedaccounts-dELINKED"
                name="dELINKED"
                data-cy="dELINKED"
                type="text"
                validate={{
                  maxLength: { value: 1, message: translate('entity.validation.maxlength', { max: 1 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.lINKEDACCOUNTS.aCCOUNTALIAS')}
                id="linkedaccounts-aCCOUNTALIAS"
                name="aCCOUNTALIAS"
                data-cy="aCCOUNTALIAS"
                type="text"
                validate={{
                  maxLength: { value: 20, message: translate('entity.validation.maxlength', { max: 20 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.lINKEDACCOUNTS.sHORTCUTS')}
                id="linkedaccounts-sHORTCUTS"
                name="sHORTCUTS"
                data-cy="sHORTCUTS"
                type="text"
                validate={{
                  maxLength: { value: 1000, message: translate('entity.validation.maxlength', { max: 1000 }) },
                }}
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/linkedaccounts" replace color="info">
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

export default LINKEDACCOUNTSUpdate;
