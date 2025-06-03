<template>
  <Doughnut :data="data" :options="options" />
</template>

<script lang="ts">
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js';
import { Doughnut } from 'vue-chartjs';

ChartJS.register(ArcElement, Tooltip, Legend);

export default {
  name: 'txnsdonut',
  components: {
    Doughnut,
  },
  data() {
    return {
      data: {
        labels: ['Success', 'Failed'],
        datasets: [
          {
            backgroundColor: ['#41B883', '#E46651'],
            data: [90, 10],
          },
        ],
      },
    };
  },
};
</script>
