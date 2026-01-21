<script lang="ts">
	export let data;

	const { turnos } = data;
</script>

<h1>Turnos</h1>

{#if turnos.length === 0}
	<p>No hay turnos registrados</p>
{:else}
	<table>
		<thead>
			<tr>
				<th>Fecha</th>
				<th>Hora</th>
				<th>Paciente</th>
				<th>Profesional</th>
				<th>Servicio</th>
				<th>Estado</th>
			</tr>
		</thead>

		<tbody>
			{#each turnos as turno}
				<tr>
					<td>{turno.fecha}</td>
					<td>{turno.hora}</td>
					<td>{turno.pacienteNombre}</td>
					<td>{turno.profesionalNombre}</td>
					<td>{turno.servicioNombre}</td>
					<td>
						<span class:activo={turno.estado === 'ACTIVO'}>
							{turno.estado}
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
		text-align: left;
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

