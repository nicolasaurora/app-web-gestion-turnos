<script>
	let nombre = '';
	let apellido = '';
	let email = '';
	let password = '';
	let telefono = '';
	let error = '';
	let loading = false;

	async function register() {
		error = '';
		loading = true;

		try {
			const res = await fetch('http://localhost:8080/auth/register', {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json'
				},
				body: JSON.stringify({
					nombre,
					apellido,
					email,
					password,
					telefono
				})
			});

			if (!res.ok) {
				throw new Error();
			}

			// Registro exitoso → volver al login
			window.location.href = '/login';

		} catch {
			error = 'No se pudo crear la cuenta';
		} finally {
			loading = false;
		}
	}
</script>




<form on:submit|preventDefault={register}>
	<h1>Crear cuenta</h1>

	<input placeholder="Nombre" bind:value={nombre} required />
	<input placeholder="Apellido" bind:value={apellido} required />
	<input type="email" placeholder="Email" bind:value={email} required />
	<input type="password" placeholder="Contraseña" bind:value={password} required />
	<input placeholder="Teléfono" bind:value={telefono} />

	<button disabled={loading}>
		{loading ? 'Creando cuenta...' : 'Registrarme'}
	</button>

	{#if error}
		<p class="error">{error}</p>
	{/if}

	<p class="login-link">
	¿Ya tenés cuenta?
	<a href="/login">Iniciar sesión</a>
	</p>
</form>







<style>

	
	h1 {
		text-align: center;
		margin-bottom: 1.5rem;
		color: #1f2937;
	}

	form {
		max-width: 360px;
		margin: 3rem auto;
		display: flex;
		flex-direction: column;
		gap: 0.9rem;
		background: #FEEAC9;
		padding: 2rem;
		border-radius: 12px;
		box-shadow: 0 10px 25px rgba(0,0,0,0.05);
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

	.error {
		color: #dc2626;
		text-align: center;
		font-size: 0.85rem;
	}

	.login-link {
		text-align: center;
		font-size: 0.9rem;
		color: #555;
	}

	.login-link a {
		color: tomato;
		font-weight: 500;
		text-decoration: none;
	}

</style>


