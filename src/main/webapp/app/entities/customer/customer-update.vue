<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate @submit.prevent="save()">
        <h2
          id="bbMobileBankingAdminApp.customer.home.createOrEditLabel"
          data-cy="CustomerCreateUpdateHeading"
          v-text="t$('bbMobileBankingAdminApp.customer.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="customer.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="customer.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.customername')"
              for="customer-customername"
            ></label>
            <input
              type="text"
              class="form-control"
              name="customername"
              id="customer-customername"
              data-cy="customername"
              :class="{ valid: !v$.customername.$invalid, invalid: v$.customername.$invalid }"
              v-model="v$.customername.$model"
            />
            <div v-if="v$.customername.$anyDirty && v$.customername.$invalid">
              <small class="form-text text-danger" v-for="error of v$.customername.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.phonenumber')"
              for="customer-phonenumber"
            ></label>
            <input
              type="text"
              class="form-control"
              name="phonenumber"
              id="customer-phonenumber"
              data-cy="phonenumber"
              :class="{ valid: !v$.phonenumber.$invalid, invalid: v$.phonenumber.$invalid }"
              v-model="v$.phonenumber.$model"
              required
            />
            <div v-if="v$.phonenumber.$anyDirty && v$.phonenumber.$invalid">
              <small class="form-text text-danger" v-for="error of v$.phonenumber.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.cardnumber')" for="customer-cardnumber"></label>
            <input
              type="text"
              class="form-control"
              name="cardnumber"
              id="customer-cardnumber"
              data-cy="cardnumber"
              :class="{ valid: !v$.cardnumber.$invalid, invalid: v$.cardnumber.$invalid }"
              v-model="v$.cardnumber.$model"
            />
            <div v-if="v$.cardnumber.$anyDirty && v$.cardnumber.$invalid">
              <small class="form-text text-danger" v-for="error of v$.cardnumber.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.accountnumber')"
              for="customer-accountnumber"
            ></label>
            <input
              type="text"
              class="form-control"
              name="accountnumber"
              id="customer-accountnumber"
              data-cy="accountnumber"
              :class="{ valid: !v$.accountnumber.$invalid, invalid: v$.accountnumber.$invalid }"
              v-model="v$.accountnumber.$model"
              required
            />
            <div v-if="v$.accountnumber.$anyDirty && v$.accountnumber.$invalid">
              <small class="form-text text-danger" v-for="error of v$.accountnumber.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.lang')" for="customer-lang"></label>
            <input
              type="text"
              class="form-control"
              name="lang"
              id="customer-lang"
              data-cy="lang"
              :class="{ valid: !v$.lang.$invalid, invalid: v$.lang.$invalid }"
              v-model="v$.lang.$model"
            />
            <div v-if="v$.lang.$anyDirty && v$.lang.$invalid">
              <small class="form-text text-danger" v-for="error of v$.lang.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.pin')" for="customer-pin"></label>
            <input
              type="text"
              class="form-control"
              name="pin"
              id="customer-pin"
              data-cy="pin"
              :class="{ valid: !v$.pin.$invalid, invalid: v$.pin.$invalid }"
              v-model="v$.pin.$model"
            />
            <div v-if="v$.pin.$anyDirty && v$.pin.$invalid">
              <small class="form-text text-danger" v-for="error of v$.pin.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.firstlogin')" for="customer-firstlogin"></label>
            <input
              type="text"
              class="form-control"
              name="firstlogin"
              id="customer-firstlogin"
              data-cy="firstlogin"
              :class="{ valid: !v$.firstlogin.$invalid, invalid: v$.firstlogin.$invalid }"
              v-model="v$.firstlogin.$model"
            />
            <div v-if="v$.firstlogin.$anyDirty && v$.firstlogin.$invalid">
              <small class="form-text text-danger" v-for="error of v$.firstlogin.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.active')" for="customer-active"></label>
            <input
              type="text"
              class="form-control"
              name="active"
              id="customer-active"
              data-cy="active"
              :class="{ valid: !v$.active.$invalid, invalid: v$.active.$invalid }"
              v-model="v$.active.$model"
            />
            <div v-if="v$.active.$anyDirty && v$.active.$invalid">
              <small class="form-text text-danger" v-for="error of v$.active.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.registered')" for="customer-registered"></label>
            <input
              type="number"
              class="form-control"
              name="registered"
              id="customer-registered"
              data-cy="registered"
              :class="{ valid: !v$.registered.$invalid, invalid: v$.registered.$invalid }"
              v-model.number="v$.registered.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.cstdelete')" for="customer-cstdelete"></label>
            <input
              type="number"
              class="form-control"
              name="cstdelete"
              id="customer-cstdelete"
              data-cy="cstdelete"
              :class="{ valid: !v$.cstdelete.$invalid, invalid: v$.cstdelete.$invalid }"
              v-model.number="v$.cstdelete.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.regdate')" for="customer-regdate"></label>
            <div class="d-flex">
              <input
                id="customer-regdate"
                data-cy="regdate"
                type="datetime-local"
                class="form-control"
                name="regdate"
                :class="{ valid: !v$.regdate.$invalid, invalid: v$.regdate.$invalid }"
                :value="convertDateTimeFromServer(v$.regdate.$model)"
                @change="updateInstantField('regdate', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.alertenabled')"
              for="customer-alertenabled"
            ></label>
            <input
              type="number"
              class="form-control"
              name="alertenabled"
              id="customer-alertenabled"
              data-cy="alertenabled"
              :class="{ valid: !v$.alertenabled.$invalid, invalid: v$.alertenabled.$invalid }"
              v-model.number="v$.alertenabled.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.remark')" for="customer-remark"></label>
            <input
              type="text"
              class="form-control"
              name="remark"
              id="customer-remark"
              data-cy="remark"
              :class="{ valid: !v$.remark.$invalid, invalid: v$.remark.$invalid }"
              v-model="v$.remark.$model"
            />
            <div v-if="v$.remark.$anyDirty && v$.remark.$invalid">
              <small class="form-text text-danger" v-for="error of v$.remark.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.imsi')" for="customer-imsi"></label>
            <input
              type="text"
              class="form-control"
              name="imsi"
              id="customer-imsi"
              data-cy="imsi"
              :class="{ valid: !v$.imsi.$invalid, invalid: v$.imsi.$invalid }"
              v-model="v$.imsi.$model"
            />
            <div v-if="v$.imsi.$anyDirty && v$.imsi.$invalid">
              <small class="form-text text-danger" v-for="error of v$.imsi.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.partiallyregistered')"
              for="customer-partiallyregistered"
            ></label>
            <input
              type="text"
              class="form-control"
              name="partiallyregistered"
              id="customer-partiallyregistered"
              data-cy="partiallyregistered"
              :class="{ valid: !v$.partiallyregistered.$invalid, invalid: v$.partiallyregistered.$invalid }"
              v-model="v$.partiallyregistered.$model"
            />
            <div v-if="v$.partiallyregistered.$anyDirty && v$.partiallyregistered.$invalid">
              <small class="form-text text-danger" v-for="error of v$.partiallyregistered.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.partialdate')"
              for="customer-partialdate"
            ></label>
            <div class="d-flex">
              <input
                id="customer-partialdate"
                data-cy="partialdate"
                type="datetime-local"
                class="form-control"
                name="partialdate"
                :class="{ valid: !v$.partialdate.$invalid, invalid: v$.partialdate.$invalid }"
                :value="convertDateTimeFromServer(v$.partialdate.$model)"
                @change="updateInstantField('partialdate', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.registerdate')"
              for="customer-registerdate"
            ></label>
            <div class="d-flex">
              <input
                id="customer-registerdate"
                data-cy="registerdate"
                type="datetime-local"
                class="form-control"
                name="registerdate"
                :class="{ valid: !v$.registerdate.$invalid, invalid: v$.registerdate.$invalid }"
                :value="convertDateTimeFromServer(v$.registerdate.$model)"
                @change="updateInstantField('registerdate', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.approved')" for="customer-approved"></label>
            <input
              type="number"
              class="form-control"
              name="approved"
              id="customer-approved"
              data-cy="approved"
              :class="{ valid: !v$.approved.$invalid, invalid: v$.approved.$invalid }"
              v-model.number="v$.approved.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.approvedby')" for="customer-approvedby"></label>
            <input
              type="text"
              class="form-control"
              name="approvedby"
              id="customer-approvedby"
              data-cy="approvedby"
              :class="{ valid: !v$.approvedby.$invalid, invalid: v$.approvedby.$invalid }"
              v-model="v$.approvedby.$model"
            />
            <div v-if="v$.approvedby.$anyDirty && v$.approvedby.$invalid">
              <small class="form-text text-danger" v-for="error of v$.approvedby.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.approveddate')"
              for="customer-approveddate"
            ></label>
            <div class="d-flex">
              <input
                id="customer-approveddate"
                data-cy="approveddate"
                type="datetime-local"
                class="form-control"
                name="approveddate"
                :class="{ valid: !v$.approveddate.$invalid, invalid: v$.approveddate.$invalid }"
                :value="convertDateTimeFromServer(v$.approveddate.$model)"
                @change="updateInstantField('approveddate', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.declined')" for="customer-declined"></label>
            <input
              type="number"
              class="form-control"
              name="declined"
              id="customer-declined"
              data-cy="declined"
              :class="{ valid: !v$.declined.$invalid, invalid: v$.declined.$invalid }"
              v-model.number="v$.declined.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.declinedby')" for="customer-declinedby"></label>
            <input
              type="text"
              class="form-control"
              name="declinedby"
              id="customer-declinedby"
              data-cy="declinedby"
              :class="{ valid: !v$.declinedby.$invalid, invalid: v$.declinedby.$invalid }"
              v-model="v$.declinedby.$model"
            />
            <div v-if="v$.declinedby.$anyDirty && v$.declinedby.$invalid">
              <small class="form-text text-danger" v-for="error of v$.declinedby.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.declineddate')"
              for="customer-declineddate"
            ></label>
            <div class="d-flex">
              <input
                id="customer-declineddate"
                data-cy="declineddate"
                type="datetime-local"
                class="form-control"
                name="declineddate"
                :class="{ valid: !v$.declineddate.$invalid, invalid: v$.declineddate.$invalid }"
                :value="convertDateTimeFromServer(v$.declineddate.$model)"
                @change="updateInstantField('declineddate', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.checkerremarks')"
              for="customer-checkerremarks"
            ></label>
            <input
              type="text"
              class="form-control"
              name="checkerremarks"
              id="customer-checkerremarks"
              data-cy="checkerremarks"
              :class="{ valid: !v$.checkerremarks.$invalid, invalid: v$.checkerremarks.$invalid }"
              v-model="v$.checkerremarks.$model"
            />
            <div v-if="v$.checkerremarks.$anyDirty && v$.checkerremarks.$invalid">
              <small class="form-text text-danger" v-for="error of v$.checkerremarks.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.postaladdress')"
              for="customer-postaladdress"
            ></label>
            <input
              type="text"
              class="form-control"
              name="postaladdress"
              id="customer-postaladdress"
              data-cy="postaladdress"
              :class="{ valid: !v$.postaladdress.$invalid, invalid: v$.postaladdress.$invalid }"
              v-model="v$.postaladdress.$model"
            />
            <div v-if="v$.postaladdress.$anyDirty && v$.postaladdress.$invalid">
              <small class="form-text text-danger" v-for="error of v$.postaladdress.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.residence')" for="customer-residence"></label>
            <input
              type="text"
              class="form-control"
              name="residence"
              id="customer-residence"
              data-cy="residence"
              :class="{ valid: !v$.residence.$invalid, invalid: v$.residence.$invalid }"
              v-model="v$.residence.$model"
            />
            <div v-if="v$.residence.$anyDirty && v$.residence.$invalid">
              <small class="form-text text-danger" v-for="error of v$.residence.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.dob')" for="customer-dob"></label>
            <div class="d-flex">
              <input
                id="customer-dob"
                data-cy="dob"
                type="datetime-local"
                class="form-control"
                name="dob"
                :class="{ valid: !v$.dob.$invalid, invalid: v$.dob.$invalid }"
                :value="convertDateTimeFromServer(v$.dob.$model)"
                @change="updateInstantField('dob', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.createdby')" for="customer-createdby"></label>
            <input
              type="text"
              class="form-control"
              name="createdby"
              id="customer-createdby"
              data-cy="createdby"
              :class="{ valid: !v$.createdby.$invalid, invalid: v$.createdby.$invalid }"
              v-model="v$.createdby.$model"
            />
            <div v-if="v$.createdby.$anyDirty && v$.createdby.$invalid">
              <small class="form-text text-danger" v-for="error of v$.createdby.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.emailaddress')"
              for="customer-emailaddress"
            ></label>
            <input
              type="text"
              class="form-control"
              name="emailaddress"
              id="customer-emailaddress"
              data-cy="emailaddress"
              :class="{ valid: !v$.emailaddress.$invalid, invalid: v$.emailaddress.$invalid }"
              v-model="v$.emailaddress.$model"
            />
            <div v-if="v$.emailaddress.$anyDirty && v$.emailaddress.$invalid">
              <small class="form-text text-danger" v-for="error of v$.emailaddress.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.identificationid')"
              for="customer-identificationid"
            ></label>
            <input
              type="text"
              class="form-control"
              name="identificationid"
              id="customer-identificationid"
              data-cy="identificationid"
              :class="{ valid: !v$.identificationid.$invalid, invalid: v$.identificationid.$invalid }"
              v-model="v$.identificationid.$model"
            />
            <div v-if="v$.identificationid.$anyDirty && v$.identificationid.$invalid">
              <small class="form-text text-danger" v-for="error of v$.identificationid.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.addaccount')" for="customer-addaccount"></label>
            <input
              type="number"
              class="form-control"
              name="addaccount"
              id="customer-addaccount"
              data-cy="addaccount"
              :class="{ valid: !v$.addaccount.$invalid, invalid: v$.addaccount.$invalid }"
              v-model.number="v$.addaccount.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.aclinkinginstitution')"
              for="customer-aclinkinginstitution"
            ></label>
            <input
              type="text"
              class="form-control"
              name="aclinkinginstitution"
              id="customer-aclinkinginstitution"
              data-cy="aclinkinginstitution"
              :class="{ valid: !v$.aclinkinginstitution.$invalid, invalid: v$.aclinkinginstitution.$invalid }"
              v-model="v$.aclinkinginstitution.$model"
            />
            <div v-if="v$.aclinkinginstitution.$anyDirty && v$.aclinkinginstitution.$invalid">
              <small class="form-text text-danger" v-for="error of v$.aclinkinginstitution.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.deactivated')"
              for="customer-deactivated"
            ></label>
            <input
              type="number"
              class="form-control"
              name="deactivated"
              id="customer-deactivated"
              data-cy="deactivated"
              :class="{ valid: !v$.deactivated.$invalid, invalid: v$.deactivated.$invalid }"
              v-model.number="v$.deactivated.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.deactivatedby')"
              for="customer-deactivatedby"
            ></label>
            <input
              type="text"
              class="form-control"
              name="deactivatedby"
              id="customer-deactivatedby"
              data-cy="deactivatedby"
              :class="{ valid: !v$.deactivatedby.$invalid, invalid: v$.deactivatedby.$invalid }"
              v-model="v$.deactivatedby.$model"
            />
            <div v-if="v$.deactivatedby.$anyDirty && v$.deactivatedby.$invalid">
              <small class="form-text text-danger" v-for="error of v$.deactivatedby.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.deactivatedon')"
              for="customer-deactivatedon"
            ></label>
            <div class="d-flex">
              <input
                id="customer-deactivatedon"
                data-cy="deactivatedon"
                type="datetime-local"
                class="form-control"
                name="deactivatedon"
                :class="{ valid: !v$.deactivatedon.$invalid, invalid: v$.deactivatedon.$invalid }"
                :value="convertDateTimeFromServer(v$.deactivatedon.$model)"
                @change="updateInstantField('deactivatedon', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.phonenochanged')"
              for="customer-phonenochanged"
            ></label>
            <input
              type="number"
              class="form-control"
              name="phonenochanged"
              id="customer-phonenochanged"
              data-cy="phonenochanged"
              :class="{ valid: !v$.phonenochanged.$invalid, invalid: v$.phonenochanged.$invalid }"
              v-model.number="v$.phonenochanged.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.phonenochangedby')"
              for="customer-phonenochangedby"
            ></label>
            <input
              type="text"
              class="form-control"
              name="phonenochangedby"
              id="customer-phonenochangedby"
              data-cy="phonenochangedby"
              :class="{ valid: !v$.phonenochangedby.$invalid, invalid: v$.phonenochangedby.$invalid }"
              v-model="v$.phonenochangedby.$model"
            />
            <div v-if="v$.phonenochangedby.$anyDirty && v$.phonenochangedby.$invalid">
              <small class="form-text text-danger" v-for="error of v$.phonenochangedby.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.phonenochangedon')"
              for="customer-phonenochangedon"
            ></label>
            <div class="d-flex">
              <input
                id="customer-phonenochangedon"
                data-cy="phonenochangedon"
                type="datetime-local"
                class="form-control"
                name="phonenochangedon"
                :class="{ valid: !v$.phonenochangedon.$invalid, invalid: v$.phonenochangedon.$invalid }"
                :value="convertDateTimeFromServer(v$.phonenochangedon.$model)"
                @change="updateInstantField('phonenochangedon', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.originalphoneno')"
              for="customer-originalphoneno"
            ></label>
            <input
              type="text"
              class="form-control"
              name="originalphoneno"
              id="customer-originalphoneno"
              data-cy="originalphoneno"
              :class="{ valid: !v$.originalphoneno.$invalid, invalid: v$.originalphoneno.$invalid }"
              v-model="v$.originalphoneno.$model"
            />
            <div v-if="v$.originalphoneno.$anyDirty && v$.originalphoneno.$invalid">
              <small class="form-text text-danger" v-for="error of v$.originalphoneno.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.newphoneno')" for="customer-newphoneno"></label>
            <input
              type="text"
              class="form-control"
              name="newphoneno"
              id="customer-newphoneno"
              data-cy="newphoneno"
              :class="{ valid: !v$.newphoneno.$invalid, invalid: v$.newphoneno.$invalid }"
              v-model="v$.newphoneno.$model"
            />
            <div v-if="v$.newphoneno.$anyDirty && v$.newphoneno.$invalid">
              <small class="form-text text-danger" v-for="error of v$.newphoneno.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.reset')" for="customer-reset"></label>
            <input
              type="number"
              class="form-control"
              name="reset"
              id="customer-reset"
              data-cy="reset"
              :class="{ valid: !v$.reset.$invalid, invalid: v$.reset.$invalid }"
              v-model.number="v$.reset.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.resetinginstitution')"
              for="customer-resetinginstitution"
            ></label>
            <input
              type="text"
              class="form-control"
              name="resetinginstitution"
              id="customer-resetinginstitution"
              data-cy="resetinginstitution"
              :class="{ valid: !v$.resetinginstitution.$invalid, invalid: v$.resetinginstitution.$invalid }"
              v-model="v$.resetinginstitution.$model"
            />
            <div v-if="v$.resetinginstitution.$anyDirty && v$.resetinginstitution.$invalid">
              <small class="form-text text-danger" v-for="error of v$.resetinginstitution.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.pinresetremark')"
              for="customer-pinresetremark"
            ></label>
            <input
              type="text"
              class="form-control"
              name="pinresetremark"
              id="customer-pinresetremark"
              data-cy="pinresetremark"
              :class="{ valid: !v$.pinresetremark.$invalid, invalid: v$.pinresetremark.$invalid }"
              v-model="v$.pinresetremark.$model"
            />
            <div v-if="v$.pinresetremark.$anyDirty && v$.pinresetremark.$invalid">
              <small class="form-text text-danger" v-for="error of v$.pinresetremark.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.resetby')" for="customer-resetby"></label>
            <input
              type="text"
              class="form-control"
              name="resetby"
              id="customer-resetby"
              data-cy="resetby"
              :class="{ valid: !v$.resetby.$invalid, invalid: v$.resetby.$invalid }"
              v-model="v$.resetby.$model"
            />
            <div v-if="v$.resetby.$anyDirty && v$.resetby.$invalid">
              <small class="form-text text-danger" v-for="error of v$.resetby.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.reseton')" for="customer-reseton"></label>
            <div class="d-flex">
              <input
                id="customer-reseton"
                data-cy="reseton"
                type="datetime-local"
                class="form-control"
                name="reseton"
                :class="{ valid: !v$.reseton.$invalid, invalid: v$.reseton.$invalid }"
                :value="convertDateTimeFromServer(v$.reseton.$model)"
                @change="updateInstantField('reseton', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.unblockinginstitution')"
              for="customer-unblockinginstitution"
            ></label>
            <input
              type="text"
              class="form-control"
              name="unblockinginstitution"
              id="customer-unblockinginstitution"
              data-cy="unblockinginstitution"
              :class="{ valid: !v$.unblockinginstitution.$invalid, invalid: v$.unblockinginstitution.$invalid }"
              v-model="v$.unblockinginstitution.$model"
            />
            <div v-if="v$.unblockinginstitution.$anyDirty && v$.unblockinginstitution.$invalid">
              <small class="form-text text-danger" v-for="error of v$.unblockinginstitution.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.pinblock')" for="customer-pinblock"></label>
            <input
              type="number"
              class="form-control"
              name="pinblock"
              id="customer-pinblock"
              data-cy="pinblock"
              :class="{ valid: !v$.pinblock.$invalid, invalid: v$.pinblock.$invalid }"
              v-model.number="v$.pinblock.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.pinblockby')" for="customer-pinblockby"></label>
            <input
              type="text"
              class="form-control"
              name="pinblockby"
              id="customer-pinblockby"
              data-cy="pinblockby"
              :class="{ valid: !v$.pinblockby.$invalid, invalid: v$.pinblockby.$invalid }"
              v-model="v$.pinblockby.$model"
            />
            <div v-if="v$.pinblockby.$anyDirty && v$.pinblockby.$invalid">
              <small class="form-text text-danger" v-for="error of v$.pinblockby.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.pinblockremarks')"
              for="customer-pinblockremarks"
            ></label>
            <input
              type="text"
              class="form-control"
              name="pinblockremarks"
              id="customer-pinblockremarks"
              data-cy="pinblockremarks"
              :class="{ valid: !v$.pinblockremarks.$invalid, invalid: v$.pinblockremarks.$invalid }"
              v-model="v$.pinblockremarks.$model"
            />
            <div v-if="v$.pinblockremarks.$anyDirty && v$.pinblockremarks.$invalid">
              <small class="form-text text-danger" v-for="error of v$.pinblockremarks.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.blockinginstitution')"
              for="customer-blockinginstitution"
            ></label>
            <input
              type="text"
              class="form-control"
              name="blockinginstitution"
              id="customer-blockinginstitution"
              data-cy="blockinginstitution"
              :class="{ valid: !v$.blockinginstitution.$invalid, invalid: v$.blockinginstitution.$invalid }"
              v-model="v$.blockinginstitution.$model"
            />
            <div v-if="v$.blockinginstitution.$anyDirty && v$.blockinginstitution.$invalid">
              <small class="form-text text-danger" v-for="error of v$.blockinginstitution.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.pinblockon')" for="customer-pinblockon"></label>
            <div class="d-flex">
              <input
                id="customer-pinblockon"
                data-cy="pinblockon"
                type="datetime-local"
                class="form-control"
                name="pinblockon"
                :class="{ valid: !v$.pinblockon.$invalid, invalid: v$.pinblockon.$invalid }"
                :value="convertDateTimeFromServer(v$.pinblockon.$model)"
                @change="updateInstantField('pinblockon', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.approvedon')" for="customer-approvedon"></label>
            <div class="d-flex">
              <input
                id="customer-approvedon"
                data-cy="approvedon"
                type="datetime-local"
                class="form-control"
                name="approvedon"
                :class="{ valid: !v$.approvedon.$invalid, invalid: v$.approvedon.$invalid }"
                :value="convertDateTimeFromServer(v$.approvedon.$model)"
                @change="updateInstantField('approvedon', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.pinunblockby')"
              for="customer-pinunblockby"
            ></label>
            <input
              type="text"
              class="form-control"
              name="pinunblockby"
              id="customer-pinunblockby"
              data-cy="pinunblockby"
              :class="{ valid: !v$.pinunblockby.$invalid, invalid: v$.pinunblockby.$invalid }"
              v-model="v$.pinunblockby.$model"
            />
            <div v-if="v$.pinunblockby.$anyDirty && v$.pinunblockby.$invalid">
              <small class="form-text text-danger" v-for="error of v$.pinunblockby.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.loggedin')" for="customer-loggedin"></label>
            <input
              type="number"
              class="form-control"
              name="loggedin"
              id="customer-loggedin"
              data-cy="loggedin"
              :class="{ valid: !v$.loggedin.$invalid, invalid: v$.loggedin.$invalid }"
              v-model.number="v$.loggedin.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.trials')" for="customer-trials"></label>
            <input
              type="text"
              class="form-control"
              name="trials"
              id="customer-trials"
              data-cy="trials"
              :class="{ valid: !v$.trials.$invalid, invalid: v$.trials.$invalid }"
              v-model="v$.trials.$model"
            />
            <div v-if="v$.trials.$anyDirty && v$.trials.$invalid">
              <small class="form-text text-danger" v-for="error of v$.trials.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.idtype')" for="customer-idtype"></label>
            <input
              type="text"
              class="form-control"
              name="idtype"
              id="customer-idtype"
              data-cy="idtype"
              :class="{ valid: !v$.idtype.$invalid, invalid: v$.idtype.$invalid }"
              v-model="v$.idtype.$model"
            />
            <div v-if="v$.idtype.$anyDirty && v$.idtype.$invalid">
              <small class="form-text text-danger" v-for="error of v$.idtype.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.idnumber')" for="customer-idnumber"></label>
            <input
              type="text"
              class="form-control"
              name="idnumber"
              id="customer-idnumber"
              data-cy="idnumber"
              :class="{ valid: !v$.idnumber.$invalid, invalid: v$.idnumber.$invalid }"
              v-model="v$.idnumber.$model"
            />
            <div v-if="v$.idnumber.$anyDirty && v$.idnumber.$invalid">
              <small class="form-text text-danger" v-for="error of v$.idnumber.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.gender')" for="customer-gender"></label>
            <input
              type="text"
              class="form-control"
              name="gender"
              id="customer-gender"
              data-cy="gender"
              :class="{ valid: !v$.gender.$invalid, invalid: v$.gender.$invalid }"
              v-model="v$.gender.$model"
            />
            <div v-if="v$.gender.$anyDirty && v$.gender.$invalid">
              <small class="form-text text-danger" v-for="error of v$.gender.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.cif')" for="customer-cif"></label>
            <input
              type="text"
              class="form-control"
              name="cif"
              id="customer-cif"
              data-cy="cif"
              :class="{ valid: !v$.cif.$invalid, invalid: v$.cif.$invalid }"
              v-model="v$.cif.$model"
            />
            <div v-if="v$.cif.$anyDirty && v$.cif.$invalid">
              <small class="form-text text-danger" v-for="error of v$.cif.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.dateofbirth')"
              for="customer-dateofbirth"
            ></label>
            <div class="d-flex">
              <input
                id="customer-dateofbirth"
                data-cy="dateofbirth"
                type="datetime-local"
                class="form-control"
                name="dateofbirth"
                :class="{ valid: !v$.dateofbirth.$invalid, invalid: v$.dateofbirth.$invalid }"
                :value="convertDateTimeFromServer(v$.dateofbirth.$model)"
                @change="updateInstantField('dateofbirth', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.remarks')" for="customer-remarks"></label>
            <input
              type="text"
              class="form-control"
              name="remarks"
              id="customer-remarks"
              data-cy="remarks"
              :class="{ valid: !v$.remarks.$invalid, invalid: v$.remarks.$invalid }"
              v-model="v$.remarks.$model"
            />
            <div v-if="v$.remarks.$anyDirty && v$.remarks.$invalid">
              <small class="form-text text-danger" v-for="error of v$.remarks.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.resetimsi')" for="customer-resetimsi"></label>
            <input
              type="number"
              class="form-control"
              name="resetimsi"
              id="customer-resetimsi"
              data-cy="resetimsi"
              :class="{ valid: !v$.resetimsi.$invalid, invalid: v$.resetimsi.$invalid }"
              v-model.number="v$.resetimsi.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.imsiresetby')"
              for="customer-imsiresetby"
            ></label>
            <input
              type="text"
              class="form-control"
              name="imsiresetby"
              id="customer-imsiresetby"
              data-cy="imsiresetby"
              :class="{ valid: !v$.imsiresetby.$invalid, invalid: v$.imsiresetby.$invalid }"
              v-model="v$.imsiresetby.$model"
            />
            <div v-if="v$.imsiresetby.$anyDirty && v$.imsiresetby.$invalid">
              <small class="form-text text-danger" v-for="error of v$.imsiresetby.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.firstname')" for="customer-firstname"></label>
            <input
              type="text"
              class="form-control"
              name="firstname"
              id="customer-firstname"
              data-cy="firstname"
              :class="{ valid: !v$.firstname.$invalid, invalid: v$.firstname.$invalid }"
              v-model="v$.firstname.$model"
            />
            <div v-if="v$.firstname.$anyDirty && v$.firstname.$invalid">
              <small class="form-text text-danger" v-for="error of v$.firstname.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.secondname')" for="customer-secondname"></label>
            <input
              type="text"
              class="form-control"
              name="secondname"
              id="customer-secondname"
              data-cy="secondname"
              :class="{ valid: !v$.secondname.$invalid, invalid: v$.secondname.$invalid }"
              v-model="v$.secondname.$model"
            />
            <div v-if="v$.secondname.$anyDirty && v$.secondname.$invalid">
              <small class="form-text text-danger" v-for="error of v$.secondname.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.lastname')" for="customer-lastname"></label>
            <input
              type="text"
              class="form-control"
              name="lastname"
              id="customer-lastname"
              data-cy="lastname"
              :class="{ valid: !v$.lastname.$invalid, invalid: v$.lastname.$invalid }"
              v-model="v$.lastname.$model"
            />
            <div v-if="v$.lastname.$anyDirty && v$.lastname.$invalid">
              <small class="form-text text-danger" v-for="error of v$.lastname.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.pinblocktime')"
              for="customer-pinblocktime"
            ></label>
            <input
              type="text"
              class="form-control"
              name="pinblocktime"
              id="customer-pinblocktime"
              data-cy="pinblocktime"
              :class="{ valid: !v$.pinblocktime.$invalid, invalid: v$.pinblocktime.$invalid }"
              v-model="v$.pinblocktime.$model"
            />
            <div v-if="v$.pinblocktime.$anyDirty && v$.pinblocktime.$invalid">
              <small class="form-text text-danger" v-for="error of v$.pinblocktime.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.customerstatus')"
              for="customer-customerstatus"
            ></label>
            <input
              type="text"
              class="form-control"
              name="customerstatus"
              id="customer-customerstatus"
              data-cy="customerstatus"
              :class="{ valid: !v$.customerstatus.$invalid, invalid: v$.customerstatus.$invalid }"
              v-model="v$.customerstatus.$model"
            />
            <div v-if="v$.customerstatus.$anyDirty && v$.customerstatus.$invalid">
              <small class="form-text text-danger" v-for="error of v$.customerstatus.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.username')" for="customer-username"></label>
            <input
              type="text"
              class="form-control"
              name="username"
              id="customer-username"
              data-cy="username"
              :class="{ valid: !v$.username.$invalid, invalid: v$.username.$invalid }"
              v-model="v$.username.$model"
            />
            <div v-if="v$.username.$anyDirty && v$.username.$invalid">
              <small class="form-text text-danger" v-for="error of v$.username.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.password')" for="customer-password"></label>
            <input
              type="text"
              class="form-control"
              name="password"
              id="customer-password"
              data-cy="password"
              :class="{ valid: !v$.password.$invalid, invalid: v$.password.$invalid }"
              v-model="v$.password.$model"
            />
            <div v-if="v$.password.$anyDirty && v$.password.$invalid">
              <small class="form-text text-danger" v-for="error of v$.password.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.deviceid')" for="customer-deviceid"></label>
            <input
              type="text"
              class="form-control"
              name="deviceid"
              id="customer-deviceid"
              data-cy="deviceid"
              :class="{ valid: !v$.deviceid.$invalid, invalid: v$.deviceid.$invalid }"
              v-model="v$.deviceid.$model"
            />
            <div v-if="v$.deviceid.$anyDirty && v$.deviceid.$invalid">
              <small class="form-text text-danger" v-for="error of v$.deviceid.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.channel')" for="customer-channel"></label>
            <input
              type="text"
              class="form-control"
              name="channel"
              id="customer-channel"
              data-cy="channel"
              :class="{ valid: !v$.channel.$invalid, invalid: v$.channel.$invalid }"
              v-model="v$.channel.$model"
            />
            <div v-if="v$.channel.$anyDirty && v$.channel.$invalid">
              <small class="form-text text-danger" v-for="error of v$.channel.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.passreset')" for="customer-passreset"></label>
            <input
              type="number"
              class="form-control"
              name="passreset"
              id="customer-passreset"
              data-cy="passreset"
              :class="{ valid: !v$.passreset.$invalid, invalid: v$.passreset.$invalid }"
              v-model.number="v$.passreset.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.passresetby')"
              for="customer-passresetby"
            ></label>
            <input
              type="text"
              class="form-control"
              name="passresetby"
              id="customer-passresetby"
              data-cy="passresetby"
              :class="{ valid: !v$.passresetby.$invalid, invalid: v$.passresetby.$invalid }"
              v-model="v$.passresetby.$model"
            />
            <div v-if="v$.passresetby.$anyDirty && v$.passresetby.$invalid">
              <small class="form-text text-danger" v-for="error of v$.passresetby.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.passreseton')"
              for="customer-passreseton"
            ></label>
            <div class="d-flex">
              <input
                id="customer-passreseton"
                data-cy="passreseton"
                type="datetime-local"
                class="form-control"
                name="passreseton"
                :class="{ valid: !v$.passreseton.$invalid, invalid: v$.passreseton.$invalid }"
                :value="convertDateTimeFromServer(v$.passreseton.$model)"
                @change="updateInstantField('passreseton', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.passblock')" for="customer-passblock"></label>
            <input
              type="number"
              class="form-control"
              name="passblock"
              id="customer-passblock"
              data-cy="passblock"
              :class="{ valid: !v$.passblock.$invalid, invalid: v$.passblock.$invalid }"
              v-model.number="v$.passblock.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.passblockby')"
              for="customer-passblockby"
            ></label>
            <input
              type="text"
              class="form-control"
              name="passblockby"
              id="customer-passblockby"
              data-cy="passblockby"
              :class="{ valid: !v$.passblockby.$invalid, invalid: v$.passblockby.$invalid }"
              v-model="v$.passblockby.$model"
            />
            <div v-if="v$.passblockby.$anyDirty && v$.passblockby.$invalid">
              <small class="form-text text-danger" v-for="error of v$.passblockby.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.passblockon')"
              for="customer-passblockon"
            ></label>
            <div class="d-flex">
              <input
                id="customer-passblockon"
                data-cy="passblockon"
                type="datetime-local"
                class="form-control"
                name="passblockon"
                :class="{ valid: !v$.passblockon.$invalid, invalid: v$.passblockon.$invalid }"
                :value="convertDateTimeFromServer(v$.passblockon.$model)"
                @change="updateInstantField('passblockon', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.pinmarkblock')"
              for="customer-pinmarkblock"
            ></label>
            <input
              type="number"
              class="form-control"
              name="pinmarkblock"
              id="customer-pinmarkblock"
              data-cy="pinmarkblock"
              :class="{ valid: !v$.pinmarkblock.$invalid, invalid: v$.pinmarkblock.$invalid }"
              v-model.number="v$.pinmarkblock.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.passmarkblock')"
              for="customer-passmarkblock"
            ></label>
            <input
              type="number"
              class="form-control"
              name="passmarkblock"
              id="customer-passmarkblock"
              data-cy="passmarkblock"
              :class="{ valid: !v$.passmarkblock.$invalid, invalid: v$.passmarkblock.$invalid }"
              v-model.number="v$.passmarkblock.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.passresetremarks')"
              for="customer-passresetremarks"
            ></label>
            <input
              type="text"
              class="form-control"
              name="passresetremarks"
              id="customer-passresetremarks"
              data-cy="passresetremarks"
              :class="{ valid: !v$.passresetremarks.$invalid, invalid: v$.passresetremarks.$invalid }"
              v-model="v$.passresetremarks.$model"
            />
            <div v-if="v$.passresetremarks.$anyDirty && v$.passresetremarks.$invalid">
              <small class="form-text text-danger" v-for="error of v$.passresetremarks.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.passblockremarks')"
              for="customer-passblockremarks"
            ></label>
            <input
              type="text"
              class="form-control"
              name="passblockremarks"
              id="customer-passblockremarks"
              data-cy="passblockremarks"
              :class="{ valid: !v$.passblockremarks.$invalid, invalid: v$.passblockremarks.$invalid }"
              v-model="v$.passblockremarks.$model"
            />
            <div v-if="v$.passblockremarks.$anyDirty && v$.passblockremarks.$invalid">
              <small class="form-text text-danger" v-for="error of v$.passblockremarks.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.passunblockby')"
              for="customer-passunblockby"
            ></label>
            <input
              type="text"
              class="form-control"
              name="passunblockby"
              id="customer-passunblockby"
              data-cy="passunblockby"
              :class="{ valid: !v$.passunblockby.$invalid, invalid: v$.passunblockby.$invalid }"
              v-model="v$.passunblockby.$model"
            />
            <div v-if="v$.passunblockby.$anyDirty && v$.passunblockby.$invalid">
              <small class="form-text text-danger" v-for="error of v$.passunblockby.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.passtrials')" for="customer-passtrials"></label>
            <input
              type="number"
              class="form-control"
              name="passtrials"
              id="customer-passtrials"
              data-cy="passtrials"
              :class="{ valid: !v$.passtrials.$invalid, invalid: v$.passtrials.$invalid }"
              v-model.number="v$.passtrials.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.appactive')" for="customer-appactive"></label>
            <input
              type="number"
              class="form-control"
              name="appactive"
              id="customer-appactive"
              data-cy="appactive"
              :class="{ valid: !v$.appactive.$invalid, invalid: v$.appactive.$invalid }"
              v-model.number="v$.appactive.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.lastlogin')" for="customer-lastlogin"></label>
            <input
              type="text"
              class="form-control"
              name="lastlogin"
              id="customer-lastlogin"
              data-cy="lastlogin"
              :class="{ valid: !v$.lastlogin.$invalid, invalid: v$.lastlogin.$invalid }"
              v-model="v$.lastlogin.$model"
            />
            <div v-if="v$.lastlogin.$anyDirty && v$.lastlogin.$invalid">
              <small class="form-text text-danger" v-for="error of v$.lastlogin.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.appmarkeddisable')"
              for="customer-appmarkeddisable"
            ></label>
            <input
              type="number"
              class="form-control"
              name="appmarkeddisable"
              id="customer-appmarkeddisable"
              data-cy="appmarkeddisable"
              :class="{ valid: !v$.appmarkeddisable.$invalid, invalid: v$.appmarkeddisable.$invalid }"
              v-model.number="v$.appmarkeddisable.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.disableby')" for="customer-disableby"></label>
            <input
              type="text"
              class="form-control"
              name="disableby"
              id="customer-disableby"
              data-cy="disableby"
              :class="{ valid: !v$.disableby.$invalid, invalid: v$.disableby.$invalid }"
              v-model="v$.disableby.$model"
            />
            <div v-if="v$.disableby.$anyDirty && v$.disableby.$invalid">
              <small class="form-text text-danger" v-for="error of v$.disableby.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.approvedisableby')"
              for="customer-approvedisableby"
            ></label>
            <input
              type="text"
              class="form-control"
              name="approvedisableby"
              id="customer-approvedisableby"
              data-cy="approvedisableby"
              :class="{ valid: !v$.approvedisableby.$invalid, invalid: v$.approvedisableby.$invalid }"
              v-model="v$.approvedisableby.$model"
            />
            <div v-if="v$.approvedisableby.$anyDirty && v$.approvedisableby.$invalid">
              <small class="form-text text-danger" v-for="error of v$.approvedisableby.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.appmarkedenable')"
              for="customer-appmarkedenable"
            ></label>
            <input
              type="number"
              class="form-control"
              name="appmarkedenable"
              id="customer-appmarkedenable"
              data-cy="appmarkedenable"
              :class="{ valid: !v$.appmarkedenable.$invalid, invalid: v$.appmarkedenable.$invalid }"
              v-model.number="v$.appmarkedenable.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.enableby')" for="customer-enableby"></label>
            <input
              type="text"
              class="form-control"
              name="enableby"
              id="customer-enableby"
              data-cy="enableby"
              :class="{ valid: !v$.enableby.$invalid, invalid: v$.enableby.$invalid }"
              v-model="v$.enableby.$model"
            />
            <div v-if="v$.enableby.$anyDirty && v$.enableby.$invalid">
              <small class="form-text text-danger" v-for="error of v$.enableby.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.approvedenableby')"
              for="customer-approvedenableby"
            ></label>
            <input
              type="text"
              class="form-control"
              name="approvedenableby"
              id="customer-approvedenableby"
              data-cy="approvedenableby"
              :class="{ valid: !v$.approvedenableby.$invalid, invalid: v$.approvedenableby.$invalid }"
              v-model="v$.approvedenableby.$model"
            />
            <div v-if="v$.approvedenableby.$anyDirty && v$.approvedenableby.$invalid">
              <small class="form-text text-danger" v-for="error of v$.approvedenableby.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.markeddeactivate')"
              for="customer-markeddeactivate"
            ></label>
            <input
              type="number"
              class="form-control"
              name="markeddeactivate"
              id="customer-markeddeactivate"
              data-cy="markeddeactivate"
              :class="{ valid: !v$.markeddeactivate.$invalid, invalid: v$.markeddeactivate.$invalid }"
              v-model.number="v$.markeddeactivate.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.appfirstlogin')"
              for="customer-appfirstlogin"
            ></label>
            <input
              type="text"
              class="form-control"
              name="appfirstlogin"
              id="customer-appfirstlogin"
              data-cy="appfirstlogin"
              :class="{ valid: !v$.appfirstlogin.$invalid, invalid: v$.appfirstlogin.$invalid }"
              v-model="v$.appfirstlogin.$model"
            />
            <div v-if="v$.appfirstlogin.$anyDirty && v$.appfirstlogin.$invalid">
              <small class="form-text text-danger" v-for="error of v$.appfirstlogin.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.atmtrials')" for="customer-atmtrials"></label>
            <input
              type="number"
              class="form-control"
              name="atmtrials"
              id="customer-atmtrials"
              data-cy="atmtrials"
              :class="{ valid: !v$.atmtrials.$invalid, invalid: v$.atmtrials.$invalid }"
              v-model.number="v$.atmtrials.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.shorcuts')" for="customer-shorcuts"></label>
            <input
              type="text"
              class="form-control"
              name="shorcuts"
              id="customer-shorcuts"
              data-cy="shorcuts"
              :class="{ valid: !v$.shorcuts.$invalid, invalid: v$.shorcuts.$invalid }"
              v-model="v$.shorcuts.$model"
            />
            <div v-if="v$.shorcuts.$anyDirty && v$.shorcuts.$invalid">
              <small class="form-text text-danger" v-for="error of v$.shorcuts.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.markedactivate')"
              for="customer-markedactivate"
            ></label>
            <input
              type="text"
              class="form-control"
              name="markedactivate"
              id="customer-markedactivate"
              data-cy="markedactivate"
              :class="{ valid: !v$.markedactivate.$invalid, invalid: v$.markedactivate.$invalid }"
              v-model="v$.markedactivate.$model"
            />
            <div v-if="v$.markedactivate.$anyDirty && v$.markedactivate.$invalid">
              <small class="form-text text-danger" v-for="error of v$.markedactivate.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.town')" for="customer-town"></label>
            <input
              type="text"
              class="form-control"
              name="town"
              id="customer-town"
              data-cy="town"
              :class="{ valid: !v$.town.$invalid, invalid: v$.town.$invalid }"
              v-model="v$.town.$model"
            />
            <div v-if="v$.town.$anyDirty && v$.town.$invalid">
              <small class="form-text text-danger" v-for="error of v$.town.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.approveddisableon')"
              for="customer-approveddisableon"
            ></label>
            <div class="d-flex">
              <input
                id="customer-approveddisableon"
                data-cy="approveddisableon"
                type="datetime-local"
                class="form-control"
                name="approveddisableon"
                :class="{ valid: !v$.approveddisableon.$invalid, invalid: v$.approveddisableon.$invalid }"
                :value="convertDateTimeFromServer(v$.approveddisableon.$model)"
                @change="updateInstantField('approveddisableon', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.disabledon')" for="customer-disabledon"></label>
            <div class="d-flex">
              <input
                id="customer-disabledon"
                data-cy="disabledon"
                type="datetime-local"
                class="form-control"
                name="disabledon"
                :class="{ valid: !v$.disabledon.$invalid, invalid: v$.disabledon.$invalid }"
                :value="convertDateTimeFromServer(v$.disabledon.$model)"
                @change="updateInstantField('disabledon', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.resetapproveon')"
              for="customer-resetapproveon"
            ></label>
            <div class="d-flex">
              <input
                id="customer-resetapproveon"
                data-cy="resetapproveon"
                type="datetime-local"
                class="form-control"
                name="resetapproveon"
                :class="{ valid: !v$.resetapproveon.$invalid, invalid: v$.resetapproveon.$invalid }"
                :value="convertDateTimeFromServer(v$.resetapproveon.$model)"
                @change="updateInstantField('resetapproveon', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.deletedby')" for="customer-deletedby"></label>
            <input
              type="text"
              class="form-control"
              name="deletedby"
              id="customer-deletedby"
              data-cy="deletedby"
              :class="{ valid: !v$.deletedby.$invalid, invalid: v$.deletedby.$invalid }"
              v-model="v$.deletedby.$model"
            />
            <div v-if="v$.deletedby.$anyDirty && v$.deletedby.$invalid">
              <small class="form-text text-danger" v-for="error of v$.deletedby.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.questionsasked')"
              for="customer-questionsasked"
            ></label>
            <input
              type="text"
              class="form-control"
              name="questionsasked"
              id="customer-questionsasked"
              data-cy="questionsasked"
              :class="{ valid: !v$.questionsasked.$invalid, invalid: v$.questionsasked.$invalid }"
              v-model="v$.questionsasked.$model"
            />
            <div v-if="v$.questionsasked.$anyDirty && v$.questionsasked.$invalid">
              <small class="form-text text-danger" v-for="error of v$.questionsasked.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.questionstrials')"
              for="customer-questionstrials"
            ></label>
            <input
              type="text"
              class="form-control"
              name="questionstrials"
              id="customer-questionstrials"
              data-cy="questionstrials"
              :class="{ valid: !v$.questionstrials.$invalid, invalid: v$.questionstrials.$invalid }"
              v-model="v$.questionstrials.$model"
            />
            <div v-if="v$.questionstrials.$anyDirty && v$.questionstrials.$invalid">
              <small class="form-text text-danger" v-for="error of v$.questionstrials.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.questionsanswered')"
              for="customer-questionsanswered"
            ></label>
            <input
              type="text"
              class="form-control"
              name="questionsanswered"
              id="customer-questionsanswered"
              data-cy="questionsanswered"
              :class="{ valid: !v$.questionsanswered.$invalid, invalid: v$.questionsanswered.$invalid }"
              v-model="v$.questionsanswered.$model"
            />
            <div v-if="v$.questionsanswered.$anyDirty && v$.questionsanswered.$invalid">
              <small class="form-text text-danger" v-for="error of v$.questionsanswered.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.validotp')" for="customer-validotp"></label>
            <input
              type="number"
              class="form-control"
              name="validotp"
              id="customer-validotp"
              data-cy="validotp"
              :class="{ valid: !v$.validotp.$invalid, invalid: v$.validotp.$invalid }"
              v-model.number="v$.validotp.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.activatedby')"
              for="customer-activatedby"
            ></label>
            <input
              type="text"
              class="form-control"
              name="activatedby"
              id="customer-activatedby"
              data-cy="activatedby"
              :class="{ valid: !v$.activatedby.$invalid, invalid: v$.activatedby.$invalid }"
              v-model="v$.activatedby.$model"
            />
            <div v-if="v$.activatedby.$anyDirty && v$.activatedby.$invalid">
              <small class="form-text text-danger" v-for="error of v$.activatedby.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.customer.activatedon')"
              for="customer-activatedon"
            ></label>
            <div class="d-flex">
              <input
                id="customer-activatedon"
                data-cy="activatedon"
                type="datetime-local"
                class="form-control"
                name="activatedon"
                :class="{ valid: !v$.activatedon.$invalid, invalid: v$.activatedon.$invalid }"
                :value="convertDateTimeFromServer(v$.activatedon.$model)"
                @change="updateInstantField('activatedon', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.customer.branchcode')" for="customer-branchcode"></label>
            <input
              type="text"
              class="form-control"
              name="branchcode"
              id="customer-branchcode"
              data-cy="branchcode"
              :class="{ valid: !v$.branchcode.$invalid, invalid: v$.branchcode.$invalid }"
              v-model="v$.branchcode.$model"
            />
            <div v-if="v$.branchcode.$anyDirty && v$.branchcode.$invalid">
              <small class="form-text text-danger" v-for="error of v$.branchcode.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" data-cy="entityCreateCancelButton" class="btn btn-secondary" @click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.cancel')"></span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="v$.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.save')"></span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./customer-update.component.ts"></script>
