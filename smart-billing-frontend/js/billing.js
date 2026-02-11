const customerSelect = document.getElementById('customerSelect');
const addItemBtn = document.getElementById('addItemBtn');
const billItemsContainer = document.getElementById('billItems');
const discountInput = document.getElementById('discount');
const billAmountEl = document.getElementById('billAmount');
const finalAmountEl = document.getElementById('finalAmount');
const billForm = document.getElementById('billForm');
let products = [];

function calculateTotals() {
  const rows = billItemsContainer.querySelectorAll('.bill-item-row');
  let amount = 0;
  rows.forEach(row => {
    const product = products.find(p => p.productId === Number(row.querySelector('.product-select').value));
    const qty = Number(row.querySelector('.qty-input').value || 0);
    amount += (product?.price || 0) * qty;
  });
  const discount = Number(discountInput.value || 0);
  billAmountEl.textContent = amount.toFixed(2);
  finalAmountEl.textContent = Math.max(amount - discount, 0).toFixed(2);
}

function productOptions() {
  return products.map(p => `<option value="${p.productId}">${p.productName} (${p.quantity})</option>`).join('');
}

function addLine() {
  const row = document.createElement('div');
  row.className = 'bill-item-row form-grid';
  row.innerHTML = `
    <select class="product-select">${productOptions()}</select>
    <input class="qty-input" type="number" min="1" value="1" />
  `;
  row.addEventListener('input', calculateTotals);
  billItemsContainer.appendChild(row);
  calculateTotals();
}

(async function init() {
  const [customerPage, productPage] = await Promise.all([
    apiRequest('/customers?page=0&size=100'),
    apiRequest('/products?page=0&size=100')
  ]);
  products = productPage.content;
  customerSelect.innerHTML = customerPage.content.map(c => `<option value="${c.customerId}">${c.firstName} ${c.lastName}</option>`).join('');
  addLine();
})();

addItemBtn.addEventListener('click', addLine);
discountInput.addEventListener('input', calculateTotals);

billForm.addEventListener('submit', async (event) => {
  event.preventDefault();
  const items = [...billItemsContainer.querySelectorAll('.bill-item-row')].map(row => ({
    productId: Number(row.querySelector('.product-select').value),
    quantity: Number(row.querySelector('.qty-input').value)
  }));
  const payload = {
    customerId: Number(customerSelect.value),
    discount: Number(discountInput.value || 0),
    items
  };
  const bill = await apiRequest('/bills', { method: 'POST', body: JSON.stringify(payload) });
  localStorage.setItem('latestInvoice', JSON.stringify(bill));
  window.location.href = 'invoice.html';
});
