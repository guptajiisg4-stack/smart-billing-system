(function renderInvoice() {
  const bill = JSON.parse(localStorage.getItem('latestInvoice') || 'null');
  if (!bill) return;

  document.getElementById('invoiceBillId').textContent = bill.billId;
  document.getElementById('invoiceCustomer').textContent = bill.customerName;
  document.getElementById('invoiceDate').textContent = bill.date;
  document.getElementById('invoiceSubtotal').textContent = Number(bill.amount).toFixed(2);
  document.getElementById('invoiceDiscount').textContent = Number(bill.discount).toFixed(2);
  document.getElementById('invoiceFinal').textContent = Number(bill.finalAmount).toFixed(2);

  document.getElementById('invoiceItems').innerHTML = bill.items
    .map(item => `<tr><td>${item.productName}</td><td>${item.quantity}</td><td>${Number(item.price).toFixed(2)}</td><td>${(item.price * item.quantity).toFixed(2)}</td></tr>`)
    .join('');
})();
