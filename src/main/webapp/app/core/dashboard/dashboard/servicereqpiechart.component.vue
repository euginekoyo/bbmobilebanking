<template>
  <Pie :data="data" :options="options" />
</template>

<script lang="ts">
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js';
import { Pie } from 'vue-chartjs';

ChartJS.register(ArcElement, Tooltip, Legend);

export default {
  name: 'servicereqpiechart',
  components: {
    Pie,
  },
  data() {
    return {
      data: {
        labels: ['Pending', 'Completed'],
        datasets: [
          {
            backgroundColor: ['#41B883', '#E46651'],
            data: [20, 80],
          },
        ],
      },
    };
  },
};
</script>
