<script lang="ts">
    import Fa from 'svelte-fa';
    import { faXmark } from "@fortawesome/free-solid-svg-icons";
    import { ALERT_TYPE, alertMessage, alertType } from "../stores/alertStore";
    import { fade } from 'svelte/transition'

    $: alertStyle = () => {
        if ($alertType === ALERT_TYPE.SUCCESS) {
            return 'success';
        } else if ($alertType === ALERT_TYPE.DANGER) {
            return 'error';
        } else {
            return 'info';
        }
    }

    function handleClose() {
        alertMessage.set('');
    }

</script>

<div>
    <div class="relative w-full">
        {#if $alertMessage}
            <div class="absolute flex justify-between items-center border-4 h-20 w-3/5
                        right-6 top-8 rounded-lg p-4 text-sm {alertStyle()}"
                transition:fade>
                <div>{$alertMessage}</div>
                <div>
                    <button on:click={handleClose}>
                        <Fa icon={faXmark} size="2x" />
                    </button>
                </div>
            </div>
        {/if}
    </div>
</div>

<style>
    .info {
        color: rgb(6 182 212);
        border-color: rgb(6 182 212);
    }

    .error {
        color: rgb(239 68 68);
        border-color: rgb(239 68 68);
    }

    .success {
        color: rgb(163 230 53);
        border-color: rgb(163 230 53);
    }
</style>