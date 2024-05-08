<script lang="ts">
    import "../app.css"
    import michael from "$lib/assets/Michael-Jackson.svg"
    import Alert from "./Alert.svelte";
    import { onMount } from "svelte";
    import { goto } from "$app/navigation";
    import { token } from "../stores/tokenStore";
    import { blur } from "svelte/transition";

    onMount(async () => {
        if (!$token) {
            await goto("/login");
        }
    });
</script>

<div class="flex flex-row h-screen w-screen">
    <div class="h-full w-full justify-center items-center">
        {#if $token}
            <img src={michael} alt="Michael Jackson picture" class="h-full w-full" transition:blur={{amount: 20, duration: 600}} />
        {/if}
    </div>
    <div class="bg-slate-800 h-full w-full">
        <Alert />
        <slot />
    </div>
</div>