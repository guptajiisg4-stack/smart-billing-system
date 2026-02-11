(async function loadDashboard() {
  try {
    const [customers, products, todayBills] = await Promise.all([
      apiRequest('/customers?page=0&size=1'),
      apiRequest('/products?page=0&size=1'),
      apiRequest('/bills/today')
    ]);

    const totalSales = todayBills.reduce((sum, bill) => sum + bill.finalAmount, 0);
    document.getElementById('todaySales').textContent = totalSales.toFixed(2);
    document.getElementById('totalCustomers').textContent = customers.totalElements;
    document.getElementById('totalProducts').textContent = products.totalElements;
    document.getElementById('billsToday').textContent = todayBills.length;

    const labels = todayBills.map((bill) => `#${bill.billId}`);
    const values = todayBills.map((bill) => bill.finalAmount);
    const canvas = document.getElementById('salesChart');
    if (canvas && labels.length && window.Chart) {
      new Chart(canvas, {
        type: 'bar',
        data: { labels, datasets: [{ label: 'Sales', data: values, backgroundColor: '#0ea5a4' }] }
      });
    }
  } catch (err) {
    console.error(err);
  }
})();
