
<template>
<div class="Users">
  <h1 class="title is-3">PagaCoins Administration Panel</h1>
    <!-- Table for users -->
    <table class="table is-fullwidth is-striped is-hoverable">
      <thead>
        <th>User ID</th>
        <th>User Name</th>
      </thead>
      <tbody>
        <tr v-for="user in users" :key="user.id" v-on:click=getWalletsFromUser(user.id)>
            <td>{{ user.id }}</td>
            <td>{{ user.name }}</td>
        </tr>
      </tbody>
    </table>
    <div v-bind:class="{ 'is-active': showWallets }" class="modal">
      <div class="modal-background"></div>
      <div class="modal-card">
        <header class=modal-card-head>
          <h1 class="moda-card-title title is-4"> Pagacoins wallet </h1> 
        </header>
        <section class="modal-card-body" id="wallets-container">
          <Wallets ref="Wallets"></Wallets>
        </section>
        <footer class="modal-card-foot">
          <button class="button" v-on:click="showWallets=false">Cancel</button>
        </footer> 
      </div>1
    </div>
</div>
</template>

<script>
    const usersUrl = "http://192.168.0.18:8000/user"
    import axios from 'axios'
    import Wallets from '@/components/Wallets.vue'
    
    export default {
      name: 'Users',
      components: {
        Wallets,
      },
      mounted: function () {
        this.getUsers()
      },
      data: function () {
        return {
          message: 'Users table rows',
          users: [],
          showWallets: false
        }
      },
      methods: {
        getUsers: function () {
          var self = this;
          axios.get(usersUrl, {
            dataType: 'json',
            headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json'
            },
            mode: 'no-cors',
          })
          .then(function (response) {
            console.log(JSON.stringify(response.data))
            self.users = response.data;
          })
          .catch(function (error) {
            console.log(error)
          })
        },
        getWalletsFromUser: function(userId) {
          var self = this;
          self.showWallets = true;
          this.$refs.Wallets.showTransaction = false;
          this.$refs.Wallets.getWalletsFromUser(userId);
        }    
      }
    }

</script>