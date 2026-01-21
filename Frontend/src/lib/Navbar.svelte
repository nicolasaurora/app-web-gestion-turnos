<script lang="ts">
	import { goto } from '$app/navigation';

	let rol: string | null = null;

	// Solo corre en el navegador
	if (typeof window !== 'undefined') {
		rol = localStorage.getItem('rol');
	}

	function logout() {
		localStorage.clear();
		goto('/login');
	}
</script>

<nav>
	<div class="links">
		{#if rol === 'ADMIN'}
            <a href="/admin">Dashboard</a>
            <a href="/admin/turnos">Turnos</a>
            <a href="/admin/profesionales">Profesionales</a>
            <a href="/admin/servicios">Servicios</a>
        {/if}

		{#if rol === 'PROFESIONAL'}
			<a href="/agenda">Agenda</a>
			<a href="/turnos">Turnos</a>
		{/if}

		{#if rol === 'PACIENTE'}
			<a href="/turnos">Mis turnos</a>
		{/if}
	</div>

	<button on:click={logout}>Cerrar sesión</button>
</nav>

<style>
	nav {
		display: flex;
		justify-content: space-between;
		align-items: center;
		padding: 1rem 2rem;
		background: #ffffffcc;
		backdrop-filter: blur(8px);
		box-shadow: 0 2px 8px rgba(0,0,0,0.1);
	}

	.links {
		display: flex;
		gap: 1rem;
	}

	a {
		text-decoration: none;
		color: #333;
		font-weight: 500;
	}

	a:hover {
		color: #ff7a59;
	}

	button {
		background: #ff7a59;
		color: white;
		border: none;
		padding: 0.5rem 1rem;
		border-radius: 6px;
		cursor: pointer;
	}

    button:hover {
		background: #ff4d4d;
	}
</style>
