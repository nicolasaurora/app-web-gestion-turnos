import type { PageLoad } from './$types';

export const load: PageLoad = async ({ fetch }) => {
	const token = localStorage.getItem('token');

	const res = await fetch('http://localhost:8080/admin/profesionales', {
		headers: {
			Authorization: `Bearer ${token}`
		}
	});

	if (!res.ok) {
		throw new Error('Error al cargar profesionales');
	}

	const profesionales = await res.json();

	return {
		profesionales
	};
};
