var table = document.getElementById("buddiesTable");
var submitButton = document.getElementById("submitButton");
var addressBookLink = null;
createNewAddressBook();

function createNewAddressBook(){
    fetch('http://localhost:8080/addressbook', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({})
    }).then(function (value) {
        value.json().then(function (addressbook) {
            addressBookLink = addressbook._links.self.href;
        });
    });
};

function addBuddy(e) {
    e.preventDefault();
    var name = document.getElementById("buddyName").value;
    var buddyNumber = document.getElementById("buddyNumber").value;
    var buddyAddress = document.getElementById("buddyAddress").value;

    fetch('http://localhost:8080/buddy', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            "name": name,
            "phoneNumber": buddyNumber
            "address": buddyAddress
        })
    }).then(function (value) {
        value.json().then(function (buddy) {
            var buddyAddressBookUrl = buddy._links.addressBook.href;

            fetch(buddyAddressBookUrl, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'text/uri-list'
                },
                body: addressBookLink
            }).then(function (value1) {
                attachToUI(buddy.name, buddy.phoneNumber, buddy.address);
            })
        });
    });
}

function attachToUI(name, phoneNumber, address) {
    var row =  document.createElement('tr')
    row.innerHTML = "<td>"+name+"</td><td>"+phoneNumber+"</td><td>"+address+"</td>";
    table.tBodies[0].insertBefore(row,table.rows[table.rows.length-1])
}
submitButton.addEventListener("click", addBuddy);