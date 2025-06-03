<template>
  <div v-if="authenticated">
    <div class="home row">
      <div class="col-md-12">
        <h1>DASHBOARD</h1>
      </div>
    </div>
    <br />
    <div class="home row">
      <div class="col-md-3">
        <b-card bg-variant="primary" text-variant="white" header="Number of Customers" class="text-center">
          <b-card-text>140</b-card-text>
        </b-card>
      </div>
      <div class="col-md-3">
        <b-card bg-variant="success" text-variant="white" header="Number of Transactions" class="text-center">
          <b-card-text>19000</b-card-text>
        </b-card>
      </div>
      <div class="col-md-3">
        <b-card bg-variant="warning" text-variant="white" header="Number of failed Requests" class="text-center">
          <b-card-text>1787</b-card-text>
        </b-card>
      </div>
      <div class="col-md-3">
        <b-card bg-variant="danger" text-variant="white" header="Number of Pending Pin Request" class="text-center">
          <b-card-text>10</b-card-text>
        </b-card>
      </div>
    </div>
    <br />
    <div class="home row"></div>
    <div class="home row">
      <div class="col-md-3">
        <p>Number of CBS Transactions</p>
        <cbspiechart />
      </div>
      <div class="col-md-3">
        <p>Transaction Errors vs Success</p>
        <txnsdonut />
      </div>
      <div class="col-md-3">
        <p>Service Requests Pending vs Completed</p>
        <servicereqpiechart />
      </div>
      <div class="col-md-3">
        <p>SPS Transactions</p>
        <spsgraph />
      </div>
    </div>
    <div class="home row"></div>
    <div class="home row">
      <div class="col-md-3">
        <p>Users</p>
        <usersbarchart />
      </div>
      <div class="col-md-3">
        <p>Transactions</p>
        <txnsgraph />
      </div>
      <div class="col-md-3">
        <p>Service Request</p>
        <servicereqbarchart />
      </div>
      <div class="col-md-3">
        <p>Pin Reset</p>
        <pinresetbarchart />
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./home.component.ts"></script>
<style></style>
