<template>
  <v-card class="mx-auto" width="100%">
    <v-card-text>
      <div>最近10次打分</div>
    </v-card-text>
    <v-container>
      <div style="height: 600px; width: 100%;" ref="star"></div>
    </v-container>
    <v-card-actions>
      <v-text-field
        width="100"
        label="输入分数"
        v-model="fenshu"
      ></v-text-field>
      <v-btn text color="deep-purple accent-4" @click="dafen">打分</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import echarts from "echarts";
import Axios from "axios";
export default {
  data() {
    return {
      $http: Axios.create({
        baseUrl: "http://localhost:8080"
      }),
      fenshu: 0,
      id:1,
      count: 0,
      chart: {},
      data: [

      ]
    };
  },
  activated() {
    if (JSON.stringify(this.chart) !== "{}"){
      this.chart.clear()

    }
    this.fetch()
    this.setChart(this.count);

 
  },
  methods: {
    async fetch() {
      this.data = await this.$http.get("/movie/rating", {
        id:this.id
      })
    },
    dafen() {
      for (let i = 9; i > 0; i--) {
        this.data[0][i].rating = this.data[0][i - 1].rating;
      }
      this.data[0][0].rating = this.fenshu;
      this.setChart(0);
      this.$http.post('/json/data',{
        id:this.id,
        rating: this.fenshu
      })
    },
    setChart(i) {
      let xData = [];
      for (let i = 0; i < 10; i++) {
        xData.push(`第${i + 1}条`);
      }
      let yData = [[], [], []];
      this.data.forEach((item, index) => {
        item.forEach(da => {
          yData[index].push(da.rating);
        });
      });
      const series = [];
      series.push({
        name: "Toy Story",
        type: "line",
        smooth: true,
        data: yData[i]
      });
      series.push({
        name: "Pirates of the Caribbean",
        type: "line",
        smooth: true,
        data: yData[i + 1]
      });
      series.push({
        name: "The Lord Of The Rings",
        type: "line",
        smooth: true,
        data: yData[i + 2]
      });
      const option = {
        color: ["rgb(48,241,227)", "rgb(80,122,209)", "rgb(255,183,97)"],
        title: {
          text: ""
        },
        legend: {
          icon: "circle",
          itemWidth: 10,
          itemHeight: 10,
          itemGap: 10,
          data: [
            "Toy Story",
            "Pirates of the Caribbean",
            "The Lord Of The Rings"
          ],
          right: "14",
          top: "0",
          textStyle: {
            fontSize: 12
          }
        },
        grid: {
          top: 50,
          left: 20,
          right: 20,
          containLabel: true
        },
        tooltip: {
          trigger: "axis"
        },
        xAxis: {
          data: xData,
          type: "category",
          axisLine: {
            lineStyle: {
              color: "#b2b2b2"
            }
          },
          axisTick: {
            show: false
          },
          axisLabel: {
            color: "#888888"
          }
        },
        yAxis: {
          splitLine: {
            show: true
          },
          // minInterval: 100 * 1024 * 1024, // 确保最小跨度为1000M
          type: "value",
          axisLine: {
            show: false
          },
          axisTick: {
            show: false
          },
          axisLabel: {
            color: "#888888"
            // formatter(value) {
            //     // if ((value / 1024 /1024) > 1) {
            //     //     return `${value / 1000}M`;
            //     // } else {
            //     //     return value;
            //     // }
            //     return `${(value / 1024 / 1024).toFixed(0)}M`;
            // }
          }
        },
        series
      };

      this.chart = echarts.init(this.$refs.star);
      this.chart.setOption(option);
    }
  }
};
</script>
