import { type Writable, writable } from 'svelte/store';
import type { UserAuthentication } from "$lib/model/user-authentication";

export const userToken: Writable<string | null> = writable(null);
export const userId: Writable<string | null> = writable(null);

export const login = ({id, token}: UserAuthentication) => {
    userId.set(id);
    userToken.set(token);
}

export const logout = () => {
    userId.set(null);
    userToken.set(null);
}