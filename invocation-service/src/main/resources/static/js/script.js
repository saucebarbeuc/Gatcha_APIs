const API_INVOCATION = 'http://localhost:8084/api/invocations';

let form_invocation = document.getElementById('form-invocation');
let readall_invocation_response = document.getElementById('readall-invocation-response');

let token = "";

let call_api = async (url, method, headers, body) => {
    let response = await fetch(url, {
        method: method,
        headers: headers,
        body: body
    });
    let code = response.status;
    if (!response.ok) {
        throw new Error(`Failed to fetch: ${response.statusText}`);
    }
    return response.json();
}

form_invocation.addEventListener('submit', async (e) => {
    try {
        e.preventDefault();
        let data = new FormData(form_invocation);
        token = data.get('token');

        let response = await call_api(API_INVOCATION, 'GET', {
            'Authorization': token
        });

        readall_invocation_response.innerText = `Monstre invoqué : ${JSON.stringify(response)}`;
    } catch (error) {
        error.innerText = `Erreur lors de la récupération des invocations: ${error.message}`;
    }
});
