/**
 * Adresse relative utilisée par Angular.
 *
 * Les requêtes /api sont transmises à Spring Boot
 * par le proxy défini dans proxy.conf.json.
 */
export const ApiConfig = {
  BASE_URL: '/api'
} as const;
