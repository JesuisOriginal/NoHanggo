// var firebaseConfig = {
//     apiKey: "AIzaSyBUvWA1s7wW92Qr2bvNBDM8ZWJZ35pE7p0",
//     authDomain: "feedback-hanggo-8ac02.firebaseapp.com",
//     databaseURL: "https://feedback-hanggo-8ac02.firebaseio.com",
//     projectId: "feedback-hanggo-8ac02",
//     storageBucket: "feedback-hanggo-8ac02.appspot.com",
//     messagingSenderId: "937545695781",
//     appId: "1:937545695781:web:806cd019ea14a56171b5cc",
//     measurementId: "G-GE9TL9983W"
//   };
//   firebase.initializeApp(firebaseConfig);
//   firebase.analytics();
// var db = firebase.database();
// var comentario;
// function escrever(){
//     comentario = document.getElementById("box").value;
// }
// describe("collection('users')", () => {
//     it("should set a document", () => {
//         return output = db.collection("feedback").doc("comments").set({
//         comments: comentario 
//     })
//     .then(function() {
//         console.log(comentario);
//     })
//     .catch(function(error) {
//         console.error("Error writing document: ", error);
//     }); 
//     });
// });
//   var nome;
//   var idade;
//   function escrever(){
//     firebase.database().ref("feedback").set({
//     // nome = document.getElementById('usuario').value;
//     // idade = document.getElementById('idade').value;
//     comentario: document.getElementById("box").value
//     });
// }
function escrever(){
    comentario = document.getElementById("box").value;
}

const firebase = require("firebase");
// Required for side-effects
require("firebase/firestore");
// Initialize Cloud Firestore through Firebase
firebase.initializeApp({
    apiKey: "AIzaSyBUvWA1s7wW92Qr2bvNBDM8ZWJZ35pE7p0",
    authDomain: "feedback-hanggo-8ac02.firebaseapp.com",
    projectId: "feedback-hanggo-8ac02"
  });
  
var db = firebase.firestore();

db.collection("users").add({
    comments: comentario
})
.then(function(docRef) {
    console.log("Document written with ID: ", docRef.id);
})
.catch(function(error) {
    console.error("Error adding document: ", error);
});
