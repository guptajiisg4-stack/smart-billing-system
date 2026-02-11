const loginForm = document.getElementById('loginForm');
const logoutBtn = document.getElementById('logoutBtn');

if (loginForm) {
  loginForm.addEventListener('submit', async (event) => {
    event.preventDefault();
    const username = loginForm.username.value.trim();
    const password = loginForm.password.value.trim();
    const errorEl = document.getElementById('loginError');

    if (!username || !password) {
      errorEl.textContent = 'Username and password are required.';
      return;
    }

    if (password.length < 6) {
      errorEl.textContent = 'Password must be at least 6 characters long.';
      return;
    }

    try {
      await apiRequest('/login', {
        method: 'POST',
        body: JSON.stringify({ username, password })
      });
      window.location.href = 'dashboard.html';
    } catch (error) {
      errorEl.textContent = error.message;
    }
  });
}

if (logoutBtn) {
  logoutBtn.addEventListener('click', async () => {
    await apiRequest('/logout', { method: 'POST' });
    window.location.href = 'login.html';
  });
}
