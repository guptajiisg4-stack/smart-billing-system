const customerForm = document.getElementById('customerForm');
const customerTableBody = document.getElementById('customerTableBody');
const customerSearch = document.getElementById('customerSearch');
let customerCache = [];

function renderCustomers(data) {
  const term = (customerSearch?.value || '').toLowerCase();
  customerTableBody.innerHTML = data
    .filter(c => `${c.firstName} ${c.lastName}`.toLowerCase().includes(term) || c.phoneNumber.includes(term))
    .map(c => `<tr><td>${c.firstName} ${c.middleName || ''} ${c.lastName}</td><td>${c.phoneNumber}</td><td>${c.totalVisits}</td><td>${c.lastVisit || '-'}</td></tr>`)
    .join('');
}

async function loadCustomers() {
  const page = await apiRequest('/customers?page=0&size=50');
  customerCache = page.content;
  renderCustomers(customerCache);
}

if (customerForm) {
  customerForm.addEventListener('submit', async (event) => {
    event.preventDefault();
    const payload = Object.fromEntries(new FormData(customerForm).entries());
    await apiRequest('/customers', { method: 'POST', body: JSON.stringify(payload) });
    customerForm.reset();
    loadCustomers();
  });
}

if (customerSearch) customerSearch.addEventListener('input', () => renderCustomers(customerCache));
loadCustomers();
