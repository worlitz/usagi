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
      data : [

      ]
    }
  },
  methods:{
    getData(){
      // this.fetch()
         setTimeout(()=>{
            this.data = [
           {name : "Toy Story"},
        {name: "Babes in Toyland"},
        {name: "Toys"},
        {name: "Dry Cleaning (Nettoyage à sec) (1997)"},
        {name: "Babes in Toyland (1934)"},
        {name: "Toy Story 2 (1999)"},
        {name: "Toy, The (1982)"},
        {name: "Toy Soldiers (1991)"},
        {name: "Giants and Toys (Kyojin to gangu) (1958)"},
        {name: "Puppet Master vs. Demonic Toys (Puppet Master 9) (2004)"},
        {name: "Toy Story 3 (2010)" },
        {name: "Christmas Toy, The (1986)"},
        {name: "Toy, The (Le jouet) (1976)"},
        {name: "Demonic Toys (1992)"},
        {name: "Toys in the Attic (1963)"},
        {name: "Devil Times Five (a.k.a. Peopletoys) (1974)"},
        {name: "Tin Toy (1988)"},
        {name: "Toy Story of Terror (2013)"},
        {name: "Dollman vs. Demonic Toys (1993)"},
        {name: "Toy Story Toons: Hawaiian Vacation (2011)"},
        {name: "How the Toys Saved Christmas (1996)"},
        {name: "Me Shivajiraje Bhosale Boltoy (2009)"},
        {name: "Toy Soldiers (1984)"},
        {name: "Plastic Galaxy: The Story of Star Wars Toys (2014)"},
        {name: "Toy Reanimator (2002)"},
        {name: "Toyland (2007)"},
        {name: "Soviet Toys (1924)"}
         ]
         }, 1300)
    },
    async fetch(){
      const res = await this.$http.get("/movie/search",{
        name: this.input
      })
      this.data = res.data
    }
  }
}
</script>