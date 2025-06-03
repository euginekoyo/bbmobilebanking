<template>
  <div class="container mt-5">
    <h4 class="mb-4">Notification to Verified Users</h4>
    <form @submit.prevent="submitForm">
      <!-- Send Via Options -->
      <div class="btn-group mb-4 w-100" role="group">
        <button
          v-for="option in sendOptions"
          :key="option"
          type="button"
          class="btn toggle-btn"
          :class="{ active: form.send_method === option }"
          @click="form.send_method = option"
        >
          Send Via {{ option }}
        </button>
      </div>

      <!-- Being Sent To -->
      <div class="mb-3">
        <label class="form-label">Being Sent To *</label>
        <select v-model="form.recipient_group" class="form-select" required>
          <option value="all">All Users</option>
        </select>
      </div>

      <!-- Subject -->
      <div class="mb-3">
        <label class="form-label">Subject *</label>
        <input v-model="form.subject" type="text" class="form-control" placeholder="Subject / Title" required />
      </div>

      <!-- Message -->
      <div class="mb-3">
        <label class="form-label">Message *</label>
        <textarea v-model="form.message" class="form-control" placeholder="Write your message here..." required></textarea>
      </div>

      <!-- Start Form / Per Batch / Cooling Period -->
      <div class="row mb-4">
        <div class="col-md-4">
          <label class="form-label">Start Form *</label>
          <input v-model="form.start_form_id" type="text" class="form-control" placeholder="Start form user id. e.g. 1" required />
        </div>
        <div class="col-md-4">
          <label class="form-label">Per Batch *</label>
          <div class="input-group">
            <input v-model="form.per_batch" type="number" class="form-control" placeholder="How many user" required />
            <span class="input-group-text">User</span>
          </div>
        </div>
        <div class="col-md-4">
          <label class="form-label">Cooling Period *</label>
          <div class="input-group">
            <input v-model="form.cooling_period" type="number" class="form-control" placeholder="Waiting time" required />
            <span class="input-group-text">Seconds</span>
          </div>
        </div>
      </div>

      <!-- Submit Button -->
      <div class="text-center">
        <button type="submit" class="btn btn-primary w-100">Submit</button>
      </div>
    </form>

    <!-- Optional: Status Alert -->
    <div v-if="statusMessage" class="alert mt-3" :class="statusClass" role="alert">
      {{ statusMessage }}
    </div>
  </div>
</template>

<script setup>
import { reactive, ref } from 'vue';
import axios from 'axios';

const sendOptions = ['Email', 'SMS', 'Firebase'];

const form = reactive({
  send_method: 'Email',
  recipient_group: 'all',
  subject: '',
  message: '',
  start_form_id: '',
  per_batch: '',
  cooling_period: '',
});

const statusMessage = ref('');
const statusClass = ref('alert-info');

async function submitForm() {
  try {
    const response = await axios.post('/api/notifications', form);
    statusMessage.value = 'Notification sent successfully!';
    statusClass.value = 'alert-success';
    console.log(response.data);
  } catch (error) {
    statusMessage.value = 'Failed to send notification.';
    statusClass.value = 'alert-danger';
    console.error(error);
  }
}
</script>

<style scoped>
.toggle-btn {
  border-radius: 0;
  border: 1px solid #ccc;
}
.toggle-btn.active {
  background-color: #5c6bc0;
  color: white;
  border-color: #5c6bc0;
}
textarea.form-control {
  height: 200px;
  resize: vertical;
}
</style>
