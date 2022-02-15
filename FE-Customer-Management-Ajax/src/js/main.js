$(document).ready(function () {
    showCustomer(0);
})

let totalPages = 1;

function showCustomer(pageNumber) {
    if (pageNumber < 0) {
        pageNumber = 0;
    }
    if (pageNumber > totalPages-1) {
        pageNumber = totalPages-1;
    }

    $.ajax({
        type: "GET",
        url: `http://localhost:8080/customers?page=${pageNumber}`,
        //Xử lý thành công
        success: function (data) {
            document.getElementById("tbody").innerHTML = showData(data);
            totalPages = data.totalPages;
        },
        error: function (err) {
            console.log(err)
        }
    })
}

function showData(pageable) {
    let arrCustomer = pageable.content;
    let str = "";

    for (let i = 0; i < arrCustomer.length; i++) {
        str += "<tr>"
        str += `<td>${arrCustomer[i].id}</td>`
        str += `<td>${arrCustomer[i].nameCustomer}</td>`
        str += `<td><img src="${arrCustomer[i].image}" height="220" width="200"></td>`
        str += `<td>${arrCustomer[i].categories.nameCategories}</td>`
        str += `<td><a class="btn btn-sm btn-outline-secondary badge" href="editCustomer.html?id=${arrCustomer[i].id}" id="editLg">Edit</a>
                                <button onclick="modalDelete(${arrCustomer[i].id})" class="btn btn-sm btn-outline-secondary badge"
                                                            type="button"  data-toggle="modal" data-target="#exampleModal"><i class="fa fa-trash"></i></button></td>`
        str += "</tr>"
    }
    str += `<div class="d-flex justify-content-center">
                  <button onclick="showCustomer(${pageable.number-1})">
                    <a class="page-link"><<<<<</a>
                  </button>
                  <b>${pageable.number+1}</b> / <b>${pageable.totalPages}</b>
                  <button onclick="showCustomer(${pageable.number+1})">
                    <a class="page-link">>>>>></a>
                  </button>
                </div>`
    return str;
}

// Delete
function modalDelete(id) {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/customers/" + id,
        //xử lý khi thành công
        success: function (data) {
            document.getElementById("id").value = data.id;
            document.getElementById("name").value = data.nameCustomer;
        },
        error: function (err) {
            console.log(err)
        }
    })
}

function deleteCustomer() {
    let id = document.getElementById("id").value;

    $.ajax({
        type: "DELETE",
        url: `http://localhost:8080/customers/${id}`,
        //Xử lý thành công
        success: function () {
            showCustomer(0)
        },
        error: function (err) {
            console.log(err)
        }
    })
}
// End Delete

// I18N
function showLanguage(lg) {
    $.ajax({
        type: "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url: "http://localhost:8080/customers/language?lg=" + lg,

        success: function (data) {
            document.getElementById("customerLg").innerText = data.customer
            document.getElementById("language").innerText = data.language
            document.getElementById("vietnamese").innerText = data.vietnamese
            document.getElementById("english").innerText = data.english
            document.getElementById("nameLg").innerText = data.name
            document.getElementById("imageLg").innerText = data.image
            document.getElementById("CategoriesLg").innerText = data.categories
            document.getElementById("ActionsLg").innerText = data.actions
            document.getElementById("davidLg").innerText = data.davidDucThang
            document.getElementById("studentOfClassC0921K1Lg").innerText = data.studentOfClassC0921K1
            document.getElementById("newCustomerLg").innerText = data.newCustomer
            // document.getElementById("loyalCustomerLg").innerText = data.loyalCustomer
            // document.getElementById("aNewCustomer").innerText = data.aNewCustomer
            document.getElementById("sureDeleteLg").innerText = data.sureDeleteCustomer
            document.getElementById("searchByNameLg").innerText = data.searchByName
            document.getElementById("editLg").innerText = data.edit
        },
        error: function (err) {
            console.log(err)
        }
    })
}
// End I18N

// search
const searchButton = document.getElementById('search-button');
const searchInput = document.getElementById('search-input');
searchButton.addEventListener('click', () => {
    const inputValue = searchInput.value;
    searchCustomers(inputValue)
});

function searchCustomers(inputValue) {
    $.ajax({
        type: "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url: "http://localhost:8080/customers/search/" + inputValue,
        //xử lý khi thành công
        success: function (data) {
            let str = "";

            for (let i = 0; i < data.length; i++) {
                str += "<tr>"
                str += `<td>${data[i].id}</td>`
                str += `<td>${data[i].nameCustomer}</td>`
                str += `<td><img src="${data[i].image}" height="220" width="200"></td>`
                str += `<td>${data[i].categories.nameCategories}</td>`
                str += `<td><a class="btn btn-sm btn-outline-secondary badge" href="editCustomer.html?id=${data[i].id}" id="editLg">Edit</a>
                                <button onclick="modalDelete(${data[i].id})" class="btn btn-sm btn-outline-secondary badge"
                                                            type="button"  data-toggle="modal" data-target="#exampleModal"><i class="fa fa-trash"></i></button></td>`
                str += "</tr>"
            }
            document.getElementById("tbody").innerHTML = str;
        },
        error: function (err) {
            console.log(err)
        }
    })
}
// End search