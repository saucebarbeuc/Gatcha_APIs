curl -s -X POST 'http://localhost:8081/api/users' \
        -H 'Content-Type: application/json' \
        -d '{
        "username": "jackson",
        "password": "five"
}'

curl -s -X POST 'http://localhost:8081/api/users/login' \
        -H 'Content-Type: application/json' \
        -d '{
        "username": "jackson",
        "password": "five"
}'

# verifier 

curl -X 'POST' \
  'http://localhost:8084/api/invocations' \
  -H 'accept: */*' \
  -H 'Authorization: MftpWGsRGC7A7tOTrEJO9JEGEoFsagMbwhMVPCFgrtPDDTuUoL1W6j5839Aozk5y' \
  -H 'Content-Type: application/json' \
  -d '{
  "taux": 0,
  "monstreDto": {
    "nom": "string",
    "niveau": 0,
    "experiences": 0,
    "typeElementaire": "FEU",
    "stats": {
      "pv": 0,
      "atq": 0,
      "def": 0,
      "vit": 0
    },
    "competences": [
      {
        "nom": "string",
        "degatsBase": 0,
        "ratioDegats": 0,
        "cooldown": 0,
        "niveau": 0,
        "niveauMax": 0
      }
    ]
  }
}'