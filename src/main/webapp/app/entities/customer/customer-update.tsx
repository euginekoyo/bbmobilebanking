import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { createEntity, getEntity, reset, updateEntity } from './customer.reducer';

export const CUSTOMERUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const cUSTOMEREntity = useAppSelector(state => state.cUSTOMER.entity);
  const loading = useAppSelector(state => state.cUSTOMER.loading);
  const updating = useAppSelector(state => state.cUSTOMER.updating);
  const updateSuccess = useAppSelector(state => state.cUSTOMER.updateSuccess);

  const handleClose = () => {
    navigate('/customer');
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
    if (values.rEGISTERED !== undefined && typeof values.rEGISTERED !== 'number') {
      values.rEGISTERED = Number(values.rEGISTERED);
    }
    if (values.cSTDELETE !== undefined && typeof values.cSTDELETE !== 'number') {
      values.cSTDELETE = Number(values.cSTDELETE);
    }
    values.rEGDATE = convertDateTimeToServer(values.rEGDATE);
    if (values.aLERTENABLED !== undefined && typeof values.aLERTENABLED !== 'number') {
      values.aLERTENABLED = Number(values.aLERTENABLED);
    }
    values.pARTIALDATE = convertDateTimeToServer(values.pARTIALDATE);
    values.rEGISTERDATE = convertDateTimeToServer(values.rEGISTERDATE);
    if (values.aPPROVED !== undefined && typeof values.aPPROVED !== 'number') {
      values.aPPROVED = Number(values.aPPROVED);
    }
    values.aPPROVEDDATE = convertDateTimeToServer(values.aPPROVEDDATE);
    if (values.dECLINED !== undefined && typeof values.dECLINED !== 'number') {
      values.dECLINED = Number(values.dECLINED);
    }
    values.dECLINEDDATE = convertDateTimeToServer(values.dECLINEDDATE);
    values.dOB = convertDateTimeToServer(values.dOB);
    if (values.aDDACCOUNT !== undefined && typeof values.aDDACCOUNT !== 'number') {
      values.aDDACCOUNT = Number(values.aDDACCOUNT);
    }
    if (values.dEACTIVATED !== undefined && typeof values.dEACTIVATED !== 'number') {
      values.dEACTIVATED = Number(values.dEACTIVATED);
    }
    values.dEACTIVATEDON = convertDateTimeToServer(values.dEACTIVATEDON);
    if (values.pHONENOCHANGED !== undefined && typeof values.pHONENOCHANGED !== 'number') {
      values.pHONENOCHANGED = Number(values.pHONENOCHANGED);
    }
    values.pHONENOCHANGEDON = convertDateTimeToServer(values.pHONENOCHANGEDON);
    if (values.rESET !== undefined && typeof values.rESET !== 'number') {
      values.rESET = Number(values.rESET);
    }
    values.rESETON = convertDateTimeToServer(values.rESETON);
    if (values.pINBLOCK !== undefined && typeof values.pINBLOCK !== 'number') {
      values.pINBLOCK = Number(values.pINBLOCK);
    }
    values.pINBLOCKON = convertDateTimeToServer(values.pINBLOCKON);
    values.aPPROVEDON = convertDateTimeToServer(values.aPPROVEDON);
    if (values.lOGGEDIN !== undefined && typeof values.lOGGEDIN !== 'number') {
      values.lOGGEDIN = Number(values.lOGGEDIN);
    }
    values.dATEOFBIRTH = convertDateTimeToServer(values.dATEOFBIRTH);
    if (values.rESETIMSI !== undefined && typeof values.rESETIMSI !== 'number') {
      values.rESETIMSI = Number(values.rESETIMSI);
    }
    if (values.pASSRESET !== undefined && typeof values.pASSRESET !== 'number') {
      values.pASSRESET = Number(values.pASSRESET);
    }
    values.pASSRESETON = convertDateTimeToServer(values.pASSRESETON);
    if (values.pASSBLOCK !== undefined && typeof values.pASSBLOCK !== 'number') {
      values.pASSBLOCK = Number(values.pASSBLOCK);
    }
    values.pASSBLOCKON = convertDateTimeToServer(values.pASSBLOCKON);
    if (values.pINMARKBLOCK !== undefined && typeof values.pINMARKBLOCK !== 'number') {
      values.pINMARKBLOCK = Number(values.pINMARKBLOCK);
    }
    if (values.pASSMARKBLOCK !== undefined && typeof values.pASSMARKBLOCK !== 'number') {
      values.pASSMARKBLOCK = Number(values.pASSMARKBLOCK);
    }
    if (values.pASSTRIALS !== undefined && typeof values.pASSTRIALS !== 'number') {
      values.pASSTRIALS = Number(values.pASSTRIALS);
    }
    if (values.aPPACTIVE !== undefined && typeof values.aPPACTIVE !== 'number') {
      values.aPPACTIVE = Number(values.aPPACTIVE);
    }
    if (values.aPPMARKEDDISABLE !== undefined && typeof values.aPPMARKEDDISABLE !== 'number') {
      values.aPPMARKEDDISABLE = Number(values.aPPMARKEDDISABLE);
    }
    if (values.aPPMARKEDENABLE !== undefined && typeof values.aPPMARKEDENABLE !== 'number') {
      values.aPPMARKEDENABLE = Number(values.aPPMARKEDENABLE);
    }
    if (values.mARKEDDEACTIVATE !== undefined && typeof values.mARKEDDEACTIVATE !== 'number') {
      values.mARKEDDEACTIVATE = Number(values.mARKEDDEACTIVATE);
    }
    if (values.aTMTRIALS !== undefined && typeof values.aTMTRIALS !== 'number') {
      values.aTMTRIALS = Number(values.aTMTRIALS);
    }
    values.aPPROVEDDISABLEON = convertDateTimeToServer(values.aPPROVEDDISABLEON);
    values.dISABLEDON = convertDateTimeToServer(values.dISABLEDON);
    values.rESETAPPROVEON = convertDateTimeToServer(values.rESETAPPROVEON);
    if (values.vALIDOTP !== undefined && typeof values.vALIDOTP !== 'number') {
      values.vALIDOTP = Number(values.vALIDOTP);
    }
    values.aCTIVATEDON = convertDateTimeToServer(values.aCTIVATEDON);

    const entity = {
      ...cUSTOMEREntity,
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
          rEGDATE: displayDefaultDateTime(),
          pARTIALDATE: displayDefaultDateTime(),
          rEGISTERDATE: displayDefaultDateTime(),
          aPPROVEDDATE: displayDefaultDateTime(),
          dECLINEDDATE: displayDefaultDateTime(),
          dOB: displayDefaultDateTime(),
          dEACTIVATEDON: displayDefaultDateTime(),
          pHONENOCHANGEDON: displayDefaultDateTime(),
          rESETON: displayDefaultDateTime(),
          pINBLOCKON: displayDefaultDateTime(),
          aPPROVEDON: displayDefaultDateTime(),
          dATEOFBIRTH: displayDefaultDateTime(),
          pASSRESETON: displayDefaultDateTime(),
          pASSBLOCKON: displayDefaultDateTime(),
          aPPROVEDDISABLEON: displayDefaultDateTime(),
          dISABLEDON: displayDefaultDateTime(),
          rESETAPPROVEON: displayDefaultDateTime(),
          aCTIVATEDON: displayDefaultDateTime(),
        }
      : {
          ...cUSTOMEREntity,
          rEGDATE: convertDateTimeFromServer(cUSTOMEREntity.rEGDATE),
          pARTIALDATE: convertDateTimeFromServer(cUSTOMEREntity.pARTIALDATE),
          rEGISTERDATE: convertDateTimeFromServer(cUSTOMEREntity.rEGISTERDATE),
          aPPROVEDDATE: convertDateTimeFromServer(cUSTOMEREntity.aPPROVEDDATE),
          dECLINEDDATE: convertDateTimeFromServer(cUSTOMEREntity.dECLINEDDATE),
          dOB: convertDateTimeFromServer(cUSTOMEREntity.dOB),
          dEACTIVATEDON: convertDateTimeFromServer(cUSTOMEREntity.dEACTIVATEDON),
          pHONENOCHANGEDON: convertDateTimeFromServer(cUSTOMEREntity.pHONENOCHANGEDON),
          rESETON: convertDateTimeFromServer(cUSTOMEREntity.rESETON),
          pINBLOCKON: convertDateTimeFromServer(cUSTOMEREntity.pINBLOCKON),
          aPPROVEDON: convertDateTimeFromServer(cUSTOMEREntity.aPPROVEDON),
          dATEOFBIRTH: convertDateTimeFromServer(cUSTOMEREntity.dATEOFBIRTH),
          pASSRESETON: convertDateTimeFromServer(cUSTOMEREntity.pASSRESETON),
          pASSBLOCKON: convertDateTimeFromServer(cUSTOMEREntity.pASSBLOCKON),
          aPPROVEDDISABLEON: convertDateTimeFromServer(cUSTOMEREntity.aPPROVEDDISABLEON),
          dISABLEDON: convertDateTimeFromServer(cUSTOMEREntity.dISABLEDON),
          rESETAPPROVEON: convertDateTimeFromServer(cUSTOMEREntity.rESETAPPROVEON),
          aCTIVATEDON: convertDateTimeFromServer(cUSTOMEREntity.aCTIVATEDON),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="bbMobileBankingAdminApp.cUSTOMER.home.createOrEditLabel" data-cy="CUSTOMERCreateUpdateHeading">
            <Translate contentKey="bbMobileBankingAdminApp.cUSTOMER.home.createOrEditLabel">Create or edit a CUSTOMER</Translate>
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
                  id="customer-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.cUSTOMERNAME')}
                id="customer-cUSTOMERNAME"
                name="cUSTOMERNAME"
                data-cy="cUSTOMERNAME"
                type="text"
                validate={{
                  maxLength: { value: 200, message: translate('entity.validation.maxlength', { max: 200 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.pHONENUMBER')}
                id="customer-pHONENUMBER"
                name="pHONENUMBER"
                data-cy="pHONENUMBER"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 12, message: translate('entity.validation.maxlength', { max: 12 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.cARDNUMBER')}
                id="customer-cARDNUMBER"
                name="cARDNUMBER"
                data-cy="cARDNUMBER"
                type="text"
                validate={{
                  maxLength: { value: 1000, message: translate('entity.validation.maxlength', { max: 1000 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.aCCOUNTNUMBER')}
                id="customer-aCCOUNTNUMBER"
                name="aCCOUNTNUMBER"
                data-cy="aCCOUNTNUMBER"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 20, message: translate('entity.validation.maxlength', { max: 20 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.lANG')}
                id="customer-lANG"
                name="lANG"
                data-cy="lANG"
                type="text"
                validate={{
                  maxLength: { value: 10, message: translate('entity.validation.maxlength', { max: 10 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.pIN')}
                id="customer-pIN"
                name="pIN"
                data-cy="pIN"
                type="text"
                validate={{
                  maxLength: { value: 200, message: translate('entity.validation.maxlength', { max: 200 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.fIRSTLOGIN')}
                id="customer-fIRSTLOGIN"
                name="fIRSTLOGIN"
                data-cy="fIRSTLOGIN"
                type="text"
                validate={{
                  maxLength: { value: 1, message: translate('entity.validation.maxlength', { max: 1 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.aCTIVE')}
                id="customer-aCTIVE"
                name="aCTIVE"
                data-cy="aCTIVE"
                type="text"
                validate={{
                  maxLength: { value: 1, message: translate('entity.validation.maxlength', { max: 1 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.rEGISTERED')}
                id="customer-rEGISTERED"
                name="rEGISTERED"
                data-cy="rEGISTERED"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.cSTDELETE')}
                id="customer-cSTDELETE"
                name="cSTDELETE"
                data-cy="cSTDELETE"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.rEGDATE')}
                id="customer-rEGDATE"
                name="rEGDATE"
                data-cy="rEGDATE"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.aLERTENABLED')}
                id="customer-aLERTENABLED"
                name="aLERTENABLED"
                data-cy="aLERTENABLED"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.rEMARK')}
                id="customer-rEMARK"
                name="rEMARK"
                data-cy="rEMARK"
                type="text"
                validate={{
                  maxLength: { value: 200, message: translate('entity.validation.maxlength', { max: 200 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.iMSI')}
                id="customer-iMSI"
                name="iMSI"
                data-cy="iMSI"
                type="text"
                validate={{
                  maxLength: { value: 200, message: translate('entity.validation.maxlength', { max: 200 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.pARTIALLYREGISTERED')}
                id="customer-pARTIALLYREGISTERED"
                name="pARTIALLYREGISTERED"
                data-cy="pARTIALLYREGISTERED"
                type="text"
                validate={{
                  maxLength: { value: 1, message: translate('entity.validation.maxlength', { max: 1 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.pARTIALDATE')}
                id="customer-pARTIALDATE"
                name="pARTIALDATE"
                data-cy="pARTIALDATE"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.rEGISTERDATE')}
                id="customer-rEGISTERDATE"
                name="rEGISTERDATE"
                data-cy="rEGISTERDATE"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.aPPROVED')}
                id="customer-aPPROVED"
                name="aPPROVED"
                data-cy="aPPROVED"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.aPPROVEDBY')}
                id="customer-aPPROVEDBY"
                name="aPPROVEDBY"
                data-cy="aPPROVEDBY"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.aPPROVEDDATE')}
                id="customer-aPPROVEDDATE"
                name="aPPROVEDDATE"
                data-cy="aPPROVEDDATE"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.dECLINED')}
                id="customer-dECLINED"
                name="dECLINED"
                data-cy="dECLINED"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.dECLINEDBY')}
                id="customer-dECLINEDBY"
                name="dECLINEDBY"
                data-cy="dECLINEDBY"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.dECLINEDDATE')}
                id="customer-dECLINEDDATE"
                name="dECLINEDDATE"
                data-cy="dECLINEDDATE"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.cHECKERREMARKS')}
                id="customer-cHECKERREMARKS"
                name="cHECKERREMARKS"
                data-cy="cHECKERREMARKS"
                type="text"
                validate={{
                  maxLength: { value: 200, message: translate('entity.validation.maxlength', { max: 200 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.pOSTALADDRESS')}
                id="customer-pOSTALADDRESS"
                name="pOSTALADDRESS"
                data-cy="pOSTALADDRESS"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.rESIDENCE')}
                id="customer-rESIDENCE"
                name="rESIDENCE"
                data-cy="rESIDENCE"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.dOB')}
                id="customer-dOB"
                name="dOB"
                data-cy="dOB"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.cREATEDBY')}
                id="customer-cREATEDBY"
                name="cREATEDBY"
                data-cy="cREATEDBY"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.eMAILADDRESS')}
                id="customer-eMAILADDRESS"
                name="eMAILADDRESS"
                data-cy="eMAILADDRESS"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.iDENTIFICATIONID')}
                id="customer-iDENTIFICATIONID"
                name="iDENTIFICATIONID"
                data-cy="iDENTIFICATIONID"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.aDDACCOUNT')}
                id="customer-aDDACCOUNT"
                name="aDDACCOUNT"
                data-cy="aDDACCOUNT"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.aCLINKINGINSTITUTION')}
                id="customer-aCLINKINGINSTITUTION"
                name="aCLINKINGINSTITUTION"
                data-cy="aCLINKINGINSTITUTION"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.dEACTIVATED')}
                id="customer-dEACTIVATED"
                name="dEACTIVATED"
                data-cy="dEACTIVATED"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.dEACTIVATEDBY')}
                id="customer-dEACTIVATEDBY"
                name="dEACTIVATEDBY"
                data-cy="dEACTIVATEDBY"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.dEACTIVATEDON')}
                id="customer-dEACTIVATEDON"
                name="dEACTIVATEDON"
                data-cy="dEACTIVATEDON"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.pHONENOCHANGED')}
                id="customer-pHONENOCHANGED"
                name="pHONENOCHANGED"
                data-cy="pHONENOCHANGED"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.pHONENOCHANGEDBY')}
                id="customer-pHONENOCHANGEDBY"
                name="pHONENOCHANGEDBY"
                data-cy="pHONENOCHANGEDBY"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.pHONENOCHANGEDON')}
                id="customer-pHONENOCHANGEDON"
                name="pHONENOCHANGEDON"
                data-cy="pHONENOCHANGEDON"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.oRIGINALPHONENO')}
                id="customer-oRIGINALPHONENO"
                name="oRIGINALPHONENO"
                data-cy="oRIGINALPHONENO"
                type="text"
                validate={{
                  maxLength: { value: 20, message: translate('entity.validation.maxlength', { max: 20 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.nEWPHONENO')}
                id="customer-nEWPHONENO"
                name="nEWPHONENO"
                data-cy="nEWPHONENO"
                type="text"
                validate={{
                  maxLength: { value: 20, message: translate('entity.validation.maxlength', { max: 20 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.rESET')}
                id="customer-rESET"
                name="rESET"
                data-cy="rESET"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.rESETINGINSTITUTION')}
                id="customer-rESETINGINSTITUTION"
                name="rESETINGINSTITUTION"
                data-cy="rESETINGINSTITUTION"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.pINRESETREMARK')}
                id="customer-pINRESETREMARK"
                name="pINRESETREMARK"
                data-cy="pINRESETREMARK"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.rESETBY')}
                id="customer-rESETBY"
                name="rESETBY"
                data-cy="rESETBY"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.rESETON')}
                id="customer-rESETON"
                name="rESETON"
                data-cy="rESETON"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.uNBLOCKINGINSTITUTION')}
                id="customer-uNBLOCKINGINSTITUTION"
                name="uNBLOCKINGINSTITUTION"
                data-cy="uNBLOCKINGINSTITUTION"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.pINBLOCK')}
                id="customer-pINBLOCK"
                name="pINBLOCK"
                data-cy="pINBLOCK"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.pINBLOCKBY')}
                id="customer-pINBLOCKBY"
                name="pINBLOCKBY"
                data-cy="pINBLOCKBY"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.pINBLOCKREMARKS')}
                id="customer-pINBLOCKREMARKS"
                name="pINBLOCKREMARKS"
                data-cy="pINBLOCKREMARKS"
                type="text"
                validate={{
                  maxLength: { value: 200, message: translate('entity.validation.maxlength', { max: 200 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.bLOCKINGINSTITUTION')}
                id="customer-bLOCKINGINSTITUTION"
                name="bLOCKINGINSTITUTION"
                data-cy="bLOCKINGINSTITUTION"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.pINBLOCKON')}
                id="customer-pINBLOCKON"
                name="pINBLOCKON"
                data-cy="pINBLOCKON"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.aPPROVEDON')}
                id="customer-aPPROVEDON"
                name="aPPROVEDON"
                data-cy="aPPROVEDON"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.pINUNBLOCKBY')}
                id="customer-pINUNBLOCKBY"
                name="pINUNBLOCKBY"
                data-cy="pINUNBLOCKBY"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.lOGGEDIN')}
                id="customer-lOGGEDIN"
                name="lOGGEDIN"
                data-cy="lOGGEDIN"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.tRIALS')}
                id="customer-tRIALS"
                name="tRIALS"
                data-cy="tRIALS"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.iDTYPE')}
                id="customer-iDTYPE"
                name="iDTYPE"
                data-cy="iDTYPE"
                type="text"
                validate={{
                  maxLength: { value: 20, message: translate('entity.validation.maxlength', { max: 20 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.iDNUMBER')}
                id="customer-iDNUMBER"
                name="iDNUMBER"
                data-cy="iDNUMBER"
                type="text"
                validate={{
                  maxLength: { value: 20, message: translate('entity.validation.maxlength', { max: 20 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.gENDER')}
                id="customer-gENDER"
                name="gENDER"
                data-cy="gENDER"
                type="text"
                validate={{
                  maxLength: { value: 1, message: translate('entity.validation.maxlength', { max: 1 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.cIF')}
                id="customer-cIF"
                name="cIF"
                data-cy="cIF"
                type="text"
                validate={{
                  maxLength: { value: 20, message: translate('entity.validation.maxlength', { max: 20 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.dATEOFBIRTH')}
                id="customer-dATEOFBIRTH"
                name="dATEOFBIRTH"
                data-cy="dATEOFBIRTH"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.rEMARKS')}
                id="customer-rEMARKS"
                name="rEMARKS"
                data-cy="rEMARKS"
                type="text"
                validate={{
                  maxLength: { value: 200, message: translate('entity.validation.maxlength', { max: 200 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.rESETIMSI')}
                id="customer-rESETIMSI"
                name="rESETIMSI"
                data-cy="rESETIMSI"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.iMSIRESETBY')}
                id="customer-iMSIRESETBY"
                name="iMSIRESETBY"
                data-cy="iMSIRESETBY"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.fIRSTNAME')}
                id="customer-fIRSTNAME"
                name="fIRSTNAME"
                data-cy="fIRSTNAME"
                type="text"
                validate={{
                  maxLength: { value: 200, message: translate('entity.validation.maxlength', { max: 200 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.sECONDNAME')}
                id="customer-sECONDNAME"
                name="sECONDNAME"
                data-cy="sECONDNAME"
                type="text"
                validate={{
                  maxLength: { value: 200, message: translate('entity.validation.maxlength', { max: 200 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.lASTNAME')}
                id="customer-lASTNAME"
                name="lASTNAME"
                data-cy="lASTNAME"
                type="text"
                validate={{
                  maxLength: { value: 200, message: translate('entity.validation.maxlength', { max: 200 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.pINBLOCKTIME')}
                id="customer-pINBLOCKTIME"
                name="pINBLOCKTIME"
                data-cy="pINBLOCKTIME"
                type="text"
                validate={{
                  maxLength: { value: 7, message: translate('entity.validation.maxlength', { max: 7 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.cUSTOMERSTATUS')}
                id="customer-cUSTOMERSTATUS"
                name="cUSTOMERSTATUS"
                data-cy="cUSTOMERSTATUS"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.uSERNAME')}
                id="customer-uSERNAME"
                name="uSERNAME"
                data-cy="uSERNAME"
                type="text"
                validate={{
                  maxLength: { value: 2000, message: translate('entity.validation.maxlength', { max: 2000 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.pASSWORD')}
                id="customer-pASSWORD"
                name="pASSWORD"
                data-cy="pASSWORD"
                type="text"
                validate={{
                  maxLength: { value: 3900, message: translate('entity.validation.maxlength', { max: 3900 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.dEVICEID')}
                id="customer-dEVICEID"
                name="dEVICEID"
                data-cy="dEVICEID"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.cHANNEL')}
                id="customer-cHANNEL"
                name="cHANNEL"
                data-cy="cHANNEL"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.pASSRESET')}
                id="customer-pASSRESET"
                name="pASSRESET"
                data-cy="pASSRESET"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.pASSRESETBY')}
                id="customer-pASSRESETBY"
                name="pASSRESETBY"
                data-cy="pASSRESETBY"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.pASSRESETON')}
                id="customer-pASSRESETON"
                name="pASSRESETON"
                data-cy="pASSRESETON"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.pASSBLOCK')}
                id="customer-pASSBLOCK"
                name="pASSBLOCK"
                data-cy="pASSBLOCK"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.pASSBLOCKBY')}
                id="customer-pASSBLOCKBY"
                name="pASSBLOCKBY"
                data-cy="pASSBLOCKBY"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.pASSBLOCKON')}
                id="customer-pASSBLOCKON"
                name="pASSBLOCKON"
                data-cy="pASSBLOCKON"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.pINMARKBLOCK')}
                id="customer-pINMARKBLOCK"
                name="pINMARKBLOCK"
                data-cy="pINMARKBLOCK"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.pASSMARKBLOCK')}
                id="customer-pASSMARKBLOCK"
                name="pASSMARKBLOCK"
                data-cy="pASSMARKBLOCK"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.pASSRESETREMARKS')}
                id="customer-pASSRESETREMARKS"
                name="pASSRESETREMARKS"
                data-cy="pASSRESETREMARKS"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.pASSBLOCKREMARKS')}
                id="customer-pASSBLOCKREMARKS"
                name="pASSBLOCKREMARKS"
                data-cy="pASSBLOCKREMARKS"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.pASSUNBLOCKBY')}
                id="customer-pASSUNBLOCKBY"
                name="pASSUNBLOCKBY"
                data-cy="pASSUNBLOCKBY"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.pASSTRIALS')}
                id="customer-pASSTRIALS"
                name="pASSTRIALS"
                data-cy="pASSTRIALS"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.aPPACTIVE')}
                id="customer-aPPACTIVE"
                name="aPPACTIVE"
                data-cy="aPPACTIVE"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.lASTLOGIN')}
                id="customer-lASTLOGIN"
                name="lASTLOGIN"
                data-cy="lASTLOGIN"
                type="text"
                validate={{
                  maxLength: { value: 32, message: translate('entity.validation.maxlength', { max: 32 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.aPPMARKEDDISABLE')}
                id="customer-aPPMARKEDDISABLE"
                name="aPPMARKEDDISABLE"
                data-cy="aPPMARKEDDISABLE"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.dISABLEBY')}
                id="customer-dISABLEBY"
                name="dISABLEBY"
                data-cy="dISABLEBY"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.aPPROVEDISABLEBY')}
                id="customer-aPPROVEDISABLEBY"
                name="aPPROVEDISABLEBY"
                data-cy="aPPROVEDISABLEBY"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.aPPMARKEDENABLE')}
                id="customer-aPPMARKEDENABLE"
                name="aPPMARKEDENABLE"
                data-cy="aPPMARKEDENABLE"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.eNABLEBY')}
                id="customer-eNABLEBY"
                name="eNABLEBY"
                data-cy="eNABLEBY"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.aPPROVEDENABLEBY')}
                id="customer-aPPROVEDENABLEBY"
                name="aPPROVEDENABLEBY"
                data-cy="aPPROVEDENABLEBY"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.mARKEDDEACTIVATE')}
                id="customer-mARKEDDEACTIVATE"
                name="mARKEDDEACTIVATE"
                data-cy="mARKEDDEACTIVATE"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.aPPFIRSTLOGIN')}
                id="customer-aPPFIRSTLOGIN"
                name="aPPFIRSTLOGIN"
                data-cy="aPPFIRSTLOGIN"
                type="text"
                validate={{
                  maxLength: { value: 5, message: translate('entity.validation.maxlength', { max: 5 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.aTMTRIALS')}
                id="customer-aTMTRIALS"
                name="aTMTRIALS"
                data-cy="aTMTRIALS"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.sHORCUTS')}
                id="customer-sHORCUTS"
                name="sHORCUTS"
                data-cy="sHORCUTS"
                type="text"
                validate={{
                  maxLength: { value: 1000, message: translate('entity.validation.maxlength', { max: 1000 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.mARKEDACTIVATE')}
                id="customer-mARKEDACTIVATE"
                name="mARKEDACTIVATE"
                data-cy="mARKEDACTIVATE"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.tOWN')}
                id="customer-tOWN"
                name="tOWN"
                data-cy="tOWN"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.aPPROVEDDISABLEON')}
                id="customer-aPPROVEDDISABLEON"
                name="aPPROVEDDISABLEON"
                data-cy="aPPROVEDDISABLEON"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.dISABLEDON')}
                id="customer-dISABLEDON"
                name="dISABLEDON"
                data-cy="dISABLEDON"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.rESETAPPROVEON')}
                id="customer-rESETAPPROVEON"
                name="rESETAPPROVEON"
                data-cy="rESETAPPROVEON"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.dELETEDBY')}
                id="customer-dELETEDBY"
                name="dELETEDBY"
                data-cy="dELETEDBY"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.qUESTIONSASKED')}
                id="customer-qUESTIONSASKED"
                name="qUESTIONSASKED"
                data-cy="qUESTIONSASKED"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.qUESTIONSTRIALS')}
                id="customer-qUESTIONSTRIALS"
                name="qUESTIONSTRIALS"
                data-cy="qUESTIONSTRIALS"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.qUESTIONSANSWERED')}
                id="customer-qUESTIONSANSWERED"
                name="qUESTIONSANSWERED"
                data-cy="qUESTIONSANSWERED"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.vALIDOTP')}
                id="customer-vALIDOTP"
                name="vALIDOTP"
                data-cy="vALIDOTP"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.aCTIVATEDBY')}
                id="customer-aCTIVATEDBY"
                name="aCTIVATEDBY"
                data-cy="aCTIVATEDBY"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.aCTIVATEDON')}
                id="customer-aCTIVATEDON"
                name="aCTIVATEDON"
                data-cy="aCTIVATEDON"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.cUSTOMER.bRANCHCODE')}
                id="customer-bRANCHCODE"
                name="bRANCHCODE"
                data-cy="bRANCHCODE"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/customer" replace color="info">
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

export default CUSTOMERUpdate;
