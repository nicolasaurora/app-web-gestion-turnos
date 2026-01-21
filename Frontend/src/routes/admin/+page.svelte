<script lang="ts">
	export let data;
</script>

<h1>Panel de Administración</h1>

{#if data.error}
	<p class="error">{data.error}</p>
{:else}
	<p>Bienvenida/o <strong>{data.dashboard.email}</strong></p>

	<div class="cards">
		<div class="card">Turnos: {data.dashboard.totalTurnos}</div>
		<div class="card">
			Profesionales: {data.dashboard.totalProfesionales}
		</div>
		<div class="card">Servicios: {data.dashboard.totalServicios}</div>
	</div>
{/if}



<style>
	h1 {
		margin-bottom: 1rem;
	}

	.cards {
		display: grid;
		grid-template-columns: repeat(auto-fit, minmax(180px, 1fr));
		gap: 1rem;
		margin-top: 1rem;
	}

	.card {
		background: white;
		padding: 1.5rem;
		border-radius: 12px;
		box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
		font-size: 1.1rem;
		font-weight: 600;
	}

	.error {
		color: red;
	}
</style>

