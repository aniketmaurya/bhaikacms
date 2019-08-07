<template>
  <div>
    <h1>Bulk Upload</h1>
    <center>
      <button @click="changeSelected('programs','addProgramInBulk')">Programs</button>
      <button @click="changeSelected('seasons','addSeasonInBulk')">Seasons</button>
      <button @click="changeSelected('episodes','addEpisodeInBulk')">Episodes</button>
      <button @click="changeSelected('single','addSingleVideoInBulk')">Single Videos</button>
      <!-- <button @click="changeSelected('multi','addMultiVideoByBulk')">Multi Videos</button> -->
    </center>
    <div v-if="clicked">
      <center>
        <h3>Uploaded CSV file should have header in given order</h3>
        <table>
          <tr v-if="selected=='programs'">
            <th>Program Type</th>
            <th>Description</th>
            <th>Program Name</th>
            <th>Parental Rating</th>
            <th>Keywords</th>
            <th>Languages</th>
            <th>Start Date</th>
            <th>Expiry Date</th>
            <th>Thumbnail Image URL</th>
            <th>Avatar Image URL</th>
            <th>User Id</th>
            <th>User Email</th>
          </tr>
          <tr v-if="selected=='seasons'">
            <th>Program Id</th>
            <th>Season Name</th>
            <th>Season Number</th>
            <th>Season Description</th>
            <th>Thumbnail Image URL</th>
            <th>Avatar Image URL</th>
            <th>User Id</th>
            <th>User Email</th>
          </tr>
          <tr v-if="selected=='episodes'">
            <th>Episode Number</th>
            <th>Episode Title</th>
            <th>Episode Description</th>
            <th>Epsiode Video URL</th>
            <th>Thumbnail Image URL</th>
            <th>Avatar Image URL</th>
            <th>Season Id</th>
            <th>Crew List (Example-> director:name; musician:name; ...)</th>
          </tr>
          <tr v-if="selected=='single'">
            <th>Program Id</th>
            <th>Video Title</th>
            <th>Video Description</th>
            <th>Parental Rating</th>
            <th>Thumbnail Image URL</th>
            <th>Avatar Image URL</th>
            <th>Crew List(Example-> director:name; musician:name; ...)</th>
          </tr>
        </table>
        <input type="file" @change="getFile($event)" id="input"/>
        <button @click="uploadFile()">Upload</button>
      </center>
    </div>
    <div v-else>
      <center>
        <h1>Choose Type Of Bulk Upload</h1>
      </center>
    </div>
  </div>
</template>
<script src="./js/bulkUpload.js">
</script>
<style scoped>
button{
    margin: 1%;
    padding:1%;
}
input[type="file"] {
  border: 1px solid black;
  padding: 1%;
}
tr{
    box-sizing: content-box;
}
th {
  border: 1px solid black;
  padding: 1%;
  text-emphasis: 1;
  box-sizing: content-box;
}
table {
  margin: 2%;
}
</style>
