<script lang="ts">
    import { api } from "$lib/api";
    import { isAuthenticated } from "$lib/auth";
    import { goto } from '$app/navigation'; 

    interface Turno {
        id: number;
        fechaHoraInicio: string; 
        estado: 'DISPONIBLE' | 'RESERVADO' | string;
        servicio: {
            nombre: string;
        } | null;
    }

    let turnos: Turno[] = []; 
    let error: string = "";

    if (!isAuthenticated()) {
        goto("/login");
    }

    async function cargar() {
        try {
            const data: Turno[] = await api("/turnos", "GET", null, true); 
            turnos = data;
        } catch (e) {
            if (e instanceof Error) {
                error = e.message;
            } else {
                error = "Ocurrió un error desconocido al cargar los turnos.";
            }
        }
    }

    cargar(); 

    async function reservar(id: number) {
        try {
            await api(`/turnos/${id}/reservar`, "PUT", null, true);
            
            cargar();
        } catch (e) {
            if (e instanceof Error) {
                alert(e.message);
            } else {
                alert("Error desconocido al reservar.");
            }
        }
    }
</script>

<h1 class="text-xl font-bold mb-4">Turnos Disponibles</h1>

{#if error}
    <p class="text-red-500">{error}</p>
{/if}

<ul class="space-y-3">
{#each turnos as t (t.id)} 
    <li class="p-4 bg-white rounded-lg shadow flex justify-between">
        <div>
            <p><strong>{t.fechaHoraInicio}</strong></p>
            <p>{t.servicio?.nombre}</p>
        </div>

        {#if t.estado === "DISPONIBLE"}
            <button class="btn btn-primary" on:click={() => reservar(t.id)}>
                Reservar
            </button>
        {:else}
            <span class="text-gray-500">Reservado</span>
        {/if}
    </li>
{/each}
</ul>