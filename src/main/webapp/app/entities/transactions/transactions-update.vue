<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate @submit.prevent="save()">
        <h2
          id="bbMobileBankingAdminApp.transactions.home.createOrEditLabel"
          data-cy="TransactionsCreateUpdateHeading"
          v-text="t$('bbMobileBankingAdminApp.transactions.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="transactions.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="transactions.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.processed')"
              for="transactions-processed"
            ></label>
            <input
              type="number"
              class="form-control"
              name="processed"
              id="transactions-processed"
              data-cy="processed"
              :class="{ valid: !v$.processed.$invalid, invalid: v$.processed.$invalid }"
              v-model.number="v$.processed.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.incomingbitmap')"
              for="transactions-incomingbitmap"
            ></label>
            <input
              type="text"
              class="form-control"
              name="incomingbitmap"
              id="transactions-incomingbitmap"
              data-cy="incomingbitmap"
              :class="{ valid: !v$.incomingbitmap.$invalid, invalid: v$.incomingbitmap.$invalid }"
              v-model="v$.incomingbitmap.$model"
            />
            <div v-if="v$.incomingbitmap.$anyDirty && v$.incomingbitmap.$invalid">
              <small class="form-text text-danger" v-for="error of v$.incomingbitmap.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.outgoingbitmap')"
              for="transactions-outgoingbitmap"
            ></label>
            <input
              type="text"
              class="form-control"
              name="outgoingbitmap"
              id="transactions-outgoingbitmap"
              data-cy="outgoingbitmap"
              :class="{ valid: !v$.outgoingbitmap.$invalid, invalid: v$.outgoingbitmap.$invalid }"
              v-model="v$.outgoingbitmap.$model"
            />
            <div v-if="v$.outgoingbitmap.$anyDirty && v$.outgoingbitmap.$invalid">
              <small class="form-text text-danger" v-for="error of v$.outgoingbitmap.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.inmessage')"
              for="transactions-inmessage"
            ></label>
            <input
              type="text"
              class="form-control"
              name="inmessage"
              id="transactions-inmessage"
              data-cy="inmessage"
              :class="{ valid: !v$.inmessage.$invalid, invalid: v$.inmessage.$invalid }"
              v-model="v$.inmessage.$model"
            />
            <div v-if="v$.inmessage.$anyDirty && v$.inmessage.$invalid">
              <small class="form-text text-danger" v-for="error of v$.inmessage.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.messagetocbs')"
              for="transactions-messagetocbs"
            ></label>
            <input
              type="text"
              class="form-control"
              name="messagetocbs"
              id="transactions-messagetocbs"
              data-cy="messagetocbs"
              :class="{ valid: !v$.messagetocbs.$invalid, invalid: v$.messagetocbs.$invalid }"
              v-model="v$.messagetocbs.$model"
            />
            <div v-if="v$.messagetocbs.$anyDirty && v$.messagetocbs.$invalid">
              <small class="form-text text-danger" v-for="error of v$.messagetocbs.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.messagefromcbs')"
              for="transactions-messagefromcbs"
            ></label>
            <input
              type="text"
              class="form-control"
              name="messagefromcbs"
              id="transactions-messagefromcbs"
              data-cy="messagefromcbs"
              :class="{ valid: !v$.messagefromcbs.$invalid, invalid: v$.messagefromcbs.$invalid }"
              v-model="v$.messagefromcbs.$model"
            />
            <div v-if="v$.messagefromcbs.$anyDirty && v$.messagefromcbs.$invalid">
              <small class="form-text text-danger" v-for="error of v$.messagefromcbs.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.cbsprocess')"
              for="transactions-cbsprocess"
            ></label>
            <input
              type="number"
              class="form-control"
              name="cbsprocess"
              id="transactions-cbsprocess"
              data-cy="cbsprocess"
              :class="{ valid: !v$.cbsprocess.$invalid, invalid: v$.cbsprocess.$invalid }"
              v-model.number="v$.cbsprocess.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.cbsonline')"
              for="transactions-cbsonline"
            ></label>
            <input
              type="number"
              class="form-control"
              name="cbsonline"
              id="transactions-cbsonline"
              data-cy="cbsonline"
              :class="{ valid: !v$.cbsonline.$invalid, invalid: v$.cbsonline.$invalid }"
              v-model.number="v$.cbsonline.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.cbsresponse')"
              for="transactions-cbsresponse"
            ></label>
            <input
              type="text"
              class="form-control"
              name="cbsresponse"
              id="transactions-cbsresponse"
              data-cy="cbsresponse"
              :class="{ valid: !v$.cbsresponse.$invalid, invalid: v$.cbsresponse.$invalid }"
              v-model="v$.cbsresponse.$model"
            />
            <div v-if="v$.cbsresponse.$anyDirty && v$.cbsresponse.$invalid">
              <small class="form-text text-danger" v-for="error of v$.cbsresponse.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.responsemessage')"
              for="transactions-responsemessage"
            ></label>
            <input
              type="text"
              class="form-control"
              name="responsemessage"
              id="transactions-responsemessage"
              data-cy="responsemessage"
              :class="{ valid: !v$.responsemessage.$invalid, invalid: v$.responsemessage.$invalid }"
              v-model="v$.responsemessage.$model"
            />
            <div v-if="v$.responsemessage.$anyDirty && v$.responsemessage.$invalid">
              <small class="form-text text-danger" v-for="error of v$.responsemessage.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.responsesent')"
              for="transactions-responsesent"
            ></label>
            <input
              type="number"
              class="form-control"
              name="responsesent"
              id="transactions-responsesent"
              data-cy="responsesent"
              :class="{ valid: !v$.responsesent.$invalid, invalid: v$.responsesent.$invalid }"
              v-model.number="v$.responsesent.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.channel')"
              for="transactions-channel"
            ></label>
            <input
              type="text"
              class="form-control"
              name="channel"
              id="transactions-channel"
              data-cy="channel"
              :class="{ valid: !v$.channel.$invalid, invalid: v$.channel.$invalid }"
              v-model="v$.channel.$model"
            />
            <div v-if="v$.channel.$anyDirty && v$.channel.$invalid">
              <small class="form-text text-danger" v-for="error of v$.channel.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.originaldata')"
              for="transactions-originaldata"
            ></label>
            <input
              type="text"
              class="form-control"
              name="originaldata"
              id="transactions-originaldata"
              data-cy="originaldata"
              :class="{ valid: !v$.originaldata.$invalid, invalid: v$.originaldata.$invalid }"
              v-model="v$.originaldata.$model"
            />
            <div v-if="v$.originaldata.$anyDirty && v$.originaldata.$invalid">
              <small class="form-text text-danger" v-for="error of v$.originaldata.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.field39resp')"
              for="transactions-field39resp"
            ></label>
            <input
              type="text"
              class="form-control"
              name="field39resp"
              id="transactions-field39resp"
              data-cy="field39resp"
              :class="{ valid: !v$.field39resp.$invalid, invalid: v$.field39resp.$invalid }"
              v-model="v$.field39resp.$model"
            />
            <div v-if="v$.field39resp.$anyDirty && v$.field39resp.$invalid">
              <small class="form-text text-danger" v-for="error of v$.field39resp.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.narration')"
              for="transactions-narration"
            ></label>
            <input
              type="text"
              class="form-control"
              name="narration"
              id="transactions-narration"
              data-cy="narration"
              :class="{ valid: !v$.narration.$invalid, invalid: v$.narration.$invalid }"
              v-model="v$.narration.$model"
            />
            <div v-if="v$.narration.$anyDirty && v$.narration.$invalid">
              <small class="form-text text-danger" v-for="error of v$.narration.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.authorised')"
              for="transactions-authorised"
            ></label>
            <input
              type="number"
              class="form-control"
              name="authorised"
              id="transactions-authorised"
              data-cy="authorised"
              :class="{ valid: !v$.authorised.$invalid, invalid: v$.authorised.$invalid }"
              v-model.number="v$.authorised.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.branchcode')"
              for="transactions-branchcode"
            ></label>
            <input
              type="text"
              class="form-control"
              name="branchcode"
              id="transactions-branchcode"
              data-cy="branchcode"
              :class="{ valid: !v$.branchcode.$invalid, invalid: v$.branchcode.$invalid }"
              v-model="v$.branchcode.$model"
            />
            <div v-if="v$.branchcode.$anyDirty && v$.branchcode.$invalid">
              <small class="form-text text-danger" v-for="error of v$.branchcode.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.field39original')"
              for="transactions-field39original"
            ></label>
            <input
              type="text"
              class="form-control"
              name="field39original"
              id="transactions-field39original"
              data-cy="field39original"
              :class="{ valid: !v$.field39original.$invalid, invalid: v$.field39original.$invalid }"
              v-model="v$.field39original.$model"
            />
            <div v-if="v$.field39original.$anyDirty && v$.field39original.$invalid">
              <small class="form-text text-danger" v-for="error of v$.field39original.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.messageclass')"
              for="transactions-messageclass"
            ></label>
            <input
              type="text"
              class="form-control"
              name="messageclass"
              id="transactions-messageclass"
              data-cy="messageclass"
              :class="{ valid: !v$.messageclass.$invalid, invalid: v$.messageclass.$invalid }"
              v-model="v$.messageclass.$model"
            />
            <div v-if="v$.messageclass.$anyDirty && v$.messageclass.$invalid">
              <small class="form-text text-danger" v-for="error of v$.messageclass.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.txncode')"
              for="transactions-txncode"
            ></label>
            <input
              type="text"
              class="form-control"
              name="txncode"
              id="transactions-txncode"
              data-cy="txncode"
              :class="{ valid: !v$.txncode.$invalid, invalid: v$.txncode.$invalid }"
              v-model="v$.txncode.$model"
            />
            <div v-if="v$.txncode.$anyDirty && v$.txncode.$invalid">
              <small class="form-text text-danger" v-for="error of v$.txncode.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.currcode')"
              for="transactions-currcode"
            ></label>
            <input
              type="text"
              class="form-control"
              name="currcode"
              id="transactions-currcode"
              data-cy="currcode"
              :class="{ valid: !v$.currcode.$invalid, invalid: v$.currcode.$invalid }"
              v-model="v$.currcode.$model"
            />
            <div v-if="v$.currcode.$anyDirty && v$.currcode.$invalid">
              <small class="form-text text-danger" v-for="error of v$.currcode.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.transactions.device')" for="transactions-device"></label>
            <input
              type="text"
              class="form-control"
              name="device"
              id="transactions-device"
              data-cy="device"
              :class="{ valid: !v$.device.$invalid, invalid: v$.device.$invalid }"
              v-model="v$.device.$model"
            />
            <div v-if="v$.device.$anyDirty && v$.device.$invalid">
              <small class="form-text text-danger" v-for="error of v$.device.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.branch2')"
              for="transactions-branch2"
            ></label>
            <input
              type="text"
              class="form-control"
              name="branch2"
              id="transactions-branch2"
              data-cy="branch2"
              :class="{ valid: !v$.branch2.$invalid, invalid: v$.branch2.$invalid }"
              v-model="v$.branch2.$model"
            />
            <div v-if="v$.branch2.$anyDirty && v$.branch2.$invalid">
              <small class="form-text text-danger" v-for="error of v$.branch2.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.longerbranch')"
              for="transactions-longerbranch"
            ></label>
            <input
              type="number"
              class="form-control"
              name="longerbranch"
              id="transactions-longerbranch"
              data-cy="longerbranch"
              :class="{ valid: !v$.longerbranch.$invalid, invalid: v$.longerbranch.$invalid }"
              v-model.number="v$.longerbranch.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.transactions.datex')" for="transactions-datex"></label>
            <div class="d-flex">
              <input
                id="transactions-datex"
                data-cy="datex"
                type="datetime-local"
                class="form-control"
                name="datex"
                :class="{ valid: !v$.datex.$invalid, invalid: v$.datex.$invalid }"
                :value="convertDateTimeFromServer(v$.datex.$model)"
                @change="updateInstantField('datex', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.transactions.timex')" for="transactions-timex"></label>
            <input
              type="text"
              class="form-control"
              name="timex"
              id="transactions-timex"
              data-cy="timex"
              :class="{ valid: !v$.timex.$invalid, invalid: v$.timex.$invalid }"
              v-model="v$.timex.$model"
            />
            <div v-if="v$.timex.$anyDirty && v$.timex.$invalid">
              <small class="form-text text-danger" v-for="error of v$.timex.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.transactions.posted')" for="transactions-posted"></label>
            <input
              type="number"
              class="form-control"
              name="posted"
              id="transactions-posted"
              data-cy="posted"
              :class="{ valid: !v$.posted.$invalid, invalid: v$.posted.$invalid }"
              v-model.number="v$.posted.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.attempts')"
              for="transactions-attempts"
            ></label>
            <input
              type="number"
              class="form-control"
              name="attempts"
              id="transactions-attempts"
              data-cy="attempts"
              :class="{ valid: !v$.attempts.$invalid, invalid: v$.attempts.$invalid }"
              v-model.number="v$.attempts.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.originaldata2')"
              for="transactions-originaldata2"
            ></label>
            <input
              type="text"
              class="form-control"
              name="originaldata2"
              id="transactions-originaldata2"
              data-cy="originaldata2"
              :class="{ valid: !v$.originaldata2.$invalid, invalid: v$.originaldata2.$invalid }"
              v-model="v$.originaldata2.$model"
            />
            <div v-if="v$.originaldata2.$anyDirty && v$.originaldata2.$invalid">
              <small class="form-text text-danger" v-for="error of v$.originaldata2.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.commission')"
              for="transactions-commission"
            ></label>
            <input
              type="number"
              class="form-control"
              name="commission"
              id="transactions-commission"
              data-cy="commission"
              :class="{ valid: !v$.commission.$invalid, invalid: v$.commission.$invalid }"
              v-model.number="v$.commission.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.responsecreated')"
              for="transactions-responsecreated"
            ></label>
            <input
              type="number"
              class="form-control"
              name="responsecreated"
              id="transactions-responsecreated"
              data-cy="responsecreated"
              :class="{ valid: !v$.responsecreated.$invalid, invalid: v$.responsecreated.$invalid }"
              v-model.number="v$.responsecreated.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.transactions.online')" for="transactions-online"></label>
            <input
              type="number"
              class="form-control"
              name="online"
              id="transactions-online"
              data-cy="online"
              :class="{ valid: !v$.online.$invalid, invalid: v$.online.$invalid }"
              v-model.number="v$.online.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.originaldata3')"
              for="transactions-originaldata3"
            ></label>
            <input
              type="text"
              class="form-control"
              name="originaldata3"
              id="transactions-originaldata3"
              data-cy="originaldata3"
              :class="{ valid: !v$.originaldata3.$invalid, invalid: v$.originaldata3.$invalid }"
              v-model="v$.originaldata3.$model"
            />
            <div v-if="v$.originaldata3.$anyDirty && v$.originaldata3.$invalid">
              <small class="form-text text-danger" v-for="error of v$.originaldata3.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.toswitch')"
              for="transactions-toswitch"
            ></label>
            <input
              type="text"
              class="form-control"
              name="toswitch"
              id="transactions-toswitch"
              data-cy="toswitch"
              :class="{ valid: !v$.toswitch.$invalid, invalid: v$.toswitch.$invalid }"
              v-model="v$.toswitch.$model"
            />
            <div v-if="v$.toswitch.$anyDirty && v$.toswitch.$invalid">
              <small class="form-text text-danger" v-for="error of v$.toswitch.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.fromswitch')"
              for="transactions-fromswitch"
            ></label>
            <input
              type="text"
              class="form-control"
              name="fromswitch"
              id="transactions-fromswitch"
              data-cy="fromswitch"
              :class="{ valid: !v$.fromswitch.$invalid, invalid: v$.fromswitch.$invalid }"
              v-model="v$.fromswitch.$model"
            />
            <div v-if="v$.fromswitch.$anyDirty && v$.fromswitch.$invalid">
              <small class="form-text text-danger" v-for="error of v$.fromswitch.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.transactions.tocbs')" for="transactions-tocbs"></label>
            <input
              type="text"
              class="form-control"
              name="tocbs"
              id="transactions-tocbs"
              data-cy="tocbs"
              :class="{ valid: !v$.tocbs.$invalid, invalid: v$.tocbs.$invalid }"
              v-model="v$.tocbs.$model"
            />
            <div v-if="v$.tocbs.$anyDirty && v$.tocbs.$invalid">
              <small class="form-text text-danger" v-for="error of v$.tocbs.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.fromcbs')"
              for="transactions-fromcbs"
            ></label>
            <input
              type="text"
              class="form-control"
              name="fromcbs"
              id="transactions-fromcbs"
              data-cy="fromcbs"
              :class="{ valid: !v$.fromcbs.$invalid, invalid: v$.fromcbs.$invalid }"
              v-model="v$.fromcbs.$model"
            />
            <div v-if="v$.fromcbs.$anyDirty && v$.fromcbs.$invalid">
              <small class="form-text text-danger" v-for="error of v$.fromcbs.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.postinglegs')"
              for="transactions-postinglegs"
            ></label>
            <input
              type="number"
              class="form-control"
              name="postinglegs"
              id="transactions-postinglegs"
              data-cy="postinglegs"
              :class="{ valid: !v$.postinglegs.$invalid, invalid: v$.postinglegs.$invalid }"
              v-model.number="v$.postinglegs.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.commissiontxncode')"
              for="transactions-commissiontxncode"
            ></label>
            <input
              type="text"
              class="form-control"
              name="commissiontxncode"
              id="transactions-commissiontxncode"
              data-cy="commissiontxncode"
              :class="{ valid: !v$.commissiontxncode.$invalid, invalid: v$.commissiontxncode.$invalid }"
              v-model="v$.commissiontxncode.$model"
            />
            <div v-if="v$.commissiontxncode.$anyDirty && v$.commissiontxncode.$invalid">
              <small class="form-text text-danger" v-for="error of v$.commissiontxncode.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.hostref')"
              for="transactions-hostref"
            ></label>
            <input
              type="text"
              class="form-control"
              name="hostref"
              id="transactions-hostref"
              data-cy="hostref"
              :class="{ valid: !v$.hostref.$invalid, invalid: v$.hostref.$invalid }"
              v-model="v$.hostref.$model"
            />
            <div v-if="v$.hostref.$anyDirty && v$.hostref.$invalid">
              <small class="form-text text-danger" v-for="error of v$.hostref.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.requestcreated')"
              for="transactions-requestcreated"
            ></label>
            <input
              type="number"
              class="form-control"
              name="requestcreated"
              id="transactions-requestcreated"
              data-cy="requestcreated"
              :class="{ valid: !v$.requestcreated.$invalid, invalid: v$.requestcreated.$invalid }"
              v-model.number="v$.requestcreated.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.requestmessage')"
              for="transactions-requestmessage"
            ></label>
            <input
              type="text"
              class="form-control"
              name="requestmessage"
              id="transactions-requestmessage"
              data-cy="requestmessage"
              :class="{ valid: !v$.requestmessage.$invalid, invalid: v$.requestmessage.$invalid }"
              v-model="v$.requestmessage.$model"
            />
            <div v-if="v$.requestmessage.$anyDirty && v$.requestmessage.$invalid">
              <small class="form-text text-danger" v-for="error of v$.requestmessage.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.outgoingbitmapflex')"
              for="transactions-outgoingbitmapflex"
            ></label>
            <input
              type="text"
              class="form-control"
              name="outgoingbitmapflex"
              id="transactions-outgoingbitmapflex"
              data-cy="outgoingbitmapflex"
              :class="{ valid: !v$.outgoingbitmapflex.$invalid, invalid: v$.outgoingbitmapflex.$invalid }"
              v-model="v$.outgoingbitmapflex.$model"
            />
            <div v-if="v$.outgoingbitmapflex.$anyDirty && v$.outgoingbitmapflex.$invalid">
              <small class="form-text text-danger" v-for="error of v$.outgoingbitmapflex.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.incomingbitmapflex')"
              for="transactions-incomingbitmapflex"
            ></label>
            <input
              type="text"
              class="form-control"
              name="incomingbitmapflex"
              id="transactions-incomingbitmapflex"
              data-cy="incomingbitmapflex"
              :class="{ valid: !v$.incomingbitmapflex.$invalid, invalid: v$.incomingbitmapflex.$invalid }"
              v-model="v$.incomingbitmapflex.$model"
            />
            <div v-if="v$.incomingbitmapflex.$anyDirty && v$.incomingbitmapflex.$invalid">
              <small class="form-text text-danger" v-for="error of v$.incomingbitmapflex.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.requestsent')"
              for="transactions-requestsent"
            ></label>
            <input
              type="number"
              class="form-control"
              name="requestsent"
              id="transactions-requestsent"
              data-cy="requestsent"
              :class="{ valid: !v$.requestsent.$invalid, invalid: v$.requestsent.$invalid }"
              v-model.number="v$.requestsent.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.minicbs')"
              for="transactions-minicbs"
            ></label>
            <input
              type="number"
              class="form-control"
              name="minicbs"
              id="transactions-minicbs"
              data-cy="minicbs"
              :class="{ valid: !v$.minicbs.$invalid, invalid: v$.minicbs.$invalid }"
              v-model.number="v$.minicbs.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.reversed')"
              for="transactions-reversed"
            ></label>
            <input
              type="number"
              class="form-control"
              name="reversed"
              id="transactions-reversed"
              data-cy="reversed"
              :class="{ valid: !v$.reversed.$invalid, invalid: v$.reversed.$invalid }"
              v-model.number="v$.reversed.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.offlinesenttohost')"
              for="transactions-offlinesenttohost"
            ></label>
            <input
              type="number"
              class="form-control"
              name="offlinesenttohost"
              id="transactions-offlinesenttohost"
              data-cy="offlinesenttohost"
              :class="{ valid: !v$.offlinesenttohost.$invalid, invalid: v$.offlinesenttohost.$invalid }"
              v-model.number="v$.offlinesenttohost.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.offlineresponse')"
              for="transactions-offlineresponse"
            ></label>
            <input
              type="text"
              class="form-control"
              name="offlineresponse"
              id="transactions-offlineresponse"
              data-cy="offlineresponse"
              :class="{ valid: !v$.offlineresponse.$invalid, invalid: v$.offlineresponse.$invalid }"
              v-model="v$.offlineresponse.$model"
            />
            <div v-if="v$.offlineresponse.$anyDirty && v$.offlineresponse.$invalid">
              <small class="form-text text-danger" v-for="error of v$.offlineresponse.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.sourceLongerface')"
              for="transactions-sourceLongerface"
            ></label>
            <input
              type="text"
              class="form-control"
              name="sourceLongerface"
              id="transactions-sourceLongerface"
              data-cy="sourceLongerface"
              :class="{ valid: !v$.sourceLongerface.$invalid, invalid: v$.sourceLongerface.$invalid }"
              v-model="v$.sourceLongerface.$model"
            />
            <div v-if="v$.sourceLongerface.$anyDirty && v$.sourceLongerface.$invalid">
              <small class="form-text text-danger" v-for="error of v$.sourceLongerface.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.transactions.mtirrn')" for="transactions-mtirrn"></label>
            <input
              type="text"
              class="form-control"
              name="mtirrn"
              id="transactions-mtirrn"
              data-cy="mtirrn"
              :class="{ valid: !v$.mtirrn.$invalid, invalid: v$.mtirrn.$invalid }"
              v-model="v$.mtirrn.$model"
            />
            <div v-if="v$.mtirrn.$anyDirty && v$.mtirrn.$invalid">
              <small class="form-text text-danger" v-for="error of v$.mtirrn.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.hostresponsecode')"
              for="transactions-hostresponsecode"
            ></label>
            <input
              type="text"
              class="form-control"
              name="hostresponsecode"
              id="transactions-hostresponsecode"
              data-cy="hostresponsecode"
              :class="{ valid: !v$.hostresponsecode.$invalid, invalid: v$.hostresponsecode.$invalid }"
              v-model="v$.hostresponsecode.$model"
            />
            <div v-if="v$.hostresponsecode.$anyDirty && v$.hostresponsecode.$invalid">
              <small class="form-text text-danger" v-for="error of v$.hostresponsecode.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('bbMobileBankingAdminApp.transactions.field48')"
              for="transactions-field48"
            ></label>
            <input
              type="text"
              class="form-control"
              name="field48"
              id="transactions-field48"
              data-cy="field48"
              :class="{ valid: !v$.field48.$invalid, invalid: v$.field48.$invalid }"
              v-model="v$.field48.$model"
            />
            <div v-if="v$.field48.$anyDirty && v$.field48.$invalid">
              <small class="form-text text-danger" v-for="error of v$.field48.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('bbMobileBankingAdminApp.transactions.source')" for="transactions-source"></label>
            <input
              type="text"
              class="form-control"
              name="source"
              id="transactions-source"
              data-cy="source"
              :class="{ valid: !v$.source.$invalid, invalid: v$.source.$invalid }"
              v-model="v$.source.$model"
            />
            <div v-if="v$.source.$anyDirty && v$.source.$invalid">
              <small class="form-text text-danger" v-for="error of v$.source.$errors" :key="error.$uid">{{ error.$message }}</small>
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
<script lang="ts" src="./transactions-update.component.ts"></script>
