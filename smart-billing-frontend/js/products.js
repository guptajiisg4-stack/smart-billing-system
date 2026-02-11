const productForm = document.getElementById('productForm');
const productTableBody = document.getElementById('productTableBody');

async function loadProducts() {
  const page = await apiRequest('/products?page=0&size=100');
  productTableBody.innerHTML = page.content.map(p => `
    <tr class="${p.quantity < 5 ? 'low-stock' : ''}">
      <td>${p.productName}</td>
      <td>${p.category}</td>
      <td>${Number(p.price).toFixed(2)}</td>
      <td>${p.quantity}</td>
      <td>
        <button onclick="deleteProduct(${p.productId})" class="btn-secondary">Delete</button>
      </td>
    </tr>
  `).join('');
}

if (productForm) {
  productForm.addEventListener('submit', async (event) => {
    event.preventDefault();
    const payload = Object.fromEntries(new FormData(productForm).entries());
    payload.price = Number(payload.price);
    payload.quantity = Number(payload.quantity);
    await apiRequest('/products', { method: 'POST', body: JSON.stringify(payload) });
    productForm.reset();
    loadProducts();
  });
}

async function deleteProduct(id) {
  await apiRequest(`/products/${id}`, { method: 'DELETE' });
  loadProducts();
}

window.deleteProduct = deleteProduct;
loadProducts();
