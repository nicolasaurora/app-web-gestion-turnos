import { writable } from "svelte/store";

interface UserStore {
    rol: string;
}

export const user = writable<UserStore | null>(null);

export function loginUser(token: string, rol: string) {
    localStorage.setItem("token", token);
    localStorage.setItem("rol", rol);
    user.set({ rol });
}

export function logoutUser() {
    localStorage.removeItem("token");
    localStorage.removeItem("rol");
    user.set(null);
}

export function isAuthenticated(): boolean {
    return !!localStorage.getItem("token");
}