let url = "http://localhost:8080/api/v2/students";

let init = {
    mode: 'cors',
    method: 'get',
    headers: {
        'Content-Type': 'application/json; charset=UTF-8',
    }
};

function loadAllStudents() {
    fetch(url)
        .then(response => response.json())
        .then(json => createTable(json));
}

function createTable(response) {
    let result = "<table>";
    result += "<th>ID</th><th>First Name</th><th>Last Name</th>";

    // the response is an array
    for (let i = 0; i < response.length; i++) {
        result += `<tr><td>${response[i].id}</td><td>${response[i].firstName}</td><td>${response[i].lastName}</td></tr>`;
    }

    result += "</table>";
    document.getElementById("list").innerHTML = result;
}

function loadOneStudent() {
    let id = document.getElementById("student_id").value;

    fetch(url + "/" + id)
        .then(response => response.json())
        .then(json => prepareStudent(json))
        .catch((json) => { if (!json.ok) clearForm() });
}

function prepareStudent(json) {
    document.getElementById("first_name").value = json.firstName;
    document.getElementById("last_name").value = json.lastName;
}

function clearForm() {
    document.getElementById("first_name").value = '';
    document.getElementById("last_name").value = '';
}

function addStudent() {
    let student = {
        "firstName": document.getElementById("first_name").value,
        "lastName": document.getElementById("last_name").value
    };

    init.method = 'post';
    init.body = JSON.stringify(student);

    fetch(url, init).then(() => loadAllStudents());
}

function updateStudent() {
    let student = {
        "id": document.getElementById("student_id").value,
        "firstName": document.getElementById("first_name").value,
        "lastName": document.getElementById("last_name").value
    };

    init.method = 'put';
    init.body = JSON.stringify(student);

    fetch(url, init).then(() => loadAllStudents());
}

function deleteStudent() {
    let id = document.getElementById("student_id").value;
    init.method = 'delete';
    fetch(url + "/" + id, init).then(() => loadAllStudents());
}

