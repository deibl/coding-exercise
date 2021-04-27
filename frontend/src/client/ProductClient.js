function baseUrl() {
  return process.env.REACT_APP_BACKEND_API_BASE_URL;
}

export function getProductsByCategory(categoryId) {
  return fetch(baseUrl() + '/products?categoryDomainId=' + categoryId)
  .then(res => res.json())
}

export function createProduct(newProduct, currentCategoryId) {
  return fetch(baseUrl() + '/products?categoryDomainId=' + currentCategoryId, {
    method: 'POST',
    body: JSON.stringify(newProduct),
    headers: new Headers({'content-type': 'application/json'}),
  })
  .then(res => res.json())
}

export function updateProduct(updatedProduct) {
  return fetch(baseUrl() + '/products/' + updatedProduct.domainId, {
    method: 'PUT',
    body: JSON.stringify(updatedProduct),
    headers: new Headers({'content-type': 'application/json'}),
  })
  .then(res => res.json())
}

export function deleteProduct(productToDeleteId) {
  return fetch(baseUrl() + '/products/' + productToDeleteId,
      {method: 'DELETE'})
}
