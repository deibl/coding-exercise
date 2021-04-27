function baseUrl() {
  return process.env.REACT_APP_BACKEND_API_BASE_URL;
}

export function getCategories() {
  return fetch(baseUrl() + '/categories')
  .then(res => res.json());
}

export function createCategory(newCategory) {
  return fetch(baseUrl() + '/categories', {
    method: 'POST',
    body: JSON.stringify(newCategory),
    headers: new Headers({'content-type': 'application/json'}),
  });
}

export function updateCategory(updatedCategory) {
  return fetch(baseUrl() + '/categories/' + updatedCategory.domainId, {
    method: 'PUT',
    body: JSON.stringify(updatedCategory),
    headers: new Headers({'content-type': 'application/json'}),
  })
}

export function deleteCategory(categoryToDeleteId) {
  return fetch(baseUrl() + '/categories/' + categoryToDeleteId,
      {method: 'DELETE'})
}
