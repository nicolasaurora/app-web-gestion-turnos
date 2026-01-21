const API_URL = "http://localhost:8080";

export async function api(
    path: string, 
    method: string = "GET", 
    body: any = null, 
    auth: boolean = false
) {
    const headers: Record<string, string> = { "Content-Type": "application/json" };

    if (auth) {
        const token = localStorage.getItem("token");
        if (token) headers["Authorization"] = `Bearer ${token}`;
    }

    const res = await fetch(API_URL + path, {
        method,
        headers,
        body: body ? JSON.stringify(body) : null
    });

    if (!res.ok) {
        const error = await res.text();
        throw new Error(error || "Error en la API");
    }

    return res.json().catch(() => null);
}
