const API_MONSTER = 'http://localhost:8083/api/monstres';
const API_JOUEUR = 'http://localhost:8082/api/joueurs';
const API_AUTH = 'http://localhost:8081/api/users';
const API_INVOCATION = 'http://localhost:8084/api/invocations';

let error = document.getElementById('error');

let form_register = document.getElementById('form-register');
let form_login = document.getElementById('form-login');
let get_joueur = document.getElementById('get-joueur');
let get_invocation = document.getElementById('get-invocation');
let form_monster = document.getElementById('form-monster');
let get_monsters = document.getElementById('get-monsters');

let readall_monster_response = document.getElementById('readall-monster-response');
let readall_joueur_response = document.getElementById('readall-joueur-response');
let readall_invocation_response = document.getElementById('readall-invocation-response');
let connected = document.getElementById('connected');

connected.innerText = "Non Connecté";

let token = "";
let code = "";

let call_api = async (url, method, header, body) => {
    let response = await fetch(url, {
        method: method,
        headers: header,
        body: body
    });
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

    error.innerText = `${code} - ${Object.entries(response)[0][0]}: ${Object.entries(response)[0][1]}`;
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
    error.innerText = `${code} - ${Object.entries(response)[0][0]}: ${Object.entries(response)[0][1]}`;

});

form_monster.addEventListener('submit', async (e) => {
    e.preventDefault();
    let data = new FormData(form_monster);
    let json = JSON.stringify({
        nom: data.get("nom"),
        niveau: 0,
        experiences: 0,
        typeElementaire: "FEU",
        stats: {
            pv: 1,
            atq: 1,
            def: 1,
            vit: 1
        },
        competences: [
            {
                nom: "string",
                degatsBase: 1,
                ratioDegats: 1,
                cooldown: 1,
                niveau: 1,
                niveauMax: 1
            }
        ]
    });
    console.log(json);
    let response = await call_api(API_MONSTER, 'POST', {
        'Content-Type': 'application/json',
        'Authorization': token
    }, json);
    error.innerText = `${code} - ${response.message}`;
});

get_monsters.addEventListener('click', async (e) => {
    let response = await fetch(API_MONSTER, {
        mode: 'no-cors',
        method: 'GET',
        headers: {
            'Authorization': token
        },
    });
    let data = await response.json();

    readall_monster_response.innerText = "";

    if (response.status != 200) {
        error.innerText = `${response.status} - ${Object.entries(data)[0][0]}: ${Object.entries(data)[0][1]}`;
    } else {
        data.forEach(element => {
            let li = document.createElement('li');
            li.innerText = `${element.nom}: ${element.description}`;
            readall_monster_response.appendChild(li);
        });
    }

});

get_joueur.addEventListener('click', async (e) => {
    try {
        let response = await fetch(API_JOUEUR, {
            method: 'GET',
            headers: {
                'Authorization': token
            },
        });

        if (response.status !== 200) {
            let data = await response.json();
            error.innerText = `${response.status} - ${Object.entries(data)[0][0]}: ${Object.entries(data)[0][1]}`;
        } else {
            let data = await response.json();
            readall_joueur_response.innerText = "";

            data.forEach(element => {
                let li = document.createElement('li');
                li.innerText = `${element.nom}: ${element.prenom}`;
                readall_joueur_response.appendChild(li);
            });
        }
    } catch (error) {
        error.innerText = `Erreur lors de la récupération des joueurs: ${error.message}`;
    }
});

get_invocation.addEventListener('click', async (e) => {
    try {
        let response = await fetch(API_INVOCATION, {
            mode: 'no-cors',
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });

        if (response.status !== 200) {
            let data = await response.json();
            error.innerText = `${response.status} - ${Object.entries(data)[0][0]}: ${Object.entries(data)[0][1]}`;
        } else {
            let data = await response.json();
            readall_invocation_response.innerText = "";

            data.forEach(element => {
                let li = document.createElement('li');
                li.innerText = `${element.nom}: ${element.description}`;
                readall_invocation_response.appendChild(li);
            });
        }
    } catch (error) {
        error.innerText = `Erreur lors de la récupération des invocations: ${error.message}`;
    }
});
