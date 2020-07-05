<template>
  <v-container>
    <v-navigation-drawer
      v-model="drawer"
      :color="color"
      :expand-on-hover="expandOnHover"
      :mini-variant="miniVariant"
      :right="right"
      :permanent="permanent"
      :src="bg"
      absolute
      dark
    >
      <v-list dense nav class="py-0">
        <v-list-item two-line :class="miniVariant && 'px-0'">
          <v-list-item-avatar>
            <img src="https://randomuser.me/api/portraits/men/81.jpg" />
          </v-list-item-avatar>
          <v-spacer></v-spacer>
          <v-list-item-content>
            <v-list-item-title>欢迎您</v-list-item-title>
            <v-list-item-subtitle>路人</v-list-item-subtitle>
          </v-list-item-content>
          <v-spacer></v-spacer>
          <v-spacer></v-spacer>
        </v-list-item>
        <v-list-item @click="$router.push('/search')">
          <v-list-item-icon class="icon-right">
            <v-icon>mdi-magnify</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>搜索栏</v-list-item-title>
          </v-list-item-content>
        </v-list-item>

        <v-list-item @click="$router.push('/grade')">
          <v-list-item-icon class="icon-right">
            <v-icon>mdi-clipboard-text</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>类型均分</v-list-item-title>
          </v-list-item-content>
        </v-list-item>

        <v-list-item @click="miniVariant = !miniVariant">
          <v-list-item-icon class="icon-right">
            <v-icon>mdi-swap-horizontal</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>收起</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
    <keep-alive>
      <router-view></router-view>
    </keep-alive>
  </v-container>
</template>
<script>
export default {
  data() {
    return {
      drawer: true,
      showSearch: false,
      items: [],
      color: "primary",
      colors: ["primary", "blue", "success", "red", "teal"],
      right: false,
      permanent: true,
      miniVariant: false,
      expandOnHover: false,
      background: false
    };
  },
  computed: {
    bg() {
      return this.background
        ? "https://cdn.vuetifyjs.com/images/backgrounds/bg-2.jpg"
        : undefined;
    }
  }
};
</script>

<style lang="scss">
.icon-right {
  margin-right: 10px !important;
}
.set-right {
  right: 0;
  left: 0;
  margin: auto;
}
</style>
