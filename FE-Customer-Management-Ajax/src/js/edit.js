$(document).ready(function () {
    selectCategory()
})

// Initialize Firebase
const firebaseConfig = {
    apiKey: "AIzaSyCRUkgz07EeDqrlR93Zgaqo-_6PXOs59sA",
    authDomain: "ducthang-ae432.firebaseapp.com",
    databaseURL: "https://ducthang-ae432-default-rtdb.firebaseio.com",
    projectId: "ducthang-ae432",
    storageBucket: "ducthang-ae432.appspot.com",
    messagingSenderId: "808201087012",
    appId: "1:808201087012:web:72b12d3a413494610e50b1",
    measurementId: "G-DF3E1709M4"
};
firebase.initializeApp(firebaseConfig);

let srcImg;
let imageEdit = '';
let fbBucketName = 'images';

let uploader = document.getElementById('uploader');
let fileButton = document.getElementById('fileButton');

fileButton.addEventListener('change', function (e) {

    console.log('file upload event', e);

    let file = e.target.files[0];

    let storageRef = firebase.storage().ref(`${fbBucketName}/${file.name}`);

    let uploadTask = storageRef.put(file);

    uploadTask.on(firebase.storage.TaskEvent.STATE_CHANGED,
        function (snapshot) {
            let progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
            uploader.value = progress;
            console.log('Upload is ' + progress + '% done');
            switch (snapshot.state) {
                case firebase.storage.TaskState.PAUSED:
                    console.log('Upload is paused');
                    break;
                case firebase.storage.TaskState.RUNNING:
                    console.log('Upload is running');
                    break;
            }
        }, function (error) {
            switch (error.code) {
                case 'storage/unauthorized':
                    break;

                case 'storage/canceled':
                    break;

                case 'storage/unknown':
                    break;
            }
        }, function () {
            let downloadURL = uploadTask.snapshot.downloadURL;
            imageEdit = downloadURL;
            console.log('downloadURL ===>', downloadURL);
            // let divLocation = document.getElementById("imgDiv");
            let img = document.createElement("imgDiv");
            img.src = downloadURL
            srcImg = downloadURL
            console.log('pic ==', imageEdit)
            divLocation.append(img);
        });

});

//Get cai id tren url
let url_string = window.location.href;
let url = new URL(url_string);
let id = url.searchParams.get("id");
showEdit()

function showEdit() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/customers/" + id,
        //xử lý khi thành công
        success: function (data) {
            let img = document.getElementById("imgDiv");
            img.src = data.image;

            document.getElementById("id").value = data.id;
            document.getElementById("name").value = data.nameCustomer;
            // document.getElementById("idCategories").value = data.categories.idCategories;
            selectCategory()
        },
        error: function (err) {
            console.log(err)
        }
    })
}

function editCustomer() {
    if (imageEdit == "") {
        imageEdit = document.getElementById("imgDiv").src;
    }

    let customer = {
        id : document.getElementById("id").value,
        nameCustomer : document.getElementById("name").value,
        image : imageEdit,
        categories : {idCategories : document.getElementById("idCategoryEdit").value}
    }

    $.ajax({
        type: "PUT",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url: "http://localhost:8080/customers/" + customer.id,
        data: JSON.stringify(customer),
        //Xử lý thành công
        success: function () {
            showCustomer()
        },
        error: function (err) {
            console.log(err)
        }
    })
}

// select Categories
function selectCategory() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/customers/categories",
        success: function (data) {
            let str = "";
            for (let i = 0; i < data.length; i++) {
                str += `<option value="${data[i].idCategories}">${data[i].nameCategories}</option>`
            }
            document.getElementById("idCategoryEdit").innerHTML = str;
        },
        error: function (err) {
            console.log(err)
        }
    })
}
// end select Categories

