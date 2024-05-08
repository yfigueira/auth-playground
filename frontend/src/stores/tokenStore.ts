import { type Writable, writable } from 'svelte/store';

export const token: Writable<string | null> = writable(null);

export const login = (tokenValue: string) => {
    token.set(tokenValue);
}

export const logout = () => {
    token.set(null);
}