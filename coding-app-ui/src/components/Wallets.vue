
<template>
<div class="Wallets">
    <!-- Table for wallets -->
    <table class="wallets-table">
        <th>
            <td>Wallet</td>
            <td>Balance</td>
        </th>
        <tr v-for="wallet in wallets" :key="wallet.id" v-on:click="openTransactionPanel(wallet.id)">
            <td>{{ wallet.id }}</td>
            <td>{{ wallet.balance }}</td>
        </tr>
    </table>
    <div v-if="showTransaction">
      <h3>Make transaction</h3>
      <div id="from">
        <h4>Sender: {{ sender }}</h4>
        <form>
          <label for="beneficiary">Beneficiary: </label>
          <input id="beneficiary" v-model="beneficiary" type="text">
          <br>
          <label for="amount">Amount to send: </label>
          <input id="amount" v-model="amount" type="text">
          <br>
          <input type="button" value="Send" v-on:click="makeTransaction">
        </form>
      </div>
    </div>
</div>
</template>

<script>
    const walletsUrl = "http://localhost:8000"
    import axios from 'axios'
    
    export default {
      name: 'Wallets',
      data: function () {
        return {
          message: 'Wallets table rows',
          wallets: [],
          openUserId: "",
          sender: "",
          beneficiary: "",
          amount: "",
          showTransaction: false
        }
      },
      methods: {
        getWalletsFromUser: function (userId) {
          var self = this;
          var url = walletsUrl + "/user" + "/" + userId + "/wallet"
          axios.get(url, {
            dataType: 'json',
            headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json'
            },
            mode: 'no-cors',
          })
          .then(function (response) {
            console.log(JSON.stringify(response.data))
            self.wallets = response.data;
          })
          .catch(function (error) {
            console.log(error);
          });
        },
        openTransactionPanel: function (walletId) {
          var self = this;
          self.sender = walletId;
          self.showTransaction = true;
        },
        makeTransaction: function () {
          var self = this;
          // build request object
          var body = {
            "senderId": self.sender,
            "beneficiaryId": self.beneficiary,
            "amount": self.amount
          };
          console.log(body);
          // make request
          var url = walletsUrl + "/wallet/make_transaction";
          axios.post(url, body, {
            dataType: 'json',
            headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json'
            },
            mode: 'no-cors',
          })
          .then(function (response) {
            console.log(JSON.stringify(response.data))
            alert("Operation completed successfully!");
            // Reload amounts
            self.getWalletsFromUser(response.data.user.id);
            self.showTransaction = false;
          })
          .catch(function (error) {
            console.log(error);
          });
        }      
      }
    }
</script>