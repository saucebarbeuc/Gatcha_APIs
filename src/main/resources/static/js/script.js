const API_MONSTRE = 'http://localhost:8080/api/monstres';
const API_AUTH = 'http://localhost:8080/api/users';

let error = document.getElementById('error');

let form_register = document.getElementById('form-register');
let form_login = document.getElementById('form-login');
let form_monstre = document.getElementById('form-monstre');
let get_monstres = document.getElementById('get-monstres');

let readall_monster_response = document.getElementById('readall-monster-response');
let connected = document.getElementById('connected');

connected.innerText = "Non  Connecté"

let token = ""
let code = ""

let call_api = async (url, method, header, body) => {
    let response = await fetch(url, {
        method: method,
        headers: header,
        body: body
    })
    code = response.status;
    return response.json();

}

form_register.addEventListener('submit', async (e) => {
    e.preventDefault();
    let data = new FormData(form_register);
    let json = JSON.stringify(Object.fromEntries(data.entries()));
    let response = await call_api(API_AUTH, 'POST', {
        'Content-Type': 'application/json'
    }, json);
    
    error.innerText = `${code} - ${Object.entries(response)[0][0]}: ${Object.entries(response)[0][1]}`
});

form_login.addEventListener('submit', async (e) => {
    e.preventDefault();
    let data = new FormData(form_login);
    let json = JSON.stringify(Object.fromEntries(data.entries()));
    let response = await call_api(API_AUTH + '/login', 'POST', {
        'Content-Type': 'application/json'
    }, json);

    token = response.token;
    let s =  Object.fromEntries(data.entries()).username;
    let username = String(s[0]).toUpperCase() + String(s).slice(1);

    connected.innerText = "Bonjour " + username;
    error.innerText = `${code} - ${Object.entries(response)[0][0]}: ${Object.entries(response)[0][1]}`

});

form_monstre.addEventListener('submit', async (e) => {
    e.preventDefault();
    let data = new FormData(form_monstre);
    let json = JSON.stringify(Object.fromEntries(data.entries()));
    let response = await call_api(API_MONSTRE, 'POST', {
        'Content-Type': 'application/json',
        'Authorization': token
    }, json);

    error.innerText = `${code} - ${Object.entries(response)[0][0]}: ${Object.entries(response)[0][1]}`
});

get_monstres.addEventListener('click', async (e) => {
    let response = await fetch(API_MONSTRE, {
        method: 'GET',
        headers: {
            'Authorization': token
        },
    })
    let data = await response.json();

    readall_monster_response.innerText = "";

    if (response.status != 200) {
        error.innerText = `${response.status} - ${Object.entries(data)[0][0]}: ${Object.entries(data)[0][1]}`
        
    } else {

        data.forEach(element => {
            let li = document.createElement('li');
            li.innerText = `${element.nom}: ${element.description}`;
            readall_monster_response.appendChild(li);
        });
    }


});