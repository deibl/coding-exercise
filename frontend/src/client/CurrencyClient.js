function baseUrl() {
  return process.env.REACT_APP_BACKEND_API_BASE_URL;
}

export function getCurrencies() {
  return fetch(baseUrl() + '/currencies')
  .then(res => res.json())
}
