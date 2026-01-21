<script lang="ts">
	export let data;

	const { profesionales } = data;
</script>

<h1>Profesionales</h1>

{#if profesionales.length === 0}
	<p>No hay profesionales registrados</p>
{:else}
	<table>
		<thead>
			<tr>
				<th>Nombre</th>
				<th>Email</th>
				<th>Teléfono</th>
				<th>Especialidad</th>
				<th>Activo</th>
			</tr>
		</thead>

		<tbody>
			{#each profesionales as p}
				<tr>
					<td>{p.nombre} {p.apellido}</td>
					<td>{p.email}</td>
					<td>{p.telefono}</td>
					<td>{p.especialidad}</td>
					<td>
						<span class:activo={p.activo}>
							{p.activo ? 'Sí' : 'No'}
						</span>
					</td>
				</tr>
			{/each}
		</tbody>
	</table>
{/if}

<style>
	h1 {
		margin-bottom: 1rem;
	}

	table {
		width: 100%;
		border-collapse: collapse;
		background: white;
		border-radius: 8px;
		overflow: hidden;
	}

	th, td {
		padding: 0.75rem;
		border-bottom: 1px solid #eee;
	}

	th {
		background: #f7f7f7;
		font-weight: 600;
	}

	tr:hover {
		background: #fafafa;
	}

	.activo {
		color: green;
		font-weight: 600;
	}
</style>

