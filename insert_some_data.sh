#!/bin/bash

URL="http://localhost:8080/api/monstres"

declare -a monstres=(
  '{"id": 1, "nom": "Monstre Test", "description": "Description du monstre de test pour vérifier les contraintes.", "prix": 100}'
  '{"id": 2, "nom": "Viande", "description": "Grosse viande de qualité supérieure pour les amateurs de barbecue.", "prix": 674}'
  '{"id": 3, "nom": "Légumes", "description": "Mélange de légumes bio cultivés localement pour une alimentation saine.", "prix": 50}'
  '{"id": 4, "nom": "Fruits", "description": "Fruits tropicaux frais importés directement des régions tropicales.", "prix": 120}'
  '{"id": 5, "nom": "Boissons", "description": "Jus naturel sans sucre ajouté, parfait pour une hydratation saine.", "prix": 30}'
  '{"id": 6, "nom": "Boissons Naturelles", "description": "Jus naturel sans sucre ajouté, parfait pour une hydratation saine.", "prix": 24}'
  '{"id": 7, "nom": "Monstre de Luxe", "description": "Monstre de luxe pour les occasions spéciales et les cadeaux.", "prix": 500}'
  '{"id": 8, "nom": "Électronique", "description": "Appareils électroniques de haute technologie pour la maison et le bureau.", "prix": 200}'
  '{"id": 9, "nom": "Vêtements", "description": "Vêtements de mode pour hommes, femmes et enfants, adaptés à toutes les saisons.", "prix": 80}'
  '{"id": 10, "nom": "Accessoires", "description": "Accessoires de mode et de maison pour compléter votre style et votre décoration.", "prix": 45}'
)


for monstre in "${monstres[@]}"; do
  curl -X POST -H "Content-Type: application/json" -d "$monstre" "$URL"
  echo
done

