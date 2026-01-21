import type { PageLoad } from './$types';

export const load: PageLoad = async ({ fetch }) => {
	const token = localStorage.getItem('token');

	const res = await fetch('http://localhost:8080/admin/turnos', {
		headers: {
			Authorization: `Bearer ${token}`
		}
	});

	if (!res.ok) {
		throw new Error('Error al cargar turnos');
	}

	const turnos = await res.json();

	return {
		turnos
	};
};