const API_INVOCATION = 'http://localhost:8084/api/invocations';

let form_invocation = document.getElementById('form-invocation');
let invocation_result = document.getElementById('invocation-result');

let token = "";

let call_api = async (url, method, headers) => {
    let response = await fetch(url, {
        method: method,
        headers: headers,
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

        displayInvocationResult(response);
    } catch (error) {
        error.innerText = `Erreur lors de la récupération des invocations: ${error.message}`;
    }
});

function displayInvocationResult(response) {
    invocation_result.innerHTML = `
        <h4>Monstre invoqué : ${response.nom}</h4>
        <p><strong>Type élémentaire :</strong> ${response.typeElementaire}</p>
        <p><strong>Stats :</strong></p>
        <ul>
            <li>PV : ${response.stats.pv}</li>
            <li>Attaque : ${response.stats.atq}</li>
            <li>Défense : ${response.stats.def}</li>
            <li>Vitesse : ${response.stats.vit}</li>
        </ul>
        <p><strong>Compétences :</strong></p>
        <ul>
            ${response.competences.map(comp => `
                <li>
                    <strong>Nom :</strong> ${comp.nom}<br>
                    <strong>Dégâts de base :</strong> ${comp.degatsBase}<br>
                    <strong>Ratio de dégâts :</strong> ${comp.ratioDegats}<br>
                    <strong>Cooldown :</strong> ${comp.cooldown}<br>
                    <strong>Niveau max :</strong> ${comp.niveauMax}
                </li>
            `).join('')}
        </ul>
    `;
}
