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
      data2: {
        "Film-Noir": "3.92",
        Action: "3.45",
        Adventure: "3.51",
        Horror: "3.26",
        War: "3.81",
        Romance: "3.51",
        Western: "3.58",
        Documentary: "3.80",
        "Sci-Fi": "3.45",
        Drama: "3.66",
        Thriller: "3.49",
        "(no genres listed)": "3.47",
        Crime: "3.66",
        Fantasy: "3.49",
        IMAX: "3.62",
        Animation: "3.63",
        Comedy: "3.38",
        Mystery: "3.63",
        Children: "3.41",
        Musical: "3.56"
      },
      data3: {
        zeroToSeventeen: {
          "Film-Noir": "3.32",
          Action: "3.48",
          Adventure: "3.50",
          Horror: "3.04",
          War: "3.76",
          Romance: "3.53",
          Western: "3.41",
          Documentary: "3.62",
          "Sci-Fi": "3.46",
          Drama: "3.64",
          Thriller: "3.40",
          "(no genres listed)": "4.00",
          Crime: "3.65",
          Fantasy: "3.52",
          IMAX: "3.63",
          Animation: "3.70",
          Comedy: "3.37",
          Mystery: "3.59",
          Children: "3.45",
          Musical: "3.67"
        },
        eighteenToFourtyFive: {
          "Film-Noir": "3.32",
          Action: "3.48",
          Adventure: "3.50",
          Horror: "3.04",
          War: "3.76",
          Romance: "3.53",
          Western: "3.41",
          Documentary: "3.62",
          "Sci-Fi": "3.46",
          Drama: "3.64",
          Thriller: "3.40",
          "(no genres listed)": "4.00",
          Crime: "3.65",
          Fantasy: "3.52",
          IMAX: "3.63",
          Animation: "3.70",
          Comedy: "3.37",
          Mystery: "3.59",
          Children: "3.45",
          Musical: "3.67"
        },
        overFourtySix: {
          "Film-Noir": "3.32",
          Action: "3.48",
          Adventure: "3.50",
          Horror: "3.04",
          War: "3.76",
          Romance: "3.53",
          Western: "3.41",
          Documentary: "3.62",
          "Sci-Fi": "3.46",
          Drama: "3.64",
          Thriller: "3.40",
          "(no genres listed)": "4.00",
          Crime: "3.65",
          Fantasy: "3.52",
          IMAX: "3.63",
          Animation: "3.70",
          Comedy: "3.37",
          Mystery: "3.59",
          Children: "3.45",
          Musical: "3.67"
        }
      },
      data: {
        Female: {
          "Film-Noir": "4.04",
          Action: "3.47",
          Adventure: "3.51",
          Horror: "3.33",
          War: "3.81",
          Romance: "3.50",
          Western: "3.62",
          Documentary: "3.83",
          "Sci-Fi": "3.47",
          Drama: "3.67",
          Thriller: "3.52",
          "(no genres listed)": "3.46",
          Crime: "3.67",
          Fantasy: "3.51",
          IMAX: "3.61",
          Animation: "3.65",
          Comedy: "3.37",
          Mystery: "3.66",
          Children: "3.44",
          Musical: "3.56"
        },
        Man: {
          "Film-Noir": "3.85",
          Action: "3.44",
          Adventure: "3.51",
          Horror: "3.23",
          War: "3.81",
          Romance: "3.51",
          Western: "3.56",
          Documentary: "3.78",
          "Sci-Fi": "3.45",
          Drama: "3.65",
          Thriller: "3.48",
          "(no genres listed)": "3.47",
          Crime: "3.65",
          Fantasy: "3.49",
          IMAX: "3.62",
          Animation: "3.62",
          Comedy: "3.39",
          Mystery: "3.62",
          Children: "3.40",
          Musical: "3.56"
        }
      }
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
