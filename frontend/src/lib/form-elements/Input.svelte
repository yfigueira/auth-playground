<script lang="ts">
    import InputLabel from "$lib/form-elements/InputLabel.svelte";

    export let type = 'text';
    export let id = '';
    export let value = '';
    export let error = '';
    export let label = '';

    let focused = false;
    let currentlyAccessed = false;

    export const focus = () => {
        focused = true;
        currentlyAccessed = true;
    }
    export const blur = () => {
        focused = value != null && value !== '';
        currentlyAccessed = false;
    }

    function typeAction(node: any): void {
        node.type = type;
    }
</script>

<div class="flex flex-col w-1/2">
    <InputLabel label={label} inputId={id} focused={currentlyAccessed}/>
    <input  use:typeAction
            id={id}
            class="h-12 p-2 border-2 rounded-xl bg-zinc-200 text-ellipsis
                    {focused ? 'focused' : 'standard'}
                    {error ? 'error' : ''}
                    {type === 'password' ? 'password' : 'standard'}"
            bind:value={value}
            on:focus={focus}
            on:blur={blur}
            on:change
    />
    {#if error}
        <span class="w-full text-red-500 text-right text-sm">{error}</span>
    {/if}
</div>

<style>
    .standard {
        border-color: rgb(161 161 170);
    }

    .focused {
        outline: none;
        border-color: rgb(82 82 91);
        color: rgb(63 63 70);
    }

    .error {
        color: rgb(239 68 68);
        border-color: rgb(239 68 68);
    }

    .password {
        font-size: 1.5rem;
        letter-spacing: 2px;
    }
</style>