(async function loadReports() {
  const bills = await apiRequest('/bills/today');
  document.getElementById('reportsBody').innerHTML = bills
    .map(b => `<tr><td>${b.billId}</td><td>${b.customerName}</td><td>${Number(b.amount).toFixed(2)}</td><td>${Number(b.finalAmount).toFixed(2)}</td></tr>`)
    .join('');
})();
