<script lang="ts">
    import { userToken } from "../stores/userStore";
    import Home from "./Home.svelte";
    import { onMount } from "svelte";
    import { goto } from "$app/navigation";

    onMount(async () => {
        if (!$userToken) {
            await goto("/login");
        }
    });
</script>

{#if $userToken}
    <Home />
{/if}
