<script>
	let email = '';
	let password = '';
	let error = '';
	let loading = false;

	async function login() {
		error = '';
		loading = true;

		try {
			const res = await fetch('http://localhost:8080/auth/login', {
				method: 'POST',
				headers: { 'Content-Type': 'application/json' },
				body: JSON.stringify({ email, password })
			});

			if (!res.ok) {
				throw new Error();
			}

			const data = await res.json();

			localStorage.setItem('token', data.token);
			localStorage.setItem('rol', data.rol);

			if (data.rol === 'ADMIN') {
				window.location.href = '/admin';
			} else if (data.rol === 'PROFESIONAL') {
				window.location.href = '/agenda';
			} else {
				window.location.href = '/turnos';
			}
		} catch {
			error = 'Credenciales inválidas';
		} finally {
			loading = false;
		}
	}
</script>

<div class="login-container">
	<div class="login-card">
		<h1>Iniciar sesión</h1>

		<form on:submit|preventDefault={login}>
			<div class="field">
				<label>Email</label>
				<input type="email" bind:value={email} required />
			</div>

			<div class="field">
				<label>Contraseña</label>
				<input type="password" bind:value={password} required />
			</div>

			<button disabled={loading}>
				{loading ? 'Ingresando…' : 'Ingresar'}
			</button>

			<p class="register">
				¿No tenés cuenta?
				<a href="/register">Crear cuenta</a>
			</p>

			{#if error}
				<p class="error">{error}</p>
			{/if}
		</form>
	</div>
</div>

<style>

	.login-container {
		min-height: 100vh;
		display: flex;
		align-items: center;
		justify-content: center;
		background: linear-gradient(135deg, #FF937E, #fdcdaf);
	}

	.login-card {
		background: #FEEAC9;
		padding: 2.5rem;
		border-radius: 12px;
		width: 100%;
		max-width: 380px;
		box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
	}

	h1 {
		text-align: center;
		margin-bottom: 1.5rem;
		color: #1f2937;
	}

	form {
		display: flex;
		flex-direction: column;
		gap: 1.2rem;
	}

	.field {
		display: flex;
		flex-direction: column;
		gap: 0.3rem;
	}

	label {
		font-size: 1rem;
		color: #374151;
	}

	input {
		padding: 0.7rem;
		border-radius: 8px;
		border: 1px solid #d1d5db;
		font-size: 0.95rem;
		color: tomato;
	}

	input:focus {
		outline: none;
		box-shadow: 0 0 0 2.3px rgba(255, 137, 117, 0.2);
	}

	button {
		margin-top: 0.5rem;
		padding: 0.8rem;
		border-radius: 8px;
		border: none;
		background-color: rgb(253, 155, 138);
		color: white;
		font-weight: 600;
		font-size: 0.9rem;
		cursor: pointer;
	}

	button:disabled {
		opacity: 0.7;
		cursor: not-allowed;
	}

	button:hover:not(:disabled) {
		background-color: tomato;
	}

	.register {
		text-align: center;
		font-size: 0.9rem;
		color: #555;
	}

	.register a {
		color: tomato;
		font-weight: 500;
		text-decoration: none;
	}

	.register a:hover {
		text-decoration: underline;
	}

	.error {
		text-align: center;
		color: #dc2626;
		font-size: 0.9rem;
	}
</style>
