import {type Writable, writable} from 'svelte/store';

export const alertMessage: Writable<string> = writable('');
export enum ALERT_TYPE {
    INFO,
    DANGER,
    SUCCESS
}
export const alertType: Writable<ALERT_TYPE> = writable(ALERT_TYPE.INFO);

export const displayAlert = (message: string,
                             type: ALERT_TYPE = ALERT_TYPE.INFO): void => {
    alertMessage.set(message);
    alertType.set(type);

    setTimeout(() => {
        alertMessage.set('');
        alertType.set(ALERT_TYPE.INFO);
    }, 3000);
}