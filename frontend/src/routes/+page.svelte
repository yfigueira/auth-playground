<script lang="ts">
    import Button from "$lib/form-elements/Button.svelte";
    import { goto } from "$app/navigation";
    import { token, logout } from "../stores/tokenStore";

    async function handleClick() {
        logout();
        await goto("/login");
    }
</script>

{#if $token}
    <h1 class="text-white">Home Page</h1>
    <Button on:click={handleClick}>Logout</Button>
{/if}
