<script lang="ts">
	export let data;

	const { servicios } = data;
</script>

<h1>Servicios</h1>

{#if servicios.length === 0}
	<p>No hay servicios cargados</p>
{:else}
	<table>
		<thead>
			<tr>
				<th>Nombre</th>
				<th>Duración</th>
				<th>Precio</th>
				<th>Activo</th>
			</tr>
		</thead>

		<tbody>
			{#each servicios as s}
				<tr>
					<td>{s.nombre}</td>
					<td>{s.duracion} min</td>
					<td>${s.precio}</td>
					<td>
						<span class:activo={s.activo}>
							{s.activo ? 'Sí' : 'No'}
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
		text-align: center;
	}

	th, td {
		padding: 0.75rem;
		border-bottom: 1px solid #cecece;
	}

	th {
		background: #f7f7f7;
		font-weight: 600;
	}

	tr:hover {
		background: #ffebd9;
	}

	.activo {
		color: green;
		font-weight: 600;
	}
</style>

