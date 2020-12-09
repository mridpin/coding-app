
<template>
<div class="Wallets">
    <!-- Table for wallets -->
    <table class="table is-fullwidth is-striped is-hoverable">
      <thead>
            <th>Wallet</th>
            <th>Hash</th>
            <th>Balance</th>
        </thead>
        <tbody>
          <tr v-for="wallet in wallets" :key="wallet.id" v-on:click="openTransactionPanel(wallet)">
              <td>{{ wallet.id }}</td>
              <td>{{ wallet.hash }}</td>
              <td>{{ wallet.balance }}</td>
          </tr>
        </tbody>
    </table>
    <div class="container" v-if="showTransaction">
      <h3 class="title is-5">Make transaction</h3>
      <div id="from">
        <h4 class="title is-6">Sender Hash: {{ sender.hash }}</h4>
        <form>
          <div class="field">
            <label class="label" for="beneficiary">Beneficiary: </label>
            <div class="control">
              <input class="input" id="beneficiary" v-model="beneficiary" type="text" placeholder="Beneficiary hash">
            </div>
          </div>
          <div class="field">
            <label class="label" for="amount">Amount to send: </label>
            <div class="control">
              <input class="input" id="amount" v-model="amount" type="text" placeholder="0.00">
            </div>
          </div>
          <div class="field is-grouped">
            <div class="control">
              <input class="button is-link is-medium is-left" type="button" value="Send" v-on:click="makeTransaction">
            </div>
            <div class="control">
              <input class="button is-medium is-right is-clear" type="button" value="Close" v-on:click="showTransaction=false">
            </div>
          </div>
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
          sender: {},
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
        openTransactionPanel: function (wallet) {
          var self = this;
          self.sender = wallet;
          self.showTransaction = true;
        },
        makeTransaction: function () {
          var self = this;
          // build request object
          var body = {
            "senderHash": self.sender.hash,
            "beneficiaryHash": self.beneficiary,
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
            if (error.response.status === 412) {
              alert("Operation error: Insuficient balance");
            } else if (error.response.status === 406) {
              alert("Operation failed: Amount not valid");
            } else if (error.response.status === 400) {
              alert("Operation failed: Invalid input");
            } else if (error.response.status === 404) {
              alert("Operation failed: Beneficiary does not exist");
            }
          });
        }      
      }
    }
</script>