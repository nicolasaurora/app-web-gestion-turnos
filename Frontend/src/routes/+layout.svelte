<script lang="ts">
	import { onMount } from 'svelte';
	import { page } from '$app/stores';
	import { goto } from '$app/navigation';
	import Navbar from '$lib/Navbar.svelte';

	let token: string | null = null;
	let rol: string | null = null;
    let isPublicRoute = false;

	function logout() {
		localStorage.clear();
        
        token = null;
	    rol = null;
	    isPublicRoute = true;

		goto('/login');
	}

	onMount(() => {
		token = localStorage.getItem('token');
		rol = localStorage.getItem('rol');

		const path = $page.url.pathname;

		isPublicRoute = path === '/login' || path === '/register';

		// Rutas públicas
		if (isPublicRoute) return;

		// No logueado
		if (!token) {
			goto('/login');
			return;
		}

		// ADMIN
		if (path.startsWith('/admin') && rol !== 'ADMIN') {
			goto('/login');
		}

		// PROFESIONAL o ADMIN
		if (path.startsWith('/agenda') && rol !== 'ADMIN' && rol !== 'PROFESIONAL') {
			goto('/login');
		}

		// PACIENTE
		if (path.startsWith('/turnos') && rol !== 'PACIENTE') {
			goto('/login');
		}
	});
</script>


{#if !['/login', '/register'].includes($page.url.pathname)}
	<Navbar />
{/if}


<style>
:global(body) {
    font-family: Verdana, Geneva, Tahoma, sans-serif;
	margin: 0;
	background: linear-gradient(135deg, #FF937E, #fdcdaf);
    min-height: 100vh;
}

</style>

<slot />