<template>
  <div>
    <v-card class="mx-auto" width="100%">
      <v-card-text>
        <div>总评分</div>
      </v-card-text>
      <v-container>
        <div style="height: 400px; width: 100%;" ref="total"></div>
      </v-container>
    </v-card>
    <div style="margin-top: 20px;"></div>
    <v-card class="mx-auto" width="100%">
      <v-card-text>
        <div>男女评分</div>
      </v-card-text>
      <v-container>
        <div style="height: 400px; width: 100%;" ref="gender"></div>
      </v-container>
    </v-card>
    <div style="margin-top: 20px;"></div>

    <v-card class="mx-auto" width="100%">
      <v-card-text>
        <div>年龄评分</div>
      </v-card-text>
      <v-container>
        <div style="height: 400px; width: 100%;" ref="age"></div>
      </v-container>
    </v-card>
  </div>
</template>

<script>
import echarts from "echarts";
import Axios from "axios";
export default {
  data() {
    return {
      $http: Axios.create({
        baseURL: "http:127.0.0.1:8080"
      }),
      data2: {},
      data3: {},
      data: {}
    };
  },
  activated() {
    this.fetch();
    this.setChart(["总评分"], "total");
    this.setChart(["男", "女"], "gender");
    this.setChart(["0-17", "17-45", "45s岁以上"], "age");
  },
  methods: {
    async fetch() {
      this.data = await this.$http.get("/ave_score/genres");
      this.data3 = await this.$http.get("/ave_score/age");
      this.data2 = await this.$http.get("/ave_score/gender");
    },
    setChart(arr, type) {
      let xData = [],
        y1Data = [],
        y2Data = [],
        y3Data = [];
      if (type == "gender") {
        for (const [key, obj] of Object.entries(this.data.Man)) {
          y1Data.push(obj);
          xData.push(key);
        }
        // eslint-disable-next-line no-unused-vars
        for (const [key, obj] of Object.entries(this.data.Female)) {
          y2Data.push(obj);
        }
      } else if (type == "total") {
        for (const [key, obj] of Object.entries(this.data2)) {
          y1Data.push(obj);
          xData.push(key);
        }
        // eslint-disable-next-line no-unused-vars
        // for (const [key, obj] of Object.entries(this.data.Female)) {
        //   y2Data.push(obj);
        // }
      } else {
        for (const [key, obj] of Object.entries(this.data3.zeroToSeventeen)) {
          y1Data.push(obj);
          xData.push(key);
        }
        // eslint-disable-next-line no-unused-vars
        for (const [key, obj] of Object.entries(
          this.data3.eighteenToFourtyFive
        )) {
          y2Data.push(obj);
        }
        // eslint-disable-next-line no-unused-vars
        for (const [key, obj] of Object.entries(this.data3.overFourtySix)) {
          y3Data.push(obj);
        }
      }

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
          data: arr,
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
            show: true,
            rotate: -45,
            fontSize: 10,
            verticalAlign: "center"
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
        series: [
          {
            type: "bar",
            // itemStyle: {
            //     normal: {
            //         colo,
            //     }
            // },
            barWidth: "10px",
            data: y1Data
          },
          {
            type: "bar",
            // itemStyle: {
            //     normal: {
            //         colo,
            //     }
            // },
            barWidth: "10px",
            data: y2Data
          },
          {
            type: "bar",
            // itemStyle: {
            //     normal: {
            //         colo,
            //     }
            // },
            barWidth: "10px",
            data: y3Data
          }
        ]
      };
      const chart = echarts.init(this.$refs[type]);
      chart.setOption(option);
    }
  }
};
</script>

<style lang="scss"></style>
