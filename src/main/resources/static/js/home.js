
        document.addEventListener('DOMContentLoaded', function() {
            const registerForm = document.getElementById('registerForm');
            const loginForm = document.getElementById('loginForm');
            const loginError = document.getElementById('loginError');
            const authForms = document.getElementById('authForms');
            const usernameDisplay = document.getElementById('usernameDisplay');
            const indexPage = document.getElementById('indexPage');
            const logoutButton = document.getElementById('logoutButton');

            if (registerForm) {
                registerForm.addEventListener('submit', function(event) {
                    event.preventDefault();

                    const name = document.getElementById('registerName').value;
                    const email = document.getElementById('registerEmail').value;
                    const password = document.getElementById('registerPassword').value;
                    const confirmPassword = document.getElementById('registerConfirmPassword').value;

                    if (password !== confirmPassword) {
                        alert('Passwords do not match!');
                        return;
                    }

                    const users = JSON.parse(localStorage.getItem('users')) || [];
                    users.push({ name, email, password });
                    localStorage.setItem('users', JSON.stringify(users));

                    alert('Registration successful!');
                    showLoginForm();
                });
            }

            if (loginForm) {
                loginForm.addEventListener('submit', function(event) {
                    event.preventDefault();

                    const email = document.getElementById('loginEmail').value;
                    const password = document.getElementById('loginPassword').value;

                    const users = JSON.parse(localStorage.getItem('users')) || [];
                    const user = users.find(user => user.email === email && user.password === password);

                    if (user) {
                        alert('Login successful!');
                        localStorage.setItem('loggedInUser', JSON.stringify(user));
                        showHomePage();
                    } else {
                        loginError.style.display = 'block';
                    }
                });
            }

            if (usernameDisplay) {
                const loggedInUser = JSON.parse(localStorage.getItem('loggedInUser'));
                if (loggedInUser) {
                    usernameDisplay.textContent = loggedInUser.name;
                    showHomePage();
                }
            }

            if (logoutButton) {
                logoutButton.addEventListener('click', function() {
                    localStorage.removeItem('loggedInUser');
                    showAuthForms();
                });
            }
        });

        function showRegisterForm() {
            document.getElementById('register').classList.add('show', 'active');
            document.getElementById('login').classList.remove('show', 'active');
            document.getElementById('indexPage').style.display = 'none';
        }

        function showLoginForm() {
            document.getElementById('register').classList.remove('show', 'active');
            document.getElementById('login').classList.add('show', 'active');
            document.getElementById('indexPage').style.display = 'none';
        }

        function showHomePage() {
            window.location.href = 'index.html';
        }

        function showAuthForms() {
            document.getElementById('authForms').style.display = 'block';
            document.getElementById('indexPage').style.display = 'none';
            showLoginForm();
        }
