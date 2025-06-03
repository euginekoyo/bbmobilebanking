<template>
  <Pie :data="data" :options="options" />
</template>

<script lang="ts">
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js';
import { Pie } from 'vue-chartjs';

ChartJS.register(ArcElement, Tooltip, Legend);

export default {
  name: 'cbspiechart',
  components: {
    Pie,
  },
  data() {
    return {
      data: {
        labels: ['IFT', 'BillPayment', 'AccountToMNO', 'TermDeposit'],
        datasets: [
          {
            backgroundColor: ['#41B883', '#E46651', '#00D8FF', '#DD1B16'],
            data: [40, 20, 80, 10],
          },
        ],
      },
    };
  },
};
</script>
