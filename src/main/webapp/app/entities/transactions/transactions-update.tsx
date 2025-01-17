import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { createEntity, getEntity, reset, updateEntity } from './transactions.reducer';

export const TRANSACTIONSUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const tRANSACTIONSEntity = useAppSelector(state => state.tRANSACTIONS.entity);
  const loading = useAppSelector(state => state.tRANSACTIONS.loading);
  const updating = useAppSelector(state => state.tRANSACTIONS.updating);
  const updateSuccess = useAppSelector(state => state.tRANSACTIONS.updateSuccess);

  const handleClose = () => {
    navigate('/transactions');
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
    if (values.pROCESSED !== undefined && typeof values.pROCESSED !== 'number') {
      values.pROCESSED = Number(values.pROCESSED);
    }
    if (values.cBSPROCESS !== undefined && typeof values.cBSPROCESS !== 'number') {
      values.cBSPROCESS = Number(values.cBSPROCESS);
    }
    if (values.cBSONLINE !== undefined && typeof values.cBSONLINE !== 'number') {
      values.cBSONLINE = Number(values.cBSONLINE);
    }
    if (values.rESPONSESENT !== undefined && typeof values.rESPONSESENT !== 'number') {
      values.rESPONSESENT = Number(values.rESPONSESENT);
    }
    if (values.aUTHORISED !== undefined && typeof values.aUTHORISED !== 'number') {
      values.aUTHORISED = Number(values.aUTHORISED);
    }
    if (values.longERBRANCH !== undefined && typeof values.longERBRANCH !== 'number') {
      values.longERBRANCH = Number(values.longERBRANCH);
    }
    values.dATEX = convertDateTimeToServer(values.dATEX);
    if (values.pOSTED !== undefined && typeof values.pOSTED !== 'number') {
      values.pOSTED = Number(values.pOSTED);
    }
    if (values.aTTEMPTS !== undefined && typeof values.aTTEMPTS !== 'number') {
      values.aTTEMPTS = Number(values.aTTEMPTS);
    }
    if (values.cOMMISSION !== undefined && typeof values.cOMMISSION !== 'number') {
      values.cOMMISSION = Number(values.cOMMISSION);
    }
    if (values.rESPONSECREATED !== undefined && typeof values.rESPONSECREATED !== 'number') {
      values.rESPONSECREATED = Number(values.rESPONSECREATED);
    }
    if (values.oNLINE !== undefined && typeof values.oNLINE !== 'number') {
      values.oNLINE = Number(values.oNLINE);
    }
    if (values.pOSTINGLEGS !== undefined && typeof values.pOSTINGLEGS !== 'number') {
      values.pOSTINGLEGS = Number(values.pOSTINGLEGS);
    }
    if (values.rEQUESTCREATED !== undefined && typeof values.rEQUESTCREATED !== 'number') {
      values.rEQUESTCREATED = Number(values.rEQUESTCREATED);
    }
    if (values.rEQUESTSENT !== undefined && typeof values.rEQUESTSENT !== 'number') {
      values.rEQUESTSENT = Number(values.rEQUESTSENT);
    }
    if (values.mINICBS !== undefined && typeof values.mINICBS !== 'number') {
      values.mINICBS = Number(values.mINICBS);
    }
    if (values.rEVERSED !== undefined && typeof values.rEVERSED !== 'number') {
      values.rEVERSED = Number(values.rEVERSED);
    }
    if (values.oFFLINESENTTOHOST !== undefined && typeof values.oFFLINESENTTOHOST !== 'number') {
      values.oFFLINESENTTOHOST = Number(values.oFFLINESENTTOHOST);
    }

    const entity = {
      ...tRANSACTIONSEntity,
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
          dATEX: displayDefaultDateTime(),
        }
      : {
          ...tRANSACTIONSEntity,
          dATEX: convertDateTimeFromServer(tRANSACTIONSEntity.dATEX),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="bbMobileBankingAdminApp.tRANSACTIONS.home.createOrEditLabel" data-cy="TRANSACTIONSCreateUpdateHeading">
            <Translate contentKey="bbMobileBankingAdminApp.tRANSACTIONS.home.createOrEditLabel">Create or edit a TRANSACTIONS</Translate>
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
                  id="transactions-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.pROCESSED')}
                id="transactions-pROCESSED"
                name="pROCESSED"
                data-cy="pROCESSED"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.iNCOMINGBITMAP')}
                id="transactions-iNCOMINGBITMAP"
                name="iNCOMINGBITMAP"
                data-cy="iNCOMINGBITMAP"
                type="text"
                validate={{
                  maxLength: { value: 150, message: translate('entity.validation.maxlength', { max: 150 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.oUTGOINGBITMAP')}
                id="transactions-oUTGOINGBITMAP"
                name="oUTGOINGBITMAP"
                data-cy="oUTGOINGBITMAP"
                type="text"
                validate={{
                  maxLength: { value: 150, message: translate('entity.validation.maxlength', { max: 150 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.iNMESSAGE')}
                id="transactions-iNMESSAGE"
                name="iNMESSAGE"
                data-cy="iNMESSAGE"
                type="text"
                validate={{
                  maxLength: { value: 4000, message: translate('entity.validation.maxlength', { max: 4000 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.mESSAGETOCBS')}
                id="transactions-mESSAGETOCBS"
                name="mESSAGETOCBS"
                data-cy="mESSAGETOCBS"
                type="text"
                validate={{
                  maxLength: { value: 4000, message: translate('entity.validation.maxlength', { max: 4000 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.mESSAGEFROMCBS')}
                id="transactions-mESSAGEFROMCBS"
                name="mESSAGEFROMCBS"
                data-cy="mESSAGEFROMCBS"
                type="text"
                validate={{
                  maxLength: { value: 4000, message: translate('entity.validation.maxlength', { max: 4000 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.cBSPROCESS')}
                id="transactions-cBSPROCESS"
                name="cBSPROCESS"
                data-cy="cBSPROCESS"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.cBSONLINE')}
                id="transactions-cBSONLINE"
                name="cBSONLINE"
                data-cy="cBSONLINE"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.cBSRESPONSE')}
                id="transactions-cBSRESPONSE"
                name="cBSRESPONSE"
                data-cy="cBSRESPONSE"
                type="text"
                validate={{
                  maxLength: { value: 500, message: translate('entity.validation.maxlength', { max: 500 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.rESPONSEMESSAGE')}
                id="transactions-rESPONSEMESSAGE"
                name="rESPONSEMESSAGE"
                data-cy="rESPONSEMESSAGE"
                type="text"
                validate={{
                  maxLength: { value: 4000, message: translate('entity.validation.maxlength', { max: 4000 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.rESPONSESENT')}
                id="transactions-rESPONSESENT"
                name="rESPONSESENT"
                data-cy="rESPONSESENT"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.cHANNEL')}
                id="transactions-cHANNEL"
                name="cHANNEL"
                data-cy="cHANNEL"
                type="text"
                validate={{
                  maxLength: { value: 20, message: translate('entity.validation.maxlength', { max: 20 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.oRIGINALDATA')}
                id="transactions-oRIGINALDATA"
                name="oRIGINALDATA"
                data-cy="oRIGINALDATA"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.fIELD39RESP')}
                id="transactions-fIELD39RESP"
                name="fIELD39RESP"
                data-cy="fIELD39RESP"
                type="text"
                validate={{
                  maxLength: { value: 150, message: translate('entity.validation.maxlength', { max: 150 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.nARRATION')}
                id="transactions-nARRATION"
                name="nARRATION"
                data-cy="nARRATION"
                type="text"
                validate={{
                  maxLength: { value: 4000, message: translate('entity.validation.maxlength', { max: 4000 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.aUTHORISED')}
                id="transactions-aUTHORISED"
                name="aUTHORISED"
                data-cy="aUTHORISED"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.bRANCHCODE')}
                id="transactions-bRANCHCODE"
                name="bRANCHCODE"
                data-cy="bRANCHCODE"
                type="text"
                validate={{
                  maxLength: { value: 30, message: translate('entity.validation.maxlength', { max: 30 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.fIELD39ORIGINAL')}
                id="transactions-fIELD39ORIGINAL"
                name="fIELD39ORIGINAL"
                data-cy="fIELD39ORIGINAL"
                type="text"
                validate={{
                  maxLength: { value: 150, message: translate('entity.validation.maxlength', { max: 150 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.mESSAGECLASS')}
                id="transactions-mESSAGECLASS"
                name="mESSAGECLASS"
                data-cy="mESSAGECLASS"
                type="text"
                validate={{
                  maxLength: { value: 10, message: translate('entity.validation.maxlength', { max: 10 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.tXNCODE')}
                id="transactions-tXNCODE"
                name="tXNCODE"
                data-cy="tXNCODE"
                type="text"
                validate={{
                  maxLength: { value: 10, message: translate('entity.validation.maxlength', { max: 10 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.cURRCODE')}
                id="transactions-cURRCODE"
                name="cURRCODE"
                data-cy="cURRCODE"
                type="text"
                validate={{
                  maxLength: { value: 5, message: translate('entity.validation.maxlength', { max: 5 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.dEVICE')}
                id="transactions-dEVICE"
                name="dEVICE"
                data-cy="dEVICE"
                type="text"
                validate={{
                  maxLength: { value: 20, message: translate('entity.validation.maxlength', { max: 20 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.bRANCH2')}
                id="transactions-bRANCH2"
                name="bRANCH2"
                data-cy="bRANCH2"
                type="text"
                validate={{
                  maxLength: { value: 30, message: translate('entity.validation.maxlength', { max: 30 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.longERBRANCH')}
                id="transactions-longERBRANCH"
                name="longERBRANCH"
                data-cy="longERBRANCH"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.dATEX')}
                id="transactions-dATEX"
                name="dATEX"
                data-cy="dATEX"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.tIMEX')}
                id="transactions-tIMEX"
                name="tIMEX"
                data-cy="tIMEX"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.pOSTED')}
                id="transactions-pOSTED"
                name="pOSTED"
                data-cy="pOSTED"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.aTTEMPTS')}
                id="transactions-aTTEMPTS"
                name="aTTEMPTS"
                data-cy="aTTEMPTS"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.oRIGINALDATA2')}
                id="transactions-oRIGINALDATA2"
                name="oRIGINALDATA2"
                data-cy="oRIGINALDATA2"
                type="text"
                validate={{
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.cOMMISSION')}
                id="transactions-cOMMISSION"
                name="cOMMISSION"
                data-cy="cOMMISSION"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.rESPONSECREATED')}
                id="transactions-rESPONSECREATED"
                name="rESPONSECREATED"
                data-cy="rESPONSECREATED"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.oNLINE')}
                id="transactions-oNLINE"
                name="oNLINE"
                data-cy="oNLINE"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.oRIGINALDATA3')}
                id="transactions-oRIGINALDATA3"
                name="oRIGINALDATA3"
                data-cy="oRIGINALDATA3"
                type="text"
                validate={{
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.tOSWITCH')}
                id="transactions-tOSWITCH"
                name="tOSWITCH"
                data-cy="tOSWITCH"
                type="text"
                validate={{
                  maxLength: { value: 15, message: translate('entity.validation.maxlength', { max: 15 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.fROMSWITCH')}
                id="transactions-fROMSWITCH"
                name="fROMSWITCH"
                data-cy="fROMSWITCH"
                type="text"
                validate={{
                  maxLength: { value: 15, message: translate('entity.validation.maxlength', { max: 15 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.tOCBS')}
                id="transactions-tOCBS"
                name="tOCBS"
                data-cy="tOCBS"
                type="text"
                validate={{
                  maxLength: { value: 15, message: translate('entity.validation.maxlength', { max: 15 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.fROMCBS')}
                id="transactions-fROMCBS"
                name="fROMCBS"
                data-cy="fROMCBS"
                type="text"
                validate={{
                  maxLength: { value: 15, message: translate('entity.validation.maxlength', { max: 15 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.pOSTINGLEGS')}
                id="transactions-pOSTINGLEGS"
                name="pOSTINGLEGS"
                data-cy="pOSTINGLEGS"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.cOMMISSIONTXNCODE')}
                id="transactions-cOMMISSIONTXNCODE"
                name="cOMMISSIONTXNCODE"
                data-cy="cOMMISSIONTXNCODE"
                type="text"
                validate={{
                  maxLength: { value: 10, message: translate('entity.validation.maxlength', { max: 10 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.hOSTREF')}
                id="transactions-hOSTREF"
                name="hOSTREF"
                data-cy="hOSTREF"
                type="text"
                validate={{
                  maxLength: { value: 30, message: translate('entity.validation.maxlength', { max: 30 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.rEQUESTCREATED')}
                id="transactions-rEQUESTCREATED"
                name="rEQUESTCREATED"
                data-cy="rEQUESTCREATED"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.rEQUESTMESSAGE')}
                id="transactions-rEQUESTMESSAGE"
                name="rEQUESTMESSAGE"
                data-cy="rEQUESTMESSAGE"
                type="text"
                validate={{
                  maxLength: { value: 4000, message: translate('entity.validation.maxlength', { max: 4000 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.oUTGOINGBITMAPFLEX')}
                id="transactions-oUTGOINGBITMAPFLEX"
                name="oUTGOINGBITMAPFLEX"
                data-cy="oUTGOINGBITMAPFLEX"
                type="text"
                validate={{
                  maxLength: { value: 150, message: translate('entity.validation.maxlength', { max: 150 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.iNCOMINGBITMAPFLEX')}
                id="transactions-iNCOMINGBITMAPFLEX"
                name="iNCOMINGBITMAPFLEX"
                data-cy="iNCOMINGBITMAPFLEX"
                type="text"
                validate={{
                  maxLength: { value: 150, message: translate('entity.validation.maxlength', { max: 150 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.rEQUESTSENT')}
                id="transactions-rEQUESTSENT"
                name="rEQUESTSENT"
                data-cy="rEQUESTSENT"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.mINICBS')}
                id="transactions-mINICBS"
                name="mINICBS"
                data-cy="mINICBS"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.rEVERSED')}
                id="transactions-rEVERSED"
                name="rEVERSED"
                data-cy="rEVERSED"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.oFFLINESENTTOHOST')}
                id="transactions-oFFLINESENTTOHOST"
                name="oFFLINESENTTOHOST"
                data-cy="oFFLINESENTTOHOST"
                type="text"
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.oFFLINERESPONSE')}
                id="transactions-oFFLINERESPONSE"
                name="oFFLINERESPONSE"
                data-cy="oFFLINERESPONSE"
                type="text"
                validate={{
                  maxLength: { value: 150, message: translate('entity.validation.maxlength', { max: 150 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.sOURCELongERFACE')}
                id="transactions-sOURCELongERFACE"
                name="sOURCELongERFACE"
                data-cy="sOURCELongERFACE"
                type="text"
                validate={{
                  maxLength: { value: 40, message: translate('entity.validation.maxlength', { max: 40 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.mTIRRN')}
                id="transactions-mTIRRN"
                name="mTIRRN"
                data-cy="mTIRRN"
                type="text"
                validate={{
                  maxLength: { value: 150, message: translate('entity.validation.maxlength', { max: 150 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.hOSTRESPONSECODE')}
                id="transactions-hOSTRESPONSECODE"
                name="hOSTRESPONSECODE"
                data-cy="hOSTRESPONSECODE"
                type="text"
                validate={{
                  maxLength: { value: 200, message: translate('entity.validation.maxlength', { max: 200 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.fIELD48')}
                id="transactions-fIELD48"
                name="fIELD48"
                data-cy="fIELD48"
                type="text"
                validate={{
                  maxLength: { value: 150, message: translate('entity.validation.maxlength', { max: 150 }) },
                }}
              />
              <ValidatedField
                label={translate('bbMobileBankingAdminApp.tRANSACTIONS.sOURCE')}
                id="transactions-sOURCE"
                name="sOURCE"
                data-cy="sOURCE"
                type="text"
                validate={{
                  maxLength: { value: 150, message: translate('entity.validation.maxlength', { max: 150 }) },
                }}
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/transactions" replace color="info">
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

export default TRANSACTIONSUpdate;
