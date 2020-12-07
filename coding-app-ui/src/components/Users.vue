
<template>
<div class="Users">
    <h1>Admin panel</h1>
    <!-- Table for users -->
    <table class="users-table">
        <th>
            <td>User</td>
        </th>
        <tr v-for="user in users" :key="user.id">
            <td>{{ user.id }}</td>
        </tr>
    </table>
</div>
</template>

<script>
    const usersUrl = "http://192.168.0.18:8000/user"
    import axios from 'axios'
    
    export default {
      name: 'Users',
      mounted: function () {
        this.getUsers()
      },
      data: function () {
        return {
          message: 'Users table rows',
          users: [],
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
        }      
      }
    }
</script>