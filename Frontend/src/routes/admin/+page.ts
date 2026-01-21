import type { PageLoad } from './$types';

export const load: PageLoad = async ({ fetch }) => {
	const token = localStorage.getItem('token');

	if (!token) {
		return {
			error: 'No autenticado'
		};
	}

	const headers = {
		Authorization: `Bearer ${token}`
	};

	const [dashboardRes, turnosRes, profesionalesRes, serviciosRes] =
		await Promise.all([
			fetch('http://localhost:8080/admin/dashboard', { headers }),
			fetch('http://localhost:8080/admin/turnos', { headers }),
			fetch('http://localhost:8080/admin/profesionales', { headers }),
			fetch('http://localhost:8080/admin/servicios', { headers })
		]);

	if (!dashboardRes.ok) throw new Error('Error cargando dashboard');

	return {
		dashboard: await dashboardRes.json(),
		turnos: await turnosRes.json(),
		profesionales: await profesionalesRes.json(),
		servicios: await serviciosRes.json()
	};
};
