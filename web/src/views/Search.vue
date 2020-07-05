<template>
  <div>
    <v-row>
         <v-col cols="12" sm="8" md="8">
          <v-text-field
            label="电影名"
            outlined
            v-model="input"
          ></v-text-field>
        </v-col>
         <v-col cols="12" sm="4" md="4">
          <v-btn large @click="getData">
            搜索
          </v-btn>
        </v-col>


        

      </v-row>
    <v-list-item v-for="(item, index) in data" :key="index">
      <v-list-item-content @click="$router.push('/stars')">
        <v-list-item-title>{{item.name}}</v-list-item-title>
      </v-list-item-content>
    </v-list-item>
  </div>
</template>
<script>
import Axios from 'axios'
export default {
  data(){
    return {
      $http: Axios.create({
        baseURL: "http:127.0.0.1:8080"
      }),
      input: "",
      data : []
    }
  },
  methods:{
    getData(){
      this.fetch();
    },
    async fetch(){
      const res = await this.$http.get("/movie/search",{
        name: this.input
      })
      this.data = res.data.forEach(item=>{
        item.name = item.name.chartAt(0).toUpperCase() + item.name.slice(1); // 做大小写匹配
      })
    }
  }
}
</script>