import { API_URL } from "$lib/constants";

const enum HttpMethod {
    GET = 'GET',
    POST = 'POST',
    PUT = 'PUT',
    DELETE = 'DELETE'
}

interface RequestOptions {
    method: HttpMethod;
    path: string;
    data?: Record<string, any>;
    token?: string;
}

async function send({method, path, data, token}: RequestOptions) {
    const headers = new Headers(data ? [['Content-Type', 'application/json']] : []);
    if (token) {
        headers.set('Authorization', `Bearer ${token}`)
    }

    const requestData: RequestInit = {
        method: method,
        headers: headers,
        body: data ? JSON.stringify(data) : null,
    }

    return await fetch(`${API_URL}/${path}`, requestData)
        .then(response => response)
        .catch(err => {
            console.log(err);
            throw new Error(err);
        })
}

export const post = (path: string, data?: {}, token?: string) => {
    return send({method: HttpMethod.POST, path, data, token})
}

export const get = (path: string, token?: string) => {
    return send({method: HttpMethod.GET, path, token})
}