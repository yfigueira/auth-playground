<script lang="ts">
    import Fa from 'svelte-fa'
    import { faChevronLeft } from "@fortawesome/free-solid-svg-icons";

    export let label: string;
    export let inputId: string;
    export let focused: boolean;
</script>

<div class="text-zinc-100 flex gap-4 items-center">
    <label for={inputId}>{label}</label>
    {#if focused}
        <Fa icon={faChevronLeft} size="sm" />
    {/if}
</div>
